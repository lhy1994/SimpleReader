package com.example.liuhaoyuan.simplereader.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuhaoyuan.simplereader.ConstantValues;
import com.example.liuhaoyuan.simplereader.R;
import com.example.liuhaoyuan.simplereader.adapter.CommonPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuhaoyuan on 2017/4/25.
 */

public class MovieFragment extends Fragment {
    @BindView(R.id.tabs_movie)
    TabLayout mTabLayout;
    @BindView(R.id.vp_movie)
    ViewPager mViewPager;
    @BindArray(R.array.douban_movie_category)
    String[] mMovieCategory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = View.inflate(getContext(), R.layout.fragment_movie, null);
        ButterKnife.bind(this, view);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewPager();
    }

    private void setupViewPager() {
        List<MovieListFragment> fragments = new ArrayList<>();
        for (String category : mMovieCategory) {
            MovieListFragment fragment = new MovieListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(ConstantValues.DOUBAN_MOVIE_CATEGORY, category);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        CommonPagerAdapter<MovieListFragment> adapter = new CommonPagerAdapter<>(getChildFragmentManager(), fragments,mMovieCategory);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
