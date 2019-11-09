package com.example.thingfinding;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BaseActivity extends Activity {
    TextView iv_back;
    TextView tv_title;

    //接受资源文件的id，返回一个继承与View的子类
    protected <T extends android.view.View>  T fd(@IdRes int id){
        return findViewById(id);
    }

    //初始化控件
    public void initNavBar(boolean isShowBack,String title){
        iv_back=fd(R.id.iv_back);
        tv_title=fd(R.id.tv_title);

        iv_back.setVisibility(isShowBack ? android.view.View.VISIBLE: android.view.View.GONE);
        tv_title.setText(title);

        iv_back.setOnClickListener(new android.view.View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
            @Override
            public void onClick(android.view.View view) {
                onBackPressed();
            }
        });



    }
}
