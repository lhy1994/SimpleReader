package com.example.liuhaoyuan.simplereader.model;

import com.example.liuhaoyuan.simplereader.api.ApiEngine;
import com.example.liuhaoyuan.simplereader.bean.MusicListBean;
import com.example.liuhaoyuan.simplereader.music.MusicContract;

import io.reactivex.Observable;

/**
 * Created by liuhaoyuan on 2017/4/26.
 */

public class MusicModel implements MusicContract.Model{
    @Override
    public Observable<MusicListBean> getMusicList(String category, String start, String count) {
        return ApiEngine.getInstance().getDouBanApiService().getMusicByTag(category,start,count);
    }
}
