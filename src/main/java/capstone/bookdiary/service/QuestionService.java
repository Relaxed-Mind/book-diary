package capstone.bookdiary.service;

import capstone.bookdiary.domain.dto.QuestionDto;
import capstone.bookdiary.domain.dto.ScrapResponseDto;
import capstone.bookdiary.domain.entity.BookDiary;
import capstone.bookdiary.domain.entity.Question;
import capstone.bookdiary.exception.exceptions.DataNotFoundException;
import capstone.bookdiary.repository.BookDiaryRepository;
import capstone.bookdiary.repository.QuestionRepository;
import capstone.bookdiary.repository.ScrapRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final ScrapRepository scrapRepository;
    private final QuestionRepository questionRepository;
    private final BookDiaryRepository bookDiaryRepository;

    public Map<String, Object> generateFirstQuestions(Long bookDiaryId){
        //키워드 받아오기
//        Map<String, Object> json = keywordClient.getKeyword(new KeywordParam(libraryKey,"json"));
//        System.out.println("json.get(\"response\") = " + json.get("response"));

        //스크랩 받아오기
        List<ScrapResponseDto> scrapList = scrapRepository.findByBookDiary(bookDiaryId);
        StringBuilder scrapMemo = new StringBuilder();
        for (ScrapResponseDto scrapResponseDto : scrapList) {
            scrapMemo.append("\"").append(scrapResponseDto.getContent()).append("\"").append("[").append(scrapResponseDto.getMemo()).append("] ");
        }
        System.out.println(scrapMemo);

        //TODO: AI로 전송
        Map<String, Object> data = new HashMap<>();
        data.put("scrapMemo", scrapMemo);

        //TODO: 응답 받아서 질문들 리턴
        return data;
    }

    public Map<String, Object> generateSecondQuestions(Long bookDiaryId) {
        //스크랩 받아오기
        List<ScrapResponseDto> scrapList = scrapRepository.findByBookDiary(bookDiaryId);
        StringBuilder scrapMemo = new StringBuilder();
        for (ScrapResponseDto scrapResponseDto : scrapList) {
            scrapMemo.append("\"").append(scrapResponseDto.getContent()).append("\"").append("[").append(scrapResponseDto.getMemo()).append("] ");
        }

        //1차 질문 받아오기
        BookDiary bookDiary = bookDiaryRepository.findById(bookDiaryId)
                .orElseThrow(DataNotFoundException::new);
        List<Question> questions = questionRepository.findAllByBookDiary(bookDiary);
        StringBuilder question = new StringBuilder();
        StringBuilder answer = new StringBuilder();
        for(int i=0; i<questions.size(); i++){
            Question questionAnswer = questions.get(i);
            question.append(i+1).append(".").append(questionAnswer.getQuestion()).append("\n");
            answer.append("답변").append(i + 1).append(": ").append(questionAnswer.getAnswer()).append("\n");
        }
        System.out.println("question = " + question.toString());
        System.out.println("answer = " + answer.toString());

        Map<String, Object> request = new HashMap<>();
        request.put("scrapMemo", scrapMemo.toString());
        request.put("question", question.toString());
        request.put("answer", answer.toString());

        //2차 질문 return
        return request;
    }

    public Map<String, Object> getQuestionsAndAnswers(Long bookDiaryId) {
        BookDiary bookDiary = bookDiaryRepository.findById(bookDiaryId)
                .orElseThrow(DataNotFoundException::new);
        List<Question> questions = questionRepository.findAllByBookDiary(bookDiary);

        List<QuestionDto> questionDtos = new ArrayList<>();
        for (Question question : questions) {
            questionDtos.add(new QuestionDto(question.getQuestion(), question.getAnswer(), question.getDegree()));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("questionAnswer", questionDtos);

        return response;
    }
}
