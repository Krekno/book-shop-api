package com.bookshop.bookshopapi.controller;

import com.bookshop.bookshopapi.DTO.BookRequest;
import com.bookshop.bookshopapi.entity.Book;
import com.bookshop.bookshopapi.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

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
        URI location = URI.create("/books/" + savedBook.getId());
        return ResponseEntity.created(location).body(savedBook);
    }

    @PutMapping("update/{id}")
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
            URI location = URI.create("/books/" + savedBook.getId());
            return ResponseEntity.created(location).body(savedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}