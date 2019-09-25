package com.example.thingfinding.user;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thingfinding.Adapter.addressbookAdapter;
import com.example.thingfinding.Adapter.transactionAdpter;
import com.example.thingfinding.Bean.CommonResultBean;
import com.example.thingfinding.DialogUtil;
import com.example.thingfinding.R;
import com.example.thingfinding.Util.BaseCallback;
import com.example.thingfinding.Util.OkHttpHelp;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

public class My_TransactionActivity extends AppCompatActivity implements
        View.OnClickListener,OnCheckedChangeListener {

    private TextView exitText;
    private ListView transactionLv;
    private RadioGroup radioGroup;
    private RadioButton whole; // 表示第一个RadioButton 组件
    private OkHttpHelp mokhttp;

    private transactionAdpter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__transaction);
        initView();
      //  MyBaseAdapter myBaseAdapter = new MyBaseAdapter();
       // transactionLv.setAdapter(myBaseAdapter);
        initEvent();
    }

    private void initView() {
        exitText = (TextView) findViewById(R.id.exitText);
        transactionLv= (ListView) findViewById(R.id.transactionLv);
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        whole=(RadioButton)findViewById(R.id.wholeBtn);
        adapter=new transactionAdpter(null,this);
        transactionLv.setAdapter(adapter);


    }

    private void initEvent() {
        exitText.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);

    }

    public void exit() {
        finish();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.exitText:
                exit();
                break;

        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.wholeBtn: // 全部

                break;
            case R.id.to_be_shippedBtn: // 待发货

                break;
            case R.id.goods_to_be_receivedBtn: // 待收货

                break;
            case R.id.completedBtn: // 已完成

                break;


        }
    }


    class MyBaseAdapter extends BaseAdapter {
        public int getCount() {
            return 1;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int postion) {
            return postion;
        }

        public View getView(int postion, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.transaction_list, parent, false);
                holder = new ViewHolder();
                holder.timeText = (TextView) convertView.findViewById(R.id.timeText);
                holder.stateText = (TextView) convertView.findViewById(R.id.stateText);
                holder.commoditynameText = (TextView) convertView.findViewById(R.id.commoditynameText);
                holder.priceText = (TextView) convertView.findViewById(R.id.priceText);
                holder.image = (ImageView) convertView.findViewById(R.id.image);
                holder.stateBtn = (Button) convertView.findViewById(R.id.stateBtn);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.timeText.setText("2019-9-21");
            holder.stateText.setText("已完成");
            holder.commoditynameText.setText("狗狗");
            holder.priceText.setText("共1件商品 合计：￥500");
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog, null);
            holder.image.setImageBitmap(bitmap);
            holder.stateBtn.setText("删除订单");
            return convertView;
        }

    }

    class ViewHolder {

        TextView timeText;
        TextView stateText;
        TextView commoditynameText;
        TextView priceText;
        ImageView image;
        Button stateBtn;

    }

    public void query_data(String select){
        String url=OkHttpHelp.BASE_URL+"";
        Map<String,String> map=new HashMap<>();

        try {
            mokhttp=OkHttpHelp.getinstance();
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
                    // DialogUtil.showDialog(RegisterActivity.this,"服务器响应成功",true);
                    String data=(String) response.getData();
                    Log.i("--**-**--","响应成功");
                    Log.i("--**",data);


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

}
