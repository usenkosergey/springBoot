package com.example.MyBookShopApp.dto.response;

public class ResultDTO {

    private Boolean result;
    private String error;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
