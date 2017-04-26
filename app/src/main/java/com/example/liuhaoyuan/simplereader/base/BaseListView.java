package com.example.liuhaoyuan.simplereader.base;

import com.example.liuhaoyuan.simplereader.bean.BaseListBean;

/**
 * Created by liuhaoyuan on 17/4/25.
 */

public interface BaseListView extends BaseView{
    void showLoadingView();
    void hideLoadingView();
    void showErrorView();
    void hideErrorView();

    void updateList(BaseListBean data);
    void addMoreListData(BaseListBean data);
}
