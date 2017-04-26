package com.example.liuhaoyuan.simplereader;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.liuhaoyuan.simplereader.base.BaseFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Fragment fragment = FragmentFactory.getFragment(R.id.navigation_movie_rank);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        setTitle(getString(R.string.title_movie_rank));
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_movie_rank:
                    setTitle(getString(R.string.title_movie_rank));
                    break;
                case R.id.navigation_movie:
                    setTitle(getString(R.string.title_movie));
                    break;
                case R.id.navigation_music:
                    setTitle(getString(R.string.title_music));
                    break;
                case R.id.navigation_book:
                    setTitle(getString(R.string.title_book));
                    break;
            }
            Fragment fragment = FragmentFactory.getFragment(item.getItemId());
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            return true;
        }

    };

}
