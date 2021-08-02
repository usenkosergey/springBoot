package com.example.MyBookShopApp.dto.response;

public class AuthorDTO {
    private Integer id;
    private String photo;
    private String slug;
    private String name;
    private String description;
    private Integer countBooks;
    private String visibleBiographyBlock;
    private String hiddenBiographyBlock;


    public String getVisibleBiographyBlock() {
        return visibleBiographyBlock;
    }

    public void setVisibleBiographyBlock(String visibleBiographyBlock) {
        this.visibleBiographyBlock = visibleBiographyBlock;
    }

    public String getHiddenBiographyBlock() {
        return hiddenBiographyBlock;
    }

    public void setHiddenBiographyBlock(String hiddenBiographyBlock) {
        this.hiddenBiographyBlock = hiddenBiographyBlock;
    }

    public Integer getCountBooks() {
        return countBooks;
    }

    public void setCountBooks(Integer countBooks) {
        this.countBooks = countBooks;
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
}
