package com.example.thingfinding.CustomerdemandView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.thingfinding.Bean.CommonCustomerneedBean;
import com.example.thingfinding.R;
import com.example.thingfinding.Util.OkHttpHelp;

import java.util.List;

public class gouwudingzhixuqiuActivity extends AppCompatActivity {
    private EditText et_customerName;
    private EditText et_beginDate;
    private EditText et_endDate;
    private EditText et_customerAdress;
    private EditText et_message;
    private String customerName;
    private String beginDate;
    private String endDate;
    private String customerAdress;
    private String messgse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gouwudingzhixuqiu);
    }
    public void init(){
        et_customerName=findViewById(R.id.etcustomerName);
        et_customerAdress=findViewById(R.id.etcustomerAdress);
        et_beginDate=findViewById(R.id.etbeginDate);
        et_endDate=findViewById(R.id.etendDate);
        et_message=findViewById(R.id.etmessge);
    }
    public void getData(){
        Intent getData=getIntent();
        customerName=getData.getStringExtra("customerName");
        beginDate=getData.getStringExtra("beginDate");
        endDate=getData.getStringExtra("endDate");
        customerAdress=getData.getStringExtra("customerAdsress");
        messgse=getData.getStringExtra("message");
        et_customerName.setText(customerName);
        et_customerAdress.setText(customerAdress);
        et_beginDate.setText(beginDate);
        et_endDate.setText(endDate);
        et_message.setText(messgse);
    }
}
