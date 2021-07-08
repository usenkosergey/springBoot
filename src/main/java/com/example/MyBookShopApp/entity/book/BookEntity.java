package com.example.MyBookShopApp.entity.book;

import com.example.MyBookShopApp.entity.author.AuthorEntity;
import com.example.MyBookShopApp.entity.book.file.FileDownloadEntity;
import com.example.MyBookShopApp.entity.book.links.Book2UserEntity;
import com.example.MyBookShopApp.entity.book.review.BookReviewEntity;
import com.example.MyBookShopApp.entity.genre.GenreEntity;
import com.example.MyBookShopApp.entity.payments.BalanceTransactionEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pub_date", nullable = false)
    private Date pubDate;

    @Column(name = "is_bestseller", nullable = false)
    private Boolean isBestseller;

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    private String title;

    private String image;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Integer price;

    @Column(columnDefinition = "INT NOT NULL DEFAULT 0")
    private Short discount;

    //************************
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "book2author",
            joinColumns = @JoinColumn(name = "bookId", foreignKey = @ForeignKey(name = "BOOK_ID_TO_AUTHOR_FK")),
            inverseJoinColumns = @JoinColumn(name = "authorId", foreignKey = @ForeignKey(name = "AUTHOR_ID_FK")))
    private Set<AuthorEntity> authors = new HashSet<>();
    //************************
    @OneToMany(mappedBy = "book")
    private Set<Book2UserEntity> user = new HashSet<>();
    //************************
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "book2genre",
            joinColumns = @JoinColumn(name = "bookId", foreignKey = @ForeignKey(name = "BOOK_ID_FK")),
            inverseJoinColumns = @JoinColumn(name = "genreId", foreignKey = @ForeignKey(name = "GENRE_ID_FK")))
    private Set<GenreEntity> genres = new HashSet<>();
    //************************
    @OneToMany(mappedBy = "book")
    private Set<FileDownloadEntity> fileDownload = new HashSet<>();
    //************************
    @OneToMany(mappedBy = "book")
    private Set<BalanceTransactionEntity> balanceTransaction = new HashSet<>();
    //************************
    @OneToMany(mappedBy = "book")
    private Set<BookReviewEntity> bookReview = new HashSet<>();
    //************************


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Boolean getBestseller() {
        return isBestseller;
    }

    public void setBestseller(Boolean bestseller) {
        isBestseller = bestseller;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Short getDiscount() {
        return discount;
    }

    public void setDiscount(Short discount) {
        this.discount = discount;
    }

    public Set<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorEntity> authors) {
        this.authors = authors;
    }

    public Set<Book2UserEntity> getUser() {
        return user;
    }

    public void setUser(Set<Book2UserEntity> user) {
        this.user = user;
    }

    public Set<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreEntity> genres) {
        this.genres = genres;
    }

    public Set<FileDownloadEntity> getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(Set<FileDownloadEntity> fileDownload) {
        this.fileDownload = fileDownload;
    }

    public Set<BalanceTransactionEntity> getBalanceTransaction() {
        return balanceTransaction;
    }

    public void setBalanceTransaction(Set<BalanceTransactionEntity> balanceTransaction) {
        this.balanceTransaction = balanceTransaction;
    }

    public Set<BookReviewEntity> getBookReview() {
        return bookReview;
    }

    public void setBookReview(Set<BookReviewEntity> bookReview) {
        this.bookReview = bookReview;
    }
}
