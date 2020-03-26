package com.example.myapplication.util;

import android.content.Context;
import android.content.Intent;

public class Utils {

    public static void actionStart(Context from, Class<?> to, String data1, String data2){
        Intent intent = new Intent(from, to);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
        from.startActivity(intent);
    }
}
