package com.example.thingfinding.news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.thingfinding.R;

import org.json.JSONArray;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private ListView listView;
    private List<Map<String,String>> list;
    private Map<String,String> map;
    private JSONArray arr;
    private int[] icon={R.drawable.touxiang,R.drawable.tx_two};
    private String comment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rg);
        listView=(ListView)findViewById(R.id.lv_new);
        radioGroup.setOnCheckedChangeListener(this);
        try{
            InputStream is=this.getResources().openRawResource(R.raw.news);
            List<NewsInfo> infos=Service.getInfoJson(is);

            list=new ArrayList<Map<String,String>>();
            for(NewsInfo itemInfos:infos){
                map=new HashMap<String,String>();
                map.put("name",itemInfos.getName());
                map.put("time",itemInfos.getTime());
                map.put("news",itemInfos.getNews());
                list.add(map);
            }
        }catch (Exception e){
            e.printStackTrace();
             Toast.makeText(this,"信息加载失败",Toast.LENGTH_SHORT).show();
        }
        MyBaseAdapter myBaseAdapter=new MyBaseAdapter();
        listView.setAdapter(myBaseAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Map<String,String> map=list.get(arg2);
                comment=map.get("news");
                communication();
            }
        });



    }

    public void homepageclick1(){

    }

    //我的界面
    public void wodeclick() {

    }

    public void communication() {

    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //根据不同ID 弹出不同的
        switch (group.getCheckedRadioButtonId()){
            case  R.id.radioButton_shouye:
                homepageclick1();
                break;
            case  R.id.radioButton_xiaoxi:
                break;
            case  R.id.radioButton_wode:
                wodeclick();
                break;
        }
    }


    class MyBaseAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        public int getCount(){
            return list.size();
        }
        public Object getItem(int position){
            return null;
        }
        public long getItemId(int postion){
            return postion;
        }
        public View getView(int postion,View convertView,ViewGroup parent){
            ViewHolder holder;
            if(convertView==null) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_new, parent, false);
                holder = new ViewHolder();
                holder.image=(com.example.thingfinding.MyImageView)convertView.findViewById(R.id.image);
                holder.name_text = (TextView) convertView.findViewById(R.id.name_text);
                holder.time_text = (TextView) convertView.findViewById(R.id.time_text);
                holder.news_text = (TextView) convertView.findViewById(R.id.news_text);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder)convertView.getTag();
            }
            Map<String,String> map=list.get(postion);
            holder.name_text.setText(map.get("name"));
            holder.time_text.setText(map.get("time"));
            holder.news_text.setText(map.get("news"));
            holder.image.setBackgroundResource(icon[postion]);
            return convertView;
        }

    }

    class ViewHolder{
        com.example.thingfinding.MyImageView image;
        TextView name_text;
        TextView time_text;
        TextView news_text;

    }



}
