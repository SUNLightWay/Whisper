package com.example.myapplication.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utils {

    //构造date
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-kk-mm-ss");

    //解析date
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    /**
     * 活动跳转
     * @param from
     * @param to
     * @param data1
     * @param data2
     */
    public static void actionStart(Context from, Class<?> to, String data1, String data2){
        Intent intent = new Intent(from, to);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
        from.startActivity(intent);
    }

    /**
     * 构造intent
     * @param from
     * @param to
     * @param data1
     * @param data2
     * @return
     */
    public static Intent intentFactory(Context from, Class<?> to, String data1, String data2){
        Intent intent = new Intent(from, to);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
        return intent;
    }

    /**
     * 获取随机序列
     * @param length
     * @return
     */
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

    /**
     * 获取随机整数
     * @param length
     * @return
     */
    public static Integer getRandomInteger(int length){
        String str="123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(9);
            sb.append(str.charAt(number));
        }
        return Integer.parseInt(sb.toString());
    }

    /**
     * 两个日期的差值（单位:天）
     * @param date1
     * @param date2
     * @return
     */
    public  static int differentDayMillisecond(Date date1, Date date2)
    {
        int day = (int)((date2.getTime()-date1.getTime())/(3600*1000*24));
        return day;
    }


    /**
     * 图片转字节
     * @param bitmap
     * @return
     */
    public static byte[] imageToByte(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }


    /**
     * Drawable转换成一个Bitmap
     *
     * @param drawable drawable对象
     * @return
     */
    public static final Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap( drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }


}


