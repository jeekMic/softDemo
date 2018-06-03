package com.example.administrator.myp2p.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myp2p.R;
import com.example.administrator.myp2p.bean.Index;
import com.example.administrator.myp2p.common.AppNetConfig;
import com.example.administrator.myp2p.ui.RoundProgress;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.administrator.myp2p.util.UIUtils.getXmlView;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.vp_barner)
    ViewPager vpBarner;
    @BindView(R.id.circle_barner)
    CirclePageIndicator circleBarner;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.p_progresss)
    RoundProgress pProgresss;
    @BindView(R.id.p_yearlv)
    TextView pYearlv;
    @BindView(R.id.button1)
    Button button1;

    private Index index;
    private int totalprogress=0;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

    @Override
    protected void initData(String content) {
        if (!TextUtils.isEmpty(content)){
            Gson gson = new Gson();
            index = gson.fromJson(content, Index.class);
            //适配数据
            vpBarner.setAdapter(new MyAdapter());
            //viewpager交给指示器
            circleBarner.setViewPager(vpBarner);
            totalprogress = Integer.parseInt(index.getProInfo().getProgress());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int tempProgress = 0;
                    while(tempProgress<totalprogress){
                        pProgresss.setProgress(tempProgress);
                        tempProgress++;
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.i("progress",""+tempProgress);
                    }
                }
            }).start();

        }
    }

    @Override
    protected void initTitle() {
        titleLeft.setVisibility(View.INVISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
    }


    @Override
    public String getUrl() {
        return AppNetConfig.INDEX;
    }

    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return index.getImageArr() == null ? 0 : index.getImageArr().size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            String imageurl = index.getImageArr().get(position).getIMAURL();
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.addView(imageView);
            Picasso.get().load(imageurl).into(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
