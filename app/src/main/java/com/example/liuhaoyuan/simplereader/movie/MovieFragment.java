package com.example.liuhaoyuan.simplereader.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuhaoyuan.simplereader.ConstantValues;
import com.example.liuhaoyuan.simplereader.R;
import com.example.liuhaoyuan.simplereader.api.DouBanApiService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public class MovieFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String[] mRankTitles;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = View.inflate(getContext(), R.layout.fragment_movie, container);
        mTabLayout = (TabLayout) view.findViewById(R.id.tabs_movie);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_movie);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewPager();
    }

    private void setupViewPager() {
        mRankTitles = DouBanApiService.DOUBAN_RANK_TITLE;
        List<MovieListFragment> fragments = new ArrayList<>();
        for (String mRankTitle : mRankTitles) {
            MovieListFragment fragment = new MovieListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(ConstantValues.DOUBAN_RANK_TITLE, mRankTitle);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        MoviePagerAdapter adapter = new MoviePagerAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private class MoviePagerAdapter extends FragmentPagerAdapter {
        private List<MovieListFragment> mFragments;

        private MoviePagerAdapter(FragmentManager fragmentManager, List<MovieListFragment> mFragments) {
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
