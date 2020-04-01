package com.example.myapplication.ui.plan;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.IncreasePlanActivity;
import com.example.myapplication.PlanDetailActivity;
import com.example.myapplication.ScheduleChartActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.PlanCardAdapter;
import com.example.myapplication.module.ContactInfo;
import com.example.myapplication.module.PlanListInfo;
import com.example.myapplication.service.ServiceImpl.PlanListServiceImpl;
import com.example.myapplication.util.DBUtil;
import com.example.myapplication.util.Utils;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlanFragment extends Fragment{

    private PlanViewModel planViewModel;
    private PlanCardAdapter adapter;
    List<ContactInfo> mList = new ArrayList<>();
    List<PlanListInfo> plans;     //计划列表
    private View view;
    private PlanListServiceImpl planListService = new PlanListServiceImpl();
    private String userId = "phineas";

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        //删除上方留白
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


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d("PlanFragment", "onCreateView:");

        planViewModel =
                ViewModelProviders.of(this).get(PlanViewModel.class);
        View root = inflater.inflate(R.layout.fragment_plan, container, false);
        view = root;

        //获取RecycleView对象
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.card_list);
        mRecyclerView.setHasFixedSize(true);

        //创建布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //将RecyclerView对象指定到布局管理器layoutManager
        mRecyclerView.setLayoutManager(layoutManager);

        //初始化mList
        initInfo();

        //实例化MyAdapter并传入mList对象
        adapter = new PlanCardAdapter(mList);

        //为RecyclerView对象mRecyclerView设置adapter
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new PlanCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Utils.actionStart(getActivity(), PlanDetailActivity.class, plans.get(position).getIdPlan(), userId);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getActivity().getApplicationContext(),
                        "long click: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menu_toolbar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.action_check_chart:
                //跳转到日程表界面
                Utils.actionStart(getActivity(), ScheduleChartActivity.class, null, null);
                break;
            case R.id.action_increase_plan:
                //跳转到添加计划界面
                Utils.actionStart(getActivity(), IncreasePlanActivity.class, "-1", userId);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initInfo(){
        plans = planListService.findFirstLevelPlanList();
        for (PlanListInfo plan:plans
             ) {
            ContactInfo card = new ContactInfo(plan.getTitle(),
                    Utils.differentDayMillisecond(new Date(), plan.getEndTime()), plan.getSignificance(),R.drawable.bg_card_01);
            mList.add(card);
        }
        /*ContactInfo card1 = new ContactInfo("考研",R.drawable.bg_card_01);
        mList.add(card1);
        ContactInfo card2 = new ContactInfo("学习演讲",R.drawable.bg_card_02);
        mList.add(card2);
        ContactInfo card3 = new ContactInfo("先占个位",R.drawable.bg_card_03);
        mList.add(card3);*/
    }
}