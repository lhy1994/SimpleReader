package com.example.liuhaoyuan.simplereader.model;

import com.example.liuhaoyuan.simplereader.api.ApiEngine;
import com.example.liuhaoyuan.simplereader.api.DouBanApiService;
import com.example.liuhaoyuan.simplereader.api.QQApiService;
import com.example.liuhaoyuan.simplereader.bean.music.MusicItemBean;
import com.example.liuhaoyuan.simplereader.bean.music.MusicListBean;
import com.example.liuhaoyuan.simplereader.bean.music.QQMusicBean;

import io.reactivex.Observable;

/**
 * Created by liuhaoyuan on 2017/4/26.
 */

public class MusicModel {
    private static MusicModel instance;
    private DouBanApiService mDoubanService;
    private QQApiService mQQService;

    private MusicModel() {
        mDoubanService = ApiEngine.getInstance().getDouBanApiService();
        mQQService = ApiEngine.getInstance().getQQApiService();
    }

    public synchronized static MusicModel getInstance() {
        if (instance == null) {
            instance = new MusicModel();
        }
        return instance;
    }

    public Observable<MusicListBean> getMusicList(String category, String start, String count) {
        return mDoubanService.getMusicByTag(category, start, count);
    }

    public Observable<MusicItemBean> getMusicDetail(String id) {
        return mDoubanService.getMusicDetail(id);
    }

    public Observable<QQMusicBean> getQQMusicList(String keyword, String page) {
        return mQQService.getMusicByKey(QQApiService.APP_ID, QQApiService.SECRECT, keyword, page);
    }
}
