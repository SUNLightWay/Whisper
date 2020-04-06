package com.example.myapplication;

import android.os.Bundle;

import com.example.myapplication.module.PlanListInfo;
import com.example.myapplication.module.PunchInfo;
import com.example.myapplication.service.ServiceImpl.PlanListServiceImpl;
import com.example.myapplication.service.ServiceImpl.PunchServiceImpl;
import com.example.myapplication.util.ConstUtil;
import com.example.myapplication.util.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.myapplication.util.ConstUtil.PunchAttitude.PUNCH_DISSTATISFIED;
import static com.example.myapplication.util.ConstUtil.PunchAttitude.PUNCH_SIMPLE;
import static com.example.myapplication.util.ConstUtil.PunchAttitude.PUNCH_STATISFIED;

public class PunchActivity extends AppCompatActivity {

    private PlanListServiceImpl planListService = new PlanListServiceImpl();
    private PunchServiceImpl punchService = new PunchServiceImpl();
    private String userId;

    private TextView summary;
    private Spinner attitude;
    private Button punch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punch);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userId = getIntent().getStringExtra("param2");

        summary = (TextView)findViewById(R.id.plan_summary);
        attitude = (Spinner)findViewById(R.id.plan_attitude);
        punch = (Button)findViewById(R.id.punch_main);

        List<PlanListInfo> planList = new ArrayList<>();
        planList.addAll(planListService.findLastPlanList(userId));
        List<PlanListInfo> planListPunched = new ArrayList<>();
        planListPunched.addAll(planListService.findPunchedPlanList(userId));
        final float completion = (float)planListPunched.size() / planList.size();
        summary.setText("#今日计划完成度：" + completion + " #");

        punch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int star = 0;
                if (attitude.getSelectedItem().toString().equals(ConstUtil.PunchAttitude.getTypeDesc(PUNCH_STATISFIED)))
                    star = 1;
                else if (attitude.getSelectedItem().toString().equals(ConstUtil.PunchAttitude.getTypeDesc(PUNCH_SIMPLE)))
                    star = 2;
                else if (attitude.getSelectedItem().toString().equals(ConstUtil.PunchAttitude.getTypeDesc(PUNCH_DISSTATISFIED)))
                    star = 3;
                Toast.makeText(PunchActivity.this,
                        punchService.addPunch(new PunchInfo(Utils.getRandomString(10), userId, new Date(), star, completion, summary.getText().toString())) == true ? "打卡成功": "打卡失败"
                        , Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
