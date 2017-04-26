package com.example.liuhaoyuan.simplereader.music;

import com.example.liuhaoyuan.simplereader.bean.MusicListBean;
import com.example.liuhaoyuan.simplereader.model.MusicModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liuhaoyuan on 2017/4/26.
 */

public class MusicListPresenter extends MusicContract.ListPresenter {
    public MusicListPresenter(MusicContract.ListView view){
        this.mView=view;
        mModel=new MusicModel();
    }
    @Override
    public void getMusicList(String category, String start, String count, final boolean loadMore) {
        Disposable disposable = mModel.getMusicList(category, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MusicListBean>() {
                    @Override
                    public void accept(@NonNull MusicListBean musicListBean) throws Exception {
                        if (!loadMore){
                            mView.updateList(musicListBean);
                        }else {
                            mView.addMoreListData(musicListBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
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
