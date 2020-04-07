package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PunchRecordsActivity extends AppCompatActivity {

    private final String TAG = "PunchRecordsActivity";
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punch_records);

        userId = getIntent().getStringExtra("param2");
    }
}
