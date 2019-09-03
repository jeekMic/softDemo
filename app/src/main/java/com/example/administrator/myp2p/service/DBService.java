package com.example.administrator.myp2p.service;

import android.telephony.PhoneNumberUtils;
import android.text.style.QuoteSpan;
import android.util.Log;

import com.example.administrator.myp2p.bean.DayQuestion;
import com.example.administrator.myp2p.bean.MenuInfo;
import com.example.administrator.myp2p.bean.Question;
import com.example.administrator.myp2p.bean.QuestionPoint;
import com.example.administrator.myp2p.util.DBOpenHelper;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.ta.utdid2.android.utils.StringUtils;

import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBService {
    private static Object object =new Object();
    private static final String TAG = "DBService";
    private List<DayQuestion> questions;
    private DayQuestion question = null;
    private volatile static DBService instance = null;
    private List<String> menus;
//    private List<QuestionPoint> points;
//    private QuestionPoint point;
    private Connection conn = null;
    private List<String> halfyear;
    private List<MenuInfo> menuInfos;
    private MenuInfo menuInfo;
    public static  DBService getInstance(){
        if (instance==null){
            synchronized (object){
                if (instance==null){
                    instance = new DBService();
                }
            }
        }
        return instance;
    }
    private DBService(){

    }
    private  Connection getConnection(String dbName) {
        try {
            Class.forName("com.mysql.jdbc.Driver"); //加载驱动
            String ip = "47.96.166.159";
            conn = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://" + ip + ":3306/" + dbName+"?useUnicode=true&characterEncoding=utf8&useSSL=false",
                    "root", "soft@2019!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    //获取上下午题
    public List<MenuInfo> getMorningQuestionList(int index){
        String str ="";
        int id= 1;
        if (index==0){
            id= 1;
            str = "上午题";
        }else {
            id=2;
            str = "下午案例分析";
        }
        conn = getConnection("soft");
        Statement st = null;
        halfyear = new ArrayList<>();
        menuInfos = new ArrayList<>();

        try {
            st = conn.createStatement();

            String sql = "select iyear, ihalfyear from t_course where title='"+str+"' and classid="+id+" order by iyear desc";
            Log.e("sql-",sql);
            ResultSet res = (ResultSet) st.executeQuery(sql);
            if (res == null) {
                Log.e("data===","res=null");
                return null;
            } else {
                while (res.next()){
                    menuInfo = new MenuInfo();
                    if (res.getInt(2)==1){
                        if (index==0)menuInfo.setTitle(res.getInt(1)+"上半年上午题");
                        if (index==1)menuInfo.setTitle(res.getInt(1)+"上半年下午案例分析");
                    }else {
                        if (index==0)menuInfo.setTitle(res.getInt(1)+"下半年上午题");
                        if (index==1)menuInfo.setTitle(res.getInt(1)+"下半年下午案例分析");
                    }
                    if (index==0) menuInfo.setNumber("75考题");
                    if (index==1) menuInfo.setNumber("3考题");
                    menuInfos.add(menuInfo);
                    Log.e("sql-",menuInfo.getTitle());
                }
                conn.close();
                st.close();
                res.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuInfos;
    }
    //获取所有的年份
   public List<String>  getYears(){
       menus = new ArrayList<>();
       conn = getConnection("soft");
       Statement st = null;
       try {
           st = conn.createStatement();
           String sql = "select * from allyear order by id desc";
           ResultSet res = (ResultSet) st.executeQuery(sql);
           if (res == null) {
               Log.e("data===","res=null");
               return null;
           } else {
               int cnt = res.getMetaData().getColumnCount();
               int j =0;
               while (res.next()){
                   menus.add(res.getString(2));
               }
               conn.close();
               st.close();
               res.close();
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }

        return menus;
   }
   //根据题号获取题目的信息
   public  List<DayQuestion> getQuestionById(String... question_id) {
        StringBuffer buffer = new StringBuffer();
        buffer.setLength(0);
        for (int i=0;i<question_id.length;i++){
            if (i==0){
                buffer.append(""+question_id[i]);
            }else {
                buffer.append(" or id="+question_id[i]);
            }
        }
       questions = new ArrayList<>();
       questions.clear();
        HashMap<String, String> map = new HashMap<>();
       conn = getConnection("soft");
        try {
            Statement st = conn.createStatement();
            String sql = "select * from t_question where id = " + buffer.toString() + "";
            ResultSet res = (ResultSet) st.executeQuery(sql);
            if (res == null) {
                Log.e("data===","res=null");
                return null;
            } else {
                int cnt = res.getMetaData().getColumnCount();
                int j =0;
                while (res.next()){
                    Log.e("data===","=next");
                    question = new DayQuestion();
                    question.setId(res.getObject(1).toString());
                    question.setTitle(res.getObject(2).toString());
                    question.setItype(res.getInt(3));
                    question.setRank(res.getObject(4).toString());
                    question.setClassid(res.getObject(5).toString());
                    question.setCourseid(res.getObject(6).toString());
                    question.setCourseid2(res.getObject(7).toString());
                    question.setAnswers(res.getObject(8).toString());
                    question.setKeys(res.getObject(9).toString());
                    question.setExplain(res.getObject(10).toString());
                    question.setScore(res.getObject(11).toString());
                    question.setIs_en((Boolean) res.getObject(12));
                    question.setStatus((Boolean) res.getObject(13));
                    questions.add(question);
                    Log.e("data===","=add");
                }
                conn.close();
                st.close();
                res.close();
                return questions;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, " 数据操作异常");
            return null;
        }
    }
   //读取知识点的数据
    public List<MenuInfo> getQuestionPoint(){
        menuInfos = new ArrayList<>();
        conn = getConnection("soft");
        Statement st = null;
        try {
            st = conn.createStatement();
            String sql = "select c.title,count(*) from t_course as c,t_question as q where c.id=q.courseid2 and c.classid=2 and c.itype=1 group by c.title";
            ResultSet res = (ResultSet) st.executeQuery(sql);
            if (res == null) {
                Log.e("data===","res=null");
                return null;
            } else {
                while (res.next()){
                    menuInfo = new MenuInfo();
                    menuInfo.setTitle(res.getString(1));
                    menuInfo.setNumber(res.getInt(2)+"考题");
                    menuInfos.add(menuInfo);
                }
                conn.close();
                st.close();
                res.close();
                return menuInfos;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    private static String upCaseFirstChar(String str) {
        if(str!=null&&str.length()>1)
        {
            str=str.substring(0,1).toUpperCase()+str.substring(1);
        }
        return str;
    }

    /**
     * 字符串大写换小写
     * @param str
     * @return
     */
    public static String lowerCaseChar(String str){
        char[] chars = str.toCharArray();
        for(int i=0;i<chars.length;i++){
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] = (char)(chars[i] + 32);
            }
        }

        return new String(chars);
    }


}

