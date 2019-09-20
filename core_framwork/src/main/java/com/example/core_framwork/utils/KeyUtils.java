package com.example.core_framwork.utils;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * Key 管理
 */
public class KeyUtils {
    public static boolean judgeKeyPass(String msg) {
        StringBuffer buffer = new StringBuffer();
        String s = "";
        buffer.append("05E7E1586D35B7E35420ADDD5C398F1D");
        boolean isPass = false;
        if (buffer.indexOf(msg) > -1) {
            isPass = true;
        } else {
            isPass = false;
        }
        return isPass;
    }

    public static String getDataName() {


        return "com.framwork.core";
    }

    public static String getDataKey(Application application) {
        String msg = null;
        try {
            ApplicationInfo info = application.getPackageManager().getApplicationInfo(application.getPackageName(), PackageManager.GET_META_DATA);
            msg = info.metaData.getString(getDataName());

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return msg;
    }
}
