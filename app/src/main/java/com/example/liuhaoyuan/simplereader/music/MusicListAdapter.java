package com.example.liuhaoyuan.simplereader.music;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liuhaoyuan.simplereader.R;
import com.example.liuhaoyuan.simplereader.base.BaseListAdapter;
import com.example.liuhaoyuan.simplereader.bean.MusicItemBean;
import com.example.liuhaoyuan.simplereader.bean.MusicListBean;
import com.example.liuhaoyuan.simplereader.util.ViewUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuhaoyuan on 2017/4/26.
 */

public class MusicListAdapter extends BaseListAdapter<List<MusicItemBean>, MusicListAdapter.MusicListHolder> {
    private Context mContext;

    public MusicListAdapter(Context context, List<MusicItemBean> data) {
        this.mContext = context;
        mData = data;
    }

    @Override
    public MusicListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music_list, parent, false);
        return new MusicListHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicListHolder holder, int position) {
        MusicItemBean bean = mData.get(position);
        Glide.with(mContext).load(bean.image).into(holder.mPosterIv);
        ViewUtils.setTextViewText(holder.mTitleTv, bean.title);
        StringBuilder builder = new StringBuilder();
        for (String s : bean.attrs.singer) {
            builder.append(s).append(" ");
        }
        ViewUtils.setTextViewText(holder.mSingerTv, builder.toString());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MusicListHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_poster)
        ImageView mPosterIv;
        @BindView(R.id.tv_title)
        TextView mTitleTv;
        @BindView(R.id.tv_singer)
        TextView mSingerTv;

        public MusicListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
