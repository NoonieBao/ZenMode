package com.cppzeal.nightguard.fg;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cppzeal.nightguard.R;
import com.cppzeal.nightguard.array.CustomAdapter;
import com.cppzeal.nightguard.component.CustomTimePickerDialog;
import com.cppzeal.nightguard.pojo.TimeSpanContainer;
import com.cppzeal.nightguard.pojo.Timespan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;

public class AdderFragment extends Fragment {

    Timespan timespan;//Todo always new

    private void setNewTimespan() {
        timespan = new Timespan();
    }

    private void setOneTime(long timestamp) {
        timespan.setOneTime(timestamp);
    }

    private void setTimeSpanFrom(int hour0, int minute0) {
        timespan.setFrom(hour0, minute0);
    }

    private void setTimeSpanEnd(int hour0, int minute0) {

        timespan.setEnd(hour0, minute0);
    }

    private Timespan setTimeSpanDay(int hour0) {
        timespan.setDayOfWeek(hour0);
        return timespan;
    }

    public AdderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_adder, container, false);
        Context context = view.getContext();

        ListView listView = view.findViewById(R.id.listView);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab);

        List<Timespan> list = TimeSpanContainer.getListFromCache(context);

        Resources resources = context.getResources();
        String[] stringArray = resources.getStringArray(R.array.days);

        CustomAdapter adapter = new CustomAdapter(view.getContext(), list, stringArray) {
            @NonNull
            @Override
            public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                Button button = view.findViewById(R.id.button);

                button.setOnClickListener(v -> {
                    TimeSpanContainer.onDel(context, list.get(position));
                    notifyDataSetChanged();
                });

                return view;
            }
        };
        listView.setAdapter(adapter);

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_number_picker);
        NumberPicker numberPicker = dialog.findViewById(R.id.numberPicker);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(stringArray.length);
        numberPicker.setDisplayedValues(stringArray);

        Button btnOk = dialog.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int selectedNumber = numberPicker.getValue();
                setNewTimespan();
                setTimeSpanDay(selectedNumber);
                dialog.dismiss();

                Log.i("TAG", "onClick: " + selectedNumber);
                if (selectedNumber == 9) {
                    CustomTimePickerDialog oneTimePicker = new CustomTimePickerDialog(
                            context,
                            (view, hourOfDay, minute1) -> {
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute1);

                                setOneTime(calendar.getTimeInMillis());
                                Log.i("TAG", "onClick: " + timespan.toString());

                                TimeSpanContainer.onAdd(context, timespan); //fact add action
                                adapter.notifyDataSetChanged();

                            },
                            hour,
                            minute,
                            true,
                            getString(R.string.oneTimeZen)
                    );
                    oneTimePicker.show();

                } else {

                    CustomTimePickerDialog endTime = new CustomTimePickerDialog(
                            context,
                            (view, hourOfDay, minute1) -> {
                                setTimeSpanEnd(hourOfDay, minute1);
                                Log.i("TAG", "onClick: " + timespan.toString());
                                TimeSpanContainer.onAdd(context, timespan); //fact add action
                                adapter.notifyDataSetChanged();
                            },
                            hour,
                            minute,
                            true,
                            getString(R.string.select_end_ime)
                    );
                    CustomTimePickerDialog startTime = new CustomTimePickerDialog(
                            context,
                            (view1, hourOfDay, minute12) -> {
                                setTimeSpanFrom(hourOfDay, minute12);
                                endTime.show();
                            },
                            hour,
                            minute,
                            true,
                            getString(R.string.select_start_ime)  // 标题
                    );
                    startTime.show();
                }
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo should new a Time span rather than usa the same one
                dialog.show();
            }
        });

        return view;
    }

}
