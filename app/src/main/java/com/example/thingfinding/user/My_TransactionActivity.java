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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

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
        View.OnClickListener {

    private TextView exitText;
    private EditText et_ordernumber;
    private ImageView imagebtn;
    private ListView transactionLv;

    private OkHttpHelp mokhttp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__transaction);
        initView();
        MyBaseAdapter myBaseAdapter = new MyBaseAdapter();
        transactionLv.setAdapter(myBaseAdapter);
        initEvent();
    }

    private void initView() {
        exitText = (TextView) findViewById(R.id.exitText);
        et_ordernumber=(EditText)findViewById(R.id.et_ordernumber);
        imagebtn=(ImageView)findViewById(R.id.imagebtn);
        transactionLv= (ListView) findViewById(R.id.transactionLv);

    }

    private void initEvent() {
        exitText.setOnClickListener(this);
        imagebtn.setOnClickListener(this);
    }

    public void exit() {
        finish();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.exitText:
                exit();
                break;
            case R.id.imagebtn:
                exit();
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
                holder.customerText = (TextView) convertView.findViewById(R.id.customerText);
                holder.commoditynameText = (TextView) convertView.findViewById(R.id.commoditynameText);
                holder.specificationsText = (TextView) convertView.findViewById(R.id.specificationsText);
                holder.priceText = (TextView) convertView.findViewById(R.id.priceText);
                holder.totalText = (TextView) convertView.findViewById(R.id.totalText);
                holder.numberText = (TextView) convertView.findViewById(R.id.numberText);
                holder.image = (ImageView) convertView.findViewById(R.id.image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.customerText.setText("都不睡觉");
            holder.commoditynameText.setText("两个月大的纯种金毛");
            holder.specificationsText.setText("两个月大");
            holder.priceText .setText("￥600");
            holder.numberText.setText("1");
            holder.totalText.setText("600");
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog, null);
            holder.image.setImageBitmap(bitmap);
            return convertView;
        }

    }

    class ViewHolder {
        TextView customerText;
        TextView commoditynameText;
        TextView specificationsText;
        TextView priceText;
        TextView totalText;
        TextView numberText;
        ImageView image;

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
