package com.afinalstone.androidstudy.swiperefreshview;


import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
/**
 * 上拉和下拉产生刷新动画的父类GridView
 * @author SHI
 * 2016年5月13日 10:28:11
 */
public abstract class SwipeRefreshBaseGridView<T extends GridView> extends LinearLayout implements OnRefreshListener {
	private SwipeRefreshLayout swipeRefreshLayout;
	private ScrollView scrollView;
	private T itemView;
	private OnSwipeRefreshViewListener onRefreshScrollViewListener;
	/**当前刷新事件是否是底部触发的 **/
	private boolean currentPositionTypeIsBottom;
	/**是否开启底部刷新功能**/
	private boolean IfOpenBottomRefresh = false;

	public SwipeRefreshBaseGridView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		initView(context,attrs);
	}

	public SwipeRefreshBaseGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context,attrs);
	}
	

	public SwipeRefreshBaseGridView(Context context) {
		super(context);
	}

	/**把需要上拉，和下拉刷新的控件初始化并添加到ScrollView中**/
	public abstract T initItemView(Context context,AttributeSet attrs);
	
	private void initView(Context context,AttributeSet attrs){
 		swipeRefreshLayout = new SwipeRefreshLayout(context);
		swipeRefreshLayout.setColorSchemeColors( Color.RED
				,Color.GREEN
				,Color.BLUE
				,Color.YELLOW
				,Color.CYAN
				,0xFFFE5D14
				,Color.MAGENTA);
		scrollView = new ScrollView(context);
		scrollView.setVerticalScrollBarEnabled(false);
		scrollView.setFillViewport(true);
		//初始化ItemView并添加到scrollView中
		itemView = initItemView(context, attrs);
		ViewGroup.LayoutParams groupParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		swipeRefreshLayout.addView(scrollView,groupParams);
		scrollView.addView(itemView);
		currentPositionTypeIsBottom = false;
		LayoutParams linearParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		addView(swipeRefreshLayout, linearParams);		
	}
	
	/**设置刷新控件刷新时的颜色     默认是黑色**/
	public void setColorSchemeResources(int... arg0){
		swipeRefreshLayout.setColorSchemeResources(arg0);
	}
	
	
	/**
	 * 设置当前刷新状态监听者
	 */
	public void setOnRefreshListener(OnSwipeRefreshViewListener listener){
		this.onRefreshScrollViewListener = listener;
		swipeRefreshLayout.setOnRefreshListener(this);
		scrollView.setOnTouchListener(new TouchListenerImpl());
	}
	
	/**开启刷新状态**/
	public void openRefreshState(){
		swipeRefreshLayout.post(new Runnable() {
			@Override
			public void run() {
				swipeRefreshLayout.setRefreshing(true);
				onRefresh();
			}
		});
	}

	/** 是否开启底部刷新功能 **/
	public void IfOpenBottomRefresh(boolean ifOpenBottomRefresh) {
		IfOpenBottomRefresh = ifOpenBottomRefresh;
	}
	
	/**关闭刷新状态**/
	public void closeRefreshState(){
		swipeRefreshLayout.setRefreshing(false);
	}
	
	/**获取当前ItemView,方便以后对自己添加近来的界面进行操作**/
	public T getGridView(){
		return itemView;
	}
	
	@Override
	public void onRefresh() {
//		Log.i("DownRefrushBaseView", "onRefresh被执行");
		if(currentPositionTypeIsBottom){
			if(onRefreshScrollViewListener != null){
				onRefreshScrollViewListener.onBottomRefrushListener();
			}
			currentPositionTypeIsBottom = false;
		}else{
			if(onRefreshScrollViewListener != null){
				onRefreshScrollViewListener.onTopRefrushListener();
			}
		}
	}
	
    /**
     * Demo描述:
     * 监听ScrollView滑动到顶端和底部
     * 
     * 注意事项:
     * 1 mScrollView.getChildAt(0).getMeasuredHeight()表示:
     *   ScrollView所占的高度.即ScrollView内容的高度.常常有一
     *   部分内容要滑动后才可见,这部分的高度也包含在了
     *   mScrollView.getChildAt(0).getMeasuredHeight()中
     *   
     * 2 view.getScrollY表示:
     *   ScrollView顶端已经滑出去的高度
     *   
     * 3 view.getHeight()表示:
     *   ScrollView的可见高度
     *   
     */
    private class TouchListenerImpl implements OnTouchListener{
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
 
                break;
            case MotionEvent.ACTION_UP:
                 int scrollY=view.getScrollY();
                 int height=view.getHeight();
                 int scrollViewMeasuredHeight=scrollView.getChildAt(0).getMeasuredHeight();
                 //当前控件滑动到了底部
                 if((scrollY+height)==scrollViewMeasuredHeight){
//                	 Log.i("DownRefrushGridView", "scrollView滑动到了底部 scrollY="+scrollY);
//                	 Log.i("DownRefrushGridView", "scrollView滑动到了底部 height="+height);
//                	 Log.i("DownRefrushGridView", "scrollView滑动到了底部 scrollViewMeasuredHeight="+scrollViewMeasuredHeight);
					 if(IfOpenBottomRefresh){
						 currentPositionTypeIsBottom = true;
						 openRefreshState();
					 }

                 }
                break;
 
            default:
                break;
            }
            return false;
        }
         
    };
}
