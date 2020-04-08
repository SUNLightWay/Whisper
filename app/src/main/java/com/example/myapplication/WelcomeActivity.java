package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.example.myapplication.dao.DaoImpl.SeatmateDaoImpl;
import com.example.myapplication.module.BulletinInfo;
import com.example.myapplication.module.LoginInfo;
import com.example.myapplication.module.NoteInfo;
import com.example.myapplication.module.PlanListInfo;
import com.example.myapplication.module.MailboxInfo;
import com.example.myapplication.module.SeatmateInfo;
import com.example.myapplication.module.SystemNoteInfo;
import com.example.myapplication.module.TeamInfo;
import com.example.myapplication.module.UserInfo;
import com.example.myapplication.service.MailService;
import com.example.myapplication.service.SeatmateService;
import com.example.myapplication.service.ServiceImpl.PlanListServiceImpl;
import com.example.myapplication.service.ServiceImpl.MailServiceImpl;
import com.example.myapplication.service.ServiceImpl.SeatmateServiceImpl;
import com.example.myapplication.service.ServiceImpl.SystemNoteServiceImpl;
import com.example.myapplication.service.ServiceImpl.TeamServiceImpl;
import com.example.myapplication.service.ServiceImpl.UserServiceImpl;
import com.example.myapplication.service.UserService;
import com.example.myapplication.ui.find.DeskMate.DeskmateActivity;
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
    PlanListServiceImpl planListService = new PlanListServiceImpl();
    SystemNoteServiceImpl systemNoteService = new SystemNoteServiceImpl();
    TeamServiceImpl teamService = new TeamServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置为全屏模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        LitePal.initialize(this);
        dbInitFirstInstall();
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                finish();
            }
        };
        handler.postDelayed(runnable,5000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        Log.i(TAG,"移除runable");
    }

    private void dbInitFirstInstall(){

        LitePal.getDatabase();/*
        //登录用户
        LoginInfo loginInfo = new LoginInfo("phineas", "123456", "12345678912");
        loginInfo.save();
        LoginInfo loginInfo1 = new LoginInfo("cloud", "123456", "12345678912");
        loginInfo1.save();

        LitePal.deleteAll(UserInfo.class);
        //用户信息
        UserInfo userInfo = new UserInfo("phineas", "phineas", "12345678912", 89, null,
                null, null, 1, Utils.imageToByte(Utils.drawableToBitmap(getResources().getDrawable(R.drawable.daily))));
        UserInfo userInfo2 = new UserInfo("Miracle", "Miracle", "12345678912", 89, null,
                null, null, 1, Utils.imageToByte(Utils.drawableToBitmap(getResources().getDrawable(R.drawable.daily))));
        userInfo.save();
        userInfo2.save();
        {
            UserInfo userInfo1 = new UserInfo();
            userInfo1.setIdUser("phineas");
            userInfo1.setPhone("11111111111");
            userInfo1.setRemark("update测试");
            userService.updateUserInfo(userInfo1);
        }
        //同桌
        //SeatmateInfo seatmateInfo1 = new SeatmateInfo(Utils.getRandomString(10), "phineas", "cloud", 7, new Date(), ConstUtil.SeatmateStatus.STATUS_WAITING_ANOTHER_RESPONSE, 0);
        LitePal.deleteAll(SeatmateInfo.class);
        SeatmateInfo seatmateInfo2 = new SeatmateInfo(Utils.getRandomString(10), "cloud", "phineas", 7, new Date(), ConstUtil.SeatmateStatus.STATUS_WAITING_ANOTHER_RESPONSE, 1);
        seatmateInfo2.save();

        //seatmateService.sendRequest(seatmateInfo1);
        //Log.d(TAG, "dbInitFirstInstall: findSeatmateFailedorSucceeded" + seatmateService.findSeatmateFailedorSucceeded("phineas").size());
        //Log.d(TAG, "dbInitFirstInstall: findSeatmateNeedToResponse" + seatmateService.findSeatmateNeedToResponse("cloud").size());

        //计划
        //LitePal.deleteAll(PlanListInfo.class, "fatherplan = ?", "1A2xhadRWW");
        //String id = Utils.getRandomString(10);
        //PlanListInfo plan2 = new PlanListInfo(id, "phineas", "1A2xhadRWW", "高数", "140", "分", null, new Date(), new Date(), "这只是一个开始，加油！", 0, ConstUtil.PlanType.TYPE_PERSONAL, "最无益，只怕一日曝十日寒");
        //PlanListInfo plan1 = new PlanListInfo(Utils.getRandomString(10), "phineas", id, "第一阶段基础复习", "中等题", "程度", null, new Date(), new Date(), "这只是一个开始，加油！", 0, ConstUtil.PlanType.TYPE_PERSONAL, "最无益，只怕一日曝十日寒");
        //plan2.save();
        //plan1.save();
        //PlanListInfo plan2 = new PlanListInfo(Utils.getRandomString(10), "phineas", "1A2xhadRWW", "健身", "匀称", "体态", null, new Date(), new Date(), "告别臃肿，游历世界梦想的第一步", 0, ConstUtil.PlanType.TYPE_PERSONAL, "最无益，只怕一日曝十日寒");
        //Log.d(TAG, "dbInitFirstInstall: " + planListService.addPlan(plan1));
        //Log.d(TAG, "dbInitFirstInstall: " + planListService.addPlan(plan2));

        //邮件
        MailboxInfo mailboxInfo = new MailboxInfo(10,"phineas","Miracle",3,1,new Date(),new Date(),"test","hello? nice yo meet you",ConstUtil.MailSendStatus.REACHED);
        mailboxInfo.save();
        MailboxInfo mailboxInfo1 = new MailboxInfo(11,"phineas","Miracle",3,1,new Date(),new Date(),"test","hello? nice yo meet you",ConstUtil.MailSendStatus.UNREACHED);
        mailboxInfo1.save();
//        MailService mailService = new MailServiceImpl();
//        mailService.findMailBoxList();


        //公告
        BulletinInfo bulletinInfo = new BulletinInfo(3,Utils.getRandomString(10),"Miracle","test",new Date(),"test !!!",100,200,1);
        bulletinInfo.save();

        //消息
        NoteInfo noteInfo = new NoteInfo(1,new Date(),"test",Utils.getRandomString(10),"Miracle");
        noteInfo.save();

        LitePal.deleteAll(SystemNoteInfo.class);
        SystemNoteInfo systemNoteInfo = new SystemNoteInfo(Utils.getRandomString(10), "同桌邀请", "phineas", new Date(), "来自cloud的同桌申请", ConstUtil.SysNoteType.SYS_NOTE_SEATMATE_INVITATION, ConstUtil.SysNoteRead.SYS_NOTE_NOT_ON_READ);
        systemNoteInfo.save();

        String seatmateId = Utils.getRandomString(10);
        Log.d(TAG, "dbInitFirstInstall: " +
            seatmateService.sendRequest(new SeatmateInfo(seatmateId, "Miracle", "phineas", 7, new Date(), ConstUtil.SeatmateStatus.STATUS_WAITING_ANOTHER_RESPONSE, 0))
        );

        Log.d(TAG, "dbInitFirstInstall: " + seatmateService.replyRequest(seatmateId, ConstUtil.SeatmateReplyType.TYPE_APPROVE));

        Log.d(TAG, "dbInitFirstInstall: " + systemNoteService.findNoteNotRead("Miracle").size());

        LitePal.deleteAll(TeamInfo.class);
        TeamInfo teamInfo = new TeamInfo();
        teamInfo.setIdTeam(Utils.getRandomString(10));
        teamInfo.save();
        Log.d(TAG, "dbInitFirstInstall: team: " + teamService.findTeamList().size());*/
    }
}






