package capstone.bookdiary.service;

import capstone.bookdiary.domain.dto.BookDiaryTitleDto;
import capstone.bookdiary.domain.dto.BookDto;
import capstone.bookdiary.domain.dto.ScoreDto;
import capstone.bookdiary.domain.dto.TakeawayDto;
import capstone.bookdiary.domain.entity.BookDiary;
import capstone.bookdiary.domain.entity.Member;
import capstone.bookdiary.exception.exceptions.DataNotFoundException;
import capstone.bookdiary.exception.exceptions.UserNotFoundException;
import capstone.bookdiary.repository.BookDiaryRepository;
import capstone.bookdiary.repository.MemberRepository;
import capstone.bookdiary.util.TypeConvert;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class BookService {
    @Value("${api-key.library}")
    private String libraryKey;
    @Autowired
    private RestTemplate restTemplate;
    private final BookDiaryRepository bookDiaryRepository;
    private final MemberRepository memberRepository;

    public Map<String, Object> searchBook(String title, Integer pageNo){
        String bookSearchApiUrl = "http://data4library.kr/api/srchBooks?authKey="+libraryKey+"&title=\"" +title+ "\"&pageNo="+pageNo +"&format=json";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> exchange = restTemplate.exchange(bookSearchApiUrl, HttpMethod.GET, entity, String.class);

//        String jsonString = TypeConvert.xmlToJsonString(exchange.getBody());
        Map<String, Object> json = TypeConvert.JsonStringToJson(exchange.getBody());
//        Map<String, Object> json = TypeConvert.JsonStringToJson(jsonString);
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

    public Map<String, Object> addBook(BookDto bookDto) {
        Member member = memberRepository.findById(bookDto.getMemberId())
                .orElseThrow(UserNotFoundException::new);
        BookDiary savedBookDiary = bookDiaryRepository.save(
                new BookDiary(member, bookDto.getTitle(), bookDto.getAuthor(), bookDto.getCoverImageUrl(), bookDto.getIsbn()));

        Map<String, Object> bookDiaryId = new HashMap<>();
        bookDiaryId.put("bookDiaryId", savedBookDiary.getBookDiaryId());
        return bookDiaryId;
    }

    @Transactional
    public Map<String, Object> addScore(ScoreDto scoreDto){
        BookDiary bookDiary = bookDiaryRepository.findById(scoreDto.getBookDiaryId())
                .orElseThrow(DataNotFoundException::new);
        bookDiary.addScore(scoreDto.getScore());

        Map<String, Object> bookDiaryId = new HashMap<>();
        bookDiaryId.put("bookDiaryId", bookDiary.getBookDiaryId());
        return bookDiaryId;
    }

    @Transactional
    public Map<String, Object> addTakeaway(TakeawayDto takeawayDto) {
        BookDiary bookDiary = bookDiaryRepository.findById(takeawayDto.getBookDiaryId())
                .orElseThrow(DataNotFoundException::new);
        bookDiary.addTakeaway(takeawayDto.getTakeaway());

        Map<String, Object> bookDiaryId = new HashMap<>();
        bookDiaryId.put("bookDiaryId", bookDiary.getBookDiaryId());
        return bookDiaryId;
    }

    public Map<String, Object> getMyDiary(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(UserNotFoundException::new);

        List<BookDiary> bookDiaryList = bookDiaryRepository.findAllByMember(member);
        List<BookDiaryTitleDto> bookDiaryTitleDtoList = new ArrayList<>();
        for (BookDiary bookDiary : bookDiaryList) {
            bookDiaryTitleDtoList.add(new BookDiaryTitleDto(bookDiary.getTitle(), bookDiary.getAuthor(),
                    bookDiary.getCoverImageUrl(), bookDiary.getIsbn(), bookDiary.getReadingStatusYN(),
                    bookDiary.getScore()));
        }
        Map<String, Object> bookDiaryTitleList = new HashMap<>();
        bookDiaryTitleList.put("response", bookDiaryTitleDtoList);
        return bookDiaryTitleList;
    }
}
