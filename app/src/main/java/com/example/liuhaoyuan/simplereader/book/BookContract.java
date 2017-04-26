package com.example.liuhaoyuan.simplereader.book;

import com.example.liuhaoyuan.simplereader.base.BaseListView;
import com.example.liuhaoyuan.simplereader.base.BaseModel;
import com.example.liuhaoyuan.simplereader.base.BasePresenter;
import com.example.liuhaoyuan.simplereader.base.BaseView;
import com.example.liuhaoyuan.simplereader.bean.BookItemBean;
import com.example.liuhaoyuan.simplereader.bean.BookListBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by liuhaoyuan on 17/4/26.
 */

public interface BookContract {
    interface Model extends BaseModel {
        Observable<BookListBean> getBookByTag(String tag, String start, String count);

        Observable<BookItemBean> getBookDetail(String id);

        Observable<BookListBean> getSeriesBooks(String id);
    }

    interface ListView extends BaseListView {

    }

    abstract class ListPresenter extends BasePresenter<ListView, Model> {
        public abstract void getBookList(String category, String start, String count, boolean isMore);
    }

    interface DetailView extends BaseView {
        void setPoster(String imageUrl);

        void setTitle(String title);

        void setRating(double rating, int max);

        void setOriginTitle(String originTitle);

        void setAuthor(List<String> author);

        void setPubDate(String pudate);

        void setPublisher(String publisher);

        void setAuthorIntro(String authorIntro);

        void setSeriesList(List<BookItemBean> list);
    }

    abstract class DetailPresenter extends BasePresenter<DetailView, Model> {
        public abstract void getBookDetail(String id);
        public abstract void getSeriesBooks(String id);
    }
}
