package com.example.myapplication.ui.find.DeskMate;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.DeskMateAdapter;
import com.example.myapplication.module.UserInfo;
import com.example.myapplication.service.ServiceImpl.UserServiceImpl;

import java.util.List;

public class DeskMateFindActivity extends AppCompatActivity {

    private SearchView search_deskMate;       //搜索功能暂未实现。
    private List<UserInfo> users;
    private RecyclerView mRecyclerView;
    private String idUser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_desk_mate);
         idUser = getIntent().getStringExtra("param2");
        //获取RecyclerView
        mRecyclerView = findViewById(R.id.recycler_view);
        //设置线性布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置adapter
        mRecyclerView.setAdapter(new DeskMateAdapter(DeskMateFindActivity.this, getUserInfo(), idUser));
    }
    /**
     * 获取用户数据用于显示
     */
    public List<UserInfo> getUserInfo(){
        UserServiceImpl userService = new UserServiceImpl();
        users = userService.findUserList();
        return  users;
    }
}
