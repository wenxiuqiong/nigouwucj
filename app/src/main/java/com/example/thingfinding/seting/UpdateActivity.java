package com.example.thingfinding.seting;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.thingfinding.R;

public class UpdateActivity extends AppCompatActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        btn=(Button)findViewById(R.id.update);

    }

    public void exit(View v) {
        finish();
    }

    public void update(View view){
       AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("版本升级").
                setIcon(R.mipmap.ic_launcher). // 设置提示框的图标
                setMessage("当前已是最新版本").// 设置要显示的信息
                setPositiveButton("确定",null) // 设置确定按钮
        .setNegativeButton("取消", null);//设置取消按钮,null是什么都不做，并关闭对话框
        AlertDialog alertDialog = builder.create();
    }
}
