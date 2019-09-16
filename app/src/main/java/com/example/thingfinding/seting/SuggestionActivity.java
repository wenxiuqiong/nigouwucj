package com.example.thingfinding.seting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thingfinding.R;

public class SuggestionActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestsion);
        editText=(EditText)findViewById(R.id.editText);
        button=(Button)findViewById(R.id.button5);

    }

    public void exit(View v) {
        finish();
    }

    public void subimt(View view){
        if(editText.getText().toString().trim()==null){
            Toast.makeText(this,"您还没输入您遇到的问题！",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"您提交的问题我们已经收到了，我们会尽快解决您遇到的问题！",Toast.LENGTH_SHORT).show();
        }


    }
}
