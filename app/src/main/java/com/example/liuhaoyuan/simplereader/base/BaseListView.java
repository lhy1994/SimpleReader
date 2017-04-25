package com.example.liuhaoyuan.simplereader.base;

/**
 * Created by liuhaoyuan on 17/4/25.
 */

public interface BaseListView extends BaseView{
    void showLoadingView();
    void hideLoadingView();
    void showErrorView();
    void hideErrorView();
}
