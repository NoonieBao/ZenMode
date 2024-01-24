package com.cppzeal.nightguard.util;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

@Deprecated
public class Utils {


    public static void getParam(Context context) {
            ContentResolver contentResolver = context.getContentResolver(); // 或者使用另一个上下文来获取 ContentResolver
            Uri uri = Uri.parse("content://com.cppzeal.forcesleep");

            Cursor cursor = contentResolver.query(uri, null, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    @SuppressLint("Range") String key = cursor.getString(cursor.getColumnIndex("key")); // 假设数据表中有名为 "key" 的列
                    @SuppressLint("Range") String value = cursor.getString(cursor.getColumnIndex("value")); // 假设数据表中有名为 "value" 的列
                }
                cursor.close();
            }
        }
}
