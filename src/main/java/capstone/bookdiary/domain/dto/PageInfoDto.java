package capstone.bookdiary.domain.dto;

import lombok.Getter;

@Getter
public class PageInfoDto {
    private int pageNo;
    private int pageSize;
    private int totalElements;
    private int totalPages;

    public PageInfoDto(int pageNo, int pageSize, int totalElements, int totalPages) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
}
