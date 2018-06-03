package com.example.administrator.myp2p.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.administrator.myp2p.R;
import com.example.administrator.myp2p.util.UIUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public abstract class LoadingPage extends FrameLayout {
    public   Context mContext;
    AsyncHttpClient client = new AsyncHttpClient();
    public static final int PAGE_LOADING_STATE = 1;
    public static final int PAGE_ERROR_STATE = 2;
    public static final int PAGE_EMPTY_STATE = 3;
    public static final int PAGE_SUCESS_STATE = 4;
    private int PAGE_CURRENT_STATE = 1;

    private View loadingView;
    private View errorView;
    private View emptyView;
    private View sucessView;
    private LayoutParams lp;
    private ResultState resultState = null;
    public LoadingPage(@NonNull Context context) {

        this(context,null);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);

    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();


    }

    private void init() {

            lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if (loadingView == null) {
                loadingView = UIUtils.getXmlView(R.layout.page_loading);
                addView(loadingView,lp);
            }
            if(errorView==null){
                errorView = UIUtils.getXmlView(R.layout.page_error);
                addView(errorView,lp);
            }
            if (emptyView==null){
                emptyView = UIUtils.getXmlView(R.layout.page_empty);
                addView(emptyView,lp);
            }

            showSafePage();

    }

    private void showSafePage() {
        UIUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                showpage();
            }
        });

    }

    private void showpage() {
                loadingView.setVisibility(PAGE_CURRENT_STATE==PAGE_LOADING_STATE?View.VISIBLE:View.GONE);
                errorView.setVisibility(PAGE_CURRENT_STATE==PAGE_ERROR_STATE?View.VISIBLE:View.GONE);
                emptyView.setVisibility(PAGE_CURRENT_STATE==PAGE_EMPTY_STATE?View.VISIBLE:View.GONE);
                if (sucessView==null){
                //成功的时候加载home_fregment
                    if (mContext==null){
                        Log.i("hahah","--------showpage");
                    }
                sucessView = View.inflate(mContext,LayoutID(),null);
                addView(sucessView,lp);
                }
                sucessView.setVisibility(PAGE_CURRENT_STATE==PAGE_SUCESS_STATE?View.VISIBLE:View.GONE);
    }

    public abstract int LayoutID();
    public void show(){
        //再一次请求的时候要归位
        if (PAGE_CURRENT_STATE!=PAGE_LOADING_STATE){
            PAGE_CURRENT_STATE = PAGE_LOADING_STATE;
        }
        //判断是否需要发送网络请求
        String url = url();
        if (TextUtils.isEmpty(url)){
            resultState = ResultState.SUCCESS;
            resultState.setContent("");
            loadPage();

        }else {
            client.get(url(), params(), new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(String content) {
                    if (TextUtils.isEmpty(content)) {
                        resultState = ResultState.EMPTY;
                        resultState.setContent("");
                    } else {
                        resultState = ResultState.SUCCESS;
                        resultState.setContent(content);
                    }
                    loadPage();
                }

                @Override
                public void onFailure(Throwable error, String content) {
                    resultState = ResultState.ERROR;
                    resultState.setContent("");
                    loadPage();
                }
            });
        }
    }

    protected  void loadPage(){
        switch (resultState){
            case SUCCESS:
                //访问成功，显示成功界面
                PAGE_CURRENT_STATE = PAGE_SUCESS_STATE;
                break;
            case ERROR:
                //访问成功，显示成功界面
                PAGE_CURRENT_STATE = PAGE_ERROR_STATE;
                break;
            case EMPTY:
                //访问成功，显示成功界面
                PAGE_CURRENT_STATE = PAGE_EMPTY_STATE;
                break;
        }
        showSafePage();
        if (PAGE_CURRENT_STATE==PAGE_SUCESS_STATE){
            OnSuccess(resultState,sucessView);
        }
    }

    protected abstract void OnSuccess(ResultState resultState,View successView);

    protected abstract RequestParams params();

    protected abstract String url();

    public enum ResultState{
        ERROR(2),EMPTY(2),SUCCESS(4);
        private int state;
        private String content;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        ResultState(int i) {
            this.state = i;
        }
    }
}
