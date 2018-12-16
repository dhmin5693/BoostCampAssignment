package com.min.boostcamp.boostcampassignment.vo;

import java.util.List;

public class Repo {

    private int code;
    private String message;
    private List<MovieItem> items;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MovieItem> getItems() {
        return items;
    }

    public void setItems(List<MovieItem> items) {
        this.items = items;
    }
}
