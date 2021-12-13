package com.softuni.books.service;

import com.softuni.books.model.dtos.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDTO> getAllBooks();

    Optional<BookDTO> getBookById(String id);

    void deleteBookById(String id);
    BookDTO createBook(BookDTO book);

    BookDTO editBookById(BookDTO bookDTO);
}
