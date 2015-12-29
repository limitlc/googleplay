package com.itheima45;

import com.itheima45.application.utils.StringUtils;
import com.itheima45.application.utils.UIUtils;
import com.itheima45.bean.AppInfo;
import com.itheima45.holder.DetaiSafeHolder;
import com.itheima45.holder.DetailInfoHolder;
import com.itheima45.holder.DetailScreenHolder;
import com.itheima45.protocol.DetailProtocol;
import com.mwqi.ui.widget.LoadingPage;
import com.mwqi.ui.widget.LoadingPage.LoadResult;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;

public class DetailActivity extends BaseActivity {

	private String packageName;
	private AppInfo appInfo;

	@Override
	protected void initActionbar() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initView() {
		LoadingPage mContentView = new LoadingPage(UIUtils.getContext()){

			@Override
			public View createLoadedView() {
				return DetailActivity.this.createLoadedView();
			}

			@Override
			public LoadResult load() {
				return DetailActivity.this.load();
			}
			
		};
		setContentView(mContentView);
		mContentView.show();
	}

	protected View createLoadedView() {
		
		View view = UIUtils.inflate(R.layout.activity_detail);
		
        //详情的基本信息界面		
		FrameLayout detail_info = (FrameLayout) view.findViewById(R.id.detail_info);
		DetailInfoHolder detailInfoHolder = new DetailInfoHolder();
		detailInfoHolder.setData(appInfo);
		detail_info.addView(detailInfoHolder.getRootView());
		
		//详情的安全界面
		FrameLayout detail_safe = (FrameLayout) view.findViewById(R.id.detail_safe);
        DetaiSafeHolder detaiSafeHolder = new DetaiSafeHolder();
        detaiSafeHolder.setData(appInfo);
        detail_safe.addView(detaiSafeHolder.getRootView());
        //横着滚动的图片
        HorizontalScrollView detail_screen = (HorizontalScrollView) view.findViewById(R.id.detail_screen);
        DetailScreenHolder screenHolder = new DetailScreenHolder();
        screenHolder.setData(appInfo);
        detail_screen.addView(screenHolder.getRootView());
		return view;
	}

	protected LoadResult load() {
		DetailProtocol protocol = new DetailProtocol();
		protocol.setPackageName(packageName);
		appInfo = protocol.load(0);
		if(appInfo == null || StringUtils.isEmpty(packageName)){
			return LoadResult.ERROR;
		}
		return LoadResult.SUCCEED;
	}

	@Override
	protected void init() {
		//获取到上一个页面传过来的包名
		Intent intent = getIntent();
        if(null != intent){
        	packageName = intent.getStringExtra("packageName");
        }
	}

}
