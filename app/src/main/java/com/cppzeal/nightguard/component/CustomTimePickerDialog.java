package com.cppzeal.nightguard.component;

import android.app.TimePickerDialog;
import android.content.Context;

public class CustomTimePickerDialog extends TimePickerDialog {

    private CharSequence title;

    public CustomTimePickerDialog(Context context, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView, CharSequence title) {
        super(context, listener, hourOfDay, minute, is24HourView);
        this.title = title;
        setTitle(title);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
    }
}
