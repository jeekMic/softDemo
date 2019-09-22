package com.example.administrator.myp2p.bean;

import java.io.Serializable;

public class QuestionPoint implements Serializable {
    private static final long serialVersionUID = 3L;
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
