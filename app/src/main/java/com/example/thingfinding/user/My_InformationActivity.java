package com.example.thingfinding.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thingfinding.R;
import com.example.thingfinding.SQLiteHelper;

import java.util.ArrayList;

public class My_InformationActivity extends AppCompatActivity implements OnClickListener {

    private SQLiteHelper dbhelper;
    private ListView listView;
    private String[] heading={"姓名","电话号码","身份证号码","邮箱","地址",};
    private ArrayList<String> list = new ArrayList<String>();
    Intent intent;
    String username;
    private TextView exitText;
    String mark;
   /* private TextView nameText;
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
    String address;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__information);
        intent = getIntent();
        username=intent.getStringExtra("username");
        initView();
        MyBaseAdapter myBaseAdapter=new MyBaseAdapter();
        listView.setAdapter(myBaseAdapter);
        for(int i=0;i<heading.length;i++){
            list.add(heading[i]);
        }

        initEvent();


    }

    private void initView() {
        listView=(ListView)findViewById(R.id.informationLv);
        exitText=(TextView) findViewById(R.id.exitText);
       /* nameText=(TextView)findViewById(R.id.nameending);
        phoneText=(TextView)findViewById(R.id.phoneending);
        idText=(TextView)findViewById(R.id.idending);
        emailText=(TextView)findViewById(R.id.nameending);
        addressText=(TextView)findViewById(R.id.nameending);
        nameLayout=(LinearLayout) findViewById(R.id.nameLayout);
        phoneLayout=(LinearLayout) findViewById(R.id.phoneLayout);
        idLayout=(LinearLayout) findViewById(R.id.idLayout);
        emailLayout=(LinearLayout) findViewById(R.id.emailLayout);
        addressLayout=(LinearLayout) findViewById(R.id.addressLayout);*/
    }
    private void initEvent() {
       /* nameLayout.setOnClickListener(this);
        phoneLayout.setOnClickListener(this);
        idLayout.setOnClickListener(this);
        emailLayout.setOnClickListener(this);
        addressLayout.setOnClickListener(this);*/
        exitText.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                if (list.get(arg2).equals("姓名")) {
                    mark = list.get(arg2);
                    Details();
                }
                if (list.get(arg2).equals("电话号码")) {
                    mark = list.get(arg2);
                    Details();
                }
                if (list.get(arg2).equals("身份证号码")) {
                    mark = list.get(arg2);
                    Details();
                }
                if (list.get(arg2).equals("邮箱")) {
                    mark = list.get(arg2);
                    Details();
                }
                if (list.get(arg2).equals("地址")) {
                    mark = list.get(arg2);
                    Details();
                }
            }
        });

    }

    public void exit() {
        finish();
    }
    public void Details(){
            Intent intent = new Intent(this, My_DetailsActivity.class);
            intent.putExtra("details", mark);
            startActivity(intent);
        }

    class MyBaseAdapter extends BaseAdapter {
        public int getCount(){
            return heading.length;
        }
        public Object getItem(int position){
            return heading[position];
        }
        public long getItemId(int postion){
            return postion;
        }
        public View getView(int postion, View convertView, ViewGroup parent){
            ViewHolder holder;
            if(convertView==null) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.save_list, parent, false);
                holder = new ViewHolder();
                holder.texthead = (TextView) convertView.findViewById(R.id.text_head);
                holder.textend = (TextView) convertView.findViewById(R.id.text_end);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder)convertView.getTag();
            }
            holder.texthead.setText(heading[postion]);
            return convertView;
        }

    }
    class ViewHolder{
        TextView texthead;
        TextView textend;

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.exitText:
                exit();
                break;

        }

    }

}
