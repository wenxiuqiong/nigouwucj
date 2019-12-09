package com.example.thingfinding.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thingfinding.Bean.CommonCustomerneedBean;
import com.example.thingfinding.Bean.addressItem;
import com.example.thingfinding.R;

import java.util.ArrayList;
import java.util.List;

public class transactionAdpter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<CommonCustomerneedBean> list=new ArrayList<CommonCustomerneedBean>();
    Context ct;

    public transactionAdpter(List<CommonCustomerneedBean> mlist, Context context) {
        this.list = mlist;
        ct=context;
        inflater = LayoutInflater.from(context);
    }


    public int getCount() {
        return list.size();
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
            convertView = inflater.inflate(R.layout.transaction_list, null);
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

        holder.timeText.setText(list.get(postion).getCustomerUserName());
        holder.stateText.setText(list.get(postion).getDemandType());
        holder.commoditynameText.setText(list.get(postion).getSentense());
        holder.priceText.setText("共1件商品 合计：￥500");
        holder.image.setImageResource(R.drawable.dog);
        holder.stateBtn.setText("删除订单");
        holder.stateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(ct,"请输入邮箱",Toast.LENGTH_SHORT).show();

            }

            });
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
