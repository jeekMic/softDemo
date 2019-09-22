package com.example.administrator.myp2p.fragment;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.example.administrator.myp2p.R;
import com.example.administrator.myp2p.activity.AnswerActivity;
import com.example.administrator.myp2p.adapter.QuestionBankRecycleViewAdpater;
import com.example.administrator.myp2p.bean.MenuInfo;
import com.example.administrator.myp2p.bean.Question;
import com.example.administrator.myp2p.bean.TCourse;
import com.example.administrator.myp2p.contact.QuestionBankContact;
import com.example.administrator.myp2p.contact.QuestionSearchContact;
import com.example.administrator.myp2p.imple.DialogSelect;
import com.example.administrator.myp2p.imple.OnRecycleItemClick;
import com.example.administrator.myp2p.presenter.QuestionBankFragmentPresenter;
import com.example.administrator.myp2p.service.DBService;
import com.example.administrator.myp2p.util.UIUtils;
import com.loopj.android.http.RequestParams;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class QuestionBankFragment extends BaseFragment implements QuestionBankContact.View, RadioGroup.OnCheckedChangeListener, OnRecycleItemClick {
    @BindView(R.id.title_tv)
    TextView tvTitle;
    @BindView(R.id.title_left)
    ImageView titleLeft;

    @BindView(R.id.question_bank_recycle)
    RecyclerView questionBankRecycle;
    @BindView(R.id.top_year)
    RadioButton topYear;
    @BindView(R.id.top_morning)
    RadioButton topMorning;
    @BindView(R.id.top_after)
    RadioButton topAfter;
    @BindView(R.id.top_point)
    RadioButton topPoint;
    @BindView(R.id.raido_group)
    RadioGroup raidoGroup;

    private List<String> mDatas;
    private List<MenuInfo> mens;
    private QuestionBankRecycleViewAdpater adapter;
    Unbinder unbinder;
    MenuInfo info;
    private MyHandler handler;
    private List<Fragment> fragmentlist = new ArrayList<>();
    private QuestionBankFragmentPresenter presenter;
    @Override
    protected RequestParams getParams() {
        return null;
    }


    @Override
    protected void initData(String data) {

    }

    @Override
    protected void initTitle() {
        tvTitle.setText("题库");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_quest_bank;
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mens = new ArrayList<>();
        adapter = new QuestionBankRecycleViewAdpater(getContext(), mens,this);
        handler = new MyHandler(this);
        questionBankRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        questionBankRecycle.setAdapter(adapter);
        raidoGroup.setOnCheckedChangeListener(this);
        presenter = new QuestionBankFragmentPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        topYear.toggle();
        tab1();
    }



    //加载年份数据
    private void tab1() {
        queList();
    }

    private void queList() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> years = DBService.getInstance().getYears();
                mens.clear();
                for (int i = 0; i < years.size(); i++) {
                    info = new MenuInfo();
                    info.setTitle(years.get(i) + " 年信息系统项目师真题(高级)");
                    Log.e("数据===", years.get(i) + "");
                    info.setNumber("点击进入");
                    info.setId(years.get(i));
                    mens.add(info);
                }
                Message message = Message.obtain();
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.top_year:
                tab1();
                break;
            case R.id.top_morning:
                tab2();
                break;
            case R.id.top_after:
                tab3();
                break;
            case R.id.top_point:
                tab4();
                break;
            default:
                break;

        }

    }
    //recycleView的点击事件
    @Override
    public void onItemClick(final int position) {
        int flag = 0;
        if (topYear.isChecked()){
            //select title,iyear from t_course where iyear=2017 and classid=2
            flag = 0;
        }else if (topMorning.isChecked()){
            //上午题
            flag = 1;
        }else if (topAfter.isChecked()){
            //下午题案例分析
            flag = 2;
        }else if (topPoint.isChecked()){
            //知识点
            flag = 3;
        }
        if (flag==1) {
            newPage(position,1);
        }else if (flag==0){
            String iyear = mens.get(position).getId();
            presenter.requestQuestion(iyear);
        }else if (flag==3){
            //专业知识点 select * from t_question where courseid2=40;
//            String id = mens.get(position).getId();//courseid2参数
//            presenter.requestPointion(id);
            newPage(position,3);
        }else if (flag==2){
            //select * from t_course where classid=2 and itype=2 and ihalfday=0 and classid=2 and title="下午案例分析";
            //select * from t_question where courseid=98;查找某一年的三道案例题
            newPage(position,4);
        }else {

        }
    }

    private void newPage(final int position, final int type) {
        String content = "是否确认进入:" + mens.get(position).getTitle() + " 答题";
        commonDialog("提示", content, new DialogSelect() {
            @Override
            public void select(boolean isTrue) {
                if (isTrue) {
                    Log.e("data==", "====1");
                    Intent intent = new Intent(getContext(), AnswerActivity.class);
                    intent.putExtra("year", mens.get(position).getTitle());
                    intent.putExtra("key", type);
                    intent.putExtra("num", mens.get(position).getNumber());
                    intent.putExtra("courseid", mens.get(position).getId());
                    intent.putExtra("title", mens.get(position).getTitle());
                    startActivity(intent);
                    Log.e("data==", "===============" + mens.get(position).getId());
                } else {
                    Log.e("data==", "否");
                }
            }
        });
    }

    @Override
    public void loadDataSuccess(List<TCourse> data) {
        Log.e("QuestionBankFragment ","success"+data.size());

        int j = data.size();
        MenuInfo info;
        mens.clear();
        for (int i=0;i<j;i++){
                info = new MenuInfo();
                info.setId(data.get(i).getId()+"");
                info.setTitle(data.get(i).getIyear()+data.get(i).getTitle());
                if (data.get(i).getIhalfday()==1){
                    info.setNumber("75考题");
                }else {
                    info.setNumber("3考题");
                }
                mens.add(info);
        }
        Message message = Message.obtain();
        message.what = 1;
        handler.sendMessage(message);
    }



    @Override
    public void loadDataFaild() {
        Log.e("QuestionBankFragment ","fail");
    }

    class MyHandler extends Handler {
        WeakReference<QuestionBankFragment> fragment;

        public MyHandler(QuestionBankFragment fragment) {
            this.fragment = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Log.e("handleMessage","更新");
                    fragment.get().adapter.update(mens);
                    break;
                case 2:
                    mens = (List<MenuInfo>) msg.obj;
                    fragment.get().adapter.update(mens);
                    break;
                case 4:
                    mens = (List<MenuInfo>) msg.obj;
                    fragment.get().adapter.update(mens);
                    break;
            }
        }
    }

    //加载上午题数据
    private void tab2() {
        queListhalf(0);
    }

    private void queListhalf(final int index) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<MenuInfo> menuInfos = DBService.getInstance().getMorningQuestionList(index);
                Message message = Message.obtain();
                message.what = 2;
                message.obj = menuInfos;
                handler.sendMessage(message);
            }
        }).start();
    }

    //加载下午题数据
    private void tab3() {
        queListhalf(1);
    }

    //加载知识点数据
    private void tab4() {
        quePoint();
    }

    private void quePoint() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<MenuInfo> points = DBService.getInstance().getQuestionPoint();
                Message message = Message.obtain();
                message.what = 4;
                message.obj = points;
                handler.sendMessage(message);
            }
        }).start();
    }
}
