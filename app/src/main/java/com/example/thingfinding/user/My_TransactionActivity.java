package com.example.thingfinding.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thingfinding.Adapter.ViewpagerAdapter;
import com.example.thingfinding.BaseActivity;
import com.example.thingfinding.Bean.CommonResultBean;
import com.example.thingfinding.DialogUtil;
import com.example.thingfinding.Fragment.Fragment_HomePage;
import com.example.thingfinding.Fragment.Fragment_Me;
import com.example.thingfinding.Fragment.Fragment_News;
import com.example.thingfinding.Fragment.MyFragmentPageAdapter;
import com.example.thingfinding.R;
import com.example.thingfinding.Util.BaseCallback;
import com.example.thingfinding.Util.BaseUrl;
import com.example.thingfinding.Util.OkHttpHelp;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class My_TransactionActivity extends BaseActivity implements
        RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener{

    //定义Fragment
    private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment3;
    private Fragment fragment4;
    //定义FragmentManager
    private FragmentManager fragmentManager;

    private ViewPager viewPager;
    private List<Fragment> fragmentLists;
    private ViewpagerAdapter adapter;
    private RadioGroup radioGroup;
    private RadioButton whole; // 表示第一个RadioButton 组件
    String[] mlist={"全部订单","客户选择中","交易成功","交易失败"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__transaction);
        //初始化界面组件
        init();
        //初始化ViewPager
        initViewPager();


    }
    private void initViewPager() {
//        fragment1 = new Fragment_Transaction();
//        fragment2 = new Fragment_Transaction();
//        fragment3 = new Fragment_Transaction();
//        fragment4 = new Fragment_Transaction();
//        fragmentLists = new ArrayList<Fragment>();
//        fragmentLists.add(fragment1);
//        fragmentLists.add(fragment2);
//        fragmentLists.add(fragment3);
//        fragmentLists.add(fragment4);
        //获取FragmentManager对象
        fragmentManager = getSupportFragmentManager();
        //获取FragmentPageAdapter对象
        adapter = new ViewpagerAdapter(fragmentManager, mlist);
        //设置Adapter，使ViewPager 与 Adapter 进行绑定
        viewPager.setAdapter(adapter);
        //设置ViewPager默认显示第一个View
       // viewPager.setCurrentItem(0);
        //设置第一个RadioButton为默认选中状态
        whole.setChecked(true);
        //ViewPager页面切换监听
        viewPager.addOnPageChangeListener(this);
    }

    private void init() {
        initNavBar(true,"我的交易");
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        whole = (RadioButton) findViewById(R.id.wholeBtn);
        //RadioGroup状态改变监听
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.wholeBtn: // 首页
                //显示第一个Fragment并关闭动画效果
                viewPager.setCurrentItem(0,false);
                break;
            case R.id.to_be_shippedBtn: // 团购
                viewPager.setCurrentItem(1,false);
                break;
            case R.id.goods_to_be_receivedBtn: // 发现
                viewPager.setCurrentItem(2,false);
                break;
            case R.id.completedBtn: // 发现
                viewPager.setCurrentItem(3,false);
                break;

        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {}

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {}

    /**
     * ViewPager切换Fragment时，RadioGroup做相应的监听
     */
    @Override
    public void onPageSelected(int arg0) {
        switch (arg0) {
            case 0:
                radioGroup.check(R.id.wholeBtn);
              //  Intent intent=new Intent(this,Fragment_Transaction.class);
                break;
            case 1:
                radioGroup.check(R.id.to_be_shippedBtn);

                break;
            case 2:
                radioGroup.check(R.id.goods_to_be_receivedBtn);
                break;
            case 3:
                radioGroup.check(R.id.completedBtn);
                break;
        }
    }

}
