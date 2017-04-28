package com.example.liuhaoyuan.simplereader.movie.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liuhaoyuan.simplereader.ConstantValues;
import com.example.liuhaoyuan.simplereader.R;
import com.example.liuhaoyuan.simplereader.base.BaseActivity;
import com.example.liuhaoyuan.simplereader.bean.movie.MovieHumanDetailBean;
import com.example.liuhaoyuan.simplereader.movie.MovieContract;
import com.example.liuhaoyuan.simplereader.adapter.movie.PhotosAdapter;
import com.example.liuhaoyuan.simplereader.adapter.movie.WorksAdapter;
import com.example.liuhaoyuan.simplereader.movie.presenter.MovieHumanDetailPresenter;
import com.example.liuhaoyuan.simplereader.util.DataUtils;
import com.example.liuhaoyuan.simplereader.util.ViewUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieHumanDetailActivity extends BaseActivity<MovieContract.HumanPresenter> implements MovieContract.HumanDetailView {

    @BindView(R.id.iv_movie_human)
    ImageView mMovieHumanIv;
    @BindView(R.id.tv_name)
    TextView mNameTv;
    @BindView(R.id.tv_name_en)
    TextView mNameEnTv;
    @BindView(R.id.tv_gender)
    TextView mGenderTv;
    @BindView(R.id.tv_born_place)
    TextView mBornPlaceTv;
    @BindView(R.id.tv_summary)
    TextView mSummaryTv;
    @BindView(R.id.lv_works)
    RecyclerView mWorksList;
    @BindView(R.id.lv_photos)
    RecyclerView mPhotosList;
    @BindView(R.id.tv_photos)
    TextView mPhotosTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_human_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String id = getIntent().getStringExtra(ConstantValues.DOUBAN_MOVIE_HUMAN_ID);
        mPresenter.getHumanDetail(id);
    }

    @Override
    public MovieContract.HumanPresenter onCreatePresenter() {
        return new MovieHumanDetailPresenter(this);
    }

    @Override
    public void setHumanPoster(String imageUrl) {
        Glide.with(this).load(imageUrl).into(mMovieHumanIv);
    }

    @Override
    public void setName(String name) {
        ViewUtils.setTextViewText(mNameTv, name);
    }

    @Override
    public void setNameEn(String nameEn) {
        ViewUtils.setTextViewText(mNameEnTv, nameEn);
    }

    @Override
    public void setGender(String gender) {
        ViewUtils.setTextViewText(mGenderTv, "性别：", gender);
    }

    @Override
    public void setBornPlace(String bornPlace) {
        ViewUtils.setTextViewText(mBornPlaceTv, "出生地：", bornPlace);
    }

    @Override
    public void setWorks(List<MovieHumanDetailBean.WorksBean> works) {
        WorksAdapter adapter = new WorksAdapter(this, works);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mWorksList.setLayoutManager(layoutManager);
        mWorksList.setAdapter(adapter);
    }

    @Override
    public void setHumanSummary(String summary) {
        if (!TextUtils.isEmpty(summary)) {
            mSummaryTv.setText(summary);
        } else {
            mSummaryTv.setText("暂时没有简介");
        }
    }

    @Override
    public void setHumanPhotos(List<String> photos) {
        if (DataUtils.isEmptyList(photos)) {
            mPhotosTV.setVisibility(View.GONE);
            mPhotosList.setVisibility(View.GONE);
        } else {
            PhotosAdapter adapter = new PhotosAdapter(this, photos);
            RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
            mPhotosList.setLayoutManager(layoutManager);
            mPhotosList.setAdapter(adapter);
        }
    }
}
