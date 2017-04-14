package com.example.dreamwork.application;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by co-mall on 2016/2/23.
 */
public class DreamApplication extends Application {
    public static DreamApplication mApplication;
    public ImageLoader imageLoader;
    public DisplayImageOptions.Builder options;


    public static Application getInstance() {
        if (mApplication == null) {
            mApplication = new DreamApplication();
        }
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        // imageloader初始化图片
        options = new DisplayImageOptions.Builder();
        initImageLoder();
        this.imageLoader = ImageLoader.getInstance();


    }

    /**
     * 初始化图片加载器
     */
    private void initImageLoder() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mApplication).threadPriority(Thread.NORM_PRIORITY - 2)
                .defaultDisplayImageOptions(options)
                .denyCacheImageMultipleSizesInMemory()
                // .memoryCache(new WeakMemoryCache())
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }
}
