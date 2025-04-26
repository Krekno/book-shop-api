package com.bookshop.bookshopapi.controller;

import com.bookshop.bookshopapi.DTO.BookRequest;
import com.bookshop.bookshopapi.entity.Book;
import com.bookshop.bookshopapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final BookRepository bookRepository;

    @Autowired
    public AdminController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping
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
        savedBook = bookRepository.save(savedBook);
        return ResponseEntity.ok(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookRequest updatedBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();

            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setCategory(updatedBook.getCategory());
            existingBook.setQuantity(updatedBook.getQuantity());
            existingBook.setDescription(updatedBook.getDescription());
            existingBook.setImage(updatedBook.getImage());
            existingBook.setPublisher(updatedBook.getPublisher());
            existingBook.setPrice(updatedBook.getPrice());

            Book savedBook = bookRepository.save(existingBook);
            return ResponseEntity.ok(savedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}