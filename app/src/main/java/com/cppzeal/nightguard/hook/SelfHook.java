package com.cppzeal.nightguard.hook;

import java.util.Objects;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;




/**
 * See {com.cppzeal.nightguard.util.ActiveTip}
 */
public class SelfHook implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if(!Objects.equals(lpparam.packageName, "com.cppzeal.nightguard")){
            return;
        }

        final Class<?> aClass1 = XposedHelpers.findClass("com.cppzeal.nightguard.util.ActiveTip",lpparam.classLoader);

        XposedHelpers.findAndHookMethod(aClass1, "isActive", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult(true);
            }
        });


    }

}
