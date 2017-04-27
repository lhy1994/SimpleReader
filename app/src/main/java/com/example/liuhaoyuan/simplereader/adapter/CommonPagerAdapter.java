package com.example.liuhaoyuan.simplereader.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by liuhaoyuan on 2017/4/26.
 */

public class CommonPagerAdapter<T extends Fragment> extends FragmentPagerAdapter {
    private List<T> mFragments;
    private String [] mTitles;
    public CommonPagerAdapter(FragmentManager fm, List<T> fragments, String[] titles) {
        super(fm);
        mFragments=fragments;
        mTitles=titles;
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
        return mTitles[position];
    }
}
