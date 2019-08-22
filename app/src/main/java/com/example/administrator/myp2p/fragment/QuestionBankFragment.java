package com.example.administrator.myp2p.fragment;
import android.support.v4.app.Fragment;

import android.widget.TextView;

import com.example.administrator.myp2p.R;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class QuestionBankFragment extends BaseFragment {
    @BindView(R.id.title_tv)
    TextView tvTitle;
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


}
