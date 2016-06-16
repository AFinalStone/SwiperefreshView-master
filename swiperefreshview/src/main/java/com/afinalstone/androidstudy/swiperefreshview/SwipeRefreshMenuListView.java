package com.afinalstone.androidstudy.swiperefreshview;


import com.afinalstone.androidstudy.swipemenulistview.SwipeMenuListView;

import android.content.Context;
import android.util.AttributeSet;

/**
 * 上拉和下拉产生刷新动画   并且侧滑弹出额外按钮ListView
 * @author SHI
 * 2016年5月13日 10:26:23
 */
public  class SwipeRefreshMenuListView extends SwipeRefreshBaseListView<SwipeMenuListView>{

	public SwipeRefreshMenuListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SwipeRefreshMenuListView(Context context) {
		super(context);
	}

	@Override
	public SwipeMenuListView initListView(Context context, AttributeSet attrs) {
		return new SwipeMenuListView(context,attrs);
	}

	@Override
	public SwipeMenuListView initListView(Context context) {
		return new SwipeMenuListView(context);
	}

}
