package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class DeskMateAdapter extends RecyclerView.Adapter<DeskMateAdapter.DeskMateListViewHolder> {

    private Context context;
    private LayoutInflater mLayoutInflater;

    /*
    DeskMateAdapter的构造函数
     */
    public DeskMateAdapter(Context context){
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public DeskMateListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DeskMateListViewHolder(mLayoutInflater.inflate(R.layout.item_list_deskmate,parent,false));
    }
    //数据绑定
    @Override
    public void onBindViewHolder(@NonNull DeskMateListViewHolder holder, int position) {
        holder.imageView.setImageResource(R.drawable.deskone);
        holder.nickname.setText("昵称");
        holder.study_days.setText("打卡天数");
        holder.study_now.setText("正在学习............");

    }


    //item的显示数目
    @Override
    public int getItemCount() {
        return 10;
    }


    /**
     * DEskAdapterViewHolder
     */

   public class DeskMateListViewHolder extends RecyclerView.ViewHolder {

       private ImageView imageView; //头像
       private TextView nickname; //昵称
       private TextView study_days;  //打卡天数
        private Button apply;   //申请
        private TextView study_now; //正在学学习
        public DeskMateListViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nickname = itemView.findViewById(R.id.nickname);
            study_days = itemView.findViewById(R.id.study_days);
            study_now = itemView.findViewById(R.id.study_now);
        }
    }
}
