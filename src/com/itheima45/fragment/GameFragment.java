package com.itheima45.fragment;

import java.util.List;

import android.view.View;
import android.widget.ListView;

import com.itheima45.adapter.MyBaseAdapter;
import com.itheima45.application.utils.UIUtils;
import com.itheima45.bean.AppInfo;
import com.itheima45.holder.BaseHolder;
import com.itheima45.holder.GameHolder;
import com.itheima45.protocol.GameProtocol;
import com.mwqi.ui.widget.LoadingPage.LoadResult;

public class GameFragment extends BaseFragment {

	private List<AppInfo> mDatas;

	@Override
	protected LoadResult load() {
		GameProtocol protocol = new GameProtocol();
		mDatas = protocol.load(0);
		return check(mDatas);
	}

	@Override
	protected View createLoadedView() {
		ListView mListiView = new ListView(UIUtils.getContext());
		GameAdapter adapter = new GameAdapter(mDatas);
		mListiView.setAdapter(adapter);
		return mListiView;
	}
	
	private class GameAdapter extends MyBaseAdapter<AppInfo>{

		public GameAdapter(List<AppInfo> lists) {
			super(lists);
			// TODO Auto-generated constructor stub
		}

		@Override
		public BaseHolder getHolder() {
			// TODO Auto-generated method stub
			return new GameHolder();
		}

		@Override
		protected List onLoadmore() {
			GameProtocol protocol = new GameProtocol();
			return protocol.load(getData().size());
		}
		
	}
	
	
}
