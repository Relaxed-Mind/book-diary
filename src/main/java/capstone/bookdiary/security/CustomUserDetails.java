package capstone.bookdiary.security;

import capstone.bookdiary.domain.entity.Member;
import java.util.Collection;
import java.util.Collections;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
    private Collection<? extends GrantedAuthority> authorities;

    //추가 설정
    private Long memberId;

    public CustomUserDetails(Member member){
        this.username = member.getEmail();
        this.password = member.getPassword();
        this.memberId = member.getMemberId();
        this.authorities = Collections.singletonList(
                new SimpleGrantedAuthority(member.getRole()));
    }
}
