package capstone.bookdiary.security;

import capstone.bookdiary.domain.entity.Member;
import capstone.bookdiary.repository.MemberRepository;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * 사용자의 이메일을 기반으로 User 객체 생성 후 UserDetails 객체 반환하는 메서드
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmail(email)
                .map(this::createCustomUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(email + " -> 데이터베이스에 존재하지 않습니다."));

    }

    private CustomUserDetails createCustomUserDetails(Member member){
        return new CustomUserDetails(member);
    }

    /**
     * User 객체 생성하는 메서드
     */
    private User createUser(Member member) {
        List<GrantedAuthority> grantedAuthorities = Collections.singletonList(
                new SimpleGrantedAuthority(member.getRole()));

        return new User(member.getEmail(),
                member.getPassword(),
                grantedAuthorities);
    }
}
