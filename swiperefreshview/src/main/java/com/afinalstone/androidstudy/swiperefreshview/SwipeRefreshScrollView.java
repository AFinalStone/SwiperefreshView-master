package com.afinalstone.androidstudy.swiperefreshview;


import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
/**
 * 上拉和下拉产生刷新动画的ScrollView
 * @author SHI
 * 2016年5月13日 10:28:11
 */
public class SwipeRefreshScrollView extends SwipeRefreshLayout implements OnRefreshListener {
	private ScrollView scrollView;
	private OnSwipeRefreshViewListener onRefrushScrollViewListener;
	/**当前刷新事件是否是底部触发的 **/
	private boolean currentPositionTypeIsBottom = false;

	public SwipeRefreshScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context,attrs);
	}
	

	public SwipeRefreshScrollView(Context context) {
		super(context);
	}

	
	private void initView(Context context,AttributeSet attrs){
		scrollView = new ScrollView(context);
		scrollView.setFillViewport(true);
		setColorSchemeColors( Color.RED
				,Color.GREEN
				,Color.BLUE
				,Color.YELLOW
				,Color.CYAN
				,0xFFFE5D14
				,Color.MAGENTA);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
			//TODO
			View view = getChildAt(1);
			if(view != null){
//				Log.i("测试", "被执行");
				removeView(view);
				scrollView.addView(view);
				addView(scrollView);
			}
	}
	
	
	/**
	 * 设置当前刷新状态监听者
	 */
	public void setOnRefreshListener(OnSwipeRefreshViewListener listener){
		this.onRefrushScrollViewListener = listener;
		setOnRefreshListener(this);
		scrollView.setOnTouchListener(new TouchListenerImpl());
	}
	
	/**开启刷新状态**/
	public void openRefreshState(){
		post(new Runnable() {
			@Override
			public void run() {
				setRefreshing(true);
				onRefresh();
			}
		});
	}
	
	/**关闭刷新状态**/
	public void closeRefreshState(){
		setRefreshing(false);
	}
	
	@Override
	public void onRefresh() {
//		Log.i("DownRefrushBaseView", "onRefresh被执行");
		if(currentPositionTypeIsBottom){
			if(onRefrushScrollViewListener != null){
				onRefrushScrollViewListener.onBottomRefrushListener();
			}
			currentPositionTypeIsBottom = false;
		}else{
			if(onRefrushScrollViewListener != null){
				onRefrushScrollViewListener.onTopRefrushListener();
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
                	 currentPositionTypeIsBottom = true;
                	 openRefreshState();
                 }
                break;
 
            default:
                break;
            }
            return false;
        }
         
    };
}
