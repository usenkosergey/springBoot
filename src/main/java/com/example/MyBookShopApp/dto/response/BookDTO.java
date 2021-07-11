package com.example.MyBookShopApp.dto.response;

public class BookDTO {
    private Integer id;
    private String slug;
    private String image;
    private String authors; //“Сергей Дмитриев и другие”
    private String title;
    private Short discount;
    private Boolean isBestseller;
    private Short rating;
    private String status; //KEPT (отложена), CART (в корзине) и PAID(куплена)
    private Integer price;
    private Integer discountPrice;

    public BookDTO() {
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

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
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

    public Boolean getBestseller() {
        return isBestseller;
    }

    public void setBestseller(Boolean bestseller) {
        isBestseller = bestseller;
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

    @Override
    public String toString() {
        return "\nBookDTO -> {" +
                "id=" + id +
                ", slug='" + slug + '\'' +
                ", image='" + image + '\'' +
                ", authors='" + authors + '\'' +
                ", title='" + title + '\'' +
                ", discount=" + discount +
                ", isBestseller=" + isBestseller +
                ", rating=" + rating +
                ", status='" + status + '\'' +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                '}';
    }

}
