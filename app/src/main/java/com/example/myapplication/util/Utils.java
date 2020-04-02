package com.example.myapplication.util;

import android.content.Context;
import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utils {

    //构造date
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-kk-mm-ss");

    //解析date
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void actionStart(Context from, Class<?> to, String data1, String data2){
        Intent intent = new Intent(from, to);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
        from.startActivity(intent);
    }

    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public  static int differentDayMillisecond(Date date1, Date date2)
    {


        int day = (int)((date2.getTime()-date1.getTime())/(3600*1000*24));
        return day;
    }
}


