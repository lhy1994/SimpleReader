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
    public void getMovieList(String rankTitle, String start, String count, final boolean loadMore) {
        Disposable disposable = mModel.getMovieList(rankTitle, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieListBean>() {
                    @Override
                    public void accept(@NonNull MovieListBean bean) throws Exception {
                        if (loadMore){
                            mView.addMoreData(bean);
                        }else {
                            mView.updateList(bean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        if (!loadMore){
                            mView.showErrorView();
                        }else {
                            mView.addMoreData(null);
                        }
                    }
                });
        addDisposable(disposable);
    }
}
