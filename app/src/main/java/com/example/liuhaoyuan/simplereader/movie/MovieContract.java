package com.example.liuhaoyuan.simplereader.movie;

import com.example.liuhaoyuan.simplereader.base.BaseModel;
import com.example.liuhaoyuan.simplereader.base.BasePresenter;
import com.example.liuhaoyuan.simplereader.base.BaseView;
import com.example.liuhaoyuan.simplereader.bean.MovieDetailBean;
import com.example.liuhaoyuan.simplereader.bean.MovieListBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public interface MovieContract {
    public interface Model extends BaseModel {
        Observable<MovieListBean> getMovieList(String rankTitle, String start, String count);

        Observable<MovieDetailBean> getMovieDetail(String id);
    }

    public interface MovieListView extends BaseView {
        void updateList(MovieListBean bean);

        void addMoreData(MovieListBean bean);
    }

    public abstract class MovieListPresenter extends BasePresenter<MovieListView, Model> {
        public abstract void getMovieList(String rankTitle, String start, String count, boolean loadMore);
    }

    public interface MovieDetailView extends BaseView {
        void initUi(MovieDetailBean bean);
    }

    public abstract class MovieDetailPresenter extends BasePresenter<MovieDetailView, Model> {
        public abstract void getMovieDetail(String id);
    }
}
