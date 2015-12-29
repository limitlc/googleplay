package com.itheima45.holder;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

import com.itheima45.R;
import com.itheima45.application.utils.UIUtils;
import com.itheima45.imageload.ImageLoader;
import com.mwqi.ui.widget.IndicatorView;

public class HomePicHolder extends BaseHolder<List<String>> implements OnPageChangeListener {

	private AbsListView.LayoutParams rl;
	private List<String> appInfos;
	private IndicatorView indicatorView;
	private ViewPager mViewPager;
	private AutoPlayTask autoPlayTask;

	@Override
	public void refreshView() {
		appInfos = getData();
		// 一共有几个点
		indicatorView.setCount(appInfos.size());
		// 默认设置viewpager的位置
		mViewPager.setCurrentItem(5000, false);
		autoPlayTask.start();
	}

	@Override
	public View initView() {
		// 初始化轮播图最外面的布局
		RelativeLayout mHeadView = new RelativeLayout(UIUtils.getContext());
		// 设置轮播图的宽和高
		rl = new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,
				UIUtils.getDimens(R.dimen.list_header_hight));
		

		mHeadView.setLayoutParams(rl);

		// 初始化轮播图。然后设置数据

		mViewPager = new ViewPager(UIUtils.getContext());

		RelativeLayout.LayoutParams mParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);

		ViewPagerAdapter adapter = new ViewPagerAdapter();

		mViewPager.setAdapter(adapter);
		// 初始化轮播图的宽和高
		mViewPager.setLayoutParams(mParams);

		// 初始化点
		indicatorView = new IndicatorView(UIUtils.getContext());
		// 设置点的背景
		indicatorView.setIndicatorDrawable(UIUtils
				.getDrawable(R.drawable.indicator));

		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		// 设置到父控件的右边
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		// 设置到父控件的下边
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		// 设置左上右下的距离
		params.setMargins(0, 0, 20, 20);
		// 设置点的位置从0开始
		indicatorView.setSelection(0);
		// 把宽和高丢到布局里面
		indicatorView.setLayoutParams(params);
		// 自动跳的类
		autoPlayTask = new AutoPlayTask();
		
		
		mViewPager.setOnPageChangeListener(this);

		mViewPager.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					autoPlayTask.stop();
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					autoPlayTask.start();
				}
				return false;
			}
		});

		// 把轮播图和点加入到头文件里面。需要注意。先加轮播图。然后在加点。
		mHeadView.addView(mViewPager);
		mHeadView.addView(indicatorView);

		return mHeadView;
	}

	private class AutoPlayTask implements Runnable {
		// 自动跳的时间
		private int auto_play_time = 5000;

		private boolean is_auto_play = false;
		public void start() {
			if (!is_auto_play) {
				is_auto_play = true;
				UIUtils.removeCallbacks(this);
				UIUtils.postDelayed(this, auto_play_time);
			}
		}
		public void stop() {
			if (is_auto_play) {
				UIUtils.removeCallbacks(this);
				is_auto_play = false;
			}
		}
		@Override
		public void run() {
			if (is_auto_play) {
				UIUtils.removeCallbacks(this);
				int position = mViewPager.getCurrentItem();
				mViewPager.setCurrentItem(position + 1, true);
				UIUtils.postDelayed(this, auto_play_time);
			}
		}

	}

	private class ViewPagerAdapter extends PagerAdapter {

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((ImageView) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView imageView = new ImageView(UIUtils.getContext());
			// 注意。如果只要是从网络获取图片的话。就必须设置这个属性。
			imageView.setScaleType(ScaleType.FIT_XY);
			ImageLoader.load(imageView,
					appInfos.get(position % getData().size()));
			container.addView(imageView);
			return imageView;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		indicatorView.setSelection(arg0 % getData().size());
		
	}

}
