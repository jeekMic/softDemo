package com.example.administrator.myp2p.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myp2p.MyApplication;
import com.example.administrator.myp2p.R;
import com.example.administrator.myp2p.bean.DayQuestion;
import com.example.administrator.myp2p.gbean.DayQuestionDao;
import com.example.administrator.myp2p.gbean.QuestionDao;
import com.example.administrator.myp2p.service.DBService;
import com.example.administrator.myp2p.util.UIUtils;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DayActivity extends AppCompatActivity {
    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.question_title)
    WebView questionTitle;
    @BindView(R.id.quest_number)
    TextView questNumber;
    @BindView(R.id.answer_a)
    WebView answerA;
    @BindView(R.id.answer_b)
    WebView answerB;
    @BindView(R.id.answer_c)
    WebView answerC;
    @BindView(R.id.answer_d)
    WebView answerD;
    @BindView(R.id.stand_answer)
    TextView standAnswer;
    @BindView(R.id.wv_explain)
    WebView wvExplain;
    @BindView(R.id.question_up)
    TextView questionUp;
    @BindView(R.id.question_down)
    TextView questionDown;
    private String[] randomList;
    List<DayQuestion> userInfoByName;
    private int question_int = 0;
    private StringBuilder builder;
    private QuestionDao dao;
    private DayQuestionDao day_dao;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Myhandler myhandler;
    //userid 2364
    //openid
    //ovHEN5F01HFUksMXu162UFNm3-3U
    //headurl https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKmW5VOrLibiadoRz4PKTyr9aShEompvmETaWKpbxWmCvCCGhSh81MjcWbHxlJ6eHal6s4c7L2PVxUQ/132
    //select * from t_everyday where userid=2364;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        ButterKnife.bind(this);
        initView();
    }

    static class Myhandler extends Handler {
        private WeakReference<DayActivity> reference;

        public Myhandler(DayActivity activity) {
            this.reference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1://加载数据完成
                    reference.get().showData(0);
                    break;
                default:
                    break;
            }
        }


    }

    public void showData(int num) {
        question_int = num;
        String title = userInfoByName.get(question_int).getTitle();
        SetWebView(title, questionTitle);
        String[] answer = userInfoByName.get(question_int).getAnswers().split("=\\|\\|=");
        for (int i = 0; i < answer.length; i++) {
            if (i == 0)
                SetWebView("&nbspA、" + answer[i], answerA);
            if (i == 1)
                SetWebView("&nbspB、" + answer[i], answerB);
            if (i == 2)
                SetWebView("&nbspC、" + answer[i], answerC);
            if (i == 3)
                SetWebView("&nbspD、" + answer[i], answerD);
        }
        String keys = userInfoByName.get(question_int).getKeys();
        standAnswer.setText("标准答案： " + keys);
        String explain = userInfoByName.get(question_int).getExplain();
        SetWebView("<p><font size=\"5\" face=\"arial\" color=\"#EE4000\">试题解析： \n" +
                "</font></p>" + explain, wvExplain);
        questNumber.setText("当前答题: "+(question_int+1)+"/10");
    }

    public void SetWebView(String mybody, WebView wv) {
        String head = "<head><style>* {font-size:15px}{color:#212121;}img{max-width: 100%; width:auto; height: auto;}</style></head>";
        String resultStr = "<html>" + head + "<body>" + mybody + "</body></html>";
        wv.loadDataWithBaseURL(null, resultStr, "text/html", "utf-8", null);
    }

    private void searchQuestion() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                day_dao.deleteAll();
                userInfoByName = DBService.getInstance().getQuestionById(randomList);
                day_dao.insertInTx(userInfoByName);
                Message message = Message.obtain();
                message.what = 1;
                myhandler.sendMessage(message);
//                for (int i = 0; i < userInfoByName.size(); i++){
//                    Log.e("查询数据",""+userInfoByName.get(i).getTitle());
//                }
            }
        }).start();

    }


    private void initView() {
        titleLeft.setVisibility(View.VISIBLE);
        titleTv.setText("每日一练");
        dao = MyApplication.getDaoSession().getQuestionDao();
        day_dao = MyApplication.getDaoSession().getDayQuestionDao();
        sharedPreferences = getSharedPreferences("clender", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String day = sharedPreferences.getString("clender", "0");
        boolean isSameDay = UIUtils.isSameData(day, System.currentTimeMillis() + "");
        myhandler = new Myhandler(this);
        if (!isSameDay) { //不是同一天就产生10个数据放到数据库里面
            //生成10个随机题目的序号.测试阶段先不插入数据库
            getRandom();
            //根据题号查找这是个题目
            searchQuestion();
        } else {
            //如果是同一天的话就直接访问数据库里面的数据就行sqlite 这里访问本地数据库都是采用GreenDao
            userInfoByName = day_dao.loadAll();
            Message message = Message.obtain();
            message.what = 1;
            myhandler.sendMessage(message);
        }

    }

    @OnClick({R.id.question_up, R.id.question_down,R.id.answer_a,R.id.answer_b,R.id.answer_c})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.question_up:
                question_int = question_int-1;
                if (question_int<=-1){
                    Toast.makeText(this, "没有上一题了", Toast.LENGTH_SHORT).show();
                }else {
                    showData(question_int);
                }
                break;
            case R.id.question_down:
                question_int = question_int+1;
                if (question_int>=10){
                    Toast.makeText(this, "没有下一题了", Toast.LENGTH_SHORT).show();
                }else {
                    showData(question_int);
                }
                break;
            case R.id.answer_a:
                Toast.makeText(this, "您选择了A", Toast.LENGTH_SHORT).show();
                break;
            case R.id.answer_b:
                Toast.makeText(this, "您选择了B", Toast.LENGTH_SHORT).show();
                break;
            case R.id.answer_c:
                Toast.makeText(this, "您选择了C", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    public String[] getRandom() {
        randomList = new String[10];

        Random r = new Random(1);
        for (int i = 0; i < 10; i++) {
            int ran1 = r.nextInt(1000);
            randomList[i] = ran1 + "";
        }
        return randomList;
    }
}
