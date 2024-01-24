package com.cppzeal.nightguard.component;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cppzeal.nightguard.util.ParamHandler;

import java.util.Map;

public class ParamProvider extends ContentProvider {

    public static final String ParamProviderUrl = "content://com.cppzeal.forcesleep";

    @Override
    public boolean onCreate() {
        return true;
    }

    String TAG = "MyContentProvider";

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Context context = getContext();
        if (context != null) {

            SharedPreferences sharedPreferences = ParamHandler.getSp(context);

            MatrixCursor cursor = new MatrixCursor(new String[]{"key", "value"});

            Map<String, ?> allEntries = sharedPreferences.getAll();

            for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                cursor.addRow(new Object[]{key, value});
            }

            return cursor;
        } else {
            return null;
        }


    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }


}
