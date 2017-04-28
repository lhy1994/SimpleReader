package com.example.liuhaoyuan.simplereader.book;

import com.example.liuhaoyuan.simplereader.base.BasePresenter;
import com.example.liuhaoyuan.simplereader.base.BaseView;
import com.example.liuhaoyuan.simplereader.bean.book.BookItemBean;

import java.util.List;

/**
 * Created by liuhaoyuan on 17/4/26.
 */

public interface BookContract {

    interface DetailView extends BaseView {
        void setPoster(String imageUrl);

        void setTitle(String title);

        void setRating(double rating, int max);

        void setOriginTitle(String originTitle);

        void setAuthor(List<String> author);

        void setPubDate(String pubDate);

        void setPublisher(String publisher);

        void setSummary(String summary);

        void setAuthorIntro(String authorIntro);

        void setSeriesList(List<BookItemBean> list);

        void hideSeriesList();
    }

    abstract class DetailPresenter extends BasePresenter {
        public abstract void getBookDetail(String id);
        public abstract void getSeriesBooks(String id);
    }
}
