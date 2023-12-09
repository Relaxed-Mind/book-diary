package capstone.bookdiary.feign;

import capstone.bookdiary.domain.dto.FeignQuestionDto;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "firstQuestion", url = "https://6abaok06nf.execute-api.ap-northeast-2.amazonaws.com/TexttoText1/TextLambdaFunction1")
public interface FirstQuestionClient {
    @PostMapping
    FeignQuestionDto getFirstQuestion(@RequestBody Map<String, Object> scrapMemo);
}
