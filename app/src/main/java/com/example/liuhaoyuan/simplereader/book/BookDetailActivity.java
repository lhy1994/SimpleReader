package com.example.liuhaoyuan.simplereader.book;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liuhaoyuan.simplereader.ConstantValues;
import com.example.liuhaoyuan.simplereader.R;
import com.example.liuhaoyuan.simplereader.adapter.book.BookListAdapter;
import com.example.liuhaoyuan.simplereader.base.BaseActivity;
import com.example.liuhaoyuan.simplereader.bean.BookItemBean;
import com.example.liuhaoyuan.simplereader.util.ViewUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookDetailActivity extends BaseActivity<BookContract.DetailPresenter> implements BookContract.DetailView {

    @BindView(R.id.iv_poster)
    ImageView mPosterIv;
    @BindView(R.id.tv_title)
    TextView mTitleTv;
    @BindView(R.id.tv_rating)
    TextView mRatingTv;
    @BindView(R.id.rating_bar)
    RatingBar mRatingBar;
    @BindView(R.id.tv_origin_title)
    TextView mOriginTitleTv;
    @BindView(R.id.tv_author)
    TextView mAuthorTv;
    @BindView(R.id.tv_pubdate)
    TextView mPubDateTv;
    @BindView(R.id.tv_publisher)
    TextView mPublisherTv;
    @BindView(R.id.tv_summary)
    TextView mSummaryTv;
    @BindView(R.id.tv_author_intro)
    TextView mAuthorIntroTv;
    @BindView(R.id.lv_series)
    RecyclerView mSeriesList;
    @BindView(R.id.tv_series)
    TextView mSeriesTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);
        String id = getIntent().getStringExtra(ConstantValues.DOUBAN_BOOK_ID);
        mPresenter.getBookDetail(id);
    }

    @Override
    public BookContract.DetailPresenter onCreatePresenter() {
        return new BookDetailPresenter(this);
    }

    @Override
    public void setPoster(String imageUrl) {
        Glide.with(this).load(imageUrl).into(mPosterIv);
    }

    @Override
    public void setTitle(String title) {
        ViewUtils.setTextViewText(mTitleTv,title);
    }

    @Override
    public void setRating(double rating, int max) {
        ViewUtils.setTextViewText(mRatingTv,String.valueOf(rating));
        mRatingBar.setMax(max/2);
        mRatingBar.setRating((float) rating/2);
    }

    @Override
    public void setOriginTitle(String originTitle) {
        if (TextUtils.isEmpty(originTitle)){
            mOriginTitleTv.setVisibility(View.GONE);
        }else {
            ViewUtils.setTextViewText(mOriginTitleTv,"原名：",originTitle);
        }
    }

    @Override
    public void setAuthor(List<String> author) {
        StringBuilder builder=new StringBuilder();
        for (String s : author) {
            builder.append(s).append(" ");
        }
        ViewUtils.setTextViewText(mAuthorTv,"作者：",builder.toString());
    }

    @Override
    public void setPubDate(String pubDate) {
        ViewUtils.setTextViewText(mPubDateTv,"出版日期：",pubDate);
    }

    @Override
    public void setPublisher(String publisher) {
        ViewUtils.setTextViewText(mPublisherTv,"出版单位：",publisher);
    }

    @Override
    public void setSummary(String summary) {
        mSummaryTv.setText(summary);
    }

    @Override
    public void setAuthorIntro(String authorIntro) {
        ViewUtils.setTextViewText(mAuthorIntroTv,authorIntro);
    }

    @Override
    public void setSeriesList(List<BookItemBean> list) {
        BookListAdapter adapter=new BookListAdapter(this,list);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        mSeriesList.setLayoutManager(layoutManager);
        mSeriesList.setAdapter(adapter);
    }

    @Override
    public void hideSeriesList() {
        mSeriesTv.setVisibility(View.GONE);
        mSeriesList.setVisibility(View.GONE);
    }
}
