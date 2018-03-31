package com.hungth.sachmemdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hungth.sachmemdemo.view.OnItemClickListenner;
import com.hungth.sachmemdemo.R;
import com.hungth.sachmemdemo.model.Lesson;

import java.util.List;

/**
 * Created by Admin on 2/27/2018.
 */

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Lesson> lessons;
    private Context context;
    private OnItemClickListenner onItemClickListenner;

    public LessonAdapter(Context context, List<Lesson> lessons) {
        this.context = context;
        this.lessons = lessons;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public LessonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_lesson, parent, false);
        return new LessonAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LessonAdapter.ViewHolder holder, int position) {
        Lesson lesson = lessons.get(position);
        holder.txtLessonName.setText(lesson.getName());

        holder.txtLessonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListenner.onItemClicked(holder.itemView, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtLessonName;

        public ViewHolder(View itemView) {
            super(itemView);

            txtLessonName = itemView.findViewById(R.id.txt_lesson);
        }
    }

    public void setOnItemClickListenner(OnItemClickListenner onItemClickListenner) {
        this.onItemClickListenner = onItemClickListenner;
    }
}
