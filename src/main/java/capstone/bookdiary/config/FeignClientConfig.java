package capstone.bookdiary.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("capstone.bookdiary")
public class FeignClientConfig {
}
