package com.example.MyBookShopApp.entity.book.links;

import javax.persistence.*;

@Entity
@Table(name = "book2genre")
public class Book2GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "book_id", nullable = false)
    private Integer bookId;

    @Column(name = "genre_id", nullable = false)
    private Integer genreId;
}
