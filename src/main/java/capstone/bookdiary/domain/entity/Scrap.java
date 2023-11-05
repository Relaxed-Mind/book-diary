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
public class Scrap extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scrapId;

    //TODO: 컬럼 세부 지정

    @ManyToOne(fetch = FetchType.LAZY)
    private BookDiary bookDiary;

    private String content;

    private String memo;

    public Scrap(BookDiary bookDiary, String content, String memo) {
        this.bookDiary = bookDiary;
        this.content = content;
        this.memo = memo;
    }
}
