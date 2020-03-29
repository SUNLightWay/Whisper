package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.example.myapplication.module.LoginInfo;
import com.example.myapplication.module.UserInfo;

import org.litepal.LitePal;

public class WelcomeActivity extends AppCompatActivity {

    private static final String TAG ="WelcomeActivity" ;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置为全屏模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                finish();
            }
        };
        handler.postDelayed(runnable,5000);

        dbInitFirstInstall();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        Log.i(TAG,"移除runable");
    }

    private void dbInitFirstInstall(){

        LitePal.getDatabase();
        //登录用户
        LoginInfo loginInfo = new LoginInfo("phineas", "123456", "12345678912");
        loginInfo.save();

        //用户信息
        UserInfo userInfo = new UserInfo("phineas", "phineas", "12345678912", 89, null, null, 1, null);
        userInfo.save();
    }
}
