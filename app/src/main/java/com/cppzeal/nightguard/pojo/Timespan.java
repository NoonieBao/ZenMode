package com.cppzeal.nightguard.pojo;

import android.util.Log;

import androidx.annotation.NonNull;

import java.sql.Timestamp;
import java.util.Calendar;

public class Timespan implements Comparable<Timespan> {
    int dayOfWeek;
    int hour0;
    int hour1;
    int minute0;
    int minute1;

    long ontTime;

    public Timespan(){

    }
    public Timespan(int dayOfWeek, int hour0, int minute0, int hour1, int minute1) {
        this.dayOfWeek = dayOfWeek;
        this.hour0 = hour0;
        this.hour1 = hour1;
        this.minute0 = minute0;
        this.minute1 = minute1;
    }

    public void setOneTime(long timestamp){
        this.ontTime=timestamp;
        // setDayOfWeek(9);//buxuyao
    }
    public void setFrom(int hour0,int minute0){
        this.hour0 = hour0;
        this.minute0 = minute0;
    }

    public void setEnd(int hour1,int minute1){
        this.hour1 = hour1;
        this.minute1 = minute1;
    }
    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getHour0() {
        return hour0;
    }

    public void setHour0(int hour0) {
        this.hour0 = hour0;
    }

    public int getHour1() {
        return hour1;
    }

    public void setHour1(int hour1) {
        this.hour1 = hour1;
    }

    public int getMinute0() {
        return minute0;
    }

    @NonNull
    @Override
    public String toString() {
        return  "["+ dayOfWeek + ",(" + hour0 + ":" + minute0 + "),(" + hour1 + ":" + minute1 + "),"+ontTime+"]";
    }

    public String toString1(String[] param) {
        return  "["+ param[dayOfWeek-1] + ","+
                (this.ontTime==0?("(" + hour0 + ":" + minute0 + "),"+ "(" + hour1 + ":" + minute1 + ")]"):
                        new Timestamp(this.ontTime)

                        )

                ;
    }
    public boolean isMerged(int dow, int hour, int minute) {
        final String TAG = "isMerged";
        int hour1=this.hour1;//Good

        if ((dayOfWeek != 8 && dayOfWeek!=9 )&& dow != dayOfWeek) {
            // 非一次性\所有天\或指定星期几
            Log.i(TAG, "isMerged: " + "星期不匹配");
            return false;
        }
        if(dayOfWeek==9){
            //一次性
            Calendar calendar = Calendar.getInstance();
            long timeInMillis = calendar.getTimeInMillis();
            if(this.ontTime>timeInMillis){
                return  true;
            }else {
                //移除这个时间段
                return  false;
            }
        }
        if ((hour0 == hour1 && minute0 > minute1)) {
            Log.i(TAG, "isMerged: " + "不合理的时间范围");
            return false;
        }
        Log.i(TAG, "check: " + hour0 + " " + hour1);
        if (hour0 > hour1) {
            //后面事件晚于前面时间
            if (hour < hour1) {
                hour += 24;
            }
            hour1 += 24;
        }
        Log.i(TAG, "isMerged: " + hour0 + " " + hour1);
        int i = hour * 60 + minute;
        if (hour0 * 60 + minute0 <= i && i <= hour1 * 60 + minute1) {
            Log.i(TAG, "isMerged: return true " + this.toString());
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Timespan other) {
        if (this.dayOfWeek < other.dayOfWeek) {
            return -1; // 当前对象持续时间较短，返回负数
        } else if (this.dayOfWeek > other.dayOfWeek) {
            return 1;
        }

        if (this.hour0 < other.hour0) {
            return -1; // 当前对象持续时间较短，返回负数
        }

        return 0;
    }
}
