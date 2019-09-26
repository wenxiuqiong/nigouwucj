package com.example.thingfinding.CustomerdemandView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.thingfinding.R;
import com.example.thingfinding.Util.OkHttpHelp;
import com.example.thingfinding.user.My_DemandActivity;

public class weixiufuwuxuqiuActivity extends AppCompatActivity {
    private OkHttpHelp mokhttp;
    private TextView et_customerName;
    private TextView et_beginDate;
    private TextView et_endDate;
    private TextView et_customerAdress;
    private TextView et_message;
    private String customerName;
    private String beginDate;
    private String endDate;
    private String customerAdress;
    private String messgse;
    private String demandType;
    private Button btnjiedan;
    private Button btnCancel;
    private  Intent setData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixiufuwuxuqiu);
        setData=new Intent(this,My_DemandActivity.class);
        init();
        getData();
        Listen();
    }
    public void init(){
        et_customerName=findViewById(R.id.etcustomerName);
        et_customerAdress=findViewById(R.id.etcustomerAdress);
        et_beginDate=findViewById(R.id.etbeginDate);
        et_endDate=findViewById(R.id.etendDate);
        et_message=findViewById(R.id.etmessge);
        btnjiedan=findViewById(R.id.btnjiedan);
        btnCancel=findViewById(R.id.btn_cancel);
    }
    public void getData(){
        Intent getData=getIntent();
        customerName=getData.getStringExtra("customerName");
        beginDate=getData.getStringExtra("beginDate");
        endDate=getData.getStringExtra("endDate");
        customerAdress=getData.getStringExtra("customerAddress");
        messgse=getData.getStringExtra("message");
        demandType=getData.getStringExtra("demandType");
        et_customerName.setText(customerName);
        et_customerAdress.setText(customerAdress);
        et_beginDate.setText(beginDate.substring(0,10));
        et_endDate.setText(endDate.substring(0,10));
        et_message.setText(messgse);
    }
    public void Listen(){
        btnjiedan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData.putExtra("demandType",demandType);
                startActivity(setData);
            }
        });
    }
}
