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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thingfinding.R;
import com.example.thingfinding.SQLiteHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_nameuser;
    private EditText et_namepass;
    private EditText et_passwordagin;
    private ImageView image;
    private Button btn;
    private TextView exitText;
    private int REQUEST_GET_IMAGE = 1;
    private int MAX_SIZE = 769;
    private Bitmap bitmap = null;
    private SQLiteHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initEvent();
    }

    private void initView() {
        et_nameuser = (EditText) findViewById(R.id.editText);
        et_namepass = (EditText) findViewById(R.id.editText2);
        et_passwordagin = (EditText) findViewById(R.id.passwordagin_e);
        image = (ImageView) findViewById(R.id.touxiang);
        btn = (Button) findViewById(R.id.zhuce);
        exitText = (TextView) findViewById(R.id.exitText);
    }

    private void initEvent() {
        exitText.setOnClickListener(this);
        btn.setOnClickListener(this);
        image.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exitText:
                Return();
                break;
            case R.id.zhuce:
                passData();
                break;
            case R.id.touxiang:
                iamgeclik();
                break;
        }
    }
    public void Return(){
        finish();
    }

    public void iamgeclik(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Browser Image..."),REQUEST_GET_IMAGE);
    }

    public void passData() {
        String name=et_nameuser.getText().toString().trim();
        String password=et_namepass.getText().toString().trim();
        String passwordagin=et_passwordagin.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"请输入用户名",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
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
            cv.put("username", et_nameuser.getText().toString());
            cv.put("password", et_namepass.getText().toString());
            cv.put("avatar", bitmabToBytes());//图片转为二进制
            db.insert("Users", null, cv);
            Toast.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();
            db.close();
            Intent data = new Intent();
            data.putExtra("userName", name);
            setResult(RESULT_OK, data);
            RegisterActivity.this.finish();
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
