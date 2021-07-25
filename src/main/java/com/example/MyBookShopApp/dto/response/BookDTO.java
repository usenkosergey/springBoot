package com.example.MyBookShopApp.dto.response;

public class BookDTO {
    private Integer id;
    private String slug;
    private String image;
    private String author; //“Сергей Дмитриев и другие”
    private String title;
    private Short discount;
    private Byte isBestseller;
    private Short rating;
    private String status; //KEPT (отложена), CART (в корзине) и PAID(куплена)
    private Integer price;
    private Integer discountPrice;
    private Integer popularity;

    public BookDTO() {
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Byte getIsBestseller() {
        return isBestseller;
    }

    public void setIsBestseller(Byte isBestseller) {
        this.isBestseller = isBestseller;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Short getDiscount() {
        return discount;
    }

    public void setDiscount(Short discount) {
        this.discount = discount;
    }

    public Short getRating() {
        return rating;
    }

    public void setRating(Short rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Integer discountPrice) {
        this.discountPrice = discountPrice;
    }

}
