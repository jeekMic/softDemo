package com.example.administrator.myp2p.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myp2p.BaseActivity;
import com.example.administrator.myp2p.R;
import com.example.administrator.myp2p.bean.Question;
import com.example.administrator.myp2p.contact.QuestionSearchContact;
import com.example.administrator.myp2p.presenter.ISearchPresenter;
import com.example.administrator.myp2p.util.UIUtils;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnswerActivity extends BaseActivity implements QuestionSearchContact.View {

    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.question_up)
    TextView questionUp;
    @BindView(R.id.quest_number)
    TextView questNumber;
    @BindView(R.id.question_down)
    TextView questionDown;
    @BindView(R.id.question_title)
    WebView questionTitle;
    @BindView(R.id.stand_answer)
    TextView standAnswer;
    @BindView(R.id.wv_explain)
    WebView wvExplain;
    @BindView(R.id.answer_a)
    TextView answerA;
    @BindView(R.id.answer_b)
    TextView answerB;
    @BindView(R.id.answer_c)
    TextView answerC;
    @BindView(R.id.answer_d)
    TextView answerD;
    @BindView(R.id.select_info)
    LinearLayout selectInfo;
    @BindView(R.id.sv_detail)
    ScrollView svDetail;
    @BindView(R.id.fab_1)
    FloatingActionButton fab1;
    @BindView(R.id.fab_2)
    FloatingActionButton fab2;
    @BindView(R.id.fab_3)
    FloatingActionButton fab3;
    @BindView(R.id.fab_4)
    FloatingActionButton fab4;
    @BindView(R.id.fab_menu)
    FloatingActionsMenu fabMenu;
    private ISearchPresenter presenter;
    private String year;
    private int key;
    private String courseid;
    private String title;
    private String num_point;
    private List<Question> data;
    private static int index = 1;
    private static String num = "";
    private String currentSelect="T";
    /**
     * 加载布局
     *
     * @return
     */
    @Override
    public int getLayout() {
        return R.layout.activity_answer;
    }

    @Override
    public void loadDataPointSuccess(List<Question> data) {
        this.data = data;
        index = 0;
        //知识点数据加载成功
        showData();
    }

    @Override
    public void loadDataCaseSuccess(List<Question> data) {
        //加载案例的数据成功
        this.data = data;
        index = 0;
        showData();
    }

    @Override
    public void loadDataSuccess(List<Question> data) {
        this.data = data;
        Log.e("AnswerActivity:", "加载数据成功，数据长度：" + data.size());
        showData();
    }

    @OnClick({R.id.answer_a, R.id.answer_b, R.id.answer_c, R.id.answer_d, R.id.question_up, R.id.question_down})
    public void clickView(View view) {
        answerA.setBackgroundResource(R.color.white);
        answerB.setBackgroundResource(R.color.white);
        answerC.setBackgroundResource(R.color.white);
        answerD.setBackgroundResource(R.color.white);
        switch (view.getId()) {
            case R.id.answer_a:
                toast("您选择了A");
                currentSelect = "A";
                data.get(index).setSelect(currentSelect);
                answerA.setBackgroundResource(R.color.pro_color);

                break;
            case R.id.answer_b:
                toast("您选择了B");
                currentSelect = "B";
                data.get(index).setSelect(currentSelect);
                answerB.setBackgroundResource(R.color.pro_color);
                break;
            case R.id.answer_c:
                toast("您选择了C");
                currentSelect = "C";
                data.get(index).setSelect(currentSelect);
                answerC.setBackgroundResource(R.color.pro_color);
                break;
            case R.id.answer_d:
                toast("您选择了D");
                currentSelect = "D";
                data.get(index).setSelect(currentSelect);
                answerD.setBackgroundResource(R.color.pro_color);
                break;
            case R.id.question_up:
                index -= 1;
                if (index == -1) {
                    index = 0;
                    toast("没有上一题了");
                } else {
                    showData();
                }
                break;
            case R.id.question_down:
                index += 1;
                if (index == 75) {
                    index = 74;
                    toast("没有下一题了");
                } else {
                    showData();
                }
                break;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                String message = "";
                if (index<Integer.parseInt(num)){
                    message = "还有题目未做完，是否交卷";
                }else {
                    message = "请确认是否交卷";
                }
                if (Integer.parseInt(num)==3){
                    message="确认是否退出";
                }
                builder.setMessage(message);
                builder.show();


                return false;//拦截事件

            default:
                break;
        }

        return super.onKeyDown(keyCode, event);

    }

    /**
     * 确认
     */
    @Override
    protected void sureClick() {
        super.sureClick();
        UIUtils.L("确认");
        if (Integer.parseInt(num)<4){
            finish();
        }else {
            Intent intent = new Intent(this,StatisticsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("question",(Serializable)data);
            bundle.putString("year",year);
            bundle.putString("courseid",courseid);
            // intent
            intent.putExtras(bundle);

            // navigate
            startActivity(intent);
            finish();
        }
    }
    /**
     * 取消
     */
    @Override
    protected void negativeClick() {
        super.negativeClick();
        UIUtils.L("取消");
    }

    public void showData() {
        String title = data.get(index).getTitle();
        SetWebView(title, questionTitle);
        String[] answer = data.get(index).getAnswers().split("=\\|\\|=");
        Log.e("showData", answer.length + "");
        for (int i = 0; i < answer.length; i++) {
            if (i == 0)
                answerA.setText("  " + answer[i]);
            if (i == 1)
                answerB.setText("  " + answer[i]);
            if (i == 2)
                answerC.setText("  " + answer[i]);
            if (i == 3)
                answerD.setText("  " + answer[i]);
        }
        String keys = data.get(index).getKeys();
        standAnswer.setText("标准答案： " + keys);

        String explain = data.get(index).getExplain();
        SetWebView("<p><font size=\"5\" face=\"arial\" color=\"#EE4000\">试题解析： \n" +
                "</font></p>" + explain, wvExplain);

        standAnswer.setVisibility(View.INVISIBLE);
        wvExplain.setVisibility(View.INVISIBLE);
        questNumber.setText("当前答题: " + (index + 1) + "/" + num);
    }

    public void SetWebView(String mybody, WebView wv) {
        String head = "<head><style>* {font-size:15px}{color:#212121;}img{max-width: 100%; width:auto; height: auto;}</style></head>";
        String resultStr = "<html>" + head + "<body>" + mybody + "</body></html>";
        wv.loadDataWithBaseURL(null, resultStr, "text/html", "utf-8", null);

    }

    @Override
    public void loadDataFaild() {
        Log.e("AnswerActivity:", "加载数据失败");
    }


    @Override
    public void initView() {
        Intent intent = this.getIntent();
        year = intent.getStringExtra("year");//年份
        key = intent.getIntExtra("key", 0);//0上午题, 1下午题 2知识点
        courseid = intent.getStringExtra("courseid");
        title = intent.getStringExtra("title");
        num_point = intent.getStringExtra("num");
        presenter = new ISearchPresenter(this);

    }

    @Override
    public void initData() {
        titleTv.setText(title);
        answerA.setVisibility(View.VISIBLE);
        answerB.setVisibility(View.VISIBLE);
        answerC.setVisibility(View.VISIBLE);
        answerD.setVisibility(View.VISIBLE);
        standAnswer.setVisibility(View.VISIBLE);
        if (key == 1) {//上午题答题模式
            num = "75";
            presenter.requestQuestion(courseid);

        } else if (key == 3) {//知识点答题
            num = "";
            for (int i = 0; i < num_point.length(); i++) {
                if (num_point.charAt(i) >= 48 && num_point.charAt(i) <= 57) {
                    num += num_point.charAt(i);
                }
            }
            presenter.requestPointion(courseid);
        } else if (key == 4) {//案例答题 案例题基本上只用看就行不用答题
            num = "3";
            presenter.requestCase(courseid);
            answerA.setVisibility(View.GONE);
            answerB.setVisibility(View.GONE);
            answerC.setVisibility(View.GONE);
            answerD.setVisibility(View.GONE);
            standAnswer.setVisibility(View.GONE);
        }

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast("显示答案");
                standAnswer.setVisibility(View.VISIBLE);
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast("查看解析");
                wvExplain.setVisibility(View.VISIBLE);
            }
        });


        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast("收藏");
            }
        });

        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "";
                if (index<Integer.parseInt(num)){
                    message = "还有题目未做完，是否交卷";
                }else {
                    message = "请确认是否交卷";
                }
                if (Integer.parseInt(num)==3){
                    message="确认是否退出";
                }
                builder.setMessage(message);
                builder.show();
            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
