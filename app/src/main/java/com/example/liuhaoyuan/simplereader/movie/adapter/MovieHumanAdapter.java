package com.example.liuhaoyuan.simplereader.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liuhaoyuan.simplereader.ConstantValues;
import com.example.liuhaoyuan.simplereader.R;
import com.example.liuhaoyuan.simplereader.bean.MovieHumanBean;
import com.example.liuhaoyuan.simplereader.movie.view.MovieHumanDetailActivity;
import com.example.liuhaoyuan.simplereader.util.DataUtils;
import com.example.liuhaoyuan.simplereader.util.ViewUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuhaoyuan on 2017/4/25.
 */

public class MovieHumanAdapter extends RecyclerView.Adapter<MovieHumanAdapter.MovieHumanHolder> {
    private List<MovieHumanBean> mData;
    private Context mContext;

    public MovieHumanAdapter(Context context, List<MovieHumanBean> data) {
        this.mData = data;
        this.mContext = context;
    }

    @Override
    public MovieHumanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_human, parent, false);
        return new MovieHumanHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHumanHolder holder, int position) {
        final MovieHumanBean humanBean = mData.get(position);
        String imageUrl = DataUtils.getImageUrl(humanBean.avatars);
        Glide.with(mContext).load(imageUrl).into(holder.mMovieHumanIv);
        ViewUtils.setTextViewText(holder.mMovieHumanNameTv, humanBean.name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, MovieHumanDetailActivity.class);
                intent.putExtra(ConstantValues.DOUBAN_MOVIE_HUMAN_ID,humanBean.id);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MovieHumanHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_movie_human)
        ImageView mMovieHumanIv;
        @BindView(R.id.tv_movie_human_name)
        TextView mMovieHumanNameTv;

        public MovieHumanHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
