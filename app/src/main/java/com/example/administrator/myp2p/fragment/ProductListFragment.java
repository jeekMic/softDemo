package com.example.administrator.myp2p.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myp2p.R;
import com.example.administrator.myp2p.adapter.MySimpleBaseAdapter;
import com.example.administrator.myp2p.bean.Product;
import com.example.administrator.myp2p.common.AppNetConfig;
import com.example.administrator.myp2p.ui.RoundProgress;
import com.example.administrator.myp2p.util.UIUtils;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProductListFragment extends Fragment {
    @BindView(R.id.lv)
    ListView lv;
    Unbinder unbinder;
    private AsyncHttpClient client = new AsyncHttpClient();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIUtils.getXmlView(R.layout.fragment_product_list);
        unbinder = ButterKnife.bind(this, view);
        initTitle();
        initData();
        return view;
    }

    private void initData() {
        client.post(AppNetConfig.PRODUCT, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                Gson gson = new Gson();
                Product product = gson.fromJson(content, Product.class);
                if (product.isSuccess()) {
                    lv.setAdapter(new MyAdapter2(product.getData()));
                }
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
            }
        });
    }

    private void initTitle() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class MyDataAdapter extends BaseAdapter {
        private final List<Product.DataBean> products;

        public MyDataAdapter(List<Product.DataBean> list) {
            this.products = list;
        }

        @Override
        public int getCount() {
            return products == null ? 0 : products.size();
        }

        @Override
        public Object getItem(int position) {
            return products.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                Product.DataBean dataBean = products.get(position);
                ViewHolder viewHolder = null;
                if (convertView==null){
                    convertView = View.inflate(parent.getContext(), R.layout.item_product_list, null);
                    viewHolder = new ViewHolder(convertView);
                    convertView.setTag(viewHolder);
                }else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                //设置数据
                viewHolder.pMoney.setText(dataBean.getMoney());
                viewHolder.pMinzouzi.setText(dataBean.getMinTouMoney());
                viewHolder.pName.setText(dataBean.getName());
                viewHolder.pSuodingdays.setText(dataBean.getSuodingDays());
                viewHolder.pYearlv.setText(dataBean.getYearLv());
                viewHolder.pProgresss.setProgress(Integer.parseInt(dataBean.getProgress()));


                return convertView;



        }
    }
    static class ViewHolder {
        @BindView(R.id.p_name)
        TextView pName;
        @BindView(R.id.p_money)
        TextView pMoney;
        @BindView(R.id.p_yearlv)
        TextView pYearlv;
        @BindView(R.id.p_suodingdays)
        TextView pSuodingdays;
        @BindView(R.id.p_minzouzi)
        TextView pMinzouzi;
        @BindView(R.id.p_progresss)
        RoundProgress pProgresss;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private class MyAdapter2 extends MySimpleBaseAdapter<Product.DataBean>{

        public MyAdapter2(List list) {
            super(list);
        }

        @Override
        public View getYourView(int position, View convertView, ViewGroup parent) {
            Product.DataBean dataBean = list.get(position);
            ViewHolder viewHolder = null;
            if (convertView==null){
                convertView = View.inflate(parent.getContext(), R.layout.item_product_list, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //设置数据
            viewHolder.pMoney.setText(dataBean.getMoney());
            viewHolder.pMinzouzi.setText(dataBean.getMinTouMoney());
            viewHolder.pName.setText(dataBean.getName());
            viewHolder.pSuodingdays.setText(dataBean.getSuodingDays());
            viewHolder.pYearlv.setText(dataBean.getYearLv());
            viewHolder.pProgresss.setProgress(Integer.parseInt(dataBean.getProgress()));


            return convertView;

        }

    }
}
