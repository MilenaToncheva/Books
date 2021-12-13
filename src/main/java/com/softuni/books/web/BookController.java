package com.softuni.books.web;

import com.softuni.books.model.dtos.BookDTO;
import com.softuni.books.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = this.bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<BookDTO>> getAllBooks(@RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
                                                     @RequestParam(name = "pageSize", defaultValue = "3") int pageSize,
                                                     @RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(this.bookService.geAllBooks(pageNo,pageSize,sortBy));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable("id") String id) {
        Optional<BookDTO> book = this.bookService.getBookById(id);
        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(book.get());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable("id") String id) {
        this.bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO,
                                              UriComponentsBuilder uriComponentsBuilder) {
        BookDTO bookDto = this.bookService.createBook(bookDTO);
        String bookId = bookDTO.getId();
        URI location = uriComponentsBuilder.path("/books/{id}").buildAndExpand(bookId).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> editBook(@PathVariable("id") String id,
                                            @RequestBody BookDTO bookDTO) {
        bookDTO.setId(id);
        BookDTO updatedBook = this.bookService.editBookById(bookDTO);
        return updatedBook != null ?
                ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
