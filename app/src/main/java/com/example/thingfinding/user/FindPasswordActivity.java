package com.example.thingfinding.user;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
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
import com.example.thingfinding.Util.BaseUrl;
import com.example.thingfinding.Util.OkHttpHelp;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

public class FindPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText newpassword;
    private EditText code;
    private Button btnreset;
    private Button btn;
    private ImageView codeImage;
    private TextView exitText;
    private SQLiteHelper dbhelper;
    private CodeUtils codeUtils;
    private String codeStr;

    private OkHttpHelp mokhttphelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        initView();
        initEvent();

        codeClik();

    }

    private void initView() {
        username = (EditText) findViewById(R.id.username);
        newpassword = (EditText) findViewById(R.id.new_password);
        code = (EditText) findViewById(R.id.code);
        btnreset = (Button) findViewById(R.id.reset);
        btn = (Button) findViewById(R.id.button4);
        codeImage = (ImageView) findViewById(R.id.image);
        exitText = (TextView) findViewById(R.id.exitText);

    }

    private void initEvent() {
        exitText.setOnClickListener(this);
        btnreset.setOnClickListener(this);

        btn.setOnClickListener(this);
    }

    public void exit() {
        finish();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exitText:
                exit();
                break;
            case R.id.reset:
                resetpassword();
                break;
            case R.id.button4:
                codeClik();
                break;
        }
    }

    public void resetPassword() {
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
                    String type = response.getType();
                    String msg = response.getMsg();
                    Log.i("--**-**--", "已重置密码");
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



    public void resetpassword() {
        this.dbhelper = SQLiteHelper.getInstance(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String name = null;
        Cursor cur = db.query("Users", new String[]{"username"},
                null, null, null, null, null);
        while (cur.moveToNext()) {
            //将Blob数据转化为字节数组
            name = cur.getString(cur.getColumnIndex("username"));
        }
        codeStr = code.getText().toString().trim();
        Log.e("codeStr", codeStr);
        if (null == codeStr || TextUtils.isEmpty(codeStr)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();

        }
        String code = codeUtils.getCode();
        Log.e("code", code);
        if (code.equalsIgnoreCase(codeStr) && username.getText().toString().trim().equals(name)) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("password", newpassword.getText().toString());
            db.update("Users", contentValues, "username=?",
                    new String[]{username.getText().toString().trim()});
            Toast.makeText(this, "修改成功！", Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "验证码正确", Toast.LENGTH_SHORT).show();
        } else if (!username.getText().toString().trim().equals(name)) {
            Toast.makeText(this, "用户名不存在！", Toast.LENGTH_SHORT).show();
            codeClik();
        } else {
            Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
            codeClik();
        }
        cur.close();
        db.close();
    }

    public void codeClik() {
        codeUtils = CodeUtils.getInstance();
        Bitmap bitmap = codeUtils.createBitmap();
        codeImage.setImageBitmap(bitmap);
    }
}