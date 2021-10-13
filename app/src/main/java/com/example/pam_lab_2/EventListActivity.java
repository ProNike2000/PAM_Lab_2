package com.example.pam_lab_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

public class EventListActivity extends AppCompatActivity {

    String date;
    RecyclerView event_list;
    EventAdapter adapter;
    ArrayList<Event> eventArrayList = new ArrayList<>();
    EventAdapter.OnClickListener listener;

    public void loadEvents()
    {
        eventArrayList.clear();
        eventArrayList.addAll(allEventsList.getEvents(date));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        Intent eventList = getIntent();
        TextView currentDate = findViewById(R.id.currentDate);
        date = eventList.getStringExtra("date");
        currentDate.setText(date);
        event_list = findViewById(R.id.eventListView);

        buildRecyclerView();
        loadEvents();
    }

    private void buildRecyclerView() {
        listener = new EventAdapter.OnClickListener() {
            @Override
            public void OnItemClick(int position) {
                Event e = eventArrayList.get(position);
                Intent update = new Intent(getApplicationContext(), UpdateActivity.class);
                update.putExtra("timestamp", e.getTimestamp());
                startActivityForResult(update, 2222);
            }
        };
        adapter = new EventAdapter(eventArrayList,
                EventListActivity.this, listener);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        event_list.setHasFixedSize(true);
        event_list.setLayoutManager(manager);
        event_list.setAdapter(adapter);
    }

    public void addEvent(View view)
    {
        Intent addEvent = new Intent(this, AddActivity.class);
        addEvent.putExtra("date", date);
        startActivityForResult(addEvent, 1111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1111 && resultCode == RESULT_OK)
        {
            String obtStr = data.getStringExtra("passed_item");
            String[] obtdStr = obtStr.split(",");
            Event e = new Event(obtdStr[0], obtdStr[1], obtdStr[2], obtdStr[3], obtdStr[4]);
            allEventsList.saveData(e);
            loadEvents();

            Calendar alarmFor = Calendar.getInstance();
            String[] year = obtdStr[1].split("-");
            alarmFor.set(Calendar.YEAR,Integer.parseInt(year[2]));
            alarmFor.set(Calendar.MONTH, Integer.parseInt(year[1])- 1);
            alarmFor.set(Calendar.DAY_OF_MONTH, Integer.parseInt(year[0]));

            String[] time = obtdStr[2].split(":");
            alarmFor.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
            alarmFor.set(Calendar.MINUTE, Integer.parseInt(time[1]));
            alarmFor.set(Calendar.SECOND, 0);

            LocalDateTime date2 = null;
            date2 = LocalDateTime.now();
            System.out.println(date2);

            Intent MyIntent = new Intent(getApplicationContext(), EventBroadcast.class);

            Bundle b = new Bundle();
            b.putString("title", obtdStr[4]);
            b.putString("date", obtdStr[1]);
            b.putString("timeStart", obtdStr[2]);
            b.putString("timeEnd", obtdStr[3]);
            MyIntent.putExtras(b);

            PendingIntent MyPendIntent = PendingIntent.getBroadcast(getApplicationContext(), Integer.parseInt(obtdStr[0]), MyIntent, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager MyAlarm = (AlarmManager) getSystemService(ALARM_SERVICE);
            MyAlarm.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmFor.getTimeInMillis(), MyPendIntent);
        }
        if (requestCode == 2222 && resultCode == RESULT_OK)
        {
            loadEvents();
        }
    }
}