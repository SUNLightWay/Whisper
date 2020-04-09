package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.module.TeamInfo;
import com.example.myapplication.service.ServiceImpl.TeamServiceImpl;
import com.example.myapplication.ui.find.TeamReceive;
import com.example.myapplication.ui.find.dialog.CustomerDialog;

import java.util.List;


public class GroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG =  "GroupAdapter";

    Context context;
    List<TeamInfo> data;

    private final int N_TYPE = 0;
    private final int F_TYPE = 1;
    private Boolean isfootView = true;
    private int MAX_NUM = 6; //预加载的数据 一共6条

    private TeamServiceImpl teamService = new TeamServiceImpl();
    private CustomerDialog customerDialog;

    private String idUser = "phineas"; //暂定用户

    public GroupAdapter(Context context, List<TeamInfo> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public Recycle_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_item, parent, false);
        View footView = LayoutInflater.from(parent.getContext()).inflate(R.layout.foot_item, parent, false);
        if (viewType == F_TYPE) {
            return new Recycle_ViewHolder(footView, F_TYPE);
        } else {
            return new Recycle_ViewHolder(view, N_TYPE);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (isfootView && (getItemViewType(position)) == F_TYPE) {
            final Recycle_ViewHolder recycle_viewHolder = (Recycle_ViewHolder) holder;
            recycle_viewHolder.loading.setText("加载中...");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MAX_NUM += 6;  //每次加载出8条数据
                    notifyDataSetChanged();
                }
            }, 4000);
        } else {
            //activity_team_receive的内容
            final Recycle_ViewHolder recycle_viewHolder = (Recycle_ViewHolder) holder;
            final TeamInfo teamInfo = data.get(position);
            recycle_viewHolder.title.setText(teamInfo.getTeamTitle());
            recycle_viewHolder.info.setText(teamInfo.getTeamInfo());
            //加入按钮
            recycle_viewHolder.enjoy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (teamService.joinTeam(idUser, teamInfo.getIdTeam())) {
                        customerDialog = new CustomerDialog(context,"温馨提示", "加入成功", "确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customerDialog.dismiss();
                                Log.e(TAG,"判断加入是否成功");
                            }
                        });
                        customerDialog.setCanotBackPress();
                        customerDialog.setCanceledOnTouchOutside(false);
                        customerDialog.show();
                    }
                }
            });
            recycle_viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //查看小组信息
                    int i = recycle_viewHolder.getAdapterPosition();
                    Intent intent = new Intent(context, TeamReceive.class);
                    intent.putExtra("id", data.get(i).getIdTeam());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == MAX_NUM - 1) {
            return F_TYPE;
        } else {
            return N_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        if(data == null)
            return 0;
        if (data.size() < MAX_NUM) {
            return data.size();
        }
        return MAX_NUM;
    }

    private class Recycle_ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, info;
        private Button enjoy;
        private TextView loading;

        public Recycle_ViewHolder(View view, int view_type) {
            super(view);
            if (view_type == N_TYPE) {
                title = view.findViewById(R.id.title);
                info = view.findViewById(R.id.info);
                enjoy = view.findViewById(R.id.enjoy);
            } else if (view_type == F_TYPE) {
                loading = view.findViewById(R.id.footText);
            }
        }
    }
}
