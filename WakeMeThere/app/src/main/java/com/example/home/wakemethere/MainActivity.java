package com.example.home.wakemethere;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView alarmList;
    FloatingActionButton addAlarmButton;
    AlarmAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            alarmList = (ListView) findViewById(R.id.alarmList);

            final ArrayList<String> alarms = getAlarmsDbList();
            adapter = new AlarmAdapter(getApplicationContext(), R.layout.layout);
            alarmList.setAdapter(adapter);
            for (int i = 0; i < alarms.size(); i++) {
                AlarmListDataProvider dataProvider = new AlarmListDataProvider(alarms.get(i), 1, "  ", "  ", "", "", "", "", "", "", true,true);
                adapter.add(dataProvider);
            }
            addAlarmButton = (FloatingActionButton) findViewById(R.id.addAlarmBtn);
            addAlarmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),MapSearch.class);
                    startActivity(intent );
                }
            });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
        }

    }





    protected ArrayList<String> getAlarmsDbList(){
        ArrayList<String> alarms = new ArrayList<String>();
    
        for (int i=0;i<10;i++)
        {
            alarms.add("Rajahmundry");
        }
        return alarms;
    }
}


