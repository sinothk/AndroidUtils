package com.sinothk.android.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * <pre>
 *  创建:  梁玉涛 2019/8/26 on 15:33
 *  项目: AndroidUtilsLib
 *  描述:
 *  更新:
 * <pre>
 */
public class PageUtil {

    public void addActivity(Activity currActivity) {
        ActivityUtil.addActivity(currActivity);
    }

    public void removeActivity(Activity currActivity) {
        ActivityUtil.removeActivity(currActivity);
    }

    public void finishAll() {
        ActivityUtil.finishAll();
    }

    public static class ActivityUtil {

        private static Stack<Activity> activityList = new Stack<>();

        public static void addActivity(Activity currActivity) {
            activityList.add(currActivity);
        }

        public static void removeActivity(Activity currActivity) {
            activityList.remove(currActivity);
        }

        public static void finishAll() {
            if (activityList == null) {
                return;
            }
            for (Activity activity : activityList) {
                if (activity == null) {
                    continue;
                }
                activity.finish();
            }
        }
    }
}
