package com.example.myapplication.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.example.myapplication.R;


public class LetterListAdapter extends RecyclerView.Adapter<LetterListAdapter.LetterListViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;

    /*
        LetterListAdapter构造函数
     */
    public LetterListAdapter(Context context){
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public LetterListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new  LetterListViewHolder (mLayoutInflater.inflate(R.layout.item_list_mail,parent,false));
    }
    //数据绑定
    @Override
    public void onBindViewHolder(@NonNull  LetterListViewHolder  holder, int position) {
        holder.letter_who.setText("你是谁");
        holder.letter_text.setText("146546113131");
        holder.letter_from.setText("来自哪里");
        holder.letter_date.setText("2020-3-3");
    }
    //item数目
    @Override
    public int getItemCount() {
        return 25;
    }


    /*
        LetterListViewHolder
     */
    public class LetterListViewHolder extends RecyclerView.ViewHolder {

        public TextView letter_who;
        public TextView letter_from;
        public TextView letter_text;
        public TextView letter_date;
        public LetterListViewHolder(View itemView) {
            super(itemView);
            letter_who = itemView.findViewById(R.id.letter_who);
            letter_text = itemView.findViewById(R.id.letter_text);
            letter_from = itemView.findViewById(R.id.letter_from);
            letter_date = itemView.findViewById(R.id.letter_date);
        }
    }
}


