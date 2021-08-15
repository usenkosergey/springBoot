package com.example.MyBookShopApp.dto.request;

public class RateBook {

    private Integer bookId;
    private Integer value;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = Integer.valueOf(bookId);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = Integer.valueOf(value);
    }

}
