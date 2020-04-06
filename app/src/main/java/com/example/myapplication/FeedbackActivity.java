package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.module.FeedbackInfo;
import com.example.myapplication.service.ServiceImpl.UserServiceImpl;
import com.example.myapplication.ui.my.MyFragment;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText f_title;
    private EditText f_details;
    private EditText f_contact;

    private Button but_help_feedback;
    private Button Customer_tele;

    private String problem;
    private String details;
    private String contact;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        //初始化视图
        initView();
    }


    private void initView() {

        f_title=(EditText)findViewById(R.id.f_title);
        f_details =(EditText)findViewById(R.id.f_details);
        f_contact=(EditText)findViewById(R.id.f_contact);

        but_help_feedback=(Button)findViewById(R.id.but_help_feedback);
        but_help_feedback.setOnClickListener(this);

        Customer_tele=(Button)findViewById(R.id.Customer_tele);
        Customer_tele.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_help_feedback:
                but_feedback();
                //提交成功后提示提交成功
                Toast.makeText(getApplicationContext(),"提交成功!",Toast.LENGTH_SHORT).show();
                //返回 我的 页面
                //Intent intent=new Intent(FeedbackActivity.this, MyFragment.class);
                //startActivity(intent);
                break;
            case R.id.Customer_tele:
                String phoneNum="12345678910";
                toTelephone(phoneNum);
                break;
        }
    }

    private void toTelephone(String phoneNum) {

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum));
        startActivity(intent);
    }

    private void but_feedback() {
        problem=f_title.getText().toString();
        details=f_details.getText().toString();
        contact=f_contact.getText().toString();

        UserServiceImpl userService=new UserServiceImpl();
        userService.updateFeedbackInfo(problem,details,contact);


    }


}
