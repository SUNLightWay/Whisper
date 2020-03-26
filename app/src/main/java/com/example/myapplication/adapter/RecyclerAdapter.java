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


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private LayoutInflater inflater;
    private String[] titles=null;

    public RecyclerAdapter(Context context) {
        this.inflater= LayoutInflater.from(context);
        this.titles=new String[20];
        for (int i=0;i<20;i++){
            int index=i+1;
            titles[i]="item"+index;
        }
    }

    /**
     * item显示类型
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    /**
     * 数据的绑定显示
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTag.setText(titles[position]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
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

    /**
     * 设置item的监听接口
     */

}
