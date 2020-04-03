package com.example.myapplication.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.dao.DaoImpl.UserDaoImpl;
import com.example.myapplication.module.MailboxInfo;
import com.example.myapplication.module.PlanListInfo;
import com.example.myapplication.service.ServiceImpl.UserServiceImpl;
import com.example.myapplication.util.Utils;

import java.util.List;


public class LetterListAdapter extends RecyclerView.Adapter<LetterListAdapter.LetterListViewHolder> {

    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<MailboxInfo> mailbox;
    private UserServiceImpl userService;
    private final String TAG = "LetterListAdapter";

    /*
        LetterListAdapter构造函数
     */
    public LetterListAdapter(List<MailboxInfo> mailbox){
        this.mailbox = mailbox;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private ChildPlanCardAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(ChildPlanCardAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public LetterListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return new LetterListViewHolder (mLayoutInflater.inflate(R.layout.item_list_mail,parent,false));

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_mail,parent,false);
        return new LetterListViewHolder(itemView);
    }
    //数据绑定
    @Override
    public void onBindViewHolder(@NonNull final LetterListViewHolder  holder, int position) {
        MailboxInfo mail = mailbox.get(position);

        holder.letter_who.setText(mail.getTitle());
        holder.letter_text.setText(mail.getContent().substring(0, 20) + "...");
        Log.d(TAG, "onBindViewHolder: " + mail.getFrom());
        holder.letter_from.setText(mail.getFrom());
        holder.letter_date.setText(Utils.sdf.format(mail.getRecelveTime()));

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
    //item数目
    @Override
    public int getItemCount() {
        return mailbox.size();
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


