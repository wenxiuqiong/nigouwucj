package com.example.thingfinding.seting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.thingfinding.BaseActivity;
import com.example.thingfinding.R;

public class HelpActivity extends BaseActivity implements View.OnClickListener {

    private TextView exitText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        initView();
    }

    private void initView() {
        initNavBar(true,"帮助中心");
    }



    public void onClick(View v){
        switch(v.getId()){
            case R.id.exitText:
                exit();
                break;
        }
    }
    public void exit() {
        finish();
    }
}
