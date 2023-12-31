package capstone.bookdiary.domain.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ImageRequestDto {
    private Long scrapId;
    @Size(max = 256, message = "스크랩 내용은 256자 이내로 입력해주세요.")
    private String content;
    @Size(max = 500, message = "메모는 500자 이내로 입력해주세요.")
    private String memo;
}
