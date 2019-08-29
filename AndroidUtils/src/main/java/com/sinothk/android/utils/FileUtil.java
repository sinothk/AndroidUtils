package com.sinothk.android.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.text.TextUtils;

import java.io.File;
import java.lang.reflect.Method;

/**
 * <pre>
 *  创建:  梁玉涛 2019/8/26 on 14:49
 *  项目: AndroidUtilsLib
 *  描述:
 *  更新:
 * <pre>
 */
public class FileUtil {

    public String getRootPath(Context var0) {

        int var1 = Build.VERSION.SDK_INT;
        byte var2 = 12;
        if (var1 >= var2) {
            try {
                StorageManager var24 = (StorageManager)var0.getSystemService("storage");
                Method var25 = StorageManager.class.getMethod("getVolumeList");
                Method var5 = StorageManager.class.getMethod("getVolumeState", String.class);
                Object[] var6 = (Object[])((Object[])var25.invoke(var24));
                String var7 = null;
                String var8 = null;
                Boolean var9 = false;
                String var10 = "";
                String var11 = "";
                String var12 = "";
                String var13 = "";
                String var14 = null;
                Object[] var15 = var6;
                int var16 = var6.length;

                for(int var17 = 0; var17 < var16; ++var17) {
                    Object var18 = var15[var17];
                    Method var19 = var18.getClass().getMethod("getPath");
                    Method var20 = var18.getClass().getMethod("isRemovable");
                    var8 = (String)var19.invoke(var18);
                    var7 = (String)var5.invoke(var24, var19.invoke(var18));
                    var9 = (Boolean)var20.invoke(var18);
                    if (TextUtils.isEmpty(var8) || !var8.toLowerCase().contains("private")) {
                        if (var9) {
                            if (null != var8 && null != var7 && var7.equals("mounted")) {
                                if (var1 <= 18) {
                                    var14 = var8;
                                } else {
                                    try {
                                        File[] var21 = var0.getExternalFilesDirs((String)null);
                                        if (var21 != null) {
                                            if (var21.length > 1) {
                                                var14 = var21[1].getAbsolutePath();
                                            } else {
                                                var14 = var8;
                                            }
                                        }
                                    } catch (Exception var22) {
                                        var14 = var8;
                                    }
                                }
                                break;
                            }
                        } else {
                            var11 = var8;
                            var13 = var7;
                        }
                    }
                }

                byte var26 = 18;
                if (var1 <= var26) {
                    if (null == var14 && null != var11 && null != var13 && var13.equals("mounted")) {
                        var14 = var11;
                    }

                    return var14;
                }

                if (null != var11 && null != var13 && var13.equals("mounted")) {
                    var14 = var11;
                }

                return var14;
            } catch (Throwable var23) {
            }
        }

        File var3 = null;
        boolean var4 = Environment.getExternalStorageState().equals("mounted");
        if (var4) {
            var3 = Environment.getExternalStorageDirectory();
            return var3.toString();
        } else {
            return null;
        }
    }
}
