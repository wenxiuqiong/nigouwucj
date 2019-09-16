package com.example.thingfinding.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thingfinding.R;

import java.util.List;

/**
 * Created by  ansen
 * Create Time 2016-12-16
 */
public class ListViewAdapter extends BaseAdapter{
    private final int TYPE_SEND=0;//消息发送
    private final int TYPE_ACCEPT=1;//消息接收

    private LayoutInflater inflater;
    private List<Message> messages;

    public ListViewAdapter(Context context, List<Message> messages){
        inflater=LayoutInflater.from(context);
        this.messages=messages;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //item 类型数量
    @Override
    public int getViewTypeCount() {
        return TYPE_ACCEPT+1;
    }

    //每个类型对应的int类型的值 必须从0开始
    @Override
    public int getItemViewType(int position) {
        if(messages.get(position).isSended()){
            return TYPE_SEND;//发送类型
        }
        return TYPE_ACCEPT;//接收类型
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        Message message=messages.get(position);

        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            if(type==TYPE_SEND){//发送的消息
                convertView = inflater.inflate(R.layout.item_message_chat_send, null);
            }else if(type==TYPE_ACCEPT){//接收
                convertView = inflater.inflate(R.layout.item_message_chat_accept, null);
            }

            viewHolder.tvContent= (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.tvContent.setText(message.getContent());
        return convertView;
    }

    private class ViewHolder{
        private TextView tvContent;
    }

}
