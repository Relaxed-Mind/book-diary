package capstone.bookdiary.domain.dto;

import lombok.Getter;

@Getter
public class BookDiaryTitleDto {
    private String title;

    private String author;

    private String coverImageUrl;

    private String isbn;

    private Character readingStatusYN;

    private Integer score;

    public BookDiaryTitleDto(String title, String author, String coverImageUrl, String isbn,
                             Character readingStatusYN, Integer score) {
        this.title = title;
        this.author = author;
        this.coverImageUrl = coverImageUrl;
        this.isbn = isbn;
        this.readingStatusYN = readingStatusYN;
        this.score = score;
    }
}