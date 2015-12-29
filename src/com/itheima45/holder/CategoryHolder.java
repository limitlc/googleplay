package com.itheima45.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima45.R;
import com.itheima45.application.utils.StringUtils;
import com.itheima45.application.utils.UIUtils;
import com.itheima45.bean.CategoryInfo;
import com.itheima45.imageload.ImageLoader;

public class CategoryHolder extends BaseHolder<CategoryInfo> {

	private ImageView iv_1;
	private ImageView iv_2;
	private ImageView iv_3;
	private TextView tv_1;
	private TextView tv_2;
	private TextView tv_3;

	@Override
	public void refreshView() {
		CategoryInfo categoryInfo = getData();
		String imageUrl1 = categoryInfo.getImageUrl1();
		String imageUrl2 = categoryInfo.getImageUrl2();
		String imageUrl3 = categoryInfo.getImageUrl3();
		
		String name1 = categoryInfo.getName1();
		String name2 = categoryInfo.getName2();
		String name3 = categoryInfo.getName3();
		
		if(StringUtils.isEmpty(imageUrl1)){
			iv_1.setImageDrawable(null);
			tv_1.setText("");
		}else{
			ImageLoader.load(iv_1, imageUrl1);
			tv_1.setText(name1);
		}
		
		if(StringUtils.isEmpty(imageUrl2)){
			iv_2.setImageDrawable(null);
			tv_2.setText("");
		}else{
			ImageLoader.load(iv_2, imageUrl2);
			tv_2.setText(name2);
		}
		
		if(StringUtils.isEmpty(imageUrl3)){
			iv_3.setImageDrawable(null);
			tv_3.setText("");
		}else{
			ImageLoader.load(iv_3, imageUrl3);
			tv_3.setText(name3);
		}
	}

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.category_item);
		iv_1 = (ImageView) view.findViewById(R.id.iv_1);
		iv_2 = (ImageView) view.findViewById(R.id.iv_2);
		iv_3 = (ImageView) view.findViewById(R.id.iv_3);
		
		tv_1 = (TextView) view.findViewById(R.id.tv_1);
		tv_2 = (TextView) view.findViewById(R.id.tv_2);
		tv_3 = (TextView) view.findViewById(R.id.tv_3);
		return view;
	}

}
