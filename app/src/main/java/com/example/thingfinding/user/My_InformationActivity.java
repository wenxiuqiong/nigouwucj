package com.example.thingfinding.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thingfinding.Bean.CommonResultBean;
import com.example.thingfinding.Bean.informationinfo;
import com.example.thingfinding.Adapter.informationAdapter;
import com.example.thingfinding.DialogUtil;
import com.example.thingfinding.R;
import com.example.thingfinding.SQLiteHelper;
import com.example.thingfinding.Util.BaseCallback;
import com.example.thingfinding.Util.BaseUrl;
import com.example.thingfinding.Util.OkHttpHelp;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class My_InformationActivity extends AppCompatActivity implements OnClickListener {

    private SQLiteHelper dbhelper;
    private ListView listView;
    private String[] heading={"姓名","电话号码","身份证号码","邮箱","店名","店地址","店铺介绍"};
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayList<String> informationList=new ArrayList<>();
    Intent intent;
    String username;
    private TextView exitText;
    String mark;
    private OkHttpHelp mokhttp;
    informationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__information);
        intent = getIntent();
        username=intent.getStringExtra("username");
        initView();
        informationAdapter adapter=new informationAdapter(list,this);
        listView.setAdapter(adapter);
//        MyBaseAdapter myBaseAdapter=new MyBaseAdapter();
//        listView.setAdapter(myBaseAdapter);
        for(int i=0;i<heading.length;i++){
            list.add(heading[i]);
        }
        getInformation();

        initEvent();


    }

    private void initView() {
        listView=(ListView)findViewById(R.id.informationLv);
        exitText=(TextView) findViewById(R.id.exitText);

    }
    private void initEvent() {

        exitText.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                if (list.get(arg2).equals("姓名")) {
                    mark = list.get(arg2);

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
                if (list.get(arg2).equals("店名")) {
                    mark = list.get(arg2);
                    Details();
                }
                if (list.get(arg2).equals("店地址")) {
                    mark = list.get(arg2);
                    Details();
                }
                if (list.get(arg2).equals("店铺介绍")) {
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

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.exitText:
                exit();
                break;

        }

    }

    public ArrayList getInformation(){
        String url = BaseUrl.BASE_URL + "";
        Map<String,String> map=new HashMap<>();
        try {
            mokhttp=OkHttpHelp.getinstance();
            mokhttp.post(url, map, new BaseCallback<CommonResultBean>() {
                @Override
                public void onRequestBefore() {

                }

                @Override
                public void onFailure(Request request, Exception e) {
                    e.printStackTrace();
                }

                @Override
                public void onSuccess(CommonResultBean response) {
                    DialogUtil.showDialog(My_InformationActivity.this,"服务器响应成功",true);
                    informationinfo data=(informationinfo) response.getData();
                    Log.i("--**-**--","响应成功");
                   // Log.i("--**",data);
                    informationList.add(data.getName());
                    informationList.add(data.getPhone());
                    informationList.add(data.getIdCard());
                    informationList.add(data.getEmail());
                    informationList.add(data.getStoreName());
                    informationList.add(data.getStoreaddress());
                    informationList.add(data.getStoreintroduction());

                }
                @Override
                public void onError(Response response, int errorCode, Exception e) {
                    e.printStackTrace();
                }
            });
        }catch (Exception e){
            DialogUtil.showDialog(this,"服务器响应异常",false);
            e.printStackTrace();
        }
        return informationList;
    }



}
