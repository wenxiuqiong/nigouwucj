package com.example.thingfinding.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.thingfinding.R;


public class My_DemandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__demand);
    }
    public void exit(View v) {
        finish();
    }
}
