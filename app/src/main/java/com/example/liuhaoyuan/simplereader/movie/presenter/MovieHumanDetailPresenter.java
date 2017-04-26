package com.example.liuhaoyuan.simplereader.movie.presenter;

import com.example.liuhaoyuan.simplereader.api.GetActor;
import com.example.liuhaoyuan.simplereader.bean.MovieHumanDetailBean;
import com.example.liuhaoyuan.simplereader.model.MovieModel;
import com.example.liuhaoyuan.simplereader.movie.MovieContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liuhaoyuan on 2017/4/26.
 */

public class MovieHumanDetailPresenter extends MovieContract.HumanPresenter {
    public MovieHumanDetailPresenter(MovieContract.HumanDetailView view) {
        this.mView = view;
        mModel = new MovieModel();
    }

    @Override
    public void getHumanDetail(String id) {
        Disposable disposable = mModel.getHumanDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieHumanDetailBean>() {
                    @Override
                    public void accept(@NonNull MovieHumanDetailBean bean) throws Exception {
                        mView.initUi(bean);
                    }
                });
        addDisposable(disposable);
    }

    @Override
    public void getHumanSummary(final String id) {
        Disposable disposable = Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                        GetActor getActor = new GetActor(id);
                        String summary = getActor.getActorSummary();
                        e.onNext(summary);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        mView.updateHumanSummary(s);
                    }
                });
        addDisposable(disposable);
    }

    @Override
    public void getHumanPhotos(final String id) {
        Disposable disposable = Observable
                .create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<String>> e) throws Exception {
                        GetActor getActor = new GetActor(id);
                        List<String> images = getActor.getActorImage();
                        e.onNext(images);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(@NonNull List<String> strings) throws Exception {
                        mView.updateHumanPhotos(strings);
                    }
                });
        addDisposable(disposable);
    }
}
