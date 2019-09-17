package com.example.thingfinding.user;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thingfinding.R;
import com.example.thingfinding.SQLiteHelper;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private String username;
    private EditText o_password;
    private EditText n_password;
    private EditText a_password;
    private Button btn;
    private TextView exitText;
    private SQLiteHelper dbhelper;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
        initEvent();

    }

    private void initView() {
        intent=getIntent();
        username=intent.getStringExtra("username");
        o_password=(EditText)findViewById(R.id.o_password) ;
        n_password=(EditText)findViewById(R.id.n_password) ;
        a_password=(EditText)findViewById(R.id.a_password);
        btn=(Button)findViewById(R.id.update);
        exitText = (TextView) findViewById(R.id.exitText);

    }

    private void initEvent() {
        btn.setOnClickListener(this);
        exitText.setOnClickListener(this);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.exitText:
                exit();
                break;
            case R.id.update:
                changePassword();
                break;
        }
    }

    public void exit() {
        finish();
    }
    public void changePassword(){
        this.dbhelper = SQLiteHelper.getInstance(this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("password", n_password.getText().toString());
        db.update("Users", contentValues, "username=?",
                new String[]{username});
        Toast.makeText(this,"修改成功！",Toast.LENGTH_SHORT).show();
        db.close();
    }
}
