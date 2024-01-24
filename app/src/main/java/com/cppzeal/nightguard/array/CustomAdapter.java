package com.cppzeal.nightguard.array;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cppzeal.nightguard.R;
import com.cppzeal.nightguard.pojo.Timespan;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Timespan> {

    private Context mContext;
    private List<Timespan> mList;

    private String[] daysParam;

    public CustomAdapter(Context context, List<Timespan> list,String[] daysParam) {
        super(context, 0, list);
        mContext = context;
        mList = list;
        this.daysParam=daysParam;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(mContext).inflate(R.layout.custom_list_item, parent, false);
        }

        Timespan timespan = mList.get(position);
        TextView textView = listItemView.findViewById(R.id.textView);
        textView.setText(timespan.toString1(daysParam));



        return listItemView;
    }
}
