package cn.utsoft.commons.customsimpleviewpager_demo;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Create by 任新 on 2017/4/12 10:04
 * Function：
 * Desc：viewpager 的切换效果
 */
public class ScalerPageTransformer implements ViewPager.PageTransformer {


    private static final String LOG_TAG = "ScalerPageTransformer";

    @SuppressLint("NewApi")
    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int translateX = (int) (pageWidth * 0.2);

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(1);
        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when
            // moving to the left page
            view.setAlpha(1);

            /**
             * 0 --> 0
             *  -1 ==> w/3
             *
             */
            view.setTranslationX(-translateX * position);
            /**
             *
             *  0 --> 0.8
             *  -1 ==> 0.7
             *
             */
            float scale = (float) ((1 + position) * 0.1 + 0.7);
            view.setScaleX(scale);
            view.setScaleY(scale);
        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            view.setAlpha(1);
            // Counteract the default slide transition
            /**
             *
             *  1 ==> w / 3
             *  0 ==> 0
             *
             */
            view.setTranslationX(-translateX * position);
            // Scale the page down (between MIN_SCALE and 1)

            /**
             * 1 ==> 0.8
             * 0 --> 0.9
             *
             *
             */
            float sc = (float) ((1 - position) * 0.1 + 0.7);
            view.setScaleX(sc);
            view.setScaleY(sc);
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(1);

        }
    }
}
