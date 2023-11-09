package capstone.bookdiary.repository;

import static capstone.bookdiary.domain.entity.QBookDiary.*;
import static capstone.bookdiary.domain.entity.QScrap.*;

import capstone.bookdiary.domain.dto.QScrapResponseDto;
import capstone.bookdiary.domain.dto.ScrapResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ScrapRepositoryImpl implements ScrapRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ScrapResponseDto> findByBookDiary(Long bookDiaryId) {
        return queryFactory
                .select(new QScrapResponseDto(scrap.content, scrap.memo))
                .from(scrap)
                .leftJoin(bookDiary, scrap.bookDiary)
                .where(bookDiary.bookDiaryId.eq(bookDiaryId))
                .fetch();
    }
}
