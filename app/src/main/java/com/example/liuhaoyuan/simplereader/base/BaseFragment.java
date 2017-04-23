package com.example.liuhaoyuan.simplereader.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter==null){
            mPresenter=onCreatePresenter();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter!=null){
            mPresenter.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPresenter!=null){
            mPresenter.clearDisposable();
        }
    }

    protected abstract P onCreatePresenter();
}
