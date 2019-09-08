package com.example.administrator.myp2p.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myp2p.R;
import com.example.administrator.myp2p.activity.DayActivity;
import com.example.administrator.myp2p.adapter.MyRecommentAdapater;
import com.example.administrator.myp2p.bean.Recommend;
import com.example.administrator.myp2p.util.ScreenUtil;
import com.loopj.android.http.RequestParams;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.entity.LocalImageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.title_tv)
    TextView tvTitle;
    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.xbanner)
    XBanner xbanner;
    Unbinder unbinder;
    @BindView(R.id.lv_recommend)
    ListView lv_recommend;
    @BindView(R.id.my_collect)
    TextView myCollect;
    @BindView(R.id.my_error)
    TextView myError;
    @BindView(R.id.day_practice)
    TextView dayPractice;
    private MyRecommentAdapater adapter;
    private List<Recommend> list;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        adapter = new MyRecommentAdapater(list, getContext());
        lv_recommend.setAdapter(adapter);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtil.getScreenWidth(Objects.requireNonNull(getContext())) / 2);
        xbanner.setLayoutParams(layoutParams);
        initBanner(xbanner);
        initLocalImage();
//        xbanner.setBannerData(data);

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
        list = new ArrayList<>();
        list.add(new Recommend("第一章 第四节  IT服务、云计算", "36考题"));
        list.add(new Recommend("第一章 第三节  信息系统基础", "86考题"));
        list.add(new Recommend("第一章 第二节  计算机网络知识", "23考题"));
    }


    @Override
    public String getUrl() {
        return null;
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        unbinder = ButterKnife.bind(this, rootView);
//        return rootView;
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 加载本地图片
     */
    private void initLocalImage() {
        List<LocalImageInfo> data = new ArrayList<>();
        data.add(new LocalImageInfo(R.drawable.a1));
        data.add(new LocalImageInfo(R.drawable.a2));
        data.add(new LocalImageInfo(R.drawable.a3));
        xbanner.setBannerData(data);
        xbanner.setAutoPlayAble(true);
    }

    /**
     * 初始化XBanner
     */
    private void initBanner(XBanner banner) {
        //设置广告图片点击事件
        banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                Toast.makeText(getContext(), "点击了第" + (position + 1) + "图片", Toast.LENGTH_SHORT).show();
            }
        });
        //加载广告图片
        banner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {

//                加载本地图片展示
                ((ImageView) view).setImageResource(((LocalImageInfo) model).getXBannerUrl());
            }
        });
    }

    @OnClick({R.id.my_collect, R.id.my_error, R.id.day_practice})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.my_collect:
                Toast.makeText(getContext(), "我的收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_error:
                Toast.makeText(getContext(), "错题本", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day_practice:
                Toast.makeText(getContext(), "每日一练", Toast.LENGTH_SHORT).show();
                intent= new Intent(getContext(),DayActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
