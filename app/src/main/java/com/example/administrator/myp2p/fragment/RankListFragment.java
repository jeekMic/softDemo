package com.example.administrator.myp2p.fragment;
import android.widget.TextView;

import com.example.administrator.myp2p.R;
import com.loopj.android.http.RequestParams;

import butterknife.BindView;


public class RankListFragment extends BaseFragment{
    @BindView(R.id.title_tv)
    TextView tvTitle;

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected void initData(String data) {

    }

    @Override
    protected void initTitle() {
        tvTitle.setText("排行榜");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_rank;
    }

    @Override
    public String getUrl() {
        return null;
    }
}
