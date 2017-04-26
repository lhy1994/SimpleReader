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

public class MovieModel implements MovieContract.Model{
    @Override
    public Observable<MovieListBean> getMovieRankList(String rankTitle, String start, String count) {
        DouBanApiService service = ApiEngine.getInstance().getDouBanApiService();
        Observable<MovieListBean> observable = null;
        switch (rankTitle) {
            case "正在热映":
                observable = service.getMovieInTheaters(start, count);
                break;
            case "即将上映":
                observable = service.getMovieComingSoon(start, count);
                break;
            case "Top250":
                observable = service.getMovieTop250(start, count);
                break;
            case "口碑榜":
                observable = service.getMovieWeekly(start, count);
                break;
            case "北美票房榜":
                observable = service.getMovieUsBox(start, count);
                break;
            case "新片榜":
                observable = service.getMovieNew(start, count);
                break;
            default:
                break;
        }
        return observable;
    }

    @Override
    public Observable<MovieDetailBean> getMovieDetail(String id) {
        return ApiEngine.getInstance().getDouBanApiService().getMovieDetail(id);
    }

    @Override
    public Observable<MovieListBean> getMovieByTag(String tag, String start, String count) {
        return ApiEngine.getInstance().getDouBanApiService().getMovieByTag(tag, start, count);
    }

    @Override
    public Observable<MovieHumanDetailBean> getHumanDetail(String id) {
        return ApiEngine.getInstance().getDouBanApiService().getMovieHumanDetail(id);
    }
}