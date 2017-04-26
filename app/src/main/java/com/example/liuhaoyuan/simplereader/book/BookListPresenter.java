package com.example.liuhaoyuan.simplereader.book;

import com.example.liuhaoyuan.simplereader.base.BasePresenter;
import com.example.liuhaoyuan.simplereader.bean.BookListBean;
import com.example.liuhaoyuan.simplereader.model.BookModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liuhaoyuan on 17/4/26.
 */

public class BookListPresenter extends BookContract.ListPresenter {

    public BookListPresenter(BookContract.ListView view){
        mView=view;
        mModel=new BookModel();
    }

    @Override
    public void getBookList(String category, String start, String count, final boolean isMore) {
        Disposable disposable = mModel.getBookByTag(category, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BookListBean>() {
                    @Override
                    public void accept(@NonNull BookListBean bookListBean) throws Exception {
                        if (isMore) {
                            mView.addMoreListData(bookListBean);
                        } else {
                            mView.updateList(bookListBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        if (isMore){
                            mView.addMoreListData(null);
                        }else {
                            mView.showErrorView();
                        }
                    }
                });
        addDisposable(disposable);
    }
}
