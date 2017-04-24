package com.example.liuhaoyuan.simplereader.movie;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.liuhaoyuan.simplereader.ConstantValues;
import com.example.liuhaoyuan.simplereader.R;
import com.example.liuhaoyuan.simplereader.base.BaseActivity;
import com.example.liuhaoyuan.simplereader.bean.MovieDetailBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends BaseActivity<MovieDetailContract.Presenter> implements MovieDetailContract.View {

    @BindView(R.id.iv_header)
    ImageView mHeaderIv;
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
    @BindView(R.id.tv_country)
    TextView mCountryTv;
    @BindView(R.id.tv_year)
    TextView mYearTv;
    @BindView(R.id.genre)
    LinearLayout mGenre;
    @BindView(R.id.tv_summary)
    TextView mSummaryTv;
    @BindView(R.id.tv_photos)
    TextView mPhotosTv;
    @BindView(R.id.lv_movie_photos)
    RecyclerView mMoviePhotosList;
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
    public MovieDetailContract.Presenter onCreatePresenter() {
        return new MovieDetailPresenter(this);
    }

    @Override
    public void initUi(MovieDetailBean bean) {

    }
}
