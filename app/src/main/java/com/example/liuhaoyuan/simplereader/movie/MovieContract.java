package com.example.liuhaoyuan.simplereader.movie;

import com.example.liuhaoyuan.simplereader.base.BaseListView;
import com.example.liuhaoyuan.simplereader.base.BaseModel;
import com.example.liuhaoyuan.simplereader.base.BasePresenter;
import com.example.liuhaoyuan.simplereader.base.BaseView;
import com.example.liuhaoyuan.simplereader.bean.MovieDetailBean;
import com.example.liuhaoyuan.simplereader.bean.MovieListBean;

import io.reactivex.Observable;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public interface MovieContract {
    interface Model extends BaseModel {
        Observable<MovieListBean> getMovieRankList(String rankTitle, String start, String count);

        Observable<MovieDetailBean> getMovieDetail(String id);

        Observable<MovieListBean> getMovieByTag(String tag, String start, String count);
    }

    interface MovieListView extends BaseListView {
        void updateList(MovieListBean bean);

        void addMoreData(MovieListBean bean);
    }

    abstract class BaseMovieListPresenter extends BasePresenter<MovieListView, Model> {
        public abstract void getMovieList(String category, String start, String count, boolean loadMore);
    }

    interface MovieDetailView extends BaseView {
        void initUi(MovieDetailBean bean);
    }

    abstract class MovieDetailPresenter extends BasePresenter<MovieDetailView, Model> {
        public abstract void getMovieDetail(String id);
    }
}
