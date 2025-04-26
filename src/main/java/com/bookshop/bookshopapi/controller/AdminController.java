package com.bookshop.bookshopapi.controller;

import com.bookshop.bookshopapi.DTO.BookRequest;
import com.bookshop.bookshopapi.entity.Book;
import com.bookshop.bookshopapi.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final BookRepository bookRepository;

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody BookRequest book) {
        Book savedBook = new Book();
        savedBook.setIsbn(book.getIsbn());
        savedBook.setTitle(book.getTitle());
        savedBook.setAuthor(book.getAuthor());
        savedBook.setCategory(book.getCategory());
        savedBook.setQuantity(book.getQuantity());
        savedBook.setDescription(book.getDescription());
        savedBook.setImage(book.getImage());
        savedBook.setPublisher(book.getPublisher());
        savedBook.setPrice(book.getPrice());
        bookRepository.save(savedBook);
        System.out.println("Saved book ID: " + savedBook.getId());
        if (savedBook.getId() != null)
            return ResponseEntity.status(HttpStatus.CREATED).build();
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("update/{isbn}")
    public ResponseEntity<Book> updateBook(@PathVariable Long isbn, @RequestBody BookRequest updatedBook) {
        return bookRepository.findByIsbn(isbn)
                .map(book -> {
                    book.setIsbn(updatedBook.getIsbn());
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                    book.setCategory(updatedBook.getCategory());
                    book.setQuantity(updatedBook.getQuantity());
                    book.setDescription(updatedBook.getDescription());
                    book.setImage(updatedBook.getImage());
                    book.setPublisher(updatedBook.getPublisher());
                    Book updated = bookRepository.save(book);
                    return ResponseEntity.status(HttpStatus.OK).body(updated);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Transactional
    @DeleteMapping("delete/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long isbn) {
        if (bookRepository.existsByIsbn(isbn)) {
            bookRepository.deleteByIsbn(isbn);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}