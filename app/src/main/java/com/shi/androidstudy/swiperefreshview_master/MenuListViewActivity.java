package com.shi.androidstudy.swiperefreshview_master;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MenuListViewActivity extends AppCompatActivity{

    private MenuListView menuListView;
    private int[] imagesUrl = {R.mipmap.item03,R.mipmap.item02,R.mipmap.item03
            ,R.mipmap.item02,R.mipmap.item01,R.mipmap.item03,R.mipmap.item02,R.mipmap.item03};
    private MyAdapter adapter = new MyAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_listview);
        menuListView = (MenuListView) findViewById(R.id.listview);
        menuListView.setAdapter(adapter);
        MenuCreator creator = new MenuCreator() {

            @Override
            public void create(Menu menu) {
                MenuItem openItem = new MenuItem(MenuListViewActivity.this);
                openItem.setBackground(android.R.color.holo_orange_dark);
                openItem.setWidth(120);
                openItem.setTitle("点击按钮");
                openItem.setTitleSize(14);
                openItem.setTitleColor(Color.RED);
                menu.addMenuItem(openItem);

            }
        };
        menuListView.setMenuCreator(creator);
        menuListView.setOnMenuItemClickListener(
                new MenuListView.OnMenuItemClickListener() {
                    @Override
                    public void onMenuItemClick(int position, Menu menu, int index) {
                        switch (index) {
                            case 0:
                                Toast.makeText(MenuListViewActivity.this,"按钮被点击",Toast.LENGTH_LONG).show();
                                break;
                        }
                    }

                });
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
            ImageView imageView = new ImageView(MenuListViewActivity.this);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setLayoutParams(new AbsListView.LayoutParams(150,150));
            imageView.setImageResource(imagesUrl[position]);
            return imageView;
        }
    }
}
