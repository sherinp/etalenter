package com.etalenter.etalenterpro;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyScoreCardAdapter extends RecyclerView.Adapter<MyScoreCardAdapter.ViewHolder> {

    private String[] chess_number;
    private String compatetion_name;


    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;



    // data is passed into the constructor
    MyScoreCardAdapter(Context context, String[] option_count,String compatetion_name) {
        this.mInflater = LayoutInflater.from(context);
        this.chess_number = option_count;
        this.compatetion_name = compatetion_name;

    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.score_card, parent, false);

        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chess_number_tv.setText(chess_number[position]);


    }

    // total number of cells
    @Override
    public int getItemCount() {
        return chess_number.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView chess_number_tv;




        ViewHolder(View itemView) {
            super(itemView);
            chess_number_tv = itemView.findViewById(R.id.chess_number);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());

            ///Toast.makeText(itemView.getContext(), "Clicked Laugh Vote"+chess_number[getAdapterPosition()], Toast.LENGTH_SHORT).show();


            Intent intent = new Intent(itemView.getContext(),MyRatingActivity.class);


            intent.putExtra("chess_no", chess_number[getAdapterPosition()]);
            intent.putExtra("compatetion_name", compatetion_name);
            Log.e("chess_no",chess_number[getAdapterPosition()].toString());
            Log.e("compatetion_name",compatetion_name.toString());
            itemView.getContext().startActivity(intent);

        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return chess_number[id];
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
