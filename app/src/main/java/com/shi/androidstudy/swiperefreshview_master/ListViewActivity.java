package com.shi.androidstudy.swiperefreshview_master;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.afinalstone.androidstudy.swiperefreshview.OnSwipeRefreshViewListener;
import com.afinalstone.androidstudy.swiperefreshview.SwipeRefreshListView;

public class ListViewActivity extends AppCompatActivity implements OnSwipeRefreshViewListener {

    private SwipeRefreshListView listView;
    private int[] imagesUrl = {R.mipmap.item01,R.mipmap.item02,R.mipmap.item03
            ,R.mipmap.item02,R.mipmap.item01,R.mipmap.item03,R.mipmap.item02,R.mipmap.item03};
    private MyAdapter adapter = new MyAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        listView = (SwipeRefreshListView) findViewById(R.id.listView);
        listView.getListView().setAdapter(adapter);
        listView.setOnRefreshListener(this);
        listView.openRefreshState();
    }

    @Override
    public void onTopRefrushListener() {
        Toast.makeText(ListViewActivity.this,"顶部刷新被执行",Toast.LENGTH_SHORT).show();
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {

                listView.closeRefreshState();
            }
        },5000);
    }

    @Override
    public void onBottomRefrushListener() {
        Toast.makeText(ListViewActivity.this,"底部刷新被执行",Toast.LENGTH_SHORT).show();
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {

                listView.closeRefreshState();
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
            ImageView imageView = new ImageView(ListViewActivity.this);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,150));
            imageView.setImageResource(imagesUrl[position]);
            return imageView;
        }
    }
}
