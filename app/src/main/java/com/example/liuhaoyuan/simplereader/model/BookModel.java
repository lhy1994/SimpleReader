package com.example.liuhaoyuan.simplereader.model;

import com.example.liuhaoyuan.simplereader.api.ApiEngine;
import com.example.liuhaoyuan.simplereader.api.DouBanApiService;
import com.example.liuhaoyuan.simplereader.bean.BookItemBean;
import com.example.liuhaoyuan.simplereader.bean.BookListBean;
import com.example.liuhaoyuan.simplereader.book.BookContract;

import io.reactivex.Observable;

/**
 * Created by liuhaoyuan on 17/4/26.
 */

public class BookModel {
    private static BookModel instance;
    private DouBanApiService mDoubanService;

    private BookModel(){
        mDoubanService=ApiEngine.getInstance().getDouBanApiService();
    }

    public static synchronized BookModel getInstance(){
        if (instance==null){
            instance=new BookModel();
        }
        return instance;
    }

    public Observable<BookListBean> getBookByTag(String tag,String start,String count){
        return mDoubanService.getBookByTag(tag,start,count);
    }
    public Observable<BookItemBean> getBookDetail(String id){
        return mDoubanService.getBookDetail(id);
    }

    public Observable<BookListBean> getSeriesBooks(String id) {
        return mDoubanService.getSeriesBooks(id);
    }
}
