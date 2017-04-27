package com.example.liuhaoyuan.simplereader.music;

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
import com.example.liuhaoyuan.simplereader.adapter.CommenPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public class MusicFragment extends Fragment {
    @BindView(R.id.tabs_movie)
    TabLayout mTabLayout;
    @BindView(R.id.vp_movie)
    ViewPager mViewPager;
    @BindArray(R.array.douban_music_category)
    String[] mMusicCategory;

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
        List<MusicListFragment> fragments = new ArrayList<>();
        for (String category : mMusicCategory) {
            MusicListFragment fragment = new MusicListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(ConstantValues.DOUBAN_MUSIC_CATEGORY, category);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        CommenPagerAdapter<MusicListFragment> adapter = new CommenPagerAdapter<>(getChildFragmentManager(),fragments,mMusicCategory);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
