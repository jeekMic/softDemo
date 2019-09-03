package com.example.administrator.myp2p.bean;

public class MenuInfo {
    private String title;
    private String number;

    public MenuInfo() {
    }

    public MenuInfo(String title, String number) {
        this.title = title;
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
