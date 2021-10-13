package com.example.pam_lab_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    CalendarView c;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allEventsList.appContext = this;
        allEventsList.loadData();
        
        c = findViewById(R.id.calendarView);
        Calendar cal = Calendar.getInstance();
        int _day = cal.get(Calendar.DAY_OF_MONTH);
        int _month = cal.get(Calendar.MONTH);
        int _year = cal.get(Calendar.YEAR);
        date = _day + "-" + (_month+1) + "-" + _year;
        
        c.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            month++;
            date = dayOfMonth + "-" + month + "-" +  year;
        });
    }

    public void openCurrentDate(View view)
    {
        Intent eventList = new Intent(this, EventListActivity.class);
        eventList.putExtra("date", date);
        startActivity(eventList);
    }
}