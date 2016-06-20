##效果展示
一些简单实用的自定义刷新控件:<br>
1、上拉加载下拉刷新ListView控件：SwipeRefreshListView<br>
![listview](https://github.com/AFinalStone/SwiperefreshView-master/blob/master/screenshot/listview.gif)<br>
2、上拉加载下拉刷新GridView控件：SwipeRefreshGridView<br>
![gridview](https://github.com/AFinalStone/SwiperefreshView-master/blob/master/screenshot/gridview.gif)<br>
3、上拉加载下拉刷新ScrollView控件：SwipeRefreshScrollView<br>
![scrollview](https://github.com/AFinalStone/SwiperefreshView-master/blob/master/screenshot/scrollview.gif)<br>
4、侧滑弹出菜单按钮的ListView控件：MuneListView<br>
![menu_listview](https://github.com/AFinalStone/SwiperefreshView-master/blob/master/screenshot/menu_listview.gif)<br>
5、上拉加载下拉刷新且侧滑可弹出菜单按钮的ListView控件：SwipeRefreshMuneListView<br>
![swipe_menu_listview](https://github.com/AFinalStone/SwiperefreshView-master/blob/master/screenshot/swipe_menu_listview.gif)

##导入项目

* Android Studio<br>
	```

    compile 'com.afinalstone:SwiperefreshView:1.0.4'

	```
* Eclipse<br>

    下载最新jar包:swiprefreshview-1.0.1.jar:<br>
    https://github.com/AFinalStone/SwiperefreshView-master/blob/master/jar/swiprefreshview-1.0.1.jar?raw=true

    注：需要同时导入最新的android-support-v4.jar:<br>
    https://github.com/AFinalStone/SwiperefreshView-master/blob/master/jar/android-support-v4.jar?raw=true

##使用方法:

简要说举例说一下SwipeRefreshListView的使用方法：<br>

1.首先是布局文件中：<br>

```java

<com.afinalstone.androidstudy.swiperefreshview.SwipeRefreshListView
     android:id="@+id/listview"
     android:layout_width="match_parent"
     android:layout_height="wrap_content" />

```
2.然后是Activity代码内容：<br>

```java

public class ListViewActivity extends AppCompatActivity implements OnSwipeRefreshViewListener {

    private SwipeRefreshListView listView;
    private int[] imagesUrl = {R.mipmap.item01,R.mipmap.item02,R.mipmap.item03
            ,R.mipmap.item02,R.mipmap.item01,R.mipmap.item03,R.mipmap.item02,R.mipmap.item03};
    private MyAdapter adapter = new MyAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        listView = (SwipeRefreshListView) findViewById(R.id.listview);
        listView.getListView().setAdapter(adapter);
        listView.setOnRefreshListener(this);//这句是关键
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
```

代码很简单，控件使用也很简单，我就不多说了，有兴趣的童鞋可以下载下来研究一下。