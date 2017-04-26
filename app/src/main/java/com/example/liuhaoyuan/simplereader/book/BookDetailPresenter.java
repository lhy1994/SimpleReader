package com.example.liuhaoyuan.simplereader.book;

import com.example.liuhaoyuan.simplereader.bean.BookItemBean;
import com.example.liuhaoyuan.simplereader.bean.BookListBean;
import com.example.liuhaoyuan.simplereader.model.BookModel;
import com.example.liuhaoyuan.simplereader.util.DataUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liuhaoyuan on 17/4/26.
 */

public class BookDetailPresenter extends BookContract.DetailPresenter{
    public BookDetailPresenter (BookContract.DetailView view){
        mView=view;
        mModel=new BookModel();
    }
    @Override
    public void getBookDetail(String id) {
        Disposable disposable = mModel.getBookDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BookItemBean>() {
                    @Override
                    public void accept(@NonNull BookItemBean bookItemBean) throws Exception {
                        String imageUrl = DataUtils.getImageUrl(bookItemBean.images);
                        mView.setPoster(imageUrl);
                        mView.setTitle(bookItemBean.titleX);
                        mView.setRating(bookItemBean.rating.average,bookItemBean.rating.max);
                        mView.setOriginTitle(bookItemBean.origin_title);
                        mView.setAuthor(bookItemBean.author);
                        mView.setPubDate(bookItemBean.pubdate);
                        mView.setPublisher(bookItemBean.publisher);
                        mView.setAuthorIntro(bookItemBean.author_intro);
                        getSeriesBooks(bookItemBean.series.id);
                    }
                });
        addDisposable(disposable);
    }

    @Override
    public void getSeriesBooks(String id) {
        Disposable disposable = mModel.getSeriesBooks(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BookListBean>() {
                    @Override
                    public void accept(@NonNull BookListBean bookListBean) throws Exception {
                        mView.setSeriesList(bookListBean.books);
                    }
                });
        addDisposable(disposable);
    }
}