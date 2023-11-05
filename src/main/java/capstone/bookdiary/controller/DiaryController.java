package capstone.bookdiary.controller;

import capstone.bookdiary.domain.dto.BookDto;
import capstone.bookdiary.domain.dto.ScrapDto;
import capstone.bookdiary.service.BookService;
import capstone.bookdiary.service.ScrapService;
import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "독후감 컨트롤러", description = "독후감 관련 API")

public class DiaryController {

    private final BookService bookService;
    private final ScrapService scrapService;

    @PostMapping("/diary")
    @Operation(summary = "내 서재 등록", description = "해당 isbn에 해당되는 책을 내 서재에 등록합니다.")
    private ResponseEntity<Map<String,Object>> addBook(@RequestBody BookDto bookDto){
        return ResponseEntity
                .ok()
                .body(bookService.addBook(bookDto));
    }

    @PostMapping("/diary/scrap")
    @Operation(summary = "스크랩 추가", description = "스크랩과 메모를 추가합니다.")
    private ResponseEntity<Map<String, Object>> addScrap(@RequestBody ScrapDto scrapDto){
        return ResponseEntity
                .ok()
                .body(scrapService.addScrap(scrapDto));
    }
}
