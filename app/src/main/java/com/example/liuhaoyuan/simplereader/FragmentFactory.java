package com.example.liuhaoyuan.simplereader;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import com.example.liuhaoyuan.simplereader.book.BookFragment;
import com.example.liuhaoyuan.simplereader.me.MeFragment;
import com.example.liuhaoyuan.simplereader.movie.MovieFragment;
import com.example.liuhaoyuan.simplereader.music.MusicFragment;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public class FragmentFactory {
    private static SparseArray<Fragment> mFragments = new SparseArray<>();

    public static Fragment getFragment(int id) {
        Fragment fragment = mFragments.get(id);
        if (fragment == null) {
            switch (id) {
                case R.id.navigation_movie:
                    fragment = new MovieFragment();
                    break;
                case R.id.navigation_music:
                    fragment = new MusicFragment();
                    break;
                case R.id.navigation_book:
                    fragment = new BookFragment();
                    break;
                case R.id.navigation_me:
                    fragment = new MeFragment();
                    break;
            }
            mFragments.put(id, fragment);
        }
        return fragment;
    }
}
