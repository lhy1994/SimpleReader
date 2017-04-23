package com.example.liuhaoyuan.simplereader.movie;

import com.example.liuhaoyuan.simplereader.bean.MovieListBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public class MovieListPresenter extends MovieListContract.Presenter {

    public MovieListPresenter(MovieListContract.View view) {
        this.mView = view;
        mModel = new MovieListModel();
    }

    @Override
    public void getMovieList(String rankTitle) {
        Disposable disposable = mModel.getMovieList(rankTitle)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieListBean>() {
                    @Override
                    public void accept(@NonNull MovieListBean bean) throws Exception {
                        mView.updateList(bean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
        addDisposable(disposable);
    }
}
