package com.afinalstone.androidstudy.menulistview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

/**
 * 
 * @author SHI
 */
public class Menu {

	private Context mContext;
	private List<MenuItem> mItems;
	private int mViewType;

	public Menu(Context context) {
		mContext = context;
		mItems = new ArrayList<MenuItem>();
	}

	public Context getContext() {
		return mContext;
	}

	public void addMenuItem(MenuItem item) {
		mItems.add(item);
	}

	public void removeMenuItem(MenuItem item) {
		mItems.remove(item);
	}

	public List<MenuItem> getMenuItems() {
		return mItems;
	}

	public MenuItem getMenuItem(int index) {
		return mItems.get(index);
	}

	public int getViewType() {
		return mViewType;
	}

	public void setViewType(int viewType) {
		this.mViewType = viewType;
	}

}
