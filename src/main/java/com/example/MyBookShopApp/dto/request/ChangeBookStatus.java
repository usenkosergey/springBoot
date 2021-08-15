package com.example.MyBookShopApp.dto.request;

public class ChangeBookStatus {

    private String[] booksIds;
    private String status;

    public String[] getBooksIds() {
        return booksIds;
    }

    public void setBooksIds(String[] booksIds) {
        this.booksIds = booksIds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ChangeBookStatus{" +
                "booksIds='" + booksIds + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
