package capstone.bookdiary.controller;

import capstone.bookdiary.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "질문 컨트롤러", description = "질문 생성 관련 API")

public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/questions/{bookDiaryId}")
    @Operation(summary = "[미완성] 질문 및 답변 가져오기", description = "질문 및 답변을 가져옵니다. [답변이 없으면 \"\" 반환]")
    private ResponseEntity<Map<String, Object>> getQuestionsAndAnswers(@PathVariable Long bookDiaryId){
        return ResponseEntity
                .ok()
                .body(questionService.getQuestionsAndAnswers(bookDiaryId));
    }

    @PostMapping("/questions/first/{bookDiaryId}")
    @Operation(summary = "[미완성] 1차 질문 생성", description = "스크랩을 바탕으로 1차 질문을 생성합니다.")
    private ResponseEntity<Map<String, Object>> generateFirstQuestions(@PathVariable Long bookDiaryId){
        return ResponseEntity
                .ok()
                .body(questionService.generateFirstQuestions(bookDiaryId));
    }

    @PostMapping("/questions/second/{bookDiaryId}")
    @Operation(summary = "[미완성] 2차 질문 생성", description = "스크랩과 1차 답변을 바탕으로 2차 질문을 생성합니다.")
    private ResponseEntity<Map<String, Object>> generateSecondQuestions(@PathVariable Long bookDiaryId){
        return ResponseEntity
                .ok()
                .body(questionService.generateSecondQuestions(bookDiaryId));
    }

}
