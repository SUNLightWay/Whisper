package com.example.myapplication.ui.my.MyDeskMate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.module.SeatmateInfo;
import com.example.myapplication.module.SystemNoteInfo;
import com.example.myapplication.module.UserInfo;
import com.example.myapplication.service.ServiceImpl.SeatmateServiceImpl;
import com.example.myapplication.service.ServiceImpl.SystemNoteServiceImpl;
import com.example.myapplication.service.ServiceImpl.UserServiceImpl;
import com.example.myapplication.service.UserService;
import com.example.myapplication.util.ConstUtil;
import com.example.myapplication.util.Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.myapplication.util.ConstUtil.SeatmateStatus.STATUS_FAIL;
import static com.example.myapplication.util.ConstUtil.SeatmateStatus.STATUS_SUCCEED;

public class MyDeskMate extends AppCompatActivity {

    private String idUser ;  //用户名当前用户
    private String person2;


    private CircleImageView  personOneHead; //头像
    private CircleImageView  personTwoHead;//头像
    private TextView personOneNickname;
    private TextView dayClock;  //打卡天数
    private TextView personTwoNickname;
    private CircleImageView dayOne_p1;
    private CircleImageView dayOne_p2;
    private CircleImageView dayTwo_p1;
    private CircleImageView dayTwo_p2;
    private CircleImageView dayThree_p1;
    private CircleImageView dayThree_p2;
    private CircleImageView dayFour_p1;
    private CircleImageView dayFour_p2;
    private CircleImageView dayFive_p1;
    private CircleImageView dayFive_p2;
    private CircleImageView daySix_p1;
    private CircleImageView daySix_p2;
    private CircleImageView daySeven_p1;
    private CircleImageView daySeven_p2;
    private Button remind;     //提醒打卡

    private EditText edit_message;
    private Button leave_message;
    private TextView message_show;

    private List<SeatmateInfo> seatmateInfos;
    private SeatmateServiceImpl seatmateService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydeskmate);

        //获取用户名
        Intent intent = getIntent();
        idUser = intent.getStringExtra("idUser");
        intiView();//初始化控件
        judgeDayClock();
        //留言
        leaveMessage();

    }

    /**
     * 由于两者的同桌之间的留言，在同桌的信息里没有留言信息的字段
     * 所以，采用系统提醒的方式进行发送留言。
     */
    private void leaveMessage() {
        leave_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_message.getText().toString().equals("")==false){
                    //输入留言的内容
                    message_show.setText("留言："+edit_message.getText());
                    //发送系统通知，通知对方留言
                    SystemNoteServiceImpl systemNoteService = new SystemNoteServiceImpl();
                    //向对方发送系统消息
                    systemNoteService.addNote(new SystemNoteInfo(Utils.getRandomString(10),
                            "同桌留言", seatmateInfos.get(0).getPerson2(), new Date(), "来自" + idUser + "的同桌留言:"+edit_message.getText(),
                            ConstUtil.SysNoteType.SYS_NOTE_SEATMATE_INVITATION, ConstUtil.SysNoteRead.SYS_NOTE_NOT_ON_READ));

                }else {
                    Toast.makeText(MyDeskMate.this,"请先输入留言内容",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * 判断打卡到哪一天
     */
    private void judgeDayClock() {
        //根据person1的Id查询同桌信息,默认这个人只有一个正在进行的同桌
        seatmateService = new SeatmateServiceImpl();
       seatmateInfos = seatmateService.findSeatmateProcessing(idUser);
        //设定第一个人的昵称
        if(seatmateInfos.isEmpty()==false) {
            personOneNickname.setText(idUser);
            //personTwoNickname.setText(seatmateInfos.get(0).getPerson2());
            //进行到哪一天,且在这时把头像进行显示
            //进行进行天数的判断
            Log.d("kkk",Integer.toString(seatmateInfos.get(0).getProcessingDay()));
            dayClock.setText("第"+Integer.toString(seatmateInfos.get(0).getProcessingDay())+"天");
            /**
             * 判断两个人今天是否打卡，如果第一个人打卡则进行显示第一个人头像
             * 当天未打卡的，这次的任务就显示失败，同桌失效
             */
            UserServiceImpl userService = new UserServiceImpl();
            final UserInfo userInfo1 = userService.findUserByID(idUser);
            //获取头像,头像进行显示
            personOneHead.setImageBitmap(BitmapFactory.decodeByteArray(userInfo1.getHeadshot(),0,userInfo1.getHeadshot().length));
            if (userInfo1.getIsPunch() == 2) { //已打卡
                switch (seatmateInfos.get(0).getProcessingDay()) {
                    case 1:
                        dayOne_p1.setImageBitmap(BitmapFactory.decodeByteArray(userInfo1.getHeadshot(),0,userInfo1.getHeadshot().length));
                        dayOne_p1.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        dayTwo_p1.setImageBitmap(BitmapFactory.decodeByteArray(userInfo1.getHeadshot(),0,userInfo1.getHeadshot().length));
                        dayTwo_p1.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        dayThree_p1.setImageBitmap(BitmapFactory.decodeByteArray(userInfo1.getHeadshot(),0,userInfo1.getHeadshot().length));
                        dayThree_p1.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        dayFour_p1.setImageBitmap(BitmapFactory.decodeByteArray(userInfo1.getHeadshot(),0,userInfo1.getHeadshot().length));
                        dayFour_p1.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        dayFive_p1.setImageBitmap(BitmapFactory.decodeByteArray(userInfo1.getHeadshot(),0,userInfo1.getHeadshot().length));
                        dayFive_p1.setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        daySix_p1.setImageBitmap(BitmapFactory.decodeByteArray(userInfo1.getHeadshot(),0,userInfo1.getHeadshot().length));
                        daySix_p1.setVisibility(View.VISIBLE);
                        break;
                    case 7:
                        daySeven_p1.setImageBitmap(BitmapFactory.decodeByteArray(userInfo1.getHeadshot(),0,userInfo1.getHeadshot().length));
                        daySeven_p1.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }
            //登录角色的转换
            final UserInfo userInfo2;
            if(idUser.equals(seatmateInfos.get(0).getPerson1())){
                userInfo2 = userService.findUserByID(seatmateInfos.get(0).getPerson2());
            }else {
                 userInfo2 = userService.findUserByID(seatmateInfos.get(0).getPerson1());
            }
            personTwoNickname.setText(userInfo2.getIdUser());
            //获取头像,头像进行显示
            personTwoHead.setImageBitmap(BitmapFactory.decodeByteArray(userInfo2.getHeadshot(),0,userInfo2.getHeadshot().length));
           // Log.d("mmm",seatmateInfos.get(0).getPerson1()+" "+seatmateInfos.get(0).getPerson2()+" "
           // +userInfo1.getIsPunch()+" "+userInfo2.getIsPunch());
            if (userInfo2.getIsPunch() == 2) { //已打卡
                switch (seatmateInfos.get(0).getProcessingDay()) {
                    case 1:
                        dayOne_p2.setImageBitmap(BitmapFactory.decodeByteArray(userInfo2.getHeadshot(),0,userInfo2.getHeadshot().length));
                        dayOne_p2.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        dayTwo_p2.setImageBitmap(BitmapFactory.decodeByteArray(userInfo2.getHeadshot(),0,userInfo2.getHeadshot().length));
                        dayTwo_p2.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        dayThree_p2.setImageBitmap(BitmapFactory.decodeByteArray(userInfo2.getHeadshot(),0,userInfo2.getHeadshot().length));
                        dayThree_p2.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        dayFour_p2.setImageBitmap(BitmapFactory.decodeByteArray(userInfo2.getHeadshot(),0,userInfo2.getHeadshot().length));
                        dayFour_p2.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        dayFive_p2.setImageBitmap(BitmapFactory.decodeByteArray(userInfo2.getHeadshot(),0,userInfo2.getHeadshot().length));
                        dayFive_p2.setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        daySix_p2.setImageBitmap(BitmapFactory.decodeByteArray(userInfo2.getHeadshot(),0,userInfo2.getHeadshot().length));
                        daySix_p2.setVisibility(View.VISIBLE);
                        break;
                    case 7:
                        daySeven_p2.setImageBitmap(BitmapFactory.decodeByteArray(userInfo2.getHeadshot(),0,userInfo2.getHeadshot().length));
                        daySeven_p2.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }
            /**
             * 两个都打卡则进入下一天，如果打卡天数大于7天那么此次同桌记录成功
             *
             * 存在bug,当两个人都打卡过后，会出现，你点击一次他就会造成正在进行的时间加1
             * 改正思路，让两个人都卡后这个方法只执行一次才可以！！！
             */
            if (userInfo1.getIsPunch() == 1 && userInfo2.getIsPunch() == 1) {
                seatmateInfos.get(0).setProcessingDay(seatmateInfos.get(0).getProcessingDay() + 1);
                if (seatmateInfos.get(0).getProcessingDay() >= 7) //打卡成功
                {
                    seatmateInfos.get(0).setStatus(STATUS_SUCCEED);
                }
            }

            /**
             * 当天两个中有一个未打卡，那么就按照失败处理
             * 这时按照超过今天的24点打卡记录仍为1的情况
             */
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
            if (hour == 23 && minute == 59 && second == 59) {
                if (userInfo1.getIsPunch() == 2 || userInfo2.getIsPunch() == 2) {
                    seatmateInfos.get(0).setStatus(STATUS_FAIL);
                }
            }

            /**
             * 提醒打卡，显然是提醒person2进行打卡
             *此此时应当发送系统消息。
             */
            remind.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //当您打卡了才可以提醒别人打卡
                    if(userInfo1.getIsPunch()==2) {
                        if (userInfo2.getIsPunch() == 2) {
                            Toast.makeText(MyDeskMate.this, "您的同桌已打卡", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MyDeskMate.this, "已发送提醒！", Toast.LENGTH_LONG).show();
                            //发送系统通知，通知打卡
                            SystemNoteServiceImpl systemNoteService = new SystemNoteServiceImpl();
                            //向对方发送系统消息
                            systemNoteService.addNote(new SystemNoteInfo(Utils.getRandomString(10),
                                    "打卡提醒", seatmateInfos.get(0).getPerson2(), new Date(), "来自" + seatmateInfos.get(0).getPerson1() + "打卡提醒！",
                                    ConstUtil.SysNoteType.SYS_NOTE_SEATMATE_INVITATION, ConstUtil.SysNoteRead.SYS_NOTE_NOT_ON_READ));
                        }
                    }else {
                        Toast.makeText(MyDeskMate.this, "请您先打卡！", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }

    /**
     * 初始化控件
     */
    private void intiView() {

        personOneHead = findViewById(R.id.personOneHead);
        personTwoHead = findViewById(R.id.personTwoHead);

        personOneNickname = findViewById(R.id.personOneNickname);
        personTwoNickname = findViewById(R.id.personTwoNickname);
        dayClock = findViewById(R.id.dayClock);
        dayOne_p1 = findViewById(R.id.dayOne_p1);
        dayOne_p2 = findViewById(R.id.dayOne_p2);
        dayTwo_p1 = findViewById(R.id.dayTwo_p1);
        dayTwo_p2 = findViewById(R.id.dayTwo_p2);
        dayThree_p1 = findViewById(R.id.dayThree_p1);
        dayThree_p2 = findViewById(R.id.dayThree_p2);
        dayFour_p1 = findViewById(R.id.dayFour_p1);
        dayFour_p2 = findViewById(R.id.dayFour_p2);
        dayFive_p1 = findViewById(R.id.dayFive_p1);
        dayFive_p2 = findViewById(R.id.dayFive_p2);
        daySix_p1 = findViewById(R.id.daySix_p1);
        daySix_p2 = findViewById(R.id.daySix_p2);
        daySeven_p1 = findViewById(R.id.daySeven_p1);
        daySeven_p2 = findViewById(R.id.daySeven_p2);
        remind = findViewById(R.id.remind);

        edit_message = findViewById(R.id.edit_message);
        leave_message = findViewById(R.id.leave_message);

        message_show = findViewById(R.id.message_show);
    }
}
