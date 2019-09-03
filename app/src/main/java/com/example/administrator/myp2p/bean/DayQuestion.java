package com.example.administrator.myp2p.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class DayQuestion {
    @Id
    private String id;
    private String title;
    private int  itype;
    private String rank;
    private String classid;
    private String courseid;
    private String courseid2;
    private String answers;
    private String keys;
    private String explain;
    private String score;
    private boolean is_en;
    private boolean status;
    @Generated(hash = 868536253)
    public DayQuestion() {
    }
    @Generated(hash = 295640197)
    public DayQuestion(String id, String title, int itype, String rank, String classid, String courseid, String courseid2, String answers, String keys, String explain, String score, boolean is_en, boolean status) {
        this.id = id;
        this.title = title;
        this.itype = itype;
        this.rank = rank;
        this.classid = classid;
        this.courseid = courseid;
        this.courseid2 = courseid2;
        this.answers = answers;
        this.keys = keys;
        this.explain = explain;
        this.score = score;
        this.is_en = is_en;
        this.status = status;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getCourseid2() {
        return courseid2;
    }

    public void setCourseid2(String courseid2) {
        this.courseid2 = courseid2;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public boolean isIs_en() {
        return is_en;
    }

    public void setIs_en(boolean is_en) {
        this.is_en = is_en;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public boolean getIs_en() {
        return this.is_en;
    }
    public boolean getStatus() {
        return this.status;
    }
}
