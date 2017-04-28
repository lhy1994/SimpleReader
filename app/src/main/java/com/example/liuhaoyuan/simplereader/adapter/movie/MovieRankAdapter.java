package com.example.liuhaoyuan.simplereader.adapter.movie;

import android.content.Context;
import android.content.Intent;
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
import com.example.liuhaoyuan.simplereader.ConstantValues;
import com.example.liuhaoyuan.simplereader.R;
import com.example.liuhaoyuan.simplereader.adapter.BaseListAdapter;
import com.example.liuhaoyuan.simplereader.bean.movie.MovieHumanBean;
import com.example.liuhaoyuan.simplereader.bean.movie.MovieItemBean;
import com.example.liuhaoyuan.simplereader.movie.view.MovieDetailActivity;
import com.example.liuhaoyuan.simplereader.util.DataUtils;
import com.example.liuhaoyuan.simplereader.util.ViewUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuhaoyuan on 17/4/24.
 */

public class MovieRankAdapter extends BaseListAdapter<List<MovieItemBean>,MovieRankAdapter.MovieRankHolder> {
    private Context mContext;

    public MovieRankAdapter(List<MovieItemBean> data, Context context) {
        this.mData = data;
        this.mContext = context;
    }

    @Override
    public MovieRankHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_rank_list, parent, false);
        return new MovieRankHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieRankHolder holder, int position) {
        final MovieItemBean movieItem = mData.get(position);
        if (movieItem.images != null) {
            String imageUrl = DataUtils.getImageUrl(movieItem.images);
            if (!TextUtils.isEmpty(imageUrl)) {
                Glide.with(mContext).load(imageUrl).into(holder.mPosterIv);
            }
        }
        ViewUtils.setTextViewText(holder.mTitleTv, movieItem.title);
        ViewUtils.setTextViewText(holder.mYearTv, movieItem.year);
        ViewUtils.setTextViewText(holder.mCollectCountTv, String.valueOf(movieItem.collect_count));

        if (movieItem.rating != null) {
            ViewUtils.setTextViewText(holder.mRatingTv, movieItem.rating.average + "");
        }
        if (!DataUtils.isEmptyList(movieItem.genres)) {
            holder.mGenre.removeAllViews();
            for (String genre : movieItem.genres) {
                TextView textView = new TextView(mContext);
                ViewUtils.setTextViewText(textView, genre);
                textView.setBackgroundResource(R.drawable.bg_genre);
                textView.setTextColor(Color.WHITE);
                textView.setPadding(ViewUtils.dpTopx(mContext, 8),
                        ViewUtils.dpTopx(mContext, 2),
                        ViewUtils.dpTopx(mContext, 8),
                        ViewUtils.dpTopx(mContext, 2));
                textView.setTextSize(11);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, ViewUtils.dpTopx(mContext, 5), 0);
                textView.setLayoutParams(params);
                holder.mGenre.addView(textView);
            }
            holder.mGenre.setVisibility(View.VISIBLE);
        } else {
            holder.mGenre.setVisibility(View.GONE);
        }

        if (!DataUtils.isEmptyList(movieItem.directors)) {
            StringBuilder directorsString = new StringBuilder();
            for (MovieHumanBean director : movieItem.directors) {
                directorsString.append(director.name).append(" ");
            }
            ViewUtils.setTextViewText(holder.mDirectorsTV, "导演：", directorsString.toString());
            holder.mDirectorsTV.setVisibility(View.VISIBLE);
        } else {
            holder.mDirectorsTV.setVisibility(View.GONE);
        }
        if (!DataUtils.isEmptyList(movieItem.casts)) {
            StringBuilder actorsString = new StringBuilder();
            for (MovieHumanBean cast : movieItem.casts) {
                actorsString.append(cast.name).append(" ");
            }
            holder.mActorsTv.setText(actorsString);
            ViewUtils.setTextViewText(holder.mActorsTv, "主演：", actorsString.toString());
            holder.mActorsTv.setVisibility(View.VISIBLE);
        } else {
            holder.mActorsTv.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra(ConstantValues.DOUBAN_MOVIE_ID,movieItem.id);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MovieRankHolder extends RecyclerView.ViewHolder {

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
        @BindView(R.id.tv_director)
        TextView mDirectorsTV;
        @BindView(R.id.tv_actor)
        TextView mActorsTv;
        @BindView(R.id.tv_collect_count)
        TextView mCollectCountTv;

        MovieRankHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


