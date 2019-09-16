package com.example.thingfinding.tenderview;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thingfinding.R;

public class partyActivity extends AppCompatActivity {

    private Button btntijiao;
    private Button btngoueuche;
    private EditText etfuwuming;
    private EditText  etyuanjia;
    private EditText etcuxiaojia;
    private EditText etchangdifei;
    private EditText etyudingfei;
//    private EditText
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party);
        btntijiao=(Button)findViewById(R.id.btntijiao);
        btngoueuche=(Button)findViewById(R.id.btngouwuche);
        etfuwuming=(EditText)findViewById(R.id.etchangdifuwu);
        etyuanjia=(EditText)findViewById(R.id.etyuanjia);
        etcuxiaojia=(EditText)findViewById(R.id.etcuxiaojia);
        etchangdifei=(EditText)findViewById(R.id.etchangdifei);
        etyudingfei=(EditText)findViewById(R.id.etyudingfei);
        Intent intent=getIntent();
        String fuwuming=intent.getStringExtra("fuwuming");
        etfuwuming.setText(fuwuming);
        etyuanjia.setText("2000");
        etcuxiaojia.setText("1500");
        etchangdifei.setText("100");
        etyudingfei.setText("50");
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
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(partyActivity.this,"加入购物车成功",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消",null)
                .create();
        dialog.show();

    }
    public void showtijiaoDialog(){
        AlertDialog dialog;
        dialog=new AlertDialog.Builder(this).setTitle("提交订单")
                .setMessage("是否提交订单")
                .setIcon(R.drawable.ic_launcher)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(partyActivity.this,"提交订单成功",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消",null)
                .create();
        dialog.show();
    }
    public void toastshow(){
        Toast.makeText(this,"加入购物车成功",Toast.LENGTH_SHORT).show();
    }
}
