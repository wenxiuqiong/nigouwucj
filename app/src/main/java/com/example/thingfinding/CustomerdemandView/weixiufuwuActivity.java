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

import com.example.thingfinding.Bean.CommonResultBean;
import com.example.thingfinding.Bean.CommonCustomerneedBean;
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

public class weixiufuwuActivity extends AppCompatActivity {
    private List<CommonCustomerneedBean> weixiuinfo;
    private ListView lvweixiu;
    private LinearLayout loading;
    private CommonCustomerneedBean wxbean;
    private OkHttpHelp mokhttp;
    private String xuqiuming;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixiufuwu);
        init();
        Intent intent=getIntent();
        xuqiuming=intent.getStringExtra("xuqiuming");
        String url=BaseUrl.BASE_URL +"/select?";
        Intent setdata=new Intent(this,weixiufuwuxuqiuActivity.class);
        mokhttp=OkHttpHelp.getinstance();
        Map<String,String> map=new HashMap<>();
        map.put("demandType",xuqiuming);
        map.put("nowPage","1");
        map.put("pageSize","100");
        try {
            Log.i("/**-*8-/","5656516");
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
                    Log.i("**---**","响应成功");
                   weixiuinfo=(List<CommonCustomerneedBean>) response.getData();
                    Log.i("**---**", String.valueOf(weixiuinfo.size()));
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
//        loading.setVisibility(View.INVISIBLE);
//        lvweixiu.setAdapter(new weixiuAdapter(weixiuinfo));

    }
    private void init(){
        loading=(LinearLayout)findViewById(R.id.loading);
        lvweixiu=(ListView)findViewById(R.id.lvweixiufuwu);
    }
    class weixiuAdapter extends BaseAdapter {
        private Context mcontext;
        private HolderView mholder;
        private View view;
        private List<CommonCustomerneedBean> wxlist;
        public weixiuAdapter(List<CommonCustomerneedBean> wxlist){
            this.wxlist=wxlist;
        }
        @Override
        public int getCount() {
            return wxlist.size();
        }

        @Override
        public Object getItem(int position) {
            return wxlist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                view = View.inflate(weixiufuwuActivity.this, R.layout.list_item3, null);
                mholder = new HolderView();
                mholder.img = (ImageView) view.findViewById(R.id.item_image);
                mholder.tvcustomername = (TextView) view.findViewById(R.id.tvname);
                mholder.tvfuwuming = (TextView) view.findViewById(R.id.tvfuwuming);
                view.setTag(mholder);
            }else {
                view=convertView;
                mholder=(HolderView) view.getTag();
            }
            mholder.tvcustomername.setText(wxlist.get(position).getCustomerUserName());
            mholder.tvfuwuming.setText(wxlist.get(position).getSentense());
            mholder.img.setImageResource(R.drawable.carweixiu);
//            Picasso.with(weixiufuwuActivity.this)
//                    .load(wxlist.get(position).getIco())
//                    .into(mholder.img);
            return view;
        }
        class HolderView{
            public TextView tvcustomername;
            public TextView tvfuwuming;
            public ImageView img;
        }
    }

}
