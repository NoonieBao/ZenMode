package com.cppzeal.nightguard.util;
import android.util.Log;
import de.robv.android.xposed.XposedBridge;
public class XpLog {
    private final static String tag= "[com.cppzeal.forcesleep]".trim()+" ";
    public static void log(String msg){
        log("debug",msg);
    }
    public static void log(String brunch, String msg){
        XposedBridge.log(tag+brunch.trim()+" "+msg.trim());
        Log.i(tag, brunch.trim()+" "+msg.trim());
    }
}

