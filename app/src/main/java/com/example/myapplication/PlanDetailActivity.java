package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapter.ChildPlanCardAdapter;
import com.example.myapplication.adapter.PlanCardAdapter;
import com.example.myapplication.module.ContactInfo;
import com.example.myapplication.module.PlanListInfo;
import com.example.myapplication.service.ServiceImpl.PlanListServiceImpl;
import com.example.myapplication.util.ConstUtil;
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
    private PlanListServiceImpl planListService = new PlanListServiceImpl();
    private RecyclerView mRecyclerView;

    private final String TAG = "PlanDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_detail);

        //获取fatherPlan的id
        idFatherPlan = getIntent().getStringExtra("param1");
        idUser = getIntent().getStringExtra("param2");
        Log.d(TAG, "onCreate: " + idFatherPlan);

        //获取RecycleView对象
        mRecyclerView = (RecyclerView) findViewById(R.id.child_card_list);
        mRecyclerView.setHasFixedSize(true);

        //创建布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //将RecyclerView对象指定到布局管理器layoutManager
        mRecyclerView.setLayoutManager(layoutManager);

        //初始化mList
        initPlanList();

        ImageButton increasePlan = (ImageButton) findViewById(R.id.child_card_increase);
        increasePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Utils.actionStart(PlanDetailActivity.this, IncreasePlanActivity.class, idFatherPlan, idUser);
                startActivityForResult(Utils.intentFactory(PlanDetailActivity.this, IncreasePlanActivity.class, idFatherPlan, idUser), ConstUtil.RequestCode.REQUEST_CODE_INCREASE);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_plan_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.enter_update_plan:
                //更新计划
                //Utils.actionStart(this, UpdatePlanActivity.class, idFatherPlan, idUser);
                startActivityForResult(Utils.intentFactory(this, UpdatePlanActivity.class, idFatherPlan, idUser), ConstUtil.RequestCode.REQUEST_CODE_UPDATE);
                break;
            case R.id.enter_delete_plan:
                //删除主计划
                planListService.deletePlan(idFatherPlan);
                setResult(ConstUtil.ResponseCode.RESPONSE_CODE_REFRESH);
                finish();
                Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void initPlanList(){

        mList.clear();

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

        //实例化MyAdapter并传入mList对象
        adapter = new ChildPlanCardAdapter(mList);

        //为RecyclerView对象mRecyclerView设置adapter
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ChildPlanCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Utils.actionStart(PlanDetailActivity.this, PlanDetailActivity.class, plans.get(0).getIdPlan(), idUser);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(),
                        "long click: " + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case (ConstUtil.RequestCode.REQUEST_CODE_UPDATE):
            case (ConstUtil.RequestCode.REQUEST_CODE_DETAIL):
            case (ConstUtil.RequestCode.REQUEST_CODE_INCREASE):
                if (resultCode == ConstUtil.ResponseCode.RESPONSE_CODE_REFRESH) {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    Log.d(TAG, "onActivityResult: refresh");
                }
                break;
                //确认删除时关闭该activity，同时更新上一activity
            case (ConstUtil.RequestCode.REQUEST_CODE_DELETE):
                if (resultCode == ConstUtil.ResponseCode.RESPONSE_CODE_FINISH){
                    setResult(ConstUtil.ResponseCode.RESPONSE_CODE_REFRESH);
                    finish();
                }
                break;

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
