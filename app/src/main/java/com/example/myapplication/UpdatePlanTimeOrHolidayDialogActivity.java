package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.module.PlanListInfo;
import com.example.myapplication.service.ServiceImpl.PlanListServiceImpl;
import com.example.myapplication.util.ConstUtil;

public class UpdatePlanTimeOrHolidayDialogActivity extends AppCompatActivity implements View.OnClickListener {

    private PlanListServiceImpl planListService = new PlanListServiceImpl();
    private final String TAG = "UpdatePlanTimeOrHoliday";
    private String planId;

    private Button update;
    private Button applyHoliday;
    private Button punch;
    private EditText startTime;
    private EditText duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_plan_time_or_holiday_dialog);

        planId = getIntent().getStringExtra("param1");
        Log.d(TAG, "onCreate: " + planId);

        //该变量存储为偏移量shifting
        startTime = (EditText)findViewById(R.id.et_start_time_dialog);
        duration = (EditText)findViewById(R.id.et_duration_dialog);

        //按钮监听器
        update = (Button)findViewById(R.id.button_confirm_dialog);
        applyHoliday = (Button)findViewById(R.id.apply_holiday_dialog);
        punch = (Button)findViewById(R.id.punch_dialog);

        update.setOnClickListener(this);
        applyHoliday.setOnClickListener(this);
        punch.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_confirm_dialog:
                setResult(ConstUtil.ResponseCode.RESPONSE_CODE_REFRESH);
                PlanListInfo plan = new PlanListInfo();
                plan.setIdPlan(planId);
                plan.setShifting(Float.parseFloat(startTime.getText().toString().trim()));
                plan.setHourPerTime(Float.parseFloat(duration.getText().toString().trim()));
                Log.d(TAG, "onClick: " + Float.parseFloat(startTime.getText().toString().trim()) + " // " + Float.parseFloat(duration.getText().toString().trim()));
                //Log.d(TAG, "onClick: " + planListService.updatePlanInfo(plan));
                Toast.makeText(this, planListService.updatePlanInfo(plan) == true ? "更新成功": "更新失败", Toast.LENGTH_SHORT).show();
                finish();
                break;

            case R.id.apply_holiday_dialog:
                setResult(ConstUtil.ResponseCode.RESPONSE_CODE_REFRESH);
                PlanListInfo plan2 = new PlanListInfo();
                plan2.setIdPlan(planId);
                plan2.setIsHoliday(ConstUtil.PlanHolidayStatus.PLAN_ON_HOLIDAY);
                Toast.makeText(this, planListService.updatePlanInfo(plan2) == true ? "更新成功": "更新失败", Toast.LENGTH_SHORT).show();
                finish();
                break;

            case R.id.punch_dialog:
                setResult(ConstUtil.ResponseCode.RESPONSE_CODE_REFRESH);
                PlanListInfo plan3 = new PlanListInfo();
                plan3.setIdPlan(planId);
                plan3.setIsPunch(ConstUtil.PlanPunchStatus.PLAN_ON_PUNCH);
                Toast.makeText(this, planListService.updatePlanInfo(plan3) == true ? "更新成功": "更新失败", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
