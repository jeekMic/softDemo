package com.example.administrator.myp2p.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.administrator.myp2p.R;
import com.example.administrator.myp2p.bean.CollectQuestion;
import com.example.administrator.myp2p.bean.MenuInfo;
import com.example.administrator.myp2p.imple.OnRecycleItemClickCollect;
import com.example.administrator.myp2p.util.StringUtils;

import java.util.List;

public class DayPracticeRecycleViewAdpater extends RecyclerView.Adapter<DayPracticeRecycleViewAdpater.MyviewHolder> {
    private Context mContext;
    private List<CollectQuestion> data;
    private OnRecycleItemClickCollect listener;
    public void update(List<CollectQuestion> list){
        this.data = list;
        Log.e("数据===3","刷新"+data.size());
        notifyDataSetChanged();
    }
    public DayPracticeRecycleViewAdpater(Context mContext, List<CollectQuestion> data, OnRecycleItemClickCollect listener) {
        this.mContext = mContext;
        this.data = data;
        this.listener = listener;
    }

    @Override
    public DayPracticeRecycleViewAdpater.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycle_collect_item,parent,false);
        MyviewHolder myviewHolder = new MyviewHolder(view);
        return myviewHolder;
    }

    /**
     * 操作具体item的方法
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull DayPracticeRecycleViewAdpater.MyviewHolder holder, final int position) {
    CollectQuestion s = data.get(position);
    holder.tv_head.setText(StringUtils.getCourseById(Integer.parseInt(s.getCourseid2())));
    SetWebView(s.getTitle(),holder.wv_info);
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
        WebView wv_info;
        public MyviewHolder(View itemView) {
            super(itemView);
            tv_head = itemView.findViewById(R.id.tv_quest_head);
            wv_info = itemView.findViewById(R.id.wv_info);
        }
    }
    public void SetWebView(String mybody, WebView wv) {
        String head = "<head><style>* {font-size:15px}{color:#212121;}img{max-width: 100%; width:auto; height: auto;}</style></head>";
        String resultStr = "<html>" + head + "<body>" + mybody + "</body></html>";
        wv.loadDataWithBaseURL(null, resultStr, "text/html", "utf-8", null);
    }
}
