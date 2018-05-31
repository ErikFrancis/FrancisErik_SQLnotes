package com.example.francise4856.mycontactapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Get the intent that initiated this activity
        Log.d("MyContactApp", "SearchActivity: Get the intent that initiated this activity");
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //Capture the layout's textView and set the string as the text

        Log.d("MyContactApp", "SearchActivity: Capture the layout's textView and set the string as the text");
        TextView textView = findViewById(R.id.textView2);
        Log.d("MyContactApp", "SearchActivity: 1");
        textView.setText("test");
        Log.d("MyContactApp", "SearchActivity: 2");

    }


}