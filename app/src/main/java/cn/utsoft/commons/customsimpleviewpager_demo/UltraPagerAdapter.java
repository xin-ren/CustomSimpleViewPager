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
 * Desc: 图片资源来自于UTOUU科技有限公司切图
 */
public class UltraPagerAdapter extends PagerAdapter {
    private boolean isMultiScr;

    private int[] mPicture = {R.drawable.img_subscribe,
            R.drawable.img_talk,
            R.drawable.img_free,
            R.drawable.img_payment,
            R.drawable.img_fitting,
            R.drawable.img_take_clothes};

    private String[] mTitles = {"预约", "店内洽谈", "免费量体", "线上付款", "到店试衣", "取衣"};
    private String[] mContents = new String[]{"在线预约，时间自由挑选", "到店交流，需求一步到位", "量体裁衣，数据精准保留", "订单生成，线上一键支付",
            "到店试衣，精致细节调整", "取衣便捷，到店快递任选"};

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
