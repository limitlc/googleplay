package com.mwqi.ui.widget;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

import com.itheima45.R;
import com.itheima45.application.utils.UIUtils;
import com.itheima45.manager.ThreadManager;

/**
 * ============================================================
 * 
 * 版 权 ： 黑马程序员教育集团 版权所有 (c) 2014
 * 
 * 作 者 : 马伟奇
 * 
 * 版 本 ： 1.0
 * 
 * 创建日期 ： 2014-12-19 下午2:40:13
 * 
 * 描 述 ：
 * 
 * 加载数据展示界面 修订历史 ：
 * 
 * ============================================================
 **/
public abstract class LoadingPage extends FrameLayout {

	private static final int STATE_UNLOADED = 0;// 加载默认的状态
	private static final int STATE_LOADING = 1;// 加载的状态
	private static final int STATE_ERROR = 3;/// 加载失败的状态
	private static final int STATE_EMPTY = 4;// 加载成功了。但是没有数据。
	private static final int STATE_SUCCEED = 5;// 加载成功的状态

	private View mLoadingView;//加载时显示的View
	private View mErrorView;//加载出错显示的View
	private View mEmptyView;//加载没有数据显示的View
	private View mSucceedView;//加载成功显示的View

	private int mState;//当前的状态，显示需要根据该状态判断

	public LoadingPage(Context context) {
		super(context);
		init();
	}

	private void init() {
		// 第一次进来先给一个默认的状态
		mState = STATE_UNLOADED;
		// 创建一个加载的页面
		mLoadingView = createLoadingView();
		if (null != mLoadingView) {
			addView(mLoadingView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}
		// 创建一个错误的页面
		mErrorView = createErrorView();
		if (null != mErrorView) {
			addView(mErrorView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}
		// 创建一个空的页面
		mEmptyView = createEmptyView();
		if (null != mEmptyView) {
			addView(mEmptyView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}
		showPageSafe();
	}

	private void showPageSafe() {
		UIUtils.runInMainThread(new Runnable() {
			@Override
			public void run() {
				showPage();
			}
		});
	}
    /**
     * 根据状态来展示具体的某一个界面
     */
	private void showPage() {
		if (null != mLoadingView) {
			mLoadingView.setVisibility(mState == STATE_UNLOADED || mState == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
		}
		if (null != mErrorView) {
			mErrorView.setVisibility(mState == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
		}
		if (null != mEmptyView) {
			mEmptyView.setVisibility(mState == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
		}

		if (mState == STATE_SUCCEED && mSucceedView == null) {
			mSucceedView = createLoadedView();
			addView(mSucceedView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}

		if (null != mSucceedView) {
			mSucceedView.setVisibility(mState == STATE_SUCCEED ? View.VISIBLE : View.INVISIBLE);
		}
	}



	public void show() {
		if (mState == STATE_ERROR || mState == STATE_EMPTY) {
			mState = STATE_UNLOADED;
		}
		if (mState == STATE_UNLOADED) {
			mState = STATE_LOADING;
			TaskRunnable task = new TaskRunnable();
			ThreadManager.getLongPool().execute(task);
		}
		showPageSafe();
	}

	protected View createLoadingView() {
		return UIUtils.inflate(R.layout.loading_page_loading);
	}

	protected View createEmptyView() {
		return UIUtils.inflate(R.layout.loading_page_empty);
	}

	protected View createErrorView() {
		View view = UIUtils.inflate(R.layout.loading_page_error);
		return view;
	}

	public abstract View createLoadedView();

	public abstract LoadResult load();

	class TaskRunnable implements Runnable {
		@Override
		public void run() {
			final LoadResult loadResult = load();
			UIUtils.runInMainThread(new Runnable() {
				@Override
				public void run() {
					mState = loadResult.getValue();
					showPage();
				}
			});
		}
	}

	public enum LoadResult {
		ERROR(3), EMPTY(4), SUCCEED(5);
		int value;

		LoadResult(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

}
