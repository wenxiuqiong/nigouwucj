package com.example.thingfinding.CustomerdemandView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class gouwudingzhiActivity extends AppCompatActivity {
    private List<CommonCustomerneedBean> gouwudingzhiinfo;
    private ListView lvgouwu;
    private LinearLayout loading;
    private CommonCustomerneedBean bean;
    private JSONObject jsonObject;
    private OkHttpHelp mokhttp;
    private String xuqiuming;
    private JSONArray jsonArray;
    private JSONObject[] jsonObjects;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gouwudingzhi);
        Intent intent=getIntent();
        xuqiuming=intent.getStringExtra("xuqiuming");
        init();
        getData();
    }
    private void init(){
        loading=(LinearLayout)findViewById(R.id.loading);
        lvgouwu=(ListView)findViewById(R.id.lvweixiufuwu);

    }
    private void getData(){
        String url=BaseUrl.BASE_URL +"/select?";
        Intent setdata=new Intent(this,gouwudingzhixuqiuActivity.class);
        mokhttp=OkHttpHelp.getinstance();
        Map<String,String> map=new HashMap<>();
        map.put("demandType",xuqiuming);
        try {
            mokhttp.post(url, map, new BaseCallback<CommonResultBean>() {
                @Override
                public void onRequestBefore() {
                }

                @Override
                public void onFailure(Request request, Exception e) {

                }
                @Override
                public void onSuccess(CommonResultBean response) {
                    DialogUtil.showDialog(gouwudingzhiActivity.this,"响应成功",true);
                    loading.setVisibility(View.INVISIBLE);
                    //gouwudingzhiinfo=(List<CommonCustomerneedBean>) response.getData();
                    lvgouwu.setAdapter(new weixiuAdapter( gouwudingzhiinfo));
                    lvgouwu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            setdata.putExtra("customerName", gouwudingzhiinfo.get(position).getCustomerUserName());
                            setdata.putExtra("beginDate", gouwudingzhiinfo.get(position).getBeginTime());
                            setdata.putExtra("endDate", gouwudingzhiinfo.get(position).getEndTime());
                            setdata.putExtra("customerAddress", gouwudingzhiinfo.get(position).getCustomerAddress());
                            setdata.putExtra("message", gouwudingzhiinfo.get(position).getSentense());
                            startActivity(setdata);
                        }
                    });
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
    }
    private class weixiuAdapter extends BaseAdapter {
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
                view = View.inflate(gouwudingzhiActivity.this, R.layout.list_item3, null);
                mholder = new weixiuAdapter.HolderView();
                mholder.img = (ImageView) view.findViewById(R.id.item_image);
                mholder.tvcustomername = (TextView) view.findViewById(R.id.tvname);
                mholder.tvfuwuming = (TextView) view.findViewById(R.id.tvfuwuming);
                view.setTag(mholder);
            }else {
                view=convertView;
                mholder=(weixiuAdapter.HolderView) view.getTag();
            }
            mholder.tvcustomername.setText(wxlist.get(position).getCustomerUserName());
            mholder.tvfuwuming.setText(wxlist.get(position).getSentense());
//            Picasso.with(weixiufuwuActivity.this)
//                    .load(wxlist.get(position).getIco())
//                    .into(mholder.img);
            mholder.img.setImageResource(R.drawable.clothes);
            return view;
        }
        class HolderView{
            public TextView tvcustomername;
            public TextView tvfuwuming;
            public ImageView img;
        }
    }

}
