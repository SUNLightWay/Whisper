package com.example.myapplication.ui.mail;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MailListActivity;
import com.example.myapplication.R;
import com.example.myapplication.WriteMailActivity;
import com.example.myapplication.adapter.RecyclerAdapter;
import com.example.myapplication.util.Utils;

public class MailFragment extends Fragment{

    private MailViewModel mailViewModel;
    private View view;             //定义view用来设置fragment的layout
    public RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerAdapter recyclerAdapter;
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
     * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_mail, container, false);   //获取fragment的layout
        initRecyclerView();
        return view;
    }

    @Override
    public void onStart() {
        //初始化监听器
        initClickListener();
        super.onStart();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initRecyclerView(){
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);                     //设置固定的大小
        recyclerAdapter=new RecyclerAdapter(getActivity());
        layoutManager=new LinearLayoutManager(getActivity());   //创建线性布局
        layoutManager.setOrientation(RecyclerView.VERTICAL);    //设置垂直方向
        recyclerView.setLayoutManager(layoutManager);           //给RecyclerView设置布局管理器
        recyclerAdapter=new RecyclerAdapter(getActivity());     //创建适配器，并设置
        recyclerView.setAdapter(recyclerAdapter);
    }


    public void initClickListener(){

        ImageButton ibMailBox = (ImageButton)getActivity().findViewById(R.id.ib_mail_box);
        ibMailBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.actionStart(getActivity(), MailListActivity.class, null, null);
            }
        });

        ImageButton sendEmail = (ImageButton)getActivity().findViewById(R.id.btWriteLetters);
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.actionStart(getActivity(), WriteMailActivity.class, null, null);
            }
        });
    }
}