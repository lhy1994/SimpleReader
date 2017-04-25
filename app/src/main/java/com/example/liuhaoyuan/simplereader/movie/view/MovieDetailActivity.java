package com.example.liuhaoyuan.simplereader.movie.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liuhaoyuan.simplereader.ConstantValues;
import com.example.liuhaoyuan.simplereader.R;
import com.example.liuhaoyuan.simplereader.base.BaseActivity;
import com.example.liuhaoyuan.simplereader.bean.MovieDetailBean;
import com.example.liuhaoyuan.simplereader.movie.MovieContract;
import com.example.liuhaoyuan.simplereader.movie.presenter.MovieDetailPresenter;
import com.example.liuhaoyuan.simplereader.movie.adapter.MovieHumanAdapter;
import com.example.liuhaoyuan.simplereader.util.DataUtils;
import com.example.liuhaoyuan.simplereader.util.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends BaseActivity<MovieContract.MovieDetailPresenter> implements MovieContract.MovieDetailView {

    @BindView(R.id.header_view)
    RelativeLayout mHeaderView;
    @BindView(R.id.iv_poster)
    ImageView mPosterIv;
    @BindView(R.id.card_poster)
    CardView mPosterCard;
    @BindView(R.id.tv_title)
    TextView mTitleTv;
    @BindView(R.id.tv_rating)
    TextView mRatingTv;
    @BindView(R.id.rating_bar)
    RatingBar mRatingBar;
    @BindView(R.id.tv_rating_count)
    TextView mRatingCountTv;
    @BindView(R.id.tv_country)
    TextView mCountryTv;
    @BindView(R.id.tv_year)
    TextView mYearTv;
    @BindView(R.id.genre)
    LinearLayout mGenre;
    @BindView(R.id.tv_summary)
    TextView mSummaryTv;
    @BindView(R.id.lv_movie_directors)
    RecyclerView mMovieDirectorsList;
    @BindView(R.id.lv_movie_casts)
    RecyclerView mMovieCastsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        mPresenter.getMovieDetail(getIntent().getStringExtra(ConstantValues.DOUBAN_MOVIE_ID));
    }

    @Override
    public MovieContract.MovieDetailPresenter onCreatePresenter() {
        return new MovieDetailPresenter(this);
    }

    @Override
    public void initUi(MovieDetailBean bean) {
        String imageUrl = DataUtils.getImageUrl(bean.images);
        if (!TextUtils.isEmpty(imageUrl)) {
            Glide.with(this).load(imageUrl).into(mPosterIv);
        }
        ViewUtils.setTextViewText(mTitleTv, bean.title);
        if (bean.rating != null) {
            ViewUtils.setTextViewText(mRatingTv, String.valueOf(bean.rating.average));
            mRatingBar.setMax(bean.rating.max);
            mRatingBar.setRating((float) bean.rating.average);
        }
        ViewUtils.setTextViewText(mRatingCountTv, String.valueOf(bean.ratings_count), "评分");
        StringBuilder builder = new StringBuilder();
        for (String country : bean.countries) {
            builder.append(country).append(" ");
        }
        ViewUtils.setTextViewText(mCountryTv, builder.toString());
        ViewUtils.setTextViewText(mYearTv, bean.year);

        if (!DataUtils.isEmptyList(bean.genres)) {
            mGenre.removeAllViews();
            for (String genre : bean.genres) {
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
        ViewUtils.setTextViewText(mSummaryTv,bean.summary);

        RecyclerView.LayoutManager directorsManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        MovieHumanAdapter directorAdapter=new MovieHumanAdapter(this,bean.directors);
        mMovieDirectorsList.setLayoutManager(directorsManager);
        mMovieDirectorsList.setAdapter(directorAdapter);

        RecyclerView.LayoutManager castsManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        MovieHumanAdapter castAdapter=new MovieHumanAdapter(this,bean.casts);
        mMovieCastsList.setLayoutManager(castsManager);
        mMovieCastsList.setAdapter(castAdapter);
    }
}
