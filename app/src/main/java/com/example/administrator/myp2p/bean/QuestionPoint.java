package com.example.administrator.myp2p.bean;

public class QuestionPoint {
    private String title;
    private String num;

    public QuestionPoint() {
    }

    public QuestionPoint(String title, String num) {
        this.title = title;
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
