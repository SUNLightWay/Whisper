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
    private String idUser = "phineas";  //用户名当前用户
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
     * 用于我的同桌界面得显示
     */
    public void initView() {

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
        super.onViewCreated(view, savedInstanceState);
        initView();
    }
}