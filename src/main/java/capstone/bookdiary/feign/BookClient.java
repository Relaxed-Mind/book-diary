package capstone.bookdiary.feign;

import capstone.bookdiary.feign.param.BookDetailParam;
import capstone.bookdiary.feign.param.BookSearchParam;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "book", url = "http://data4library.kr/api")
public interface BookClient {

    @GetMapping("/srchBooks")
    Map<String, Object> getBookList(
            @SpringQueryMap BookSearchParam bookSearchParam
            );

    @GetMapping("/srchDtlList")
    Map<String, Object> getDetailBook(
            @SpringQueryMap BookDetailParam bookDetailParam
            );
}
