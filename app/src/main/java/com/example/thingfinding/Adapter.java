package com.example.thingfinding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Shang on 2016/8/3.
 */
public class Adapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<ItemInfo> list;
    private OnShowItemClickListener onShowItemClickListener;

    public Adapter(List<ItemInfo> list, Context context) {
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
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.mark_item, null);
            holder.text = (TextView) convertView.findViewById(R.id.text);
            holder.timeText = (TextView) convertView.findViewById(R.id.time_text);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iamge_item);
            holder.cb = (CheckBox) convertView.findViewById(R.id.listview_select_cb);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
       final ItemInfo bean = list.get(position);
        // 是否是多选状态
         if (bean.isShow()) {
            holder.cb.setVisibility(View.VISIBLE);
        } else {
            holder.cb.setVisibility(View.GONE);
        }

        holder.text.setText(bean.getInformation());
        holder.imageView.setImageBitmap(bean.getBitmap());
        holder.timeText.setText(bean.getTime());
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bean.setChecked(true);
                } else {
                    bean.setChecked(false);
                }
                // 回调方法，将Item加入已选
                onShowItemClickListener.onShowItemClick(bean,position);
            }
        });
        // 必须放在监听后面
        holder.cb.setChecked(bean.isChecked());
        return convertView;
    }

    static class ViewHolder {
        TextView text;
        TextView timeText;
        ImageView imageView;
        CheckBox cb;
    }

    public interface OnShowItemClickListener {
        public void onShowItemClick(ItemInfo bean, int position);
    }

    public void setOnShowItemClickListener(OnShowItemClickListener onShowItemClickListener) {
        this.onShowItemClickListener = onShowItemClickListener;
    }
}

