package com.example.administrator.myp2p.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

@Entity
public class Question implements Parcelable {
    private static final long serialVersionUID = 1L;
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
    private String select="E";

    protected Question(Parcel in) {
        id = in.readString();
        title = in.readString();
        itype = in.readInt();
        rank = in.readString();
        classid = in.readString();
        courseid = in.readString();
        courseid2 = in.readString();
        answers = in.readString();
        keys = in.readString();
        explain = in.readString();
        score = in.readString();
        is_en = in.readByte() != 0;
        status = in.readByte() != 0;
        select = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    @Generated(hash = 1868476517)
    public Question() {
    }
    @Generated(hash = 675433217)
    public Question(String id, String title, int itype, String rank, String classid, String courseid, String courseid2, String answers, String keys, String explain, String score, boolean is_en, boolean status,
            String select) {
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
        this.select = select;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeInt(itype);
        parcel.writeString(rank);
        parcel.writeString(classid);
        parcel.writeString(courseid);
        parcel.writeString(courseid2);
        parcel.writeString(answers);
        parcel.writeString(keys);
        parcel.writeString(explain);
        parcel.writeString(score);
        parcel.writeByte((byte) (is_en ? 1 : 0));
        parcel.writeByte((byte) (status ? 1 : 0));
        parcel.writeString(select);
    }
}
