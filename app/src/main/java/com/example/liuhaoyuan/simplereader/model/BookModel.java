package com.example.liuhaoyuan.simplereader.model;

import com.example.liuhaoyuan.simplereader.api.ApiEngine;
import com.example.liuhaoyuan.simplereader.bean.BookItemBean;
import com.example.liuhaoyuan.simplereader.bean.BookListBean;
import com.example.liuhaoyuan.simplereader.book.BookContract;

import io.reactivex.Observable;

/**
 * Created by liuhaoyuan on 17/4/26.
 */

public class BookModel implements BookContract.Model {
    @Override
    public Observable<BookListBean> getBookByTag(String tag,String start,String count){
        return ApiEngine.getInstance().getDouBanApiService().getBookByTag(tag,start,count);
    }
    @Override
    public Observable<BookItemBean> getBookDetail(String id){
        return ApiEngine.getInstance().getDouBanApiService().getBookDetail(id);
    }

    @Override
    public Observable<BookListBean> getSeriesBooks(String id) {
        return ApiEngine.getInstance().getDouBanApiService().getSeriesBooks(id);
    }
}
