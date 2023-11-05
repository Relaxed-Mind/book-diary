package capstone.bookdiary.service;

import capstone.bookdiary.domain.dto.ScrapDto;
import capstone.bookdiary.domain.entity.BookDiary;
import capstone.bookdiary.domain.entity.Scrap;
import capstone.bookdiary.repository.BookDiaryRepository;
import capstone.bookdiary.repository.ScrapRepository;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScrapService {
    private final BookDiaryRepository bookDiaryRepository;
    private final ScrapRepository scrapRepository;
    public Map<String, Object> addScrap(ScrapDto scrapDto) {
        BookDiary bookDiary = bookDiaryRepository.findById(scrapDto.getBookDiaryId()).get();
        Scrap scrap = new Scrap(bookDiary, scrapDto.getContent(), scrapDto.getMemo());
        Scrap savedScrap = scrapRepository.save(scrap);

        Map<String, Object> scrapId = new HashMap<>();
        scrapId.put("scrapId", savedScrap.getScrapId());
        return scrapId;
    }
}
