package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.module.BulletinInfo;
import com.example.myapplication.module.MailboxInfo;
import com.example.myapplication.service.ServiceImpl.BulletinServiceImpl;
import com.example.myapplication.service.ServiceImpl.UserServiceImpl;
import com.example.myapplication.util.Utils;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<BulletinInfo> bulletinInfos;
    private BulletinServiceImpl bulletinService;
    private final String TAG = "RecyclerAdapter";


    public RecyclerAdapter(List<BulletinInfo> bulletinInfos) {
        this.bulletinInfos=bulletinInfos;
    }

    /**
     * item监听接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private ChildPlanCardAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(ChildPlanCardAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    /**
     * item显示类型
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout,parent,false);
        return new RecyclerAdapter.ViewHolder(itemView);
    }

    /**
     * 数据的绑定显示
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        BulletinInfo bulletin=bulletinInfos.get(position);

        //需要获取标题、发布时间
        holder.tvTag.setText(bulletin.getTitle());
        holder.tvTime.setText(Utils.sdf.format(bulletin.getTime()));

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return bulletinInfos.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView tvTitle;
        public TextView tvTime;
        public TextView tvTag;
        public TextView tvContext;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView) itemView.findViewById(R.id.imageView);
            tvTitle=(TextView)itemView.findViewById(R.id.tvTitle);
            tvTime=(TextView)itemView.findViewById(R.id.tvTime);
            tvTag=(TextView)itemView.findViewById(R.id.tvTag);
            tvContext=(TextView)itemView.findViewById(R.id.tvContext);
        }
    }

}
