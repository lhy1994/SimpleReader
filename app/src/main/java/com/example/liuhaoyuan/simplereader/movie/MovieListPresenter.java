package com.example.liuhaoyuan.simplereader.movie;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public class MovieListPresenter extends MovieListContract.Presenter {

    public MovieListPresenter(MovieListContract.View view) {
        this.mView=view;
        mModel=new MovieListModel();
    }

    @Override
    public void start() {

    }

}
