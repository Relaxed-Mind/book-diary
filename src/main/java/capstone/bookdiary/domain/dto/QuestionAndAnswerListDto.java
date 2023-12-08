package capstone.bookdiary.domain.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class QuestionAndAnswerListDto {
    private List<QuestionAndAnswerDto> questionAndAnswer = new ArrayList<>();
}
