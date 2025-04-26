package com.bookshop.bookshopapi.repository;

import com.bookshop.bookshopapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
