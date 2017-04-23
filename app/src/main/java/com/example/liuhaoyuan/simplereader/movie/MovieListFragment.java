package com.example.liuhaoyuan.simplereader.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.liuhaoyuan.simplereader.ConstantValues;
import com.example.liuhaoyuan.simplereader.base.BaseFragment;
import com.example.liuhaoyuan.simplereader.bean.MovieListBean;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public class MovieListFragment extends BaseFragment<MovieListContract.Presenter> implements MovieListContract.View{
    @Override
    protected MovieListContract.Presenter onCreatePresenter() {
        return new MovieListPresenter(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String rankTitle = getArguments().getString(ConstantValues.DOUBAN_RANK_TITLE);
        mPresenter.getMovieList(rankTitle);
    }

    @Override
    public void updateList(MovieListBean bean) {

    }
}
