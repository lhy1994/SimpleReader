package com.example.liuhaoyuan.simplereader.book;

import android.media.MediaDataSource;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuhaoyuan.simplereader.ConstantValues;
import com.example.liuhaoyuan.simplereader.adapter.BaseListAdapter;
import com.example.liuhaoyuan.simplereader.adapter.book.BookListAdapter;
import com.example.liuhaoyuan.simplereader.base.BaseListFragment;
import com.example.liuhaoyuan.simplereader.bean.BaseListBean;
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

public class BookListFragment extends BaseListFragment{

    private String mCategory;
    private BookModel mModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCategory = getArguments().getString(ConstantValues.DOUBAN_BOOK_CATEGORY);
        mModel = BookModel.getInstance();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void getBookList(String start, String count, final boolean isMore) {
        Disposable disposable = mModel.getBookByTag(mCategory, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BookListBean>() {
                    @Override
                    public void accept(@NonNull BookListBean bookListBean) throws Exception {
                        if (isMore) {
                            addMoreListData(bookListBean);
                        } else {
                            updateList(bookListBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        if (isMore){
                            addMoreListData(null);
                        }else {
                            showErrorView();
                        }
                    }
                });
        addDisposable(disposable);
    }

    @Override
    protected void loadData() {
        getBookList("0", "20", false);
    }

    @Override
    protected void loadMoreData(int start) {
        getBookList(String.valueOf(start), "20", true);
    }

    @Override
    protected BaseListAdapter onCreateAdapter(BaseListBean bean) {
        BookListBean data = (BookListBean) bean;
        return new BookListAdapter(getContext(), data.books);
    }

    @Override
    protected RecyclerView.LayoutManager onCreateLayoutManager() {
        return new GridLayoutManager(getContext(),2);
    }

    @Override
    protected void setAdapterData(BaseListBean bean, boolean append) {
        if (bean instanceof BookListBean){
            BookListBean data= (BookListBean) bean;
            if (append){
                mAdapter.addMoreData(data.books);
            }else {
                mAdapter.addMoreData(data.books);
            }
        }
    }
}
