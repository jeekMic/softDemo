package com.example.administrator.myp2p.bean;

public class Recommend {
    private String title;
    private String number_ti;

    public Recommend() {
    }

    public Recommend(String title, String number_ti) {
        this.title = title;
        this.number_ti = number_ti;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumber_ti() {
        return number_ti;
    }

    public void setNumber_ti(String number_ti) {
        this.number_ti = number_ti;
    }
}
