package com.example.administrator.myp2p;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    private String TAG = this.getClass().getName();
    protected static Toast mToast;
    protected AlertDialog.Builder builder;

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        initView();
        initBase();
        initData();
    }

    protected void initBase() {
        builder = new AlertDialog.Builder(this).setIcon(R.mipmap.ic_launcher).setTitle("提示")
                .setMessage("").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sureClick();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        negativeClick();
                        dialogInterface.dismiss();
                    }
                });
        builder.create();
    }

    protected  void sureClick(){

    }
    protected  void negativeClick(){

    }
    protected abstract int getLayout();

    public abstract void initView();
    public abstract void initData();

    public void toast(String str){
        if(mToast!=null)
        {
            mToast.setText(str);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        else
        {
            /*
             * 第一个参数：当前的上下文环境，用this或getApplicationContext()表示。
             * 第二个参数：显示的字符串，用R.string表示。
             * 第三个参数：显示的时间长短。用LENGTH_LONG(长)或LENGTH_SHORT(短)表示，也可以用毫秒。
             */
            mToast=Toast.makeText(this,str, Toast.LENGTH_SHORT);
        }
        mToast.show(); //显示toast信息
    }
}
