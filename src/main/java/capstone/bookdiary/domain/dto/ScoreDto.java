package capstone.bookdiary.domain.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class ScoreDto {
    @Range(min = 0, max = 10)
    private Integer score;
}
