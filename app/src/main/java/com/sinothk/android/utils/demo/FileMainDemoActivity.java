package com.sinothk.android.utils.demo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sinothk.android.utils.FileUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileMainDemoActivity extends AppCompatActivity {

    TextView recordTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_main_demo);

        recordTv = this.findViewById(R.id.recordTv);

        findViewById(R.id.selectFilesBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileUtil.chooseFile(FileMainDemoActivity.this, CHOOSE_FILE_CODE);
            }
        });
    }


    //**********************************File Choose Depart****************************************
    private static final int CHOOSE_FILE_CODE = 0;
    private static final String TAG1 = "FileChoose";

    @Override
// 文件选择完之后，自动调用此函数
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CHOOSE_FILE_CODE) {
                Uri uri = data.getData();

                String sPath1 = FileUtil.getFilePathByUri(this, uri); // Paul Burke写的函数，根据Uri获得文件路径
                File f = new File(sPath1);

                sPath1 += "," + f.length();

                recordTv.setText(sPath1);
            }
        } else {
            Log.e(TAG1, "onActivityResult() error, resultCode: " + resultCode);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
