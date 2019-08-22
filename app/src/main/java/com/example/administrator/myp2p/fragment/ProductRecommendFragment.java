package com.example.administrator.myp2p.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myp2p.R;
import com.example.administrator.myp2p.ui.randomLayout.StellarMap;
import com.example.administrator.myp2p.util.UIUtils;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProductRecommendFragment extends Fragment {
    @BindView(R.id.StellarMap)
    StellarMap StellarMap;
    Unbinder unbinder;

    private Random random;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIUtils.getXmlView(R.layout.fragment_product_recommend);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        random = new Random();
        StellarMap.setAdapter(new MyAdapter());
        //每组数据的搭配规则
        StellarMap.setRegularity(7,9);
        //设置从那一组开始
        StellarMap.setGroup(0,true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class MyAdapter implements StellarMap.Adapter {
        @Override
        public int getGroupCount() {
            //两组数据
            return 2;
        }

        @Override
        public int getCount(int group) {
            //每一组对应多少个条目
            return 8;
        }

        @Override
        public View getView(int group, int position, View convertView) {
            TextView tv =new  TextView(getActivity());

            return tv;
        }
        //下一组将要出现的动画
        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            return 1;
        }
    }
}
