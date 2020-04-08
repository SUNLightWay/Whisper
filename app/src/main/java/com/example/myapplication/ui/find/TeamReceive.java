package com.example.myapplication.ui.find;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.module.TeamInfo;
import com.example.myapplication.service.ServiceImpl.TeamServiceImpl;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeamReceive extends AppCompatActivity {

    private LinearLayout mBack;
    private TextView title, info;
    private CircleImageView mCirclew;
    private Button enjoy;
    private TeamInfo teamInfo;
    private TeamServiceImpl teamService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_receive);
        initView();

        initData();

        //返回
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //加入小组
        enjoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TeamReceive.this, "加入小组", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
     *初始化数据
     */
    private void initData() {
        //获取队伍id
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        teamInfo = teamService.findTeamById(id);
        if (teamInfo == null) {
            Toast.makeText(this, "获取数据失败", Toast.LENGTH_SHORT).show();
        } else {
            title.setText(teamInfo.getTeamTitle());
            info.setText(teamInfo.getTeamInfo());
        }
    }

    private void initView() {

        mBack = findViewById(R.id.back);
        mCirclew = findViewById(R.id.team_photo);
        title = findViewById(R.id.tean_Title);
        info = findViewById(R.id.team_info);
        enjoy = findViewById(R.id.enjoyTeam);

    }
}
