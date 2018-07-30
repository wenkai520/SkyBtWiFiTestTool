package com.tianci.btwifitesttool.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by yellowlgx on 2015/7/21.
 */
public class ScreenUtils
{
    private static ScreenUtils mUtil;

    private static DisplayMetrics dm;

    private static float mDiv = 1.0f;
    private static float mDpi = 1.0f;

    public static ScreenUtils init(Context context)
    {
        if (mUtil == null)
        {
            mUtil = new ScreenUtils();
        }
        if (dm == null)
        {
            dm = context.getResources().getDisplayMetrics();
        }
        mDiv = (float) dm.widthPixels / 1920.0f;
        mDpi = mDiv / dm.density;
        return mUtil;
    }

    public static int Div(int x) {
        return (int) (x * mDiv + 0.5f);
    }

    public static int Dpi(int x) {
        return (int) (x * mDpi + 0.5f);
    }

}
