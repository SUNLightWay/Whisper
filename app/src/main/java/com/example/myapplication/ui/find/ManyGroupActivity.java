package com.example.myapplication.ui.find;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.myapplication.MailListActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.GroupAdapter;
import com.example.myapplication.adapter.LetterListAdapter;
import com.example.myapplication.module.TeamInfo;
import com.example.myapplication.service.ServiceImpl.TeamServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class ManyGroupActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ManyGroupActivity";
    private LinearLayout mBack;
    private SearchView mSearchView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerViewS;

    private TeamServiceImpl mTeamService = new TeamServiceImpl();

    private List<TeamInfo> mData = new ArrayList<>();
    private GroupAdapter groupAdapter;
    private String keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
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

        SearchView searchView = (SearchView)findViewById(R.id.search_team);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                keyword = newText;
                mRecyclerViewS.setAdapter(new GroupAdapter(ManyGroupActivity.this, null));
                mRecyclerViewS.removeAllViews();
                refresh();
                Toast.makeText(ManyGroupActivity.this, "text changed", Toast.LENGTH_SHORT).show();
                return false;
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
        if (keyword == null || keyword.equals(""))
            mData = mTeamService.findTeamList();
        else
            mData = mTeamService.findTeamListByKeyword(keyword);

        Log.e(TAG,"小组列表："+mData);
        mSwipeRefreshLayout.setRefreshing(false);
        if (mData == null){
            mSwipeRefreshLayout.setRefreshing(false);
            Toast.makeText(this, "获取数据失败", Toast.LENGTH_SHORT).show();
        }
        else {
            //mSwipeRefreshLayout.setRefreshing(true);
            groupAdapter = new GroupAdapter(ManyGroupActivity.this,mData);
            mRecyclerViewS.setLayoutManager(new LinearLayoutManager(ManyGroupActivity.this));
            mRecyclerViewS.setAdapter(groupAdapter);
        }
    }

}
