package com.itheima45.holder;

import com.itheima45.R;
import com.itheima45.adapter.MyBaseAdapter;
import com.itheima45.application.utils.UIUtils;
import com.itheima45.application.utils.ViewUtils;

import android.view.View;
import android.widget.RelativeLayout;

public class MoreHolder extends BaseHolder<Integer> {

	/**
	 * 表示加载成功的数据类型
	 * 表示加载成功了。但是服务器已经没有了更多的数据类型了
	 * 表示加载失败的数据类型
	 */
	
	//表示有更多的数据类型
	public static int HAS_MORE = 1;
	//表示没有更多的数据类型
	public static int NO_MORE = 2;
	//表示错误的数据类型
	public static int ERROR = 3;
	private View view;
	private RelativeLayout rl_more_loading;
	private RelativeLayout rl_more_error;
	

    private MyBaseAdapter adapter = null;

	public MoreHolder(MyBaseAdapter adapter, boolean hasmore) {
		this.adapter = adapter;
		setData(hasmore ? HAS_MORE : NO_MORE);
	}



	@Override
	public void refreshView() {
		Integer data = getData();
		rl_more_loading.setVisibility(data == HAS_MORE ? View.VISIBLE : View.GONE);
		rl_more_error.setVisibility(data == ERROR ? View.VISIBLE : View.GONE);
	}

	@Override
	public View initView() {
		view = UIUtils.inflate(R.layout.list_more_loading);
		//加载数据的view
		
		rl_more_loading = (RelativeLayout) view.findViewById(R.id.rl_more_loading);
		//加载失败的view
		
		rl_more_error = (RelativeLayout) view.findViewById(R.id.rl_more_error);
		
		return view;
	}
	/**
	 * 如果当前的数据表示加载更多的数据。那么就需要去加载更多的数据
	 */
	@Override
	public View getRootView() {
		if(getData() == HAS_MORE){
			loadMore();
		}
		return super.getRootView();
	}



	private void loadMore() {
		adapter.loadMore();
		
	}

}
