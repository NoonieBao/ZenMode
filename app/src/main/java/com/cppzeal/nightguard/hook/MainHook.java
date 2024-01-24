package com.cppzeal.nightguard.hook;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;

import com.cppzeal.nightguard.component.ParamProvider;
import com.cppzeal.nightguard.pojo.Hooker;
import com.cppzeal.nightguard.pojo.TimeSpanContainer;
import com.cppzeal.nightguard.pojo.Timespan;
import com.cppzeal.nightguard.util.XpLog;

import java.util.List;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class MainHook implements IXposedHookLoadPackage {
    String TAG = "sfasf";

    public void handleLoadPackage(LoadPackageParam loadPackageParam) throws Throwable {


        Hooker hooker = new Hooker() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Context context = null;
                List<Timespan> timeParam = null;

                try {
                    context = (Context) param.thisObject;
                    String packageName = context.getPackageName();
                    XposedBridge.log("Package Name " + "Application Package Name: " + packageName);
                } catch (Exception e) {
                    XpLog.log("(Context) param.thisObject", e.toString());
                }

                timeParam=this.getList();
                if(timeParam==null){
                    XpLog.log("Time Param", "511");

                    ContentResolver contentResolver = context.getContentResolver(); // 或者使用另一个上下文来获取 ContentResolver
                    Uri uri = Uri.parse(ParamProvider.ParamProviderUrl);
                    Cursor cursor = contentResolver.query(uri, null, null, null, null);

                    if (cursor != null) {
                        while (cursor.moveToNext()) {
                            @SuppressLint("Range") String key = cursor.getString(cursor.getColumnIndex("key")); // 假设数据表中有名为 "key" 的列
                            if (key.equals(TimeSpanContainer.NAME_OF_SP)) {
                                @SuppressLint("Range") String value = cursor.getString(cursor.getColumnIndex("value")); // 假设数据表中有名为 "value" 的列
                                timeParam = TimeSpanContainer.fromStringToObj(value);
                                this.setList(timeParam);
                            }
                        }
                        cursor.close();
                    }
                }


                XpLog.log("Time Param", timeParam.toString());

                if (!TimeSpanContainer.isLocked(timeParam)) {
                    XpLog.log("Time check", "No locked");
                    return;
                }

                Toast.makeText(context, "Sleep zzz...", Toast.LENGTH_SHORT).show();

                XpLog.log("Time check", "Locked");
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                System.exit(0);
            }
        };
        Class<?> aClass = Class.forName("android.app.Activity");
        XposedBridge.hookAllMethods(aClass, "onResume", hooker);
    }
}