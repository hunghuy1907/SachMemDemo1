package com.hungth.sachmemdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hungth.sachmemdemo.view.OnItemClickListenner;
import com.hungth.sachmemdemo.R;
import com.hungth.sachmemdemo.model.Book;

import java.util.List;

/**
 * Created by Admin on 2/7/2018.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private Context context;
    public List<Book> bookNames;
    private OnItemClickListenner onItemClickListenner;

    public BookAdapter(Context context, List<Book> bookNames) {
        this.context = context;
        this.bookNames = bookNames;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Book book =bookNames.get(position);

        holder.txtBookNames.setText(book.getName());
        holder.txtBookNames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListenner.onItemClicked(holder.itemView, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookNames.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtBookNames;

        public ViewHolder(View itemView) {
            super(itemView);

            txtBookNames = itemView.findViewById(R.id.txt_book);
        }
    }

    public OnItemClickListenner getOnItemClickListenner() {
        return onItemClickListenner;
    }

    public void setOnItemClickListenner(OnItemClickListenner onItemClickListenner) {
        this.onItemClickListenner = onItemClickListenner;
    }
}
