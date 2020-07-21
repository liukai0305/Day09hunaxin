package com.example.day09hunaxin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day09hunaxin.R;

import java.util.ArrayList;

public class RcyAdapter extends RecyclerView.Adapter {
    private ArrayList<String> list;
    private Context context;

    public RcyAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_rcy, null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String s = list.get(position);
        ViewHolder holder1= (ViewHolder) holder;
        holder1.name.setText(s);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickLis.click(position);
            }
        });
    }
    private OnItemClickLis onItemClickLis;

    public void setOnItemClickLis(OnItemClickLis onItemClickLis) {
        this.onItemClickLis = onItemClickLis;
    }

    public interface OnItemClickLis{
        void click(int position);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView name;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.name = (TextView) rootView.findViewById(R.id.name);
        }

    }
}
