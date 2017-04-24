package com.example.liuhaoyuan.simplereader.movie;

import com.example.liuhaoyuan.simplereader.App;
import com.example.liuhaoyuan.simplereader.R;
import com.example.liuhaoyuan.simplereader.api.ApiEngine;
import com.example.liuhaoyuan.simplereader.api.DouBanApiService;
import com.example.liuhaoyuan.simplereader.bean.MovieListBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public class MovieListModel implements MovieListContract.Model {
    @Override
    public Observable<MovieListBean> getMovieList(String rankTitle, String start, String count) {
        DouBanApiService service = ApiEngine.getInstance().getDouBanApiService();
        Observable<MovieListBean> observable = null;
        Map<String, String> parms = new HashMap<>();
        parms.put("start",start);
        parms.put("count",count);
        switch (rankTitle) {
            case "正在热映":
                observable = service.getMovieInTheaters(parms);
                break;
            case "即将上映":
                observable = service.getMovieComingSoon(parms);
                break;
            case "Top250":
                observable = service.getMovieTop250(parms);
                break;
            case "口碑榜":
                observable = service.getMovieWeekly(parms);
                break;
            case "北美票房榜":
                observable = service.getMovieUsBox(parms);
                break;
            case "新片榜":
                observable = service.getMovieNew(parms);
                break;
            default:
                break;
        }
        return observable;
    }
}
