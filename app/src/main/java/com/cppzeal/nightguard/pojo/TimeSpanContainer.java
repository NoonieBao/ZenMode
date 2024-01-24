package com.cppzeal.nightguard.pojo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.cppzeal.nightguard.util.ParamHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeSpanContainer {

    public static final String NAME_OF_SP = "TIME_SPAN_PARAM";

    //Todo private
    public static List<Timespan> list;

    public static boolean isLocked(Context context) {
        List<Timespan> listFromCache = getListFromCache(context);
        return isLocked(listFromCache);
    }

    public static boolean isLocked(List<Timespan> list) {

        Calendar calendar = Calendar.getInstance();

        long currentTimeMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeMillis);
        calendar.setTime(currentDate);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        Log.i("TAG", "isLocked?: " + hour + ' ' + minute + " " + dayOfWeek);
        for (Timespan timespan : list) {
            if (timespan.isMerged(1, hour, minute)) {
                Log.i("TAG", "isLocked: " + hour + ' ' + minute + " " + dayOfWeek);
                return true;
            }
        }
        return false;
    }
    public static boolean isLockedByString(Object str) {

        if (str instanceof String) {
            List<Timespan> timeParam = fromStringToObj(str);
            return isLocked(timeParam);
        }
        return false;
    }

    public static List<Timespan> fromStringToObj(Object str) {
        if (str instanceof String) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Timespan>>() {
            }.getType();
            return gson.fromJson((String) str, listType);
        }
        return null;
    }


    public static List<Timespan> getListFromCache(Context context) {

        if (list!=null) {
            return list;
        }
        return getListFromDb(context);
    }

    private static List<Timespan> getListFromDb(Context context) {
        SharedPreferences sp = ParamHandler.getSp(context);
        String o = (String) sp.getAll().get(TimeSpanContainer.NAME_OF_SP);
        if(o==null){
            list=new ArrayList<>();
        }else {
            list = fromStringToObj(o);

        }
        return list;
    }

    public static void onAdd(Context context, Timespan timespan) {
        list.add(timespan);
        onUpdate(context);

    }

    public static void onDel(Context context, Timespan timespan) {
        list.remove(timespan);
        onUpdate(context);
    }

    private static void onUpdate(Context context) {
        list.sort(Timespan::compareTo);
        ParamHandler.setSp(context, ParamHandler.PARAM_NAME, TimeSpanContainer.NAME_OF_SP, list);

    }
}
