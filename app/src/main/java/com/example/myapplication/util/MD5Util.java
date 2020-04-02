package com.example.myapplication.util;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    private static final String TAG = "MD5Util";

    /*
     *MD5加密  生成32位md5码
     */
    public static String Md5_32(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] chars = str.toCharArray();
        byte[] bytes = new byte[chars.length];
        for (int i = 0; i < chars.length; i++) {
            bytes[i] = (byte) chars[i];
        }
        byte[] byte_Md5 = messageDigest.digest(bytes);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < byte_Md5.length; i++) {
            int val = ((int) byte_Md5[i]) & 0xff;
            if (val < 16) hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /*
     * 解密算法  执行一次加密 两次解密
     */

    public static String convertMD5(String inStr) {
        Log.e(TAG, "convertMD5: ----------------------------------------------------------");
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }

    /*
     * 译成密码
     */
    public String encrypt(String str) {
        // MD5
        String s1 = Md5_32(str);
        //加密
        // String s1 = MD5(str);
        String s = new String(str);
        Log.e(TAG, "show: ------------原始密码：" + s);
        Log.e(TAG, "show: ------------MD5加密后：" + Md5_32(s));
        //Log.e(TAG, "show: ------------加密的密码：" + convertMD5(s));
       // Log.e(TAG, "show: ------------解密的：" + convertMD5(convertMD5(s)));
        //返回MD5加密结果
        return Md5_32(s);
    }

    public static String MD5(String sourceStr) {
        try {
            // 获得MD5摘要算法的 MessageDigest对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(sourceStr.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < md.length; i++) {
                int tmp = md[i];
                if (tmp < 0) tmp += 256;
                if (tmp < 16) buf.append("0");
                buf.append(Integer.toHexString(tmp));
            }
           // return buf.toString().substring(8, 24);// 16位加密
            return buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

