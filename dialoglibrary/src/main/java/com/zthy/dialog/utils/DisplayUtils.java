package com.zthy.dialog.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;

public class DisplayUtils {

    /**
     * 单位转换: dp -> px
     *
     * @param dp
     * @return
     */
    public static int dp2px(Context context, int dp) {
        return (int) (getDensity(context) * dp + 0.5);
    }

    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static int getScreenHeight(Context context) {
        int screenHeight = getDisplayMetrics(context).heightPixels;
        if(isXiaomi() && xiaomiNavigationGestureEnabled(context)){
            screenHeight += getResourceNavHeight(context);
        }
        return screenHeight;
    }

    /**
     * 获取 DisplayMetrics
     *
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    private static int getResourceNavHeight(Context context){
        // 小米4没有nav bar, 而 navigation_bar_height 有值
        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return -1;
    }

    /**
     * 判断是否为小米
     * https://dev.mi.com/doc/?p=254
     */
    public static boolean isXiaomi() {
        return Build.MANUFACTURER.toLowerCase().equals("xiaomi");
    }

    public static boolean xiaomiNavigationGestureEnabled(Context context) {
        int val = Settings.Global.getInt(context.getContentResolver(), "force_fsg_nav_bar", 0);
        return val != 0;
    }
}
