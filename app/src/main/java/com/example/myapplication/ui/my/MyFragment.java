package com.example.myapplication.ui.my;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.RecyclerAdapter;
import com.example.myapplication.module.SeatmateInfo;
import com.example.myapplication.service.ServiceImpl.SeatmateServiceImpl;
import com.example.myapplication.ui.mail.MailViewModel;
import com.example.myapplication.ui.my.MyDeskMate.MyDeskMate;

import java.util.List;

public class MyFragment extends Fragment {

    private MyViewModel myViewModel;
    private View view;             //定义view用来设置fragment的layout
    
    //暂未与登录模块整合在一起，先假定为111用户
    private String idUser = "111";  

    private ImageView image_head;//头像
    private TextView t_name;//昵称
    private TextView t_number;//账号

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
   

    public void initView() {


        //分享
        LinearLayout me_share=getActivity().findViewById(R.id.me_share);

        //设置
        LinearLayout settings=getActivity().findViewById(R.id.settings);

        //编辑个人资料
        LinearLayout personInfo=getActivity().findViewById(R.id.personInfo);

        //反馈
        LinearLayout feedback=getActivity().findViewById(R.id.feedback);

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
                //实施更新首页图像
                UserServiceImpl userService=new UserServiceImpl();
                UserInfo userInfo=userService.findUserByID(idUser);
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
    }

        //我的同桌
        LinearLayout myPartner =  getActivity().findViewById(R.id.myPartner);

        myPartner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeatmateServiceImpl seatmateService = new SeatmateServiceImpl();
                List<SeatmateInfo>seatmateInfos = seatmateService.findSeatmateProcessing(idUser);
                //判断现在是否有同桌！！
                /*SeatmateInfo seatmateInfo = new SeatmateInfo("132","phineas","zzq",7,null,2,3);
                seatmateInfo.save();

               Log.d("hhh",Integer.toString(seatmateInfos.size())+seatmateInfos.get(0).getPerson1()+" "
                +seatmateInfos.get(0).getPerson2()+" "+seatmateInfos.get(0).getStatus());*/

                if(seatmateInfos.isEmpty()==false){
                    Intent intent = new Intent(getActivity(), MyDeskMate.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(),"当前暂无正在进行的同桌",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    /**
     * 继承Fragment类，重写两个方法
     * 第一个方法onCreateView--返回布局
     * 第二个方法onViewCreated--绑定控件
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_my, container, false);   //获取fragment的layout
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        image_head=(ImageView)view.findViewById(R.id.images_head);
        t_name=(TextView)view.findViewById(R.id.t_name);
        t_number=(TextView)view.findViewById(R.id.t_number);

        UserServiceImpl userService=new UserServiceImpl();
        UserInfo userInfo=userService.findUserByID(idUser);

        //渲染头像
        //image_head.setImageBitmap(BitmapFactory.decodeByteArray(userInfo.getHeadshot(),0,userInfo.getHeadshot().length));
        image_head.setImageBitmap(BitmapFactory.decodeByteArray(userInfo.getHeadshot(),0,userInfo.getHeadshot().length));



        //渲染昵称
        t_name.setText(userInfo.getNickname());

        //渲染账号
        t_number.setText(userInfo.getIdUser());

        super.onViewCreated(view, savedInstanceState);

        initView();//初始化视图
    }
}