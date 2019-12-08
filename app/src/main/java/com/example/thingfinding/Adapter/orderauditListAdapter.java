package com.example.thingfinding.Adapter;

import android.content.Context;
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

public class orderauditListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<addressItem> list;

    public orderauditListAdapter(List<addressItem> list, Context context) {
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
            convertView = inflater.inflate(R.layout.orderaudit_list, null);
            holder = new ViewHolder();
            holder.nameText = (TextView) convertView.findViewById(R.id.nameText);
            holder.phoneText = (TextView) convertView.findViewById(R.id.phoneText);
            holder.commoditynameText = (TextView) convertView.findViewById(R.id.commoditynameText);
            holder.shuliangText = (TextView) convertView.findViewById(R.id.shuliangText);
            holder.addressText = (TextView) convertView.findViewById(R.id.addressText);
            holder.yuanjiaText = (TextView) convertView.findViewById(R.id.yuanjiaText);
            holder.cuxiaojiaText = (TextView) convertView.findViewById(R.id.cuxiaojiaText);
            holder.chonliangfeiText = (TextView) convertView.findViewById(R.id.chonliangfeiText);
            holder.baoyangfeiText = (TextView) convertView.findViewById(R.id.baoyangfeiText);
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.stateBtn = (Button) convertView.findViewById(R.id.stateBtn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameText.setText("天天吃好店");
        holder.phoneText.setText("17629458765");
        holder.addressText.setText("广东石油化工学院");
        holder.commoditynameText.setText("狗狗");
        holder.shuliangText.setText("共1件商品");
        holder.yuanjiaText.setText("1000");
        holder.cuxiaojiaText.setText("888");
        holder.chonliangfeiText.setText("200");
        holder.baoyangfeiText.setText("300");
        holder.image.setImageResource(R.drawable.dog);
        return convertView;
    }

    class ViewHolder {
        TextView nameText;
        TextView phoneText;
        TextView addressText;
        TextView commoditynameText;
        TextView shuliangText;
        TextView yuanjiaText;
        TextView cuxiaojiaText;
        TextView chonliangfeiText;
        TextView baoyangfeiText;
        ImageView image;

        Button stateBtn;
    }

}