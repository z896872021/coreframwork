package com.framwork.common.commonframwork2.utils;

import android.support.annotation.Nullable;

public class StringUtils {
    public static boolean isEmpty(@Nullable CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }
}
