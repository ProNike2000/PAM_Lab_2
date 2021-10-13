package com.example.pam_lab_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class UpdateActivity extends AppCompatActivity {

    String timestamp;
    Event e;
    EditText date, startTime, endTime, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        date = findViewById(R.id.editTextDate);
        startTime = findViewById(R.id.editTextTime);
        endTime = findViewById(R.id.editTextTime2);
        title = findViewById(R.id.editTextTitle);

        timestamp = getIntent().getStringExtra("timestamp");
        e = allEventsList.getOneEvent(timestamp);
        date.setText(e.getDate());
        startTime.setText(e.getTimeStart());
        endTime.setText(e.getTimeEnd());
        title.setText(e.getTitle());
    }

    public void updateEvent(View view)
    {
        allEventsList.updateEvent(
                timestamp,
                date.getText().toString(),
                startTime.getText().toString(),
                endTime.getText().toString(),
                title.getText().toString()
        );
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        super.finish();
        super.onBackPressed();
    }

    public void deleteEvent(View view)
    {
        allEventsList.deleteEvent(timestamp);
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        super.finish();
        super.onBackPressed();
    }
}