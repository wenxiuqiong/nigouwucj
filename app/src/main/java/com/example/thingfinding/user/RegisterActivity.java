package com.example.thingfinding.user;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thingfinding.BaseActivity;
import com.example.thingfinding.Bean.CodeBean;
import com.example.thingfinding.Bean.CommonCustomerneedBean;
import com.example.thingfinding.Bean.CommonResultBean;
import com.example.thingfinding.Bean.phoneBean;
import com.example.thingfinding.Bean.userBean;
import com.example.thingfinding.DialogUtil;
import com.example.thingfinding.R;
import com.example.thingfinding.SQLiteHelper;
import com.example.thingfinding.Util.BaseCallback;
import com.example.thingfinding.Util.BaseUrl;
import com.example.thingfinding.Util.OkHttpHelp;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

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
    private EditText codeText;
    private TextView codeBtn;
    private ImageView image;
    private Button btn;
    private int REQUEST_GET_IMAGE = 1;
    private int MAX_SIZE = 769;
    private Bitmap bitmap = null;
    private SQLiteHelper dbhelper;
    private OkHttpHelp mokhttp;
    private TimeCount time;
    private String getCheckCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initEvent();
    }

    private void initView() {
        initNavBar(true,"注册");
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
        image = (ImageView) findViewById(R.id.touxiang);
        btn = (Button) findViewById(R.id.zhuce);
        time = new TimeCount(60000, 1000);
        codeText=(EditText) findViewById(R.id.et_code);
        codeBtn=(TextView) findViewById(R.id.codeBtn);

    }

    private void initEvent() {
        btn.setOnClickListener(this);
        image.setOnClickListener(this);
        codeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                time.getCode();
                time.start();
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhuce:
                passData();
                break;
            case R.id.touxiang:
                iamgeclik();
                break;
        }
    }

    public void iamgeclik(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Browser Image..."),REQUEST_GET_IMAGE);
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            codeBtn.setBackgroundColor(Color.parseColor("#B6B6D8"));
            codeBtn.setClickable(false);
            codeBtn.setText("("+millisUntilFinished / 1000 +") 秒后可重新发送");
        }

        public void getCode(){
            //获取验证码
            String url = BaseUrl.BASE_URL + "/business/telephone/getCheckCode?";
            Map<String,String> map=new HashMap<>();
            map.put("telephoneNumber",et_telephone.getText().toString().trim());
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
                        // DialogUtil.showDialog(RegisterActivity.this,"服务器响应成功",true);
                        Gson gson=new Gson();
                        String result=gson.toJson(response.getData());
                        phoneBean phone=gson.fromJson(result,phoneBean.class);
                        getCheckCode=phone.getCheckCode();
                        Log.i("--**-**--","响应成功");
                        Log.i("--**-**--",getCheckCode);
                        // Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(Response response, int errorCode, Exception e) {
                        e.printStackTrace();
                    }
                });
            }catch (Exception e){
                DialogUtil.showDialog(RegisterActivity.this,"服务器响应异常",false);
                e.printStackTrace();
            }
        }


        public void onFinish() {
            codeBtn.setText("重新获取验证码");
            codeBtn.setClickable(true);
            codeBtn.setBackgroundColor(Color.parseColor("#dd3344"));

        }
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
        String checkCode=codeText.getText().toString().trim();
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
        }if(!et_namepass.getText().toString().equals(et_passwordagin.getText().toString())){
            Toast.makeText(this,"确认密码失败，请重新确认",Toast.LENGTH_SHORT).show();
        }else if(!checkCode.equals(getCheckCode)){
            Toast.makeText(this,"验证码错误，请重新获取验证码",Toast.LENGTH_SHORT).show();
        }else {
            String url = BaseUrl.BASE_URL + "/business/user/isContainEmailOrPhone?";
            Map<String,String> map=new HashMap<>();
            map.put("user",phone);
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
                        // DialogUtil.showDialog(RegisterActivity.this,"服务器响应成功",true);
                        Gson gson=new Gson();
                        String result=gson.toJson(response.getData());
                        String code = response.getCode();
                        Log.i("--**-**--","响应成功");
                        Log.i("--**-**--",code);
                        if(code.equals("200")){
                            RegisterActivity.this.dbhelper = SQLiteHelper.getInstance(RegisterActivity.this);
                            SQLiteDatabase db = dbhelper.getWritableDatabase();
                            ContentValues cv = new ContentValues();
                            String url = BaseUrl.BASE_URL + "/business/user/register?";
                            Map<String,String> map=new HashMap<>();
                            map.put("user",name);
                            map.put("password",password);
                            map.put("name",realname);
                            map.put("telephone",phone);
                            map.put("idCard",idcard);
                            map.put("eMail",email);
                            map.put("storeName",storeName);
                            map.put("storeAddress",storeAdress);
                            map.put("storeIntroduction",storeInduction);
                            map.put("code",storeInduction);
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
                                        // DialogUtil.showDialog(RegisterActivity.this,"服务器响应成功",true);
                                        String data=(String) response.getData();
                                        Log.i("--**-**--","响应成功");
                                        //Log.i("--**",data);
                                        cv.put("username", name);
                                        cv.put("phone", phone);
                                        cv.put("email",email);
                                        cv.put("avatar", bitmabToBytes());//图片转为二进制
                                        db.insert("Users", null, cv);
                                        Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                                        db.close();
                                        Intent intent = new Intent();
                                        intent.putExtra("userName", name);
                                        setResult(RESULT_OK, intent);
                                        RegisterActivity.this.finish();
                                        System.out.print("666");
                                    }
                                    @Override
                                    public void onError(Response response, int errorCode, Exception e) {
                                        e.printStackTrace();
                                    }
                                });
                            }catch (Exception e){
                                DialogUtil.showDialog(RegisterActivity.this,"服务器响应异常",false);
                                e.printStackTrace();
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this,"该手机号已经被注册",Toast.LENGTH_SHORT).show();
                        }
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

    }

    //数据回调
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
