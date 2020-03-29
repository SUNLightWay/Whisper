package com.example.myapplication.ui.find.DeskMate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;


public class DeskmateActivity extends AppCompatActivity {

    private Button find_deskMate_go;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskmate);

        find_deskMate_go = findViewById(R.id.find_deskMate_go);
        find_deskMate_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeskmateActivity.this, DeskMateFindActivity.class);
                startActivity(intent);

            }
        });
    }
}
