package capstone.bookdiary.feign;

import capstone.bookdiary.domain.dto.ImageResponseDto;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "imageClient", url = "https://js8sb56r97.execute-api.ap-northeast-2.amazonaws.com/TextToImageTrigger/ImageTriggerFunction")

public interface ImageClient {

    @PostMapping(consumes = "application/json")
    ImageResponseDto getImageUrl(@RequestBody Map<String, String> message);
}
