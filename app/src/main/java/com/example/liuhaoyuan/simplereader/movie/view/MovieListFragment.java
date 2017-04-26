package com.example.liuhaoyuan.simplereader.movie.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuhaoyuan.simplereader.ConstantValues;
import com.example.liuhaoyuan.simplereader.adapter.BaseListAdapter;
import com.example.liuhaoyuan.simplereader.base.BaseListFragment;
import com.example.liuhaoyuan.simplereader.bean.BaseListBean;
import com.example.liuhaoyuan.simplereader.bean.MovieItemBean;
import com.example.liuhaoyuan.simplereader.bean.MovieListBean;
import com.example.liuhaoyuan.simplereader.movie.MovieContract;
import com.example.liuhaoyuan.simplereader.adapter.movie.MovieListAdapter;
import com.example.liuhaoyuan.simplereader.movie.presenter.MovieListPresenter;

import java.util.List;

/**
 * Created by liuhaoyuan on 17/4/25.
 */

public class MovieListFragment extends BaseListFragment<MovieContract.BaseMovieListPresenter> implements MovieContract.MovieListView {

    private String mCategory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCategory = getArguments().getString(ConstantValues.DOUBAN_MOVIE_CATEGORY);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void loadData() {
        mPresenter.getMovieList(mCategory, "0", "20", false);
    }

    @Override
    protected void loadMoreData(int start) {
        mPresenter.getMovieList(mCategory, String.valueOf(start), "20", true);
    }

    @Override
    protected BaseListAdapter<List<MovieItemBean>, MovieListAdapter.MovieListHolder> onCreateAdapter(BaseListBean bean) {
        MovieListBean data = (MovieListBean) bean;
        return new MovieListAdapter(getContext(), data.subjects);
    }

    @Override
    protected RecyclerView.LayoutManager onCreateLayoutManager() {
        return new GridLayoutManager(getContext(), 3);
    }

    @Override
    protected void setAdapterData(BaseListBean bean, boolean append) {
        MovieListBean data= (MovieListBean) bean;
        if (append){
            mAdapter.addMoreData(data.subjects);
        }else {
            mAdapter.setData(data.subjects);
        }
    }

    @Override
    protected MovieContract.BaseMovieListPresenter onCreatePresenter() {
        return new MovieListPresenter(this);
    }
}
