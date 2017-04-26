package com.example.liuhaoyuan.simplereader.music;

import com.example.liuhaoyuan.simplereader.base.BaseListView;
import com.example.liuhaoyuan.simplereader.base.BaseModel;
import com.example.liuhaoyuan.simplereader.base.BasePresenter;
import com.example.liuhaoyuan.simplereader.base.BaseView;
import com.example.liuhaoyuan.simplereader.bean.MusicListBean;

import io.reactivex.Observable;

/**
 * Created by liuhaoyuan on 2017/4/26.
 */

public interface MusicContract {
    interface Model extends BaseModel{
        Observable<MusicListBean> getMusicList(String category,String start,String count);
    }

    interface ListView extends BaseListView{

    }
    abstract class ListPresenter extends BasePresenter<ListView,Model>{
        public abstract void getMusicList(String category,String start,String count,boolean isMore);
    }

}
