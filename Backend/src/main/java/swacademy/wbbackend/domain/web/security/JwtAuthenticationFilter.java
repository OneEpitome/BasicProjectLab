package swacademy.wbbackend.domain.web.security;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import swacademy.wbbackend.domain.member.Member;
import swacademy.wbbackend.domain.member.MemberService;
import swacademy.wbbackend.domain.web.security.JwtRefreshToken.JwtRefreshTokenRepository;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;
    private final JwtRefreshTokenRepository jwtRefreshTokenRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 헤더에서 JWT 를 받아옵니다.
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        String refreshToken = jwtTokenProvider.resolveRefreshToken((HttpServletRequest) request);

        if (token != null && jwtTokenProvider.validateToken(token)) {
            // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            // SecurityContext 에 Authentication 객체를 저장합니다.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else if (refreshToken != null && jwtTokenProvider.validateRefreshToken(refreshToken)) {
            updateAccessToken(httpServletResponse, refreshToken);
            Authentication authentication = jwtTokenProvider.getAuthenticationFromRefreshToken(refreshToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

    private void updateAccessToken(HttpServletResponse httpServletResponse, String refreshToken) {
        Member member = memberService.findMemberByUsername(jwtTokenProvider.getUserPkFromRefreshToken(refreshToken));

        String newToken = jwtTokenProvider.createToken(member.getUsername(), List.of(member.getAuthority()));
        httpServletResponse.setHeader("X-AUTH-TOKEN", newToken);
        Cookie cookie = new Cookie("X-AUTH-TOKEN", newToken);
        httpServletResponse.addCookie(cookie);
    }
}