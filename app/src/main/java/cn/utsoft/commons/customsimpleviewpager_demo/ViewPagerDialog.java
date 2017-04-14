package cn.utsoft.commons.customsimpleviewpager_demo;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 任新 on 2017/4/12 10:07.
 * Function:
 * Desc: 可滑动的dialog
 */
public class ViewPagerDialog implements ViewPager.OnPageChangeListener {


    private FrameLayout parent;
    private View rootView;

    private ViewPager contentViewPager;
    private LinearLayout pointsLinearLayout;


    private boolean ishowing = false;//当前是否正在显示
    private PagerAdapter pagerAdapter;
    private Map<Integer,ImageView> points = new HashMap<>();

    private static final int[] POINT_STATE = new int[]{
            R.drawable.ic_favorite_border_white_24dp,//未选中
            R.drawable.ic_favorite_red_a700_24dp//选中
    };

    public ViewPagerDialog(FrameLayout parent) {
        this.parent = parent;
        initView();
    }

    /**
     *
     * 这个初始化需要在 onCreate之后
     *
     * @param activity
     */
    public ViewPagerDialog(Activity activity){
        if(activity == null){
            return;
        }

        parent = (FrameLayout) activity.getWindow()
                .getDecorView()
                .findViewById(android.R.id.content);

        initView();
    }

    public boolean isShowing(){
        return ishowing;
    }

    private void initView() {
        if(parent == null){
            return;
        }
        Context context = parent.getContext();
        if(context == null){
            return;
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        rootView = inflater.inflate(R.layout.layout_viewpager_dialog,null);
        contentViewPager = (ViewPager) rootView.findViewById(R.id.view_viewpager_dialog_vp_content);
        pointsLinearLayout = (LinearLayout) rootView.findViewById(R.id.view_viewpager_dialog_ll_points);
    }


    public void show(final PagerAdapter pagerAdapter, final int currentPosition){
        if(pagerAdapter == null || pagerAdapter.getCount() == 0){
            return;
        }

        if(ishowing){
            return;
        }

        if(parent == null || rootView == null){
            return;
        }

        if(rootView.getParent() != null){
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            viewGroup.removeView(rootView);
        }

        /**
         *
         * 显示viewPager
         *
         */
        this.pagerAdapter = pagerAdapter;
        contentViewPager.setAdapter(pagerAdapter);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        params.gravity = Gravity.CENTER;
        initPointsView();
        contentViewPager.addOnPageChangeListener(this);
        /**
         * 设置切换效果
         *
         */
        contentViewPager.setPageTransformer(true,new ScalerPageTransformer());
        contentViewPager.setOffscreenPageLimit(pagerAdapter.getCount());

        rootView.setLayoutParams(params);
        parent.addView(rootView);
        ishowing = true;

        contentViewPager.post(new Runnable() {
            @Override
            public void run() {
                if(currentPosition == 0){
                    changeStateByPosition(0);
                    return;
                }
                if(currentPosition < pagerAdapter.getCount()){
                    contentViewPager.setCurrentItem(currentPosition);
                }
            }
        });

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // FIXME: 2017/4/11/011  默认点击空白处消失
                dismiss();
            }
        });
    }

    public void show(PagerAdapter pagerAdapter){
        show(pagerAdapter,0);
    }

    /**
     *
     * 需要消失的时候就调用这个方法
     *
     */
    public void dismiss(){

        if(!ishowing){
            return;
        }
        if(rootView == null){
            return;
        }
        if(rootView.getParent() == null){
            return;
        }
        ViewGroup viewGroup = (ViewGroup) rootView.getParent();
        viewGroup.removeView(rootView);

        ishowing = false;
    }

    private void initPointsView() {
        points.clear();
        if(pagerAdapter == null || pagerAdapter.getCount() == 0){
            return;
        }

        if(parent == null || parent.getContext() == null){
            return;
        }

        pointsLinearLayout.removeAllViews();

        for(int i=0;i < pagerAdapter.getCount();i++){
            ImageView imageView = createImageView();
            /**
             * add to linearLayout
             */
            pointsLinearLayout.addView(imageView);
            points.put(i,imageView);
        }
    }

    private ImageView createImageView() {
        ImageView imageView = new ImageView(parent.getContext());
        /**
         *
         * 父布局的LayoutParams
         *
         */
        // FIXME: 2017/4/11/011 这个就根据指示器的高度来设置把  这里设置的 8 * 8
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                30,
                20
        );
        params.setMargins(8,8,8,8);
        imageView.setLayoutParams(params);
        return imageView;
    }

    /**
     *
     * 改变指示器的位置
     *
     */
    private void changeStateByPosition(int position){
        if(points == null){
            return;
        }
        Iterator<Map.Entry<Integer, ImageView>> iterator = points.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer, ImageView> next = iterator.next();
            next.getValue().setImageResource(
                    POINT_STATE[next.getKey() == position ? 1 : 0]
            );
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
    @Override
    public void onPageSelected(int position) {
        changeStateByPosition(position);
    }
    @Override
    public void onPageScrollStateChanged(int state) {}
}

