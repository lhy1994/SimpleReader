package com.example.liuhaoyuan.simplereader.movie.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuhaoyuan.simplereader.ConstantValues;
import com.example.liuhaoyuan.simplereader.adapter.BaseListAdapter;
import com.example.liuhaoyuan.simplereader.base.BaseListFragment;
import com.example.liuhaoyuan.simplereader.bean.BaseListBean;
import com.example.liuhaoyuan.simplereader.bean.MovieListBean;
import com.example.liuhaoyuan.simplereader.movie.MovieContract;
import com.example.liuhaoyuan.simplereader.movie.presenter.MovieRankListPresenter;
import com.example.liuhaoyuan.simplereader.adapter.movie.MovieRankAdapter;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public class MovieRankListFragment extends BaseListFragment<MovieContract.BaseMovieListPresenter> implements MovieContract.MovieListView {

    private String mRankTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRankTitle = getArguments().getString(ConstantValues.DOUBAN_MOVIE_RANK_TITLE);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected MovieContract.BaseMovieListPresenter onCreatePresenter() {
        return new MovieRankListPresenter(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRankTitle = getArguments().getString(ConstantValues.DOUBAN_MOVIE_RANK_TITLE);
        mPresenter.getMovieList(mRankTitle, "0", "20", false);
    }

    @Override
    protected void loadData() {
        mPresenter.getMovieList(mRankTitle, "0", "20", false);
    }

    @Override
    protected void loadMoreData(int start) {
        mPresenter.getMovieList(mRankTitle, String.valueOf(start), "20", true);
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
        MovieListBean data = (MovieListBean) bean;
        if (append) {
            mAdapter.setData(data.subjects);
        } else {
            mAdapter.addMoreData(data.subjects);
        }
    }
}
