package com.itheima45.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima45.adapter.MyBaseAdapter;
import com.itheima45.application.utils.UIUtils;
import com.itheima45.bean.AppInfo;
import com.itheima45.holder.BaseHolder;
import com.itheima45.holder.HomeHolder;
import com.itheima45.holder.HomePicHolder;
import com.itheima45.http.HttpHelper;
import com.itheima45.http.HttpHelper.HttpResult;
import com.itheima45.protocol.HomeProtocol;
import com.mwqi.ui.widget.LoadingPage.LoadResult;

public class HomeFragment extends BaseFragment {
	private List<AppInfo> mDatas;
	// 图片的url地址
	private List<String> pictureListUrls;

	// List<String> mDatas = new ArrayList<String>();
	/**
	 * 从网络加载数据
	 */
	@Override
	protected LoadResult load() {
		HomeProtocol protocol = new HomeProtocol();
		mDatas = protocol.load(0);
		pictureListUrls = protocol.getPictureUrl();
		return check(mDatas);
	}

	/**
	 * 初始化界面
	 */
	@Override
	protected View createLoadedView() {
		ListView mListView = new ListView(UIUtils.getContext());
		if (pictureListUrls != null) {
			HomePicHolder homePicHolder = new HomePicHolder();
			homePicHolder.setData(pictureListUrls);
			mListView.addHeaderView(homePicHolder.getRootView());
		}

		HomeAdapter adapter = new HomeAdapter(mDatas);
		mListView.setAdapter(adapter);
		return mListView;
	}

	private class HomeAdapter extends MyBaseAdapter<AppInfo> {

		public HomeAdapter(List<AppInfo> mDatas) {
			super(mDatas);
		}

		@Override
		public BaseHolder getHolder() {
			return new HomeHolder();
		}

		@Override
		protected List onLoadmore() {
			HomeProtocol protocol = new HomeProtocol();

			return protocol.load(getData().size());
		}

	}

	// private class HomeHolder extends BaseHolder<String> {
	// TextView tv;
	//
	// @Override
	// public void refreshView() {
	// tv.setText(getData());
	//
	// }
	//
	// @Override
	// public View initView() {
	// tv = new TextView(UIUtils.getContext());
	// return tv;
	// }
	// }

}
