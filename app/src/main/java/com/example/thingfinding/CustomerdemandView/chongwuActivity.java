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

import com.example.thingfinding.Bean.CommonCustomerneedBean;
import com.example.thingfinding.Bean.CommonResultBean;
import com.example.thingfinding.DialogUtil;
import com.example.thingfinding.R;
import com.example.thingfinding.Util.BaseCallback;
import com.example.thingfinding.Util.OkHttpHelp;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class chongwuActivity extends AppCompatActivity {
    private List<CommonCustomerneedBean> weixiuinfo;
    private ListView lvweixiu;
    private LinearLayout loading;
    private CommonCustomerneedBean wxbean;
    private OkHttpHelp mokhttp;
    private String xuqiuming;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chongwu);
    }
    private void init(){
        loading=(LinearLayout)findViewById(R.id.loading);
        lvweixiu=(ListView)findViewById(R.id.lvweixiufuwu);

    }
    private void getData(){
        String url=OkHttpHelp.BASE_URL+"";
        Intent setdata=new Intent(this,chongwuxuqiuActivity.class);
        mokhttp=OkHttpHelp.getinstance();
        Map<String,String> map=new HashMap<>();
        map.put("user",xuqiuming);
        try {
            mokhttp.post(url, map, new BaseCallback<CommonResultBean<CommonCustomerneedBean>>() {
                @Override
                public void onRequestBefore() {

                }

                @Override
                public void onFailure(Request request, Exception e) {

                }
                @Override
                public void onSuccess(CommonResultBean<CommonCustomerneedBean> response) {
                    DialogUtil.showDialog(chongwuActivity.this,"响应成功",false);
                    loading.setVisibility(View.INVISIBLE);
                    weixiuinfo=(List<CommonCustomerneedBean>) response.getData();
                    lvweixiu.setAdapter(new weixiuAdapter(weixiuinfo));
                    lvweixiu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            setdata.putExtra("customerName",weixiuinfo.get(position).getCustomerUserName());
                            setdata.putExtra("beginDate",weixiuinfo.get(position).getBeginTime());
                            setdata.putExtra("endDate",weixiuinfo.get(position).getEndTime());
                            setdata.putExtra("customerAddress",weixiuinfo.get(position).getCustomerAddress());
                            setdata.putExtra("message",weixiuinfo.get(position).getSentense());
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
                view = View.inflate(chongwuActivity.this, R.layout.list_item3, null);
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
