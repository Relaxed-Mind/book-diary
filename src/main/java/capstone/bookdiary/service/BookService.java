package capstone.bookdiary.service;

import capstone.bookdiary.domain.dto.BookDiaryTitleDto;
import capstone.bookdiary.domain.dto.BookDto;
import capstone.bookdiary.domain.dto.ScoreDto;
import capstone.bookdiary.domain.dto.TakeawayDto;
import capstone.bookdiary.domain.entity.BookDiary;
import capstone.bookdiary.domain.entity.Member;
import capstone.bookdiary.exception.exceptions.DataNotFoundException;
import capstone.bookdiary.exception.exceptions.UserNotFoundException;
import capstone.bookdiary.feign.BookClient;
import capstone.bookdiary.feign.param.BookDetailParam;
import capstone.bookdiary.feign.param.BookSearchParam;
import capstone.bookdiary.repository.BookDiaryRepository;
import capstone.bookdiary.repository.MemberRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {
    @Value("${api-key.library}")
    private String libraryKey;
    private final BookClient bookClient;
    private final MemberRepository memberRepository;
    private final BookDiaryRepository bookDiaryRepository;

    public Map<String, Object> searchBook(String title, Integer pageNo){
        return bookClient.getBookList(new BookSearchParam(libraryKey, title, pageNo, "json"));
    }

    public Map<String, Object> viewBookInfo(String isbn){
        return bookClient.getDetailBook(new BookDetailParam(libraryKey, isbn, "json"));

//        String bookSearchApiUrl = "http://data4library.kr/api/srchDtlList?authKey="+libraryKey+"&isbn13="+isbn+"&format=json";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<String> exchange = restTemplate.exchange(bookSearchApiUrl, HttpMethod.GET, entity, String.class);
//        Map<String, Object> json = TypeConvert.JsonStringToJson(exchange.getBody());
//        return json;
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
