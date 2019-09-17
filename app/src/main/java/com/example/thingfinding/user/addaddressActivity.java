package com.example.thingfinding.user;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thingfinding.R;
import com.example.thingfinding.SQLiteHelper;

public class addaddressActivity extends AppCompatActivity implements View.OnClickListener {

    private Button addbtn;
    private EditText nameText;
    private EditText phoneText;
    private EditText addressText;
    private SQLiteHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaddress);
        initView();
        initEvent();
    }

    private void initView() {
        addbtn=(Button)findViewById(R.id.addBtn) ;
        nameText=(EditText) findViewById(R.id.nameText) ;
        phoneText=(EditText)findViewById(R.id.phoneText) ;
        addressText=(EditText)findViewById(R.id.addressText) ;
    }

    private void initEvent() {
        addbtn.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addBtn:
                addaddress();
                break;
        }
    }

    public  void  addaddress(){
        //String name=nameText.getText().toString().trim();
        //String phone=phoneText.getText().toString().trim();
        //String address=addressText.getText().toString().trim();
        this.dbhelper = SQLiteHelper.getInstance(this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", nameText.getText().toString().trim());
        cv.put("phone", phoneText.getText().toString().trim());
        cv.put("address", addressText.getText().toString().trim());
        db.insert("AddressBook", null, cv);
        Toast.makeText(this, "添加成功！", Toast.LENGTH_SHORT).show();
        db.close();
        Intent intent = new Intent();
        setResult(1, intent);
        finish();

    }

}
