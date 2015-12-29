package com.itheima45.holder;

import android.view.View;
import android.widget.ImageView;

import com.itheima45.R;
import com.itheima45.application.utils.UIUtils;
import com.itheima45.bean.AppInfo;
import com.itheima45.imageload.ImageLoader;

public class DetailScreenHolder extends BaseHolder<AppInfo> {

	private ImageView[] imageViews;

	@Override
	public void refreshView() {
		AppInfo appInfo = getData();
		
		for(int i = 0 ;i < 5 ;i++){
			if(appInfo != null){
				ImageLoader.load(imageViews[i], appInfo.getScreen().get(i));
				imageViews[i].setVisibility(View.VISIBLE);
			}else{
				imageViews[i].setVisibility(View.GONE);
			}
		
		}

	}

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.app_detail_screen);

		imageViews = new ImageView[5];

		imageViews[0] = (ImageView) view.findViewById(R.id.screen_1);
		imageViews[1] = (ImageView) view.findViewById(R.id.screen_2);
		imageViews[2] = (ImageView) view.findViewById(R.id.screen_3);
		imageViews[3] = (ImageView) view.findViewById(R.id.screen_4);
		imageViews[4] = (ImageView) view.findViewById(R.id.screen_5);

		return view;
	}
}
