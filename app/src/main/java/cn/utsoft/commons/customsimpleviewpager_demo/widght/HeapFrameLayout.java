package cn.utsoft.commons.customsimpleviewpager_demo.widght;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 任新 on 2017/4/12 23:13.
 * Function:
 * Desc: 重叠控件
 */
public class HeapFrameLayout extends ViewGroup {
    private List<String> data = new ArrayList<>();

    private LoadImageInterface loadImageInterface;//加载图片的时候的回掉

    private static final int FE = 3;

    private static final String LOG_TAG = "HeapFrameLayout";

    public HeapFrameLayout(Context context) {
        super(context);
    }

    public HeapFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeapFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {


        int left = 0;

        for(int i=0;i < getChildCount();i++){
            View childView = getChildAt(i);
            int childW = childView.getMeasuredWidth();
            int childH = childView.getMeasuredHeight();
            left += childW - childW / FE;
            left = i == 0 ? 0 : left;
            Log.i(LOG_TAG,"left  "+left + "  cw "+childW);
            childView.layout(left,0,left+childW,childH);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        Log.i(LOG_TAG,"onMeasure  "+widthSize+"   "+heightSize);

        // FIXME: 2017/4/12/012 仅仅只是计算了宽度哈
        if(getChildCount() > 0){
            View view = getChildAt(0);
            int width = view.getMeasuredWidth();
            widthSize = width * getChildCount() - width / FE *(getChildCount() - 1);
        }

        setMeasuredDimension(widthSize,heightSize);

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }//end onDraw

    public void setImages(List<String> data){

        HeapFrameLayout.this.data.clear();
        if(data != null && data.size() >= 0){
            if(data.size() > data.size()){
                for(int i=0;i < data.size();i++){
                    this.data.add(data.get(i));
                }
            }else{
                this.data.addAll(data);
            }
        }

        post(new Runnable() {
            @Override
            public void run() {

                //重新创建ImageView
                removeAllViews();
                for(int i = 0; i < HeapFrameLayout.this.data.size(); i++){
                    ImageView imageView = createImageView();
                    addView(imageView);
                    Log.i(LOG_TAG,"addview "+imageView);
                }

                for(int i=0;i < getChildCount();i++){
                    ImageView imageView = (ImageView) getChildAt(i);
                    if(loadImageInterface != null){
                        if(HeapFrameLayout.this.data.size() > i){
                            loadImageInterface.loadImage(HeapFrameLayout.this.data.get(i),imageView);
                        }
                    }
                }

                postInvalidate();
            }
        });

    }

    private ImageView createImageView() {
        ImageView imageView = new ImageView(getContext());
        /**
         *
         * 计算控件的高度
         *
         */
        int measureH = getMeasuredHeight();
        Log.i(LOG_TAG,"measureH  "+measureH);
        LayoutParams params = new LayoutParams(
                measureH,measureH
        );
        imageView.setLayoutParams(params);
        return imageView;
    }

    public void setLoadImageInterface(LoadImageInterface loadImageInterface) {
        this.loadImageInterface = loadImageInterface;
    }

    public interface LoadImageInterface{

        void loadImage(String url, ImageView imageView);

    }


}
