package capstone.bookdiary.service;

import capstone.bookdiary.domain.dto.FeignQuestionDto;
import capstone.bookdiary.domain.dto.QuestionAndAnswerDto;
import capstone.bookdiary.domain.dto.QuestionAndAnswerListDto;
import capstone.bookdiary.domain.dto.QuestionDto;
import capstone.bookdiary.domain.dto.ScrapResponseDto;
import capstone.bookdiary.domain.entity.BookDiary;
import capstone.bookdiary.domain.entity.Question;
import capstone.bookdiary.exception.exceptions.DataNotFoundException;
import capstone.bookdiary.feign.FirstQuestionClient;
import capstone.bookdiary.repository.BookDiaryRepository;
import capstone.bookdiary.repository.QuestionRepository;
import capstone.bookdiary.repository.ScrapRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final ScrapRepository scrapRepository;
    private final QuestionRepository questionRepository;
    private final BookDiaryRepository bookDiaryRepository;
    private final FirstQuestionClient firstQuestionClient;

    public Map<String, Object> generateFirstQuestions(Long bookDiaryId){
        //키워드 받아오기
//        Map<String, Object> json = keywordClient.getKeyword(new KeywordParam(libraryKey,"json"));
//        System.out.println("json.get(\"response\") = " + json.get("response"));
        BookDiary bookDiary = bookDiaryRepository.findById(bookDiaryId)
                .orElseThrow(DataNotFoundException::new);

        //스크랩 받아오기
        List<ScrapResponseDto> scrapList = scrapRepository.findByBookDiary(bookDiaryId);
        StringBuilder scrapMemo = new StringBuilder();
        for (ScrapResponseDto scrapResponseDto : scrapList) {
            scrapMemo.append("\"").append(scrapResponseDto.getContent()).append("\"").append("[").append(scrapResponseDto.getMemo()).append("] ");
        }

        Map<String, Object> data = new HashMap<>();
        data.put("scrapMemo", scrapMemo.toString());
        //scrapMemo에 담아서 AI로 전송
        FeignQuestionDto firstQuestion = firstQuestionClient.getFirstQuestion(data);
        JSONObject body = firstQuestion.getBody();

        //받아온 결과 파싱해서 저장
        List<Long> questionIds = new ArrayList<>();

        String questionString = (String)body.get("gpt_generated_question");
        String[] questionStrings = questionString.split("\n");
        for (String qs : questionStrings) {
            Question savedQuestion = questionRepository.save(new Question(bookDiary, qs, 1));
            questionIds.add(savedQuestion.getQuestionId());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("questionIds", questionIds);
        return response;
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

    @Transactional
    public Map<String, Object> answerQuestions(QuestionAndAnswerListDto questionAndAnswerListDto) {
        List<Long> ids = new ArrayList<>();

        for (QuestionAndAnswerDto questionAndAnswerDto : questionAndAnswerListDto.getQuestionAndAnswer()) {
            Question question = questionRepository.findById(questionAndAnswerDto.getQuestionId()).
                    orElseThrow(DataNotFoundException::new);

            String answer = questionAndAnswerDto.getAnswer();
            if(answer.equals("")){
                questionRepository.delete(question);
            }else{
                question.answerQuestion(answer);
                ids.add(question.getQuestionId());
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("questionIds", ids);

        return response;
    }
}
