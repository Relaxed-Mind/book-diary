package capstone.bookdiary.controller;

import capstone.bookdiary.service.BookService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class BookController {
    private final BookService bookService;


    @GetMapping("/search/{title}")
    public ResponseEntity<Map<String, Object>> searchBook(@PathVariable String title){
        return ResponseEntity
                .ok()
                .body(bookService.searchBook(title));
    }
}
