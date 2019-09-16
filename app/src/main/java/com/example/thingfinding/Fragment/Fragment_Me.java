package com.example.thingfinding.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thingfinding.R;
import com.example.thingfinding.SQLiteHelper;
import com.example.thingfinding.tenderview.partytypeActivity;
import com.example.thingfinding.tenderview.tenderActivity;
import com.example.thingfinding.user.My_DemandActivity;
import com.example.thingfinding.user.My_InformationActivity;
import com.example.thingfinding.user.My_SetupActivity;
import com.example.thingfinding.user.My_TransactionActivity;
import com.example.thingfinding.user.loginActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class Fragment_Me extends Fragment {


    private LinearLayout layout;
    private ListView listView;
    protected TextView txname;
    private ImageView image;
    Bitmap bitmap = null;
    private SQLiteHelper dbhelper;
    private String FILE = "saveUserNamePwd";//用于保存SharedPreferences的文件
    private SharedPreferences sp = null;//声明一个SharedPreferences
    //private String[] heading={"姓名","手机号","身份证","电子邮箱","收货地址"};
    private String[] heading = {"个人信息", "发布需求", "我的交易", "设置"};
    private String[] ending = {">", ">", ">", ">"};
    private ArrayList<String> list = new ArrayList<String>();
    private String select;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__me, container, false);
        txname = (TextView) view.findViewById(R.id.textView10);
        image = (ImageView) view.findViewById(R.id.imageView2);
        listView = (ListView) view.findViewById(R.id.lv_main);
        layout = (LinearLayout) view.findViewById(R.id.linearLayout);
        MyBaseAdapter myBaseAdapter = new MyBaseAdapter();
        listView.setAdapter(myBaseAdapter);
        for (int i = 0; i < heading.length; i++) {
            list.add(heading[i]);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                if (list.get(arg2).equals("个人信息")) {
                    //select = list.get(arg2);
                    my_information();
                }
                if (list.get(arg2).equals("发布需求")) {
                    // select = list.get(arg2);
                    my_demand();
                }
                if (list.get(arg2).equals("我的交易")) {
                    my_transaction();
                }
                if (list.get(arg2).equals("设置")) {
                    my_setup();
                }
            }
        }
        );
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click();
            }
        });
        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Fragment f = fragmentManager.findFragmentByTag(curFragmentTag);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==  1 && resultCode == 1) {
            String name=data.getStringExtra("login");
            txname.setText(name);

            this.dbhelper = SQLiteHelper.getInstance(getActivity());
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            byte[] imgData = null;
            Cursor cur = db.query("Users",new String[]{"avatar"},
                    "username=?", new String[]{txname.getText().toString()}, null, null, null);
            if(cur.getCount()==0){
            }
            while(cur.moveToNext()) {
                //将Blob数据转化为字节数组
                imgData = cur.getBlob(cur.getColumnIndex("avatar"));
                Bitmap imagebitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
                //将位图显示为图片
                image.setImageBitmap(imagebitmap);
            }
            cur.close();
            db.close();
        }
        if (requestCode == 2 && resultCode == 2){
            String name=data.getStringExtra("result");
            txname.setText(name);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.chushi, null);
            image.setImageBitmap(bitmap);
        }
    }

    public void click(){
        if(txname.getText().toString().trim().equals("登 录")) {
       Intent intent = new Intent(getActivity(),loginActivity.class);
//           Intent intent = new Intent(getActivity(),partytypeActivity.class);
            startActivityForResult(intent,1);
        }else{
        }

    }

    public void my_information(){
        if(txname.getText().toString().trim().equals("登 录")) {
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(getActivity(), My_InformationActivity.class);
            intent.putExtra("username", txname.getText().toString().trim());
            startActivity(intent);
        }

    }

    public void my_demand(){
        if(txname.getText().toString().trim().equals("登 录")) {
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(getActivity(),  My_DemandActivity.class);
            startActivity(intent);
        }

    }
    public void my_transaction(){
        if(txname.getText().toString().trim().equals("登 录")) {
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(getActivity(),  My_TransactionActivity.class);
            startActivity(intent);
        }

    }


    public void my_setup(){
        if(txname.getText().toString().trim().equals("登 录")) {
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(getActivity(), My_SetupActivity.class);
            intent.putExtra("username",txname.getText().toString().trim());
            startActivityForResult(intent, 2);
        }
    }


    public byte[] bitmabToBytes(){

        //将图片转化为位图
        image.setDrawingCacheEnabled(Boolean.TRUE);
        Bitmap bi=image.getDrawingCache();
        //Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.id.imageView2);
        int size = bi.getWidth() * bitmap.getHeight() * 4;
        //创建一个字节数组输出流,流的大小为size
        ByteArrayOutputStream baos= new ByteArrayOutputStream(size);
        try {
            //设置位图的压缩格式，质量为100%，并放入字节数组输出流中
            bi.compress(Bitmap.CompressFormat.PNG, 100, baos);
            //将字节数组输出流转化为字节数组byte[]
            byte[] imagedata = baos.toByteArray();
            return imagedata;
        }catch (Exception e){
        }finally {
            try {
                bi.recycle();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new byte[0];
    }
    public void queryImage() {
        this.dbhelper = SQLiteHelper.getInstance(getActivity());
        //  Toast.makeText(this,"信息已删除！",Toast.LENGTH_SHORT).show();
        SQLiteDatabase db = dbhelper.getReadableDatabase();

        byte[] imgData = null;

        Cursor cur = db.query("Users",new String[]{"avatar"},
                "username=?", new String[]{txname.getText().toString()}, null, null, null);

        if(cur.getCount()==0){
        }
        while(cur.moveToNext()) {
            //将Blob数据转化为字节数组
            imgData = cur.getBlob(cur.getColumnIndex("avatar"));
            Bitmap imagebitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
            //将位图显示为图片
            image.setImageBitmap(imagebitmap);
        }
        cur.close();
        db.close();

    }

    class MyBaseAdapter extends BaseAdapter {
        public int getCount(){
            return heading.length;
        }
        public Object getItem(int position){
            return heading[position];
        }
        public long getItemId(int postion){
            return postion;
        }
        public View getView(int postion,View convertView,ViewGroup parent){
            ViewHolder holder;
            if(convertView==null) {
                convertView = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.main_item, parent, false);
                holder = new ViewHolder();
                holder.texthead = (TextView) convertView.findViewById(R.id.main_staring);
                holder.textend = (TextView) convertView.findViewById(R.id.main_ending);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder)convertView.getTag();
            }
            holder.texthead.setText(heading[postion]);
            holder.textend.setText(ending[postion]);
            return convertView;
        }

    }
    class ViewHolder{
        TextView texthead;
        TextView textend;
    }
}
