package com.example.myapplication.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.module.ContactInfo;

import java.util.List;


public class PlanCardAdapter extends RecyclerView.Adapter<PlanCardAdapter.ContactViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private List<ContactInfo> contactInfoList;
    private LayoutInflater mInflater;

    public PlanCardAdapter(List<ContactInfo> contactInfoList){
        this.contactInfoList = contactInfoList;
    }

//重写构造方法

    @NonNull
    @Override
    public PlanCardAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_plan_card,parent,false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlanCardAdapter.ContactViewHolder holder, int position) {
        //contactInfoList中包含的都是ContactInfo类的对象
        //通过其get()方法可以获得其中的对象
        ContactInfo ci =contactInfoList.get(position);
        holder.item_tv.setText(ci.getText());
        holder.item_iv.setImageResource(ci.getImgId());

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
        return contactInfoList.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder{

        private ImageView item_iv;
        private TextView item_tv;
        private CardView item_cardview;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            item_cardview = itemView.findViewById(R.id.item_plan_card);
            item_iv = itemView.findViewById(R.id.item_iv);
            item_tv = itemView.findViewById(R.id.item_tv);
        }
    }
}
