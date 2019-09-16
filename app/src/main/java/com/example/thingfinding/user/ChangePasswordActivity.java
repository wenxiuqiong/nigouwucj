package com.example.thingfinding.user;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thingfinding.R;
import com.example.thingfinding.SQLiteHelper;

public class ChangePasswordActivity extends AppCompatActivity {

    private String username;
    private EditText n_password;
    private EditText a_password;
    private Button btn;
    private SQLiteHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Intent intent=getIntent();
        username=intent.getStringExtra("username") ;
        n_password=(EditText)findViewById(R.id.n_password) ;
        a_password=(EditText)findViewById(R.id.a_password);
        btn=(Button)findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
            }
        });
    }

    public void exit(View v){
        finish();
    }

    public void changePassword(){
        this.dbhelper = SQLiteHelper.getInstance(this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("password", n_password.getText().toString());
        db.update("Users", contentValues, "username=?",
                new String[]{username.toString().trim()});
        Toast.makeText(this,"修改成功！",Toast.LENGTH_SHORT).show();
        db.close();
    }
}
