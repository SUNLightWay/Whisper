package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapter.ChildPlanCardAdapter;
import com.example.myapplication.adapter.PlanCardAdapter;
import com.example.myapplication.module.ContactInfo;
import com.example.myapplication.module.PlanListInfo;
import com.example.myapplication.service.ServiceImpl.PlanListServiceImpl;
import com.example.myapplication.util.Utils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PlanDetailActivity extends AppCompatActivity {

    private String idFatherPlan;
    private ChildPlanCardAdapter adapter;
    List<PlanListInfo> mList = new ArrayList<>();
    List<PlanListInfo> plans;     //计划列表
    private final String TAG = "PlanDetailActivity";
    private PlanListServiceImpl planListService = new PlanListServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_detail);

        //获取fatherPlan的id
        idFatherPlan = getIntent().getStringExtra("param1");
        Log.d(TAG, "onCreate: " + idFatherPlan);

        //获取RecycleView对象
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.child_card_list);
        mRecyclerView.setHasFixedSize(true);

        //创建布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //将RecyclerView对象指定到布局管理器layoutManager
        mRecyclerView.setLayoutManager(layoutManager);

        //初始化mList
        initPlanList();

        //实例化MyAdapter并传入mList对象
        adapter = new ChildPlanCardAdapter(mList);

        //为RecyclerView对象mRecyclerView设置adapter
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ChildPlanCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Utils.actionStart(PlanDetailActivity.this, PlanDetailActivity.class, plans.get(0).getIdPlan(), null);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(),
                        "long click: " + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    protected void initPlanList(){

        plans = planListService.findChildPlanList(idFatherPlan);
        Log.d(TAG, "initPlanList: " + plans.size());
        mList.addAll(plans);

    }
}
