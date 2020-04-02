package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.module.UserInfo;
import com.example.myapplication.service.ServiceImpl.UserServiceImpl;
import com.example.myapplication.util.MD5Util;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "日志打印：-----";

    private EditText username, password;
    private Button register;
    private MD5Util md5Util = new MD5Util();
    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponet();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegister();
            }


        });
    }

    private void initComponet() {
        username = findViewById(R.id.tv_username);
        password = findViewById(R.id.tv_password);
        register = findViewById(R.id.register);

    }

    /*
     * 用户注册
     */
    public void userRegister() {
        if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
            Toast.makeText(this, "输入不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            UserInfo userInfo = new UserInfo();
            userInfo.setIdUser(username.getText().toString().trim());
            //加密密码
            String psw = md5Util.encrypt(password.getText().toString().trim());
            if (userService.doRegister(userInfo, psw)) {
                Log.e(TAG, "用户加密后的密码：" + psw);
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.putExtra("idUser", username.getText().toString().trim());
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
