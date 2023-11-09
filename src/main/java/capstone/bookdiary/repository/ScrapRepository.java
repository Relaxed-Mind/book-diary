package capstone.bookdiary.repository;

import capstone.bookdiary.domain.dto.ScrapResponseDto;
import capstone.bookdiary.domain.entity.BookDiary;
import capstone.bookdiary.domain.entity.Scrap;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapRepository extends JpaRepository<Scrap, Long>, ScrapRepositoryCustom{
    List<ScrapResponseDto> findAllByBookDiary(BookDiary bookDiary);
}
