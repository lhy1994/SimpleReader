package com.example.liuhaoyuan.simplereader.movie;

import com.example.liuhaoyuan.simplereader.base.BaseFragment;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public class MovieListFragment extends BaseFragment<MovieListContract.Presenter> implements MovieListContract.View{
    @Override
    protected MovieListContract.Presenter onCreatePresenter() {
        return new MovieListPresenter(this);
    }
}
