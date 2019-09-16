package com.example.thingfinding.tenderview;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.thingfinding.R;

public class carweixiuActivity extends AppCompatActivity {
    private Button btntijiao;
    private Button btngoueuche;
    private EditText etfuwuming;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carweixiu);
        btntijiao=(Button)findViewById(R.id.btntijiao);
        btngoueuche=(Button)findViewById(R.id.btngouwuche);
        etfuwuming=(EditText)findViewById(R.id.etcarweixiufuwuming);
        Intent intent=getIntent();
        String fuwuming=intent.getStringExtra("fuwuming");
        etfuwuming.setText(fuwuming);
        btngoueuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showgouwucheDialog();
            }
        });
        btntijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showtijiaoDialog();
            }
        });
    }
    public void showgouwucheDialog(){
        AlertDialog dialog;
        dialog=new AlertDialog.Builder(this).setTitle("购物车")
                .setMessage("加入购物车")
                .setIcon(R.drawable.ic_launcher)
                .setPositiveButton("确定",null)
                .setNegativeButton("取消",null)
                .create();
        dialog.show();

    }
    public void showtijiaoDialog(){
        AlertDialog dialog;
        dialog=new AlertDialog.Builder(this).setTitle("提交订单")
                .setMessage("是否提交订单")
                .setIcon(R.drawable.ic_launcher)
                .setPositiveButton("确定",null)
                .setNegativeButton("取消",null)
                .create();
        dialog.show();
    }
}
