package com.example.administrator.myp2p.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myp2p.BaseActivity;
import com.example.administrator.myp2p.MyApplication;
import com.example.administrator.myp2p.R;
import com.example.administrator.myp2p.adapter.ErrorRecycleViewAdpater;
import com.example.administrator.myp2p.bean.ErrorQuestion;
import com.example.administrator.myp2p.gbean.ErrorQuestionDao;
import com.example.administrator.myp2p.gbean.MenuInfoDao;
import com.example.administrator.myp2p.imple.OnRecycleItemClickCollect;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyErrorActivity extends BaseActivity implements OnRecycleItemClickCollect {
    @BindView(R.id.title_left)
    ImageView titleLeft;

    @BindView(R.id.rv_collect)
    RecyclerView rvCollect;
    @BindView(R.id.title_tv)
    TextView titleTv;
    //    private List<MenuInfo> mens;
    private MyHandler handler;
    private MenuInfoDao menuInfo_dao;
    private ErrorRecycleViewAdpater adapter;
    private ErrorQuestionDao error_dao;
    private List<ErrorQuestion> errorQuestions;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_error;
    }
    @Override
    public void initView() {
        titleTv.setText("我的错题本");
        errorQuestions = new ArrayList<>();
        menuInfo_dao = MyApplication.getDaoSession().getMenuInfoDao();
        error_dao = MyApplication.getDaoSession().getErrorQuestionDao();
    }

    @Override
    public void initData() {
        handler = new MyHandler(MyErrorActivity.this);
        adapter = new ErrorRecycleViewAdpater(this, errorQuestions, this);
        rvCollect.setLayoutManager(new LinearLayoutManager(this));
        rvCollect.setAdapter(adapter);
        errorQuestions = error_dao.loadAll();
        adapter.update(errorQuestions);
//        if (UIUtils.the_same_week){
        //如果是同一个星期
//            mens.clear();
//            mens = menuInfo_dao.queryBuilder().where(MenuInfoDao.Properties.Page.ge(4)).where(MenuInfoDao.Properties.Page.le(4)).list();

//            Message message = Message.obtain();
//            message.what = 4;
//            handler.sendMessage(message);
//            return;
//        }else {
//            Log.e("same no::"," "+mens.size());
//            quePoint();
//        }

    }

    @Override
    public void onItemClick(final int position) {
        builder2.setMessage("查看或者删除？");
        builder2.setIcon(R.mipmap.remind);
        builder2.setTitle("提示");
        builder2.setPositiveButton("查看", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MyErrorActivity.this, DetailActivity.class);
                intent.putExtra("object",errorQuestions.get(position));
                intent.putExtra("flagId",1);
                startActivity(intent);
            }
        });
        builder2.setNegativeButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.e("data====",""+i);
                error_dao.delete(errorQuestions.get(position));
                errorQuestions.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        builder2.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder2.create();
        builder2.show();

    }

    @Override
    protected void negativeClick() {
        super.negativeClick();
        toast("删除");
    }

    @Override
    protected void sureClick() {
        super.sureClick();
        toast("查看");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
//    private void quePoint() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                mens = DBService.getInstance().getQuestionPoint();
//                Message message = Message.obtain();
//                message.what = 4;
//                handler.sendMessage(message);
//            }
//        }).start();
//
//    public void notifyAdapter(){
//        adapter.update(mens);
//    }

    class MyHandler extends Handler {
        WeakReference<MyErrorActivity> activity;

        public MyHandler(MyErrorActivity activity) {
            this.activity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 4:
//                    activity.get().notifyAdapter();
                    break;
            }
        }
    }
}
