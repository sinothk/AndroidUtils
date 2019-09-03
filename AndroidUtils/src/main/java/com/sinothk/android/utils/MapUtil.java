package com.sinothk.android.utils;

/**
 * <pre>
 *  创建:  梁玉涛 2019/9/3 on 14:06
 *  项目: AndroidUtilsLib
 *  描述:
 *  更新:
 * <pre>
 */
public class MapUtil {

    /**
     * 通过经纬度获取距离(单位：米)
     *
     * @param centerLat
     * @param centerLon
     * @param lat
     * @param lng
     * @return
     */
    public double getDistance(double centerLat, double centerLon, double lat, double lng) {

        // ==================================
        double EARTH_RADIUS = 6378.137;

        // ==================================
        double radLat1 = rad(centerLat);
        double radLat2 = rad(lat);
        double a = radLat1 - radLat2;
        double b = rad(centerLon) - rad(lng);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        return s;
    }

    private double rad(double d) {
        return d * Math.PI / 180.0;
    }
}
