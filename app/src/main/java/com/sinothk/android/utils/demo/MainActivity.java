package com.sinothk.android.utils.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sinothk.android.utils.AndroidUtils;
import com.sinothk.android.utils.FileUtil;
import com.sinothk.android.utils.LogcatUtil;
import com.sinothk.android.utils.NetUtil;
import com.sinothk.android.utils.staticV.NetStaticUtil;
import com.sinothk.android.utils.ToastUtil;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndroidUtils.init(getApplicationContext());

        boolean netUtil = AndroidUtils.net().is4G();

        // string
        boolean d = AndroidUtils.string().isEmpty("1111");

        // intent
        AndroidUtils.intent().openActivity(MainActivity.this, Main2Activity.class)
                .putIntExtra("id", 22).start();

        // 文件
        FileUtil fileUtil = AndroidUtils.file();
        fileUtil.save(new File(""), "");

        // 日志
        LogcatUtil log = AndroidUtils.logcat(MainActivity.class);
        log.v("v");
        log.d("d");
        AndroidUtils.logcat(MainActivity.class).i("i");
        AndroidUtils.logcat(MainActivity.class).w("w");
        AndroidUtils.logcat(MainActivity.class).e("e");
        log.d(d + "");

        // toast
        ToastUtil toast = AndroidUtils.toast();
        toast.show("11");
        AndroidUtils.toast().show(R.string.app_name);
    }


}
