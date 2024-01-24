package com.cppzeal.nightguard.pojo;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
public  class Hooker extends XC_MethodHook {
    private List<Timespan> list;

    public List<Timespan> getList(){
        if(list!=null){
            return list;
        }
        return null;
    }

    public void setList(List<Timespan> t){
        this.list=t;
    }

}
