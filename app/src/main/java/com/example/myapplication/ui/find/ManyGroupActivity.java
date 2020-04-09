package com.example.myapplication.ui.find;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.GroupAdapter;
import com.example.myapplication.module.TeamInfo;
import com.example.myapplication.service.ServiceImpl.TeamServiceImpl;

import java.util.List;

public class ManyGroupActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ManyGroupActivity";
    private LinearLayout mBack;
    private SearchView mSearchView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerViewS;

    private TeamServiceImpl mTeamService = new TeamServiceImpl();

    private List<TeamInfo> mData;
    private GroupAdapter groupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_many_group);

        initView();

        refresh();
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_red_light, android.R.color.holo_blue_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新：
                refresh();
            }
        });
    }


    private void initView() {
        mBack = findViewById(R.id.back);
        mSearchView = findViewById(R.id.search_team);
        mSwipeRefreshLayout = findViewById(R.id.swipe);
        mRecyclerViewS = findViewById(R.id.rv_teamList);

        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }

    private void refresh() {
        mData = mTeamService.findTeamList();
        Log.e(TAG,"小组列表："+mData);
        mSwipeRefreshLayout.setRefreshing(false);
        if (mData == null){
            mSwipeRefreshLayout.setRefreshing(false);
            Toast.makeText(this, "获取数据失败", Toast.LENGTH_SHORT).show();
        }
        else {
            groupAdapter = new GroupAdapter(ManyGroupActivity.this,mData);
            mRecyclerViewS.setLayoutManager(new LinearLayoutManager(ManyGroupActivity.this));
            mRecyclerViewS.setAdapter(groupAdapter);
        }
    }

}
