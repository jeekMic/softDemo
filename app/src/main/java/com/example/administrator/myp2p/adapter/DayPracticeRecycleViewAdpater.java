package com.example.administrator.myp2p.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myp2p.R;
import com.example.administrator.myp2p.bean.MenuInfo;
import com.example.administrator.myp2p.imple.OnRecycleItemClick;

import java.util.List;

public class QuestionBankRecycleViewAdpater extends RecyclerView.Adapter<QuestionBankRecycleViewAdpater.MyviewHolder> {
    private Context mContext;
    private List<MenuInfo> data;
    private OnRecycleItemClick listener;
    public void update(List<MenuInfo> list){
        Log.e("数据===2","刷新"+list.size());
        this.data = list;
        Log.e("数据===3","刷新"+data.size());
        notifyDataSetChanged();
    }
    public QuestionBankRecycleViewAdpater(Context mContext, List<MenuInfo> data,OnRecycleItemClick listener) {
        this.mContext = mContext;
        this.data = data;
        this.listener = listener;
    }

    @Override
    public QuestionBankRecycleViewAdpater.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycle_item,parent,false);
        MyviewHolder myviewHolder = new MyviewHolder(view);
        return myviewHolder;
    }

    /**
     * 操作具体item的方法
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull QuestionBankRecycleViewAdpater.MyviewHolder holder, final int position) {
    MenuInfo s = data.get(position);
    holder.tv_head.setText(s.getTitle());
    holder.tv_number.setText(s.getNumber());
    holder.tv_head.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            listener.onItemClick(position);
        }
    });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyviewHolder extends RecyclerView.ViewHolder{
        TextView tv_head;
        TextView tv_number;
        public MyviewHolder(View itemView) {
            super(itemView);
            tv_head = itemView.findViewById(R.id.tv_quest_head);
            tv_number = itemView.findViewById(R.id.tv_info);
        }
    }
}
