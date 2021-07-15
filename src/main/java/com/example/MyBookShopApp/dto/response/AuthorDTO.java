package com.example.MyBookShopApp.dto.response;

import com.example.MyBookShopApp.entity.book.BookEntity;

import java.util.ArrayList;
import java.util.List;

public class AuthorDTO {
    private Integer id;
    private String photo;
    private String slug;
    private String name;
    private String description;
    //private List<BookEntity> books = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
