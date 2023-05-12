package swacademy.wbbackend.domain.web.servlet;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import swacademy.wbbackend.domain.member.Member;
import swacademy.wbbackend.domain.member.MemberService;
import swacademy.wbbackend.domain.web.security.JwtTokenProvider;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TestController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    @PostMapping("/api/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        Member member = memberService.findMemberByUsername(username);
        if (!passwordEncoder.matches(password, member.getPassword())) {
            System.out.println("비밀번호 오류");
        }

        String token = jwtTokenProvider.createToken(member.getUsername(), List.of(member.getAuthority()));
        String refreshToken = jwtTokenProvider.createRefreshToken(member.getUsername(), List.of(member.getAuthority()));
        response.setHeader("X-AUTH-TOKEN", token);
        response.setHeader("X-REFRESH-TOKEN", refreshToken);

        Cookie cookie = new Cookie("X-AUTH-TOKEN", token);
        Cookie cookie2 = new Cookie("X-REFRESH-TOKEN", refreshToken);
        response.addCookie(cookie);
        response.addCookie(cookie2);

        return token;
    }

    @GetMapping("/api/logout")
    public void logout(HttpServletResponse resp) throws IOException {

        Cookie cookie = new Cookie("X-AUTH-TOKEN", null);
        cookie.setMaxAge(0);
        cookie.setPath("/api");
        resp.addCookie(cookie);

        Cookie cookie1 = new Cookie("X-REFRESH-TOKEN", null);
        cookie.setMaxAge(0);
        cookie.setPath("/api");
        resp.addCookie(cookie1);
        resp.sendRedirect("/");
    }
}
