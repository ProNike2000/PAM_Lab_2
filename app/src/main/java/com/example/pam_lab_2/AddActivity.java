package com.example.pam_lab_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.concurrent.TimeUnit;

public class AddActivity extends AppCompatActivity {

    String date;
    EditText start, end, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        date = getIntent().getStringExtra("date");
        start = findViewById(R.id.editTextTimeStart);
        end = findViewById(R.id.editTextTimeEnd);
        title = findViewById(R.id.editTextTitle);
    }

    public void saveEvent(View view) {
        String ee = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())) + "," +
                date + "," +
                String.valueOf(start.getText()) + "," +
                String.valueOf(end.getText()) + "," +
                String.valueOf(title.getText());

        Intent returnIntent = new Intent();
        returnIntent.putExtra("passed_item", ee);

        setResult(RESULT_OK, returnIntent);
        super.finish();
    }
    public void cancelAdd(View view)
    {
        super.onBackPressed();
    }
}