package com.example.myapplication.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.InformationSys;
import com.example.myapplication.R;
import com.example.myapplication.module.SeatmateInfo;
import com.example.myapplication.module.SystemNoteInfo;
import com.example.myapplication.service.ServiceImpl.SeatmateServiceImpl;

import java.util.List;

import static com.example.myapplication.util.ConstUtil.SeatmateReplyType.TYPE_APPROVE;
import static com.example.myapplication.util.ConstUtil.SeatmateReplyType.TYPE_REFUSE;
import static com.example.myapplication.util.ConstUtil.SysNoteRead.SYS_NOTE_NOT_ON_READ;

public class InformationSysAdapter extends RecyclerView.Adapter<InformationSysAdapter.SystemInfoViewHolder> {

    private Context context;
    private LayoutInflater mLayoutInflater;

    private List<SystemNoteInfo> datas;
    public InformationSysAdapter(Context context, List<SystemNoteInfo> datas){
        this.datas = datas;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public SystemInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SystemInfoViewHolder(mLayoutInflater.inflate(R.layout.item_systeminfo,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final SystemInfoViewHolder holder, final int position) {
        holder.textView.setText(datas.get(position).getContent());
        /**
         * 点击这些个消息，现在只认为是有着同桌请求的消息，可能还会有小组的请求消息，这个时候就要根据消息的类型进行
         * 区分是哪种消息,
         * 当这个消息是已读的时候，点击无效果，即不能点击
         * 有bug,即每次进入消息列表都会是来自什么的打卡信息
         */

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 获取该用户需要回复的同桌的id
                 */
                final SeatmateServiceImpl seatmateService = new SeatmateServiceImpl();
                final List<SeatmateInfo> seatmateInfos = seatmateService.findSeatmateNeedToResponse(datas.get(position).getTo());
                switch (datas.get(position).getTitle()){
                    case "同桌邀请":
                        /**
                         * 实现信息的回复
                         */

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("同桌邀请");

                        builder.setMessage(datas.get(position).getContent());
                        builder.setPositiveButton("同意", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                seatmateService.replyRequest(seatmateInfos.get(0).getIdSeatmate(),TYPE_APPROVE);

                                holder.textView.setClickable(false);//设置不能点击
                                holder.textView.setText("已同意"+datas.get(position).getContent());//改变文本
                                datas.get(position).setContent("已同意"+datas.get(position).getContent());
                                datas.get(position).setIsRead(SYS_NOTE_NOT_ON_READ);
                                Log.d("ddddd",datas.get(position).getContent());
                                //Toast.makeText(context,Integer.toString(seatmateInfos.get(0).getStatus()),Toast.LENGTH_LONG).show();
                            }
                        });
                        builder.setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                seatmateService.replyRequest(seatmateInfos.get(0).getIdSeatmate(),TYPE_REFUSE);
                                holder.textView.setClickable(false);//设置不能点击
                                holder.textView.setText("已拒绝"+datas.get(position).getContent());//改变文本
                                datas.get(position).setContent("已拒绝"+datas.get(position).getContent());
                                datas.get(position).setIsRead(SYS_NOTE_NOT_ON_READ);
                                //Toast.makeText(context,Integer.toString(seatmateInfos.get(0).getStatus()),Toast.LENGTH_LONG).show();
                            }
                        });
                        builder.show();
                        break;
                    case "13":

                        break;
                        default:
                            //最终是信息是已读实现，已读位2
                            datas.get(position).setIsRead(SYS_NOTE_NOT_ON_READ);
                            break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class SystemInfoViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public SystemInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_information);
        }
    }
}
