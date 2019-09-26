package com.example.thingfinding.Fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

/*import com.example.thingfinding.CustomerdemandView.changdiyudingActivity;
import com.example.thingfinding.CustomerdemandView.chongwuActivity;
import com.example.thingfinding.CustomerdemandView.gouwudingzhiActivity;*/
import com.example.thingfinding.CustomerdemandView.changdiyudingActivity;
import com.example.thingfinding.CustomerdemandView.chongwuActivity;
import com.example.thingfinding.CustomerdemandView.gongyijiajuActivity;
import com.example.thingfinding.CustomerdemandView.weixiufuwuActivity;
import com.example.thingfinding.R;
import com.example.thingfinding.SQLiteHelper;
import com.example.thingfinding.tenderview.carweixiutypeActivity;
import com.example.thingfinding.tenderview.furniturestypeActivity;
import com.example.thingfinding.tenderview.partytypeActivity;
import com.example.thingfinding.tenderview.pettypeActivity;
import com.example.thingfinding.tenderview.tenderActivity;
import com.example.thingfinding.user.loginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Fragment_HomePage extends Fragment {

    private List<Map<String,Object>> lists;
    private SimpleAdapter homeadapter;
    private ListView listView;
    private Fragment_HomePage context;
    private String[] typenames=new String[]{"场地预定","宠物需求","维修服务","购物订制","工艺家具","衣着","数码产品","学习","其他"};
    private  int[] imageIds=new int[]{R.drawable.venuerentals,R.drawable.pet,R.drawable.car,R.drawable.clothes,R.drawable.shafa,R.drawable.clothes,R.drawable.digitalproduct,R.drawable.study,R.drawable.weixin};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__home_page, container, false);
        listView = (ListView) view.findViewById(R.id.lvhome);
        lists=new ArrayList<>();
        for (int i=0;i<typenames.length;i++){
            Map<String,Object> map =new HashMap<>();
            map.put("typenames",typenames[i]);
            map.put("images",imageIds[i]);
            lists.add(map);
        }
        homeadapter=new SimpleAdapter(getActivity().getApplicationContext(),lists,R.layout.list_item,new String[]{"typenames","images"},new int[]{R.id.typename,R.id.typeimage});
        listView.setAdapter(homeadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String name=typenames[position];
                Log.i("test++",typenames[position]);
                if (name.equals("场地预定")){
                    changdixuqiu("场地");
                }
               if(name.equals("宠物需求")){
                    petxuqiu("宠物");
                }
                if(name.equals("维修服务")){
                    weixiuxuqiu("维修");
                }
                if(name.equals("购物订制")){
                    gouwuyuding("购物订制");
                }
                if(name.equals("工艺家具")){
                    gongyijiaju("工艺家具");
                }
            }
        });
        return view;
    }
    public void changdixuqiu(String name){
        Intent intent=new Intent(getActivity(),changdiyudingActivity.class);
        intent.putExtra("xuqiuming",name);
        startActivity(intent);
    }
    public void petxuqiu(String name){
        Intent intent=new Intent(getActivity(),chongwuActivity.class);
        intent.putExtra("xuqiuming",name);
        startActivity(intent);
    }
    public void weixiuxuqiu(String name){
        Intent intent=new Intent(getActivity(),weixiufuwuActivity.class);
        intent.putExtra("xuqiuming",name);
        startActivity(intent);
    }
    public void gouwuyuding(String name){
        Intent intent=new Intent(getActivity(),furniturestypeActivity.class);
        intent.putExtra("xuqiuming",name);
        startActivity(intent);
    }
    public void gongyijiaju(String name){
        Intent intent=new Intent(getActivity(),gongyijiajuActivity.class);
        intent.putExtra("xuqiuming",name);
        startActivity(intent);
    }
}
