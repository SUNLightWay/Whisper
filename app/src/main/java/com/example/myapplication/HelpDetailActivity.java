package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.module.BulletinInfo;
import com.example.myapplication.service.BulletinService;
import com.example.myapplication.service.ServiceImpl.BulletinServiceImpl;

public class HelpDetailActivity extends AppCompatActivity {

    private final String TAG = "HelpDetailActivity";
    private Integer bulletinId;
    private BulletinServiceImpl bulletinService = new BulletinServiceImpl();
    private BulletinInfo bulletin;

    private TextView tv_title;
    private TextView tv_context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_list);

        bulletinId=Integer.parseInt(getIntent().getStringExtra("param1"));
        Log.d(TAG, "onCreate: mailId: " + bulletinId + " userId: " );

        tv_title=(TextView)findViewById(R.id.tv_title);
        tv_context=(TextView)findViewById(R.id.tv_context);

    }

    /**
     *  private void initMailContent(){
     *         mail = mailService.getMailDetailById(String.valueOf(mailId));
     *         Log.d(TAG, "initMailContent: " + mail.getFrom());
     *
     *         sender.setText(mail.getFrom());
     *         subject.setText(mail.getTitle());
     *         content.setText(mail.getContent());
     *     }
     */
    private void initBulletinContent(){
        bulletin= (BulletinInfo) bulletinService.getBulletinsById(bulletinId);
        Log.d(TAG, "initBulletinContent: "+bulletin.getTitle() );

        tv_title.setText(bulletin.getTitle());
        tv_context.setText(bulletin.getContent());

    }
}
