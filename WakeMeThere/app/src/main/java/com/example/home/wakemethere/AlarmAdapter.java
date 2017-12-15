package com.example.home.wakemethere;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class AlarmAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public AlarmAdapter(Context context, int resource) {
        super(context, resource);
    }
    static class DataHandler{
        Switch alarmSwitch;
        TextView alarmName;
    }
    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }
    @Override
    public int getCount() {
        return this.list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row=convertView;
        DataHandler handler;
        if (convertView== null){
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.layout,parent,false);
            handler = new DataHandler();
            handler.alarmSwitch = (Switch) row.findViewById(R.id.switch3);
            handler.alarmName = (TextView)row.findViewById(R.id.textView);
            row.setTag(handler);
        }
        else{
            handler = (DataHandler) row.getTag();
        }
        AlarmListDataProvider dataProvider;
        dataProvider = (AlarmListDataProvider) this.getItem(position);
        handler.alarmName.setText(dataProvider.getAlarm_name_Resource());
        //.setOnText(dataProvider.getAlarm_name_Resource());
        return row;
    }
}
