package com.example.liuhaoyuan.simplereader.api;

import com.example.liuhaoyuan.simplereader.bean.book.BookItemBean;
import com.example.liuhaoyuan.simplereader.bean.book.BookListBean;
import com.example.liuhaoyuan.simplereader.bean.movie.MovieDetailBean;
import com.example.liuhaoyuan.simplereader.bean.movie.MovieHumanDetailBean;
import com.example.liuhaoyuan.simplereader.bean.movie.MovieListBean;
import com.example.liuhaoyuan.simplereader.bean.music.MusicItemBean;
import com.example.liuhaoyuan.simplereader.bean.music.MusicListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public interface DouBanApiService {
    String BASE_URL = "https://api.douban.com/";

    @GET("v2/movie/coming_soon")
    Observable<MovieListBean> getMovieComingSoon(@Query("start") String start, @Query("count") String count);

    @GET("v2/movie/in_theaters")
    Observable<MovieListBean> getMovieInTheaters(@Query("start") String start, @Query("count") String count);

    @GET("v2/movie/top250")
    Observable<MovieListBean> getMovieTop250(@Query("start") String start, @Query("count") String count);

    @GET("v2/movie/us_box")
    Observable<MovieListBean> getMovieUsBox(@Query("start") String start, @Query("count") String count);

    @GET("v2/movie/weekly")
    Observable<MovieListBean> getMovieWeekly(@Query("start") String start, @Query("count") String count);

    @GET("v2/movie/new_movies")
    Observable<MovieListBean> getMovieNew(@Query("start") String start, @Query("count") String count);

    @GET("v2/movie/subject/{id}")
    Observable<MovieDetailBean> getMovieDetail(@Path("id") String id);

    @GET("v2/movie/search")
    Observable<MovieListBean> getMovieByTag(@Query("tag") String tag, @Query("start") String start, @Query("count") String count);

    @GET("v2/movie/search")
    Observable<MovieListBean> getMovieByKey(@Query("q") String key, @Query("start") String start, @Query("count") String count);

    @GET("v2/movie/celebrity/{id}")
    Observable<MovieHumanDetailBean> getMovieHumanDetail(@Path("id") String id);

    @GET("v2/music/search")
    Observable<MusicListBean> getMusicByTag(@Query("tag") String tag, @Query("start") String start, @Query("count") String count);

    @GET("v2/music/search")
    Observable<MusicListBean> getMusicByKey(@Query("q") String key, @Query("start") String start, @Query("count") String count);

    @GET("v2/music/{id}")
    Observable<MusicItemBean> getMusicDetail(@Path("id") String id);

    @GET("v2/book/search")
    Observable<BookListBean> getBookByTag(@Query("tag") String tag, @Query("start") String start, @Query("count") String count);

    @GET("v2/book/{id}")
    Observable<BookItemBean> getBookDetail(@Path("id") String id);

    @GET("v2/book/series/{id}/books")
    Observable<BookListBean> getSeriesBooks(@Path("id") String id);
}
