package capstone.bookdiary.domain.dto;

import lombok.Getter;

@Getter
public class QuestionWithIdDto {
    private Long questionId;
    private String question;
    private String answer;
    private Integer degree;

    public QuestionWithIdDto(Long questionId, String question, String answer, Integer degree) {
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
        this.degree = degree;
    }
}
