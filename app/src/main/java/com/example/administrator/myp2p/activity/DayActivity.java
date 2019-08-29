package com.example.administrator.myp2p.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myp2p.R;
import com.example.administrator.myp2p.bean.Question;
import com.example.administrator.myp2p.service.DBService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DayActivity extends AppCompatActivity {
    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.tv_result)
    TextView tvResult;
    private List<Integer> randomList;
    private StringBuilder builder;
    //userid 2364
    //openid
    //ovHEN5F01HFUksMXu162UFNm3-3U
    //headurl https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKmW5VOrLibiadoRz4PKTyr9aShEompvmETaWKpbxWmCvCCGhSh81MjcWbHxlJ6eHal6s4c7L2PVxUQ/132
    //select * from t_everyday where userid=2364;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        Log.e("hongbiao","进入每日一练");
        ButterKnife.bind(this);
        initView();
        //生成10个随机题目的序号.测试阶段先不插入数据库
        getRandom();
        //根据题号查找这是个题目
        searchQuestion();

    }

    private void searchQuestion() {
    }


    private void initView() {
        titleLeft.setVisibility(View.VISIBLE);
        titleTv.setText("每日一练");
    }

    public void onClick(View view) {
        builder = new StringBuilder();
        builder.setLength(0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Question> userInfoByName = DBService.getInstance().getQuestionById("15");
                for (int i=0;i<userInfoByName.size();i++){
                    String[] answer = userInfoByName.get(i).getAnswers().split("=\\|\\|=");
                    Log.e("answer==",userInfoByName.get(i).getAnswers());
                    builder.append("题目:"+userInfoByName.get(i).getTitle()+"\n");
                    builder.append("A、"+answer[0]+"\n");
                    builder.append("B、"+answer[1]+"\n");
                    builder.append("C、"+answer[2]+"\n");
                    builder.append("D、"+answer[3]+"\n");

                    for (int j=0;j<answer.length;j++){
                        Log.e("answer==",answer[j]);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvResult.setText(builder.toString());
                        }
                    });
                }

            }
        }).start();
    }

    public List<Integer> getRandom() {
        randomList = new ArrayList<>();
        randomList.clear();
        Random r = new Random(1);
        for (int i = 0; i < 10; i++) {
            int ran1 = r.nextInt(1000);
            randomList.add(ran1);
        }
        return randomList;
    }
}
