package com.example.administrator.myp2p.service;

import android.telephony.PhoneNumberUtils;
import android.text.style.QuoteSpan;
import android.util.Log;

import com.example.administrator.myp2p.bean.Question;
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
    private List<Question> questions;
    private Question question = null;
    private volatile static DBService instance = null;
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
        Connection conn = null;
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

   public  List<Question> getQuestionById(String question_id) {
       questions = new ArrayList<>();
       questions.clear();
        HashMap<String, String> map = new HashMap<>();
        Connection conn = getConnection("soft");
        try {
            Statement st = conn.createStatement();
            String sql = "select * from t_question where id = '" + question_id + "'";
            ResultSet res = (ResultSet) st.executeQuery(sql);
            if (res == null) {
                Log.e("data===","res=null");
                return null;
            } else {
                int cnt = res.getMetaData().getColumnCount();
                int j =0;
                while (res.next()){
                    Log.e("data===","=next");
                    question = new Question();
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

