package capstone.bookdiary.controller;

import capstone.bookdiary.domain.dto.LoginDto;
import capstone.bookdiary.domain.dto.SignupDto;
import capstone.bookdiary.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(name = "유저 컨트롤러", description = "유저 관련 API")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "사용자가 회원가입을 합니다.")
    public ResponseEntity<Map<String, Object>> signup(@Valid @RequestBody SignupDto signupDto){
        return ResponseEntity
                .ok()
                .body(memberService.signup(signupDto));
    }

    @PostMapping("/signin")
    @Operation(summary = "로그인", description = "사용자가 이메일과 패스워드를 넣어 로그인을 시도합니다.")
    public ResponseEntity<Map<String, Object>> signin(@RequestBody LoginDto loginDto) {
        return ResponseEntity
                .ok()
                .body(memberService.signin(loginDto));
    }
}
