package com.example.thingfinding.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thingfinding.Adapter.transactionAdpter;
import com.example.thingfinding.Bean.CommonResultBean;
import com.example.thingfinding.DialogUtil;
import com.example.thingfinding.R;
import com.example.thingfinding.Util.BaseCallback;
import com.example.thingfinding.Util.BaseUrl;
import com.example.thingfinding.Util.OkHttpHelp;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_Transaction_four extends Fragment {
    private ListView listView;
    private OkHttpHelp mokhttp;
    private static String getStr;
    private transactionAdpter adapter;


    public Fragment_Transaction_four() {
    }

    public static Fragment newInstance(String str) {
        Fragment_Transaction_four fragment = new Fragment_Transaction_four();
        getStr=str;
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        listView = (ListView) view.findViewById(R.id.transactionLv);
        Toast.makeText(getActivity(),getStr,Toast.LENGTH_LONG).show();
       // adapter=new transactionAdpter(query_data(),getActivity());
       // listView.setAdapter(adapter);
        return view;
    }

    public List query_data(){
        String url = BaseUrl.BASE_URL + "";
        Map<String,String> map=new HashMap<>();
        try {
            mokhttp= OkHttpHelp.getinstance();
            mokhttp.post(url, map, new BaseCallback<CommonResultBean>() {
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
                    String data=(String) response.getData();
                    Log.i("--**-**--","响应成功");
                    Log.i("--**",data);
                    System.out.print("666");

                }
            });
        }catch (Exception e){
            DialogUtil.showDialog(getActivity(),"服务器响应异常",false);
            e.printStackTrace();
        }
        return null;
    }


}