package com.softuni.books.service.impl;

import com.softuni.books.model.dtos.AuthorDTO;
import com.softuni.books.model.dtos.BookDTO;
import com.softuni.books.model.entites.AuthorEntity;
import com.softuni.books.model.entites.BookEntity;
import com.softuni.books.repository.AuthorRepository;
import com.softuni.books.repository.BookRepository;
import com.softuni.books.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return this.bookRepository
                .findAll()
                .stream()
                .map(this::asBook)
                .collect(Collectors.toList());

    }

    @Override
    public Optional<BookDTO> getBookById(String id) {
        return this.bookRepository.findById(id).map(this::asBook);

    }

    @Override
    public void deleteBookById(String id) {
        this.bookRepository.deleteById(id);

    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        AuthorEntity author = this.authorRepository.findByName(bookDTO.getAuthor().getName())
                .orElseGet(() -> {
                    AuthorEntity authorEntity = new AuthorEntity();
                    authorEntity.setName(bookDTO.getAuthor().getName());
                    return authorEntity;
                });
        this.authorRepository.save(author);
        BookEntity book = new BookEntity();
        book.setAuthor(author);
        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
       return this.modelMapper.map(this.bookRepository.save(book),BookDTO.class);

    }

    @Override
    public BookDTO editBookById(BookDTO bookDTO) {
       BookEntity bookEntity= this.bookRepository.findById(bookDTO.getId()).orElse(null);
       if(bookEntity==null){
           return null;
       }
       AuthorEntity authorEntity=this.authorRepository.findByName(bookDTO.getAuthor().getName())
               .orElseGet(()->{
                   AuthorEntity author=new AuthorEntity();
                   author.setName(bookDTO.getAuthor().getName());
                   return this.authorRepository.save(author);
               });
       bookEntity.setAuthor(authorEntity);
       bookEntity.setTitle(bookDTO.getTitle());
       bookEntity.setIsbn(bookDTO.getIsbn());
        return this.modelMapper.map(this.bookRepository.save(bookEntity),BookDTO.class);
    }


    private BookDTO asBook(BookEntity book) {
        BookDTO bookDTO = this.modelMapper
                .map(book, BookDTO.class);
        AuthorDTO authorDTO = this.modelMapper.map(book.getAuthor(), AuthorDTO.class);
        bookDTO.setAuthor(authorDTO);

        return bookDTO;

    }
}