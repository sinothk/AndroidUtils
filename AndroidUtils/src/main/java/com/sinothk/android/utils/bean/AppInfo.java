package com.sinothk.android.utils.bean;

import android.graphics.drawable.Drawable;

/**
 * <pre>
 *  创建:  梁玉涛 2019/8/26 on 18:06
 *  项目: AndroidUtilsLib
 *  描述:
 *  更新:
 * <pre>
 */
public class AppInfo {

    private String name;
    private Drawable icon;
    private String packageName;
    private String packagePath;
    private String versionName;
    private int versionCode;
    private boolean isSD;
    private boolean isUser;

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public boolean isSD() {
        return isSD;
    }

    public void setSD(boolean SD) {
        isSD = SD;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packagName) {
        this.packageName = packagName;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    /**
     * @param name        名称
     * @param icon        图标
     * @param packageName 包名
     * @param packagePath 包路径
     * @param versionName 版本号
     * @param versionCode 版本Code
     * @param isSD        是否安装在SD卡
     * @param isUser      是否是用户程序
     */
    public AppInfo(String name, Drawable icon, String packageName, String packagePath,
                   String versionName, int versionCode, boolean isSD, boolean isUser) {
        this.setName(name);
        this.setIcon(icon);
        this.setPackageName(packageName);
        this.setPackagePath(packagePath);
        this.setVersionName(versionName);
        this.setVersionCode(versionCode);
        this.setSD(isSD);
        this.setUser(isUser);
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "name='" + name + '\'' +
                ", icon=" + icon +
                ", packageName='" + packageName + '\'' +
                ", packagePath='" + packagePath + '\'' +
                ", versionName='" + versionName + '\'' +
                ", versionCode=" + versionCode +
                ", isSD=" + isSD +
                ", isUser=" + isUser +
                '}';
    }
}
