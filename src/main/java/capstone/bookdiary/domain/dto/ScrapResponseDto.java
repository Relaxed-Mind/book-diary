package capstone.bookdiary.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class ScrapResponseDto {
    private String content;
    private String memo;

    public ScrapResponseDto(){
    }

    @QueryProjection
    public ScrapResponseDto(String content, String memo){
        this.content = content;
        this.memo = memo;
    }
}
