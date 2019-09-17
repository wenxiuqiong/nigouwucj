package com.example.thingfinding.user;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.example.thingfinding.R;

public class My_TransactionActivity extends AppCompatActivity implements
        View.OnClickListener,OnCheckedChangeListener {

    private TextView exitText;
    private ListView transactionLv;
    private RadioGroup radioGroup;
    private RadioButton home; // 表示第一个RadioButton 组件

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
        transactionLv= (ListView) findViewById(R.id.transactionLv);
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        home = (RadioButton) findViewById(R.id.radioButton_whole);
        home.setChecked(true);
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
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radioButton_home_page: // 首页
                //显示第一个Fragment并关闭动画效果
                //viewPager.setCurrentItem(0,false);
                break;
            case R.id.radioButton_news: // 团购
                //viewPager.setCurrentItem(1,false);
                break;

            case R.id.radioButton_me: // 发现
               // viewPager.setCurrentItem(3,false);
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
                holder.shopnameText = (TextView) convertView.findViewById(R.id.shopnameText);
                holder.commoditynameText = (TextView) convertView.findViewById(R.id.commoditynameText);
                holder.specificationsText = (TextView) convertView.findViewById(R.id.specificationsText);
                holder.priceText = (TextView) convertView.findViewById(R.id.priceText);
                holder.totalText = (TextView) convertView.findViewById(R.id.totalText);
                holder.image = (ImageView) convertView.findViewById(R.id.image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.shopnameText.setText("广油店");
            holder.commoditynameText.setText("两个月大的纯种金毛");
            holder.specificationsText.setText("两个月大");
            holder.priceText .setText("￥600");
            holder.totalText.setText("总共1件商品  总价：600");
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog, null);
            holder.image.setImageBitmap(bitmap);
            return convertView;
        }

    }

    class ViewHolder {
        TextView shopnameText;
        TextView commoditynameText;
        TextView specificationsText;
        TextView priceText;
        TextView totalText;
        ImageView image;

    }



}