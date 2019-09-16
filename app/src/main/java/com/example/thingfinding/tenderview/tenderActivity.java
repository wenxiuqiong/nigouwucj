package com.example.thingfinding.tenderview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.example.thingfinding.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class tenderActivity extends AppCompatActivity {

    private List<Map<String,Object>> lists;
    private SimpleAdapter changdiadapter;
    private ListView listView;
    private String[] changdinames=new String[]{"结婚party","生日party","圣诞party","泳池party","音乐party"};
    private  int[] imageIds=new int[]{R.drawable.car,R.drawable.pet,
            R.drawable.digitalproduct,
            R.drawable.furnitures,R.drawable.diet};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tender);
        listView = (ListView)findViewById(R.id.lvpartytype);
        lists=new ArrayList<>();
        for (int i=0;i<changdinames.length;i++){
            Map<String,Object> map =new HashMap<>();
            map.put("typenames",changdinames[i]);
            map.put("images",imageIds[i]);
            lists.add(map);
        }
        changdiadapter=new SimpleAdapter(this,lists,R.layout.list_item,new String[]{"typenames","images"},new int[]{R.id.typename,R.id.typeimage});
        listView.setAdapter(changdiadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.i("cahngdi",changdinames[position]);
                String name=changdinames[position];
            }
        });
    }
}
