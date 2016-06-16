package com.afinalstone.androidstudy.swiperefreshview;

/**SwipeRefreshView刷新状态监听接口对象
 * 2016年5月23日 14:27:25
 * **/
public interface OnSwipeRefreshViewListener {
   	/**顶部触发刷新时调用**/
	public void onTopRefrushListener();
	/**底部触发刷新时调用**/
	public void onBottomRefrushListener();
}
