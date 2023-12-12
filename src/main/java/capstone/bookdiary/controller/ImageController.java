package capstone.bookdiary.controller;

import capstone.bookdiary.domain.dto.ImageRequestDto;
import capstone.bookdiary.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "이미지 컨트롤러", description = "이미지 생성 관련 API")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/image")
    @Operation(summary = "스크랩 이미지 변환", description = "해당하는 스크랩들을 이미지로 변환시킵니다.")
    private ResponseEntity<Map<String, Object>> generateImage(@Valid @RequestBody ImageRequestDto imageRequestDto){
        return ResponseEntity
                .ok()
                .body(imageService.generateImage(imageRequestDto));
    }
    @DeleteMapping("/image/{imageId}")
    @Operation(summary = "이미지 삭제", description = "해당 스크랩의 이미지를 삭제합니다.")
    private ResponseEntity<Map<String, Object>> deleteImage(@PathVariable Long imageId){
        return ResponseEntity
                .ok()
                .body(imageService.deleteImage(imageId));
    };
}
