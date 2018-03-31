package com.hungth.sachmemdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hungth.sachmemdemo.view.OnItemClickListenner;
import com.hungth.sachmemdemo.R;
import com.hungth.sachmemdemo.model.Class;

import java.util.List;

/**
 * Created by Admin on 2/3/2018.
 */

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder>{
    private List<Class> classNames;
    private LayoutInflater inflater;
    private Context context;
    private OnItemClickListenner onItemClickListenner;


    public ClassAdapter(Context context, List<Class> classNames) {
        this.context = context;
        this.classNames = classNames;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_class, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Class className = classNames.get(position);

        holder.txtClassName.setText(className.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListenner.onItemClicked(holder.itemView, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return classNames.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtClassName;

        public ViewHolder(View itemView) {
            super(itemView);
            txtClassName = itemView.findViewById(R.id.txt_class);
        }
    }

    public Class getItem(int position) {
        return classNames.get(position);
    }

    public void setOnItemClickListenner(OnItemClickListenner onItemClickListenner) {
        this.onItemClickListenner = onItemClickListenner;

    }


}
