package com.example.liuhaoyuan.simplereader.music;

import com.example.liuhaoyuan.simplereader.base.BasePresenter;
import com.example.liuhaoyuan.simplereader.base.BaseView;

import java.util.List;

/**
 * Created by liuhaoyuan on 2017/4/26.
 */

public interface MusicContract {

    interface DetailView extends BaseView<DetailPresenter>{
        void setPoster(String imageUrl);
        void setTitle(String title);
        void setRating(float rating,int max);
        void setRatingCount(int ratingCount);
        void setSummary(String summary);
        void setGenre(List<String> genre);
        void setSongList(List<String> songList);
    }

    abstract class DetailPresenter extends BasePresenter{
        public abstract void getMusicDetail(String id);
    }
}
