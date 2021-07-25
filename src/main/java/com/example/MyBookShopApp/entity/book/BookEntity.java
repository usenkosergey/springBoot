package com.example.MyBookShopApp.entity.book;

import com.example.MyBookShopApp.entity.book.file.FileDownloadEntity;
import com.example.MyBookShopApp.entity.book.links.Book2AuthorEntity;
import com.example.MyBookShopApp.entity.book.links.Book2UserEntity;
import com.example.MyBookShopApp.entity.book.review.BookReviewEntity;
import com.example.MyBookShopApp.entity.genre.GenreEntity;
import com.example.MyBookShopApp.entity.payments.BalanceTransactionEntity;
import com.example.MyBookShopApp.entity.tag.TagEntity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pub_date", nullable = false)
    private Date pubDate;

    @Column(name = "is_bestseller", nullable = false)
    private Byte isBestseller;

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    private String title;

    private String image;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Integer price;

    @Column(columnDefinition = "SMALLINT NOT NULL DEFAULT 0")
    private Short discount;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer popularity;


    //************************
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "book2tags", indexes = {@Index(name = "book_id_in", columnList = "book_id"), @Index(name = "tag_id_in", columnList = "tag_id")},
            joinColumns = @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "BOOK_ID_TAG_FK")),
            inverseJoinColumns = @JoinColumn(name = "tag_id", foreignKey = @ForeignKey(name = "TAGS_ID_FK")))
    private List<TagEntity> tags = new ArrayList<>();
    //************************
    @OneToMany(mappedBy = "book")
    private List<Book2UserEntity> user = new ArrayList<>();
    //************************
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "book2genre",
            joinColumns = @JoinColumn(name = "bookId", foreignKey = @ForeignKey(name = "BOOK_ID_FK")),
            inverseJoinColumns = @JoinColumn(name = "genreId", foreignKey = @ForeignKey(name = "GENRE_ID_FK")))
    private List<GenreEntity> genres = new ArrayList<>();
    //************************
    @OneToMany(mappedBy = "book")
    private List<FileDownloadEntity> fileDownload = new ArrayList<>();
    //************************
    @OneToMany(mappedBy = "book")
    private List<BalanceTransactionEntity> balanceTransaction = new ArrayList<>();
    //************************
    @OneToMany(mappedBy = "book")
    private List<BookReviewEntity> bookReview = new ArrayList<>();
    //************************
    @OneToMany (mappedBy="book", fetch=FetchType.EAGER)
    private List<Book2AuthorEntity> book2author;
    //************************


    public Integer getPopularity() {
        return popularity;
    }

    public List<TagEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagEntity> tags) {
        this.tags = tags;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

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

    public Byte getIsBestseller() {
        return isBestseller;
    }

    public void setIsBestseller(Byte isBestseller) {
        this.isBestseller = isBestseller;
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

    public List<Book2UserEntity> getUser() {
        return user;
    }

    public void setUser(List<Book2UserEntity> user) {
        this.user = user;
    }

    public List<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreEntity> genres) {
        this.genres = genres;
    }

    public List<FileDownloadEntity> getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(List<FileDownloadEntity> fileDownload) {
        this.fileDownload = fileDownload;
    }

    public List<BalanceTransactionEntity> getBalanceTransaction() {
        return balanceTransaction;
    }

    public void setBalanceTransaction(List<BalanceTransactionEntity> balanceTransaction) {
        this.balanceTransaction = balanceTransaction;
    }

    public List<BookReviewEntity> getBookReview() {
        return bookReview;
    }

    public void setBookReview(List<BookReviewEntity> bookReview) {
        this.bookReview = bookReview;
    }

    public List<Book2AuthorEntity> getBook2author() {
        return book2author;
    }

    public void setBook2author(List<Book2AuthorEntity> book2author) {
        this.book2author = book2author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BookEntity bookEntity = (BookEntity) o;
        return Objects.equals(id, bookEntity.getId());

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
