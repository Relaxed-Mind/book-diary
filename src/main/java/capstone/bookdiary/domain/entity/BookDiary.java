package capstone.bookdiary.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class BookDiary extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookDiaryId;

    //TODO: 컬럼 세부 조정
    private String title;

    private String author;

    private String coverImageUrl;

    private String isbn;

    private Character readingStatus;

    private Integer score;

    public BookDiary(String title, String author, String coverImageUrl, String isbn) {
        this.title = title;
        this.author = author;
        this.coverImageUrl = coverImageUrl;
        this.isbn = isbn;
        this.readingStatus = '0';
        this.score = null;
    }
}
