package capstone.bookdiary.controller;

import capstone.bookdiary.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "책 컨트롤러", description = "책 관련 API")

public class BookController {
    private final BookService bookService;

    @GetMapping("/search/{title}/{pageNo}")
    @Operation(summary = "책 제목 검색", description = "사용자가 책 제목을 넣어 검색합니다.")
    public ResponseEntity<Map<String, Object>> searchBook(@PathVariable String title, @PathVariable Integer pageNo){
        return ResponseEntity
                .ok()
                .body(bookService.searchBook(title, pageNo));
    }

    @GetMapping("/book/{isbn}")
    @Operation(summary = "책 상세 조회", description = "사용자가 특정 책을 누른다.")
    public ResponseEntity<Map<String,Object>> viewBookInfo(@PathVariable String isbn){
        return ResponseEntity
                .ok()
                .body(bookService.viewBookInfo(isbn));
    }
}
