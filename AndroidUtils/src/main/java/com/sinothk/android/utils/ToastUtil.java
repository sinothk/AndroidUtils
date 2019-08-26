package com.sinothk.android.utils;

import android.content.Context;
import android.widget.Toast;


/**
 * Company SINOTHK Created by 梁玉涛 on 2015/9/22.
 */
public class ToastUtil {

    private Context mContext;

    ToastUtil(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * Toast 短时间显示,传文字
     *
     * @param txt
     */
    public void show(String txt) {
        Toast.makeText(mContext, txt, Toast.LENGTH_SHORT).show();
    }

    /**
     * Toast 短时间显示,传文字Id
     *
     * @param id
     */
    public void show(int id) {
        Toast.makeText(mContext, mContext.getResources().getString(id), Toast.LENGTH_SHORT).show();
    }

    /**
     * Toast 长时间显示,传文字
     *
     * @param txt
     */
    public void showLong(String txt) {
        Toast.makeText(mContext, txt, Toast.LENGTH_LONG).show();
    }

    /**
     * Toast 长时间显示,传文字Id
     *
     * @param id
     */
    public void showLong(int id) {
        Toast.makeText(mContext, mContext.getResources().getString(id), Toast.LENGTH_LONG).show();
    }
}
