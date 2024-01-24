//package com.cppzeal.nightguard.component;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.ContentResolver;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.SharedPreferences;
//import android.content.pm.ApplicationInfo;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//
//import com.cppzeal.nightguard.R;
//import com.cppzeal.nightguard.pojo.TimeSpanContainer;
//import com.cppzeal.nightguard.util.RootUtils;
//import com.google.gson.Gson;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.List;
//
//public class MainActivityTest extends Activity {
//    String TAG = "sfasf";
//    private Button sendbt;
//    private Button gotoroot;
//    private Button checktime;
//    private Button saveSetting;
//    private Button readSetting;
//    private Button readParam;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        sendbt = (Button) findViewById(R.id.sendbroad);
//        gotoroot = (Button) findViewById(R.id.gotoRoot);
//        checktime = (Button) findViewById(R.id.checktime);
//        saveSetting = (Button) findViewById(R.id.saveSetting);
//        readSetting = (Button) findViewById(R.id.readSetting);
//        readParam = (Button) findViewById(R.id.readParam);
//
//        readParam.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ContentResolver contentResolver = getContentResolver(); // 或者使用另一个上下文来获取 ContentResolver
//                Uri uri = Uri.parse("content://com.cppzeal.forcesleep");
//
//                Cursor cursor = contentResolver.query(uri, null, null, null, null);
//
//                Log.i(TAG, "onClick: "+cursor);
//                if (cursor != null) {
//                    while (cursor.moveToNext()) {
//                        @SuppressLint("Range") String key = cursor.getString(cursor.getColumnIndex("key")); // 假设数据表中有名为 "key" 的列
//                        @SuppressLint("Range") String value = cursor.getString(cursor.getColumnIndex("value")); // 假设数据表中有名为 "value" 的列
//                        // 处理数据
//
//                        Log.i(TAG, "onClick: "+key);
//                        Log.i(TAG, "onClick: "+value);
//                    }
//                    cursor.close();
//                }
//            }
//        });
//
//        readSetting.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                File file = new File("/data/data/cppzeal.forcesleep/files/param.json");
//                try {
//                    FileInputStream fileInputStream = new FileInputStream(file);
//                    int read = fileInputStream.read();
//                    System.out.println(read);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//
//
//            }
//        });
//        saveSetting.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String packageName = getPackageName();
//                SharedPreferences param = getSharedPreferences("param", Context.MODE_PRIVATE);
//                SharedPreferences.Editor edit = param.edit();
//                Gson gson = new Gson();
//                edit.putString("timeParam",gson.toJson(TimeSpanContainer.list));
//                edit.apply();
//
//            }
//        });
//
//        checktime.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TimeSpanContainer timeSpanContainer = new TimeSpanContainer();
////                boolean locked = timeSpanContainer.isLocked();
////                Toast.makeText(MainActivity.this, " " + locked, Toast.LENGTH_SHORT).show();
//            }
//        });
//        gotoroot.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showConfirmationDialog();
//            }
//        });
//        sendbt.setOnClickListener(new OnClickListener() {
//            String cmd = "am start -a android.intent.action.MAIN -c org.lsposed.manager.LAUNCH_MANAGER -f 0x10000000 -n com.android.shell/.BugreportWarningActivity";
//
//            @Override
//            public void onClick(View v) {
////                NotificationSender.sendNotification(MainActivity.this);
//                RootUtils.executeCommand(cmd);
//            }
//        });
//    }
//
//    private void showConfirmationDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("确定执行此操作吗？")
//                .setCancelable(false)
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // 执行回调或操作
//                        performAction();
//                        Log.i(TAG, "onClick: ok");
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // 取消操作
//                        Log.i(TAG, "onClick: cancle");
//                        dialog.cancel();
//                    }
//                });
//        AlertDialog alert = builder.create();
//        alert.show();
//    }
//
//    private void performAction() {
//        PackageManager packageManager = getPackageManager();
//        String packageName = getApplicationContext().getPackageName();
//        try {
//            ApplicationInfo info = packageManager.getApplicationInfo(packageName, 0);
//            String appSourceDir = info.sourceDir;
//            // 在这里可以获取到应用程序的安装路径，进行后续操作
//            appSourceDir = extractFolderPath(appSourceDir);
//            if (appSourceDir == null) {
//                return;
//            }
//            Log.i(TAG, "onClick: " + appSourceDir);
//            String newDir = " /system/app/" + "ForceSleep";
//            String command = "mount -o remount,rw /system\n" +
//                    "cp -r " + appSourceDir + " " + newDir + "\n" +
//                    "chmod -R 755 " + newDir + "\n" +
//                    "mount -o remount,ro /system\n" +
//                    "exit\n";
//            Log.i(TAG, "onClick: " + command);
//            RootUtils.executeCommand(command);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//    }
//
//    private static String extractFolderPath(String filePath) {
//        String ret = null;
//        if (!filePath.endsWith(".apk")) {
//            return ret;
//        }
//        int lastIndex = filePath.lastIndexOf('/');
//        if (lastIndex != -1) {
//            ret = filePath.substring(0, lastIndex);
//        }
//        if (ret.endsWith("app/")) {
//            ret = null;
//        }
//        return ret;
//    }
//
//    public String getAllAppInfo() {
//        PackageManager packageManager = getPackageManager();
//        StringBuilder appInfoStringBuilder = new StringBuilder();
//        List<ApplicationInfo> packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
//        for (ApplicationInfo packageInfo : packages) {
//            if ((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
//                String appName = packageManager.getApplicationLabel(packageInfo).toString();
//                String packageName = packageInfo.packageName;
//                appInfoStringBuilder.append("App Name: ").append(appName).append(", Package Name: ").append(packageName).append("\n");
//            }
//        }
//        return appInfoStringBuilder.toString();
//    }
//}