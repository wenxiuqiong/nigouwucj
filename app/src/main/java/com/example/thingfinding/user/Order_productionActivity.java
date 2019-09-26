package com.example.thingfinding.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.thingfinding.R;


public class Order_productionActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText manufactornameText;
    private EditText manufactorphoneText;
    private EditText manufactoraddText;
    private TextView exitText;
    private TextView addressbookText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_production);
        intnView();
        intnEvent();

    }

    private void intnView(){
        manufactornameText=(EditText) findViewById(R.id.manufactornameText);
        manufactorphoneText=(EditText)findViewById(R.id.manufactorphoneText);
        manufactoraddText=(EditText)findViewById(R.id.manufactoraddText);
        exitText=(TextView)findViewById(R.id.exitText);
        addressbookText=(TextView)findViewById(R.id.addressbookText);
    }

    private void intnEvent(){
        exitText.setOnClickListener(this);
        addressbookText.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.exitText:
                exit();
                break;
            case R.id.addressbookText:
                addressbook();
                break;

        }
    }
    public void exit() {
        finish();
    }
    public void addressbook() {
        Intent intent=new Intent(this,addressbookActivity.class);
        startActivityForResult(intent,1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Fragment f = fragmentManager.findFragmentByTag(curFragmentTag);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            manufactornameText.setText(data.getStringExtra("name"));
            manufactorphoneText.setText(data.getStringExtra("phone"));
            manufactoraddText.setText(data.getStringExtra("address"));

        }
    }

}
