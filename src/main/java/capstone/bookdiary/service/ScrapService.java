package capstone.bookdiary.service;

import capstone.bookdiary.domain.dto.ScrapRequestDto;
import capstone.bookdiary.domain.entity.BookDiary;
import capstone.bookdiary.domain.entity.Image;
import capstone.bookdiary.domain.entity.Scrap;
import capstone.bookdiary.exception.exceptions.DataNotFoundException;
import capstone.bookdiary.repository.BookDiaryRepository;
import capstone.bookdiary.repository.ImageRepository;
import capstone.bookdiary.repository.ScrapRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScrapService {
    private final BookDiaryRepository bookDiaryRepository;
    private final ScrapRepository scrapRepository;
    private final ImageRepository imageRepository;

    public Map<String, Object> addScrap(Long bookDiaryId, ScrapRequestDto scrapRequestDto) {
        BookDiary bookDiary = bookDiaryRepository.findById(bookDiaryId)
                .orElseThrow(DataNotFoundException::new);
        Scrap scrap = new Scrap(bookDiary, scrapRequestDto.getContent(), scrapRequestDto.getMemo(), scrapRequestDto.getPage());
        Scrap savedScrap = scrapRepository.save(scrap);

        Map<String, Object> scrapId = new HashMap<>();
        scrapId.put("scrapId", savedScrap.getScrapId());
        return scrapId;
    }

    @Transactional
    public Map<String, Object> modifyScrap(Long scrapId, ScrapRequestDto scrapRequestDto){
        Scrap scrap = scrapRepository.findById(scrapId)
                .orElseThrow(DataNotFoundException::new);
        scrap.modifyContent(scrapRequestDto.getContent());
        scrap.modifyMemo(scrapRequestDto.getMemo());

        Map<String, Object> response = new HashMap<>();
        response.put("scrapId", scrapId);
        return response;
    }

    @Transactional
    public Map<String, Object> deleteScrap(Long scrapId) {
        Scrap scrap = scrapRepository.findById(scrapId)
                .orElseThrow(DataNotFoundException::new);
        Optional<Image> optionalImage = imageRepository.findByScrap(scrap);
        if(optionalImage.isPresent()){
            imageRepository.delete(optionalImage.get());
        }
        scrapRepository.deleteById(scrapId);

        Map<String, Object> response = new HashMap<>();
        response.put("response", "ok");
        return response;
    }
}
