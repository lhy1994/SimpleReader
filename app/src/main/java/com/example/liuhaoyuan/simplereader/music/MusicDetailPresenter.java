package com.example.liuhaoyuan.simplereader.music;

import com.example.liuhaoyuan.simplereader.bean.MusicItemBean;
import com.example.liuhaoyuan.simplereader.model.MusicModel;

import java.util.ArrayList;
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
 * Created by liuhaoyuan on 2017/4/27.
 */

public class MusicDetailPresenter extends MusicContract.DetailPresenter {
    public MusicDetailPresenter(MusicContract.DetailView view) {
        mView = view;
        mModel = new MusicModel();
    }

    @Override
    public void getMusicDetail(String id) {
        Disposable disposable = mModel.getMusicDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MusicItemBean>() {
                    @Override
                    public void accept(@NonNull MusicItemBean musicItemBean) throws Exception {
                        mView.setPoster(musicItemBean.image);
                        mView.setTitle(musicItemBean.title);
                        mView.setRating((float) musicItemBean.rating.average, musicItemBean.rating.max);
                        mView.setRatingCount(musicItemBean.rating.numRaters);
                        parseSummary(musicItemBean.attrs);
                        parseTags(musicItemBean.tags);
                        parseTracks(musicItemBean.attrs.tracks);
                    }
                });
        addDisposable(disposable);
    }

    private void parseTags(final List<MusicItemBean.TagsBean> tags) {
        Disposable disposable = Observable
                .create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<String>> e) throws Exception {
                        List<String> list = new ArrayList<String>();
                        for (MusicItemBean.TagsBean tag : tags) {
                            list.add(tag.name);
                        }
                        e.onNext(list);
                        e.onComplete();
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(@NonNull List<String> strings) throws Exception {
                        mView.setGenre(strings);
                    }
                });
        addDisposable(disposable);
    }

    private void parseTracks(final List<String> tracks) {
        Disposable disposable = Observable
                .create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<String>> e) throws Exception {
                        String tracksString = tracks.get(0);
                        String[] split = tracksString.split("\\n");
                        List<String> list = new ArrayList<String>();
                        for (String s : split) {
                            int index = s.indexOf(".");
                            String temp = s;
                            if (index != -1) {
                                temp = s.substring(index + 1);
                            }
                            list.add(temp);
                        }
                        e.onNext(list);
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(@NonNull List<String> strings) throws Exception {
                        mView.setSongList(strings);
                    }
                });
        addDisposable(disposable);
    }

    private void parseSummary(MusicItemBean.AttrsBean attrsBean) {
        StringBuilder builder = new StringBuilder();
        builder.append(attrsBean.singer)
                .append(" ")
                .append(attrsBean.version)
                .append(" ")
                .append(attrsBean.pubdate)
                .append(" ")
                .append(attrsBean.publisher)
                .append(" ")
                .append(attrsBean.media);
        mView.setSummary(builder.toString());
    }
}
