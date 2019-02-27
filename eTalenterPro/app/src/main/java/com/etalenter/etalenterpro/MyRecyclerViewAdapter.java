package com.etalenter.etalenterpro;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private String[] option_count;
    private int[] option_colour;
    private String[] option_name;
    private int[] option_image;

    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, String[] option_count,int[] option_colour,String[] option_name,int[] option_image) {
        this.mInflater = LayoutInflater.from(context);
        this.option_count = option_count;
        this.option_colour = option_colour;
        this.option_name = option_name;
        this.option_image = option_image;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.option_count.setText(option_count[position]);
        holder.option_count.setBackgroundResource(option_colour[position]);
        holder.option_name.setText(option_name[position]);
        holder.option_image.setImageResource(option_image[position]);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return option_count.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView option_count;
        ImageView option_image;
        TextView option_name;


        ViewHolder(View itemView) {
            super(itemView);
            option_count = itemView.findViewById(R.id.option_count);
            option_image = itemView.findViewById(R.id.option_images);
            option_name = itemView.findViewById(R.id.option_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return option_count[id];
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
