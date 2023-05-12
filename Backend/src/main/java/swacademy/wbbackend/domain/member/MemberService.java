package swacademy.wbbackend.domain.member;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MemberService implements UserDetailsService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void createMember(String username, String password) {
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(new BCryptPasswordEncoder().encode(password));
        member.setCreatedAt(LocalDateTime.now());
        member.setAuthority(Role.ROLE_USER);

        memberRepository.save(member);
    }

    public Member findMemberByUsername(String username) {
        return memberRepository.findMemberByUsername(username).get();
    }

    public Long findMemberIdByUsername(String username) {
        return memberRepository.findMemberByUsername(username).get().getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findMemberByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " is not found"));
        User user = new User(member.getUsername(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getAuthority()));
        return user;
    }
}
