package com.etalenter.etalenterpro;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRatingViewAdapter extends RecyclerView.Adapter<MyRatingViewAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    public static ArrayList<EditModel> editModelArrayList;


    public MyRatingViewAdapter(Context ctx, ArrayList<EditModel> editModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.editModelArrayList = editModelArrayList;
    }

    @Override
    public MyRatingViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rating_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyRatingViewAdapter.MyViewHolder holder, final int position) {


        holder.item.setText(editModelArrayList.get(position).getItem());
        holder.item_score.setText(editModelArrayList.get(position).getItemScore());
//        holder.ratingbar.setRating(Float.parseFloat(editModelArrayList.get(position).getEditTextValue()));
        Log.d("print","yes");

    }

    @Override
    public int getItemCount() {
        return editModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        protected final TextView item;
        protected final EditText item_score;
        protected final RatingBar ratingbar;

        public MyViewHolder(View itemView) {
            super(itemView);
            ratingbar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            item = (TextView) itemView.findViewById(R.id.item);
            item_score= (EditText) itemView.findViewById(R.id.item_score);
            ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    item_score.setText(String.valueOf(rating) );
                }
            });

            item_score.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    editModelArrayList.get(getAdapterPosition()).setItemScore(item_score.getText().toString());
                    ratingbar.setRating(Float.parseFloat(item_score.getText().toString()));
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        }

    }
}