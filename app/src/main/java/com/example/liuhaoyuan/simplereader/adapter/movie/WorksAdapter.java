package com.example.liuhaoyuan.simplereader.adapter.movie;

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
import com.example.liuhaoyuan.simplereader.bean.movie.MovieHumanDetailBean;
import com.example.liuhaoyuan.simplereader.movie.view.MovieDetailActivity;
import com.example.liuhaoyuan.simplereader.util.DataUtils;
import com.example.liuhaoyuan.simplereader.util.ViewUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuhaoyuan on 2017/4/26.
 */

public class WorksAdapter extends RecyclerView.Adapter<WorksAdapter.WorksHolder> {
    private Context mContext;
    private List<MovieHumanDetailBean.WorksBean> mData;

    public WorksAdapter(Context context, List<MovieHumanDetailBean.WorksBean> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public WorksHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_works_list, parent, false);
        return new WorksHolder(view);
    }

    @Override
    public void onBindViewHolder(WorksHolder holder, int position) {
        final MovieHumanDetailBean.WorksBean worksBean = mData.get(position);
        String imageUrl = DataUtils.getImageUrl(worksBean.subject.images);
        Glide.with(mContext).load(imageUrl).into(holder.mPosterIv);
        ViewUtils.setTextViewText(holder.mTitleTv,worksBean.subject.title);
        StringBuilder builder=new StringBuilder();
        for (String role : worksBean.roles) {
            builder.append(role).append(" ");
        }
        ViewUtils.setTextViewText(holder.mRolesTv,builder.toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra(ConstantValues.DOUBAN_MOVIE_ID,worksBean.subject.id);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class WorksHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_poster)
        ImageView mPosterIv;
        @BindView(R.id.tv_title)
        TextView mTitleTv;
        @BindView(R.id.tv_roles)
        TextView mRolesTv;

        public WorksHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
