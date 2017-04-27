package com.example.liuhaoyuan.simplereader.movie;

import com.example.liuhaoyuan.simplereader.base.BaseModel;
import com.example.liuhaoyuan.simplereader.base.BasePresenter;
import com.example.liuhaoyuan.simplereader.base.BaseView;
import com.example.liuhaoyuan.simplereader.bean.MovieDetailBean;
import com.example.liuhaoyuan.simplereader.bean.MovieHumanBean;
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

    interface MovieDetailView extends BaseView {
        void setPoster(String imageUrl);
        void setTitle(String title);
        void setRating(float rating,int max);
        void setRatingCount(int ratingCount);
        void setCountry(List<String> country);
        void setYear(String year);
        void setGenre(List<String> genres);
        void setSummary(String summary);
        void setDirectors(List<MovieHumanBean> directors);
        void setCasts(List<MovieHumanBean> casts);
    }

    abstract class MovieDetailPresenter extends BasePresenter<MovieDetailView, Model> {
        public abstract void getMovieDetail(String id);
    }

    interface HumanDetailView extends BaseView{
        void setHumanPoster(String imageUrl);
        void setName(String name);
        void setNameEn(String nameEn);
        void setGender(String gender);
        void setBornPlace(String bornPlace);
        void setWorks(List<MovieHumanDetailBean.WorksBean> works);
        void setHumanSummary(String summary);
        void setHumanPhotos(List<String> photos);
    }

    abstract class HumanPresenter extends BasePresenter<HumanDetailView,Model>{
        public abstract void getHumanDetail(String id);
        public abstract void getHumanSummary(String id);
        public abstract void getHumanPhotos(String id);
    }
}
