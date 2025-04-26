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
    private Double publisher;
    private Double price;
    private byte[] image;
}
