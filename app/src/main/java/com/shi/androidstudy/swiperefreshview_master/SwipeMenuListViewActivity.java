package com.shi.androidstudy.swiperefreshview_master;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.afinalstone.androidstudy.menulistview.Menu;
import com.afinalstone.androidstudy.menulistview.MenuCreator;
import com.afinalstone.androidstudy.menulistview.MenuItem;
import com.afinalstone.androidstudy.menulistview.MenuListView;
import com.afinalstone.androidstudy.swiperefreshview.OnSwipeRefreshViewListener;
import com.afinalstone.androidstudy.swiperefreshview.SwipeRefreshMenuListView;

public class SwipeMenuListViewActivity extends AppCompatActivity implements OnSwipeRefreshViewListener{

    private SwipeRefreshMenuListView swipeRefreshMenuListView;
    private int[] imagesUrl = {R.mipmap.item01,R.mipmap.item02,R.mipmap.item03
            ,R.mipmap.item02,R.mipmap.item01,R.mipmap.item03,R.mipmap.item02,R.mipmap.item03};
    private MyAdapter adapter = new MyAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_menulistview);
        swipeRefreshMenuListView = (SwipeRefreshMenuListView) findViewById(R.id.listview);
        swipeRefreshMenuListView.getListView().setAdapter(adapter);
        swipeRefreshMenuListView.setOnRefreshListener(this);
        // step 1. create a MenuCreator
        MenuCreator creator = new MenuCreator() {

            @Override
            public void create(Menu menu) {
                MenuItem openItem = new MenuItem(SwipeMenuListViewActivity.this);
//                openItem.setBackground();
                openItem.setWidth(120);
                openItem.setTitle("点击按钮");
                openItem.setTitleSize(14);
                openItem.setIcon(R.mipmap.ic_launcher);
                openItem.setTitleColor(Color.RED);
                menu.addMenuItem(openItem);

            }
        };
        swipeRefreshMenuListView.getListView().setMenuCreator(creator);
        swipeRefreshMenuListView.getListView().setOnMenuItemClickListener(
                new MenuListView.OnMenuItemClickListener() {
                    @Override
                    public void onMenuItemClick(int position, Menu menu, int index) {
                        switch (index) {
                            case 0:
                                Toast.makeText(SwipeMenuListViewActivity.this,"按钮被点击",Toast.LENGTH_LONG).show();
                                break;
                        }
                    }

                });

        swipeRefreshMenuListView.openRefreshState();
    }

    @Override
    public void onTopRefrushListener() {
        Toast.makeText(SwipeMenuListViewActivity.this,"顶部刷新被执行",Toast.LENGTH_SHORT).show();
        swipeRefreshMenuListView.postDelayed(new Runnable() {
            @Override
            public void run() {

                swipeRefreshMenuListView.closeRefreshState();
            }
        },5000);
    }

    @Override
    public void onBottomRefrushListener() {
        Toast.makeText(SwipeMenuListViewActivity.this,"底部刷新被执行",Toast.LENGTH_SHORT).show();
        swipeRefreshMenuListView.postDelayed(new Runnable() {
            @Override
            public void run() {

                swipeRefreshMenuListView.closeRefreshState();
            }
        },5000);
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return imagesUrl.length;
        }

        @Override
        public Object getItem(int position) {
            return imagesUrl[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(SwipeMenuListViewActivity.this);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setLayoutParams(new AbsListView.LayoutParams(150,150));
            imageView.setImageResource(imagesUrl[position]);
            return imageView;
        }
    }
}
