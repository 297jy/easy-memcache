package com.zhuanyi.hexo.base.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static final String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
