package com.example.MyBookShopApp.dto.response;

import com.example.MyBookShopApp.entity.book.BookEntity;

import java.util.List;

public class BooksDTO {

    private Integer count;
    private List<BookEntity> books;

    public BooksDTO(List<BookEntity> books) {
        this.books = books;
        this.count = books.size();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }
}
