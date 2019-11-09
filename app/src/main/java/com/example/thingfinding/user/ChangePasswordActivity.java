package com.example.thingfinding.user;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thingfinding.BaseActivity;
import com.example.thingfinding.Bean.CommonResultBean;
import com.example.thingfinding.DialogUtil;
import com.example.thingfinding.R;
import com.example.thingfinding.SQLiteHelper;
import com.example.thingfinding.Util.BaseCallback;
import com.example.thingfinding.Util.BaseUrl;
import com.example.thingfinding.Util.OkHttpHelp;
import com.example.thingfinding.Views.InputView;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordActivity extends BaseActivity implements View.OnClickListener {

    private String username;
    private InputView o_password;
    private InputView n_password;
    private InputView a_password;
    private Button btn;
    private SQLiteHelper dbhelper;
    private OkHttpHelp mokhttphelp;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
        initEvent();

    }

    private void initView() {
        initNavBar(true,"修改密码");
        intent=getIntent();
        username=intent.getStringExtra("username");
        o_password=fd(R.id.input_old_password) ;
        n_password=fd(R.id.input_new_password) ;
        a_password=fd(R.id.input_new_password_confirm);
        btn=fd(R.id.commit_btn);

    }

    private void initEvent() {
        btn.setOnClickListener(this);
    }

    public void onClick(View v){
        switch(v.getId()){

            case R.id.update:
                changepassword();
                break;
        }
    }
//    public void changePassword(){
//        this.dbhelper = SQLiteHelper.getInstance(this);
//        SQLiteDatabase db = dbhelper.getWritableDatabase();
//        ContentValues contentValues=new ContentValues();
//        contentValues.put("password", n_password.getText().toString());
//        db.update("Users", contentValues, "username=?",
//                new String[]{username});
//        Toast.makeText(this,"修改成功！",Toast.LENGTH_SHORT).show();
//        db.close();
//    }

    public void changepassword() {

        String url = BaseUrl.BASE_URL + "";
        Map<String, String> map = new HashMap<>();

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
                   // String type = response.getType();
                    String msg = response.getMsg();
                    Log.i("--**-**--", "响应成功");
                    Log.i("--**", data);
                    Log.i("--**", code);
                   // Log.i("--**", type);
                    Log.i("--**", msg);
                    if(code.equals("200")){
                        Toast.makeText(ChangePasswordActivity.this,"修改成功！",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ChangePasswordActivity.this,"修改失败",Toast.LENGTH_SHORT).show();
                    }
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
}
