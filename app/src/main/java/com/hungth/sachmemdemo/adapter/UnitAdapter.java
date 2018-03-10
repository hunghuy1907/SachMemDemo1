package com.hungth.sachmemdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hungth.sachmemdemo.OnItemClickListenner;
import com.hungth.sachmemdemo.R;
import com.hungth.sachmemdemo.model.Unit;

import java.util.List;

/**
 * Created by Admin on 2/27/2018.
 */

public class UnitAdapter extends RecyclerView.Adapter<UnitAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private List<Unit> units;
    private Context context;
    private OnItemClickListenner onItemClickListenner;

    public UnitAdapter(Context context, List<Unit> units) {
        this.context = context;
        this.units = units;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_unit, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Unit unit = units.get(position);
        holder.txtUnitName.setText(unit.getName());

        holder.txtUnitName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListenner.onItemClicked(holder.itemView, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return units.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtUnitName;

        public ViewHolder(View itemView) {
            super(itemView);

            txtUnitName = itemView.findViewById(R.id.txt_unit);
        }
    }

    public void setOnItemClickListenner(OnItemClickListenner onItemClickListenner) {
        this.onItemClickListenner = onItemClickListenner;
    }
}
