package com.example.liuhaoyuan.simplereader.movie.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.liuhaoyuan.simplereader.ConstantValues;
import com.example.liuhaoyuan.simplereader.base.BaseListFragment;
import com.example.liuhaoyuan.simplereader.bean.MovieListBean;
import com.example.liuhaoyuan.simplereader.movie.MovieContract;
import com.example.liuhaoyuan.simplereader.movie.adapter.MovieListAdapter;
import com.example.liuhaoyuan.simplereader.movie.presenter.MovieListPresenter;
import com.example.liuhaoyuan.simplereader.util.DataUtils;

/**
 * Created by liuhaoyuan on 17/4/25.
 */

public class MovieListFragment extends BaseListFragment<MovieContract.BaseMovieListPresenter> implements MovieContract.MovieListView {

    private int mCurrentCount = 0;
    private String mRankTitle;
    private int mTotal;
    private MovieListAdapter mAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String category = getArguments().getString(ConstantValues.DOUBAN_MOVIE_CATEGORY);
        mPresenter.getMovieList(category, "0", "20", false);
    }

    @Override
    public void updateList(MovieListBean bean) {
        hideLoadingView();
        hideErrorView();
        mListView.setVisibility(View.VISIBLE);
        mTotal = bean.total;
        mCurrentCount += bean.count;
        if (!DataUtils.isEmptyList(bean.subjects)) {
            if (mAdapter == null) {
                mAdapter = new MovieListAdapter(getContext(), bean.subjects);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
                mListView.setLayoutManager(layoutManager);
                mListView.setAdapter(mAdapter);
            } else {
                mAdapter.setData(bean.subjects);
                mListView.refreshComplete();
            }
        }
    }

    @Override
    public void addMoreData(MovieListBean bean) {
        if (bean == null) {
            mListView.loadMoreComplete();
            Toast.makeText(getContext(), "加载失败，请重试", Toast.LENGTH_LONG).show();
            return;
        }
        mCurrentCount += bean.count;
        if (!DataUtils.isEmptyList(bean.subjects)) {
            mAdapter.addMoreData(bean.subjects);
            mListView.loadMoreComplete();
        }
    }

    @Override
    protected MovieContract.BaseMovieListPresenter onCreatePresenter() {
        return new MovieListPresenter(this);
    }

    @Override
    public void onRefresh() {
        mCurrentCount = 0;
        mPresenter.getMovieList(mRankTitle, "0", "20", false);
    }

    @Override
    public void onLoadMore() {
        if (mCurrentCount < mTotal) {
            mPresenter.getMovieList(mRankTitle, String.valueOf(mCurrentCount), "20", true);
        } else {
            mListView.loadMoreComplete();
            Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRetryClick() {
        showLoadingView();
        mPresenter.getMovieList(mRankTitle, "0", "20", false);
    }
}
