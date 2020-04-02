package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.dao.UserDao;
import com.example.myapplication.module.LoginInfo;
import com.example.myapplication.service.ServiceImpl.UserServiceImpl;
import com.example.myapplication.util.MD5Util;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "日志打印：-----";

    private EditText username, password;
    private Button login, register;
    private TextView forgetPassword;
    private ImageView q, weChat, sina;
    private MD5Util md5Util = new MD5Util();
    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponet();

    }

    /*
     * 初始化控件
     */
    private void initComponet() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        forgetPassword = findViewById(R.id.forget_password);
        forgetPassword.setOnClickListener(this);
        login = findViewById(R.id.btn_login);
        login.setOnClickListener(this);
        register = findViewById(R.id.btn_register);
        register.setOnClickListener(this);
    }

    /*
     * 监听事件 登录 注册 忘记密码 第三方登录
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                userLogin();
                break;
            case R.id.btn_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(this, "出错啦！", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    /*
     * 用户登录逻辑
     */
    public void userLogin() {

        //输入为空的情况处理
        if (username.getText().toString().equals("")) {
            Toast.makeText(this, "账号为空", Toast.LENGTH_SHORT).show();
        } else if (password.getText().toString().equals("")) {
            Toast.makeText(this, "密码为空", Toast.LENGTH_SHORT).show();
        } else {
            //获取加密后的密码
            String psw = md5Util.encrypt(password.getText().toString().trim());
            LoginInfo loginInfo = new LoginInfo(username.getText().toString().trim(), psw, null);
            if (userService.doLogin(loginInfo)) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("username", username.getText().toString()); //传递用户名
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "账号或密码错误！", Toast.LENGTH_SHORT).show();
                Log.e(TAG,"用户的输入:"+ psw);
            }
        }

    }

}
