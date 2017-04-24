package com.example.liuhaoyuan.simplereader.util;

import android.content.Context;

/**
 * Created by liuhaoyuan on 17/4/24.
 */

public class ViewUtils {
    public static int pxTodp(Context context,float px){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px/scale+0.5f);
    }

    public static int dpTopx(Context context,float dp){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp*scale+0.5f);
    }
}
