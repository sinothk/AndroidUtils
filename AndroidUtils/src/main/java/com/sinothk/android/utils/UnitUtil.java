package com.sinothk.android.utils;

import android.content.Context;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/17 on 16:38
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class UnitUtil {

    private Context context;

    UnitUtil(Context mContext) {
        context = mContext;
    }

    //将px转换为dp
    public int px2dp(int pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    //将像素转换为px
    public int dip2px(int dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 格式化小数，精确到小数点后两位
     */
    /**
     * @param value
     * @param dataStyle "#0.00"
     * @return
     */
    public String formatData(double value, String dataStyle) {
        DecimalFormat df = new DecimalFormat(dataStyle);
        return df.format(value);
    }

    /**
     * 格式化小数，精确到小数点后两位
     */
    /**
     * 格式化小数距离的米数：33米、3千米
     *
     * @param value
     * @return
     */
    public String formatDistance(double value) {
        if (value < 100) {
            return "100米";
        } else if (value < 1000) {
            return (int) value + "米";
        } else {
            DecimalFormat df = new DecimalFormat("#0.0");
            return df.format(value / 1000.0) + "千米";
        }
    }

    public String getFileSize(long size) {
        //获取到的size为：1705230
        int GB = 1024 * 1024 * 1024;//定义GB的计算常量
        int MB = 1024 * 1024;//定义MB的计算常量
        int KB = 1024;//定义KB的计算常量
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        String resultSize = "";
        if (size / GB >= 1) {
            //如果当前Byte的值大于等于1GB
            resultSize = df.format(size / (float) GB) + "GB";
        } else if (size / MB >= 1) {
            //如果当前Byte的值大于等于1MB
            resultSize = df.format(size / (float) MB) + "MB";
        } else if (size / KB >= 1) {
            //如果当前Byte的值大于等于1KB
            resultSize = df.format(size / (float) KB) + "KB";
        } else {
            resultSize = size + "B";
        }
        return resultSize;
    }
}
