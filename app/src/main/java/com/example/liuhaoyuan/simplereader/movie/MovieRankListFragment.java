package com.example.liuhaoyuan.simplereader.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuhaoyuan.simplereader.ConstantValues;
import com.example.liuhaoyuan.simplereader.adapter.BaseListAdapter;
import com.example.liuhaoyuan.simplereader.adapter.movie.MovieRankAdapter;
import com.example.liuhaoyuan.simplereader.base.BaseListFragment;
import com.example.liuhaoyuan.simplereader.bean.BaseListBean;
import com.example.liuhaoyuan.simplereader.bean.MovieListBean;
import com.example.liuhaoyuan.simplereader.model.MovieModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public class MovieRankListFragment extends BaseListFragment {

    private String mRankTitle;
    private MovieModel mModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRankTitle = getArguments().getString(ConstantValues.DOUBAN_MOVIE_RANK_TITLE);
        mModel = new MovieModel();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void getMovieRankList(String start, String count, final boolean loadMore) {
        Disposable disposable = mModel.getMovieRankList(mRankTitle, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieListBean>() {
                    @Override
                    public void accept(@NonNull MovieListBean bean) throws Exception {
                        if (loadMore) {
                            addMoreListData(bean);
                        } else {
                            updateList(bean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
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
        getMovieRankList("0", "20", false);
    }

    @Override
    protected void loadMoreData(int start) {
        getMovieRankList(String.valueOf(start), "20", true);
    }

    @Override
    protected BaseListAdapter onCreateAdapter(BaseListBean bean) {
        MovieListBean data = (MovieListBean) bean;
        return new MovieRankAdapter(data.subjects, getContext());
    }

    @Override
    protected RecyclerView.LayoutManager onCreateLayoutManager() {
        return new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
    }

    @Override
    protected void setAdapterData(BaseListBean bean, boolean append) {
        if (bean instanceof MovieListBean){
            MovieListBean data = (MovieListBean) bean;
            if (append) {
                mAdapter.setData(data.subjects);
            } else {
                mAdapter.addMoreData(data.subjects);
            }
        }
    }
}
