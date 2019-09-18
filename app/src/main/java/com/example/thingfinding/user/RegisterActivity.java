package com.example.thingfinding.user;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thingfinding.Bean.CommonResultBean;
import com.example.thingfinding.DialogUtil;
import com.example.thingfinding.R;
import com.example.thingfinding.SQLiteHelper;
import com.example.thingfinding.Util.BaseCallback;
import com.example.thingfinding.Util.OkHttpHelp;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_nameuser;
    private EditText et_namepass;
    private EditText et_passwordagin;
    private EditText et_name;
    private EditText et_telephone;
    private EditText et_idCard;
    private EditText et_eMail;
    private EditText et_storeName;
    private EditText et_storeAdress;
    private EditText et_storeIntroduce;
    private ImageView image;
    private Button btn;
    private TextView exitText;
    private int REQUEST_GET_IMAGE = 1;
    private int MAX_SIZE = 769;
    private Bitmap bitmap = null;
    private SQLiteHelper dbhelper;
    private OkHttpHelp mokhttp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initEvent();
    }

    private void initView() {
        et_nameuser = (EditText) findViewById(R.id.etusername);
        et_name=(EditText)findViewById(R.id.etname);
        et_namepass = (EditText) findViewById(R.id.etpwd);
        et_passwordagin = (EditText) findViewById(R.id.etpawdagin);
        et_telephone=(EditText)findViewById(R.id.etphone);
        et_eMail=(EditText)findViewById(R.id.etemail);
        et_idCard=(EditText)findViewById(R.id.etIdCard);
        et_storeAdress=(EditText)findViewById(R.id.etstoreadress);
        et_storeName=(EditText)findViewById(R.id.etstoreName);
        et_storeIntroduce=(EditText)findViewById(R.id.etstoreintroduction);
        image = (ImageView) findViewById(R.id.imageView2);
        btn = (Button) findViewById(R.id.zhuce);
        exitText = (TextView) findViewById(R.id.exitText);
    }

    private void initEvent() {
        exitText.setOnClickListener(this);
        btn.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exitText:
                Return();
                break;
            case R.id.zhuce:
                passData();
                break;
        }
    }
    public void Return(){
        finish();
    }

    public void iamgeclik(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Browser Image..."),REQUEST_GET_IMAGE);
    }

    public void passData() {
        String name=et_nameuser.getText().toString().trim();
        String password=et_namepass.getText().toString().trim();
        String passwordagin=et_passwordagin.getText().toString().trim();
        String email=et_eMail.getText().toString().trim();
        String realname=et_name.getText().toString().trim();
        String idcard=et_idCard.getText().toString().trim();
        String storeName=et_storeName.getText().toString().trim();
        String storeAdress=et_storeAdress.getText().toString().trim();
        String storeInduction=et_storeIntroduce.getText().toString().trim();
        String phone=et_telephone.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"请输入用户名",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        } else if(TextUtils.isEmpty(realname)){
            Toast.makeText(this,"请输入姓名",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"请输入邮箱",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"请输入电话",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(storeName)){
            Toast.makeText(this,"请输入店名",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(storeAdress)){
            Toast.makeText(this,"请输入店铺地址",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(storeInduction)){
            Toast.makeText(this,"请输入店铺介绍",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(idcard)){
            Toast.makeText(this,"请输入ID号",Toast.LENGTH_SHORT).show();
            return;
        }

        else if(!et_namepass.getText().toString().equals(et_passwordagin.getText().toString())){
            Toast.makeText(this,"确认密码失败，请重新确认",Toast.LENGTH_SHORT).show();
        }else {
            //boolean savesuccess=SPSave.saveUserInfo(this,name,password);
            //if(savesuccess){
            this.dbhelper = SQLiteHelper.getInstance(this);
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            String url=OkHttpHelp.BASE_URL+"";
            Map<String,String> map=new HashMap<>();
            map.put("user",name);
            map.put("password",password);
            map.put("name",realname);
            map.put("telephone",phone);
            map.put("idCard",idcard);
            map.put("eMail",email);
            map.put("storeName",storeName);
            map.put("storeAdress",storeAdress);
            map.put("storeIntroduction",storeInduction);
            try {
                mokhttp=OkHttpHelp.getinstance();
                mokhttp.post(url, map, new BaseCallback<CommonResultBean>() {
                    @Override
                    public void onRequestBefore() {

                    }

                    @Override
                    public void onFailure(Request request, Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onSuccess(CommonResultBean response) {
                        DialogUtil.showDialog(RegisterActivity.this,"服务器响应成功",true);
                        String data=(String) response.getData();
                        Log.i("--**-**--","响应成功");
                        Log.i("--**",data);
//  cv.put("username", et_nameuser.getText().toString());
//                        cv.put("password", et_namepass.getText().toString());
//                        cv.put("avatar", bitmabToBytes());//图片转为二进制
//                        db.insert("Users", null, cv);
//                        Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
//                        db.close();
//                        Intent data = new Intent();
//                        data.putExtra("userName", name);
//                        setResult(RESULT_OK, data);
//                        RegisterActivity.this.finish();
                    }
                    @Override
                    public void onError(Response response, int errorCode, Exception e) {
                        e.printStackTrace();
                    }
                });
            }catch (Exception e){
                DialogUtil.showDialog(this,"服务器响应异常",false);
                e.printStackTrace();
            }
        }
        //Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
        //finish();
        //}else{
        //Toast.makeText(this,"注册失败",Toast.LENGTH_SHORT).show();
        // }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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


}
