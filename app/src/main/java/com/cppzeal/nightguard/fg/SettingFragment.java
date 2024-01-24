package com.cppzeal.nightguard.fg;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.cppzeal.nightguard.R;
import com.cppzeal.nightguard.pojo.TimeSpanContainer;
import com.cppzeal.nightguard.pojo.Timespan;
import com.cppzeal.nightguard.util.RootUtils;

import java.util.List;

public class SettingFragment extends Fragment {

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_setting, container, false);
        Context context = inflate.getContext();
        Button openLsp = (Button) inflate.findViewById(R.id.openLsp);
        Button checkTime = (Button) inflate.findViewById(R.id.checkTime);
        Button test = (Button) inflate.findViewById(R.id.test);


        checkTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Timespan> listFromCache = TimeSpanContainer.getListFromCache(context);
                boolean lockedByString = TimeSpanContainer.isLockedByString(listFromCache);
                Toast.makeText(context, listFromCache.toString()+"\n"+lockedByString, Toast.LENGTH_LONG).show();
            }
        });
        openLsp.setOnClickListener(new View.OnClickListener() {
            final String command = "am start -a android.intent.action.MAIN -c org.lsposed.manager.LAUNCH_MANAGER -f 0x10000000 -n com.android.shell/.BugreportWarningActivity";
            @Override
            public void onClick(View v) {
                RootUtils.executeCommand(command);
            }
        });

        return inflate;
    }
}
