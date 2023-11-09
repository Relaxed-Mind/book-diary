package capstone.bookdiary.repository;

import capstone.bookdiary.domain.dto.ScrapResponseDto;
import java.util.List;

public interface ScrapRepositoryCustom {
    List<ScrapResponseDto> findByBookDiary(Long bookDiaryId);
}
