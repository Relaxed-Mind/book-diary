package capstone.bookdiary.domain.dto;

import lombok.Getter;

@Getter
public class BookDiaryDto {
    private String title;

    private String author;

    private String coverImageUrl;

    private String isbn;

    private Character readingStatus;

    private Integer score;

    private String takeaway;

    public BookDiaryDto(String title, String author, String coverImageUrl, String isbn, Character readingStatus,
                        Integer score, String takeaway) {
        this.title = title;
        this.author = author;
        this.coverImageUrl = coverImageUrl;
        this.isbn = isbn;
        this.readingStatus = readingStatus;
        this.score = score;
        this.takeaway = takeaway;
    }
}
