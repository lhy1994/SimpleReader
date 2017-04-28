package com.example.liuhaoyuan.simplereader.movie;

import com.example.liuhaoyuan.simplereader.base.BasePresenter;
import com.example.liuhaoyuan.simplereader.base.BaseView;
import com.example.liuhaoyuan.simplereader.bean.movie.MovieHumanBean;
import com.example.liuhaoyuan.simplereader.bean.movie.MovieHumanDetailBean;

import java.util.List;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public interface MovieContract {

    interface MovieDetailView extends BaseView<MovieDetailPresenter> {
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

    abstract class MovieDetailPresenter extends BasePresenter {
        public abstract void getMovieDetail(String id);
    }

    interface HumanDetailView extends BaseView<HumanPresenter>{
        void setHumanPoster(String imageUrl);
        void setName(String name);
        void setNameEn(String nameEn);
        void setGender(String gender);
        void setBornPlace(String bornPlace);
        void setWorks(List<MovieHumanDetailBean.WorksBean> works);
        void setHumanSummary(String summary);
        void setHumanPhotos(List<String> photos);
    }

    abstract class HumanPresenter extends BasePresenter{
        public abstract void getHumanDetail(String id);
        public abstract void getHumanSummary(String id);
        public abstract void getHumanPhotos(String id);
    }
}
