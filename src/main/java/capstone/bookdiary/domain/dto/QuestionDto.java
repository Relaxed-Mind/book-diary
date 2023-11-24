package capstone.bookdiary.domain.dto;

import lombok.Getter;

@Getter
public class QuestionDto {
    private String question;
    private String answer;
    private Integer degree;

    public QuestionDto(String question, String answer, Integer degree) {
        this.question = question;
        this.answer = answer;
        this.degree = degree;
    }
}
