package cn.utsoft.commons.customsimpleviewpager_demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.marno.easyutilcode.ScreenUtil;
import com.marno.rapidlib.basic.BasicActivity;
import com.marno.rapidlib.manager.GlideManager;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.utsoft.commons.customsimpleviewpager_demo.model.guide.adapter.UltraPagerAdapter;
import cn.utsoft.commons.customsimpleviewpager_demo.model.switchlist.adapter.RecommendedRecyclerAdapter;
import cn.utsoft.commons.customsimpleviewpager_demo.utils.GlideCircleTransform;
import cn.utsoft.commons.customsimpleviewpager_demo.widght.HeapFrameLayout;
import cn.utsoft.commons.customsimpleviewpager_demo.widght.TitleBarView;
import cn.utsoft.commons.customsimpleviewpager_demo.widght.ViewPagerDialog;


public class MainActivity extends BasicActivity {

    private ViewPagerDialog dialog;
    private UltraPagerAdapter adapter;
    private HeapFrameLayout mHfLayout;
    private TitleBarView mTitleBar;
    private BGABanner mBanner;
    private RecyclerView mRecycler;

    private RecommendedRecyclerAdapter mAdapter;

    private ArrayList<String> mListBanner = new ArrayList<>();
    private ArrayList<String> mListRecycler = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle bundle) {
        mTitleBar = (TitleBarView) findViewById(R.id.titleBar);
        mBanner = (BGABanner) findViewById(R.id.banner_main_default);
        mRecycler = (RecyclerView) findViewById(R.id.recycler);


        dialog = new ViewPagerDialog(this);
        adapter = new UltraPagerAdapter(false);
        mTitleBar.setTitleMainText("首页");
        mTitleBar.setRightTextDrawable(R.drawable.ic_toc_grey);
        mTitleBar.setOnRightTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show(adapter);
            }
        });
        setBannerData();
        setImgData();
    }

    //设置测试数据（图片来自于百度）
    private void setBannerData() {
        mListBanner.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492764593&di=0ca0871a53c829b5c14166f" +
                "e73a0be56&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.gai001.com%2Fcars%2Fthumbs%2Fd43%2Farticle11920_big.jpg");
        mListBanner.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492169871601&di=12d073aaab80c597b99742e52b3e4231&imgtype=0&sr" +
                "c=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fbbs6%2F1401%2F09%2Fc8%2F30432145_1389259380529_1024x1024.jpg");
        mListBanner.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_1000" +
                "0&sec=1492161280521&di=cf9290345724698f088c48150451045c&imgtype=0&src=h" +
                "ttp%3A%2F%2Fpic1.t139.com%2Fimages%2Fuserimg%2F20150417%2F20150417164139_58849.jpg");
        mListBanner.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492169871599" +
                "&di=54da5ed5a9d76f52b9235a2cf29f4072&imgtype=0&src=http%3A%2F%2Fs7.sinaimg.cn%2Fmw690%2F004gqhyTgy6HytMBaYef6%26690");
        mListBanner.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492169871599&di=56d4c7c9cbb79d7a440e865b0924a7f8&imgtype=" +
                "0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fbbs6%2F1401%2F09%2Fc7%2F30432116_1389259314328_500x500.jpg");
        setBanner(mListBanner);
    }

    private void setImgData(){

        for (int i = 0;i < 6;i++){
            mListRecycler.add("");
        }
        setInformationRecycler(mListRecycler);
    }


    /**
     * 设置资讯图片数据
     *
     * @param data
     */
    private void setInformationRecycler(List<String> data) {
        //RecyclerView的一些初始设置
        mRecycler.setHasFixedSize(true);
        mRecycler.setFocusableInTouchMode(false);
        mRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new RecommendedRecyclerAdapter(data, mContext);
        mRecycler.setAdapter(mAdapter);
        mAdapter.initObserble();
    }

    /**
     * 设置Banner
     *
     * @param entities
     */
    private void setBanner(List<String> entities) {
        ViewGroup.LayoutParams params = mBanner.getLayoutParams();
        int width = ScreenUtil.getScreenWidth(mContext);
        params.height = (int) (width * 400.0 / 600);
        mBanner.setLayoutParams(params);

        //设置适配器
        mBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                GlideManager.loadImg(model, itemView);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        });
        //设置数据
        if (entities != null && entities.size() != 0) {
            mBanner.setData(entities, null);
        }
    }

    private void setData(){
        List<String> newList = new ArrayList<>();
        newList.add("http://avatar.csdn.net/7/1/E/2_hdszlk.jpg");
        newList.add("http://avatar.csdn.net/7/1/E/2_hdszlk.jpg");
        newList.add("http://avatar.csdn.net/7/1/E/2_hdszlk.jpg");
        newList.add("http://avatar.csdn.net/6/E/C/1_u010694658.jpg");
        newList.add("http://avatar.csdn.net/6/E/C/1_u010694658.jpg");
        newList.add("http://avatar.csdn.net/6/E/C/1_u010694658.jpg");
        newList.add("http://avatar.csdn.net/6/E/C/1_u010694658.jpg");
        newList.add("http://avatar.csdn.net/1/7/7/1_qianmang.jpg");
        newList.add("http://avatar.csdn.net/1/7/7/1_qianmang.jpg");
        newList.add("http://avatar.csdn.net/1/7/7/1_qianmang.jpg");
        mHfLayout.setLoadImageInterface(new HeapFrameLayout.LoadImageInterface() {
            @Override
            public void loadImage(String url, ImageView imageView) {
                Glide.with(MainActivity.this).load(url)
                        .transform(new GlideCircleTransform(MainActivity.this, 2, Color.WHITE)).into(imageView);
            }
        });

        mHfLayout.setImages(newList);
    }
}
