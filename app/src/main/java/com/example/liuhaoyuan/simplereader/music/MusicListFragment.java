package com.example.liuhaoyuan.simplereader.music;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuhaoyuan.simplereader.ConstantValues;
import com.example.liuhaoyuan.simplereader.adapter.BaseListAdapter;
import com.example.liuhaoyuan.simplereader.adapter.music.MusicListAdapter;
import com.example.liuhaoyuan.simplereader.base.BaseListFragment;
import com.example.liuhaoyuan.simplereader.bean.BaseListBean;
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

public class MusicListFragment extends BaseListFragment {

    private String mCategory;
    private MusicModel mModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCategory = getArguments().getString(ConstantValues.DOUBAN_MUSIC_CATEGORY);
        mModel = MusicModel.getInstance();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void getMusicList(String start, String count, final boolean loadMore) {
        Disposable disposable = mModel.getMusicList(mCategory, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MusicListBean>() {
                    @Override
                    public void accept(@NonNull MusicListBean musicListBean) throws Exception {
                        if (!loadMore) {
                            updateList(musicListBean);
                        } else {
                            addMoreListData(musicListBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        if (!loadMore) {
                            showErrorView();
                        } else {
                            addMoreListData(null);
                        }
                    }
                });
        addDisposable(disposable);
    }

    @Override
    protected void loadData() {
        getMusicList("0", "20", false);
    }

    @Override
    protected void loadMoreData(int start) {
        getMusicList(String.valueOf(start), "20", true);
    }

    @Override
    protected BaseListAdapter onCreateAdapter(BaseListBean bean) {
        MusicListBean data = (MusicListBean) bean;
        return new MusicListAdapter(getContext(), data.musics);
    }

    @Override
    protected RecyclerView.LayoutManager onCreateLayoutManager() {
        return new GridLayoutManager(getContext(), 3);
    }

    @Override
    protected void setAdapterData(BaseListBean bean, boolean append) {
        if (bean instanceof MusicListBean) {
            MusicListBean data = (MusicListBean) bean;
            if (append) {
                mAdapter.addMoreData(data.musics);
            } else {
                mAdapter.setData(data.musics);
            }
        }
    }
}
