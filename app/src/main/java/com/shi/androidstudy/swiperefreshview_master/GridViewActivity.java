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
import com.afinalstone.androidstudy.swiperefreshview.SwipeRefreshGridView;

public class GridViewActivity extends AppCompatActivity implements OnSwipeRefreshViewListener{
    private SwipeRefreshGridView gridview;
    private int[] imagesUrl = {R.mipmap.item01,R.mipmap.item02,R.mipmap.item03
            ,R.mipmap.item02,R.mipmap.item01,R.mipmap.item03,R.mipmap.item02,R.mipmap.item03,
            R.mipmap.item02,R.mipmap.item01,R.mipmap.item03};
    private MyAdapter adapter = new MyAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);
        gridview = (SwipeRefreshGridView) findViewById(R.id.gridview);
        gridview.getGridView().setAdapter(adapter);
        gridview.setOnRefreshListener(this);
        gridview.openRefreshState();
    }

    @Override
    public void onTopRefrushListener() {
        Toast.makeText(GridViewActivity.this,"顶部刷新被执行",Toast.LENGTH_SHORT).show();
        gridview.postDelayed(new Runnable() {
            @Override
            public void run() {
                gridview.closeRefreshState();
            }
        },5000);
    }

    @Override
    public void onBottomRefrushListener() {
        Toast.makeText(GridViewActivity.this,"底部刷新被执行",Toast.LENGTH_SHORT).show();
        gridview.postDelayed(new Runnable() {
            @Override
            public void run() {
                gridview.closeRefreshState();
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
            ImageView imageView = new ImageView(GridViewActivity.this);
            imageView.setLayoutParams(new AbsListView.LayoutParams(180,180));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setImageResource(imagesUrl[position]);
            return imageView;
        }
    }
}
