package com.example.MyBookShopApp.dto.response;

import java.util.List;

public class BooksDTO {

    private Integer count;
    private List<BookDTO> books;

    public BooksDTO() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
}
