package com.example.administrator.myp2p.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myp2p.R;
import com.example.administrator.myp2p.ui.LoadingPage;
import com.example.administrator.myp2p.util.UIUtils;
import com.loopj.android.http.RequestParams;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.administrator.myp2p.util.UIUtils.getXmlView;

public abstract  class BaseFragment extends Fragment {
    private Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = getXmlView(getLayoutId());
        unbinder = ButterKnife.bind(this,view);
        initTitle();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initEvent();

    }

    protected void initEvent() {
    }

    protected abstract RequestParams getParams();

    protected abstract void initData(String data);

    protected abstract void initTitle();

    public abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    public abstract String getUrl();
    
}
