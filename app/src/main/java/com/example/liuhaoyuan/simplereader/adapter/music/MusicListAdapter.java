package com.example.liuhaoyuan.simplereader.adapter.music;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.liuhaoyuan.simplereader.ConstantValues;
import com.example.liuhaoyuan.simplereader.R;
import com.example.liuhaoyuan.simplereader.adapter.BaseListAdapter;
import com.example.liuhaoyuan.simplereader.bean.MusicItemBean;
import com.example.liuhaoyuan.simplereader.music.MusicDetailActivity;
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
    public void onBindViewHolder(final MusicListHolder holder, int position) {
        final MusicItemBean bean = mData.get(position);
        Glide.with(mContext).load(bean.image).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                holder.mPosterIv.setImageBitmap(resource);
                ViewUtils.getDominantColor(resource, new ViewUtils.PaletteCallBack() {
                    @Override
                    public void onColorGenerated(int color, int textColor) {
                        holder.mCardView.setCardBackgroundColor(color);
                        holder.mSingerTv.setTextColor(textColor);
                        holder.mTitleTv.setTextColor(textColor);
                    }
                });
            }
        });
        ViewUtils.setTextViewText(holder.mTitleTv, bean.title);
        StringBuilder builder = new StringBuilder();
        for (String s : bean.attrs.singer) {
            builder.append(s).append(" ");
        }
        ViewUtils.setTextViewText(holder.mSingerTv, builder.toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, MusicDetailActivity.class);
                intent.putExtra(ConstantValues.DOUBAN_MUSIC_ID,bean.id);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MusicListHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_music)
        CardView mCardView;
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
