package capstone.bookdiary.feign;

import capstone.bookdiary.domain.dto.FeignQuestionDto;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "secondQuestion", url = "https://v8ks4hotj8.execute-api.ap-northeast-2.amazonaws.com/TexttoText2/TextLambdaFunction2")
public interface SecondQuestionClient {
    @PostMapping
    FeignQuestionDto getSecondQuestion(Map<String, Object> json);
}
