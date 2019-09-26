package com.example.thingfinding.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thingfinding.Adapter.orderauditAdapter;
import com.example.thingfinding.R;

public class OrderauditActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView exitText;
    private ListView orderauditLv;
    private orderauditAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderaudit);
        initView();
        initEvent();
    }

    private void initView() {
        exitText = (TextView) findViewById(R.id.exitText);
        orderauditLv=(ListView)findViewById(R.id.orderauditLv);
        adapter=new orderauditAdapter(null,this);
        orderauditLv.setAdapter(adapter);
    }

    private void initEvent() {
        exitText.setOnClickListener(this);
        orderauditLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ord_pro();
            }
        });
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exitText:
                exit();
                break;
        }
    }

    public void exit() {
        finish();
    }

    public  void ord_pro(){
        Intent intent=new Intent(this,Order_productionActivity.class);
        startActivity(intent);

    }

}
