package com.example.liuhaoyuan.simplereader.movie;

import com.example.liuhaoyuan.simplereader.api.ApiEngine;
import com.example.liuhaoyuan.simplereader.bean.MovieDetailBean;

import io.reactivex.Observable;

/**
 * Created by liuhaoyuan on 17/4/24.
 */

public class MovieDetailModel implements MovieDetailContract.Model {
    @Override
    public Observable<MovieDetailBean> getMovieDetail(String id) {
        return ApiEngine.getInstance().getDouBanApiService().getMovieDetail(id);
    }
}
