package com.itheima45.holder;

import android.view.View;

public abstract class BaseHolder<T> {

	private View view;

	public T mData;
	
	public BaseHolder() {
		view = initView();
		view.setTag(this);
	}
	
	public void setData(T data){
		this.mData = data;
		refreshView();
	}
	
	public T getData(){
		return mData;
	}
	
	public abstract void refreshView();

	public View getRootView(){
		return view;
	}

	public abstract View initView();

	
	
}
