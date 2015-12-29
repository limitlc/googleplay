package com.itheima45.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.itheima45.adapter.MyBaseAdapter;
import com.itheima45.application.utils.UIUtils;
import com.itheima45.bean.SubjectInfo;
import com.itheima45.holder.BaseHolder;
import com.itheima45.holder.SubjectHolder;
import com.itheima45.protocol.SubjectProtocol;
import com.mwqi.ui.widget.LoadingPage;
import com.mwqi.ui.widget.LoadingPage.LoadResult;

public class SubjectFragment extends BaseFragment {

	private List<SubjectInfo> mDatas;

	@Override
	protected LoadResult load() {
		SubjectProtocol protocol = new SubjectProtocol();
		mDatas = protocol.load(0);
		return check(mDatas);
	}

	@Override
	protected View createLoadedView() {
		ListView mListView = new ListView(UIUtils.getContext());
		SubjectAdapter adapter = new SubjectAdapter(mDatas);
		mListView.setAdapter(adapter);
		return mListView;
	}
	
	private class SubjectAdapter extends MyBaseAdapter<SubjectInfo>{

		public SubjectAdapter(List<SubjectInfo> lists) {
			super(lists);
			// TODO Auto-generated constructor stub
		}

		@Override
		public BaseHolder getHolder() {
			// TODO Auto-generated method stub
			return new SubjectHolder();
		}

		@Override
		protected List onLoadmore() {
			SubjectProtocol protocol = new SubjectProtocol();
			return protocol.load(getData().size());
		}
		
	}
	
	
}
