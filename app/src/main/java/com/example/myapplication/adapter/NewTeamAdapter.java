package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.module.TeamInfo;
import com.example.myapplication.ui.find.TeamReceive;

import java.util.List;

public class NewTeamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<TeamInfo> data;

    private int ITEM_MAX = 3; //最多显示三条数据

    public NewTeamAdapter(Context context,List<TeamInfo> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newteam_list,parent,false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final RecyclerHolder recyclerHolder = (RecyclerHolder) holder;
        final TeamInfo teamInfo =  data.get(position);
        recyclerHolder.title.setText(teamInfo.getTeamTitle());
        //点击事件
        recyclerHolder.newTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TeamReceive.class);
                intent.putExtra("id",teamInfo.getIdTeam());
                context.startActivity(intent);
            }
        });
    }


    /*
     * item最大条目为ITEM_MAX
     */
    @Override
    public int getItemCount() {
        return ITEM_MAX;
    }

    private class RecyclerHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private LinearLayout newTeam;

        public RecyclerHolder(View view){
            super(view);
            title = view.findViewById(R.id.TeamTitle);
            newTeam =view.findViewById(R.id.newTeam);
        }
    }
}
