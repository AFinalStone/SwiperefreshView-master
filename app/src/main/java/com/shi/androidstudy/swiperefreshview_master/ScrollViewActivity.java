package com.shi.androidstudy.swiperefreshview_master;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.afinalstone.androidstudy.swiperefreshview.OnSwipeRefreshViewListener;
import com.afinalstone.androidstudy.swiperefreshview.SwipeRefreshScrollView;

public class ScrollViewActivity extends AppCompatActivity implements OnSwipeRefreshViewListener{

    private SwipeRefreshScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        scrollView = (SwipeRefreshScrollView) findViewById(R.id.scrollView);
        scrollView.setOnRefreshListener(this);
    }

    @Override
    public void onTopRefrushListener() {
        Toast.makeText(ScrollViewActivity.this,"顶部刷新被执行",Toast.LENGTH_SHORT).show();
        scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.closeRefreshState();
            }
        },5000);
    }

    @Override
    public void onBottomRefrushListener() {
        Toast.makeText(ScrollViewActivity.this,"底部刷新被执行",Toast.LENGTH_SHORT).show();
        scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {

                scrollView.closeRefreshState();
            }
        },5000);
    }
}
