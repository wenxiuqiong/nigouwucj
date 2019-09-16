package com.example.thingfinding.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.thingfinding.R;


public class My_DetailsActivity extends AppCompatActivity {

    EditText details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__details);
        Intent intent=getIntent();
        details=(EditText)findViewById(R.id.details);
        details.setText(intent.getStringExtra("details"));
    }
    public void exit(View v) {
        finish();
    }
}
