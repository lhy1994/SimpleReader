package com.example.liuhaoyuan.simplereader.movie.presenter;

import com.example.liuhaoyuan.simplereader.bean.MovieDetailBean;
import com.example.liuhaoyuan.simplereader.movie.MovieContract;
import com.example.liuhaoyuan.simplereader.model.MovieModel;
import com.example.liuhaoyuan.simplereader.util.DataUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liuhaoyuan on 17/4/24.
 */

public class MovieDetailPresenter extends MovieContract.MovieDetailPresenter {
    public MovieDetailPresenter(MovieContract.MovieDetailView view) {
        this.mView=view;
        this.mModel=new MovieModel();
    }

    @Override
    public void getMovieDetail(String id) {
        Disposable disposable = mModel.getMovieDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieDetailBean>() {
                    @Override
                    public void accept(@NonNull MovieDetailBean bean) throws Exception {
                        String imageUrl = DataUtils.getImageUrl(bean.images);
                        mView.setPoster(imageUrl);
                        mView.setTitle(bean.title);
                        mView.setRating((float) bean.rating.average,bean.rating.max);
                        mView.setRatingCount(bean.ratings_count);
                        mView.setCountry(bean.countries);
                        mView.setYear(bean.year);
                        mView.setGenre(bean.genres);
                        mView.setSummary(bean.summary);
                        mView.setDirectors(bean.directors);
                        mView.setCasts(bean.casts);
                    }
                });
        addDisposable(disposable);
    }
}
