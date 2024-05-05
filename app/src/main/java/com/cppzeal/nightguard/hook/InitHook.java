package com.cppzeal.nightguard.hook;

import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class InitHook implements IXposedHookZygoteInit
{
    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
//        Class<?> classIfExists = XposedHelpers.findClassIfExists("android.app.Activity", null);
//        if(classIfExists!=null){
//            XposedHelpers.findAndHookMethod(AC)
//        }
        String TAG="fuckk";
        XposedHelpers.findAndHookMethod(android.app.Activity.class, "onResume", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                XposedBridge.log(TAG+"qqq");
            }
        });
    }
}
