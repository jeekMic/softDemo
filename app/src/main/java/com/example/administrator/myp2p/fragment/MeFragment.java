package com.example.administrator.myp2p.fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myp2p.R;
import com.loopj.android.http.RequestParams;

import static com.example.administrator.myp2p.util.UIUtils.getXmlView;

public class MeFragment  extends BaseFragment{


    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected void initData(String data) {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public String getUrl() {
        return null;
    }
}
