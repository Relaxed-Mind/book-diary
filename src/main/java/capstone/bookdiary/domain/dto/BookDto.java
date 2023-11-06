package capstone.bookdiary.domain.dto;

import lombok.Getter;

@Getter
public class BookDto {
    private Long memberId;

    private String title;

    private String author;

    private String coverImageUrl;

    private String isbn;
}
