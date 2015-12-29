package com.itheima45.fragment;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.itheima45.adapter.MyBaseAdapter;
import com.itheima45.application.utils.UIUtils;
import com.itheima45.bean.CategoryInfo;
import com.itheima45.holder.BaseHolder;
import com.itheima45.holder.CategoryHolder;
import com.itheima45.holder.CategoryTitleHolder;
import com.itheima45.protocol.CategoryProtocol;
import com.mwqi.ui.widget.LoadingPage.LoadResult;

public class CategoryFragment extends BaseFragment {

	private List<CategoryInfo> mDatas;

	@Override
	protected LoadResult load() {
		CategoryProtocol protocol = new CategoryProtocol();
		mDatas = protocol.load(0);
		return check(mDatas);
	}

	@Override
	protected View createLoadedView() {
		ListView mListView = new ListView(UIUtils.getContext());
		CategoryAdapter adapter = new CategoryAdapter(mDatas);
		mListView.setAdapter(adapter);
		return mListView;
	}

	private class CategoryAdapter extends MyBaseAdapter<CategoryInfo> {

		public CategoryAdapter(List<CategoryInfo> lists) {
			super(lists);
			// TODO Auto-generated constructor stub
		}

		int position = 0;

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			this.position = position;
			return super.getView(position, convertView, parent);
		}

		@Override
		public BaseHolder getHolder() {
			if (getData().get(position).isTitle()) {
				return new CategoryTitleHolder();
			} else {
				return new CategoryHolder();
			}
		}
		
		
		

		@Override
		public int getInnerItemViewType(int position) {
			if(getData().get(position).isTitle()){
				return super.getInnerItemViewType(position) + 1;
			}else{
				return super.getInnerItemViewType(position);
			}
			
		}

		@Override
		public int getViewTypeCount() {
			// TODO Auto-generated method stub
			return super.getViewTypeCount() + 1;
		}

		@Override
		protected List onLoadmore() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasmore() {
			// TODO Auto-generated method stub
			return false;
		}
	}

}
