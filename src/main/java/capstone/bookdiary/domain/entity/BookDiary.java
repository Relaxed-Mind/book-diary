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
public class BookDiary extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookDiaryId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    //TODO: 컬럼 세부 조정
    private String title;

    private String author;

    private String coverImageUrl;

    private String isbn;

    private Character readingStatus;

    private Integer score;

    private String takeaway;

    public BookDiary(Member member, String title, String author, String coverImageUrl, String isbn) {
        this.member = member;
        this.title = title;
        this.author = author;
        this.coverImageUrl = coverImageUrl;
        this.isbn = isbn;
        this.readingStatus = '0';
        this.score = null;
        this.takeaway = "";
    }

    public void addTakeaway(String takeaway){
        this.takeaway = takeaway;
    }
    public void addScore(Integer score){
        this.score = score;
    }
}
