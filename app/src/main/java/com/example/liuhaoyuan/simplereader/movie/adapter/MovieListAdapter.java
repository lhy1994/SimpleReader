package com.example.liuhaoyuan.simplereader.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liuhaoyuan.simplereader.R;
import com.example.liuhaoyuan.simplereader.bean.ImagesBean;
import com.example.liuhaoyuan.simplereader.bean.MovieItemBean;
import com.example.liuhaoyuan.simplereader.bean.MovieListBean;
import com.example.liuhaoyuan.simplereader.util.DataUtils;
import com.example.liuhaoyuan.simplereader.util.ViewUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuhaoyuan on 17/4/25.
 */

public class MovieListAdapter extends RecyclerView.Adapter <MovieListAdapter.MovieListHolder>{
    private Context mContext;
    private List<MovieItemBean> mData;

    public MovieListAdapter(Context context, List<MovieItemBean> data) {
        this.mContext = context;
        this.mData = data;
    }

    public void setData(List<MovieItemBean> data){
        mData=data;
        notifyDataSetChanged();
    }

    public void addMoreData(List<MovieItemBean> data){
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public MovieListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_list, parent, false);
        return new MovieListHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieListHolder holder, int position) {
        String imageUrl = DataUtils.getImageUrl(mData.get(position).images);
        Glide.with(mContext).load(imageUrl).into(holder.mPosterIv);
        ViewUtils.setTextViewText(holder.mTitleTv,mData.get(position).title);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class MovieListHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_poster)
        ImageView mPosterIv;
        @BindView(R.id.tv_title)
        TextView mTitleTv;
        public MovieListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
