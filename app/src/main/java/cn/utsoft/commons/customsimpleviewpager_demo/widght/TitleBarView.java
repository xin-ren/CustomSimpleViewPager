package cn.utsoft.commons.customsimpleviewpager_demo.widght;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.utsoft.commons.customsimpleviewpager_demo.R;


/**
 * Created by 李波 on 2017/3/17.
 * Function:
 * Desc:
 */
public class TitleBarView extends LinearLayout {
	private static final int DEFAULT_TEXT_COLOR = -1;
	private static final int DEFAULT_TEXT_BG_COLOR = 0;
	private static final int DEFAULT_TEXT_SIZE = 16;
	private static final int DEFAULT_SUB_TEXT_SIZE = 12;
	private LinearLayout mLeftLayout;
	private LinearLayout mCenterLayout;
	private LinearLayout mRightLayout;
	private TextView mLeftTv;
	private TextView mTitleTv;
	private TextView mSubTitleText;
	private View mDividerView;
	private TextView mRightTv;
	private int mStatusBarHeight;
	private int mScreenWidth;
	private int mPaddingValue;
	private int mDividerHeight;
	private int mActionPadding;
	private int mLeftTextSize;
	private int mLeftTextColor;
	private int mLeftTextBackgroundColor;
	private int mLeftDrawable;
	private int mLeftDrawablePadding;
	private int mLeftTextBackgroundResource;
	private int mTitleMainTextSize;
	private int mTitleMainTextColor;
	private int mTitleMainTextBackgroundColor;
	private int mTitleMainTextBackgroundResource;
	private int mTitleSubTextSize;
	private int mTitleSubTextColor;
	private int mTitleSubTextBackgroundColor;
	private int mTitleSubTextBackgroundResource;
	private int mRightTextSize;
	private int mRightTextColor;
	private int mRightTextBackgroundColor;
	private int mRightDrawable;
	private int mRightDrawablePadding;
	private int mRightTextBackgroundResource;
	private int mActionTextSize;
	private int mActionTextColor;
	private int mActionTextBackgroundColor;
	private int mActionTextBackgroundResource;
	private boolean immersible;
	private boolean isTitleFakeBold;
	private String mTitleMainText;
	private String mTitleSubText;
	private String mLeftText;
	private String mRightText;

	public TitleBarView(Context context) {
		this(context, (AttributeSet) null, 0);
	}

	public TitleBarView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TitleBarView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.immersible = false;
		this.initAttributes(context, attrs);
		this.init(context);
	}

	private void initAttributes(Context context, AttributeSet attrs) {
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TitleBarView);
		this.mPaddingValue = ta.getDimensionPixelSize(R.styleable.TitleBarView_title_outPadding, dip2px(context, 6.0F));
		this.mDividerHeight = ta.getDimensionPixelSize(R.styleable.TitleBarView_title_dividerHeight, 0);
		this.mActionPadding = ta.getDimensionPixelSize(R.styleable.TitleBarView_title_actionPadding, dip2px(context, 2.0F));
		this.immersible = ta.getBoolean(R.styleable.TitleBarView_title_immersible, false);
		this.isTitleFakeBold = ta.getBoolean(R.styleable.TitleBarView_title_titleMainTextFakeBold, false);
		this.mLeftText = ta.getString(R.styleable.TitleBarView_title_leftText);
		this.mLeftTextSize = ta.getDimensionPixelSize(R.styleable.TitleBarView_title_leftTextSize, sp2px(context, 16.0F));
		this.mLeftTextColor = ta.getColor(R.styleable.TitleBarView_title_leftTextColor, -1);
		this.mLeftTextBackgroundResource = ta.getResourceId(R.styleable.TitleBarView_title_leftTextBackgroundResource, -1);
		this.mLeftTextBackgroundColor = ta.getColor(R.styleable.TitleBarView_title_leftTextBackgroundColor, 0);
		this.mLeftDrawable = ta.getResourceId(R.styleable.TitleBarView_title_leftTextDrawable, -1);
		this.mLeftDrawablePadding = ta.getDimensionPixelSize(R.styleable.TitleBarView_title_leftTextDrawablePadding, 0);
		this.mTitleMainText = ta.getString(R.styleable.TitleBarView_title_titleMainText);
		this.mTitleMainTextSize = ta.getDimensionPixelSize(R.styleable.TitleBarView_title_titleMainTextSize, sp2px(context, 16.0F));
		this.mTitleMainTextColor = ta.getColor(R.styleable.TitleBarView_title_titleMainTextColor, -1);
		this.mTitleMainTextBackgroundColor = ta.getColor(R.styleable.TitleBarView_title_titleMainTextBackgroundColor, 0);
		this.mTitleMainTextBackgroundResource = ta.getResourceId(R.styleable.TitleBarView_title_titleMainTextBackgroundResource, -1);
		this.mTitleSubText = ta.getString(R.styleable.TitleBarView_title_titleSubText);
		this.mTitleSubTextSize = ta.getDimensionPixelSize(R.styleable.TitleBarView_title_titleSubTextSize, sp2px(context, 12.0F));
		this.mTitleSubTextColor = ta.getColor(R.styleable.TitleBarView_title_titleSubTextColor, -1);
		this.mTitleSubTextBackgroundColor = ta.getColor(R.styleable.TitleBarView_title_titleSubTextBackgroundColor, 0);
		this.mTitleSubTextBackgroundResource = ta.getResourceId(R.styleable.TitleBarView_title_titleSubTextBackgroundResource, -1);
		this.mRightText = ta.getString(R.styleable.TitleBarView_title_rightText);
		this.mRightTextSize = ta.getDimensionPixelSize(R.styleable.TitleBarView_title_rightTextSize, sp2px(context, 16.0F));
		this.mRightTextColor = ta.getColor(R.styleable.TitleBarView_title_rightTextColor, -1);
		this.mRightTextBackgroundResource = ta.getResourceId(R.styleable.TitleBarView_title_rightTextBackgroundResource, -1);
		this.mRightTextBackgroundColor = ta.getColor(R.styleable.TitleBarView_title_rightTextBackgroundColor, 0);
		this.mRightDrawable = ta.getResourceId(R.styleable.TitleBarView_title_rightTextDrawable, -1);
		this.mRightDrawablePadding = ta.getDimensionPixelSize(R.styleable.TitleBarView_title_rightTextDrawablePadding, 0);
		this.mActionTextSize = ta.getDimensionPixelSize(R.styleable.TitleBarView_title_actionTextSize, sp2px(context, 16.0F));
		this.mActionTextColor = ta.getColor(R.styleable.TitleBarView_title_actionTextColor, -1);
		this.mActionTextBackgroundColor = ta.getColor(R.styleable.TitleBarView_title_actionTextBackgroundColor, 0);
		this.mActionTextBackgroundResource = ta.getResourceId(R.styleable.TitleBarView_title_actionTextBackgroundResource, -1);
	}

	private void init(Context context) {
		if (context instanceof Activity) {
			this.setImmersible((Activity) context, this.immersible);
		}

		this.mScreenWidth = this.getScreenWidth();
		this.initView(context);
	}

	private void initView(Context context) {
		LayoutParams params = new LayoutParams(-2, -1);
		LayoutParams dividerParams = new LayoutParams(-1, this.mDividerHeight);
		this.mLeftLayout = new LinearLayout(context);
		this.mCenterLayout = new LinearLayout(context);
		this.mRightLayout = new LinearLayout(context);
		this.mDividerView = new View(context);
		this.mLeftLayout.setGravity(16);
		this.mCenterLayout.setGravity(17);
		this.mCenterLayout.setOrientation(VERTICAL);
		this.mRightLayout.setGravity(16);
		this.mLeftLayout.setPadding(this.mPaddingValue, 0, 0, 0);
		this.mRightLayout.setPadding(0, 0, this.mPaddingValue, 0);
		this.mLeftTv = new TextView(context);
		this.mLeftTv.setGravity(17);
		this.setLeftText(this.mLeftText);
		this.mLeftTv.setSingleLine();
		this.mLeftTv.setTextSize((float) px2sp(context, (float) this.mLeftTextSize));
		this.mLeftTv.setTextColor(this.mLeftTextColor);
		this.mLeftTv.setBackgroundColor(this.mLeftTextBackgroundColor);
		if (this.mLeftTextBackgroundResource != -1) {
			this.mLeftTv.setBackgroundResource(this.mLeftTextBackgroundResource);
		}

		if (this.mLeftDrawable != -1) {
			this.setLeftTextDrawable(this.mLeftDrawable, this.mLeftDrawablePadding);
		}

		this.mLeftLayout.addView(this.mLeftTv, params);
		this.mTitleTv = new TextView(context);
		this.mTitleTv.setGravity(17);
		this.setTitleMainText(this.mTitleMainText);
		this.mTitleTv.getPaint().setFakeBoldText(this.isTitleFakeBold);
		this.mTitleTv.setTextSize((float) px2sp(context, (float) this.mTitleMainTextSize));
		this.mTitleTv.setTextColor(this.mTitleMainTextColor);
		this.mTitleTv.setBackgroundColor(this.mTitleMainTextBackgroundColor);
		if (this.mTitleMainTextBackgroundResource != -1) {
			this.mTitleTv.setBackgroundResource(this.mTitleMainTextBackgroundResource);
		}
		this.mTitleTv.setTag("skin:main_title:textColor");
//		setTag("skin:main_bg_title:background");
		this.mCenterLayout.addView(this.mTitleTv);
		this.mSubTitleText = new TextView(context);
		this.mSubTitleText.setTextSize((float) px2sp(context, (float) this.mTitleSubTextSize));
		this.mSubTitleText.setSingleLine();
		this.setTitleSubText(this.mTitleSubText);
		this.mSubTitleText.setGravity(17);
		this.mSubTitleText.setTextColor(this.mTitleSubTextColor);
		this.mSubTitleText.setBackgroundColor(this.mTitleSubTextBackgroundColor);
		this.mSubTitleText.setEllipsize(TextUtils.TruncateAt.END);
		if (this.mTitleSubTextBackgroundResource != -1) {
			this.mSubTitleText.setBackgroundResource(this.mTitleSubTextBackgroundResource);
		}

		this.mCenterLayout.addView(this.mSubTitleText);
		this.mRightTv = new TextView(context);
		this.mRightTv.setGravity(17);
		this.setRightText(this.mRightText);
		this.mRightTv.setSingleLine();
		this.mRightTv.setTextSize((float) px2sp(context, (float) this.mRightTextSize));
		this.mRightTv.setTextColor(this.mRightTextColor);
		this.mRightTv.setBackgroundColor(this.mRightTextBackgroundColor);
		if (this.mRightTextBackgroundResource != -1) {
			this.mRightTv.setBackgroundResource(this.mRightTextBackgroundResource);
		}

		if (this.mRightDrawable != -1) {
			this.setRightTextDrawable(this.mRightDrawable, this.mRightDrawablePadding);
		}

		this.mRightLayout.addView(this.mRightTv, params);
		this.addView(this.mLeftLayout, params);
		this.addView(this.mCenterLayout, params);
		this.addView(this.mRightLayout, params);
		this.addView(this.mDividerView, dividerParams);
	}

	public void setImmersible(Activity activity, boolean immersible) {
		this.setImmersible(activity, immersible, true);
	}

	public void setImmersible(Activity activity, boolean immersible, boolean isPlusStatusHeight) {
		if (this.immersible != immersible) {
			this.immersible = immersible;
			if (immersible && isPlusStatusHeight && Build.VERSION.SDK_INT >= 19) {
				this.mStatusBarHeight = this.getStatusBarHeight();
			} else {
				this.mStatusBarHeight = 0;
			}

			if (activity != null) {
				Window window = activity.getWindow();
				if (immersible) {
					if (Build.VERSION.SDK_INT >= 19) {
						window.addFlags(67108864);
						if (Build.VERSION.SDK_INT >= 21) {
							window.clearFlags(67108864);
							window.getDecorView().setSystemUiVisibility(1024);
							window.addFlags(-2147483648);
							window.setStatusBarColor(0);
						}
					}
				} else if (Build.VERSION.SDK_INT >= 19) {
					window.clearFlags(67108864);
					if (Build.VERSION.SDK_INT >= 21) {
						window.getDecorView().setSystemUiVisibility(1024);
						window.clearFlags(-2147483648);
						window.setStatusBarColor(0);
					}
				}

				this.invalidate();
			}
		}
	}

	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		this.mLeftLayout.layout(0, this.mStatusBarHeight, this.mLeftLayout.getMeasuredWidth(), this.mLeftLayout.getMeasuredHeight() + this.mStatusBarHeight);
		this.mRightLayout.layout(this.mScreenWidth - this.mRightLayout.getMeasuredWidth(), this.mStatusBarHeight, this.mScreenWidth, this.mRightLayout.getMeasuredHeight() + this.mStatusBarHeight);
		if (this.mLeftLayout.getMeasuredWidth() > this.mRightLayout.getMeasuredWidth()) {
			this.mCenterLayout.layout(this.mLeftLayout.getMeasuredWidth(), this.mStatusBarHeight, this.mScreenWidth - this.mLeftLayout.getMeasuredWidth(), this.getMeasuredHeight());
		} else {
			this.mCenterLayout.layout(this.mRightLayout.getMeasuredWidth(), this.mStatusBarHeight, this.mScreenWidth - this.mRightLayout.getMeasuredWidth(), this.getMeasuredHeight());
		}

		this.mDividerView.layout(0, this.getMeasuredHeight() - this.mDividerView.getMeasuredHeight(), this.getMeasuredWidth(), this.getMeasuredHeight());
	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		this.measureChild(this.mLeftLayout, widthMeasureSpec, heightMeasureSpec);
		this.measureChild(this.mRightLayout, widthMeasureSpec, heightMeasureSpec);
		if (this.mLeftLayout.getMeasuredWidth() > this.mRightLayout.getMeasuredWidth()) {
			this.mCenterLayout.measure(MeasureSpec.makeMeasureSpec(this.mScreenWidth - 2 * this.mLeftLayout.getMeasuredWidth(), MeasureSpec.EXACTLY), heightMeasureSpec);
		} else {
			this.mCenterLayout.measure(MeasureSpec.makeMeasureSpec(this.mScreenWidth - 2 * this.mRightLayout.getMeasuredWidth(), MeasureSpec.EXACTLY), heightMeasureSpec);
		}

		this.measureChild(this.mDividerView, widthMeasureSpec, heightMeasureSpec);
		this.setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec) + this.mStatusBarHeight);
	}

	public void setLeftText(CharSequence title) {
		this.mLeftTv.setText(title);
	}

	public void setLeftText(int id) {
		this.mLeftTv.setText(id);
	}

	public void setLeftTextSize(int unit, float size) {
		this.mLeftTv.setTextSize(unit, size);
	}

	public void setLeftTextSize(float size) {
		this.mLeftTv.setTextSize(size);
	}

	public void setLeftTextColor(int id) {
		this.mLeftTv.setTextColor(id);
	}

	public void setLeftTextColor(ColorStateList color) {
		try {
			this.mLeftTv.setTextColor(color);
		} catch (Exception var3) {
			;
		}

	}

	public void setLeftTextBackgroundColor(int color) {
		this.mLeftTv.setBackgroundColor(color);
	}

	public void setLeftTextBackgroundResource(int id) {
		this.mLeftTv.setBackgroundResource(id);
	}

	public void setLeftTextDrawable(int id, int drawablePadding) {
		this.mLeftTv.setCompoundDrawablesWithIntrinsicBounds(id, 0, 0, 0);
		this.setLeftTextDrawablePadding(drawablePadding);
	}

	public void setLeftTextDrawablePadding(int drawablePadding) {
		this.mLeftDrawablePadding = drawablePadding;
		this.mLeftTv.setCompoundDrawablePadding(dip2px(this.getContext(), (float) this.mLeftDrawablePadding));
	}

	public void setLeftTextDrawable(int id) {
		this.setLeftTextDrawable(id, this.mLeftDrawablePadding);
	}

	public void setLeftTextPadding(int left, int top, int right, int bottom) {
		this.mLeftTv.setPadding(left, top, right, bottom);
	}

	public void setOnLeftTextClickListener(OnClickListener l) {
		this.mLeftTv.setOnClickListener(l);
	}

	public void setLeftVisible(boolean visible) {
		this.mLeftTv.setVisibility(visible ? VISIBLE : GONE);
	}

	public void addLeftAction(Action action, int position) {
		View view = this.inflateAction(action);
		this.mLeftLayout.addView(view, position);
	}

	public View addLeftAction(Action action) {
		View view = this.inflateAction(action);
		this.mLeftLayout.addView(view);
		return view;
	}

	public void addCenterAction(Action action, int position) {
		View view = this.inflateAction(action);
		this.mCenterLayout.addView(view, position);
	}

	public void addCenterAction(Action action) {
		View view = this.inflateAction(action);
		if (view != null) {
			this.mCenterLayout.addView(view);
		}

	}

	public void setOnCenterClickListener(OnClickListener l) {
		this.mCenterLayout.setOnClickListener(l);
	}

	public void setTitleMainText(int id) {
		this.mTitleTv.setText(id);
	}

	public void setTitleMainText(CharSequence charSequence) {
		this.mTitleTv.setText(charSequence);
	}

	public void setTitleMainTextSize(float titleMainTextSpValue) {
		this.mTitleTv.setTextSize(titleMainTextSpValue);
	}

	public void setTitleMainTextSize(int unit, float titleMainTextSpValue) {
		this.mTitleTv.setTextSize(unit, titleMainTextSpValue);
	}

	public void setTitleMainTextColor(int id) {
		this.mTitleTv.setTextColor(id);
	}

	public void setTitleMainTextBackgroundColor(int color) {
		this.mTitleTv.setBackgroundColor(color);
	}

	public void setTitleMainTextBackgroundResource(int id) {
		this.mTitleTv.setBackgroundResource(id);
	}

	public void setTitleMainTextFakeBold(boolean isFakeBold) {
		this.isTitleFakeBold = isFakeBold;
	}

	public void setTitleMainTextPadding(int left, int top, int right, int bottom) {
		this.mTitleTv.setPadding(left, top, right, bottom);
	}

	public void setTitleSubText(int id) {
		this.mSubTitleText.setText(id);
	}

	public void setTitleSubText(CharSequence charSequence) {
		if (charSequence != null && !charSequence.toString().isEmpty()) {
			this.mSubTitleText.setVisibility(VISIBLE);
		} else {
			this.mSubTitleText.setVisibility(GONE);
		}

		this.mSubTitleText.setText(charSequence);
	}

	public void setTitleSubTextSize(float titleMainTextSpValue) {
		this.mSubTitleText.setTextSize(titleMainTextSpValue);
	}

	public void setTitleSubTextColor(int id) {
		this.mSubTitleText.setTextColor(id);
	}

	public void setTitleSubTextBackgroundColor(int color) {
		this.mSubTitleText.setBackgroundColor(color);
	}

	public void setTitleSubTextBackgroundResource(int id) {
		this.mSubTitleText.setBackgroundResource(id);
	}

	public void setRightText(CharSequence title) {
		this.mRightTv.setText(title);
	}

	public void setRightText(int id) {
		this.mRightTv.setText(id);
	}

	public void setRightTextSize(int unit, float size) {
		this.mRightTv.setTextSize(unit, size);
	}

	public void setRightTextSize(float size) {
		this.mRightTv.setTextSize(size);
	}

	public void setRightTextColor(int id) {
		this.mRightTv.setTextColor(id);
	}

	public void setRightTextColor(ColorStateList color) {
		try {
			this.mRightTv.setTextColor(color);
		} catch (Exception var3) {
			;
		}

	}

	public void setRightTextBackgroundColor(int color) {
		this.mRightTv.setBackgroundColor(color);
	}

	public void setRightTextBackgroundResource(int id) {
		this.mRightTv.setBackgroundResource(id);
	}

	public void setRightTextDrawable(int id, int drawablePadding) {
		this.mRightTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, id, 0);
		this.setRightTextDrawablePadding(drawablePadding);
	}

	public void setRightTextDrawablePadding(int drawablePadding) {
		this.mRightDrawablePadding = drawablePadding;
		this.mRightTv.setCompoundDrawablePadding(dip2px(this.getContext(), (float) this.mRightDrawablePadding));
	}

	public void setRightTextDrawable(int id) {
		this.setRightTextDrawable(id, this.mRightDrawablePadding);
	}

	public void setRightTextPadding(int left, int top, int right, int bottom) {
		this.mRightTv.setPadding(left, top, right, bottom);
	}

	public void setOnRightTextClickListener(OnClickListener l) {
		this.mRightTv.setOnClickListener(l);
	}

	public void setRightVisible(boolean visible) {
		this.mRightTv.setVisibility(visible ? VISIBLE : GONE);
	}

	public View addRightAction(Action action, int position) {
		View view = this.inflateAction(action);
		this.mRightLayout.addView(view, position);
		return view;
	}

	public View addRightAction(Action action) {
		View view = this.inflateAction(action);
		this.mRightLayout.addView(view);
		return view;
	}

	public void setDivider(Drawable drawable) {
		this.mDividerView.setBackgroundDrawable(drawable);
	}

	public void setDividerBackgroundColor(int color) {
		this.mDividerView.setBackgroundColor(color);
	}

	public void setDividerBackgroundResource(int resourceId) {
		this.mDividerView.setBackgroundResource(resourceId);
	}

	public void setDividerHeight(int dividerHeight) {
		this.mDividerHeight = dividerHeight;
		this.mDividerView.getLayoutParams().height = dividerHeight;
	}

	public void setOutPadding(int paddingValue) {
		this.mPaddingValue = paddingValue;
		this.mLeftLayout.setPadding(this.mPaddingValue, 0, 0, 0);
		this.mRightLayout.setPadding(0, 0, this.mPaddingValue, 0);
	}

	private View inflateAction(Action action) {
		Object view = null;
		Object obj = action.getData();
		if (obj == null) {
			return null;
		} else {
			if (obj instanceof View) {
				view = (View) obj;
			} else if (obj instanceof String) {
				TextView img = new TextView(this.getContext());
				img.setGravity(17);
				img.setText((String) obj);
				img.setTextSize((float) px2sp(this.getContext(), (float) this.mActionTextSize));
				img.setTextColor(this.mActionTextColor);
				img.setBackgroundColor(this.mActionTextBackgroundColor);
				if (this.mActionTextBackgroundResource != -1) {
					img.setBackgroundResource(this.mActionTextBackgroundResource);
				}

				view = img;
			} else if (obj instanceof Integer) {
				ImageView img1 = new ImageView(this.getContext());
				img1.setScaleType(ImageView.ScaleType.CENTER_CROP);
				img1.setImageResource(((Integer) obj).intValue());
				view = img1;
			}

			((View) view).setPadding(this.mActionPadding, 0, this.mActionPadding, 0);
			((View) view).setTag(action);
			((View) view).setOnClickListener(action.getOnClickListener());
			return (View) view;
		}
	}

	public int getStatusBarHeight() {
		int result = 0;
		int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = this.getResources().getDimensionPixelSize(resourceId);
		}

		return result;
	}

	private int getScreenWidth() {
		return this.getResources().getDisplayMetrics().widthPixels;
	}

	public static int dip2px(Context context, float dipValue) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5F);
	}

	public static int sp2px(Context context, float spValue) {
		float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5F);
	}

	public static int px2sp(Context context, float pxValue) {
		float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5F);
	}

	public class ViewAction implements Action<View> {
		private View mView;
		private OnClickListener onClickListener;

		public ViewAction(View mView, OnClickListener onClickListener) {
			this.onClickListener = onClickListener;
		}

		public View getData() {
			return this.mView;
		}

		public OnClickListener getOnClickListener() {
			return this.onClickListener;
		}
	}

	public class TextAction implements Action<String> {
		private String mText;
		private OnClickListener onClickListener;

		public TextAction(String mText, OnClickListener onClickListener) {
			this.mText = mText;
			this.onClickListener = onClickListener;
		}

		public TextAction(int mText, OnClickListener onClickListener) {
			this.mText = getResources().getString(mText);
			this.onClickListener = onClickListener;
		}

		public String getData() {
			return this.mText;
		}

		public OnClickListener getOnClickListener() {
			return this.onClickListener;
		}
	}

	public class ImageAction implements Action<Integer> {
		private int mDrawable;
		private OnClickListener onClickListener;

		public ImageAction(int mDrawable, OnClickListener onClickListener) {
			this.mDrawable = mDrawable;
			this.onClickListener = onClickListener;
		}

		public ImageAction(int mDrawable) {
			this.mDrawable = mDrawable;
		}

		public Integer getData() {
			return Integer.valueOf(this.mDrawable);
		}

		public OnClickListener getOnClickListener() {
			return this.onClickListener;
		}
	}

	public interface Action<T> {
		T getData();

		OnClickListener getOnClickListener();
	}
}
