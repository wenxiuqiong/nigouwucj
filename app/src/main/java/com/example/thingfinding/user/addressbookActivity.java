package com.example.thingfinding.user;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thingfinding.Adapter;
import com.example.thingfinding.Bean.addressItem;
import com.example.thingfinding.R;
import com.example.thingfinding.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class addressbookActivity extends AppCompatActivity implements View.OnClickListener{

    private Button addbtn;
    private ListView listView;
    private TextView exitText;
    private SQLiteHelper dbhelper;
    private List<addressItem> dataList;
    addressItem listdateils;//存储点击的启事的数据
    private Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addressbook);
        initView();
        initEvent();
    }
    private void initView() {
        addbtn = (Button) findViewById(R.id.releasebtn);
        listView = (ListView) findViewById(R.id.addLv);
        exitText=(TextView) findViewById(R.id.exitText);
        dataList = new ArrayList<addressItem>();
        listdateils=new addressItem();
        adapter=new Adapter(readImage(),this);
        listView.setAdapter(adapter);

    }
    private void initEvent() {
        addbtn.setOnClickListener(this);
        exitText.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listdateils=dataList.get(position);
                notfy();
            }
        });
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.releasebtn:
                addAddress();
                break;
            case R.id.exitText:
                exit();
                break;
        }
    }
    public void exit() {
        finish();
    }

    public void addAddress(){
        Intent intent=new Intent(this,addaddressActivity.class);
        startActivityForResult(intent,1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Fragment f = fragmentManager.findFragmentByTag(curFragmentTag);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==  1 && resultCode == 1) {
            notifyDataSetChanged();
        }
    }

    public void notifyDataSetChanged() {
        if (dataList.size() > 0) {
            dataList.removeAll(dataList);
            adapter=new Adapter(readImage(),this);
            listView.setAdapter(adapter);
        }
    }


    private List<addressItem> readImage() {
        this.dbhelper = SQLiteHelper.getInstance(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql = "SELECT name,phone,address FROM AddressBook";
       Cursor cur = db.rawQuery(sql, null);
        for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
            addressItem info = new addressItem();
            info.setName(cur.getString(0));
            info.setPhone(cur.getString(1));
            info.setAddress(cur.getString(2));
            dataList.add(info);
        }

        return dataList;
    }

    public void notfy(){
        Intent intent = new Intent();
        intent.putExtra("name",listdateils.getName());
        intent.putExtra("phone",listdateils.getPhone());
        intent.putExtra("address",listdateils.getAddress());
        setResult(1, intent);
        finish();
    }

}
