package com.example.liuhaoyuan.simplereader.music;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuhaoyuan.simplereader.ConstantValues;
import com.example.liuhaoyuan.simplereader.base.BaseListAdapter;
import com.example.liuhaoyuan.simplereader.base.BaseListFragment;
import com.example.liuhaoyuan.simplereader.bean.BaseListBean;
import com.example.liuhaoyuan.simplereader.bean.MusicListBean;

/**
 * Created by liuhaoyuan on 2017/4/26.
 */

public class MusicListFragment extends BaseListFragment<MusicContract.ListPresenter> implements MusicContract.ListView {

    private String mCategory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCategory = getArguments().getString(ConstantValues.DOUBAN_MUSIC_CATEGORY);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void loadData() {
        mPresenter.getMusicList(mCategory, "0", "20", false);
    }

    @Override
    protected void loadMoreData(int start) {
        mPresenter.getMusicList(mCategory, String.valueOf(start), "20", true);
    }

    @Override
    protected BaseListAdapter onCreateAdapter(BaseListBean bean) {
        MusicListBean data = (MusicListBean) bean;
        return new MusicListAdapter(getContext(), data.musics);
    }

    @Override
    protected RecyclerView.LayoutManager onCreateLayoutManager() {
        return new GridLayoutManager(getContext(),2);
    }

    @Override
    protected void setAdapterData(BaseListBean bean, boolean append) {
        MusicListBean data= (MusicListBean) bean;
        if (append){
            mAdapter.addMoreData(data.musics);
        }else {
            mAdapter.setData(data.musics);
        }
    }

    @Override
    protected MusicContract.ListPresenter onCreatePresenter() {
        return new MusicListPresenter(this);
    }
}
