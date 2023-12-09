package capstone.bookdiary.controller;

import capstone.bookdiary.domain.dto.BookDto;
import capstone.bookdiary.domain.dto.ReadingStatusDto;
import capstone.bookdiary.domain.dto.ScoreDto;
import capstone.bookdiary.domain.dto.ScrapRequestDto;
import capstone.bookdiary.domain.dto.TakeawayDto;
import capstone.bookdiary.service.BookService;
import capstone.bookdiary.service.ScrapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "독후감 컨트롤러", description = "독후감 관련 API")

public class DiaryController {

    private final BookService bookService;
    private final ScrapService scrapService;

    @GetMapping("/diary/{memberId}")
    @Operation(summary = "내가 등록한 다이어리(책)들 조회", description = "내가 등록한 다이어리(책)을 간단히 조회합니다.")
    private ResponseEntity<Map<String, Object>> getMyDiary(@PathVariable Long memberId, @RequestParam Integer pageNo){
        return ResponseEntity
                .ok()
                .body(bookService.getMyDiary(memberId, pageNo));
    }

    @GetMapping("/diary/{memberId}/{isbn}")
    @Operation(summary = "내가 등록한 책인지 확인", description = "이미 등록 돼있으면 bookDiaryId를 return. 등록 안 돼있으면 -1 리턴")
    private ResponseEntity<Map<String, Object>> isAdded(@PathVariable Long memberId, @PathVariable String isbn){
        return ResponseEntity
                .ok()
                .body(bookService.isAdded(memberId, isbn));
    }

    @PostMapping("/diary")
    @Operation(summary = "내 서재 등록", description = "해당 isbn에 해당되는 책을 내 서재에 등록합니다.")
    private ResponseEntity<Map<String,Object>> addBook(@RequestBody BookDto bookDto){
        return ResponseEntity
                .ok()
                .body(bookService.addBook(bookDto));
    }

    @PostMapping("/diary/{bookDiaryId}/scrap")
    @Operation(summary = "스크랩 추가", description = "스크랩과 메모를 추가합니다. (이때 페이지 안 넣으면 null 들어감)")
    private ResponseEntity<Map<String, Object>> addScrap(@PathVariable Long bookDiaryId, @Valid @RequestBody ScrapRequestDto scrapRequestDto){
        return ResponseEntity
                .ok()
                .body(scrapService.addScrap(bookDiaryId, scrapRequestDto));
    }

    @PutMapping("/diary/{scrapId}")
    @Operation(summary = "스크랩 수정", description = "스크랩과 메모를 수정합니다.")
    private ResponseEntity<Map<String, Object>> modifyScrap(@PathVariable Long scrapId, @RequestBody ScrapRequestDto scrapRequestDto){
        return ResponseEntity
                .ok()
                .body(scrapService.modifyScrap(scrapId, scrapRequestDto));
    }

    @DeleteMapping("/diary/{scrapId}")
    @Operation(summary = "스크랩 삭제", description = "스크랩을 삭제합니다.")
    private ResponseEntity<Map<String, Object>> deleteScrap(@PathVariable Long scrapId){
        return ResponseEntity
                .ok()
                .body(scrapService.deleteScrap(scrapId));
    }

    @PatchMapping("/diary/{bookDiaryId}/rate")
    @Operation(summary = "평점 매기기", description = "책을 다 읽고 평점을 매깁니다.")
    private ResponseEntity<Map<String, Object>> addScore(@PathVariable Long bookDiaryId, @Valid @RequestBody ScoreDto scoreDto){
        return ResponseEntity
                .ok()
                .body(bookService.addScore(bookDiaryId, scoreDto));
    }

    @PatchMapping("/diary/{bookDiaryId}/report")
    @Operation(summary = "감상문 작성", description = "책을 다 읽고 감상문을 작성합니다.")
    private ResponseEntity<Map<String, Object>> addTakeaway(@PathVariable Long bookDiaryId, @RequestBody TakeawayDto takeawayDto){
        return ResponseEntity
                .ok()
                .body(bookService.addTakeaway(bookDiaryId, takeawayDto));
    }

    @PatchMapping("/diary/{bookDiaryId}/reading-status")
    @Operation(summary = "책 상태 변경", description = "0: 담아놓은 상태, 1: 읽는 중, 2: 다 읽음")
    private ResponseEntity<Map<String, Object>> changeReadingStatus(@PathVariable Long bookDiaryId, @RequestBody
                                                                    ReadingStatusDto readingStatusDto){
        return ResponseEntity
                .ok()
                .body(bookService.changeReadingStatus(bookDiaryId, readingStatusDto));
    }


    @GetMapping("/diary/specific/{bookDiaryId}")
    @Operation(summary = "독후감 상세보기", description = "특정 독후감을 상세보기 합니다. (스크랩은 생성시간 기준 null 우선 오름차순)")
    private ResponseEntity<Map<String, Object>> getSpecificDiary(@PathVariable Long bookDiaryId){
        return ResponseEntity
                .ok()
                .body(bookService.getSpecificDiary(bookDiaryId));
    }

}
