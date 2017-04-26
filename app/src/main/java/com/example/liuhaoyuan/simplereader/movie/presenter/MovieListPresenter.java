package com.example.liuhaoyuan.simplereader.movie.presenter;

import com.example.liuhaoyuan.simplereader.bean.MovieListBean;
import com.example.liuhaoyuan.simplereader.model.MovieModel;
import com.example.liuhaoyuan.simplereader.movie.MovieContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liuhaoyuan on 17/4/25.
 */

public class MovieListPresenter extends MovieContract.BaseMovieListPresenter {

    public MovieListPresenter(MovieContract.MovieListView view) {
        this.mView = view;
        mModel = new MovieModel();
    }

    @Override
    public void getMovieList(String tag, String start, String count, final boolean loadMore) {
        Disposable disposable = mModel.getMovieByTag(tag, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieListBean>() {
                    @Override
                    public void accept(@NonNull MovieListBean bean) throws Exception {
                        if (loadMore) {
                            mView.addMoreListData(bean);
                        } else {
                            mView.updateList(bean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        if (!loadMore) {
                            mView.showErrorView();
                        } else {
                            mView.addMoreListData(null);
                        }
                    }
                });
        addDisposable(disposable);
    }
}
