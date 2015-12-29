package com.itheima45.fragment;

import java.util.HashMap;

import android.support.v4.app.Fragment;

/**
 * ============================================================
 * 
 * 版 权 ： 黑马程序员教育集团 版权所有 (c) 2014
 * 
 * 作 者 : 马伟奇
 * 
 * 版 本 ： 1.0
 * 
 * 创建日期 ： 2014-12-19 上午11:17:40
 * 
 * 描 述 ：
 * 
 * 创建fragment的工厂 修订历史 ：
 * 
 * ============================================================
 **/
public class FragmentFactory {
	// 首页
	private static final int TAB_HOME = 0;

	// 应用
	private static final int TAB_APP = 1;

	// 游戏
	private static final int TAB_GAME = 2;

	// 专题
	private static final int TAB_SUBJECT = 3;

	// 推荐
	private static final int TAB_RECOMMENDED = 4;

	// 分类
	private static final int TAB_CATEGORY = 5;

	// 热门
	private static final int TAB_HOT = 6;
	// 设置缓存fragment的数据
	private static HashMap<Integer, BaseFragment> mFragments = new HashMap<Integer, BaseFragment>();

	public static BaseFragment createFragment(int position) {
		// 从缓存里面取出fragment的数据
		BaseFragment fragment = mFragments.get(position);

		if (fragment == null) {
			switch (position) {

			case TAB_HOME:

				fragment = new HomeFragment();
				break;

			case TAB_APP:
				fragment = new AppFragment();
				break;
			case TAB_GAME:
				fragment = new GameFragment();
				break;
			case TAB_SUBJECT:
				fragment = new SubjectFragment();
				break;
			case TAB_RECOMMENDED:
				fragment = new RecommendedFragment();
				break;
			case TAB_CATEGORY:
				fragment = new CategoryFragment();
				break;
			case TAB_HOT:
				fragment = new HotFragment();
				break;
			}
            //把fragment加入到缓存数据里面
			mFragments.put(position, fragment);
		}

		return fragment;
	}

}
