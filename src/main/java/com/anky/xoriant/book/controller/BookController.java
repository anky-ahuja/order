package com.anky.xoriant.book.controller;

import com.anky.xoriant.book.utils.Constants;
import com.anky.xoriant.book.entity.Book;
import com.anky.xoriant.book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/server")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(
            path = "/getBookById",
            produces = "application/json"
    )
    public ResponseEntity getBookById(@RequestParam int bookId) {
        Optional<Book> optionalBook = bookService.getBookById(bookId);
        return optionalBook.map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(
            path = "/saveNewBook",
            produces = "application/json"
    )
    public ResponseEntity<String> saveNewBook(@RequestBody Book book) {
        Book returnBook = bookService.saveNewBook(book);
        if(returnBook == null) {
            return new ResponseEntity<>(Constants.SAVE_ERROR, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(Constants.SAVE_SUCCESS + returnBook.getId(), HttpStatus.OK);
    }

    @DeleteMapping(
            path = "/deleteBookById",
            produces = "application/json"
    )
    public ResponseEntity<String> deleteBookById(@RequestParam int bookId) {
        return bookService.deleteBookById(bookId);
    }
}
