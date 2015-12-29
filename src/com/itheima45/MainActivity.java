package com.itheima45;

import com.itheima45.application.utils.UIUtils;
import com.itheima45.fragment.AppFragment;
import com.itheima45.fragment.BaseFragment;
import com.itheima45.fragment.CategoryFragment;
import com.itheima45.fragment.FragmentFactory;
import com.itheima45.fragment.GameFragment;
import com.itheima45.fragment.HomeFragment;
import com.itheima45.fragment.HotFragment;
import com.itheima45.fragment.RecommendedFragment;
import com.itheima45.fragment.SubjectFragment;
import com.mwqi.ui.widget.PagerTab;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends BaseActivity implements OnPageChangeListener {

	private PagerTab tabs;
	private ViewPager pager;
	private DrawerLayout drawer_layout;
	private ActionBarDrawerToggle mActionBarDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	/**
	 * 初始化actionbar
	 */
	@Override
	protected void initActionbar() {
		// 为了向下兼容低版本的手机。所以必须使用扩展包里面的actionbar
		// ActionBar actionBar = getActionBar();

		ActionBar mActionBar = getSupportActionBar();
		// 设置title的名字
		mActionBar.setTitle(R.string.app_name);
		// 设置actionbar的点击事件
		mActionBar.setDisplayShowHomeEnabled(true);
		mActionBar.setHomeButtonEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(true);
		/**
		 * 第一个参数表示activity
		 * 
		 * 第二个参数接收的滑动菜单的对象
		 * 
		 * 第三个参数接收滑动菜单的图片
		 * 
		 * 第四个和第五个参数表示打开的文字和关闭的文字
		 */
		
		mActionBarDrawerToggle = new ActionBarDrawerToggle(
				this, drawer_layout, R.drawable.ic_drawer_am, R.string.drawer_open, R.string.drawer_close);
		//让滑动开关和滑动菜单关联到一起
		mActionBarDrawerToggle.syncState();

	}
	
	
	/**
	 * 如果想实现滑动菜单可以滑动。必须实现如下方法
	 */

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return mActionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
	}

	@Override
	protected void initView() {
		setContentView(R.layout.activity_main);
		// 滑动菜单

		drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);

		MyDrawerListener listener = new MyDrawerListener();

		// 设置滑动菜单的监听器
		drawer_layout.setDrawerListener(listener);

		// 初始化横着滚动的title
		tabs = (PagerTab) findViewById(R.id.tabs);
		// 初始化viewpager

		pager = (ViewPager) findViewById(R.id.pager);
		// 初始化适配器。传入getFragmentManager() 由于这个是高版本的方法。没办法适配
		ViewPagerAdapter adapter = new ViewPagerAdapter(
				getSupportFragmentManager());

		pager.setAdapter(adapter);
		// 横着滚动的title和下面的fragment绑定到一起
		tabs.setViewPager(pager);
		// 设置fragment左右滑动的监听
		tabs.setOnPageChangeListener(this);
	}
    /**
     * 滑动开关的监听器
     * @author Administrator
     *
     */
	private class MyDrawerListener implements DrawerListener {

		@Override
		public void onDrawerClosed(View arg0) {
			mActionBarDrawerToggle.onDrawerClosed(arg0);

		}

		@Override
		public void onDrawerOpened(View arg0) {
			mActionBarDrawerToggle.onDrawerOpened(arg0);

		}

		@Override
		public void onDrawerSlide(View arg0, float arg1) {
			mActionBarDrawerToggle.onDrawerSlide(arg0, arg1);

		}

		@Override
		public void onDrawerStateChanged(int arg0) {
			mActionBarDrawerToggle.onDrawerStateChanged(arg0);

		}

	}

	private class ViewPagerAdapter extends FragmentStatePagerAdapter {
		// title的名字
		private String[] tab_names;

		public ViewPagerAdapter(FragmentManager fm) {
			super(fm);
			tab_names = UIUtils.getStringArray(R.array.tab_names);
		}

		/**
		 * 如果想实现title的功能。必须实现这个方法
		 */
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return tab_names[position];
		}

		@Override
		public Fragment getItem(int position) {
			return FragmentFactory.createFragment(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return tab_names.length;
		}

	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	/**
	 * 当view选中的时候。就会回调这个方法
	 */
	@Override
	public void onPageSelected(int position) {
		BaseFragment fragment = FragmentFactory.createFragment(position);
		fragment.show();
	}

}
