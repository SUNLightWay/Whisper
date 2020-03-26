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
    public void onBindViewHolder(@NonNull PlanCardAdapter.ContactViewHolder holder, int position) {
        //contactInfoList中包含的都是ContactInfo类的对象
        //通过其get()方法可以获得其中的对象
        ContactInfo ci =contactInfoList.get(position);
        holder.item_tv.setText(ci.getText());
        holder.item_iv.setImageResource(ci.getImgId());
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
