package com.example.liuhaoyuan.simplereader.music;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.liuhaoyuan.simplereader.ConstantValues;
import com.example.liuhaoyuan.simplereader.R;
import com.example.liuhaoyuan.simplereader.adapter.music.SongListAdapter;
import com.example.liuhaoyuan.simplereader.base.BaseActivity;
import com.example.liuhaoyuan.simplereader.util.DataUtils;
import com.example.liuhaoyuan.simplereader.util.ViewUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicDetailActivity extends BaseActivity<MusicContract.DetailPresenter> implements MusicContract.DetailView {

    @BindView(R.id.container)
    NestedScrollView mContainer;
    @BindView(R.id.iv_poster)
    ImageView mPosterIv;
    @BindView(R.id.tv_title)
    TextView mTitleTv;
    @BindView(R.id.tv_rating)
    TextView mRatingTv;
    @BindView(R.id.rating_bar)
    RatingBar mRatingBar;
    @BindView(R.id.tv_rating_count)
    TextView mRatingCountTv;
    @BindView(R.id.tv_summary)
    TextView mSummaryTv;
    @BindView(R.id.genre)
    LinearLayout mGenre;
    @BindView(R.id.lv_album_songs)
    RecyclerView mAlbumSongsList;
    @BindView(R.id.tv_songs)
    TextView mSongsTv;
    private SongListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_detail);
        ButterKnife.bind(this);
        String id = getIntent().getStringExtra(ConstantValues.DOUBAN_MUSIC_ID);
        mPresenter.getMusicDetail(id);
    }

    @Override
    public MusicContract.DetailPresenter onCreatePresenter() {
        return new MusicDetailPresenter(this);
    }

    @Override
    public void setPoster(String imageUrl) {
//        Glide.with(this).load(imageUrl).into(mPosterIv);
        Glide.with(this).load(imageUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                mPosterIv.setImageBitmap(resource);
                ViewUtils.getMutedColor(resource, new ViewUtils.PaletteCallBack() {
                    @Override
                    public void onColorGenerated(int color, int textColor) {
                        mContainer.setBackgroundColor(color);
                        mTitleTv.setTextColor(textColor);
                        mRatingCountTv.setTextColor(textColor);
                        mSummaryTv.setTextColor(textColor);
                        mSongsTv.setTextColor(textColor);
                        if (mAdapter!=null){
                            mAdapter.setTextColor(textColor);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void setTitle(String title) {
        ViewUtils.setTextViewText(mTitleTv, title);
    }

    @Override
    public void setRating(float rating, int max) {
        ViewUtils.setTextViewText(mRatingTv, String.valueOf(rating));
        mRatingBar.setMax(max / 2);
        mRatingBar.setRating(rating / 2);
    }

    @Override
    public void setRatingCount(int ratingCount) {
        ViewUtils.setTextViewText(mRatingCountTv, String.valueOf(ratingCount), "人评价");
    }

    @Override
    public void setSummary(String summary) {
        ViewUtils.setTextViewText(mSummaryTv, summary);
    }

    @Override
    public void setGenre(List<String> genres) {
        if (!DataUtils.isEmptyList(genres)) {
            mGenre.removeAllViews();
            for (int i = 0; i < genres.size() && i < 3; i++) {
                String genre = genres.get(i);
                TextView textView = new TextView(this);
                ViewUtils.setTextViewText(textView, genre);
                textView.setBackgroundResource(R.drawable.bg_genre);
                textView.setTextColor(Color.WHITE);
                textView.setPadding(ViewUtils.dpTopx(this, 8),
                        ViewUtils.dpTopx(this, 2),
                        ViewUtils.dpTopx(this, 8),
                        ViewUtils.dpTopx(this, 2));
                textView.setTextSize(11);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, ViewUtils.dpTopx(this, 5), 0);
                textView.setLayoutParams(params);
                mGenre.addView(textView);
            }
            mGenre.setVisibility(View.VISIBLE);
        } else {
            mGenre.setVisibility(View.GONE);
        }
    }

    @Override
    public void setSongList(List<String> songList) {
        mAdapter = new SongListAdapter(songList);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAlbumSongsList.setLayoutManager(manager);
        mAlbumSongsList.setAdapter(mAdapter);
    }
}
