package com.example.thingfinding.seting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thingfinding.BaseActivity;
import com.example.thingfinding.R;

public class SuggestionActivity extends BaseActivity implements View.OnClickListener {

    private EditText editText;
    private Button subimt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestsion);
        initView();
        initEvent();

    }

    private void initView() {
        initNavBar(true,"问题反馈");
        editText=(EditText)findViewById(R.id.editText);
        subimt=(Button)findViewById(R.id.subimt);
    }

    private void initEvent() {
        subimt.setOnClickListener(this);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.subimt:
                subimt();
                break;

        }
    }

    public void subimt(){
        if(editText.getText().toString().trim()==null){
            Toast.makeText(this,"您还没输入您遇到的问题！",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"您提交的问题我们已经收到了，我们会尽快解决您遇到的问题！",Toast.LENGTH_SHORT).show();
        }


    }
}
