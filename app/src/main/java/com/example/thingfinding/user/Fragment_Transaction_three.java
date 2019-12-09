package com.example.thingfinding.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.example.thingfinding.Adapter.transactionAdpter;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_Transaction_three extends Fragment {
    private ListView listView;
    private OkHttpHelp mokhttp;
    private static String getStr;
    private transactionAdpter adapter;
    private List<CommonCustomerneedBean> infolist=new ArrayList<CommonCustomerneedBean>();


    public Fragment_Transaction_three() {

    }

    public static Fragment newInstance(String str) {
        Fragment_Transaction fragment = new Fragment_Transaction();
        getStr=str;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //query_data();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        inflater=LayoutInflater();
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        listView = (ListView) view.findViewById(R.id.transactionLv);
        //Toast.makeText(getActivity(),getStr,Toast.LENGTH_LONG).show();
        query_data();


        return view;
    }

    public void query_data(){
        String url = BaseUrl.BASE_URL + "/customerDemand/select?";
        Map<String,String> map=new HashMap<>();
        map.put("businessUsername","密码：a12345678");
        map.put("nowPage","1");
        map.put("pageSize","100");
        map.put("status","完成订单");
        try {
            mokhttp= OkHttpHelp.getinstance();
            mokhttp.post(url, map, new BaseCallback<CommonCustomerneedBean>() {
                @Override
                public void onRequestBefore() {

                }

                @Override
                public void onFailure(Request request, Exception e) {
                    e.printStackTrace();
                }

                @Override
                public void onError(Response response, int errorCode, Exception e) {

                }

                public void onSuccess(CommonResultBean response) {
                    // DialogUtil.showDialog(RegisterActivity.this,"服务器响应成功",true);
//                    String data=(String) response.getData();
                    Gson gson=new Gson();
                    String result=gson.toJson(response.getData());
                    Log.i("--**--",result);
                    listView.setAdapter(new transactionAdpter( (List<CommonCustomerneedBean>) JSONArray.parseArray(result, CommonCustomerneedBean.class),getActivity()));
                    Log.i("--**-**--","响应成功");
                    System.out.print("666");

                }
            });
        }catch (Exception e){
            DialogUtil.showDialog(getActivity(),"服务器响应异常",false);
            e.printStackTrace();
        }

    }


}