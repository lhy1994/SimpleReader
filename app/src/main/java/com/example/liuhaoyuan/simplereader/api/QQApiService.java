package com.example.liuhaoyuan.simplereader.api;

import com.example.liuhaoyuan.simplereader.bean.music.QQMusicBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by liuhaoyuan on 2017/4/28.
 */

public interface QQApiService {
    String BASE_URL = "http://route.showapi.com/";
    String APP_ID = "22447";
    String SECRECT = "27ed84878a794201a8b325c301b031d4";

    @GET("213-1")
    Observable<QQMusicBean> getMusicByKey(@Query("showapi_appid") String appID, @Query("showapi_sign") String sign, @Query("keyword") String keyword, @Query("page") String page);
}
