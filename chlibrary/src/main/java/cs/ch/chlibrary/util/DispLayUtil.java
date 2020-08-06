package cs.ch.chlibrary.util;

import android.content.res.Resources;

/**
 * @author Administrator
 * DATE on 2020/8/5
 * Describe:
 */
public class DispLayUtil {


    public static int dp2px(float dipValue, Resources resources) {
        final float scale = resources.getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2sp(float pxValue, Resources resources) {
        final float fontScale = resources.getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
