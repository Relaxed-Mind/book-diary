package capstone.bookdiary.controller;

import capstone.bookdiary.domain.dto.LoginDto;
import capstone.bookdiary.domain.dto.SignupDto;
import capstone.bookdiary.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "유저", description = "유저 관련 API")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody SignupDto signupDto){
        return ResponseEntity
                .ok()
                .body(memberService.signup(signupDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<Map<String, Object>> signin(@RequestBody LoginDto loginDto) {
        return ResponseEntity
                .ok()
                .body(memberService.signin(loginDto));
    }
}
