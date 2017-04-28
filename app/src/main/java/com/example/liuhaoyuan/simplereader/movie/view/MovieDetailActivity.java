package com.example.liuhaoyuan.simplereader.movie.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.example.liuhaoyuan.simplereader.adapter.movie.MovieHumanAdapter;
import com.example.liuhaoyuan.simplereader.bean.movie.MovieHumanBean;
import com.example.liuhaoyuan.simplereader.movie.MovieContract;
import com.example.liuhaoyuan.simplereader.movie.presenter.MovieDetailPresenter;
import com.example.liuhaoyuan.simplereader.util.DataUtils;
import com.example.liuhaoyuan.simplereader.util.ViewUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailActivity extends AppCompatActivity implements MovieContract.MovieDetailView {

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
    @BindView(R.id.btn_more_summary)
    TextView mMoreSummaryBtn;
    private boolean mOpenSummary;
    private MovieContract.MovieDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        mPresenter = setPresenter();
        mPresenter.getMovieDetail(getIntent().getStringExtra(ConstantValues.DOUBAN_MOVIE_ID));
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.clearDisposable();
    }

    @OnClick(R.id.btn_more_summary)
    public void toggleSummary() {
        if (mOpenSummary) {
            mSummaryTv.setLines(4);
            mSummaryTv.setEllipsize(TextUtils.TruncateAt.END);
            ViewUtils.setTextViewText(mMoreSummaryBtn, "更多");
        } else {
            mSummaryTv.setSingleLine(false);
            mSummaryTv.setEllipsize(null);
            ViewUtils.setTextViewText(mMoreSummaryBtn, "收起");
        }
        mOpenSummary = !mOpenSummary;
    }

    @Override
    public void setPoster(String imageUrl) {
        Glide.with(this).load(imageUrl).into(mPosterIv);
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
    public void setCountry(List<String> countries) {
        StringBuilder builder = new StringBuilder();
        for (String country : countries) {
            builder.append(country).append(" ");
        }
        ViewUtils.setTextViewText(mCountryTv, builder.toString());
    }

    @Override
    public void setYear(String year) {
        ViewUtils.setTextViewText(mYearTv, year);
    }

    @Override
    public void setGenre(List<String> genres) {
        if (!DataUtils.isEmptyList(genres)) {
            mGenre.removeAllViews();
            for (String genre : genres) {
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
    public void setSummary(String summary) {
        ViewUtils.setTextViewText(mSummaryTv, summary);
        if (mSummaryTv.getLineCount() < 4) {
            mMoreSummaryBtn.setVisibility(View.GONE);
        } else {
            mOpenSummary = false;
            mMoreSummaryBtn.setVisibility(View.VISIBLE);
            mSummaryTv.setLines(4);
            mSummaryTv.setEllipsize(TextUtils.TruncateAt.END);
            ViewUtils.setTextViewText(mMoreSummaryBtn, "更多");
        }
    }

    @Override
    public void setDirectors(List<MovieHumanBean> directors) {
        RecyclerView.LayoutManager directorsManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        MovieHumanAdapter directorAdapter = new MovieHumanAdapter(this, directors);
        mMovieDirectorsList.setLayoutManager(directorsManager);
        mMovieDirectorsList.setAdapter(directorAdapter);
    }

    @Override
    public void setCasts(List<MovieHumanBean> casts) {
        RecyclerView.LayoutManager castsManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        MovieHumanAdapter castAdapter = new MovieHumanAdapter(this, casts);
        mMovieCastsList.setLayoutManager(castsManager);
        mMovieCastsList.setAdapter(castAdapter);
    }

    @Override
    public MovieContract.MovieDetailPresenter setPresenter() {
        return new MovieDetailPresenter(this);
    }
}
