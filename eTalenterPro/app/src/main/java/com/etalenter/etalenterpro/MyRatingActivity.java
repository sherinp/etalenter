package com.etalenter.etalenterpro;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRatingActivity extends AppCompatActivity {

    private TextView chess_number_tv,item;
    RatingBar ratingbar;
    EditText total_score;
    Button detail,submit;
    String s;
    public ArrayList<EditModel> editModelArrayList;
    String chess_no,compatetion_name;
    double avg;


    String[] item_speach = {"Voice Clarity", "Timing", "Topic", "Introduction", "Content","Conclusion"};
    String[] item_song = {"Voice Clarity", "Voice Quality", "Timing", "Tone"};
    String[] item_quiz = {};
    String[] item_essay = {};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                chess_no= null;
            } else {
                chess_no= extras.getString("chess_no");
                compatetion_name= extras.getString("compatetion_name");
                Log.e("chess_no",chess_no.toString());
                Log.e("compatetion_name",compatetion_name.toString());
            }
        } else {
            chess_no= (String) savedInstanceState.getSerializable("chess no");
            compatetion_name= (String) savedInstanceState.getSerializable("compatetion_name");
            Log.e("chess_no",chess_no.toString());
            Log.e("compatetion_name",compatetion_name.toString());
        }

        chess_number_tv = findViewById(R.id.chess_number);
        item = findViewById(R.id.item);
         ratingbar = findViewById(R.id.ratingBar);
          total_score = findViewById(R.id.total_score);
          detail = findViewById(R.id.detail);
        submit = findViewById(R.id.submit);

//        chess_number_tv.setText(chess_no.toString());
        //editModelArrayList = populateList(item_speach);
        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                total_score.setText(String.valueOf(rating) );
            }
        });

       /* if(compatetion_name.equals("speach"))
        {
            editModelArrayList = populateList(item_speach);

        }else if(compatetion_name.equals("song"))
        {
            editModelArrayList = populateList(item_song);

        }else if(compatetion_name.equals("quiz"))
        {
            editModelArrayList = populateList(item_quiz);

        }else if(compatetion_name.equals("essay"))
        {
            editModelArrayList = populateList(item_essay);

        }*/

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent = new Intent(MyRatingActivity.this,MyDetailedRatingActivity.class);


                intent.putExtra("chess_no", "b1");
                intent.putExtra("compatetion_name", "speach");
                intent.putExtra("rating", String.valueOf(ratingbar.getRating()));
                Log.e("no diff in rating",String.valueOf(ratingbar.getRating()));


                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


        EditModel em = new EditModel();
        //
        if(em.getItemScore().length() != 0){


        for (int j = 0; j < em.getItemScore().length(); j++){

            item.setText(item.getText() + " " + em.getItemScore().length());
            Log.e("output",item.getText().toString());

            s=item.getText().toString().trim() ;

        }

        //String s = "1,2";
        String[] parts = s.split(" ");    //s is string containing integers
        Float [] a;
        double total =0.0;
        a = new Float[parts.length];
        for(int i=0; i<parts.length; i++){
            a[i]= Float.parseFloat(parts[i]);
            System.out.println(a[i]);
            Log.e("lob", String.valueOf(a[i]));
            total += a[i];
            Log.e("tot", String.valueOf(total));
        }
        avg= total/parts.length;
        Log.e("avg", String.valueOf(avg));

        ratingbar.setRating(Float.parseFloat(String.valueOf(avg)));
        total_score.setText(String.valueOf(ratingbar.getRating()));


        }
    }
    /*private ArrayList<EditModel> populateList(String[] s){

        ArrayList<EditModel> list = new ArrayList<>();

        for(int i = 0; i < s.length; i++){
            EditModel editModel = new EditModel();
            editModel.setItem(String.valueOf(s[i]));
            editModel.setItemScore(String.valueOf(i));
            list.add(editModel);
        }

        return list;
    }*/
}
