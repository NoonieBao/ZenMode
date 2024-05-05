package com.cppzeal.nightguard.fg;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.cppzeal.nightguard.R;
import com.cppzeal.nightguard.pojo.TimeSpanContainer;
import com.cppzeal.nightguard.pojo.Timespan;
import com.cppzeal.nightguard.util.ActiveTip;

import java.util.List;
import java.util.stream.Collectors;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView viewById = view.findViewById(R.id.timeParam);
        List<Timespan> list = TimeSpanContainer.getListFromCache(viewById.getContext());
        Context context = view.getContext();
        String[] stringArray = context.getResources().getStringArray(R.array.days);
        String result = list.stream()
                .map(t->t.toString1(stringArray))
                .collect(Collectors.joining("\n"));
        boolean locked = TimeSpanContainer.isLocked(list);

        result = String.join("\n",result,context.getResources().getString(R.string.is_sleep_time)+Boolean.toString(locked));

        result = String.join("\n",result,context.getResources().getString(R.string.was_actived_in_lsp)+ActiveTip.isActive().toString());
        viewById.setText(result);
        return view;
    }
}
