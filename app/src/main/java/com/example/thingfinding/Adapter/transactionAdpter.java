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
        holder.stateText.setText("2019-9-21");
        holder.commoditynameText.setText("2019-9-21");
        holder.priceText.setText("2019-9-21");
       // image.setText("2019-9-21");

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
