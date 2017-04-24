package com.example.liuhaoyuan.simplereader.util;

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
}
