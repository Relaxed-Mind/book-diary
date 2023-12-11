package capstone.bookdiary.domain.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScrapRequestDto {
    @Size(max = 256, message = "스크랩 내용은 256자 이내로 입력해주세요.")
    private String content;
    @Size(max = 500, message = "메모는 500자 이내로 입력해주세요.")
    private String memo;
    @Positive(message = "페이지 값은 0보다 커야합니다")
    private Integer page;
}
