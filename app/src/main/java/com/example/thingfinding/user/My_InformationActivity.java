package com.example.thingfinding.user;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.thingfinding.Fragment.Fragment_Me;
import com.example.thingfinding.R;
import com.example.thingfinding.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class My_InformationActivity extends AppCompatActivity  implements OnClickListener{

    private SQLiteHelper dbhelper;
    Intent intent;
    String username;
    private TextView nameText;
    private TextView phoneText;
    private TextView idText;
    private TextView emailText;
    private TextView addressText;
    private LinearLayout nameLayout;
    private LinearLayout phoneLayout;
    private LinearLayout idLayout;
    private LinearLayout emailLayout;
    private LinearLayout addressLayout;
    String pass;
    String name;
    String phone;
    String id;
    String email;
    String address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__information);
        intent = getIntent();
        username=intent.getStringExtra("username");
        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
        initView();
        passData();
        initEvent();


    }

    private void initView() {
        nameText=(TextView)findViewById(R.id.nameending);
        phoneText=(TextView)findViewById(R.id.phoneending);
        idText=(TextView)findViewById(R.id.idending);
        emailText=(TextView)findViewById(R.id.nameending);
        addressText=(TextView)findViewById(R.id.nameending);
        nameLayout=(LinearLayout) findViewById(R.id.nameLayout);
        phoneLayout=(LinearLayout) findViewById(R.id.phoneLayout);
        idLayout=(LinearLayout) findViewById(R.id.idLayout);
        emailLayout=(LinearLayout) findViewById(R.id.emailLayout);
        addressLayout=(LinearLayout) findViewById(R.id.addressLayout);
    }
    private void initEvent() {
        nameLayout.setOnClickListener(this);
        phoneLayout.setOnClickListener(this);
        idLayout.setOnClickListener(this);
        emailLayout.setOnClickListener(this);
        addressLayout.setOnClickListener(this);

    }

    public void exit(View view) {
        finish();
    }
    public void passData() {
        this.dbhelper = SQLiteHelper.getInstance(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();

        Cursor cur = db.query("Users",new String[]{"password","name","phone","id","email","address"},
                "username=?", new String[]{username},
                null, null, null);
        while(cur.moveToNext()) {
             pass= cur.getString(cur.getColumnIndex("password"));
             name= cur.getString(cur.getColumnIndex("name"));
             phone = cur.getString(cur.getColumnIndex("phone"));
             id= cur.getString(cur.getColumnIndex("id"));
             email = cur.getString(cur.getColumnIndex("email"));
             address= cur.getString(cur.getColumnIndex("address"));
             }
        nameText.setText(pass);
        phoneText.setText(pass);
        idText.setText(pass);
        emailText.setText(pass);
        addressText.setText(pass);


    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.nameLayout:
                Toast.makeText(this, "被点击", Toast.LENGTH_SHORT).show();
                break;
            case R.id.phoneLayout:
                Toast.makeText(this, "被点击", Toast.LENGTH_SHORT).show();
                break;
            case R.id.idLayout:
                Toast.makeText(this, "被点击", Toast.LENGTH_SHORT).show();
                break;
            case R.id.emailLayout:
                Toast.makeText(this, "被点击", Toast.LENGTH_SHORT).show();
                break;
            case R.id.addressLayout:
                Toast.makeText(this, "被点击", Toast.LENGTH_SHORT).show();
                break;
        }

    }

}
