package com.example.liuhaoyuan.simplereader.movie;

import com.example.liuhaoyuan.simplereader.bean.MovieDetailBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liuhaoyuan on 17/4/24.
 */

public class MovieDetailPresenter extends MovieDetailContract.Presenter{
    public MovieDetailPresenter(MovieDetailContract.View view) {
        this.mView=view;
        this.mModel=new MovieDetailModel();
    }

    @Override
    public void getMovieDetail(String id) {
        Disposable disposable = mModel.getMovieDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieDetailBean>() {
                    @Override
                    public void accept(@NonNull MovieDetailBean bean) throws Exception {
                        mView.initUi(bean);
                    }
                });
        addDisposable(disposable);
    }
}
