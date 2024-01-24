package com.cppzeal.nightguard.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class ParamHandler {

    // This is a util, for retrieve and store active time scope on sdcard.

    public static final String PARAM_NAME="param";


    public static SharedPreferences getSp(Context context) {
        if (context != null) {
            return context.getSharedPreferences(PARAM_NAME, Context.MODE_PRIVATE);
        }
        return null;
    }

    public static void setSp(Context context,String paramFileName,String key,Object value) {
        if (context != null) {
            SharedPreferences param = context.getSharedPreferences(paramFileName, Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = param.edit();
            Gson gson = new Gson();
            edit.putString(key,gson.toJson(value));
            edit.apply();

        }
        return;
    }






}
