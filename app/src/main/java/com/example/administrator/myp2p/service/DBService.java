package com.example.administrator.myp2p.service;

import android.telephony.PhoneNumberUtils;
import android.util.Log;

import com.example.administrator.myp2p.util.DBOpenHelper;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.ta.utdid2.android.utils.StringUtils;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DBService {

    private static final String TAG = "DBService";
    private static Connection getConnection(String dbName) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); //加载驱动
            String ip = "47.96.166.159";
            conn = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://" + ip + ":3306/" + dbName,
                    "root", "soft@2019!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

   public static HashMap<String, String> getUserInfoByName(String name) {
        HashMap<String, String> map = new HashMap<>();
        Connection conn = getConnection("soft");
        try {
            Statement st = conn.createStatement();
            String sql = "select * from t_question where rank = '" + name + "'";
            ResultSet res = (ResultSet) st.executeQuery(sql);


            if (res == null) {
                return null;
            } else {
                int cnt = res.getMetaData().getColumnCount();
                int j =0;
                while (res.next()){
                    for (int i = 1; i <= cnt; i++) {
                        Log.e(res.getMetaData().getColumnName(i)+"===",res.getObject(i)+"");
                    }
                }
                conn.close();
                st.close();
                res.close();
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, " 数据操作异常");
            return null;
        }
    }

}

