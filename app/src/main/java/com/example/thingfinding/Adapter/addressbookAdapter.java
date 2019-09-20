package com.example.thingfinding.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thingfinding.Bean.addressItem;
import com.example.thingfinding.R;

import java.util.List;

/**
 * Created by Shang on 2016/8/3.
 */
public class addressbookAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<addressItem> list;

    public addressbookAdapter(List<addressItem> list, Context context) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.addbook_list, null);
            holder = new ViewHolder();
            holder.nameText = (TextView) convertView.findViewById(R.id.nameText);
            holder.phoneText = (TextView) convertView.findViewById(R.id.phoneText);
            holder.addressText = (TextView) convertView.findViewById(R.id.addText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final addressItem bean = list.get(position);
        holder.nameText.setText(bean.getName());
        holder.phoneText.setText(bean.getPhone());
        holder.addressText.setText(bean.getAddress());
        return convertView;
    }
    class ViewHolder {
        TextView nameText;
        TextView phoneText;
        TextView addressText;
     }
}



