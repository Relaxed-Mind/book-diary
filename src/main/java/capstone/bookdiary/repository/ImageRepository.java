package capstone.bookdiary.repository;

import capstone.bookdiary.domain.entity.Image;
import capstone.bookdiary.domain.entity.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByScrap(Scrap scrap);
}
