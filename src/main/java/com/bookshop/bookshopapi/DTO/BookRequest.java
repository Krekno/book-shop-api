package com.bookshop.bookshopapi.DTO;

import lombok.Data;

@Data
public class BookRequest {
    private Long isbn;
    private String title;
    private String author;
    private String category;
    private Integer quantity;
    private String description;
    private String publisher;
    private Double price;
    private String image;
}
