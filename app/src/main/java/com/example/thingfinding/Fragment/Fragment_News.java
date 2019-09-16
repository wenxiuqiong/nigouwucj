package com.example.thingfinding.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thingfinding.R;
import com.example.thingfinding.news.CommunicationActivity;
import com.example.thingfinding.news.NewsActivity;
import com.example.thingfinding.news.NewsInfo;
import com.example.thingfinding.news.Service;

import org.json.JSONArray;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_News extends Fragment {

    private ListView listView;
    private List<Map<String,String>> list;
    private Map<String,String> map;
    private JSONArray arr;
    private int[] icon={R.drawable.touxiang,R.drawable.tx_two};
    private String comment;
    public Fragment_News() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__news, container, false);
        listView=(ListView)view.findViewById(R.id.lv_new);
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
            Toast.makeText(getActivity(),"信息加载失败",Toast.LENGTH_SHORT).show();
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
        return view;
    }

    public void communication() {
        Intent intent = new Intent(getActivity(), CommunicationActivity.class);
        intent.putExtra("comment", comment);
        startActivity(intent);
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
                convertView = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.list_new, parent, false);
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
