package com.example.MyBookShopApp.entity.book.links;

import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.entity.user.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "book_2_not_registered_user")
@Entity
public class Book2NotRegisteredUserEntyti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime time;

    @Column(nullable = false)
    private int typeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "BOOK_ID_FK"))
    private BookEntity book;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "USER_ID_FK"))
    private UserEntity user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}