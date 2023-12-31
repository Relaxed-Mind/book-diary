package capstone.bookdiary.repository;

import capstone.bookdiary.domain.entity.BookDiary;
import capstone.bookdiary.domain.entity.Member;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDiaryRepository extends JpaRepository<BookDiary, Long> {
    Page<BookDiary> findAllByMember(Member member, Pageable pageable);

    Optional<BookDiary> findByMemberAndIsbn(Member member, String isbn);
}
