package cn.utsoft.commons.customsimpleviewpager_demo.model.switchlist.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import cn.utsoft.commons.customsimpleviewpager_demo.R;

/**
 * Create by 任新 on 2017/3/16 11:50
 * Function：
 * Desc：首页精品推荐适配器
 */
public class RecommendedRecyclerAdapter extends RecyclerView.Adapter{

    public static final int TYPE_ONE = 0x0011; //精品推荐
    public static final int TYPE_TWO = 0x0012; //图片
    public static final int TYPE_THREE = 0x0013; //查看全部

    private List<String> data;

    List<Object> allData;
    Object obj1 = new Object();
    Object obj2 = new Object();


    private Context mContext;
    private OnItemClickListener mListener;

    public RecommendedRecyclerAdapter(List<String> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
        allData = new ArrayList<>();
        initData();
    }

    /**
     *
     * 注册观察者
     *  这个方法需要在 setAdapter之后初始化一次
     */
    public void initObserble(){
        registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                initData();
            }
        });
    }

    /**
     *
     * 每次数据改变的时候都需要调用这个方法
     *
     */
    private void initData(){
        allData.clear();
        allData.add(obj1);
        allData.addAll(data);
        allData.add(obj2);
    }


    @Override
    public int getItemViewType(int position) {
        Object object = allData.get(position);
        if (object == obj1 ){
            return TYPE_ONE;
        }


        if (object instanceof String){
            return TYPE_TWO;
        }
        if (object == obj2){
            return TYPE_THREE;
        }

        return TYPE_TWO;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater  = LayoutInflater.from(parent.getContext());

        if (viewType == TYPE_ONE){
            return new RecommendOneViewHolder(inflater.inflate(R.layout.item_home_recommend_1,null));
        }

        if(viewType == TYPE_TWO){
            return new RecommendTwoViewHolder(inflater.inflate(R.layout.item_home_recommend,null));
        }

        if(viewType == TYPE_THREE){
            return new RecommendThreeViewHolder(inflater.inflate(R.layout.item_home_recommend_1,null));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof RecommendOneViewHolder){}
        if (holder instanceof RecommendTwoViewHolder){
            RecommendTwoViewHolder twoViewHolder = (RecommendTwoViewHolder) holder;
            twoViewHolder.setData(position);
        }
        if (holder instanceof RecommendThreeViewHolder){
            RecommendThreeViewHolder threeViewHolder = (RecommendThreeViewHolder) holder;
            threeViewHolder.setData();
        }
    }

    @Override
    public int getItemCount() {
        return allData.size();
    }

    class RecommendOneViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout mLl;
        private TextView mTv;
        public RecommendOneViewHolder(View itemView) {
            super(itemView);
            mLl = (LinearLayout) itemView.findViewById(R.id.item_ll_recommend);
            mTv = (TextView) itemView.findViewById(R.id.item_tv_all);
            mTv.setText("汽车推荐");
            mLl.setBackgroundResource(R.drawable.test_bg_recommend_1);
        }
    }

    class RecommendTwoViewHolder extends RecyclerView.ViewHolder{
        private ImageView mIv;
        public RecommendTwoViewHolder(View itemView) {
            super(itemView);
            mIv = (ImageView) itemView.findViewById(R.id.item_img);
            ViewGroup.LayoutParams params = mIv.getLayoutParams();
            //设置图片的相对于屏幕的宽高比
            params.height = 300;
            mIv.setLayoutParams(params);
        }

        public void setData(int position){

        }
    }

    class RecommendThreeViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout mLl;
        private TextView mTv;
        public RecommendThreeViewHolder(View itemView) {
            super(itemView);
            mLl = (LinearLayout) itemView.findViewById(R.id.item_ll_recommend);
            mTv = (TextView) itemView.findViewById(R.id.item_tv_all);
            mLl.setBackgroundResource(R.drawable.test_bg_recommend);
            mTv.setText("查看全部");
            mTv.setTextColor(Color.rgb(255, 86, 128));
        }

        public void setData(){
            mLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mListener.onItemClick(v,0);
                }
            });
        }
    }


    public void setOnItemClick(OnItemClickListener listener){
        mListener = listener;
    }
}
