package com.example.administrator.myp2p.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myp2p.R;
import com.example.administrator.myp2p.service.DBService;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DayActivity extends AppCompatActivity {

    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.title_tv)
    TextView titleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleLeft.setVisibility(View.VISIBLE);
        titleTv.setText("每日一练");
    }

    public void onClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> mp = DBService.getUserInfoByName("32");
            }
        }).start();
    }
}
