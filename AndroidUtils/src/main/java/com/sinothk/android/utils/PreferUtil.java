package com.sinothk.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.sinothk.android.utils.bean.Bank;

import java.util.Map;

import static com.sinothk.android.utils.BuildConfig.DEBUG;

/**
 * 首选项设置
 */
public class PreferUtil {

    private static Context mContext;
    private static String TAG = "Preferences";

    PreferUtil(Context context) {
        mContext = context;
    }

    public boolean setBean(String key, Object entity) {
        String valueStr = JSON.toJSONString(entity);
        return set(key, valueStr);
    }

    public Object getBean(String key, Class<?> currClass) {
        String valueStr = (String) get(key, "");

        if (valueStr == null || valueStr.trim().length() == 0) {
            return null;
        }
        return JSON.parseObject(valueStr, currClass);
    }

    /**
     * store preference settings:
     * value 只能是Boolean，Integer，Long，Float,String;
     */
    public boolean set(String key, Object value) {
        try {
            if (mContext != null) {
                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mContext);
                SharedPreferences.Editor editor = settings.edit();

                if (value instanceof Boolean) {
                    editor.putBoolean(key, (Boolean) value);

                } else if (value instanceof Integer) {
                    editor.putInt(key, (Integer) value);

                } else if (value instanceof Long) {
                    editor.putLong(key, (long) value);

                } else if (value instanceof Float || value instanceof Double) {
                    editor.putFloat(key, (Float) value);

                } else if (value instanceof String) {
                    editor.putString(key, (String) value);
                } else {
                    Log.e(TAG, "Unexpected type:" + key + "=" + value);
                    return false;
                }
                return editor.commit();
            } else {
                initTip();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Object get(String key, Object deft) {
        try {
            if (mContext != null) {
                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mContext);
                Map<String, ?> settingMap = settings.getAll();
                Object obj = settingMap.get(key);
                return obj != null ? obj : deft;
            } else {
                initTip();
                return deft;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return deft;
        }
    }

    private static void initTip() {
        if (mContext == null) {
            if (DEBUG) {
                throw new NullPointerException("mContext == null或参数为null, 请在调用前初始化：init(context)");
            }
            return;
        }
    }

    /**
     * remove preference settings
     */
    public boolean remove(String key) {
        try {
            if (mContext != null) {
                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mContext);
                SharedPreferences.Editor editor = settings.edit();
                editor.remove(key);
                return editor.commit();
            } else {
                initTip();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 清除SP中所有数据
     *
     * @return
     */
    public boolean clearAll() {
        try {
            if (mContext != null) {
                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mContext);
                SharedPreferences.Editor editor = settings.edit();
                editor.clear().apply();
                return true;
            } else {
                initTip();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取SP中所有键值对
     *
     * @return Map对象
     */
    public Map<String, ?> getAll() {
        try {
            if (mContext != null) {
                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mContext);
                return settings.getAll();
            } else {
                initTip();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断SP中是否存在该key
     *
     * @param key 键
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public boolean exist(String key) {
        try {
            if (mContext != null) {
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
                return sp.contains(key);
            } else {
                initTip();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}