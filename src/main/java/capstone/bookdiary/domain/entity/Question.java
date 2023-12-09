package capstone.bookdiary.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Question extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    private BookDiary bookDiary;

    //TODO: 컬럼 세부 지정
    private String question;

    private String answer;

    private Integer degree;

    public Question(BookDiary bookDiary, String question, Integer degree) {
        this.bookDiary = bookDiary;
        this.question = question;
        this.degree = degree;
        this.answer = "";
    }

    public void answerQuestion(String answer){
        this.answer = answer;
    }
}
