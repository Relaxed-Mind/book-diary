package capstone.bookdiary.repository;

import capstone.bookdiary.domain.entity.BookDiary;
import capstone.bookdiary.domain.entity.Scrap;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapRepository extends JpaRepository<Scrap, Long>, ScrapRepositoryCustom{
    List<Scrap> findAllByBookDiary(BookDiary bookDiary);
}
