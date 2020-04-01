package com.example.myapplication;

import android.os.Bundle;

import com.example.myapplication.module.PlanListInfo;
import com.example.myapplication.service.ServiceImpl.PlanListServiceImpl;
import com.example.myapplication.util.ConstUtil;
import com.example.myapplication.util.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.text.ParseException;

public class IncreasePlanActivity extends AppCompatActivity {

    private PlanListServiceImpl planListService = new PlanListServiceImpl();
    private PlanListInfo plan = new PlanListInfo();
    private String fatherPlanId;
    private String userId;
    private final String TAG = "IncreasePlanActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_increase_plan);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fatherPlanId = getIntent().getStringExtra("param1");
        userId = getIntent().getStringExtra("param2");
        Log.d(TAG, "onCreate: fatherPlanId" + fatherPlanId + "/ userId:" + userId);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        CheckBox isLast = (CheckBox)findViewById(R.id.cb_is_last);
        isLast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    plan.setIsLast(1);
                }else{
                    plan.setIsLast(0);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar_increase_plan, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.enter_increase:
                //跳转到日程表界面
                setValue();
                planListService.addPlan(plan);
                this.finish();
                //Utils.actionStart(this, ScheduleChartActivity.class, null, null);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setValue(){
        plan.setTitle(((TextView)findViewById(R.id.et_title)).getText().toString());
        plan.setBless(((TextView)findViewById(R.id.et_blessing)).getText().toString());
        plan.setSignificance(((TextView)findViewById(R.id.et_significance)).getText().toString());
        plan.setGoal(((TextView)findViewById(R.id.et_goal)).getText().toString());
        plan.setGoalType(((TextView)findViewById(R.id.et_goal_type)).getText().toString());
        plan.setDetail(((TextView)findViewById(R.id.et_detail)).getText().toString());
        try {
            plan.setStartTime(Utils.sdf.parse(((TextView)findViewById(R.id.et_start_time)).getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            plan.setEndTime(Utils.sdf.parse(((TextView)findViewById(R.id.et_end_time)).getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        plan.setSumHourNeeded(Integer.parseInt(((TextView)findViewById(R.id.et_time_needed)).getText().toString()));
        plan.setCompletion(0);
        plan.setFatherPlan(fatherPlanId);
        plan.setIdPlan(Utils.getRandomString(10));
        plan.setIdUser(userId);
        plan.setType(ConstUtil.PlanType.TYPE_PERSONAL);
        plan.setHourPerTime(Float.parseFloat(((TextView)findViewById(R.id.et_time_n)).getText().toString()));
    }
}
