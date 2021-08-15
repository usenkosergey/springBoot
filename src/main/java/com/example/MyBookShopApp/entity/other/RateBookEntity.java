package com.example.MyBookShopApp.entity.other;

import javax.persistence.*;

@Entity
@Table(name = "rate_book", indexes =
        {
                @Index(columnList = "book_id", name = "index_book_id"),
                @Index(columnList = "rating", name = "index_rating"),
                @Index(columnList = "rating_owner", name = "index_rating_owner")
        })
public class RateBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "book_id")
    private Integer bookId;

    private Integer rating;

    @Column(name = "rating_owner")
    private Integer ratingOwner;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getRatingOwner() {
        return ratingOwner;
    }

    public void setRatingOwner(Integer ratingOwner) {
        this.ratingOwner = ratingOwner;
    }
}
