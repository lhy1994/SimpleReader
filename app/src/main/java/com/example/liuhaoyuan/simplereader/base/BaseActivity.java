package com.example.liuhaoyuan.simplereader.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected BasePresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter!=null){
            mPresenter=onCreatePrestener();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.clearDisposable();
        }
    }

    public abstract BasePresenter onCreatePrestener();
}
