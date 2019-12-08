package com.example.thingfinding.CustomerdemandView;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thingfinding.Bean.CommonResultBean;
import com.example.thingfinding.R;
import com.example.thingfinding.Util.BaseCallback;
import com.example.thingfinding.Util.BaseUrl;
import com.example.thingfinding.Util.OkHttpHelp;
import com.example.thingfinding.user.My_DemandActivity;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

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
                AlertDialog dialog=new AlertDialog.Builder(weixiufuwuxuqiuActivity.this)
                        .setMessage("是否接单")
                        .setTitle("接单")
                        .setIcon(R.mipmap.logo)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setData.putExtra("demandType",demandType);
                                startActivity(setData);
                                acceptance();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();
                dialog.show();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void acceptance(){
        String url=BaseUrl.BASE_URL +"";
        System.out.println("路径名"+url);
        mokhttp=OkHttpHelp.getinstance();
        Map<String,String> map=new HashMap<>();
        map.put("userName",et_customerName.getText().toString().trim());
        map.put("customerAdress",et_customerAdress.getText().toString().trim());
        map.put("beginDate",et_beginDate.getText().toString().trim());
        map.put("endDate",et_endDate.getText().toString().trim());
        map.put("message",et_message.getText().toString().trim());
        map.put("demandType",demandType);
        try {
            mokhttp.post(url, map, new BaseCallback<CommonResultBean>() {
                @Override
                public void onRequestBefore() {

                }

                @Override
                public void onFailure(Request request, Exception e) {

                }
                @Override
                public void onSuccess(CommonResultBean response) {
                    Gson gson=new Gson();
                    String result=gson.toJson(response.getData());
                    String code = response.getCode();
                    Toast.makeText(weixiufuwuxuqiuActivity.this,result,Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onError(Response response, int errorCode, Exception e) {

                }
            });
        }catch (Exception e){
           e.printStackTrace();
        }
    }
}
