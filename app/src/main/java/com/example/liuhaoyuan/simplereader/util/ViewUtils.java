package com.example.liuhaoyuan.simplereader.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

/**
 * Created by liuhaoyuan on 17/4/24.
 */

public class ViewUtils {
    public static void setTextViewText(TextView textView, String text) {
//        setTextViewText(textView, null, text, null);
        if (textView != null && !TextUtils.isEmpty(text)) {
            textView.setText(text);
        }
    }

    public static void setTextViewText(TextView textView, String prefix, String text) {
//        setTextViewText(textView, prefix, null);
        if (textView != null && !TextUtils.isEmpty(text)) {
            textView.setText(prefix+text);
        }
    }

    public static void setTextViewText(TextView textView, String prefix, String text, String append) {
        if (textView != null && !TextUtils.isEmpty(text)) {
            textView.setText(prefix+text+append);
        }
    }

    public static int pxTodp(Context context, float px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static int dpTopx(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int pxTosp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int spTopx(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
