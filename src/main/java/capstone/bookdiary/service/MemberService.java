package capstone.bookdiary.service;

import capstone.bookdiary.domain.dto.LoginDto;
import capstone.bookdiary.security.CustomUserDetailService;
import capstone.bookdiary.security.JwtTokenProvider;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final CustomUserDetailService customUserDetailService;
    private final JwtTokenProvider jwtTokenProvider;
    public String login(LoginDto loginDto) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(loginDto.getEmail());
        if(loginDto.getPassword().equals(userDetails.getPassword())){
            return jwtTokenProvider.createToken(userDetails.getUsername(), Collections.singletonList("ROLE_USER"));
        }else{
            return "invalid password";
        }

    }
}
