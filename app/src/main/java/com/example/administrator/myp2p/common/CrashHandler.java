package com.example.administrator.myp2p.common;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 处理函数崩溃异常的能力
 * CrashHandler 设计为单例模式
 */
public class CrashHandler implements UncaughtExceptionHandler{
    private static CrashHandler  mCrashHandler =null;

    private CrashHandler() {

    }
    public  static CrashHandler getInstance(){
        if (mCrashHandler==null){
            mCrashHandler = new CrashHandler();
        }
        return mCrashHandler;
    }
    private Context mContext;
    private Thread.UncaughtExceptionHandler defaultUncashHandler;
    public void init(Context context){
        //将CrashHandler作为默认的异常捕捉器
        this.mContext = context;
        defaultUncashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 记录日志信息并且汉化，发送到后台
     * @param t
     * @param e
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.e("哈哈","出错了");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //子线程不能谈toast
                Looper.prepare();
                Toast.makeText(mContext, "标哥为此感到很抱歉,程序出错了!", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
        try {
            Thread.sleep(2000);
            AppManager.getInstance().removeAll();
            android.os.Process.killProcess(android.os.Process.myPid());
            //java虚拟机关了
            System.exit(0);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

    }
}
