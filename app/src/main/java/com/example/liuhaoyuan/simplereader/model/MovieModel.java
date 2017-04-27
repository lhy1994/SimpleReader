package com.example.liuhaoyuan.simplereader.model;

import com.example.liuhaoyuan.simplereader.api.ApiEngine;
import com.example.liuhaoyuan.simplereader.api.DouBanApiService;
import com.example.liuhaoyuan.simplereader.bean.MovieDetailBean;
import com.example.liuhaoyuan.simplereader.bean.MovieHumanDetailBean;
import com.example.liuhaoyuan.simplereader.bean.MovieListBean;
import com.example.liuhaoyuan.simplereader.movie.MovieContract;

import io.reactivex.Observable;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public class MovieModel{
    private static MovieModel instance;
    private DouBanApiService mDouBanService;
    private MovieModel(){
        mDouBanService=ApiEngine.getInstance().getDouBanApiService();
    }
    public static synchronized MovieModel getInstance(){
        if (instance==null){
            instance=new MovieModel();
        }
        return instance;
    }

    public Observable<MovieListBean> getMovieRankList(String rankTitle, String start, String count) {
        Observable<MovieListBean> observable = null;
        switch (rankTitle) {
            case "正在热映":
                observable = mDouBanService.getMovieInTheaters(start, count);
                break;
            case "即将上映":
                observable = mDouBanService.getMovieComingSoon(start, count);
                break;
            case "Top250":
                observable = mDouBanService.getMovieTop250(start, count);
                break;
            case "口碑榜":
                observable = mDouBanService.getMovieWeekly(start, count);
                break;
            case "北美票房榜":
                observable = mDouBanService.getMovieUsBox(start, count);
                break;
            case "新片榜":
                observable = mDouBanService.getMovieNew(start, count);
                break;
            default:
                break;
        }
        return observable;
    }

    public Observable<MovieDetailBean> getMovieDetail(String id) {
        return mDouBanService.getMovieDetail(id);
    }

    public Observable<MovieListBean> getMovieByTag(String tag, String start, String count) {
        return mDouBanService.getMovieByTag(tag, start, count);
    }

    public Observable<MovieHumanDetailBean> getHumanDetail(String id) {
        return mDouBanService.getMovieHumanDetail(id);
    }
}
