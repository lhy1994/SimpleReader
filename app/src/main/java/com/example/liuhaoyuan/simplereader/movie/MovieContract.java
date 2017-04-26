package com.example.liuhaoyuan.simplereader.movie;

import com.example.liuhaoyuan.simplereader.base.BaseActivity;
import com.example.liuhaoyuan.simplereader.base.BaseListView;
import com.example.liuhaoyuan.simplereader.base.BaseModel;
import com.example.liuhaoyuan.simplereader.base.BasePresenter;
import com.example.liuhaoyuan.simplereader.base.BaseView;
import com.example.liuhaoyuan.simplereader.bean.MovieDetailBean;
import com.example.liuhaoyuan.simplereader.bean.MovieHumanDetailBean;
import com.example.liuhaoyuan.simplereader.bean.MovieListBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public interface MovieContract {
    interface Model extends BaseModel {
        Observable<MovieListBean> getMovieRankList(String rankTitle, String start, String count);

        Observable<MovieDetailBean> getMovieDetail(String id);

        Observable<MovieListBean> getMovieByTag(String tag, String start, String count);

        Observable<MovieHumanDetailBean> getHumanDetail(String id);
    }

    interface MovieListView extends BaseListView {

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

    interface HumanDetailView extends BaseView{
        void initUi(MovieHumanDetailBean bean);
        void updateHumanSummary(String summary);
        void updateHumanPhotos(List<String> photos);
    }

    abstract class HumanPresenter extends BasePresenter<HumanDetailView,Model>{
        public abstract void getHumanDetail(String id);
        public abstract void getHumanSummary(String id);
        public abstract void getHumanPhotos(String id);
    }
}
