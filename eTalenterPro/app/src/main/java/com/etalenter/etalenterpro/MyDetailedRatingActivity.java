package com.etalenter.etalenterpro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MyDetailedRatingActivity extends AppCompatActivity {

    TextView chess_number_tv;
    RatingBar ratingbar;
    EditText total_score;
    Button button_save;
    RecyclerView recyclerView;
    private MyRatingViewAdapter RatingViewAdapter;
    String s;
    public ArrayList<EditModel> editModelArrayList;
    String chess_no,compatetion_name ="speach",rating = "0.0" ;

    String[] item_speach = {"Voice Clarity", "Timing", "Topic", "Introduction", "Content","Conclusion"};
    String[] item_song = {"Voice Clarity", "Voice Quality", "Timing", "Tone"};
    String[] item_quiz = {};
    String[] item_essay = {};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_rating);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                chess_no= null;
            } else {
                chess_no= extras.getString("chess_no");
                compatetion_name= extras.getString("compatetion_name");
                rating= extras.getString("rating");



                Log.e("rating",String.valueOf(rating));
                Log.e("chess_no",chess_no.toString());
                Log.e("compatetion_name",compatetion_name.toString());
            }
        } else {
            chess_no= (String) savedInstanceState.getSerializable("chess no");
            compatetion_name= (String) savedInstanceState.getSerializable("compatetion_name");
            rating= (String) savedInstanceState.getSerializable("rating");


            Log.e("rating",String.valueOf(rating));
            Log.e("chess_no",chess_no.toString());
            Log.e("compatetion_name",compatetion_name.toString());
        }






        chess_number_tv = (TextView) findViewById(R.id.chess_no);
        chess_number_tv.setText(chess_no);
        button_save = findViewById(R.id.button_save);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        if(compatetion_name.equals("speach"))
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

        }
        RatingViewAdapter = new MyRatingViewAdapter(this,editModelArrayList);
        recyclerView.setAdapter(RatingViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyDetailedRatingActivity.this,MyRatingActivity.class);
                startActivity(intent);
            }
        });





    }
    private ArrayList<EditModel> populateList(String[] s){

        ArrayList<EditModel> list = new ArrayList<>();

        for(int i = 0; i < s.length; i++){
            EditModel editModel = new EditModel();
            editModel.setItem(String.valueOf(s[i]));
            editModel.setItemScore(String.valueOf(rating));
            list.add(editModel);
        }

        return list;
    }
}
