package com.example.thingfinding.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.thingfinding.R;


public class My_DetailsActivity extends AppCompatActivity implements View.OnClickListener{

    EditText details;
    TextView exitText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__details);
        Intent intent=getIntent();
        initView();
        initEvent();
        details.setText(intent.getStringExtra("details"));
    }
    private void initView() {
        details=(EditText)findViewById(R.id.details);
        exitText=(TextView) findViewById(R.id.exitText);
    }
    private void initEvent() {
        exitText.setOnClickListener(this);
    }
    public void exit() {
        finish();
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exitText:
                exit();
                break;
        }
    }
}
