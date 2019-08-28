package com.example.administrator.myp2p.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.myp2p.BaseActivity;
import com.example.administrator.myp2p.R;
import com.example.administrator.myp2p.bean.Recommend;

import java.util.List;
import java.util.zip.Inflater;

public class MyRecommentAdapater extends BaseAdapter {
    private Context context;
    private List<Recommend> recommend_list;
    public MyRecommentAdapater(List list,Context context) {
        this.context = context;
        recommend_list = list;
    }
    @Override
    public int getCount() {
        return recommend_list==null?0:recommend_list.size();
    }

    @Override
    public Object getItem(int position) {
        return recommend_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View view;
        if (convertView==null){
            view = LayoutInflater.from(context).inflate(R.layout.home_recomment_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tv_chapter = view.findViewById(R.id.tv_chapter);
            viewHolder.tv_number_ti = view.findViewById(R.id.tv_number_ti);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv_chapter.setText(recommend_list.get(position).getTitle());
        viewHolder.tv_number_ti.setText(recommend_list.get(position).getNumber_ti());
        return view;
    }

    static class ViewHolder{
        TextView tv_chapter;
        TextView tv_number_ti;
    }
}
