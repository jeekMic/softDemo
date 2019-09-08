package com.example.administrator.myp2p.bean;

public class MenuInfo {
    private String title;
    private String number;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MenuInfo() {
    }

    public MenuInfo(String title, String number,String id) {
        this.title = title;
        this.number = number;
        this.id = id;
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
