package com.example.administrator.myp2p.fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myp2p.R;
import com.example.administrator.myp2p.util.UIUtils;
import com.loopj.android.http.RequestParams;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TouZiFragment extends BaseFragment {

    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.tab_indictor)
    TabPageIndicator tabIndictor;
    @BindView(R.id.pager)
    ViewPager pager;
    Unbinder unbinder;
    private List<Fragment> fragmentlist = new ArrayList<>();
    @Override
    protected RequestParams getParams() {
        return null;
    }

    private void initFragment(){
        ProductHotFragment productHotFragment = new ProductHotFragment();
        ProductListFragment productListFragment = new ProductListFragment();
        ProductRecommendFragment productRecommendFragment = new ProductRecommendFragment();
        fragmentlist.add(productListFragment);
        fragmentlist.add(productRecommendFragment);
        fragmentlist.add(productHotFragment);

    }

    @Override
    protected void initData(String data) {
        initFragment();
        pager.setAdapter(new MyAdapter(getFragmentManager()));
        tabIndictor.setViewPager(pager);
    }

    @Override
    protected void initTitle() {
        titleTv.setText("我要投资");
        titleLeft.setVisibility(View.INVISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_touzi;
    }

    @Override
    public String getUrl() {
        return null;
    }
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentlist.get(position);
        }

        @Override
        public int getCount() {
            return fragmentlist == null ? 0 : fragmentlist.size();
        }

//        @Override
//        public CharSequence getPageTitle(int position) {
//            return UIUtils.getStringArr(R.array.touzi_tab)[position];
//        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return UIUtils.getStringarr(R.array.touzi_tab)[position];
        }
    }

}
