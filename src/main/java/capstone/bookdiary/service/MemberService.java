package capstone.bookdiary.service;

import capstone.bookdiary.domain.dto.LoginDto;
import capstone.bookdiary.domain.dto.SignupDto;
import capstone.bookdiary.domain.entity.Member;
import capstone.bookdiary.exception.exceptions.EmailDuplicateException;
import capstone.bookdiary.exception.exceptions.UserNotFoundException;
import capstone.bookdiary.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    public Map<String, Object> signin(LoginDto loginDto) {
        Map<String, Object> token = new HashMap<>();
        UserDetails userDetails = customUserDetailService.loadUserByUsername(loginDto.getEmail());

        if(passwordEncoder.matches(loginDto.getPassword(), userDetails.getPassword())){
            token.put("token", jwtTokenProvider.createToken(userDetails.getUsername(), Collections.singletonList("ROLE_USER")));
            return token;
        }else{
            throw new UserNotFoundException();
        }
    }

    public Map<String, Object> signup(SignupDto signupDto) {
        //이메일 중복 검증
        if(memberRepository.existsByEmail(signupDto.getEmail())){
            throw new EmailDuplicateException();
        }
        String encryptedPassword = passwordEncoder.encode(signupDto.getPassword());
        Member savedMember = memberRepository.save(new Member(signupDto.getEmail(), encryptedPassword));

        Map<String, Object> memberId = new HashMap<>();
        memberId.put("memberId", savedMember.getMemberId());
        return memberId;
    }
}
