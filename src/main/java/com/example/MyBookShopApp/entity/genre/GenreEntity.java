package com.example.MyBookShopApp.entity.genre;

import com.example.MyBookShopApp.entity.book.BookEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genre")
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = true)
    private GenreEntity parentId;

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "genres")
    private List<BookEntity> books = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, mappedBy = "parentId")
    //@JoinColumn(name = "id", referencedColumnName = "parent_id")
    private List<GenreEntity> subGenres;

    public List<GenreEntity> getSubGenres() {
        return subGenres;
    }

    public void setSubGenres(List<GenreEntity> subGenres) {
        this.subGenres = subGenres;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GenreEntity getParentId() {
        return parentId;
    }

    public void setParentId(GenreEntity parentId) {
        this.parentId = parentId;
    }

    //    public int getParentId() {
//        return parentId;
//    }
//
//    public void setParentId(int parentId) {
//        this.parentId = parentId;
//    }

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
}
