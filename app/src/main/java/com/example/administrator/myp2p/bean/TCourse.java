package com.example.administrator.myp2p.bean;

import java.io.Serializable;

public class TCourse implements Serializable {
    private static final long serialVersionUID =2L;
    private int id;
    private int classid;
    private String title;
    private int itype;
    private int iyear;
    private int ihalfyear;
    private int ihalfday;
    private String image;
    private String intro;
    private int sort;
    private int ifree;

    public TCourse(int id, int classid, String title, int itype, int iyear, int ihalfyear, int ihalfday, String image, String intro, int sort, int ifree) {
        this.id = id;
        this.classid = classid;
        this.title = title;
        this.itype = itype;
        this.iyear = iyear;
        this.ihalfyear = ihalfyear;
        this.ihalfday = ihalfday;
        this.image = image;
        this.intro = intro;
        this.sort = sort;
        this.ifree = ifree;
    }

    public TCourse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getItype() {
        return itype;
    }

    public void setItype(int itype) {
        this.itype = itype;
    }

    public int getIyear() {
        return iyear;
    }

    public void setIyear(int iyear) {
        this.iyear = iyear;
    }

    public int getIhalfyear() {
        return ihalfyear;
    }

    public void setIhalfyear(int ihalfyear) {
        this.ihalfyear = ihalfyear;
    }

    public int getIhalfday() {
        return ihalfday;
    }

    public void setIhalfday(int ihalfday) {
        this.ihalfday = ihalfday;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getIfree() {
        return ifree;
    }

    public void setIfree(int ifree) {
        this.ifree = ifree;
    }
}
