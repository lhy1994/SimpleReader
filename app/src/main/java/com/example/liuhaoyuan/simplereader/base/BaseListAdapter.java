package com.example.liuhaoyuan.simplereader.base;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by liuhaoyuan on 2017/4/26.
 */

public abstract class BaseListAdapter<T extends List, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected T mData;

    public void setData(T data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void addMoreData(T data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }
}
