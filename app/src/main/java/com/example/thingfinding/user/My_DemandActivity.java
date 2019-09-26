package com.example.thingfinding.user;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;

import com.example.thingfinding.Bean.CommonResultBean;
import com.example.thingfinding.DialogUtil;
import com.example.thingfinding.Fragment.Fragment_HomePage;
import com.example.thingfinding.Fragment.Fragment_News;
import com.example.thingfinding.Fragment.MyFragmentPageAdapter;
import com.example.thingfinding.R;
import com.example.thingfinding.Util.BaseCallback;
import com.example.thingfinding.Util.BaseUrl;
import com.example.thingfinding.Util.OkHttpHelp;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class My_DemandActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameText;
    private EditText phoneText;
    private EditText addText;
    private TextView exitText;
    private TextView addressbookText;
    private TextView tv_type;
    private EditText demand;
    private EditText originalpriceText;
    private EditText promotionalpriceText;
    private EditText stockText;
    private EditText costText;
    private EditText typeText;
    private EditText chargeText;

    private Button releasebtn;
    private ImageView image;
    private int REQUEST_GET_IMAGE = 1;
    private int MAX_SIZE = 769;
    private Bitmap bitmap = null;
    Spinner spinner;
    private OkHttpHelp mokhttphelp;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__demand);
        initView();
        initEvent();
    }

    private void initView() {
        nameText = (EditText) findViewById(R.id.nameText);
        phoneText = (EditText) findViewById(R.id.phoneText);
        addText = (EditText) findViewById(R.id.addText);
        exitText = (TextView) findViewById(R.id.exitText);
        tv_type = (TextView) findViewById(R.id.tv_type);
        addressbookText= (TextView) findViewById(R.id.addressbookText);
        demand= (EditText) findViewById(R.id.demand);
        originalpriceText= (EditText) findViewById(R.id.originalpriceText);
        promotionalpriceText= (EditText) findViewById(R.id.promotionalpriceText);
        stockText= (EditText) findViewById(R.id.stockText);
        costText= (EditText) findViewById(R.id.costText);
        typeText= (EditText) findViewById(R.id.typeText);
        chargeText= (EditText) findViewById(R.id.chargeText);
        releasebtn= (Button) findViewById(R.id.releasebtn);
        image=(ImageView)findViewById(R.id.image);
        tv_type.setText(getIntent().getStringExtra("demandType"));
    }


    private void initEvent() {
        exitText.setOnClickListener(this);

        addressbookText.setOnClickListener(this);
        releasebtn.setOnClickListener(this);
        image.setOnClickListener(this);;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //这个方法里可以对点击事件进行处理
                //i指的是点击的位置,通过i可以取到相应的数据源
                String info=adapterView.getItemAtPosition(i).toString();//获取i所在的文本
               //mBodyLayout.addView(PetsView);
                //Toast.makeText(My_DemandActivity.this, info, Toast.LENGTH_SHORT).show();
                System.out.print("666");
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exitText:
                exit();
                break;
            case R.id.addressbookText:
                addressbook();
                break;
            case R.id.releasebtn:

                showtijiaoDialog();
                break;
            case R.id.image:
                iamgeclik();
                break;
        }
    }

    public void exit() {
        finish();
    }

    public void addressbook() {
        Intent intent=new Intent(this,addressbookActivity.class);
        startActivityForResult(intent,1);
    }

    public void iamgeclik(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Browser Image..."),REQUEST_GET_IMAGE);
    }

    public void release() {
        String url = BaseUrl.BASE_URL + "";
        Map<String, String> map = new HashMap<>();
        map.put("user",nameText.getText().toString().trim());
        map.put("pwd",phoneText.getText().toString().trim());
        map.put("user",addText.getText().toString().trim());
        map.put("pwd",demand .getText().toString().trim());
        map.put("user",originalpriceText.getText().toString().trim());
        map.put("pwd",promotionalpriceText.getText().toString().trim());
        map.put("user",stockText.getText().toString().trim());
        map.put("pwd",costText.getText().toString().trim());
        map.put("user",typeText.getText().toString().trim());
        map.put("pwd",chargeText.getText().toString().trim());
        try {
            mokhttphelp = OkHttpHelp.getinstance();
            mokhttphelp.post(url, map, new BaseCallback<CommonResultBean>() {
                @Override
                public void onRequestBefore() {

                }

                @Override
                public void onFailure(Request request, Exception e) {
                    e.printStackTrace();
                }

                @Override
                public void onSuccess(CommonResultBean response) {
                    String data = (String) response.getData();
                    String code = response.getCode();
                    String type = response.getType();
                    String msg = response.getMsg();
                    Log.i("--**-**--", "发布成功");
                    Log.i("--**", data);
                    Log.i("--**", code);
                    Log.i("--**", type);
                    Log.i("--**", msg);
                }

                @Override
                public void onError(Response response, int errorCode, Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            DialogUtil.showDialog(this, "服务器响应异常", false);
            e.printStackTrace();
        }
    }

    public void showtijiaoDialog(){
        final android.app.AlertDialog.Builder normalDialog = new android.app.AlertDialog.Builder(this);
        normalDialog.setTitle("发布服务");
        normalDialog.setMessage("是否发布该服务");
        normalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(My_DemandActivity.this, "发布成功", Toast.LENGTH_SHORT).show();

            }
        });
        normalDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        normalDialog.show();// 显示
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




    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Fragment f = fragmentManager.findFragmentByTag(curFragmentTag);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==  1 && resultCode == 1) {
            nameText.setText(data.getStringExtra("name"));
            phoneText.setText(data.getStringExtra("phone"));
            addText.setText(data.getStringExtra("address"));

        }

        if ( resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            try{
                InputStream inputStream = getContentResolver().openInputStream(uri);
                BitmapFactory.decodeStream(inputStream,null,options);
                inputStream.close();
                int height = options.outHeight;
                int width = options.outWidth;
                int sampleSize = 1;
                int max = Math.max(height,width);
                if (max>MAX_SIZE){
                    int nw = width/2;
                    int nh = height/2;
                    while ((nw/sampleSize)> MAX_SIZE || (nh / sampleSize)>MAX_SIZE){
                        sampleSize *=2;
                    }
                }
                options.inSampleSize = sampleSize;
                options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri),null,options);
                image.setImageBitmap(bitmap);
            }catch (IOException ioe){

            }

        }
    }


}
