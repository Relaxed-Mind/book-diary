package capstone.bookdiary.domain.dto;

import lombok.Getter;

@Getter
public class DataWithPageDto<T> {
    private T data;
    private PageInfoDto pageInfo;

    public DataWithPageDto(T data, PageInfoDto pageInfo) {
        this.data = data;
        this.pageInfo = pageInfo;
    }
}
