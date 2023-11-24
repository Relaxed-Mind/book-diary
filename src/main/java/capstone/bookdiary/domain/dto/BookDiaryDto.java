package capstone.bookdiary.domain.dto;

import lombok.Getter;

@Getter
public class BookDiaryDto {
    private String title;

    private String author;

    private String coverImageUrl;

    private String isbn;

    private Character readingStatusYN;

    private Integer score;

    private String takeaway;

    public BookDiaryDto(String title, String author, String coverImageUrl, String isbn, Character readingStatusYN,
                        Integer score, String takeaway) {
        this.title = title;
        this.author = author;
        this.coverImageUrl = coverImageUrl;
        this.isbn = isbn;
        this.readingStatusYN = readingStatusYN;
        this.score = score;
        this.takeaway = takeaway;
    }
}
