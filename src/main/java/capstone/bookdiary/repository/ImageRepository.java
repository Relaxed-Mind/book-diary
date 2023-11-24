package capstone.bookdiary.repository;

import capstone.bookdiary.domain.entity.Image;
import capstone.bookdiary.domain.entity.Scrap;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByScrap(Scrap scrap);
}
