package com.example.liuhaoyuan.simplereader.api;

import com.example.liuhaoyuan.simplereader.bean.MovieDetailBean;
import com.example.liuhaoyuan.simplereader.bean.MovieListBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public interface DouBanApiService {
    String BASE_URL="https://api.douban.com/";
    String[] DOUBAN_RANK_TITLE=new String[]{"正在热映","即将上映","Top250","口碑榜","北美票房榜","新片榜"};

    @GET("v2/movie/coming_soon")
    Observable<MovieListBean> getMovieComingSoon(@QueryMap Map<String,String> params);

    @GET("v2/movie/in_theaters")
    Observable<MovieListBean> getMovieInTheaters(@QueryMap Map<String,String> params);

    @GET("v2/movie/top250")
    Observable<MovieListBean> getMovieTop250(@QueryMap Map<String,String> params);

    @GET("v2/movie/us_box")
    Observable<MovieListBean> getMovieUsBox(@QueryMap Map<String,String> params);

    @GET("v2/movie/weekly")
    Observable<MovieListBean> getMovieWeekly(@QueryMap Map<String,String> params);

    @GET("v2/movie/new_movies")
    Observable<MovieListBean> getMovieNew(@QueryMap Map<String,String> params);

    @GET("/v2/movie/subject/{id}")
    Observable<MovieDetailBean> getMovieDetail(@Path("id")  String id);

}
