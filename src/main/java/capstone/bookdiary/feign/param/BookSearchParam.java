package capstone.bookdiary.feign.param;

import lombok.Getter;

@Getter
public class BookSearchParam {
    private String authKey;
    private String title;
    private Integer pageNo;
    private String format;

    public BookSearchParam(String authKey, String title, Integer pageNo, String format) {
        this.authKey = authKey;
        this.title = title;
        this.pageNo = pageNo;
        this.format = format;
    }
}
