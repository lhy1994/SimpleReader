package com.example.liuhaoyuan.simplereader.api;

import com.example.liuhaoyuan.simplereader.bean.MovieListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public interface DouBanApiService {
    public static final String BASE_URL="https://api.douban.com/";
    public static final String[] DOUBAN_RANK_TITLE=new String[]{"正在热映","即将上映","Top250","口碑榜","北美票房榜","新片榜"};

    @GET("v2/movie/coming_soon")
    Observable<MovieListBean> getMovieComingSoon();

    @GET("v2/movie/in_theaters")
    Observable<MovieListBean> getMovieInTheaters();

    @GET("v2/movie/top250")
    Observable<MovieListBean> getMovieTop250();

    @GET("v2/movie/us_box")
    Observable<MovieListBean> getMovieUsBox();

    @GET("v2/movie/weekly")
    Observable<MovieListBean> getMovieWeekly();

    @GET("v2/movie/new_movies")
    Observable<MovieListBean> getMovieNew();
}
