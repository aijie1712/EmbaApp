package com.emba.embaapp;

import android.app.Application;

import com.emba.embaapp.utils.LogUtils;
import com.emba.embaapp.utils.SpUtils;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016-12-02
 *
 * @desc
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    /**
     * 全局sessionId
     */
    public static String sessionId;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        sessionId = SpUtils.getInstance(this).getString(AppConstant.SESSIONID_KEY);
        LogUtils.i("MyApplication onCreate");
        initHttp();

        initFresco();
    }

    private void initFresco() {
        //fresco
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder().setBaseDirectoryPath(getExternalCacheDir())
                .setBaseDirectoryName("imageCache")
                .setMaxCacheSize(100 * ByteConstants.MB)
                .setMaxCacheSizeOnLowDiskSpace(10 * ByteConstants.MB)
                .setMaxCacheSizeOnVeryLowDiskSpace(5 * ByteConstants.MB)
                .setVersion(1)
                .build();
        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)
                .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig()).build();
        Fresco.initialize(this, imagePipelineConfig);
    }

    private void initHttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    public static MyApplication getApplication() {
        return instance;
    }
}
