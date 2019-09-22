package com.example.administrator.myp2p.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myp2p.R;
import com.example.administrator.myp2p.bean.Question;
import com.example.administrator.myp2p.bean.ResultQuestion;
import com.example.administrator.myp2p.ui.RoundProgress;
import com.example.administrator.myp2p.util.StringUtils;
import com.example.administrator.myp2p.widget.MyViewPager;

import java.util.List;

public class DataAnalysisRecycleAdapter extends RecyclerView.Adapter<DataAnalysisRecycleAdapter.MyviewHolder>{
    private Context context;
    protected List<ResultQuestion> questions;

    public DataAnalysisRecycleAdapter(Context context, List<ResultQuestion> question) {
        this.context = context;
        this.questions = question;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(context).inflate(R.layout.item_product_list,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
    ResultQuestion question = questions.get(position);
    holder.p_name.setText(StringUtils.getCourseById(Integer.parseInt(question.getCourseId())));
    holder.tv_all_question.setText(question.all+"");
    int persent = (int) (question.corrent*100.0f/question.all);
    holder.tv_persent.setText(persent+"");
    holder.p_yes_num.setText(question.corrent+"");
    holder.p_progresss.setProgress(persent);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class MyviewHolder extends RecyclerView.ViewHolder{
        TextView p_name;
        TextView tv_all_question;
        TextView tv_persent;
        TextView p_yes_num;
        RoundProgress p_progresss;
        public MyviewHolder(View itemView) {
            super(itemView);
            p_name = itemView.findViewById(R.id.p_name);
            tv_all_question = itemView.findViewById(R.id.tv_all_question);
            tv_persent = itemView.findViewById(R.id.tv_persent);
            p_yes_num = itemView.findViewById(R.id.p_yes_num);
            p_progresss = itemView.findViewById(R.id.p_progresss);
        }
    }
}
