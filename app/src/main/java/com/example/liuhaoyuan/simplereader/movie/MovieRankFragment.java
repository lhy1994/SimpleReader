package com.example.liuhaoyuan.simplereader.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuhaoyuan.simplereader.ConstantValues;
import com.example.liuhaoyuan.simplereader.R;
import com.example.liuhaoyuan.simplereader.movie.view.MovieRankListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public class MovieRankFragment extends Fragment {

    @BindView(R.id.tabs_movie)
    TabLayout mTabLayout;
    @BindView(R.id.vp_movie)
    ViewPager mViewPager;
    @BindArray(R.array.douban_movie_ranks_title)
    String[] mRankTitles;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = View.inflate(getContext(), R.layout.fragment_movie, null);
        ButterKnife.bind(this,view);
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
        List<MovieRankListFragment> fragments = new ArrayList<>();
        for (String mRankTitle : mRankTitles) {
            MovieRankListFragment fragment = new MovieRankListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(ConstantValues.DOUBAN_MOVIE_RANK_TITLE, mRankTitle);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        MovieRankPagerAdapter adapter = new MovieRankPagerAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private class MovieRankPagerAdapter extends FragmentPagerAdapter {
        private List<MovieRankListFragment> mFragments;

        private MovieRankPagerAdapter(FragmentManager fragmentManager, List<MovieRankListFragment> mFragments) {
            super(fragmentManager);
            this.mFragments = mFragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mRankTitles[position];
        }
    }
}
