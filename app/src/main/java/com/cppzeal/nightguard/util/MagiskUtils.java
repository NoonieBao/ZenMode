package com.cppzeal.nightguard.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.cppzeal.nightguard.R;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MagiskUtils {

    private static final String TAG = "ZipUtils";
    private static final String Magisk_Zip_Name = "ZenEnhanceModule.zip";

    public static final String ZipType = "application/zip";
    public static final int Magisk_Zip_Req_Code = 67373;

    public static void saveFile(Object activity) {
        Intent intent = new Intent(android.content.Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(android.content.Intent.CATEGORY_OPENABLE);
        intent.setType(ZipType);
        intent.putExtra(Intent.EXTRA_TITLE, Magisk_Zip_Name); // 设置默认文件名
        if(activity instanceof Activity){
            ((Activity)activity).startActivityForResult(intent, Magisk_Zip_Req_Code);
        } else if (activity instanceof Fragment) {
            ((Fragment)activity).startActivityForResult(intent, Magisk_Zip_Req_Code);
        }
    }

    public static void onResult(Context context, int requestCode, int resultCode, Intent data) {
        if (requestCode == Magisk_Zip_Req_Code && resultCode == Activity.RESULT_OK) {

            try {
                Uri uri = data.getData();
                OutputStream outputStream = context.getContentResolver().openOutputStream(uri);
                if (outputStream != null) {
                    // out stream
                    zipForMagisk(context, outputStream);
                    outputStream.close();
                    Toast.makeText(context, context.getResources().getString(R.string.magsikZipDone), Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                Log.e(TAG, Objects.requireNonNull(e.getMessage()));
                Toast.makeText(context, "Error saving file.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public static void zipForMagisk(Context context, OutputStream fos) {
        try {

            ZipOutputStream zos = new ZipOutputStream(fos);

            addToZip(context.getAssets().open("module.prop"), "module.prop", zos);
            addToZip(context.getAssets().open("service.sh"), "service.sh", zos);
            addToZip(context.getAssets().open("update-binary"), "META-INF/com/google/android/update-binary", zos);
            addToZip(context.getAssets().open("updater-script"), "META-INF/com/google/android/updater-script", zos);

            addApkToZip(context, zos);

            zos.close();
            fos.close();

        } catch (IOException e) {
            Log.e(TAG, "Error zipping files: " + e.getMessage());
            Toast.makeText(context, "Error zipping files.", Toast.LENGTH_SHORT).show();
        }
    }

    private static void addToZip(InputStream inputStream, String dist, ZipOutputStream zos) throws IOException {
        ZipEntry zipEntry = new ZipEntry(dist);
        zos.putNextEntry(zipEntry);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            zos.write(buffer, 0, length);
        }

        zos.closeEntry();
        inputStream.close();
    }

    private static void addApkToZip(Context context, ZipOutputStream zos) throws IOException {
        try {
            PackageManager pm = context.getPackageManager();
            String packageName = context.getPackageName();

            ApplicationInfo appInfo = pm.getApplicationInfo(packageName, 0);
            String sourceDir = appInfo.sourceDir;

            FileInputStream fis = new FileInputStream(sourceDir);
            addToZip(fis, "system/app/ZenMode/app-release.apk", zos);
            fis.close();
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Package name not found: " + e.getMessage());
        }
    }

}