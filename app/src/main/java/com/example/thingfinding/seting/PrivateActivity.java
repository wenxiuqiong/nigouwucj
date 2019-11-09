package com.example.thingfinding.seting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.thingfinding.BaseActivity;
import com.example.thingfinding.R;

public class PrivateActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private);
        initView();

    }
    private void initView() {
        initNavBar(true,"隐私协议");
    }

}
