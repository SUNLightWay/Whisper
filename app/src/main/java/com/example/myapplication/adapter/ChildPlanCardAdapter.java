package com.example.myapplication.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.module.ContactInfo;
import com.example.myapplication.module.PlanListInfo;
import com.example.myapplication.service.ServiceImpl.PlanListServiceImpl;

import java.text.SimpleDateFormat;
import java.util.List;


public class ChildPlanCardAdapter extends RecyclerView.Adapter<ChildPlanCardAdapter.ContactViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private List<PlanListInfo> planListInfos;
    private LayoutInflater mInflater;
    private PlanListServiceImpl planListService = new PlanListServiceImpl();

    public ChildPlanCardAdapter(List<PlanListInfo> planListInfos){
        this.planListInfos = planListInfos;
    }

//重写构造方法

    @NonNull
    @Override
    public ChildPlanCardAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_child_card,parent,false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChildPlanCardAdapter.ContactViewHolder holder, int position) {
        //contactInfoList中包含的都是ContactInfo类的对象
        //通过其get()方法可以获得其中的对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        PlanListInfo plan = planListInfos.get(position);
        List<PlanListInfo> childlist = planListService.findChildPlanList(plan.getIdPlan());

        holder.child_card_title.setText(plan.getTitle());
        holder.child_card_goal.setText(plan.getGoal());
        holder.child_card_goal_type.setText(plan.getGoalType());
        holder.child_card_stage_plan_list.setText(plan.getTitle());
        for (PlanListInfo childPlan: childlist
             ) {
            holder.child_card_stage_plan_list.setText(
                    sdf.format(childPlan.getStartTime()) + "~" + sdf.format(childPlan.getEndTime()) + " " +
                            childPlan.getTitle()
            );
        }
        if (childlist.size() == 0){
            holder.child_card_stage_plan_list.setText(
                    sdf.format(plan.getStartTime()) + "~" + sdf.format(plan.getEndTime()) + " " +
                            plan.getTitle()
            );
        }
        holder.child_card_progressbar.setProgress((int)plan.getCompletion());


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
        return planListInfos.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder{

        private TextView child_card_title;
        private TextView child_card_goal;
        private TextView child_card_goal_type;
        private TextView child_card_stage_plan_list;
        private ProgressBar child_card_progressbar;


        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            child_card_title = itemView.findViewById(R.id.child_card_title);
            child_card_goal = itemView.findViewById(R.id.child_card_goal);
            child_card_goal_type = itemView.findViewById(R.id.child_card_goal_type);
            child_card_stage_plan_list = itemView.findViewById(R.id.child_card_stage_plan_list);
            child_card_progressbar = itemView.findViewById(R.id.child_card_progressbar);
        }
    }
}
