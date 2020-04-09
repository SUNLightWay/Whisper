package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.module.SeatmateInfo;
import com.example.myapplication.module.UserInfo;
import com.example.myapplication.service.SeatmateService;
import com.example.myapplication.service.ServiceImpl.SeatmateServiceImpl;
import com.example.myapplication.util.ConstUtil;
import com.example.myapplication.util.Utils;

import java.util.Date;
import java.util.List;

public class DeskMateAdapter extends RecyclerView.Adapter<DeskMateAdapter.DeskMateListViewHolder> {

    private String idUser;
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<UserInfo> datas; //传过来用户列表

    /*
    DeskMateAdapter的构造函数
     */
    public DeskMateAdapter(Context context ,List<UserInfo> datas,String idUser){
        this.datas = datas;
        this.context = context;
        this.idUser = idUser;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public DeskMateListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DeskMateListViewHolder(mLayoutInflater.inflate(R.layout.item_list_deskmate,parent,false));
    }
    //数据绑定
    @Override
    public void onBindViewHolder(@NonNull final DeskMateListViewHolder holder, final int position) {
        //头像
       holder.imageView.setImageBitmap(BitmapFactory.decodeByteArray(datas.get(position).getHeadshot(),0,datas.get(position).getHeadshot().length));
        //显示用户id
        holder.nickname.setText(datas.get(position).getIdUser());
        Log.d("aaaa","onBindViewHolder————"+datas.get(position).getIdUser()+"  "+position+" "+datas.size());

        // holder.nickname.setText("昵称");
        //打卡天数，和正在学习什么数据库内没有数据
        holder.study_days.setText("打卡天数");
        holder.study_now.setText("正在学习............");

        //点击申请按钮，进行申请同桌，
        holder.apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 提示申请
                 * 修改列表申请标记
                 * 向申请人发送申请
                 */

                SeatmateInfo seatmateInfo = new SeatmateInfo();
                seatmateInfo.setIdSeatmate(Utils.getRandomString(10));
                seatmateInfo.setPerson1(idUser); //设定一个名字,发起请求的人
                seatmateInfo.setPerson2(datas.get(position).getIdUser());


                //设置状态等待答复。
                seatmateInfo.setStatus(ConstUtil.SeatmateStatus.STATUS_WAITING_ANOTHER_RESPONSE);
                SeatmateServiceImpl seatmateService = new SeatmateServiceImpl();
                //保存同桌信息，发送请求,其实就是保存这个同桌信息。
                seatmateService.sendRequest(seatmateInfo);
               Log.d("bbb",seatmateInfo.getIdSeatmate()+" "+seatmateInfo.getPerson1()+" "+seatmateInfo.getPerson2());
                 //弹出申请
                 Toast.makeText(context,"已发出申请", Toast.LENGTH_LONG).show();
                 //改变申请按钮的标记
                holder.apply.setText("已申请");
            }
        });

    }
    //item的显示数目
    @Override
    public int getItemCount() {
        return datas.size();
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
            apply = itemView.findViewById(R.id.apply);
        }
    }
}