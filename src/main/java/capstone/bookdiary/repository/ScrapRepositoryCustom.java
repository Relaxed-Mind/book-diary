package capstone.bookdiary.repository;

import capstone.bookdiary.domain.entity.Scrap;

public interface ScrapRepositoryCustom {
    Scrap findByBookDiary(Long bookDiaryId);
}
