package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.module.MailboxInfo;
import com.example.myapplication.service.ServiceImpl.MailServiceImpl;
import com.example.myapplication.util.ConstUtil;
import com.example.myapplication.util.Utils;

import org.w3c.dom.Text;

public class MailDetailActivity extends AppCompatActivity {

    private final String TAG = "MailDetailActivity";
    private String userId;
    private Integer mailId;
    private MailServiceImpl mailService = new MailServiceImpl();
    private MailboxInfo mail;

    TextView sender;
    TextView subject;
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_detail);

        mailId = Integer.parseInt(getIntent().getStringExtra("param1"));
        userId = getIntent().getStringExtra("param2");
        Log.d(TAG, "onCreate: mailId: " + mailId + " userId: " + userId);

        sender = (TextView)findViewById(R.id.tv_sender);
        subject = (TextView)findViewById(R.id.tv_subject);
        content = (TextView)findViewById(R.id.tv_content);

        initMailContent();
    }

    private void initMailContent(){
        mail = mailService.getMailDetailById(String.valueOf(mailId));
        Log.d(TAG, "initMailContent: " + mail.getFrom());

        sender.setText(mail.getFrom());
        subject.setText(mail.getTitle());
        content.setText(mail.getContent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_response_mail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.enter_response_mail:
                //更新计划
                Utils.actionStart(this, WriteMailActivity.class, mail.getFrom(), userId);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
