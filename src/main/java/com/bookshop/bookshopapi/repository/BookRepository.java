package com.bookshop.bookshopapi.repository;

import com.bookshop.bookshopapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(Long isbn);

    boolean existsByIsbn(Long isbn);

    void deleteByIsbn(Long isbn);
}
