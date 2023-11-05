package capstone.bookdiary.repository;

import capstone.bookdiary.domain.entity.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapRepository extends JpaRepository<Scrap, Long>, ScrapRepositoryCustom{
}
