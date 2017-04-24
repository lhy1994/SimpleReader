package com.example.liuhaoyuan.simplereader.movie.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liuhaoyuan.simplereader.R;
import com.example.liuhaoyuan.simplereader.bean.CastBean;
import com.example.liuhaoyuan.simplereader.bean.DirectorBean;
import com.example.liuhaoyuan.simplereader.bean.ImagesBean;
import com.example.liuhaoyuan.simplereader.bean.MovieListBean;
import com.example.liuhaoyuan.simplereader.util.DataUtils;
import com.example.liuhaoyuan.simplereader.util.ViewUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuhaoyuan on 17/4/24.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private List<MovieListBean.SubjectsBean> data;
    private Context context;

    public MovieAdapter(List<MovieListBean.SubjectsBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public void setData(List<MovieListBean.SubjectsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void addMoreData(List<MovieListBean.SubjectsBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_list, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        MovieListBean.SubjectsBean subject = data.get(position);
        if (subject.images != null) {
            String imageUrl = getImageUrl(subject.images);
            if (!TextUtils.isEmpty(imageUrl)) {
                Glide.with(context).load(imageUrl).into(holder.mPosterIv);
            }
        }
        ViewUtils.setTextViewText(holder.mOriginNameTv, "原名:  ", subject.original_title);
        ViewUtils.setTextViewText(holder.mTitleTv, subject.title);
        ViewUtils.setTextViewText(holder.mYearTv, subject.year);
        ViewUtils.setTextViewText(holder.mCollectCountTv, String.valueOf(subject.collect_count));

        if (subject.rating != null) {
            ViewUtils.setTextViewText(holder.mRatingTv, subject.rating.average + "");
        }
        if (!DataUtils.isEmptyList(subject.genres)) {
            holder.mGenre.removeAllViews();
            for (String genre : subject.genres) {
                TextView textView = new TextView(context);
                ViewUtils.setTextViewText(textView, genre);
                textView.setBackgroundResource(R.drawable.bg_genre);
                textView.setTextColor(Color.WHITE);
                textView.setPadding(ViewUtils.dpTopx(context, 8),
                        ViewUtils.dpTopx(context, 2),
                        ViewUtils.dpTopx(context, 8),
                        ViewUtils.dpTopx(context, 2));
                textView.setTextSize(11);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, ViewUtils.dpTopx(context, 5), 0);
                textView.setLayoutParams(params);
                holder.mGenre.addView(textView);
            }
            holder.mGenre.setVisibility(View.VISIBLE);
        } else {
            holder.mGenre.setVisibility(View.GONE);
        }

        if (!DataUtils.isEmptyList(subject.directors)) {
            StringBuilder directorsString = new StringBuilder();
            for (DirectorBean director : subject.directors) {
                directorsString.append(director.name).append(" ");
            }
            ViewUtils.setTextViewText(holder.mDirectorsTV, "导演：", directorsString.toString());
            holder.mDirectorsTV.setVisibility(View.VISIBLE);
        } else {
            holder.mDirectorsTV.setVisibility(View.GONE);
        }
        if (!DataUtils.isEmptyList(subject.casts)) {
            StringBuilder actorsString = new StringBuilder();
            for (CastBean cast : subject.casts) {
                actorsString.append(cast.name).append(" ");
            }
            holder.mActorsTv.setText(actorsString);
            ViewUtils.setTextViewText(holder.mActorsTv, "主演：", actorsString.toString());
            holder.mActorsTv.setVisibility(View.VISIBLE);
        } else {
            holder.mActorsTv.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public String getImageUrl(ImagesBean imagesBean) {
        if (!TextUtils.isEmpty(imagesBean.large)) {
            return imagesBean.large;
        }
        if (!TextUtils.isEmpty(imagesBean.medium)) {
            return imagesBean.medium;
        }
        return imagesBean.small;
    }

    public static class MovieHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_poster)
        ImageView mPosterIv;
        @BindView(R.id.tv_movie_title)
        TextView mTitleTv;
        @BindView(R.id.tv_year)
        TextView mYearTv;
        @BindView(R.id.tv_rating)
        TextView mRatingTv;
        @BindView(R.id.genre)
        LinearLayout mGenre;
        @BindView(R.id.tv_origin_name)
        TextView mOriginNameTv;
        @BindView(R.id.tv_director)
        TextView mDirectorsTV;
        @BindView(R.id.tv_actor)
        TextView mActorsTv;
        @BindView(R.id.tv_collect_count)
        TextView mCollectCountTv;

        MovieHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


