package com.example.thingfinding.seting;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thingfinding.BaseActivity;
import com.example.thingfinding.R;
import com.example.thingfinding.user.My_DemandActivity;

public class UpdateActivity extends BaseActivity implements View.OnClickListener {

    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initView();
        initEvent();

    }

    private void initView() {
        initNavBar(true,"版本更新");
        btn=(Button)findViewById(R.id.update);
    }

    private void initEvent() {
        btn.setOnClickListener(this);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.update:
                update();
                break;
        }
    }
    public void update() {
        final android.app.AlertDialog.Builder normalDialog = new android.app.AlertDialog.Builder(this);
        normalDialog.setTitle("系统更新");
        normalDialog.setIcon(R.mipmap.ic_launcher); // 设置提示框的图标
        normalDialog.setMessage("是否更新本系统");
        normalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(UpdateActivity.this, "正在准备，请耐心等待", Toast.LENGTH_SHORT).show();

            }
        });
        normalDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        normalDialog.show();// 显示
    }
}
