package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ChildPlanCardAdapter;
import com.example.myapplication.adapter.LetterListAdapter;
import com.example.myapplication.adapter.MailListSentAdapter;
import com.example.myapplication.module.MailboxInfo;
import com.example.myapplication.service.ServiceImpl.MailServiceImpl;
import com.example.myapplication.util.Utils;

import java.util.List;

public class MailListSentActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<MailboxInfo> mailbox;
    private MailServiceImpl mailService = new MailServiceImpl();
    private String idUser;
    private MailListSentAdapter adapter;

    private final String TAG = "MailListSentActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mail_list_sent);

        idUser = getIntent().getStringExtra("param2");

        initRecyclerView();
    }

    private void initRecyclerView(){
        //获取RecyclerView
        mRecyclerView = findViewById(R.id.mail_list_sent);
        //设置线性布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mailbox = mailService.findMailSentListByUserId(idUser);

        if (mailbox == null)
            return;

        Log.d(TAG, "initMail: " + mailbox.size());

        //实例化MyAdapter并传入mList对象
        adapter = new MailListSentAdapter(mailbox);

        //为RecyclerView对象mRecyclerView设置adapter
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MailListSentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Utils.actionStart(MailListSentActivity.this, MailDetailActivity.class, String.valueOf(mailbox.get(0).getIdMail()), idUser);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(),
                        "long click: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
