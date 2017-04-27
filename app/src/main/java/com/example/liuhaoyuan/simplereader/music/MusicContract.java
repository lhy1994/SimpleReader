package com.example.liuhaoyuan.simplereader.music;

import com.example.liuhaoyuan.simplereader.base.BaseModel;
import com.example.liuhaoyuan.simplereader.base.BasePresenter;
import com.example.liuhaoyuan.simplereader.base.BaseView;
import com.example.liuhaoyuan.simplereader.bean.MusicItemBean;
import com.example.liuhaoyuan.simplereader.bean.MusicListBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by liuhaoyuan on 2017/4/26.
 */

public interface MusicContract {
    interface Model extends BaseModel{
        Observable<MusicListBean> getMusicList(String category,String start,String count);
        Observable<MusicItemBean> getMusicDetail(String id);
    }

    interface DetailView extends BaseView{
        void setPoster(String imageUrl);
        void setTitle(String title);
        void setRating(float rating,int max);
        void setRatingCount(int ratingCount);
        void setSummary(String summary);
        void setGenre(List<String> genre);
        void setSongList(List<String> songList);
    }

    abstract class DetailPresenter extends BasePresenter<DetailView,Model>{
        public abstract void getMusicDetail(String id);
    }
}
