package com.example.thingfinding.seting;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.thingfinding.R;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private TextView exitText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initView();
        initEvent();

    }

    private void initView() {
        btn=(Button)findViewById(R.id.update);
        exitText = (TextView) findViewById(R.id.exitText);
    }

    private void initEvent() {
        btn.setOnClickListener(this);
        exitText.setOnClickListener(this);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.exitText:
                exit();
                break;
            case R.id.update:
                update();
                break;
        }
    }
    public void exit() {
        finish();
    }

    public void update(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("版本升级").
                setIcon(R.mipmap.ic_launcher). // 设置提示框的图标
                setMessage("当前已是最新版本").// 设置要显示的信息
                setPositiveButton("确定",null) // 设置确定按钮
                .setNegativeButton("取消", null);//设置取消按钮,null是什么都不做，并关闭对话框
        AlertDialog alertDialog = builder.create();
    }
}
