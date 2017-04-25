package com.example.liuhaoyuan.simplereader.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.liuhaoyuan.simplereader.R;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuhaoyuan on 2017/4/25.
 */

public abstract class BaseListFragment<P extends BasePresenter>extends  BaseFragment<P> implements XRecyclerView.LoadingListener {

    @BindView(R.id.list)
    protected XRecyclerView mListView;
    @BindView(R.id.loading_view)
    protected ProgressBar mLoadingView;
    @BindView(R.id.btn_retry)
    protected Button mRetryBtn;
    @BindView(R.id.error_view)
    protected FrameLayout mErrorView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = View.inflate(getContext(), R.layout.fragment_base_list, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView.setLoadingListener(this);
        mListView.setLoadingMoreEnabled(true);
        mListView.setLoadingMoreProgressStyle(ProgressStyle.SysProgress);
        mListView.setRefreshProgressStyle(ProgressStyle.SysProgress);
        showLoadingView();
    }

    @Override
    protected abstract P onCreatePresenter();

    public void showLoadingView(){
        mLoadingView.setVisibility(View.VISIBLE);
        mListView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
    }

    public void hideLoadingView(){
        mLoadingView.setVisibility(View.GONE);
    }

    public void showErrorView(){
        mErrorView.setVisibility(View.VISIBLE);
        mListView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
    }
    public void hideErrorView(){
        mErrorView.setVisibility(View.GONE);
    }

    @Override
    public abstract void onRefresh();

    @Override
    public abstract void onLoadMore();

    @OnClick(R.id.btn_retry)
    public abstract void onRetryClick();
}
