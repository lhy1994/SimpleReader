package com.example.liuhaoyuan.simplereader.movie;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liuhaoyuan.simplereader.ConstantValues;
import com.example.liuhaoyuan.simplereader.R;
import com.example.liuhaoyuan.simplereader.base.BaseFragment;
import com.example.liuhaoyuan.simplereader.bean.MovieListBean;
import com.example.liuhaoyuan.simplereader.util.DataUtils;
import com.example.liuhaoyuan.simplereader.util.ViewUtils;

import java.util.List;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public class MovieListFragment extends BaseFragment<MovieListContract.Presenter> implements MovieListContract.View {

    private RecyclerView mMovieListView;

    @Override
    protected MovieListContract.Presenter onCreatePresenter() {
        return new MovieListPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = View.inflate(getContext(), R.layout.fragment_moive_list, null);
        mMovieListView = (RecyclerView) view.findViewById(R.id.lv_movie);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String rankTitle = getArguments().getString(ConstantValues.DOUBAN_RANK_TITLE);
        mPresenter.getMovieList(rankTitle);
    }

    @Override
    public void updateList(MovieListBean bean) {
        if (!DataUtils.isEmptyList(bean.subjects)) {
            MovieAdapter adapter = new MovieAdapter(bean.subjects);
            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
            mMovieListView.setLayoutManager(layoutManager);
            mMovieListView.setAdapter(adapter);
        }else {
            System.out.println("error");
        }
    }

    private class MovieAdapter extends RecyclerView.Adapter<MovieHolder> {
        private List<MovieListBean.SubjectsBean> data;

        private MovieAdapter(List<MovieListBean.SubjectsBean> data) {
            this.data = data;
        }

        @Override
        public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_list, parent, false);
            return new MovieHolder(view);
        }

        @Override
        public void onBindViewHolder(MovieHolder holder, int position) {
            MovieListBean.SubjectsBean subject = data.get(position);
            Glide.with(getActivity()).load(subject.images.medium).into(holder.mPosterIv);
            holder.mTitleTv.setText(subject.title);
            holder.mYearTv.setText(subject.year);
            holder.mRatingTv.setText(subject.rating.average + "");
            holder.mGenre.removeAllViews();
            for (String s : subject.genres) {
                TextView textView = new TextView(getContext());
                textView.setText(s);
                textView.setBackgroundResource(R.drawable.bg_genre);
                textView.setTextColor(Color.WHITE);
                textView.setPadding(ViewUtils.dpTopx(getContext(), 8),
                        ViewUtils.dpTopx(getContext(), 2),
                        ViewUtils.dpTopx(getContext(), 8),
                        ViewUtils.dpTopx(getContext(), 2));
                textView.setTextSize(ViewUtils.dpTopx(getContext(), 12));
                holder.mGenre.addView(textView);
            }
            holder.mOriginNameTv.setText(subject.original_title);
            if (subject.directors != null && subject.directors.size() > 0) {
                StringBuilder directorsString = new StringBuilder("导演：");
                for (MovieListBean.DirectorsBean director : subject.directors) {
                    directorsString.append(director + " ");
                }
                holder.mDirectorsTV.setText(directorsString.toString());
                holder.mDirectorsTV.setVisibility(View.VISIBLE);
            } else {
                holder.mDirectorsTV.setVisibility(View.GONE);
            }
            if (subject.casts != null && subject.casts.size() > 0) {
                StringBuilder actorsString = new StringBuilder("主演：");
                for (MovieListBean.CastsBean cast : subject.casts) {
                    actorsString.append(cast + " ");
                }
                holder.mActorsTv.setText(actorsString);
                holder.mActorsTv.setVisibility(View.VISIBLE);
            } else {
                holder.mActorsTv.setVisibility(View.GONE);
            }
            holder.mCollectCountTv.setText(subject.collect_count + "");
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    private class MovieHolder extends RecyclerView.ViewHolder {

        private final ImageView mPosterIv;
        private final TextView mTitleTv;
        private final TextView mYearTv;
        private final TextView mRatingTv;
        private final LinearLayout mGenre;
        private final TextView mOriginNameTv;
        private final TextView mDirectorsTV;
        private final TextView mActorsTv;
        private final TextView mCollectCountTv;

        public MovieHolder(View itemView) {
            super(itemView);
            mPosterIv = (ImageView) itemView.findViewById(R.id.iv_poster);
            mTitleTv = (TextView) itemView.findViewById(R.id.tv_movie_title);
            mYearTv = (TextView) itemView.findViewById(R.id.tv_year);
            mRatingTv = (TextView) itemView.findViewById(R.id.tv_rating);
            mGenre = (LinearLayout) itemView.findViewById(R.id.genre);
            mOriginNameTv = (TextView) itemView.findViewById(R.id.tv_origin_name);
            mDirectorsTV = (TextView) itemView.findViewById(R.id.tv_director);
            mActorsTv = (TextView) itemView.findViewById(R.id.tv_actor);
            mCollectCountTv = (TextView) itemView.findViewById(R.id.tv_collect_count);
        }
    }
}
