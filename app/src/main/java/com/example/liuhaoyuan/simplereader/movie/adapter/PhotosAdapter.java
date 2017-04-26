package com.example.liuhaoyuan.simplereader.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.liuhaoyuan.simplereader.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuhaoyuan on 2017/4/26.
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoHolder>{
    private Context context;
    private List<String> data;

    public PhotosAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photos_list, parent, false);
        return new PhotoHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {
        Glide.with(context).load(data.get(position)).into(holder.mPhotoIv);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class PhotoHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_photo)
        ImageView mPhotoIv;
        public PhotoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
