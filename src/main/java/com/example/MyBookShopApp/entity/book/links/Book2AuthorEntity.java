package com.example.MyBookShopApp.entity.book.links;

import com.example.MyBookShopApp.entity.book.BookEntity;

import javax.persistence.*;

@Entity
@Table(name = "book2author")
public class Book2AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "book_id", nullable = false)
    private Integer bookId;

    @Column(nullable = false)
    private Integer authorId;

    @Column(columnDefinition = "INT NOT NULL DEFAULT 0")
    private int sortIndex;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", updatable = false, insertable = false)
    private BookEntity book;

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
