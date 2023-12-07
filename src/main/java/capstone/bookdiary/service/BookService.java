package capstone.bookdiary.service;

import capstone.bookdiary.domain.dto.BookDiaryDto;
import capstone.bookdiary.domain.dto.BookDiaryTitleDto;
import capstone.bookdiary.domain.dto.BookDto;
import capstone.bookdiary.domain.dto.DataWithPageDto;
import capstone.bookdiary.domain.dto.PageInfoDto;
import capstone.bookdiary.domain.dto.QuestionDto;
import capstone.bookdiary.domain.dto.ReadingStatusDto;
import capstone.bookdiary.domain.dto.ScoreDto;
import capstone.bookdiary.domain.dto.ScrapImageDto;
import capstone.bookdiary.domain.dto.TakeawayDto;
import capstone.bookdiary.domain.entity.BookDiary;
import capstone.bookdiary.domain.entity.Image;
import capstone.bookdiary.domain.entity.Member;
import capstone.bookdiary.domain.entity.Question;
import capstone.bookdiary.domain.entity.Scrap;
import capstone.bookdiary.exception.exceptions.DataNotFoundException;
import capstone.bookdiary.exception.exceptions.UserNotFoundException;
import capstone.bookdiary.feign.BookClient;
import capstone.bookdiary.feign.param.BookDetailParam;
import capstone.bookdiary.feign.param.BookSearchParam;
import capstone.bookdiary.repository.BookDiaryRepository;
import capstone.bookdiary.repository.ImageRepository;
import capstone.bookdiary.repository.MemberRepository;
import capstone.bookdiary.repository.QuestionRepository;
import capstone.bookdiary.repository.ScrapRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
    private final ScrapRepository scrapRepository;
    private final QuestionRepository questionRepository;
    private final ImageRepository imageRepository;

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
    public Map<String, Object> addScore(Long bookDiaryId, ScoreDto scoreDto){
        BookDiary bookDiary = bookDiaryRepository.findById(bookDiaryId)
                .orElseThrow(DataNotFoundException::new);
        bookDiary.addScore(scoreDto.getScore());

        Map<String, Object> bookDiaryMap = new HashMap<>();
        bookDiaryMap.put("bookDiaryId", bookDiary.getBookDiaryId());
        return bookDiaryMap;
    }

    @Transactional
    public Map<String, Object> addTakeaway(Long bookDiaryId, TakeawayDto takeawayDto) {
        BookDiary bookDiary = bookDiaryRepository.findById(bookDiaryId)
                .orElseThrow(DataNotFoundException::new);
        bookDiary.addTakeaway(takeawayDto.getTakeaway());

        Map<String, Object> bookDiaryMap = new HashMap<>();
        bookDiaryMap.put("bookDiaryId", bookDiary.getBookDiaryId());
        return bookDiaryMap;
    }

    @Transactional
    public Map<String, Object> changeReadingStatus(Long bookDiaryId, ReadingStatusDto readingStatusDto){
        BookDiary bookDiary = bookDiaryRepository.findById(bookDiaryId)
                .orElseThrow(DataNotFoundException::new);
        bookDiary.changeReadingStatus(readingStatusDto.getReadingStatus());

        Map<String, Object> bookDiaryMap = new HashMap<>();
        bookDiaryMap.put("bookDiaryId", bookDiary.getBookDiaryId());
        return bookDiaryMap;
    }

    public Map<String, Object> getMyDiary(Long memberId, Integer pageNo){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(UserNotFoundException::new);
        //페이징
        PageRequest pageRequest = PageRequest.of(pageNo-1, 20, Sort.by("createdTime").descending());
        Page<BookDiary> bookDiaries = bookDiaryRepository.findAllByMember(member, pageRequest);
        List<BookDiary> bookDiaryList = bookDiaries.getContent();

        //bookDiary Data 가공
        List<BookDiaryTitleDto> bookDiaryTitleDtoList = new ArrayList<>();
        for (BookDiary bookDiary : bookDiaryList) {
            bookDiaryTitleDtoList.add(new BookDiaryTitleDto(bookDiary.getBookDiaryId(), bookDiary.getTitle(), bookDiary.getAuthor(),
                    bookDiary.getCoverImageUrl(), bookDiary.getIsbn(), bookDiary.getReadingStatus(),
                    bookDiary.getScore()));
        }

        //page 정보 가공
        PageInfoDto pageInfo = new PageInfoDto(pageNo, 20, bookDiaries.getTotalPages(), bookDiaries.getTotalPages());

        //response 가공
        Map<String, Object> bookDiaryTitleList = new HashMap<>();
        DataWithPageDto dataWithPageDto = new DataWithPageDto<>(bookDiaryTitleDtoList, pageInfo);
        bookDiaryTitleList.put("response", dataWithPageDto);
        return bookDiaryTitleList;
    }

    public Map<String, Object> getSpecificDiary(Long bookDiaryId){
        Map<String, Object> response = new HashMap<>();

        BookDiary bookDiary = bookDiaryRepository.findById(bookDiaryId)
                .orElseThrow(DataNotFoundException::new);

        BookDiaryDto bookDiaryDto = new BookDiaryDto(bookDiary.getTitle(), bookDiary.getAuthor(),
                bookDiary.getCoverImageUrl(), bookDiary.getIsbn(), bookDiary.getReadingStatus(), bookDiary.getScore(),
                bookDiary.getTakeaway());
        List<Scrap> scraps = scrapRepository.findAllByBookDiary(bookDiary, Sort.by(Direction.ASC, "page"));
        List<Question> questions = questionRepository.findAllByBookDiary(bookDiary);
        List<ScrapImageDto> scrapImageDtos = new ArrayList<>();
        List<QuestionDto> questionDtos = new ArrayList<>();

        //스크랩당 이미지 뽑기
        for (Scrap scrap : scraps) {
            Optional<Image> optionalImage = imageRepository.findByScrap(scrap);
            if(optionalImage.isEmpty()){
                scrapImageDtos.add(new ScrapImageDto(scrap.getScrapId(), scrap.getContent(), scrap.getMemo(), scrap.getPage(), ""));
            }else{
                scrapImageDtos.add(new ScrapImageDto(scrap.getScrapId(), scrap.getContent(), scrap.getMemo(), scrap.getPage(), optionalImage.get().getImageUrl()));
            }
        }

        //질문 뽑기
        for (Question question : questions) {
            questionDtos.add(new QuestionDto(question.getQuestion(), question.getAnswer(), question.getDegree()));
        }

        response.put("bookDiary", bookDiaryDto);
        response.put("scraps", scrapImageDtos);
        response.put("questions", questionDtos);

        return response;
    }

    public Map<String, Object> isAdded(Long memberId, String isbn) {
        Map<String, Object> response = new HashMap<>();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(UserNotFoundException::new);

        Optional<BookDiary> bookDiary = bookDiaryRepository.findByMemberAndIsbn(member, isbn);
        if(bookDiary.isEmpty()){
            response.put("bookDiaryId", -1);
        }else{
            response.put("bookDiaryId", bookDiary.get().getBookDiaryId());
        }

        return response;
    }
}
