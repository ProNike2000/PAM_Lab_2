package com.example.pam_lab_2;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class allEventsList {
    public static ArrayList<Event> totalEvents;
    public static Context appContext;

    public static void loadData() {
        SharedPreferences sharedPreferences = appContext.getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("events", null);
        Type type = new TypeToken<ArrayList<Event>>() {}.getType();
        totalEvents = gson.fromJson(json, type);
    }

    public static ArrayList<Event> getEvents(String date)
    {
        ArrayList<Event> tmp = new ArrayList<>();
        for (Event e : totalEvents)
        {
            if (e.getDate().compareTo(date) == 0)
                tmp.add(e);
        }

        return tmp;
    }

    public static Event getOneEvent(String timestamp)
    {
        return totalEvents.stream().filter(x -> x.getTimestamp().equals(timestamp)).findFirst().get();
    }

    public static void saveData(Event e)
    {
        totalEvents.add(e);
        SharedPreferences sharedPreferences = appContext.getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(totalEvents);
        editor.putString("events", json);
        editor.apply();
    }

    public static void updateEvent(String timestamp, String date, String timeStart, String timeEnd, String title)
    {
        Event s = totalEvents.stream().filter(x -> x.getTimestamp().equals(timestamp)).findFirst().get();
        s.setDate(date);
        s.setTimeStart(timeStart);
        s.setTimeEnd(timeEnd);
        s.setTitle(title);
    }

    public static void deleteEvent(String timestamp)
    {
        totalEvents.remove(totalEvents.stream().filter(x -> x.getTimestamp().equals(timestamp)).findFirst().get());
        SharedPreferences sharedPreferences = appContext.getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(totalEvents);
        editor.putString("events", json);
        editor.apply();
    }
}
