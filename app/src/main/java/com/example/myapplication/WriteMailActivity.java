package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class WriteMailActivity extends AppCompatActivity {

    private final String TAG = "WriteMailActivity";
    private String from;
    private String to;

    EditText receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_mail);

        from = getIntent().getStringExtra("param2");
        to = getIntent().getStringExtra("param1");

        if (to != null){
            receiver = (EditText)findViewById(R.id.receiver);
            Log.d(TAG, "onCreate: " + to);
            receiver.setText(to);
        }
    }
}
