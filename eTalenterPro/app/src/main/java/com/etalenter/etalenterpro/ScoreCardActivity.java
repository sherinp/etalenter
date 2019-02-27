package com.etalenter.etalenterpro;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ScoreCardActivity extends Activity implements RatingBar.OnRatingBarChangeListener {

    RatingBar ratingbar;
    Button button;
    EditText total_score;
    Spinner spinner_list;



    String[] speach = {"S13", "S29", "S37", "S43", "S52", "S65", "S37", "S58"};
    String[] songs = {"S13", "S29", "S37", "S43", "S52", "S65", "S37", "S58"};
    String[] quiz = {"Q13", "Q29", "Q37", "Q43", "Q52", "Q65", "Q37", "Q58"};
    String[] essay = {"E13", "E29", "E37", "E43", "E52", "E65", "E37", "E58"};

    String[] compatetions = {"speach", "songs", "quiz", "essay"};

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard);
        spinner_list  = findViewById(R.id.spinner_list);
        spinner_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),compatetions[position] , Toast.LENGTH_LONG).show();
                if(compatetions[position].equals("speach")){
                    adapter = new MyScoreCardAdapter(view.getContext(),speach,compatetions[position]);
                    recyclerView.setAdapter(adapter);
                }else if(compatetions[position].equals("song")){
                    adapter = new MyScoreCardAdapter(view.getContext(),songs,compatetions[position]);
                    recyclerView.setAdapter(adapter);
                }else if(compatetions[position].equals("quiz")){
                    adapter = new MyScoreCardAdapter(view.getContext(),quiz,compatetions[position]);
                    recyclerView.setAdapter(adapter);
                }else if(compatetions[position].equals("essay")){
                    adapter = new MyScoreCardAdapter(view.getContext(),essay,compatetions[position]);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item,compatetions);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner_list.setAdapter(spinnerArrayAdapter);



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //adapter.setClickListener(this);



    }





    public void onRatingChanged(RatingBar ratingBar, float rating,
                                boolean fromUser) {
       // String rating = String.valueOf(ratingbar.getRating());

        ///Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();
       // Toast.makeText(ScoreCardActivity.this, "New Rating: " + rating,Toast.LENGTH_SHORT).show();
        total_score.setText(String.valueOf(rating) );
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        Toast.makeText(ScoreCardActivity.this, "New Rating: " + hasCapture,Toast.LENGTH_SHORT).show();

    }
}
