package com.example.liuhaoyuan.simplereader.movie;

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
import com.example.liuhaoyuan.simplereader.bean.MovieListBean;
import com.example.liuhaoyuan.simplereader.movie.adapter.MovieAdapter;
import com.example.liuhaoyuan.simplereader.util.DataUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public class MovieListFragment extends BaseFragment<MovieListContract.Presenter> implements MovieListContract.View, XRecyclerView.LoadingListener, View.OnClickListener {

    private XRecyclerView mMovieListView;
    private int mCurrentCount = 0;
    private String mRankTitle;
    private MovieAdapter mAdapter;
    private ProgressBar mLoadingView;
    private FrameLayout mErrorView;
    private int mTotal;

    @Override
    protected MovieListContract.Presenter onCreatePresenter() {
        return new MovieListPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = View.inflate(getContext(), R.layout.fragment_moive_list, null);
        mMovieListView = (XRecyclerView) view.findViewById(R.id.lv_movie);
        mMovieListView.setLoadingMoreEnabled(true);
        mMovieListView.setRefreshProgressStyle(ProgressStyle.SysProgress);
        mMovieListView.setLoadingMoreProgressStyle(ProgressStyle.BallScaleRipple);
        mMovieListView.setLoadingListener(this);
        mLoadingView = (ProgressBar) view.findViewById(R.id.loading_view);
        mErrorView = (FrameLayout) view.findViewById(R.id.error_view);
        Button mRetryBtn = (Button) view.findViewById(R.id.btn_retry);
        mRetryBtn.setOnClickListener(this);

        mRankTitle = getArguments().getString(ConstantValues.DOUBAN_MOVIE_RANK_TITLE);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoadingView();
        mPresenter.getMovieList(mRankTitle, "0", "20", false);
    }

    @Override
    public void updateList(MovieListBean bean) {
        hideLoadingView();
        hideErrorView();
        mMovieListView.setVisibility(View.VISIBLE);
        mTotal = bean.total;
        mCurrentCount += bean.count;
        if (!DataUtils.isEmptyList(bean.subjects)) {
            if (mAdapter == null) {
                mAdapter = new MovieAdapter(bean.subjects, getContext());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                mMovieListView.setLayoutManager(layoutManager);
                mMovieListView.setAdapter(mAdapter);
            } else {
                mAdapter.setData(bean.subjects);
                mMovieListView.refreshComplete();
            }
        }
    }

    @Override
    public void addMoreData(MovieListBean bean) {
        if (bean == null) {
            mMovieListView.loadMoreComplete();
            Toast.makeText(getContext(), "加载失败，请重试", Toast.LENGTH_LONG).show();
            return;
        }
        mCurrentCount += bean.count;
        if (!DataUtils.isEmptyList(bean.subjects)) {
            mAdapter.addMoreData(bean.subjects);
            mMovieListView.loadMoreComplete();
        }
    }

    @Override
    public void showLoadingView() {
        mLoadingView.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
        mMovieListView.setVisibility(View.GONE);
    }

    @Override
    public void showErrorView() {
        mErrorView.setVisibility(View.VISIBLE);
        mLoadingView.setVisibility(View.GONE);
        mMovieListView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadingView() {
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void hideErrorView() {
        mErrorView.setVisibility(View.GONE);
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
            mMovieListView.loadMoreComplete();
            Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_retry) {
            showLoadingView();
            mPresenter.getMovieList(mRankTitle, "0", "20", false);
        }
    }
}
