package com.example.MyBookShopApp.dto.response;

import com.example.MyBookShopApp.entity.author.AuthorEntity;
import com.example.MyBookShopApp.entity.book.BookEntity;

import java.util.List;

public class AuthorsDTO {

    private Integer count;
    private List<AuthorEntity> authors;

    public AuthorsDTO(List<AuthorEntity> authors) {
        this.count = authors.size();
        this.authors = authors;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorEntity> authors) {
        this.authors = authors;
    }
}
