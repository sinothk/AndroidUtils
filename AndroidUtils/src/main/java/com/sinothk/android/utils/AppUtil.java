package com.sinothk.android.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.sinothk.android.utils.bean.AppInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Company 汉合瑞信.
 * Created by 梁玉涛 on 2015/9/22.
 */
public class AppUtil {

    private Context mContext;

    AppUtil(Context context) {
        mContext = context;
    }

    /**
     * 获取应用程序版本名称信息
     */
    public String getAppVersionName() {
        try {
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取app版本号
     */
    public int getAppVersionCode() {
        try {
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 强制隐藏输入法键盘
     */
    public void hideInput(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 切换软键盘的状态 如当前为收起变为弹出,若当前为弹出变为收起
     */
    private void toggleInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 判断Apk是调试版还是正式版
     *
     * @return
     */
    public boolean isDebug() {
        try {
            ApplicationInfo info = mContext.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 安装App
     * <p>根据路径安装App</p>
     *
     * @param filePath 文件路径
     */
    public void installApp(String filePath) {
        installApp(new File(filePath));
    }

    /**
     * 安装App
     * <p>根据文件安装App</p>
     *
     * @param file 文件
     */
    public void installApp(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    /**
     * 卸载指定包名的App
     *
     * @param packageName 包名
     */
    public void uninstallApp(String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + packageName));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    /**
     * 获取当前App信息
     * <p>AppInfo（名称，图标，包名，版本号，版本Code，是否安装在SD卡，是否是用户程序）</p>
     *
     * @return 当前应用的AppInfo
     */
    public AppInfo getAppInfo() {
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(mContext.getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pi != null ? getBean(pm, pi) : null;
    }

    /**
     * 得到AppInfo的Bean
     *
     * @param pm 包的管理
     * @param pi 包的信息
     * @return AppInfo类
     */
    private AppInfo getBean(PackageManager pm, PackageInfo pi) {
        ApplicationInfo ai = pi.applicationInfo;
        String name = ai.loadLabel(pm).toString();
        Drawable icon = ai.loadIcon(pm);
        String packageName = pi.packageName;
        String packagePath = ai.sourceDir;
        String versionName = pi.versionName;
        int versionCode = pi.versionCode;
        boolean isSD = (ApplicationInfo.FLAG_SYSTEM & ai.flags) != ApplicationInfo.FLAG_SYSTEM;
        boolean isUser = (ApplicationInfo.FLAG_SYSTEM & ai.flags) != ApplicationInfo.FLAG_SYSTEM;
        return new AppInfo(name, icon, packageName, packagePath, versionName, versionCode, isSD, isUser);
    }

    /**
     * 获取所有已安装App信息
     * <p>{@link #getBean(PackageManager, PackageInfo)}（名称，图标，包名，包路径，版本号，版本Code，是否安装在SD卡，是否是用户程序）</p>
     * <p>依赖上面的getBean方法</p>
     *
     * @return 所有已安装的AppInfo列表
     */
    public List<AppInfo> getAllAppsInfo() {
        List<AppInfo> list = new ArrayList<>();
        PackageManager pm = mContext.getPackageManager();
        // 获取系统中安装的所有软件信息
        List<PackageInfo> installedPackages = pm.getInstalledPackages(0);
        for (PackageInfo pi : installedPackages) {
            if (pi != null) {
                list.add(getBean(pm, pi));
            }
        }
        return list;
    }

    /**
     * 根据包名获取意图
     *
     * @param packageName 包名
     * @return 意图
     */
    private Intent getIntentByPackageName(String packageName) {
        return mContext.getPackageManager().getLaunchIntentForPackage(packageName);
    }

    /**
     * 根据包名判断App是否安装
     *
     * @param packageName 包名
     * @return {@code true}: 已安装<br>{@code false}: 未安装
     */
    public boolean isInstallApp(String packageName) {
        return getIntentByPackageName(packageName) != null;
    }

    /**
     * 打开指定包名的App
     *
     * @param packageName 包名
     * @return {@code true}: 打开成功<br>{@code false}: 打开失败
     */
    public boolean openAppByPackageName(String packageName) {
        Intent intent = getIntentByPackageName(packageName);
        if (intent != null) {
            mContext.startActivity(intent);
            return true;
        }
        return false;
    }

    /**
     * 打开指定包名的App应用信息界面
     *
     * @param packageName 包名
     */
    public void openAppInfo(String packageName) {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + packageName));
        mContext.startActivity(intent);
    }

    /**
     * 可用来做App信息分享
     *
     * @param info 分享信息
     */
    public void shareAppInfo(String info) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, info);
        mContext.startActivity(intent);
    }

    /**
     * 判断当前App处于前台还是后台
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.GET_TASKS"/>}</p>
     * <p>并且必须是系统应用该方法才有效</p>
     *
     * @return {@code true}: 后台<br>{@code false}: 前台
     */
    public boolean isAppBackground() {
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        @SuppressWarnings("deprecation")
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(mContext.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取屏幕的宽度px
     *
     * @param context 上下文
     * @return 屏幕宽px
     */
    public int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();// 创建了一张白纸
        windowManager.getDefaultDisplay().getMetrics(outMetrics);// 给白纸设置宽高
        return outMetrics.widthPixels;
    }

    /**
     * 获取屏幕的高度px
     *
     * @param context 上下文
     * @return 屏幕高px
     */
    public int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();// 创建了一张白纸
        windowManager.getDefaultDisplay().getMetrics(outMetrics);// 给白纸设置宽高
        return outMetrics.heightPixels;
    }

    /**
     * 设置透明状态栏(api大于19方可使用)
     * <p>可在Activity的onCreat()中调用</p>
     * <p>需在顶部控件布局中加入以下属性让内容出现在状态栏之下</p>
     * <p>android:clipToPadding="true"</p>
     * <p>android:fitsSystemWindows="true"</p>
     *
     * @param activity activity
     */
    public void setTransparentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 隐藏状态栏
     * <p>也就是设置全屏，一定要在setContentView之前调用，否则报错</p>
     * <p>此方法Activity可以继承AppCompatActivity</p>
     * <p>启动的时候状态栏会显示一下再隐藏，比如QQ的欢迎界面</p>
     * <p>在配置文件中Activity加属性android:theme="@android:style/Theme.NoTitleBar.Fullscreen"</p>
     * <p>如加了以上配置Activity不能继承AppCompatActivity，会报错</p>
     *
     * @param activity activity
     */
    public void hideStatusBar(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 获取状态栏高度
     *
     * @param context 上下文
     * @return 状态栏高度
     */
    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 判断状态栏是否存在
     *
     * @param activity activity
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public boolean isStatusBarExists(Activity activity) {
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        return (params.flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != WindowManager.LayoutParams.FLAG_FULLSCREEN;
    }

    /**
     * 获取ActionBar高度
     *
     * @param activity activity
     * @return ActionBar高度
     */
    public int getActionBarHeight(Activity activity) {
        TypedValue tv = new TypedValue();
        if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, activity.getResources().getDisplayMetrics());
        }
        return 0;
    }

//    /**
//     * 显示通知栏
//     * <p>需添加权限 {@code <uses-permission android:name="android.permission.EXPAND_STATUS_BAR"/>}</p>
//     *
//     * @param context        上下文
//     * @param isSettingPanel {@code true}: 打开设置<br>{@code false}: 打开通知
//     */
//    public void showNotificationBar(Context context, boolean isSettingPanel) {
//        String methodName = (Build.VERSION.SDK_INT <= 16) ? "expand"
//                : (isSettingPanel ? "expandSettingsPanel" : "expandNotificationsPanel");
//        invokePanels(context, methodName);
//    }
//
//    /**
//     * 隐藏通知栏
//     * <p>需添加权限 {@code <uses-permission android:name="android.permission.EXPAND_STATUS_BAR"/>}</p>
//     *
//     * @param context 上下文
//     */
//    public void hideNotificationBar(Context context) {
//        String methodName = (Build.VERSION.SDK_INT <= 16) ? "collapse" : "collapsePanels";
//        invokePanels(context, methodName);
//    }
//    /**
//     * 反射唤醒通知栏
//     *
//     * @param context    上下文
//     * @param methodName 方法名
//     */
//    private void invokePanels(Context context, String methodName) {
//        try {
//            Object service = context.getSystemService("statusbar");
//            Class<?> statusBarManager = Class.forName("android.app.StatusBarManager");
//            Method expand = statusBarManager.getMethod(methodName);
//            expand.invoke(service);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 设置屏幕为横屏
     * <p>还有一种就是在Activity中加属性android:screenOrientation="landscape"</p>
     * <p>不设置Activity的android:configChanges时，切屏会重新调用各个生命周期，切横屏时会执行一次，切竖屏时会执行两次</p>
     * <p>设置Activity的android:configChanges="orientation"时，切屏还是会重新调用各个生命周期，切横、竖屏时只会执行一次</p>
     * <p>设置Activity的android:configChanges="orientation|keyboardHidden|screenSize"（4.0以上必须带最后一个参数）时
     * 切屏不会重新调用各个生命周期，只会执行onConfigurationChanged方法</p>
     *
     * @param activity activity
     */
    public void setLandscape(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity activity
     * @return Bitmap
     */
    public Bitmap captureWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     * <p>需要用到上面获取状态栏高度getStatusBarHeight的方法</p>
     *
     * @param activity activity
     * @return Bitmap
     */
    public Bitmap captureWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int statusBarHeight = getStatusBarHeight(activity);
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * 判断是否锁屏
     *
     * @param context 上下文
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public boolean isScreenLock(Context context) {
        KeyguardManager km = (KeyguardManager) context
                .getSystemService(Context.KEYGUARD_SERVICE);
        return km.inKeyguardRestrictedInputMode();
    }

    public int getScreenWidth1(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public int getScreenHeight1(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     */
    public Bitmap snapShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenWidth1(activity);
        int height = getScreenHeight1(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * 获取当前屏幕截图，不包含状态栏 *
     */
    public Bitmap snapShotWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = getScreenWidth1(activity);
        int height = getScreenHeight1(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * 打开其他App
     *
     * @param mContext
     * @param packName
     */
    public static String startOtherApp(Context mContext, String packName, Bundle bundle) {
        PackageManager packageManager = mContext.getPackageManager();

        PackageInfo packageInfo;
        try {
            packageInfo = packageManager.getPackageInfo(packName, 0);
            if (packageInfo != null) {
                Intent intent = packageManager.getLaunchIntentForPackage(packName);
                if (bundle != null) {
                    assert intent != null;
                    intent.putExtras(bundle);
                }
                mContext.startActivity(intent);
                return "";
            } else {
                return "没有安装";
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "没有安装";
        }
    }

    /**
     * 通过调用浏览器下载文件！
     *
     * @param mContext
     * @param url
     */
    public static void downLoadByBrowser(Context mContext, String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        // 走自己的服务器
        intent.setData(Uri.parse(url));
        mContext.startActivity(intent);
    }

    /**
     * 复制内容到剪切板
     *
     * @param copyStr
     * @return
     */
    public static boolean copyIntoBox(Context mContext, String copyStr) {
        try {
            //获取剪贴板管理器
            ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
            // 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", copyStr);
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
