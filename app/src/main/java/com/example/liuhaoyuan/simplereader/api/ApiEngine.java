package com.example.liuhaoyuan.simplereader.api;

import com.example.liuhaoyuan.simplereader.App;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public class ApiEngine {
    private static final int CACHE_SIZE=1024*1024*100;
    private static ApiEngine instance;
    private final Retrofit mRetrofit;

    public static synchronized ApiEngine getInstance(){
        if (instance==null){
            instance=new ApiEngine();
        }
        return instance;
    }
    private ApiEngine(){
        File cacheFile=new File(App.getContext().getCacheDir(),"HttpCache");
        Cache cache=new Cache(cacheFile,CACHE_SIZE);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
                .cache(cache)
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(DouBanApiService.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public DouBanApiService getDouBanApiService(){
        return mRetrofit.create(DouBanApiService.class);
    }
}
