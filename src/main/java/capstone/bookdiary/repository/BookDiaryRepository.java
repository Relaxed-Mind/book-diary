package capstone.bookdiary.repository;

import capstone.bookdiary.domain.entity.BookDiary;
import capstone.bookdiary.domain.entity.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDiaryRepository extends JpaRepository<BookDiary, Long> {
    List<BookDiary> findAllByMember(Member member);
}
