package cn.utsoft.commons.customsimpleviewpager_demo;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marno.rapidlib.manager.GlideManager;


/**
 * Created by 任新 on 2017/4/11 10:48.
 * Function:
 * Desc:
 */
public class UltraPagerAdapter extends PagerAdapter {
    private boolean isMultiScr;

    private int[] mPicture = {R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six};

    private String[] mTitles = {"1", "2", "3", "4", "5", "6"};
    private String[] mContents = new String[]{"首先", "其次", "然后", "下一步",
            "紧接着", "完成"};

    public UltraPagerAdapter(boolean isMultiScr) {
        this.isMultiScr = isMultiScr;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(container.getContext()).inflate(R.layout.layout_child, null);
        TextView mTvTitle = (TextView) linearLayout.findViewById(R.id.tv_title);
        TextView mTvContent = (TextView) linearLayout.findViewById(R.id.tv_content);
        ImageView mIv = (ImageView) linearLayout.findViewById(R.id.iv_banner);
        mTvTitle.setText(mTitles[position]);
        mTvContent.setText(mContents[position]);
        GlideManager.loadImg(mPicture[position], mIv);
        container.addView(linearLayout);
        return linearLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        LinearLayout view = (LinearLayout) object;
        container.removeView(view);
    }
}
