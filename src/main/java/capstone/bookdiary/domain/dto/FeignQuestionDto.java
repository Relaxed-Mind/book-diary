package capstone.bookdiary.domain.dto;

import lombok.Getter;
import org.json.JSONObject;

@Getter
public class FeignQuestionDto {
    private Integer statusCode;
    private JSONObject body;
}
