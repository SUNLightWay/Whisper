package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.service.ServiceImpl.MailServiceImpl;
import com.example.myapplication.util.ConstUtil;
import com.example.myapplication.util.Utils;

import java.util.Calendar;
import java.util.Date;

public class WriteMailActivity extends AppCompatActivity {

    private final String TAG = "WriteMailActivity";
    private String from;
    private String to;

    private EditText receiver;
    private Spinner delay;
    private EditText subject;
    private EditText content;
    private Button bt_save;

    private MailServiceImpl mailService = new MailServiceImpl();

    Integer idMail = 0;
    Date sendTime = new Date();
    Date receiveTime = (Date)sendTime.clone();
    String title = "";
    String Content = "";
    Integer isPublic = ConstUtil.MailPublicType.TYPE_NOTPUBLIC;
    Integer isDelay = ConstUtil.MailDealyType.TYPE_DELAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_mail);

        from = getIntent().getStringExtra("param2");
        to = getIntent().getStringExtra("param1");

        if (to != null){
            receiver = (EditText)findViewById(R.id.receiver);
            Log.d(TAG, "onCreate: " + to);
            receiver.setText(to);
        }

        bt_save=(Button)findViewById(R.id.bt_save);
        receiver=(EditText)findViewById(R.id.receiver);
        subject=(EditText)findViewById(R.id.subject);
        content=(EditText)findViewById(R.id.content);
        delay=(Spinner)findViewById(R.id.delay);


        //是否延迟，默认2h
        initSpinner();

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //是否公开
                if(receiver.getText().toString().trim().equals("%")){
                    isPublic=ConstUtil.MailPublicType.TYPE_PUBLIC;
                }else {
                    to=receiver.getText().toString();
                }

                //获取信件主题、内容
                Content=content.getText().toString();
                title=subject.getText().toString();

                Toast.makeText(getApplicationContext(),
                        mailService.sendMail(Utils.getRandomInteger(6),from, to, isPublic, isDelay ,sendTime, receiveTime, title, Content) == true? "发送成功": "发送失败"
                        , Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    /**
     * 设置Spinner
     */
    private void initSpinner(){

        //设置Spinner数据源
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.delayarr,
                android.R.layout.simple_spinner_item);
        // 设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 绑定数据源到spinner控件上
        delay.setAdapter(adapter);

        delay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //此处的parent是指的spPro的下拉框
                Spinner spinner = (Spinner) parent;
                //通过getItemAtPosition(position)的方法来找到项的名称
                String date = (String) spinner.getItemAtPosition(position);
                if(date.equals("2h")){
                    isDelay = ConstUtil.MailDealyType.TYPE_NOTDELAY;
                    receiveTime.setHours(sendTime.getHours() + 2);
                }else if(date.equals("3天")){
                    isDelay = ConstUtil.MailDealyType.TYPE_DELAY;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}

