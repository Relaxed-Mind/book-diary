package capstone.bookdiary.domain.dto;

import lombok.Getter;

@Getter
public class ScrapRequestDto {
    private Long bookDiaryId;
    private String content;
    private String memo;
}
