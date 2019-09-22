package com.example.administrator.myp2p.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myp2p.BaseActivity;
import com.example.administrator.myp2p.R;
import com.example.administrator.myp2p.adapter.DataAnalysisRecycleAdapter;
import com.example.administrator.myp2p.bean.MenuInfo;
import com.example.administrator.myp2p.bean.Question;
import com.example.administrator.myp2p.bean.ResultQuestion;
import com.example.administrator.myp2p.service.DBService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.administrator.myp2p.util.UIUtils.getContext;

public class StatisticsActivity extends BaseActivity {

    protected List<Question> question;
    protected String courseid;
    protected String year;
    @BindView(R.id.rv_result_data)
    RecyclerView rvResultData;
    private int score;
    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.result_info)
    TextView resultInfo;

    private List<String> courseid2;
    private List<ResultQuestion> resultQuestions;
    private DataAnalysisRecycleAdapter adapter;
    private ResultQuestion resultQuestion;
    @Override
    protected int getLayout() {
        return R.layout.activity_statistics;
    }
    @Override
    public void initView() {
        Bundle bundle = getIntent().getExtras();
        if (question!=null){
            question.clear();
            question = null;
        }
        //得到序列化后的结果，包含所选的信息
        question = (List<Question>) bundle.getSerializable("question");
        //对应的courseid的信息
        courseid = bundle.getString("courseid");
        //哪一年的题目
        year = bundle.getString("year");
        titleTv.setText(year + "答题分析");
        courseid2 = new ArrayList<>();
        resultQuestions = new ArrayList<>();

    }

    @Override
    public void initData() {
        //统计分数
        score = 0;
        courseid2.clear();
        resultQuestions.clear();
        for (int i = 0; i < question.size(); i++) {
            //讲不同的标题存起来
            if (courseid2.contains(question.get(i).getCourseid2())) {
                for (int j = 0; j < resultQuestions.size(); j++){
                    if (resultQuestions.get(j).getCourseId().equals(question.get(i).getCourseid2()))
                    {
                        resultQuestions.get(j).all +=1;
                        if (question.get(i).getSelect().equals(question.get(i).getKeys())) {
                            resultQuestions.get(j).corrent+=1;
                            break;
                        }
                    }
                }
            } else {
                courseid2.add(question.get(i).getCourseid2());
                resultQuestion = new ResultQuestion();
                resultQuestion.all=1;
                resultQuestion.corrent = 0;
                resultQuestion.key = question.get(i).getKeys();
                resultQuestion.courseId = question.get(i).getCourseid2();
                resultQuestions.add(resultQuestion);
            }
            //所选择和正确的答案
            if (question.get(i).getSelect().equals(question.get(i).getKeys())) {
                score += 1;
            }
        }
        if (score<45){
            resultInfo.setText(" 总得分: " + score+"  考试不及格,需要加油哟！");
        }else {
            resultInfo.setText(" 总得分: " + score+"  恭喜你及格了");
        }
         adapter = new DataAnalysisRecycleAdapter(this,resultQuestions);
         rvResultData.setLayoutManager(new LinearLayoutManager(getContext()));
         rvResultData.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    /**
     * 通过知识点的情况进行分类统计
     */
    public void quePointForClassiy() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<MenuInfo> points = DBService.getInstance().getQuestionPoint();
            }
        }).start();
    }
}
