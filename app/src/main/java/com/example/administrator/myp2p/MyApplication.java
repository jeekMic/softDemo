package com.example.administrator.myp2p;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Process;

import com.example.administrator.myp2p.gbean.DaoMaster;
import com.example.administrator.myp2p.gbean.DaoSession;

public class MyApplication extends Application {
    private static DaoSession daoSession;
    public static Context context = null;
    public static Handler handler = null;
    public static Thread mainThread= null;
    public static int mainThreadID = 0;
    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }
    private void initGreenDao() {
        context = getApplicationContext();
        handler = new Handler();
        mainThread = Thread.currentThread();
        mainThreadID = Process.myTid();
        //创建OpenHelper类
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "question.db");
        //开启一个可写的数据库类
        SQLiteDatabase writableDatabase = openHelper.getWritableDatabase();
        //通过DaoMaster封装
        DaoMaster master = new DaoMaster(writableDatabase);
        daoSession = master.newSession();
    }
    public static DaoSession getDaoSession() {
        return daoSession;
    }

}
