package com.sinothk.android.utils;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * <pre>
 *  创建:  梁玉涛 2019/8/26 on 10:57
 *  项目: AndroidUtilsLib
 *  描述:
 *  更新:
 * <pre>
 */
public class AndroidUtils {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static void init(Context mContext) {
        context = mContext;
    }

    private static void contextNullError() {
        throw new NullPointerException("mContext == null或参数为null, 请在调用前初始化：init(context), 请在调用前初始化：init(context)");
    }

    /**
     * 日志功能
     */
    private static LogcatUtil logcatUtil;

    public static LogcatUtil logcat(Class<?> currClass) {
        if (logcatUtil == null) {
            synchronized (AndroidUtils.class) {
                logcatUtil = new LogcatUtil(currClass);
            }
        }
        return logcatUtil;
    }

    /**
     * Toast
     */
    @SuppressLint("StaticFieldLeak")
    private static ToastUtil toastUtil;

    public static ToastUtil toast() {
        if (toastUtil == null) {
            synchronized (AndroidUtils.class) {
                if (context == null) {
                    contextNullError();
                }
                toastUtil = new ToastUtil(context);
            }
        }
        return toastUtil;
    }

    /**
     * 文件工具
     */
    private static FileUtil fileUtil;

    public static FileUtil file() {
        if (fileUtil == null) {
            synchronized (AndroidUtils.class) {
                fileUtil = new FileUtil();
            }
        }
        return fileUtil;
    }

    /**
     * IntentUtil
     */
    private static IntentUtil intentUtil;

    public static IntentUtil intent() {
        if (intentUtil == null) {
            synchronized (AndroidUtils.class) {
                intentUtil = new IntentUtil();
            }
        }
        return intentUtil;
    }

    /**
     * StringUtil
     */
    private static StringUtil stringUtil;

    public static StringUtil string() {
        if (stringUtil == null) {
            synchronized (AndroidUtils.class) {
                stringUtil = new StringUtil();
            }
        }
        return stringUtil;
    }

    /**
     * StringUtil
     */
    @SuppressLint("StaticFieldLeak")
    private static NetUtil netUtil;

    public static NetUtil net() {
        if (netUtil == null) {
            synchronized (AndroidUtils.class) {
                if (context == null) {
                    contextNullError();
                }
                netUtil = new NetUtil(context);
            }
        }
        return netUtil;
    }

    /**
     * 页面工具类
     */
    private static PageUtil pageUtil;

    public static PageUtil page() {
        if (pageUtil == null) {
            synchronized (AndroidUtils.class) {
                pageUtil = new PageUtil();
            }
        }
        return pageUtil;
    }

    /**
     * View
     */
    @SuppressLint("StaticFieldLeak")
    private static ViewUtil viewUtil;

    public static ViewUtil view() {
        if (viewUtil == null) {
            synchronized (AndroidUtils.class) {
                if (context == null) {
                    contextNullError();
                }
                viewUtil = new ViewUtil(context);
            }
        }
        return viewUtil;
    }

    /**
     * 单位换算
     */
    @SuppressLint("StaticFieldLeak")
    private static UnitUtil unitUtil;

    public static UnitUtil unit() {
        if (unitUtil == null) {
            synchronized (AndroidUtils.class) {
                if (context == null) {
                    contextNullError();
                }
                unitUtil = new UnitUtil(context);
            }
        }
        return unitUtil;
    }

    /**
     * MD5
     */
    private static MD5Util md5Util;

    public static MD5Util codeByMd5() {
        if (md5Util == null) {
            synchronized (AndroidUtils.class) {
                md5Util = new MD5Util();
            }
        }
        return md5Util;
    }

    /**
     * 编码：使用密钥加密、解密
     */
    private static SecretUtil secretUtil;

    public static SecretUtil codeBySecret() {
        if (secretUtil == null) {
            synchronized (AndroidUtils.class) {
                secretUtil = new SecretUtil();
            }
        }
        return secretUtil;
    }

    // 手机硬件
    @SuppressLint("StaticFieldLeak")
    private static PhoneUtil phoneUtil;

    public static PhoneUtil phone() {
        if (phoneUtil == null) {
            synchronized (AndroidUtils.class) {
                if (context == null) {
                    contextNullError();
                }
                phoneUtil = new PhoneUtil(context);
            }
        }
        return phoneUtil;
    }

    /**
     * 正则
     */
    private static RegexUtil regexUtil;

    public static RegexUtil regex() {
        if (regexUtil == null) {
            synchronized (AndroidUtils.class) {
                regexUtil = new RegexUtil();
            }
        }
        return regexUtil;
    }

    /**
     * 偏好缓存
     *
     * @return
     */
    @SuppressLint("StaticFieldLeak")
    private static PreferUtil preferUtil;

    public static PreferUtil cache() {
        if (preferUtil == null) {
            synchronized (AndroidUtils.class) {
                if (context == null) {
                    contextNullError();
                }
                preferUtil = new PreferUtil(context);
            }
        }
        return preferUtil;
    }

    /**
     * 图片操作
     */
    private static ImageUtil imageUtil;

    public static ImageUtil image() {
        if (imageUtil == null) {
            synchronized (AndroidUtils.class) {
                imageUtil = new ImageUtil();
            }
        }
        return imageUtil;
    }

    /**
     * 时间工具类
     */
    private static DateUtil dateUtil;

    public static DateUtil date() {
        if (dateUtil == null) {
            synchronized (AndroidUtils.class) {
                dateUtil = new DateUtil();
            }
        }
        return dateUtil;
    }

    /**
     * App相关
     */
    @SuppressLint("StaticFieldLeak")
    private static AppUtil appUtil;

    public static AppUtil app() {
        if (appUtil == null) {
            synchronized (AndroidUtils.class) {
                if (context == null) {
                    contextNullError();
                }
                appUtil = new AppUtil(context);
            }
        }
        return appUtil;
    }
}
