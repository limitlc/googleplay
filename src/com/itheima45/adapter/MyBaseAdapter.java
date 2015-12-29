package com.itheima45.adapter;

import java.util.List;

import com.itheima45.application.utils.UIUtils;
import com.itheima45.holder.BaseHolder;
import com.itheima45.holder.MoreHolder;
import com.itheima45.manager.ThreadManager;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyBaseAdapter<T> extends BaseAdapter {

	
	public List<T> mDatas;
	private BaseHolder holder;
	
	public MyBaseAdapter(List<T> lists) {
		setData(lists);
	}

	public void setData(List<T> lists) {
		this.mDatas = lists;
	}
	
	public List<T> getData(){
		return mDatas;
	}

	@Override
	public int getCount() {
		// 需要加载更多的数据 加载中。。。。。
		return mDatas.size() + 1;
	}
	//加载更多的数据类型
	private final int MORE_VIEW_TYPE = 0;
	//加载普通的数据类型
	private final int ITEM_VIEW_TYPE = 1;
	private MoreHolder moreHolder; 
    
	@Override
	public int getItemViewType(int position) {
		//当position的位置等于20的时候。那么就要出现加载更多的数据类型
		if(position == getCount() - 1){
			return MORE_VIEW_TYPE;
		}else{
			return getInnerItemViewType(position);
		}
	}

	public int getInnerItemViewType(int position) {
		// TODO Auto-generated method stub
		return ITEM_VIEW_TYPE;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return super.getViewTypeCount() + 1;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView != null && convertView.getTag() instanceof BaseHolder){
			holder = (BaseHolder) convertView.getTag();
		}else{
			//判断当前的值是否是加载更多的数据类型。如果表示更多的数据类型。那么就需要加载更多的holder也就是转圈的view
			if(MORE_VIEW_TYPE == getItemViewType(position)){
				holder = getMoreHolder();
			}else{
				holder = getHolder();
			}
		
		}
		//如果当position的位置等于前面20条数据的时候。那么就需要设置数据。第二十一条数据是表示加载更多。
		if(ITEM_VIEW_TYPE == getItemViewType(position)){
			holder.setData(mDatas.get(position));
		}
		
		return holder.getRootView();
	}
    /**
     * 
     * 如果能走到这里。默认情况下。表示有更多的数据类型
     */
	private BaseHolder getMoreHolder() {
		if(moreHolder == null){
			moreHolder = new MoreHolder(this,hasmore());
		}
	
		return moreHolder;
	}

	public boolean hasmore() {
		// TODO Auto-generated method stub
		return true;
	}

	public abstract BaseHolder getHolder();

	public boolean is_loading = false;
	/**
	 * 加载更多的数据。。
	 */
	public void loadMore() {
		if(!is_loading){
			is_loading = true;
			//开启子线程去加载服务器发送过来的数据
			ThreadManager.getLongPool().execute(new Runnable() {
				
				@Override
				public void run() {
					final List list = onLoadmore();
				
					UIUtils.runInMainThread(new Runnable() {
						
						@Override
						public void run() {
							
							if(list == null){
								getMoreHolder().setData(MoreHolder.ERROR);
							}else if(list.size() < 20){
								getMoreHolder().setData(MoreHolder.NO_MORE);
							}else{
								getMoreHolder().setData(MoreHolder.HAS_MORE);
							}
							//把从服务器返回的数据追加到之前的集合里面
							if(list!= null){
								if(mDatas !=null){
									mDatas.addAll(list);
								}else{
									setData(list);
								}
							}
							notifyDataSetChanged();
							is_loading = false;
						}
					});
				}
			});
		}
		
		
		
	}
    //加载更多
	protected abstract List onLoadmore();

	

}
