package com.itheima45.fragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.itheima45.application.utils.UIUtils;
import com.mwqi.ui.widget.LoadingPage;
import com.mwqi.ui.widget.LoadingPage.LoadResult;

public abstract class BaseFragment extends Fragment {

	private LoadingPage mContentView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//初始化内容界面
		mContentView = new LoadingPage(UIUtils.getContext()) {
			
			@Override
			public LoadResult load() {
				// TODO Auto-generated method stub
				return BaseFragment.this.load();
			}
			
			@Override
			public View createLoadedView() {
				
				return BaseFragment.this.createLoadedView();
			}
		};
		return mContentView;
	}

	protected LoadResult check(Object obj) {
		if(obj == null){
			return LoadResult.ERROR;
		}
		if(obj instanceof List){
			List list = (List) obj;
			if(list.size() == 0){
				return LoadResult.EMPTY;
			}
		}
		return LoadResult.SUCCEED;
	}
	
	protected abstract LoadResult load();

	protected abstract View createLoadedView();

	public void show() {
		if(mContentView != null){
			mContentView.show();
		}
		
	}
}
