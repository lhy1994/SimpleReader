package com.example.liuhaoyuan.simplereader;

import android.app.Application;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public class App extends Application {
    private static App mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static App getContext() {
        return mContext;
    }
}
