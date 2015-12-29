package com.itheima45.holder;

import android.view.View;
import android.widget.TextView;

import com.itheima45.R;
import com.itheima45.application.utils.UIUtils;
import com.itheima45.bean.CategoryInfo;

public class CategoryTitleHolder extends BaseHolder<CategoryInfo> {

	private TextView tv_title;

	@Override
	public void refreshView() {
		CategoryInfo categoryInfo = getData();
		tv_title.setText(categoryInfo.getTitle());
	}

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.category_item_title);
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		return view;
	}

}
