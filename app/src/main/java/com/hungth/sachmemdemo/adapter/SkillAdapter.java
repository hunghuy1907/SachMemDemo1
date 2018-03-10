package com.hungth.sachmemdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hungth.sachmemdemo.OnItemClickListenner;
import com.hungth.sachmemdemo.R;
import com.hungth.sachmemdemo.model.Skill;

import java.util.List;

/**
 * Created by Admin on 2/27/2018.
 */

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Skill> skills;
    private Context context;
    private OnItemClickListenner onItemClickListenner;

    public SkillAdapter(Context context, List<Skill> skills) {
        this.context = context;
        this.skills = skills;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public SkillAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_skill, parent, false);
        return new SkillAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SkillAdapter.ViewHolder holder, int position) {
        Skill skill = skills.get(position);
        holder.txtskillName.setText(skill.getName());

        holder.txtskillName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListenner.onItemClicked(holder.itemView, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return skills.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtskillName;

        public ViewHolder(View itemView) {
            super(itemView);

            txtskillName = itemView.findViewById(R.id.txt_skill);
        }
    }

    public void setOnItemClickListenner(OnItemClickListenner onItemClickListenner) {
        this.onItemClickListenner = onItemClickListenner;
    }
}
