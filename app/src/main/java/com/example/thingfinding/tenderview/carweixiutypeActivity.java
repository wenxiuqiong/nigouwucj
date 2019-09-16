package com.example.thingfinding.tenderview;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thingfinding.R;

import java.util.ArrayList;

public class carweixiutypeActivity extends AppCompatActivity {
    private ListView mListView;
    private ArrayList<String> list = new ArrayList<String>();
    //需要适配的数据
    private String[] names = {"涂鸦","轮胎刮破","补漆","汽车油表不准",
            "车灯维修","发动机故障","刹车故障",};
    //图片集合
    private int[] icons = {R.drawable.carweixiu,R.drawable.carweixiu3, R.drawable.carweixiu4,
            R.drawable.carweixiu,R.drawable.carweixiu1,R.drawable.carweixiu4,R.drawable.carweixiu};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carweixiutype);
        mListView = (ListView) findViewById(R.id.lv2);
        //创建一个Adapter的实例
        MyBaseAdapter mAdapter = new MyBaseAdapter();
        //设置Adapter
        mListView.setAdapter(mAdapter);
        for (int i = 0; i < names.length; i++) {
            list.add(names[i]);
        }
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (list.get(i).equals("涂鸦")){
                    setData("涂鸦");
                }if (list.get(i).equals("轮胎刮破")){
                    setData("轮胎刮破");
                }if (list.get(i).equals("补漆")){
                    setData("补漆");
                }if (list.get(i).equals("汽车油表不准")){
                    setData("汽车油表不准");
                }if (list.get(i).equals("车灯维修")){
                    setData("车灯维修");
                }if (list.get(i).equals("发动机故障")){
                    setData("发动机故障");
                }
                if (list.get(i).equals("刹车故障")){
                    setData("刹车故障");
                }
            }
        });
    }
    public void setData(String partyname){
        Intent intent=new Intent(this,carweixiuActivity.class);
        intent.putExtra("fuwuming",partyname);
        startActivity(intent);
    }
    class MyBaseAdapter extends BaseAdapter {
        //得到item的总数
        @Override
        public int getCount() {
            //返回ListView Item条目的总数
            return names.length;
        }
        //得到Item代表的对象
        @Override
        public Object getItem(int position) {
            //返回ListView Item条目代表的对象
            return names[position];
        }
        //得到Item的id
        @Override
        public long getItemId(int position) {
            //返回ListView Item的id
            return position;
        }
        //得到Item的View视图
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
          ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).
                        inflate(R.layout.list_item2,parent,false);
                holder = new ViewHolder();
                holder.mTextView = (TextView)convertView.findViewById(R.id. item_tv);
                holder.imageView=(ImageView) convertView.findViewById(R.id.item_image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.mTextView.setText(names[position]);
            holder.imageView.setBackgroundResource(icons[position]);
            return convertView;
        }
        class ViewHolder {
            TextView mTextView;
            ImageView imageView;
        }
    }

}
