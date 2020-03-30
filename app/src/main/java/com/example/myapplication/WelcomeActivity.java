package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.example.myapplication.dao.DaoImpl.SeatmateDaoImpl;
import com.example.myapplication.module.LoginInfo;
import com.example.myapplication.module.MailboxInfo;
import com.example.myapplication.module.SeatmateInfo;
import com.example.myapplication.module.UserInfo;
import com.example.myapplication.service.MailService;
import com.example.myapplication.service.SeatmateService;
import com.example.myapplication.service.ServiceImpl.MailServiceImpl;
import com.example.myapplication.service.ServiceImpl.SeatmateServiceImpl;
import com.example.myapplication.service.ServiceImpl.UserServiceImpl;
import com.example.myapplication.service.UserService;
import com.example.myapplication.util.ConstUtil;
import com.example.myapplication.util.Utils;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class WelcomeActivity extends AppCompatActivity {

    private static final String TAG ="WelcomeActivity" ;
    private Handler handler;
    private Runnable runnable;
    UserServiceImpl userService = new UserServiceImpl();
    SeatmateServiceImpl seatmateService = new SeatmateServiceImpl();

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

        LitePal.initialize(this);
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
        LoginInfo loginInfo1 = new LoginInfo("cloud", "123456", "12345678912");
        loginInfo1.save();

        //用户信息
        UserInfo userInfo = new UserInfo("phineas", "phineas", "12345678912", 89, null, null, 1, null);
        userInfo.save();
        {
            UserInfo userInfo1 = new UserInfo();
            userInfo1.setIdUser("phineas");
            userInfo1.setPhone("11111111111");
            userInfo1.setRemark("update测试");
            userService.updateUserInfo(userInfo1);
        }
        //同桌
        SeatmateInfo seatmateInfo1 = new SeatmateInfo(Utils.getRandomString(10), "phineas", "cloud", 7, new Date(), ConstUtil.SeatmateStatus.STATUS_WAITING_ANOTHER_RESPONSE, 0);
        SeatmateInfo seatmateInfo2 = new SeatmateInfo(Utils.getRandomString(10), "cloud", "phineas", 7, new Date(), ConstUtil.SeatmateStatus.STATUS_SUCCEED, 7);
        seatmateInfo2.save();

        //seatmateService.sendRequest(seatmateInfo1);
        //Log.d(TAG, "dbInitFirstInstall: findSeatmateFailedorSucceeded" + seatmateService.findSeatmateFailedorSucceeded("phineas").size());
        //Log.d(TAG, "dbInitFirstInstall: findSeatmateNeedToResponse" + seatmateService.findSeatmateNeedToResponse("cloud").size());

        //邮件
        MailboxInfo mailboxInfo = new MailboxInfo(3,"Miracle","Phineas",1,1,new Date(),new Date(),"test","hello");
        mailboxInfo.save();
//        MailService mailService = new MailServiceImpl();
//        mailService.findMailBoxList();

    }
}






