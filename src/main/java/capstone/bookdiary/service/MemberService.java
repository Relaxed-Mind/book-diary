package capstone.bookdiary.service;

import capstone.bookdiary.domain.dto.LoginDto;
import capstone.bookdiary.exception.exceptions.UserNotFoundException;
import capstone.bookdiary.security.CustomUserDetailService;
import capstone.bookdiary.security.JwtTokenProvider;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final CustomUserDetailService customUserDetailService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    public Map<String, Object> login(LoginDto loginDto) {
        Map<String, Object> token = new HashMap<>();
        UserDetails userDetails = customUserDetailService.loadUserByUsername(loginDto.getEmail());

        if(passwordEncoder.matches(loginDto.getPassword(), userDetails.getPassword())){
            token.put("token", jwtTokenProvider.createToken(userDetails.getUsername(), Collections.singletonList("ROLE_USER")));
            return token;
        }else{
            throw new UserNotFoundException();
        }
    }
}
