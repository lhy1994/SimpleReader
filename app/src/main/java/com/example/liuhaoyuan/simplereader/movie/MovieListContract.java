package com.example.liuhaoyuan.simplereader.movie;

import com.example.liuhaoyuan.simplereader.base.BaseModel;
import com.example.liuhaoyuan.simplereader.base.BasePresenter;
import com.example.liuhaoyuan.simplereader.base.BaseView;
import com.example.liuhaoyuan.simplereader.bean.MovieListBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public interface MovieListContract {
    public interface View extends BaseView {
        void updateList(MovieListBean bean);
        void addMoreData(MovieListBean bean);
        void showLoadingView();
        void showErrorView();
        void hideLoadingView();
        void hideErrorView();
    }

    public interface Model extends BaseModel {
        Observable<MovieListBean> getMovieList(String rankTitle, String start, String count);
    }

    public abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getMovieList(String rankTitle, String start, String count,boolean loadMore);
    }
}
