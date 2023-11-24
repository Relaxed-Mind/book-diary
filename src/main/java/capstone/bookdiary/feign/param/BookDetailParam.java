package capstone.bookdiary.feign.param;

import lombok.Getter;

@Getter
public class BookDetailParam {
    private String authKey;
    private String isbn13;
    private String format;

    public BookDetailParam(String authKey, String isbn13, String format) {
        this.authKey = authKey;
        this.isbn13 = isbn13;
        this.format = format;
    }
}
