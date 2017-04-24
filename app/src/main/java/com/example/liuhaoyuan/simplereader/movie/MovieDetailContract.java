package com.example.liuhaoyuan.simplereader.movie;

import com.example.liuhaoyuan.simplereader.base.BaseModel;
import com.example.liuhaoyuan.simplereader.base.BasePresenter;
import com.example.liuhaoyuan.simplereader.base.BaseView;
import com.example.liuhaoyuan.simplereader.bean.MovieDetailBean;

import io.reactivex.Observable;

/**
 * Created by liuhaoyuan on 17/4/24.
 */

public interface MovieDetailContract {
    interface View extends BaseView {
        void initUi(MovieDetailBean bean);
    }

    interface Model extends BaseModel {
        Observable<MovieDetailBean> getMovieDetail(String id);
    }

    public abstract class Presenter extends BasePresenter<View ,Model> {
        public abstract void getMovieDetail(String id);
    }
}
