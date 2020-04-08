package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.service.ServiceImpl.UserServiceImpl;
import com.example.myapplication.service.UserService;

public class PersonalRating extends AppCompatActivity {

    private RatingBar ratingBar;//评分条
    private Button rating_save;//保存按钮
    private Button rating_share;//分享
    private EditText edit_rating;//编辑今天的感受

    private float score;//暂时保存评分
    private String feelings;//暂存感受

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //加载布局文件
        setContentView(R.layout.activity_my_rating);

        ratingBar=findViewById(R.id.ratingBar);
        rating_save=findViewById(R.id.ratig_save);
        rating_share=findViewById(R.id.rating_share);
        edit_rating=findViewById(R.id.edit_rating);

        //创建监听器
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                //设置当前评分
                ratingBar.setRating(v);

                //先把评分暂时保存起来，待会跟感受一块更新
                score=v;
            }
        });

        rating_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feelings=edit_rating.getText().toString();
                //存入数据库中
                UserServiceImpl userService=new UserServiceImpl();
                userService.updateRankingInfo(score,feelings);
                //提示保存成功
                //Toast.makeText(getApplicationContext(),"保存成功!",Toast.LENGTH_SHORT);
                AlertDialog.Builder builder  = new AlertDialog.Builder(PersonalRating.this);
                builder.setTitle("保存成功" ) ;
                builder.setMessage("数据库内容已更新！" ) ;
                builder.setPositiveButton("OK", null);
                builder.show();
            }
        });

        rating_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //默认选取手机所有可以分享的app
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_SEND);//设置分享行为
                intent.setType("text/plain");//分享内容的类型
                intent.putExtra(Intent.EXTRA_SUBJECT,"<<我在轻语的今日体验>>");//设置分享标题
                //分享的内容
                intent.putExtra(Intent.EXTRA_TEXT,"<<我在轻语的今日体验>>\n"+feelings);
                //创建分享的Dialog，调用分享选择器
                intent=Intent.createChooser(intent,"分享到：");
                startActivity(intent);
            }
        });


    }


}
