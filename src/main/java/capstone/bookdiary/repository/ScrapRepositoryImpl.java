package capstone.bookdiary.repository;

import capstone.bookdiary.domain.entity.Scrap;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ScrapRepositoryImpl implements ScrapRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public Scrap findByBookDiary(Long bookDiaryId) {
        return null;
    }
}
