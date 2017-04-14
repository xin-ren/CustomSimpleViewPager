package cn.utsoft.commons.customsimpleviewpager_demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.utsoft.commons.customsimpleviewpager_demo.model.guide.adapter.UltraPagerAdapter;
import cn.utsoft.commons.customsimpleviewpager_demo.utils.GlideCircleTransform;
import cn.utsoft.commons.customsimpleviewpager_demo.widght.HeapFrameLayout;
import cn.utsoft.commons.customsimpleviewpager_demo.widght.ViewPagerDialog;


public class MainActivity extends AppCompatActivity {

    private ViewPagerDialog dialog;
    private UltraPagerAdapter adapter;
    private HeapFrameLayout mHfLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ViewPagerDialog(this);
        adapter = new UltraPagerAdapter(false);

        mHfLayout = (HeapFrameLayout) findViewById(R.id.hf);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show(adapter);
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHfLayout.setVisibility(View.VISIBLE);
                setData();
            }
        });
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
