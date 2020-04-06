package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.InformationSysAdapter;
import com.example.myapplication.module.SystemNoteInfo;
import com.example.myapplication.service.ServiceImpl.SystemNoteServiceImpl;

import java.util.List;

public class InformationSys extends AppCompatActivity {
    private RecyclerView recyclerView;

    private String idUser = "zzq"; //始终都是这个登录人的信息

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informationsys);

        recyclerView = findViewById(R.id.recycler_view_information);
        //设置线性布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置adapter
        recyclerView.setAdapter(new InformationSysAdapter(this,getInfo()));
    }

    /**
     * 获取消息数据
     */
    public List<SystemNoteInfo> getInfo(){
        SystemNoteServiceImpl systemNoteService = new SystemNoteServiceImpl();
        List<SystemNoteInfo> systemNoteInfos = systemNoteService.findNoteNotRead(idUser);
        return  systemNoteInfos;
    }
}
