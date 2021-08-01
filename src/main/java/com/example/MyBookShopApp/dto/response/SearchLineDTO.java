package com.example.MyBookShopApp.dto.response;

public class SearchLineDTO {

    private String searchLine;
    private Integer countBooks;

    public Integer getCountBooks() {
        return countBooks;
    }

    public void setCountBooks(Integer countBooks) {
        this.countBooks = countBooks;
    }

    public String getSearchLine() {
        return searchLine;
    }

    public void setSearchLine(String searchLine) {
        this.searchLine = searchLine;
    }
}
