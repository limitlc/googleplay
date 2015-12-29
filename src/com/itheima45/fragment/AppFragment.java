package com.itheima45.fragment;

import java.util.ArrayList;
import java.util.List;

import com.itheima45.DetailActivity;
import com.itheima45.adapter.MyBaseAdapter;
import com.itheima45.application.utils.UIUtils;
import com.itheima45.bean.AppInfo;
import com.itheima45.holder.AppHolder;
import com.itheima45.holder.BaseHolder;
import com.itheima45.holder.HomePicHolder;
import com.itheima45.protocol.AppProtocol;
import com.mwqi.ui.widget.LoadingPage;
import com.mwqi.ui.widget.LoadingPage.LoadResult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class AppFragment extends BaseFragment {

	private List<AppInfo> mDatas;

	@Override
	protected LoadResult load() {
		AppProtocol protocol = new AppProtocol();
		mDatas = protocol.load(0);
		return check(mDatas);
	}

	@Override
	protected View createLoadedView() {
		ListView mListView = new ListView(UIUtils.getContext());
		
		AppAdapter adapter = new AppAdapter(mListView, mDatas);
		mListView.setAdapter(adapter);
		return mListView;
	}

	private class AppAdapter extends MyBaseAdapter<AppInfo> implements
			OnItemClickListener {

		public AppAdapter(ListView mListView, List<AppInfo> lists) {
			super(lists);
			mListView.setOnItemClickListener(this);
		}

		@Override
		public BaseHolder getHolder() {
			// TODO Auto-generated method stub
			return new AppHolder();
		}

		@Override
		protected List onLoadmore() {
			AppProtocol protocol = new AppProtocol();
			return protocol.load(getData().size());
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// 获取到app的唯一值.包名
			AppInfo appInfo = getData().get(position);
			String packageName = appInfo.getPackageName();

			Intent intent = new Intent(UIUtils.getContext(),
					DetailActivity.class);
			intent.putExtra("packageName", packageName);

			UIUtils.startActivity(intent);
		}

	}
}
