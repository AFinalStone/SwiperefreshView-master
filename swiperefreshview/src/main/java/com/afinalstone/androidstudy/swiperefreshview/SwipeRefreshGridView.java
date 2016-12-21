package com.afinalstone.androidstudy.swiperefreshview;




import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.AttributeSet;

/**
 * 上拉和下拉产生刷新动画的GridView
 * @author SHI
 * 2016年3月23日 14:12:51
 */
public class SwipeRefreshGridView extends SwipeRefreshBaseGridView<ScrollGridView> implements OnRefreshListener {


	public SwipeRefreshGridView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	public SwipeRefreshGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SwipeRefreshGridView(Context context) {
		super(context);
	}

	@Override
	public ScrollGridView initItemView(Context context, AttributeSet attrs) {
		return new ScrollGridView(context, attrs);
	}
	
}
