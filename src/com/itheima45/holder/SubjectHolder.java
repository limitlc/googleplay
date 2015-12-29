package com.itheima45.holder;

import com.itheima45.R;
import com.itheima45.application.utils.UIUtils;
import com.itheima45.bean.SubjectInfo;
import com.itheima45.imageload.ImageLoader;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SubjectHolder extends BaseHolder<SubjectInfo> {

	private ImageView item_icon;
	private TextView item_txt;

	@Override
	public void refreshView() {
		SubjectInfo subjectInfo = getData();
		ImageLoader.load(item_icon, subjectInfo.getUrl());
		item_txt.setText(subjectInfo.getDes());
	}

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.subject_item);
		item_icon = (ImageView) view.findViewById(R.id.item_icon);
		item_txt = (TextView) view.findViewById(R.id.item_txt);
		return view;
	}

}
