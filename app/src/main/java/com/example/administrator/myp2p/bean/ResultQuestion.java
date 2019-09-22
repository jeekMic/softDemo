package com.example.administrator.myp2p.bean;

public class ResultQuestion {
    public String courseId;
    public int all;
    public int corrent;
    public String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ResultQuestion() {
    }



    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }


    public int getCorrent() {
        return corrent;
    }

    public void setCorrent(int corrent) {
        this.corrent = corrent;
    }
}
