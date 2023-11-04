package capstone.bookdiary.service;

import capstone.util.XmlToJson;
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


    //TODO: 도서 검색 외부 api 요청

    public void searchBook(String title){
        String bookSearchApiUrl = "http://data4library.kr/api/srchBooks?authKey="+libraryKey+"&title="+title;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> exchange = restTemplate.exchange(bookSearchApiUrl, HttpMethod.GET, entity, String.class);
        XmlToJson.xmlToJson(exchange.getBody());

    }
    //TODO: 도서 세부 정보 외부 api 요청
}
