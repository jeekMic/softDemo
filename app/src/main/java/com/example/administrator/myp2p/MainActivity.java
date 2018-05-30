package com.example.administrator.myp2p;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myp2p.common.AppManager;
import com.example.administrator.myp2p.fragment.HomeFragment;
import com.example.administrator.myp2p.fragment.MeFragment;
import com.example.administrator.myp2p.fragment.MoreFragment;
import com.example.administrator.myp2p.fragment.TouZiFragment;
import com.example.administrator.myp2p.util.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.iv_touzi)
    ImageView ivTouzi;
    @BindView(R.id.tv_touzi)
    TextView tvTouzi;
    @BindView(R.id.ll_touzi)
    LinearLayout llTouzi;
    @BindView(R.id.iv_me)
    ImageView ivMe;
    @BindView(R.id.tv_me)
    TextView tvMe;
    @BindView(R.id.ll_me)
    LinearLayout llMe;
    @BindView(R.id.iv_more)
    ImageView ivMore;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.ll_more)
    LinearLayout llMore;
    @BindView(R.id.ll_home)
    LinearLayout ll_home;
    @BindView(R.id.iv_home)
    ImageView ivHome;
    private HomeFragment homeFragment;
    private TouZiFragment touziFragment;
    private MeFragment meFragment;
    private MoreFragment moreFragment;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);
        initData();
    }

    private void initData() {
        setSelect(0);
    }

    @OnClick({R.id.ll_home, R.id.ll_touzi, R.id.ll_me, R.id.ll_more})
    public void changeTab(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                setSelect(0);
                break;
            case R.id.ll_touzi:
                setSelect(1);
                break;
            case R.id.ll_me:
                setSelect(2);
                break;
            case R.id.ll_more:
                setSelect(3);
                break;
        }
    }

    public void setSelect(int select) {
        //定义一个碎片管理器
        FragmentManager fm = getSupportFragmentManager();
        //碎片事物
        ft = fm.beginTransaction();
        hideFragment();
        resetTab();
        switch (select) {
            case 0:
                if (homeFragment == null) {
                    //首页
                    homeFragment = new HomeFragment();
                    /*相当于移除掉再添加上去，这样不好，下次加载页面需要重新加载数据
                     * 那么我们这里可以用hide() 下次在需要的时候使用show()显示出来就可以
                     * 添加时候使用add() 这里就只需要add一遍
                     */
                    ft.add(R.id.content, homeFragment);
                }
                ivHome.setImageResource(R.mipmap.bid01);
                tvHome.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                ft.show(homeFragment);

                break;
            case 1:
                //投资
                if (touziFragment == null) {
                    //首页
                    touziFragment = new TouZiFragment();
                    /*相当于移除掉再添加上去，这样不好，下次加载页面需要重新加载数据
                     * 那么我们这里可以用hide() 下次在需要的时候使用show()显示出来就可以
                     * 添加时候使用add() 这里就只需要add一遍
                     */
                    ft.add(R.id.content, touziFragment);
                }
                ft.show(touziFragment);
                ivTouzi.setImageResource(R.mipmap.bid03);
                tvTouzi.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                break;
            case 2:
                //资产
                if (meFragment == null) {
                    //首页
                    meFragment = new MeFragment();
                    /*相当于移除掉再添加上去，这样不好，下次加载页面需要重新加载数据
                     * 那么我们这里可以用hide() 下次在需要的时候使用show()显示出来就可以
                     * 添加时候使用add() 这里就只需要add一遍
                     */
                    ft.add(R.id.content, meFragment);
                }
                ft.show(meFragment);
                ivMe.setImageResource(R.mipmap.bid05);
                tvMe.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                break;
            case 3:
                //更多
                if (moreFragment == null) {
                    //首页
                    moreFragment = new MoreFragment();
                    /*相当于移除掉再添加上去，这样不好，下次加载页面需要重新加载数据
                     * 那么我们这里可以用hide() 下次在需要的时候使用show()显示出来就可以
                     * 添加时候使用add() 这里就只需要add一遍
                     */
                    ft.add(R.id.content, moreFragment);
                }
                ft.show(moreFragment);
                ivMore.setImageResource(R.mipmap.bid07);
                tvMore.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                break;
        }
        ft.commit();
    }

    private void resetTab() {

        ivHome.setImageResource(R.mipmap.bid02);

        ivTouzi.setImageResource(R.mipmap.bid04);

        ivMe.setImageResource(R.mipmap.bid06);

        ivMore.setImageResource(R.mipmap.bid08);

        tvHome.setTextColor(UIUtils.getColor(R.color.home_back_unselected));

        tvTouzi.setTextColor(UIUtils.getColor(R.color.home_back_unselected));

        tvMe.setTextColor(UIUtils.getColor(R.color.home_back_unselected));

        tvMore.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
    }

    private void hideFragment() {
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (touziFragment != null) {
            ft.hide(touziFragment);
        }
        if (meFragment != null) {
            ft.hide(meFragment);
        }
        if (moreFragment != null) {
            ft.hide(moreFragment);
        }
    }
}
