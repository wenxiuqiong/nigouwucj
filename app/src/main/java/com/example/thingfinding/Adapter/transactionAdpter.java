package com.example.thingfinding.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thingfinding.Bean.addressItem;
import com.example.thingfinding.R;

import java.util.List;

public class transactionAdpter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<addressItem> list;

    public transactionAdpter(List<addressItem> list, Context context) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


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
            convertView = inflater.inflate(R.layout.transaction_list, null);
            holder = new ViewHolder();
            holder.order_number = (TextView) convertView.findViewById(R.id.order_number);
            holder.customerText = (TextView) convertView.findViewById(R.id.customerText);
            holder.commoditynameText = (TextView) convertView.findViewById(R.id.commoditynameText);
            holder.specificationsText = (TextView) convertView.findViewById(R.id.specificationsText);
            holder.priceText = (TextView) convertView.findViewById(R.id.priceText);
            holder.totalText = (TextView) convertView.findViewById(R.id.totalText);
            holder.numberText = (TextView) convertView.findViewById(R.id.numberText);
            holder.services_companyText = (TextView) convertView.findViewById(R.id.services_companyText);
            holder.courier_numberText = (TextView) convertView.findViewById(R.id.courier_numberText);
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.view_logisticsbtn = (Button) convertView.findViewById(R.id.view_logisticsbtn);
            holder.statebtn = (Button) convertView.findViewById(R.id.statebtn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

}

class ViewHolder {
    TextView order_number;
    TextView customerText;
    TextView commoditynameText;
    TextView specificationsText;
    TextView priceText;
    TextView totalText;
    TextView numberText;
    TextView services_companyText;
    TextView courier_numberText;
    ImageView image;
    Button view_logisticsbtn;
    Button statebtn;




}
