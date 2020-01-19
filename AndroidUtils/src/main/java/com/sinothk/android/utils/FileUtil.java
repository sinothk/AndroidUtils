package com.sinothk.android.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
                StorageManager var24 = (StorageManager) var0.getSystemService("storage");
                Method var25 = StorageManager.class.getMethod("getVolumeList");
                Method var5 = StorageManager.class.getMethod("getVolumeState", String.class);
                Object[] var6 = (Object[]) ((Object[]) var25.invoke(var24));
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

                for (int var17 = 0; var17 < var16; ++var17) {
                    Object var18 = var15[var17];
                    Method var19 = var18.getClass().getMethod("getPath");
                    Method var20 = var18.getClass().getMethod("isRemovable");
                    var8 = (String) var19.invoke(var18);
                    var7 = (String) var5.invoke(var24, var19.invoke(var18));
                    var9 = (Boolean) var20.invoke(var18);
                    if (TextUtils.isEmpty(var8) || !var8.toLowerCase().contains("private")) {
                        if (var9) {
                            if (null != var8 && null != var7 && var7.equals("mounted")) {
                                if (var1 <= 18) {
                                    var14 = var8;
                                } else {
                                    try {
                                        File[] var21 = var0.getExternalFilesDirs((String) null);
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

    //**********************************File Choose Depart****************************************
    // 调用系统文件管理器
    public static void chooseFile(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*").addCategory(Intent.CATEGORY_OPENABLE);
        try {
            activity.startActivityForResult(Intent.createChooser(intent, "Choose File"), requestCode);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(activity, "手机没有文件管理器 -_-", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri     The Uri to query.
     * @author paulburke
     */
    public static String getFilePathByUri(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getFilePathFromURI(context, uri);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static String getFilePathFromURI(Context context, Uri contentUri) {
        File rootDataDir = context.getFilesDir();
        String fileName = getFileName(contentUri);
        if (!TextUtils.isEmpty(fileName)) {
            File copyFile = new File(rootDataDir + File.separator + fileName);
            copyFile(context, contentUri, copyFile);
            return copyFile.getAbsolutePath();
        }
        return null;
    }

    public static String getFileName(Uri uri) {
        if (uri == null) return null;
        String fileName = null;
        String path = uri.getPath();
        int cut = path.lastIndexOf('/');
        if (cut != -1) {
            fileName = path.substring(cut + 1);
        }
        return fileName;
    }

    public static void copyFile(Context context, Uri srcUri, File dstFile) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(srcUri);
            if (inputStream == null) return;
            OutputStream outputStream = new FileOutputStream(dstFile);
            copyStream(inputStream, outputStream);
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int copyStream(InputStream input, OutputStream output) throws Exception, IOException {
        final int BUFFER_SIZE = 1024 * 2;
        byte[] buffer = new byte[BUFFER_SIZE];
        BufferedInputStream in = new BufferedInputStream(input, BUFFER_SIZE);
        BufferedOutputStream out = new BufferedOutputStream(output, BUFFER_SIZE);
        int count = 0, n = 0;
        try {
            while ((n = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
                out.write(buffer, 0, n);
                count += n;
            }
            out.flush();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
            }
            try {
                in.close();
            } catch (IOException e) {
            }
        }
        return count;
    }
}
