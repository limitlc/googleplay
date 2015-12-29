package com.itheima45.holder;

//import android.animation.ValueAnimator;
import java.util.List;

import android.graphics.Color;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima45.R;
import com.itheima45.application.utils.UIUtils;
import com.itheima45.bean.AppInfo;
import com.itheima45.imageload.ImageLoader;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;


public class DetaiSafeHolder extends BaseHolder<AppInfo> implements
		OnClickListener {

	private LinearLayout mSafeContent;
	private ImageView safe_arrow;
	private ImageView[] ivs;
	private TextView[] des_tvs;
	private ImageView[] des_ivs;
	private LinearLayout[] des_layouts;

	@Override
	public void refreshView() {
		AppInfo info = getData();
		//对应着官方。安全。无广告等的图片下载地址
		List<String> safeUrl = info.getSafeUrl();
		//小框框打勾的下载地址
		List<String> safeDesUrl = info.getSafeDesUrl();
		//小框框打勾后面的描述信息
		List<String> safeDes = info.getSafeDes();
		//描述的文字颜色，有广告的颜色比较醒目
		List<Integer> safeDesColor = info.getSafeDesColor();

		for (int i = 0; i < 4; i++) {
			if (i < safeUrl.size() && i < safeDesUrl.size() && i < safeDes.size() && i < safeDesColor.size()) {
				ImageLoader.load(ivs[i], safeUrl.get(i));
				ImageLoader.load(des_ivs[i], safeDesUrl.get(i));
				des_tvs[i].setText(safeDes.get(i));

				int color;
				int colorType = safeDesColor.get(i);
				if (colorType >= 1 && colorType <= 3) {
					color = Color.rgb(255, 153, 0);
				} else if (colorType == 4) {
					color = Color.rgb(0, 177, 62);
				} else {
					color = Color.rgb(122, 122, 122);
				}
				des_tvs[i].setTextColor(color);

				ivs[i].setVisibility(View.VISIBLE);
				des_layouts[i].setVisibility(View.VISIBLE);
			} else {
				ivs[i].setVisibility(View.GONE);
				des_layouts[i].setVisibility(View.GONE);
			}
		}
	}

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.app_detail_safe);
		// 给安全界面设置点击事件
		RelativeLayout mSafeLayout = (RelativeLayout) view
				.findViewById(R.id.safe_layout);
		mSafeLayout.setOnClickListener(this);
		// 设置tag的两种状态。默认情况下是向下的。点击就向上

		safe_arrow = (ImageView) view.findViewById(R.id.safe_arrow);
		safe_arrow.setTag(false);
		// 默认情况下。内容页面的高度是没有的。所以设置为0
		mSafeContent = (LinearLayout) view.findViewById(R.id.safe_content);
		mSafeContent.getLayoutParams().height = 0;
		
		ivs = new ImageView[4];
		
		ivs[0] = (ImageView) view.findViewById(R.id.iv_1);
		ivs[1] = (ImageView) view.findViewById(R.id.iv_2);
		ivs[2] = (ImageView) view.findViewById(R.id.iv_3);
		ivs[3] = (ImageView) view.findViewById(R.id.iv_4);
		
		des_ivs = new ImageView[4];
		des_ivs[0] = (ImageView) view.findViewById(R.id.des_iv_1);
		des_ivs[1] = (ImageView) view.findViewById(R.id.des_iv_2);
		des_ivs[2] = (ImageView) view.findViewById(R.id.des_iv_3);
		des_ivs[3] = (ImageView) view.findViewById(R.id.des_iv_4);
		
		
		des_tvs = new TextView[4];
		des_tvs[0] = (TextView) view.findViewById(R.id.des_tv_1);
		des_tvs[1] = (TextView) view.findViewById(R.id.des_tv_2);
		des_tvs[2] = (TextView) view.findViewById(R.id.des_tv_3);
		des_tvs[3] = (TextView) view.findViewById(R.id.des_tv_4);
		
		
		des_layouts = new LinearLayout[4];
		des_layouts[0] = (LinearLayout) view.findViewById(R.id.des_layout_1);
		des_layouts[1] = (LinearLayout) view.findViewById(R.id.des_layout_2);
		des_layouts[2] = (LinearLayout) view.findViewById(R.id.des_layout_3);
		des_layouts[3] = (LinearLayout) view.findViewById(R.id.des_layout_4);

		return view;
	}

	@Override
	public void onClick(View v) {
		// 默认获取到标记的值
		boolean flag = (Boolean) safe_arrow.getTag();
		final LayoutParams params = mSafeContent.getLayoutParams();
		int height = mSafeContent.getMeasuredHeight();
		int tagetHeight;
		if (flag) {
			safe_arrow.setTag(false);
			tagetHeight = 0;
		} else {
			safe_arrow.setTag(true);
			tagetHeight = measureSafeContentHeight();
		}
	
	    //为了避免程序的anr异常。所以必须把mSafeContent设置为不可以点击
		mSafeContent.setEnabled(false);
		//属性动画从默认的高度到目标高度的一个变化的过程 0 1000
		ValueAnimator va = ValueAnimator.ofInt(height,tagetHeight);
		va.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				params.height = (Integer) arg0.getAnimatedValue();
				mSafeContent.setLayoutParams(params);
			}
		});
		va.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animator arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animator arg0) {
				//当动画结束的时候。控件就可以被点击了
				mSafeContent.setEnabled(true);
				boolean tag = (Boolean) safe_arrow.getTag();
				safe_arrow.setImageResource(tag?R.drawable.arrow_up:R.drawable.arrow_down);
				
				
			}
			
			@Override
			public void onAnimationCancel(Animator arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		//设置动画的时间
		va.setDuration(500);
		//开启动画
		va.start();
	}
    /**
     * 测量内容页面的高度
     * @return
     */
	private int measureSafeContentHeight() {
		int width = mSafeContent.getWidth();
		mSafeContent.getLayoutParams().height = LayoutParams.WRAP_CONTENT;
		int widthMeasureSpec = MeasureSpec.makeMeasureSpec(width,
				MeasureSpec.EXACTLY);
		int heightMeasureSpec = MeasureSpec.makeMeasureSpec(20000,
				MeasureSpec.AT_MOST);

		mSafeContent.measure(widthMeasureSpec, heightMeasureSpec);
		return mSafeContent.getMeasuredHeight();
	}

}
