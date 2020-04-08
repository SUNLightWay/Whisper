package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.module.PlanListInfo;
import com.example.myapplication.service.ServiceImpl.PlanListServiceImpl;
import com.example.myapplication.util.ConstUtil;
import com.example.myapplication.util.Utils;

import java.text.ParseException;

public class UpdatePlanActivity extends AppCompatActivity {

    private String userId;
    private String planId;
    private PlanListInfo plan;
    private final String TAG = "UpdatePlanActivity";
    private PlanListServiceImpl planListService = new PlanListServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_plan);

        userId = getIntent().getStringExtra("param2");
        planId = getIntent().getStringExtra("param1");

        initEditText();

        CheckBox isLast = (CheckBox)findViewById(R.id.cb_is_last1);
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
                //完成
                setValue();
                setResult(ConstUtil.ResponseCode.RESPONSE_CODE_REFRESH);
                Toast.makeText(this, planListService.updatePlanInfo(plan) == true? "更新成功": "更新失败", Toast.LENGTH_SHORT).show();
                this.finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initEditText(){
        plan = planListService.findPlanById(planId);

        ((EditText)findViewById(R.id.et_time_n1)).setText(String.valueOf(plan.getHourPerTime()));
        ((EditText)findViewById(R.id.et_time_needed1)).setText(String.valueOf(plan.getSumHourNeeded()));
        ((EditText)findViewById(R.id.et_start_time1)).setText(Utils.sdf.format(plan.getStartTime()));
        ((EditText)findViewById(R.id.et_end_time1)).setText(Utils.sdf.format(plan.getEndTime()));
        ((EditText)findViewById(R.id.et_detail1)).setText(plan.getDetail());
        ((EditText)findViewById(R.id.et_goal_type1)).setText(plan.getGoalType());
        ((EditText)findViewById(R.id.et_goal1)).setText(plan.getGoal());
        ((EditText)findViewById(R.id.et_significance1)).setText(plan.getSignificance());
        ((EditText)findViewById(R.id.et_blessing1)).setText(plan.getBless());
        ((EditText)findViewById(R.id.et_title1)).setText(plan.getTitle());
    }

    private void setValue(){
        plan.setTitle(((TextView)findViewById(R.id.et_title1)).getText().toString());
        plan.setBless(((TextView)findViewById(R.id.et_blessing1)).getText().toString());
        plan.setSignificance(((TextView)findViewById(R.id.et_significance1)).getText().toString());
        plan.setGoal(((TextView)findViewById(R.id.et_goal1)).getText().toString());
        plan.setGoalType(((TextView)findViewById(R.id.et_goal_type1)).getText().toString());
        plan.setDetail(((TextView)findViewById(R.id.et_detail1)).getText().toString());
        try {
            plan.setStartTime(Utils.sdf.parse(((TextView)findViewById(R.id.et_start_time1)).getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            plan.setEndTime(Utils.sdf.parse(((TextView)findViewById(R.id.et_end_time1)).getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        plan.setSumHourNeeded(Integer.parseInt(((TextView)findViewById(R.id.et_time_needed1)).getText().toString()));
        plan.setCompletion(0);
        plan.setIdPlan(planId);
        plan.setIdUser(userId);
        Log.d(TAG, "setValue: " + plan.getIdUser());
        plan.setType(ConstUtil.PlanType.TYPE_PERSONAL);
        plan.setHourPerTime(Float.parseFloat(((TextView)findViewById(R.id.et_time_n1)).getText().toString()));
    }


}
