package com.example.liuhaoyuan.simplereader.model;

import com.example.liuhaoyuan.simplereader.api.ApiEngine;
import com.example.liuhaoyuan.simplereader.api.DouBanApiService;
import com.example.liuhaoyuan.simplereader.bean.MusicItemBean;
import com.example.liuhaoyuan.simplereader.bean.MusicListBean;
import com.example.liuhaoyuan.simplereader.music.MusicContract;

import io.reactivex.Observable;

/**
 * Created by liuhaoyuan on 2017/4/26.
 */

public class MusicModel{
    private static MusicModel instance;
    private DouBanApiService mDoubanService;
    private MusicModel(){
        mDoubanService=ApiEngine.getInstance().getDouBanApiService();
    }
    public synchronized static MusicModel getInstance(){
        if (instance==null){
            instance=new MusicModel();
        }
        return instance;
    }

    public Observable<MusicListBean> getMusicList(String category, String start, String count) {
        return mDoubanService.getMusicByTag(category,start,count);
    }

    public Observable<MusicItemBean> getMusicDetail(String id){
        return mDoubanService.getMusicDetail(id);
    }
}
