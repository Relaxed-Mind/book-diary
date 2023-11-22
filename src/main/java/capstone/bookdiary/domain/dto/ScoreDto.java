package capstone.bookdiary.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class ScoreDto {
    private Long bookDiaryId;
    @Range(min = 0, max = 10)
    private Integer score;
}
