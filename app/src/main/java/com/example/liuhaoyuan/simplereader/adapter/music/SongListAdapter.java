package com.example.liuhaoyuan.simplereader.adapter.music;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liuhaoyuan.simplereader.R;
import com.example.liuhaoyuan.simplereader.util.ViewUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuhaoyuan on 2017/4/27.
 */

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.SongListHolder> {
    private List<String> mData;
    private int mTextColor=0;

    public SongListAdapter(List<String> data) {
        this.mData = data;
    }

    public void setTextColor(int textColor){
        mTextColor=textColor;
        notifyDataSetChanged();
    }

    @Override
    public SongListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album_songs, parent, false);
        return new SongListHolder(view);
    }

    @Override
    public void onBindViewHolder(SongListHolder holder, int position) {
        ViewUtils.setTextViewText(holder.mPositionTv,String.valueOf(position+1));
        ViewUtils.setTextViewText(holder.mTitleTv,mData.get(position));
        if (mTextColor!=0){
            holder.mTitleTv.setTextColor(mTextColor);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class SongListHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_position)
        TextView mPositionTv;
        @BindView(R.id.tv_title)
        TextView mTitleTv;
        public SongListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
