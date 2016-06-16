package com.afinalstone.androidstudy.view.roolpager;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;


public class ImagerLoaderUtil {

	private ImageLoader imageLoader;
	private DisplayImageOptions options;
	private Context context;
	private static ImagerLoaderUtil imagerLoaderUtil;
	
	
	private ImagerLoaderUtil(Context context) {
		super();
		this.context = context;
	}

	public synchronized static ImagerLoaderUtil getInstance(Context context){
		if(imagerLoaderUtil == null){
			imagerLoaderUtil = new ImagerLoaderUtil(context);
			imagerLoaderUtil.initImageLoader();
		}
		return imagerLoaderUtil;
	}

	public void displayMyImage(String imageUrl, ImageView imageView){
		
		if(imageUrl == null){
			imageLoader.displayImage(imageUrl, imageView);
			return;
		}
		if(imageUrl.startsWith("http://")){
			imageLoader.displayImage(imageUrl, imageView);
		}else if(imageUrl.startsWith("assets://"))
		{
			imageLoader.displayImage(imageUrl, imageView);
		}
		else if(imageUrl.startsWith("file:///mnt"))
		{
			imageLoader.displayImage(imageUrl, imageView);
		}
		else if(imageUrl.startsWith("content://"))
		{
			imageLoader.displayImage(imageUrl, imageView);
		}
		else if(imageUrl.startsWith("drawable://"))
		{
			imageLoader.displayImage(imageUrl, imageView);
		}
		else{
			Uri uri = Uri.parse(imageUrl);
			imageView.setImageURI(uri);
		}
	}
	
	/**显示大小写**/
	public void displayMyImage(String imageUrl, ImageView imageView, int resourceId){
		DisplayImageOptions tempOptions = new DisplayImageOptions.Builder()
        .cacheInMemory(false)//设置下载的图片是否缓存在内存�??
        .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
		.showImageOnLoading(resourceId)         // 设置图片下载期间显示的图片  
		.showImageForEmptyUri(resourceId)  // 设置图片Uri为空或是错误的时候显示的图片  
		.showImageOnFail(resourceId)       // 设置图片加载或解码过程中发生错误显示的图片  
        .build();
		imageLoader.displayImage(imageUrl, imageView, tempOptions);
	}
	
	/**显示大小写**/
//	public void displayMyImage(String imageUrl, ImageView imageView, int ){
//		DisplayImageOptions tempOptions = new DisplayImageOptions.Builder()
//		.cacheInMemory(false)//设置下载的图片是否缓存在内存�??
//		.cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
//		.showImageOnLoading(colorResourceId)         // 设置图片下载期间显示的图片  
//		.showImageForEmptyUri(colorResourceId)  // 设置图片Uri为空或是错误的时候显示的图片  
//		.showImageOnFail(colorResourceId)       // 设置图片加载或解码过程中发生错误显示的图片  
//		.build();
//		imageLoader.displayImage(imageUrl, imageView, tempOptions);
//	}
	
	public void initImageLoader() {
		
		@SuppressWarnings("deprecation")
		DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(com.afinalstone.androidstudy.R.drawable.ic_stub) // 设置图片下载期间显示的图片
				.showImageOnLoading(com.afinalstone.androidstudy.R.drawable.ic_empty)	//设置下载过程中图片显示
				.showImageForEmptyUri(com.afinalstone.androidstudy.R.drawable.ic_empty) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(com.afinalstone.androidstudy.R.drawable.ic_error) // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
				// .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
				.build(); // 创建配置过得DisplayImageOption对象

		ImageLoaderConfiguration config = new ImageLoaderConfiguration
				.Builder(
				context.getApplicationContext())
				.defaultDisplayImageOptions(options)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.build();
				imageLoader = ImageLoader.getInstance();
				imageLoader.init(config);	
		
	}

}
