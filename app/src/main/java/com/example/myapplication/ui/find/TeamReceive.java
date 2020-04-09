package com.example.myapplication.ui.find;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

    private static final String TAG = "TeamReceive";
    private LinearLayout mBack;
    private TextView title, info;
    private CircleImageView mCirclew;
    private Button enjoy;

    private TeamInfo teamInfo;
    private TeamServiceImpl teamService = new TeamServiceImpl();


    private String teamID; //用户id

    private SharedPreferences mPreference;

    private String idUser = "phineas"; //暂定用户

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
                if (teamService.joinTeam(idUser,teamID)){
                    Toast.makeText(TeamReceive.this, "加入成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(TeamReceive.this, "加入失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*
     * 获取当前登录用户的idUser
     */
   /* public String getIdUser() {
        mPreference = getSharedPreferences("loginCOnfig", Context.MODE_PRIVATE);
        id = mPreference.getString("idUser", "");
        if (id != null){
            return id;
        }
        else {
            return null;
        }
    }
    */

    /*
     *初始化数据
     */
    private void initData() {
        //获取队伍id
        Intent intent = getIntent();
        teamID = intent.getStringExtra("id");
        teamInfo = teamService.findTeamById(teamID);
        if (teamInfo == null) {
            Toast.makeText(this, "获取数据失败", Toast.LENGTH_SHORT).show();
        } else {
            title.setText(teamInfo.getTeamTitle());
            info.setText(teamInfo.getTeamInfo());
            Log.e(TAG,"小组数据列表"+teamInfo.getTeamTitle()+"\t"+teamInfo.getTeamInfo());
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
