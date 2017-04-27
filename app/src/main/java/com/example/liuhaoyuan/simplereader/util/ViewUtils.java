package com.example.liuhaoyuan.simplereader.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.graphics.Palette;
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
            textView.setText(prefix + text);
        }
    }

    public static void setTextViewText(TextView textView, String prefix, String text, String append) {
        if (textView != null && !TextUtils.isEmpty(text)) {
            textView.setText(prefix + text + append);
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

    public static void getDominantColor(Bitmap bitmap, final PaletteCallBack callBack) {
        new Palette.Builder(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int color = palette.getDominantColor(Color.parseColor("#66000000"));
                Palette.Swatch swatch = palette.getDominantSwatch();
                int textColor;
                if (swatch != null) {
                    textColor = getOpaqueColor(swatch.getTitleTextColor());
                } else {
                    textColor = Color.parseColor("#ffffff");
                }
                callBack.onColorGenerated(color, textColor);
            }
        });
    }

    public static void getVibrantColor(Bitmap bitmap, final PaletteCallBack callBack) {
        new Palette.Builder(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int color = palette.getVibrantColor(Color.parseColor("#66000000"));
                Palette.Swatch swatch = palette.getVibrantSwatch();
                int textColor;
                if (swatch != null) {
                    textColor = getOpaqueColor(swatch.getTitleTextColor());
                } else {
                    textColor = Color.parseColor("#ffffff");
                }
                callBack.onColorGenerated(color, textColor);
            }
        });
    }

    public static void getMutedColor(Bitmap bitmap, final PaletteCallBack callBack) {
        new Palette.Builder(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int color = palette.getMutedColor(Color.parseColor("#66000000"));
                Palette.Swatch swatch = palette.getMutedSwatch();
                int textColor;
                if (swatch != null) {
                    textColor = getOpaqueColor(swatch.getTitleTextColor());
                } else {
                    textColor = Color.parseColor("#ffffff");
                }
                callBack.onColorGenerated(color, textColor);
            }
        });
    }

    public interface PaletteCallBack {
        void onColorGenerated(int color, int textColor);
    }

    public static int getOpaqueColor(@ColorInt int paramInt) {
        return 0xFF000000 | paramInt;
    }

}
