package com.bookshop.bookshopapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "isbn", unique = true, nullable = false)
    private Long isbn;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image", nullable = false)
    private byte[] image;

    @Column(name = "publisher", nullable = false)
    private String publisher;

    @Column(name = "price", nullable = false)
    private Double price;
}
