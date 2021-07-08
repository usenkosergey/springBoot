package com.example.MyBookShopApp.entity.author;

import com.example.MyBookShopApp.entity.book.BookEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "author")
@ApiModel(description = "Это сущность - АВТОР")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Это поле генериться автоматически базой данных")
    private Integer id;

    private String photo;

    @Column(nullable = false)
    private String slug;

    @ApiModelProperty(value = "Фамилия и Имя автора", example = "Иванов Иван")
    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany(mappedBy = "authors")
    @JsonIgnore
    private Set<BookEntity> books = new HashSet<>();

    public Set<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(Set<BookEntity> books) {
        this.books = books;
    }

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

    @Override
    public String toString() {
        return name;
    }
}
