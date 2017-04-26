package com.example.liuhaoyuan.simplereader.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.liuhaoyuan.simplereader.R;
import com.example.liuhaoyuan.simplereader.bean.BaseListBean;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuhaoyuan on 2017/4/25.
 */

public abstract class BaseListFragment<P extends BasePresenter> extends BaseFragment<P>
        implements BaseListView, XRecyclerView.LoadingListener {

    @BindView(R.id.list)
    protected XRecyclerView mListView;
    @BindView(R.id.loading_view)
    protected ProgressBar mLoadingView;
    @BindView(R.id.error_view)
    protected FrameLayout mErrorView;
    protected int mTotal;
    protected int mCurrentCount;
    protected BaseListAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

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
        loadData();
    }

    protected abstract void loadData();

    protected abstract void loadMoreData(int start);

    @Override
    public void updateList(BaseListBean bean) {
        hideLoadingView();
        hideErrorView();
        mListView.setVisibility(View.VISIBLE);
        mTotal = bean.total;
        mCurrentCount += bean.count;
        if (mAdapter == null) {
            mAdapter = onCreateAdapter(bean);
            mLayoutManager = onCreateLayoutManager();
            mListView.setLayoutManager(mLayoutManager);
            mListView.setAdapter(mAdapter);
        } else {
            setAdapterData(bean,false);
            mListView.refreshComplete();
        }
    }

    @Override
    public void addMoreListData(BaseListBean bean) {
        if (bean == null) {
            mListView.loadMoreComplete();
            Toast.makeText(getContext(), "加载失败，请重试", Toast.LENGTH_LONG).show();
            return;
        }
        mCurrentCount += bean.count;
        setAdapterData(bean, true);
        mListView.loadMoreComplete();
    }

    protected abstract BaseListAdapter onCreateAdapter(BaseListBean bean);

    protected abstract RecyclerView.LayoutManager onCreateLayoutManager();

    protected abstract void setAdapterData(BaseListBean bean, boolean append);


    @Override
    protected abstract P onCreatePresenter();

    @Override
    public void showLoadingView() {
        mLoadingView.setVisibility(View.VISIBLE);
        mListView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadingView() {
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showErrorView() {
        mErrorView.setVisibility(View.VISIBLE);
        mListView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void hideErrorView() {
        mErrorView.setVisibility(View.GONE);
    }

    @Override
    public  void onRefresh(){
        mCurrentCount=0;
        loadData();
    }

    @Override
    public  void onLoadMore(){
        if (mCurrentCount < mTotal) {
            loadMoreData(mCurrentCount);
        } else {
            mListView.loadMoreComplete();
            Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.btn_retry)
    public void onRetryClick(){
        showLoadingView();
        loadData();
    }
}
