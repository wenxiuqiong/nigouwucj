package com.example.thingfinding.user;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thingfinding.BaseActivity;
import com.example.thingfinding.R;
import com.example.thingfinding.seting.HelpActivity;
import com.example.thingfinding.seting.PrivateActivity;
import com.example.thingfinding.seting.SuggestionActivity;
import com.example.thingfinding.seting.UpdateActivity;

import java.util.ArrayList;


public class My_SetupActivity extends BaseActivity implements View.OnClickListener {

    private Button btn;
    private ListView listView;
    private String[] heading = {"修改密码", "意见反馈", "黑名单管理", "帮助中心", "隐私协议", "版本更新"};
    private String[] ending = {">", ">", ">", ">", ">", ">"};
    private ArrayList<String> list = new ArrayList<String>();
    private String username;
    private Intent intent;
    private String FILE = "saveUserNamePwd";//用于保存SharedPreferences的文件
    private String Mark = "mark";//用于保存SharedPreferences的文件
    private SharedPreferences sp = null;//声明一个SharedPreferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__setup);
        initView();

        MyBaseAdapter myBaseAdapter = new MyBaseAdapter();
        listView.setAdapter(myBaseAdapter);
        for (int i = 0; i < heading.length; i++) {
            list.add(heading[i]);
        }
        initEvent();
    }
    private void initView() {
        initNavBar(true,"设置");
        intent=getIntent();
        username=intent.getStringExtra("username");
        btn = (Button) findViewById(R.id.exit);
        listView = (ListView) findViewById(R.id.set_lv);

    }
    private void initEvent() {
        btn.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (list.get(position).equals("修改密码")) {
                    changepassword();
                }
                if (list.get(position).equals("意见反馈")) {
                    suggesttion();
                }
                if (list.get(position).equals("黑名单管理")) {
                    Toast.makeText(My_SetupActivity.this, "建设中。。。", Toast.LENGTH_SHORT).show();
                }
                if (list.get(position).equals("帮助中心")) {
                    help();
                }
                if (list.get(position).equals("隐私协议")) {
                    pri();
                }
                if (list.get(position).equals("版本更新")) {
                    update();
                }
            }
        });
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.exit:
                showNormalDialog();
                break;

        }
    }
    public void changepassword() {
        Intent intent = new Intent(this, ChangePasswordActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }
    public void update(){
        Intent intent=new Intent(this,UpdateActivity.class);
        startActivity(intent);
    }

    public void help(){
        Intent intent=new Intent(this,HelpActivity.class);
        startActivity(intent);
    }

    public void suggesttion(){
        Intent intent=new Intent(this,SuggestionActivity.class);
        startActivity(intent);
    }

    public void pri(){
        Intent intent=new Intent(this,PrivateActivity.class);
        startActivity(intent);
    }

    public void isLogin() {
        SharedPreferences sp1 = getSharedPreferences(Mark, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp1.edit();
        edit.putBoolean("isLogin", false);//存入boolean类型的登录状态
        edit.commit();
    }

    private void showNormalDialog() {
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(this);
        normalDialog.setTitle("退出登录");
        normalDialog.setMessage("确定要退出该用户吗？");
        normalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(My_SetupActivity.this, "退出登录成功", Toast.LENGTH_SHORT).show();
                isLogin();
                clearLoginStatus();//清除登录状态和登录时的用户名
                Intent intent = new Intent();
                intent.putExtra("result", "登 录");
                setResult(2, intent);
                finish();
            }
        });
        normalDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        normalDialog.show();// 显示
    }

    class MyBaseAdapter extends BaseAdapter {
        public int getCount() {
            return heading.length;
        }

        public Object getItem(int position) {
            return heading[position];
        }

        public long getItemId(int postion) {
            return postion;
        }

        public View getView(int postion, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.setup_list, parent, false);
                holder = new ViewHolder();
                holder.texthead = (TextView) convertView.findViewById(R.id.text_head);
                holder.textend = (TextView) convertView.findViewById(R.id.text_end);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.texthead.setText(heading[postion]);
            holder.textend.setText(ending[postion]);
            return convertView;
        }

    }

    class ViewHolder {
        TextView texthead;
        TextView textend;

    }

    private void clearLoginStatus() {
        sp = getSharedPreferences(FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();//获取编辑器
        editor.putBoolean("isLogin", false);
        editor.commit();//提交修改
    }

}