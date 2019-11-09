package com.example.thingfinding.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thingfinding.Bean.informationinfo;
import com.example.thingfinding.R;
import com.example.thingfinding.user.My_DetailsActivity;
import com.example.thingfinding.user.My_InformationActivity;

import java.util.ArrayList;

public class informationAdapter extends BaseAdapter {

    private String[] heading={"姓名","电话号码","身份证号码","邮箱","店名","店地址","店铺介绍"};
    private LayoutInflater inflater;
    private ArrayList<String> list;
    private Context mConext;
    private String mark;


    public informationAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        inflater = LayoutInflater.from(context);
        mConext=context;

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
            convertView = inflater.inflate(R.layout.save_list, null);
            holder = new ViewHolder();
            holder.heading=(TextView)convertView.findViewById(R.id.text_head);
            holder.ending = (TextView) convertView.findViewById(R.id.text_end);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.heading.setText(heading[position]);
        holder.ending.setText(list.get(position));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (heading[position].equals("邮箱")) {
                    mark = list.get(position);
                    Details(mark);
                }
                if (heading[position].equals("店名")) {
                    mark = list.get(position);
                    Details(mark);
                }
                if (heading[position].equals("店铺地址")) {
                    mark = list.get(position);
                    Details(mark);
                }
                if (heading[position].equals("店铺介绍")) {
                    mark = list.get(position);
                    Details(mark);
                }
            }
        });
        return convertView;
    }
    public void Details(String mark){
        Intent intent = new Intent(mConext, My_DetailsActivity.class);
        intent.putExtra("details", mark);
        mConext.startActivity(intent);
    }

    class ViewHolder {
        TextView heading;
        TextView ending;
    }

}



