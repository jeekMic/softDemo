package com.example.administrator.myp2p;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.example.administrator.myp2p.fragment.QuestionBankFragment;
import com.example.administrator.myp2p.fragment.RankListFragment;
import com.example.administrator.myp2p.fragment.MeFragment;
import com.example.administrator.myp2p.util.UIUtils;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.content)
    FrameLayout content;

    @BindView(R.id.tv_home)
    TextView tvHome;

    @BindView(R.id.iv_quest_bank)
    ImageView ivQuestBank;
    @BindView(R.id.tv_quest_bank)
    TextView tvQuestBank;
    @BindView(R.id.ll_quest_bank)
    LinearLayout llQuestBank;

    @BindView(R.id.iv_rank_list)
    ImageView ivRankList;

    @BindView(R.id.tv_rank_list)
    TextView tvRankList;

    @BindView(R.id.ll_rank_list)
    LinearLayout llRankList;

    @BindView(R.id.iv_me)
    ImageView ivMe;
    @BindView(R.id.tv_me)
    TextView tvMe;
    @BindView(R.id.ll_me)
    LinearLayout llMe;
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.iv_home)
    ImageView ivHome;
    private HomeFragment homeFragment;
    private QuestionBankFragment mQuestionBankFragment;
    private RankListFragment mRankListFragment;
    private MeFragment mMeFragment;
    private FragmentTransaction ft;
    private MyHandler  handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //handler测试
        handler = new MyHandler(this);
        Message  message = Message.obtain();
        handler.sendMessageDelayed(message,10*60*1000);
        AppManager.getInstance().addActivity(this);
        initData();
    }

    private void initData() {
        setSelect(0);
    }

    @OnClick({R.id.ll_home, R.id.ll_quest_bank, R.id.ll_me, R.id.ll_rank_list})
    public void changeTab(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                setSelect(0);
                break;
            case R.id.ll_quest_bank:
                setSelect(1);
                break;
            case R.id.ll_rank_list:
                setSelect(2);
                break;
            case R.id.ll_me:
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
                ivHome.setImageResource(R.mipmap.icon05);
                tvHome.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                ft.show(homeFragment);

                break;
            case 1:
                if (mQuestionBankFragment == null) {
                    //首页
                    mQuestionBankFragment = new QuestionBankFragment();
                    /*相当于移除掉再添加上去，这样不好，下次加载页面需要重新加载数据
                     * 那么我们这里可以用hide() 下次在需要的时候使用show()显示出来就可以
                     * 添加时候使用add() 这里就只需要add一遍
                     */
                    ft.add(R.id.content, mQuestionBankFragment);
                }
                ft.show(mQuestionBankFragment);
                ivQuestBank.setImageResource(R.mipmap.icon06);
                tvQuestBank.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                break;
            case 2:
                if (mRankListFragment == null) {
                    //首页
                    mRankListFragment = new RankListFragment();
                    /*相当于移除掉再添加上去，这样不好，下次加载页面需要重新加载数据
                     * 那么我们这里可以用hide() 下次在需要的时候使用show()显示出来就可以
                     * 添加时候使用add() 这里就只需要add一遍
                     */
                    ft.add(R.id.content, mRankListFragment);
                }
                ft.show(mRankListFragment);
                ivRankList.setImageResource(R.mipmap.icon07);
                tvRankList.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                break;

            case 3:
                if (mMeFragment == null) {
                    //首页
                    mMeFragment = new MeFragment();
                    /*相当于移除掉再添加上去，这样不好，下次加载页面需要重新加载数据
                     * 那么我们这里可以用hide() 下次在需要的时候使用show()显示出来就可以
                     * 添加时候使用add() 这里就只需要add一遍
                     */
                    ft.add(R.id.content, mMeFragment);
                }
                ft.show(mMeFragment);
                ivMe.setImageResource(R.mipmap.icon08);
                tvMe.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                break;
        }
        ft.commit();
    }

    private void resetTab() {

        ivHome.setImageResource(R.mipmap.icon01);

        ivQuestBank.setImageResource(R.mipmap.icon02);

        ivRankList.setImageResource(R.mipmap.icon03);

        ivMe.setImageResource(R.mipmap.icon04);

        tvHome.setTextColor(UIUtils.getColor(R.color.home_back_unselected));

        tvQuestBank.setTextColor(UIUtils.getColor(R.color.home_back_unselected));

        tvMe.setTextColor(UIUtils.getColor(R.color.home_back_unselected));

        tvRankList.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
    }

    private void hideFragment() {
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (mQuestionBankFragment != null) {
            ft.hide(mQuestionBankFragment);
        }
        if (mRankListFragment != null) {
            ft.hide(mRankListFragment);
        }
        if (mMeFragment != null) {
            ft.hide(mMeFragment);
        }
    }

    private static class MyHandler extends Handler {
        private WeakReference<MainActivity> mActivity;
        public MyHandler(MainActivity activity){
            mActivity = new WeakReference<>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
}
