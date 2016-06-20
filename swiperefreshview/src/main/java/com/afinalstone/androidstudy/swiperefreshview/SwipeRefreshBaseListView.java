package com.afinalstone.androidstudy.swiperefreshview;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

/**
 * 上拉和下拉产生刷新动画的父类listView
 * @author SHI
 * 2016年5月13日 10:28:11
 */
public abstract class SwipeRefreshBaseListView<T extends ListView> extends
		SwipeRefreshLayout implements OnRefreshListener {

	private T listView;
	private OnSwipeRefreshViewListener onRefrushScrollViewListener;
	/** 当前刷新事件是否是底部触发的 **/
	private boolean currentPositionTypeIsBottom;

	public SwipeRefreshBaseListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context, attrs);
	}

	public SwipeRefreshBaseListView(Context context) {
		super(context);
		initView(context);
	}
	

	private void initView(Context context, AttributeSet attrs) {
		setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
				Color.CYAN, 0xFFFE5D14, Color.MAGENTA);
		listView =  initListView(context, attrs);
		addView(listView);
		currentPositionTypeIsBottom = false;
	}

	private void initView(Context context) {
		setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
				Color.CYAN, 0xFFFE5D14, Color.MAGENTA);
		listView = initListView(context);
		addView(listView);
		currentPositionTypeIsBottom = false;
	}
	
	/**把需要上拉，和下拉刷新的控件初始化并添加到SwipeRefreshLayout中**/
	public abstract T initListView(Context context,AttributeSet attrs);
	
	/**把需要上拉，和下拉刷新的控件初始化并添加到SwipeRefreshLayout中**/
	public abstract T initListView(Context context);

	/**
	 * 设置当前刷新状态监听者  支持上拉加载更多数据下拉刷新
	 *
	 * @param listener
	 */
	public void setOnRefreshListener(OnSwipeRefreshViewListener listener) {
		this.onRefrushScrollViewListener = listener;
		listView.setOnScrollListener(new ListViewScrollListener());
		setOnRefreshListener(this);
	}

	/** 开启刷新状态 **/
	public void openRefreshState() {
		post(new Runnable() {
			@Override
			public void run() {
				setRefreshing(true);
				onRefresh();
			}
		});
	}

	/** 关闭刷新状态 **/
	public void closeRefreshState() {
		setRefreshing(false);
	}

	/** 获取当前ItemView,方便以后对自己添加近来的界面进行操作 **/
	public T getListView() {
		return listView;
	}

	@Override
	public void onRefresh() {
		if (currentPositionTypeIsBottom) {
			if (onRefrushScrollViewListener != null) {
				onRefrushScrollViewListener.onBottomRefrushListener();
			}
			currentPositionTypeIsBottom = false;
		} else {
			if (onRefrushScrollViewListener != null) {
				onRefrushScrollViewListener.onTopRefrushListener();
			}
		}
	}

	private class ListViewScrollListener implements OnScrollListener {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			switch (scrollState) {
			// 当不滚动时
			case OnScrollListener.SCROLL_STATE_IDLE:
				// 判断滚动到底部
				if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
               	 currentPositionTypeIsBottom = true;
               	 openRefreshState();
				}
				break;
			}
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {

		}

	}
}
