package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapter.ChildPlanCardAdapter;
import com.example.myapplication.adapter.PlanCardAdapter;
import com.example.myapplication.module.ContactInfo;
import com.example.myapplication.module.PlanListInfo;
import com.example.myapplication.service.ServiceImpl.PlanListServiceImpl;
import com.example.myapplication.util.Utils;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlanDetailActivity extends AppCompatActivity {

    private String idFatherPlan;
    private String idUser;
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
        idUser = getIntent().getStringExtra("param2");
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

        CardView increasePlan = (CardView) findViewById(R.id.child_card_increase);
        increasePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.actionStart(PlanDetailActivity.this, IncreasePlanActivity.class, idFatherPlan, idUser);
            }
        });

    }

    protected void initPlanList(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        PlanListInfo fatherPlan = planListService.findPlanById(idFatherPlan);
        ((TextView)findViewById(R.id.father_card_title)).setText(fatherPlan.getTitle());
        ((TextView)findViewById(R.id.father_card_goal)).setText(fatherPlan.getGoal());
        ((TextView)findViewById(R.id.father_card_goal_type)).setText(fatherPlan.getGoalType());
        ((TextView)findViewById(R.id.father_card_time)).setText(
                sdf.format(fatherPlan.getStartTime()) + "~" + sdf.format(fatherPlan.getEndTime())
        );
        ((ProgressBar)findViewById(R.id.father_card_progressbar)).setProgress((int)fatherPlan.getCompletion());
        ((TextView)findViewById(R.id.father_card_time_limit)).setText("剩余" + Utils.differentDayMillisecond(new Date(), fatherPlan.getEndTime()) + "天");
        ((TextView)findViewById(R.id.child_card_significance)).setText(fatherPlan.getSignificance());

        plans = planListService.findChildPlanList(idFatherPlan);
        Log.d(TAG, "initPlanList: " + plans.size());
        mList.addAll(plans);

    }
}
