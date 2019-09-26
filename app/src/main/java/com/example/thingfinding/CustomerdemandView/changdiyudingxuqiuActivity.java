package com.example.thingfinding.CustomerdemandView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.thingfinding.Bean.CommonCustomerneedBean;
import com.example.thingfinding.Bean.CommonResultBean;
import com.example.thingfinding.DialogUtil;
import com.example.thingfinding.R;
import com.example.thingfinding.Util.BaseCallback;
import com.example.thingfinding.Util.BaseUrl;
import com.example.thingfinding.Util.OkHttpHelp;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class changdiyudingxuqiuActivity extends AppCompatActivity {
    private List<CommonCustomerneedBean> changdiinfo;
    //private CommonCustomerneedBean weixiuinfo;
    private ListView lvweixiu;
    private LinearLayout loading;
    private CommonCustomerneedBean wxbean;
    private JSONObject jsonObject;
    private OkHttpHelp mokhttp;
    private String xuqiuming;
    private JSONArray jsonArray;
    private JSONObject[] jsonObjects;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cahngdiyudingxuqiu);
//        init();
//        Intent intent=getIntent();
//        xuqiuming=intent.getStringExtra("xuqiuming");
//        System.out.println(xuqiuming+"255565554");
//        String url=BaseUrl.BASE_URL +"/select?";
//        System.out.println("路径名"+url);
//        Intent setdata=new Intent(this,weixiufuwuxuqiuActivity.class);
//        mokhttp=OkHttpHelp.getinstance();
//        Map<String,String> map=new HashMap<>();
//        map.put("demandType",xuqiuming);
////        map.put("nowPage","1");
////        map.put("pageSize","100");
//        map.put("nowPage","1");
//        map.put("pageSize","100");
//        try {
//            Log.i("/**-*8-/","5656516");
//            mokhttp.post(url, map, new BaseCallback<CommonCustomerneedBean>() {
//                @Override
//                public void onRequestBefore() {
//                    Log.i("/**-*8-/","prepare");
//                }
//
//                @Override
//                public void onFailure(Request request, Exception e) {
//                    e.printStackTrace();
//                    Log.i("/**-*8-/","bad");
//                }
//
//                @Override
//                public void onSuccess(CommonResultBean response) {
//                    Gson gson=new Gson();
//                    String result=gson.toJson(response.getData());
//                    System.out.println(result);
//                    System.out.println(response.getData());
//                    jsonArray = JSONArray.parseArray(result);//遍历方式1
//                    int size = jsonArray.size();
//                    jsonObjects=new JSONObject[size];
//                    for (int i = 0; i < size; i++) {
//                        jsonObject = jsonArray.getJSONObject(i);
//                        System.out.println("用户名:  " + jsonObject.getString("customerUserName"));
//                        System.out.println("时间:  " + jsonObject.getString("endTime"));
////                        String timestr=jsonObject.getString("beginTime").substring(0,10);
////                        System.out.println(timestr);
//                        jsonObjects[i]=jsonObject;
//                    }
//                    for(int i=0;i<jsonObjects.length;i++){
//                        System.out.println(jsonObjects[i].getString("sentense"));
//                    }
//                    System.out.println("8888888888");
//                    changdiinfo=(List<CommonCustomerneedBean>)JSONArray.parseArray(result,CommonCustomerneedBean.class);
//                    for (int i=0;i<changdiinfo.size();i++){
//                        System.out.println(changdiinfo.get(i).getCustomerUserName());
//                        System.out.println(changdiinfo.get(i).getSentense());
//                        System.out.println(changdiinfo.get(i).getCustomerAddress());
//                    }
//                    loading.setVisibility(View.INVISIBLE);
//                    lvweixiu.setAdapter(new weixiuAdapter(changdiinfo));
//                    lvweixiu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            setdata.putExtra("customerName",changdiinfo.get(position).getCustomerUserName());
//                            setdata.putExtra("beginDate",changdiinfo.get(position).getBeginTime());
//                            setdata.putExtra("endDate",changdiinfo.get(position).getEndTime());
//                            setdata.putExtra("customerAddress",changdiinfo.get(position).getCustomerAddress());
//                            setdata.putExtra("message",changdiinfo.get(position).getSentense());
//                            startActivity(setdata);
//                        }
//                    });
//                }
//
//                @Override
//                public void onError(Response response, int errorCode, Exception e) {
//                    e.printStackTrace();
//                }
//            });
//
//
//            //jsonArray =mokhttp.getJsonArray();
//            /*int size = jsonArray.size();
//            jsonObjects=new JSONObject[size];
//            for (int i = 0; i < size; i++) {
//                jsonObject = jsonArray.getJSONObject(i);
//                System.out.println("用户名:  " + jsonObject.getString("customerUserName"));
//                jsonObjects[i]=jsonObject;
//            }*/
//        }catch (Exception e){
//            DialogUtil.showDialog(this,"服务器响应异常",false);
//            e.printStackTrace();
//        }
//    }}
    }

}
