package com.example.administrator.myp2p.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myp2p.R;
import com.example.administrator.myp2p.bean.Index;
import com.example.administrator.myp2p.common.AppNetConfig;
import com.example.administrator.myp2p.ui.RoundProgress;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.administrator.myp2p.util.UIUtils.getXmlView;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.title_tv)
    TextView tvTitle;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

    @Override
    protected void initData(String content) {

    }

    @Override
    protected void initTitle() {
        tvTitle.setText("首页");
    }


    @Override
    public String getUrl() {
        return AppNetConfig.INDEX;
    }

}
