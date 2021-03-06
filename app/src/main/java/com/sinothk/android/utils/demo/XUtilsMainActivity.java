package com.sinothk.android.utils.demo;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sinothk.android.utils.StatusBarUtil;
import com.sinothk.android.utils.XUtils;

public class XUtilsMainActivity extends AppCompatActivity {

    Button nextPageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarUtil.transparencyBar(this);
//
//

//        nextPageBtn = findViewById(R.id.nextPageBtn);
//        nextPageBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                XUtils.intent().openActivity(XUtilsMainActivity.this, Main2Activity.class).start();
//            }
//        });


//        ArrayList<Bank> dList = new ArrayList<Bank>();
//        dList.add(new Bank("12", "上海农商银行"));
//        dList.add(new Bank("13", "波业银行"));
//        dList.add(new Bank("14", "中国工商银行"));
//        dList.add(new Bank("11", "啊商银行"));

//        new CollectUtil<Bank>().sortByChineseKeyword(dList, "getBankName", CollectUtil.ASC);
//        for (Bank b : dList) {
//            System.out.println("code = " + b.getBankId() + "，name = " + b.getBankName());
//        }


//        String rootPath = XUtils.file().getRootPath(this);
//
//        double dis = XUtils.map().getDistance(1d, 2d, 3d, 5d);

        // app信息
//        AppInfo appInfo = XUtils.app().getAppInfo();
//        XUtils.logcat(XUtilsMainActivity.class).e(appInfo.toString());

        // 时间
//        Date date = AndroidUtils.date().getDateByDateStr("2019-09-01 15:25:23", "yyyy-MM-dd HH:mm:ss");
//        String t = AndroidUtils.date().getFriendlyDate(date);
//        AndroidUtils.logcat(MainActivity.class).e(t);
        // 图片
//        AndroidUtils.image().isImage("");

//        // 偏好设置存储
//        Bank bank = new Bank("1111", "建行");
//        boolean operateRes2 = XUtils.cache().setBean("bank", bank);
//        XUtils.logcat(XUtilsMainActivity.class).e(operateRes2 + "");
//
//        Bank bankEntity = (Bank) XUtils.cache().getBean("bank", Bank.class);
//        XUtils.logcat(XUtilsMainActivity.class).e(bankEntity.toString());
//
//        // 正则判断
//        boolean vehicleNo = AndroidUtils.regex().checkEmail("381518188@qq.com");
//        AndroidUtils.logcat(MainActivity.class).e(vehicleNo + "");
//
//        // phone硬件信息
//        String macAddress = AndroidUtils.phone().getMacAddress();
//        AndroidUtils.logcat(MainActivity.class).e(macAddress);
//
//        // SecretUtil
//        String secretStr = AndroidUtils.codeBySecret().getCode("sinothk", "123456");
//        AndroidUtils.logcat(MainActivity.class).e(secretStr);
//        String valueStr = AndroidUtils.codeBySecret().getValue(secretStr, "123456");
//        AndroidUtils.logcat(MainActivity.class).e(valueStr);
//
//        // MD5
//        String md5Str = AndroidUtils.codeByMd5().stringToMD5("sinothk");
//        AndroidUtils.logcat(MainActivity.class).e(md5Str);
//
//        // 单位换算
//        String distance0 = AndroidUtils.unit().formatDistance(545.002);
//        AndroidUtils.logcat(MainActivity.class).e(distance0);
//        String distance = AndroidUtils.unit().formatDistance(1252545.002);
//        AndroidUtils.logcat(MainActivity.class).e(distance);
//
//        // 页面部分
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
