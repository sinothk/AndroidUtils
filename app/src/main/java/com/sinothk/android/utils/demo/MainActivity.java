package com.sinothk.android.utils.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sinothk.android.utils.AndroidUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndroidUtils.init(getApplicationContext());


        // SecretUtil
//        String secretStr = AndroidUtils.codeBySecret().getCode("sinothk", "123456");
//        AndroidUtils.logcat(MainActivity.class).e(secretStr);
//        String valueStr = AndroidUtils.codeBySecret().getValue(secretStr, "123456");
//        AndroidUtils.logcat(MainActivity.class).e(valueStr);

//        // MD5
//        String md5Str = AndroidUtils.codeByMd5().stringToMD5("sinothk");
//        AndroidUtils.logcat(MainActivity.class).e(md5Str);

//        // 单位换算
//        String distance0 = AndroidUtils.unit().formatDistance(545.002);
//        AndroidUtils.logcat(MainActivity.class).e(distance0);
//        String distance = AndroidUtils.unit().formatDistance(1252545.002);
//        AndroidUtils.logcat(MainActivity.class).e(distance);

        // 页面部分
//        AndroidUtils.view().createNewViewHeight(null, 0.5f);

//        AndroidUtils.page().addActivity(this);
//        AndroidUtils.page().removeActivity(this);
//        AndroidUtils.page().finishAll();
        //
//        boolean netUtil = AndroidUtils.net().is4G();
//
//        // string
//        boolean d = AndroidUtils.string().isEmpty("1111");
//
//        // intent
//        AndroidUtils.intent().openActivity(MainActivity.this, Main2Activity.class)
//                .putIntExtra("id", 22).start();
//
//        // 文件
//        FileUtil fileUtil = AndroidUtils.file();
//        fileUtil.save(new File(""), "");
//
//        // 日志
//        LogcatUtil log = AndroidUtils.logcat(MainActivity.class);
//        log.v("v");
//        log.d("d");
//        AndroidUtils.logcat(MainActivity.class).i("i");
//        AndroidUtils.logcat(MainActivity.class).w("w");
//        AndroidUtils.logcat(MainActivity.class).e("e");
//        log.d(d + "");
//
//        // toast
//        ToastUtil toast = AndroidUtils.toast();
//        toast.show("11");
//        AndroidUtils.toast().show(R.string.app_name);
    }


}
