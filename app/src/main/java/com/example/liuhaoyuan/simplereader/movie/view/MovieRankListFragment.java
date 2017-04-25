package com.example.liuhaoyuan.simplereader.movie.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.liuhaoyuan.simplereader.ConstantValues;
import com.example.liuhaoyuan.simplereader.R;
import com.example.liuhaoyuan.simplereader.base.BaseFragment;
import com.example.liuhaoyuan.simplereader.base.BaseListFragment;
import com.example.liuhaoyuan.simplereader.bean.MovieListBean;
import com.example.liuhaoyuan.simplereader.movie.MovieContract;
import com.example.liuhaoyuan.simplereader.movie.presenter.MovieListPresenter;
import com.example.liuhaoyuan.simplereader.movie.adapter.MovieRankAdapter;
import com.example.liuhaoyuan.simplereader.util.DataUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public class MovieRankListFragment extends BaseListFragment<MovieContract.MovieListPresenter> implements MovieContract.MovieListView {

    private int mCurrentCount = 0;
    private String mRankTitle;
    private MovieRankAdapter mAdapter;
    private int mTotal;

    @Override
    protected MovieContract.MovieListPresenter onCreatePresenter() {
        return new MovieListPresenter(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRankTitle = getArguments().getString(ConstantValues.DOUBAN_MOVIE_RANK_TITLE);
        showLoadingView();
        mPresenter.getMovieList(mRankTitle, "0", "20", false);
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
                mAdapter = new MovieRankAdapter(bean.subjects, getContext());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                mListView.setLayoutManager(layoutManager);
                mListView.setAdapter(mAdapter);
            } else {
                mAdapter.setmData(bean.subjects);
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
