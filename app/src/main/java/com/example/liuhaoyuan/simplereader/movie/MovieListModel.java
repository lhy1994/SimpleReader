package com.example.liuhaoyuan.simplereader.movie;

import com.example.liuhaoyuan.simplereader.App;
import com.example.liuhaoyuan.simplereader.R;
import com.example.liuhaoyuan.simplereader.api.ApiEngine;
import com.example.liuhaoyuan.simplereader.api.DouBanApiService;
import com.example.liuhaoyuan.simplereader.bean.MovieListBean;

import io.reactivex.Observable;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public class MovieListModel implements MovieListContract.Model{
    @Override
    public Observable<MovieListBean> getMovieList(String rankTitle) {
        DouBanApiService service = ApiEngine.getInstance().getDouBanApiService();
        Observable<MovieListBean> observable = null;
        switch (rankTitle){
            case "正在热映":
                observable=service.getMovieInTheaters();
                break;
            case "即将上映":
                observable=service.getMovieComingSoon();
                break;
            case "Top250":
                observable=service.getMovieTop250();
                break;
            case "口碑榜":
                observable=service.getMovieWeekly();
                break;
            case "北美票房榜":
                observable=service.getMovieUsBox();
                break;
            case "新片榜":
                observable=service.getMovieNew();
                break;
            default:
                break;
        }
        return observable;
    }
}
