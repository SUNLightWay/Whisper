package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.module.UserInfo;
import com.example.myapplication.service.ServiceImpl.UserServiceImpl;
import com.example.myapplication.ui.my.MyFragment;
import com.example.myapplication.util.ConstUtil;
import com.example.myapplication.util.Utils;

public class EditPersonActivity extends AppCompatActivity {

    //private String idUser="111";//当前用户

    private ImageView images_head;//头像
    private EditText ID_User;//用户名
    private EditText nickname;//昵称
    private EditText phone;//电话
    private EditText rate;//等级
    private EditText ID_Seatmate;//同桌
    private EditText ID_Team;//小组
    private EditText remark;//备注
    private EditText isPunch;//是否打卡

    private Button p_sbmit;//提交按钮

    private Button button_changeimg;//更换头像
    private  String imageUrl;//头像位置

   // private int requestcode;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_userinfo);

        //初始化视图
        initView();
    }


    public void initView() {


        //获取穿的过来的用户名
        Intent intent=getIntent();
        final String IDuser=intent.getStringExtra("idUser");

        final UserServiceImpl userService=new UserServiceImpl();
        final UserInfo userInfo=userService.findUserByID(IDuser);

        //头像
        images_head=findViewById(R.id.head_icon);
        images_head.setImageBitmap(BitmapFactory.decodeByteArray(userInfo.getHeadshot(),0,userInfo.getHeadshot().length));


        //用户名
        String t_name=userInfo.getIdUser();
        ID_User=findViewById(R.id.ID_User);
        ID_User.setText(t_name);


        //昵称
        String t_nickname=userInfo.getNickname();
        nickname=findViewById(R.id.nickname);
        nickname.setText(t_nickname);

        //电话
        String t_phone=userInfo.getPhone();
        phone=findViewById(R.id.phone);
        phone.setText(t_phone);

        //等级
        Integer t_rate=userInfo.getRate();
        rate=findViewById(R.id.rate);
        rate.setText(t_rate.toString());

        //同桌
        String t_seatmate=userInfo.getIdSeatmate();
        ID_Seatmate=findViewById(R.id.ID_Seatmate);
        ID_Seatmate.setText(t_seatmate);

        //小组
        String t_team=userInfo.getIdTeam();
        ID_Team=findViewById(R.id.ID_Team);
        ID_Team.setText(t_team);

        //备注
        String t_remark=userInfo.getRemark();
        remark=findViewById(R.id.remark);
        remark.setText(t_remark);

        //是否打卡
        Integer t_punch=userInfo.getIsPunch();
        isPunch = findViewById(R.id.isPunch);
        isPunch.setText(t_punch.toString());

        //提交按钮
        p_sbmit=findViewById(R.id.p_sub);
        p_sbmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //用户名
                String s_user = ID_User.getText().toString();
                userInfo.setIdUser(s_user);

                //昵称
                String s_nickname = nickname.getText().toString();
                userInfo.setNickname(s_nickname);

                //电话号
                String s_phone = phone.getText().toString();
                userInfo.setPhone(s_phone);

                //等级
                int s_rate = Integer.parseInt(rate.getText().toString());
                userInfo.setRate(s_rate);

                //同桌id
                String s_seatmate = ID_Seatmate.getText().toString();
                userInfo.setIdSeatmate(s_seatmate);

                //小组id
                String s_team = ID_Team.getText().toString();
                userInfo.setIdTeam(s_team);

                //备注
                String s_remark = remark.getText().toString();
                userInfo.setRemark(s_remark);

                //是否打卡
                int s_punch = Integer.parseInt(isPunch.getText().toString());
                userInfo.setIsPunch(s_punch);

                //更新用户信息
                userService.updateUserInfo(userInfo);

                //更改成功后返回我的主页
                Intent intent=new Intent(EditPersonActivity.this, MyFragment.class);
                startActivity(intent);
            }
        });



        //更换头像
        button_changeimg=findViewById(R.id.button_changeimg);
        button_changeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(EditPersonActivity.this,SelectPhotoActivity.class);
                //把用户名传递过去
                intent.putExtra("idUser",IDuser);
                startActivity(intent);
                //startActivityForResult(intent,requestcode);
            }
        });


    }



}
