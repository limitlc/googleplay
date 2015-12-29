package com.itheima45.holder;

import com.itheima45.R;
import com.itheima45.application.utils.StringUtils;
import com.itheima45.application.utils.UIUtils;
import com.itheima45.bean.AppInfo;
import com.itheima45.imageload.ImageLoader;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class AppHolder extends BaseHolder<AppInfo> {

	private ImageView item_icon;
	private TextView item_title;
	private RatingBar item_rating;
	private TextView item_size;
	private TextView item_bottom;

	@Override
	public void refreshView() {
		AppInfo appInfo = getData();
		ImageLoader.load(item_icon, appInfo.getIconUrl());
		item_title.setText(appInfo.getName());
		item_rating.setRating(appInfo.getStars());
		item_size.setText(StringUtils.formatFileSize(appInfo.getSize()));
		item_bottom.setText(appInfo.getDes());
	}

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.list_item);
		item_icon = (ImageView) view.findViewById(R.id.item_icon);
		item_title = (TextView) view.findViewById(R.id.item_title);
		item_rating = (RatingBar) view.findViewById(R.id.item_rating);
		item_size = (TextView) view.findViewById(R.id.item_size);
		item_bottom = (TextView) view.findViewById(R.id.item_bottom);
		return view;
	}

}