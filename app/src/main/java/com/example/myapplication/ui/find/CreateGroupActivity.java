package com.example.myapplication.ui.find;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.module.TeamInfo;
import com.example.myapplication.service.ServiceImpl.TeamServiceImpl;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateGroupActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mBack;
    private CircleImageView mClTeamPhoto;
    private EditText mTeamTitle,mTeamInfo;
    private Button mCreateGroup;

    private TeamServiceImpl mteamService;
    private TeamInfo teamInfo;

    private SharedPreferences mPreferences;

    private String idUser = "phineas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        initView();
    }

    private void initView() {
        mBack = findViewById(R.id.back);
        mClTeamPhoto = findViewById(R.id.team_photo);
        mTeamInfo = findViewById(R.id.team_Introduct);
        mTeamTitle = findViewById(R.id.team_title);
        mCreateGroup = findViewById(R.id.btn_createTeam);

        mBack.setOnClickListener(this);
        mClTeamPhoto.setOnClickListener(this);
        mCreateGroup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.team_photo:
                Toast.makeText(this, "暂定", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_createTeam:
                createTeam();
                break;
        }
    }

    private void createTeam() {
        //mPreferences = getSharedPreferences("loginConfig")
        String title = mTeamTitle.getText().toString();
        String info = mTeamInfo.getText().toString();
        teamInfo = mteamService.createTeam(idUser,title,info,1);
        if (teamInfo == null){
            Toast.makeText(this, "创建失败！", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "创建成功！", Toast.LENGTH_SHORT).show();
        }
    }
}
