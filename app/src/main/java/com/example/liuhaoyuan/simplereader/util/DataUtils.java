package com.example.liuhaoyuan.simplereader.util;

import android.text.TextUtils;

import com.example.liuhaoyuan.simplereader.bean.ImagesBean;

import java.util.List;

/**
 * Created by liuhaoyuan on 17/4/24.
 */

public class DataUtils {
    public static boolean isEmptyList(List list){
        return list == null || list.size() == 0;
    }
    public static boolean dataValid(Object data){
        return data!=null;
    }
    public static String getImageUrl(ImagesBean imagesBean) {
        if (!TextUtils.isEmpty(imagesBean.large)) {
            return imagesBean.large;
        }
        if (!TextUtils.isEmpty(imagesBean.medium)) {
            return imagesBean.medium;
        }
        return imagesBean.small;
    }
}
