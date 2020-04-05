package com.example.myapplication.ui.mail;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.HelpDetailActivity;
import com.example.myapplication.MailListActivity;
import com.example.myapplication.MailListSentActivity;
import com.example.myapplication.R;
import com.example.myapplication.WriteMailActivity;
import com.example.myapplication.adapter.ChildPlanCardAdapter;
import com.example.myapplication.adapter.RecyclerAdapter;
import com.example.myapplication.module.BulletinInfo;
import com.example.myapplication.service.ServiceImpl.BulletinServiceImpl;
import com.example.myapplication.util.Utils;

import java.util.List;

public class MailFragment extends Fragment{

    private MailViewModel mailViewModel;
    private View view;  //定义view用来设置fragment的layout

    private List<BulletinInfo> bulletinInfos ;
    private BulletinServiceImpl bulletinService=new BulletinServiceImpl();

    public RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerAdapter adapter;

    private String idUser = "phineas";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }
    }
    /**
     * 继承Fragment类，重写两个方法
     * 第一个方法onCreateView--返回布局
     * 第二个方法onViewCreated--绑定控件
     * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =inflater.inflate(R.layout.fragment_mail, container, false);   //获取fragment的layout
        view = root;

        //获取RecyclerView对象
        recyclerView=view.findViewById(R.id.recyclerView);
        //设置固定的大小
        recyclerView.setHasFixedSize(true);

        //创建布局管理器
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //给RecyclerView设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        bulletinInfos=bulletinService.getBulletins();
        if(bulletinInfos==null)
            return null;

        //实例化Adapter并传入List对象
        adapter=new RecyclerAdapter(bulletinInfos);
        //为RecyclerView对象mRecyclerView设置adapter
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ChildPlanCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Utils.actionStart(getActivity(), HelpDetailActivity.class, String.valueOf(bulletinInfos.get(0).getIdButtetin()), idUser);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getContext(),"long click: " + position, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        //初始化监听器
        initClickListener();
        super.onStart();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    public void initClickListener(){

        ImageButton ibMailBox = (ImageButton)getActivity().findViewById(R.id.ib_mail_box);
        ibMailBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.actionStart(getActivity(), MailListActivity.class, null, idUser);
            }
        });

        ImageButton sendEmail = (ImageButton)getActivity().findViewById(R.id.btWriteLetters);
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.actionStart(getActivity(), WriteMailActivity.class, null, idUser);
            }
        });

        ImageButton postBox = (ImageButton)getActivity().findViewById(R.id.btPostbox);
        postBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.actionStart(getActivity(), MailListSentActivity.class, null, idUser);
            }
        });
    }

}
