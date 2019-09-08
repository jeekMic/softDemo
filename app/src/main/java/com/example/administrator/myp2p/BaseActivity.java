package com.example.administrator.myp2p;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    private String TAG = this.getClass().getName();
    private Toast mToast;
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
        initData();
    }

    protected abstract int getLayout();

    public abstract void initView();
    public abstract void initData();

    public void toast(String str){
        if (mToast!=null){
            mToast.setText(str);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }else {
            mToast = Toast.makeText(this,str,Toast.LENGTH_SHORT);
            mToast.show();
        }

    }
}
