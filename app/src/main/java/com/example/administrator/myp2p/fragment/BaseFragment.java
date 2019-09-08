package com.example.administrator.myp2p.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myp2p.imple.DialogSelect;
import com.loopj.android.http.RequestParams;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.administrator.myp2p.util.UIUtils.getXmlView;

public abstract  class BaseFragment extends Fragment {

    private Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

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

    /**
     * 普通对话框
     *
     * 对话框中的一些属性不一定是必须的，比如可以不设置标题、图标之类的，
     *
     * 也可以只设置一个按钮或者两个按钮，如常见的确认退出对话框
     */
    protected void commonDialog(String title,String content,final DialogSelect listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title);// 设置标题
        // builder.setIcon(R.drawable.ic_launcher);//设置图标
        builder.setMessage(content);// 为对话框设置内容
        // 为对话框设置取消按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                listener.select(false);
            }
        });

        // 为对话框设置确定按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                listener.select(true);
            }
        });
        builder.create().show();// 使用show()方法显示对话框
    }

}
