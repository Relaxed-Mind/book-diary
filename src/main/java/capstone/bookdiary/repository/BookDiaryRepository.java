package capstone.bookdiary.repository;

import capstone.bookdiary.domain.entity.BookDiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDiaryRepository extends JpaRepository<BookDiary, Long> {
}
