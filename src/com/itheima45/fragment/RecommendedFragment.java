package com.itheima45.fragment;

import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.itheima45.application.utils.UIUtils;
import com.itheima45.protocol.RecommendProtocol;
import com.itheima45.randomLayout.StellarMap;
import com.itheima45.randomLayout.StellarMap.Adapter;
import com.mwqi.ui.widget.LoadingPage.LoadResult;

public class RecommendedFragment extends BaseFragment {

	private List<String> mDatas;

	@Override
	protected LoadResult load() {
		RecommendProtocol protocol = new RecommendProtocol();
		mDatas = protocol.load(0);
		return check(mDatas);
	}

	@Override
	protected View createLoadedView() {
		StellarMap mStellarMap = new StellarMap(UIUtils.getContext());
		int padding = UIUtils.dip2px(20);
		//设置左上右下的间距
		mStellarMap.setInnerPadding(padding, padding, padding, padding);
		//设置几行几列
		mStellarMap.setRegularity(6, 9);
		
		RecommendedAdapter adapter = new RecommendedAdapter();
		mStellarMap.setAdapter(adapter);
		//设置组的动画。第一个参数表示。从第0组开始。第二个参数表示开启动画
		mStellarMap.setGroup(0, true);
		
		return mStellarMap;
	}
	
	private class RecommendedAdapter implements Adapter{
		
		
		
        private Random mRandom;

		public RecommendedAdapter() {
			mRandom = new Random();
		}
		//表示有几组数据
		@Override
		public int getGroupCount() {
			// TODO Auto-generated method stub
			return 2;
		}
       //每一组表示有几个数据
		@Override
		public int getCount(int group) {
			// TODO Auto-generated method stub
			return 15;
		}
        //设置界面数据
		@Override
		public View getView(int group, int position, View convertView) {
			
			TextView textView = new TextView(UIUtils.getContext());
			// 两个值相加不等于255.如果等于255.显示的值就是白色。那么背景也是白色。就看不到数据了
			int red = 30 + mRandom.nextInt(210);
			
			int green = 30 + mRandom.nextInt(210);
			
			int blue = 30 + mRandom.nextInt(210);
			
			int color = Color.rgb(red, green, blue);
			
			//设置颜色 ,设置一个基础的颜色
			textView.setTextColor(color);
			//设置文本大小 ，设置一个基础值。
			textView.setTextSize(15 + mRandom.nextInt(12));
			
			//设置文本文字
			textView.setText(mDatas.get(position));
			
			return textView;
		}

		@Override
		public int getNextGroupOnPan(int group, float degree) {
			// TODO Auto-generated method stub
			return (group + 1) % 2;
		}

		@Override
		public int getNextGroupOnZoom(int group, boolean isZoomIn) {
			// TODO Auto-generated method stub
			return (group + 1) % 2;
		}
		
	}

}
