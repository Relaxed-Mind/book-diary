package capstone.bookdiary.controller;

import capstone.bookdiary.service.BookService;
import lombok.RequiredArgsConstructor;
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
    public void searchBook(@PathVariable String title){
        bookService.searchBook(title);
    }
}
