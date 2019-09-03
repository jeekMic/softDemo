package com.example.administrator.myp2p.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myp2p.R;
import com.example.administrator.myp2p.adapter.QuestionBankRecycleViewAdpater;
import com.example.administrator.myp2p.bean.MenuInfo;
import com.example.administrator.myp2p.bean.QuestionPoint;
import com.example.administrator.myp2p.service.DBService;
import com.loopj.android.http.RequestParams;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class QuestionBankFragment extends BaseFragment {
    @BindView(R.id.title_tv)
    TextView tvTitle;
    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.top_year)
    TextView topYear;
    @BindView(R.id.top_morning)
    TextView topMorning;
    @BindView(R.id.top_after)
    TextView topAfter;
    @BindView(R.id.top_point)
    TextView topPoint;
    @BindView(R.id.question_bank_recycle)
    RecyclerView questionBankRecycle;
    private List<String> mDatas;
    private List<MenuInfo> mens;
    private QuestionBankRecycleViewAdpater adapter;
    Unbinder unbinder;
    MenuInfo info;
    private MyHandler handler;
    private List<Fragment> fragmentlist = new ArrayList<>();

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mens = new ArrayList<>();
        adapter = new QuestionBankRecycleViewAdpater(getContext(),mens);
        handler = new MyHandler(this);
        questionBankRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        questionBankRecycle.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        tab1();
    }

    @OnClick({R.id.top_year, R.id.top_morning, R.id.top_after, R.id.top_point})
    public void onClick(View view) {
        switch (view.getId()) {
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

    //加载年份数据
    private void tab1() {
        topYear.setTextColor(getResources().getColor(R.color.colorAccent));
        topMorning.setTextColor(getResources().getColor(R.color.gray));
        topAfter.setTextColor(getResources().getColor(R.color.gray));
        topPoint.setTextColor(getResources().getColor(R.color.gray));
        queList();
    }

    private void queList() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> years = DBService.getInstance().getYears();
                for (int i=0;i<years.size();i++){
                    info = new MenuInfo();
                    info.setTitle(years.get(i)+" 年信息系统项目师真题(高级)");
                    Log.e("数据===",years.get(i)+"");
                    info.setNumber("点击进入");
                    mens.add(info);
                }
                Message message = Message.obtain();
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();
    }

    class MyHandler extends Handler{
        WeakReference<QuestionBankFragment> fragment;

        public MyHandler(QuestionBankFragment fragment) {
            this.fragment = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    fragment.get().adapter.update(mens);
                    break;
                case 2:
                    List<MenuInfo> menuInfos =  (List<MenuInfo>)msg.obj;
                    fragment.get().adapter.update(menuInfos);
                    break;
                case 4:
                    List<MenuInfo> points = (List<MenuInfo>) msg.obj;
                    fragment.get().adapter.update(points);
                    break;
            }
        }
    }
    //加载上午题数据
    private void tab2() {
        topYear.setTextColor(getResources().getColor(R.color.gray));
        topMorning.setTextColor(getResources().getColor(R.color.colorAccent));
        topAfter.setTextColor(getResources().getColor(R.color.gray));
        topPoint.setTextColor(getResources().getColor(R.color.gray));
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
        topYear.setTextColor(getResources().getColor(R.color.gray));
        topMorning.setTextColor(getResources().getColor(R.color.gray));
        topAfter.setTextColor(getResources().getColor(R.color.colorAccent));
        topPoint.setTextColor(getResources().getColor(R.color.gray));
        queListhalf(1);
    }

    //加载知识点数据
    private void tab4() {
        topYear.setTextColor(getResources().getColor(R.color.gray));
        topMorning.setTextColor(getResources().getColor(R.color.gray));
        topAfter.setTextColor(getResources().getColor(R.color.gray));
        topPoint.setTextColor(getResources().getColor(R.color.colorAccent));
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
