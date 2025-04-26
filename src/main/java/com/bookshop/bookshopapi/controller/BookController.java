package com.bookshop.bookshopapi.controller;

import com.bookshop.bookshopapi.entity.Book;
import com.bookshop.bookshopapi.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {
    private final BookRepository bookRepository;

    @GetMapping
    public Page<Book> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort
            ) {
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Pageable paging = PageRequest.of(page, size, Sort.by(direction, sort[0]));
        return bookRepository.findAll(paging);
    }
}
