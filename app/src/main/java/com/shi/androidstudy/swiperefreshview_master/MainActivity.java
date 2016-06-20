package com.shi.androidstudy.swiperefreshview_master;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.afinalstone.androidstudy.swiperefreshview.SwipeRefreshMenuListView;

import java.util.ArrayList;
import java.util.List;


/**
 * MainActivity
 */
public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> listData =new ArrayList<String>();
    private MyAdapter adapter = new MyAdapter();

    private final String SWIPEREFRESH_LISTVIEW = "swiperefresh_listview";
    private final String SWIPEREFRESH_GRIDVIEW = "swiperefresh_gridview";
    private final String SWIPEREFRESH_SCROLLVIEW = "swiperefresh_scrollview";
    private final String MENULISTVIEW = "menulistview";
    private final String SWIPEREFRESH_MENULISTVIEW = "swiperefresh_menulistview";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        listData.add(SWIPEREFRESH_LISTVIEW);
        listData.add(SWIPEREFRESH_GRIDVIEW);
        listData.add(SWIPEREFRESH_SCROLLVIEW);
        listData.add(MENULISTVIEW);
        listData.add(SWIPEREFRESH_MENULISTVIEW);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (SWIPEREFRESH_LISTVIEW.equals(listData.get(position))){
                    Intent intent = new Intent(MainActivity.this,ListViewActivity.class);
                    startActivity(intent);
                    return;
                }
                if (SWIPEREFRESH_GRIDVIEW.equals(listData.get(position))){
                    Intent intent = new Intent(MainActivity.this, GridViewActivity.class);
                    startActivity(intent);
                    return;
                }
                if (SWIPEREFRESH_SCROLLVIEW.equals(listData.get(position))){
                    Intent intent = new Intent(MainActivity.this, ScrollViewActivity.class);
                    startActivity(intent);
                    return;
                }
                if (MENULISTVIEW.equals(listData.get(position))){
                    Intent intent = new Intent(MainActivity.this, MenuListViewActivity.class);
                    startActivity(intent);
                    return;
                }
                if (SWIPEREFRESH_MENULISTVIEW.equals(listData.get(position))){
                    Intent intent = new Intent(MainActivity.this, SwipeMenuListViewActivity.class);
                    startActivity(intent);
                    return;
                }

            }
        });
}


    private class MyAdapter extends BaseAdapter{

       @Override
       public int getCount() {
           return listData.size();
       }

       @Override
       public Object getItem(int position) {
           return listData.get(position);
       }

       @Override
       public long getItemId(int position) {
           return position;
       }

       @Override
       public View getView(int position, View convertView, ViewGroup parent) {
           TextView tv = new TextView(MainActivity.this);
           tv.setTextSize(20);
           tv.setTextColor(Color.GREEN);
           tv.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT
                   ,AbsListView.LayoutParams.WRAP_CONTENT));
           tv.setGravity(Gravity.CENTER);
           tv.setPadding(10,10,10,10);
           tv.setText(listData.get(position));
           return tv;
       }
   }
}
