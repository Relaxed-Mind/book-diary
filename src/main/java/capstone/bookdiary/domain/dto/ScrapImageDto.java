package capstone.bookdiary.domain.dto;

import lombok.Getter;

@Getter
public class ScrapImageDto {
    private String content;
    private String memo;
    private String imageUrl;

    public ScrapImageDto(String content, String memo, String imageUrl) {
        this.content = content;
        this.memo = memo;
        this.imageUrl = imageUrl;
    }
}
