package com.anky.xoriant.book.repository;

import com.anky.xoriant.book.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
}
