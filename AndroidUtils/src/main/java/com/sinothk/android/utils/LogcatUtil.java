package com.sinothk.android.utils;

import android.util.Log;

import com.sinothk.android.utils.inters.ILog;

/**
 * <pre>
 *  创建:  梁玉涛 2019/8/26 on 14:02
 *  项目: AndroidUtilsLib
 *  描述:
 *  更新:
 * <pre>
 */
public class LogcatUtil implements ILog {

    private Class<?> currClass;

    LogcatUtil(Class<?> currClass) {
        this.currClass = currClass;
    }

    @Override
    public void v(String msg) {
        printLog('v', currClass.getSimpleName(), msg);
    }

    @Override
    public void d(String msg) {
        printLog('d', currClass.getSimpleName(), msg);
    }

    @Override
    public void i(String msg) {
        printLog('i', currClass.getSimpleName(), msg);
    }

    @Override
    public void w(String msg) {
        printLog('w', currClass.getSimpleName(), msg);
    }

    @Override
    public void e(String msg) {
        printLog('e', currClass.getSimpleName(), msg);
    }

    public static void printLog(char level, String tag, String msg) {
        switch (level) {
            case 'd':
                Log.d(tag, msg);
                break;
            case 'i':
                Log.i(tag, msg);
                break;
            case 'w':
                Log.w(tag, msg);
                break;
            case 'e':
                Log.e(tag, msg);
                break;
            default:
                Log.v(tag, msg);
                break;
        }
    }
}
