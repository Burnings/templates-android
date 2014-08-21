package com.common.burning.unit;

import android.content.Context;

public class ConvertUtil {
    public static int dip2px(Context context,float dipValue){
        final float scale=context.getResources().getDisplayMetrics().densityDpi;
        return (int)(dipValue*(scale/160)+0.5f);
    }
    public static int px2dp(Context context,float pxValue){
        final float scale = context.getResources().getDisplayMetrics().densityDpi;
        return (int)((pxValue*160)/scale+0.5f);
    }
}
