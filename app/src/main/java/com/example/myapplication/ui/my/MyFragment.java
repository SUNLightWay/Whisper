package com.example.myapplication.ui.my;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.EditPersonActivity;
import com.example.myapplication.FeedbackActivity;
import com.example.myapplication.LoginActivity;
import com.example.myapplication.PersonalRating;
import com.example.myapplication.R;
import com.example.myapplication.SettingsActivity;
import com.example.myapplication.ShareActivity;
import com.example.myapplication.module.SeatmateInfo;
import com.example.myapplication.module.UserInfo;
import com.example.myapplication.service.ServiceImpl.SeatmateServiceImpl;
import com.example.myapplication.service.ServiceImpl.UserServiceImpl;
import com.example.myapplication.ui.my.MyDeskMate.MyDeskMate;
import com.example.myapplication.util.Utils;

import java.util.List;

import org.w3c.dom.Text;

import static android.content.Context.MODE_PRIVATE;

public class MyFragment extends Fragment{

    private MyViewModel myViewModel;
    private View view;             //定义view用来设置fragment的layout

    private ImageView image_head;//头像
    private TextView t_name;//昵称
    private TextView t_number;//账号
    public String idUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }
    }

    /**
     * 继承Fragment类，重写两个方法
     * 第一个方法onCreateView--返回布局
     * 第二个方法onViewCreated--绑定控件
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myViewModel= ViewModelProviders.of(this).get(MyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my, container, false);   //获取fragment的layout
        view=root;
        /**
         * 获取当前登录的用户
         */
        SharedPreferences mPreference = getActivity().getSharedPreferences("loginConfig", Context.MODE_PRIVATE);
        idUser = mPreference.getString("idUser","");

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        if(idUser == null){
            super.onViewCreated(view, savedInstanceState);
            Toast.makeText(getActivity(),"请先登录！",Toast.LENGTH_LONG).show();
            Utils.actionStart(getActivity(), LoginActivity.class,null,null);
            return;
        }else{
            image_head=(ImageView)view.findViewById(R.id.images_head);
            t_name=(TextView)view.findViewById(R.id.t_name);
            t_number=(TextView)view.findViewById(R.id.t_number);

            UserServiceImpl userService=new UserServiceImpl();
            UserInfo userInfo=userService.findUserByID(idUser);
            image_head.setImageBitmap(BitmapFactory.decodeByteArray(userInfo.getHeadshot(),0,userInfo.getHeadshot().length));
            //渲染昵称
            t_name.setText(userInfo.getNickname());
            //渲染账号
            t_number.setText(userInfo.getIdUser());
            super.onViewCreated(view, savedInstanceState);
            initView();//初始化视图
        }

    }

    public void initView(){
        //分享
        LinearLayout me_share=getActivity().findViewById(R.id.me_share);
        //设置
        LinearLayout settings=getActivity().findViewById(R.id.settings);
        //编辑个人资料
        LinearLayout personInfo=getActivity().findViewById(R.id.personInfo);

        //反馈
        LinearLayout feedback=getActivity().findViewById(R.id.feedback);

        //个人评级
        LinearLayout myRanking=getActivity().findViewById(R.id.myRanking);

        me_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),ShareActivity.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        personInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), EditPersonActivity.class);
                //传递用户名给编辑资料页
                intent.putExtra("idUser",idUser);
                startActivity(intent);

                UserServiceImpl userService=new UserServiceImpl();
                UserInfo userInfo=userService.findUserByID(idUser);
                //渲染更改后的昵称
                t_name.setText(userInfo.getNickname());
                //渲染更改后的账号
                t_number.setText(userInfo.getIdUser());
                //实施更新首页图像
                image_head.setImageBitmap(BitmapFactory.decodeByteArray(userInfo.getHeadshot(),0,userInfo.getHeadshot().length));
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), FeedbackActivity.class);
                startActivity(intent);
            }
        });

        myRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), PersonalRating.class);
                startActivity(intent);
            }
        });

	/**
         * 我的同桌
         * 根据用户id查找是否有正在进行的同桌，如果存在正在进行的同桌，则进行显示正在进行的同桌
         */
        LinearLayout myPartner =  getActivity().findViewById(R.id.myPartner);
        myPartner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeatmateServiceImpl seatmateService = new SeatmateServiceImpl();
                /*SeatmateInfo seatmateInfo = new SeatmateInfo("132","phineas","zzq",7,null,2,2);
                seatmateInfo.save();*/
                //判断现在是否有正在进行的同桌！！
                List<SeatmateInfo> seatmateInfos = seatmateService.findSeatmateProcessing(idUser);
                if(seatmateInfos.isEmpty()==false){
                    Utils.actionStart(getActivity(),MyDeskMate.class,null,idUser);
                }else{
                    Toast.makeText(getActivity(),"当前暂无正在进行的同桌，快去找个同桌吧！",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}