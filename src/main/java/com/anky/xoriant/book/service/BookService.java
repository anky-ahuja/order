package com.anky.xoriant.book.service;

import com.anky.xoriant.book.utils.Constants;
import com.anky.xoriant.book.entity.Book;
import com.anky.xoriant.book.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Optional<Book> getBookById(int id) {
        return bookRepository.findById(id);
    }

    public Book saveNewBook(Book book) {
        if(bookRepository.findById(book.getId()).isPresent()) {
            return null;
        }
        return bookRepository.save(book);
    }

    public ResponseEntity<String> deleteBookById(int id) {
        try {
            bookRepository.deleteById(id);
            return new ResponseEntity<>(Constants.DELETE_SUCCESS, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(Constants.DATA_NOT_FOUND_EX + id, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(Constants.DELETE_ERROR + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
