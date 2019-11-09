package com.example.thingfinding.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.thingfinding.BaseActivity;
import com.example.thingfinding.R;


public class My_DetailsActivity extends BaseActivity{

    EditText details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__details);
        Intent intent=getIntent();
        initView();
        details.setText(intent.getStringExtra("details"));
    }
    private void initView() {
        initNavBar(true,"");
        details=(EditText)findViewById(R.id.details);
    }

}
