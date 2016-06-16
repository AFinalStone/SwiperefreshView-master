package com.afinalstone.androidstudy.swiperefreshview;




import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 上拉和下拉产生刷新动画的ListView
 * @author SHI
 * 2016年3月23日 14:12:51
 */  
public class SwipeRefreshListView extends SwipeRefreshBaseListView<ListView>{

	
	public SwipeRefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SwipeRefreshListView(Context context) {
		super(context);
	}

	@Override
	public ListView initListView(Context context, AttributeSet attrs) {
		return new ListView(context,attrs);
	}

	@Override
	public ListView initListView(Context context) {
		return new ListView(context);
	}

}
