package com.example.administrator.myp2p.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class Question {
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
    @Generated(hash = 1868476517)
    public Question() {
    }
    @Generated(hash = 255500365)
    public Question(String id, String title, int itype, String rank, String classid, String courseid, String courseid2, String answers, String keys, String explain, String score, boolean is_en, boolean status) {
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
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getItype() {
        return this.itype;
    }
    public void setItype(int itype) {
        this.itype = itype;
    }
    public String getRank() {
        return this.rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
    public String getClassid() {
        return this.classid;
    }
    public void setClassid(String classid) {
        this.classid = classid;
    }
    public String getCourseid() {
        return this.courseid;
    }
    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }
    public String getCourseid2() {
        return this.courseid2;
    }
    public void setCourseid2(String courseid2) {
        this.courseid2 = courseid2;
    }
    public String getAnswers() {
        return this.answers;
    }
    public void setAnswers(String answers) {
        this.answers = answers;
    }
    public String getKeys() {
        return this.keys;
    }
    public void setKeys(String keys) {
        this.keys = keys;
    }
    public String getExplain() {
        return this.explain;
    }
    public void setExplain(String explain) {
        this.explain = explain;
    }
    public String getScore() {
        return this.score;
    }
    public void setScore(String score) {
        this.score = score;
    }
    public boolean getIs_en() {
        return this.is_en;
    }
    public void setIs_en(boolean is_en) {
        this.is_en = is_en;
    }
    public boolean getStatus() {
        return this.status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }


}