//package com.example.administrator.myp2p.adapter;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//
//import com.example.administrator.myp2p.R;
//
//public class HotFgListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//    private List<HotListDataBean> mDatas;
//    private LayoutInflater mInflater;
//    private OnItemClickListener onItemClickListener;
//    private final int NO_DATA = 0, IMAGE_VIEW = 1, TEXT_VIEW = 2;//无数据，图布局，字布局
//
//    public HotFgListAdapter(List<HotListDataBean> mDatas) {
//        this.mDatas = mDatas;
//    }
//
//    /**
//     * 确定使用哪一种item
//     * @param position
//     * @return
//     */
//    @Override
//    public int getItemViewType(int position) {
//        if(mDatas.size() <= 0){ //无数据情况处理
//            return NO_DATA;
//        }
//        if(position % 2 == 0){
//            return IMAGE_VIEW;
//        }
//        return TEXT_VIEW;
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        mInflater = LayoutInflater.from(parent.getContext());
//        RecyclerView.ViewHolder mHolder = null;
//        switch (viewType){
//            case NO_DATA:
//                mHolder = new ImageViewHolder(mInflater.inflate(R.layout.item_no_data, parent, false));
//                break;
//            case IMAGE_VIEW:
//                mHolder = new ImageViewHolder(mInflater.inflate(R.layout.item_hot_fg_list, parent, false));
//                break;
//            case TEXT_VIEW:
//                mHolder = new TextViewHolder(mInflater.inflate(R.layout.item_hot_fg_list2, parent, false));
//                break;
//        }
//        return mHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//        if(mDatas.size() <= 0){ //无数据的情况
//            return;
//        }
//        switch (getItemViewType(position)){
//            case IMAGE_VIEW:
//                ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
//                imageViewHolder.mIvIcon.setImageResource(mDatas.get(position).getIcon());
//                imageViewHolder.mIvIcon2.setImageResource(mDatas.get(position).getIcon());
//                imageViewHolder.mIvIcon.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if(onItemClickListener != null){
//                            onItemClickListener.OnItemClick(v, position);
//                        }
//                    }
//                });
//                break;
//            case TEXT_VIEW:
//                TextViewHolder textViewHolder = (TextViewHolder) holder;
//                textViewHolder.mTvTitle.setText(mDatas.get(position).getTitle());
//                textViewHolder.mTvTitle2.setText(mDatas.get(position).getTitle());
//                break;
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return mDatas.size()>0 ? mDatas.size() : 1; //这里在数据为空的情况下返回1，为了显示无数据的布局
//    }
//
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }
//
//    /**
//     * 图片item的holder
//     */
//    class ImageViewHolder extends RecyclerView.ViewHolder{
//
//        private ImageView mIvIcon, mIvIcon2;
//        public ImageViewHolder(View itemView) {
//            super(itemView);
//            mIvIcon = (ImageView) itemView.findViewById(R.id.hot_fg_item_icon);
//            mIvIcon2 = (ImageView) itemView.findViewById(R.id.hot_fg_item_icon2);
//        }
//    }
//
//    /**
//     * 文字item的holder
//     */
//    class TextViewHolder extends RecyclerView.ViewHolder{
//
//        private TextView mTvTitle, mTvTitle2;
//        public TextViewHolder(View itemView) {
//            super(itemView);
//            mTvTitle = (TextView) itemView.findViewById(R.id.hot_fg_item_tv);
//            mTvTitle2 = (TextView) itemView.findViewById(R.id.hot_fg_item_tv2);
//        }
//    }
//
//    /**
//     * 适配器的点击事件接口
//     */
//    public interface OnItemClickListener{
//        void OnItemClick(View v, int position);
//    }
//}
//
