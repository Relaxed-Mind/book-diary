package capstone.bookdiary.service;

import capstone.util.TypeConvert;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookService {
    @Value("${api-key.library}")
    private String libraryKey;
    @Autowired
    private RestTemplate restTemplate;

    public Map<String, Object> searchBook(String title){
        String bookSearchApiUrl = "http://data4library.kr/api/srchBooks?authKey="+libraryKey+"&title="+title;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> exchange = restTemplate.exchange(bookSearchApiUrl, HttpMethod.GET, entity, String.class);

        //도서관 정보나루 API에서 받아온 xml 형식의 data를 json으로 가공
        String jsonString = TypeConvert.xmlToJsonString(exchange.getBody());
        Map<String, Object> json = TypeConvert.JsonStringToJson(jsonString);
        return json;
    }

    public Map<String, Object> viewBookInfo(String isbn){
        String bookSearchApiUrl = "http://data4library.kr/api/srchDtlList?authKey="+libraryKey+"&isbn13="+isbn+"&format=json";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> exchange = restTemplate.exchange(bookSearchApiUrl, HttpMethod.GET, entity, String.class);
        Map<String, Object> json = TypeConvert.JsonStringToJson(exchange.getBody());
        return json;
    }
}
