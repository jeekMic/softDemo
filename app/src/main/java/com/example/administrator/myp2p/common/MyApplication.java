package com.example.administrator.myp2p.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

public class MyApplication extends Application {
    public static Context context = null;
    public static Handler handler = null;
    public static Thread mainThread= null;
    public static int mainThreadID = 0;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mainThread = Thread.currentThread();
        mainThreadID = Process.myTid();
        CrashHandler.getInstance().init(this);
    }
}
