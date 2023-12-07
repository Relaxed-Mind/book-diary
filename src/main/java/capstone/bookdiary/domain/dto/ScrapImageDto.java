package capstone.bookdiary.domain.dto;

import lombok.Getter;

@Getter
public class ScrapImageDto {
    private Long scrapId;
    private String content;
    private String memo;
    private Integer page;
    private String imageUrl;

    public ScrapImageDto(Long scrapId, String content, String memo, Integer page, String imageUrl) {
        this.scrapId = scrapId;
        this.content = content;
        this.memo = memo;
        this.page = page;
        this.imageUrl = imageUrl;
    }
}
