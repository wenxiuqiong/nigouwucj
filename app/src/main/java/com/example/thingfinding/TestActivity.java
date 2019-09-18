package com.example.thingfinding;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.example.thingfinding.Fragment.Fragment_HomePage;
import com.example.thingfinding.Fragment.Fragment_Me;
import com.example.thingfinding.Fragment.Fragment_News;
import com.example.thingfinding.Fragment.MyFragmentPageAdapter;

public class TestActivity extends FragmentActivity implements
        OnCheckedChangeListener, OnPageChangeListener{

    //定义Fragment
    private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment3;
    //定义FragmentManager
    private FragmentManager fragmentManager;

    private ViewPager viewPager;
    private List<Fragment> fragmentLists;
    private MyFragmentPageAdapter adapter;
    private RadioGroup radioGroup;
    private RadioButton home; // 表示第一个RadioButton 组件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //初始化界面组件
        init();
        //初始化ViewPager
        initViewPager();


    }
    private void initViewPager() {
        fragment1 = new Fragment_HomePage();
        fragment2 = new Fragment_News();
        fragment3 = new Fragment_Me();
        fragmentLists = new ArrayList<Fragment>();
        fragmentLists.add(fragment1);
        fragmentLists.add(fragment2);
        fragmentLists.add(fragment3);
        //获取FragmentManager对象
        fragmentManager = getSupportFragmentManager();
        //获取FragmentPageAdapter对象
        adapter = new MyFragmentPageAdapter(fragmentManager, fragmentLists);
        //设置Adapter，使ViewPager 与 Adapter 进行绑定
        viewPager.setAdapter(adapter);
        //设置ViewPager默认显示第一个View
        viewPager.setCurrentItem(0);
        //设置第一个RadioButton为默认选中状态
        home.setChecked(true);
        //ViewPager页面切换监听
        viewPager.addOnPageChangeListener(this);
    }

    private void init() {
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        home = (RadioButton) findViewById(R.id.radioButton_home_page);
        //RadioGroup状态改变监听
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radioButton_home_page: // 首页
                //显示第一个Fragment并关闭动画效果
                viewPager.setCurrentItem(0,false);
                break;
            case R.id.radioButton_news: // 团购
                viewPager.setCurrentItem(1,false);
                break;
            case R.id.radioButton_me: // 发现
                viewPager.setCurrentItem(2,false);
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
                radioGroup.check(R.id.radioButton_home_page);
                break;
            case 1:
                radioGroup.check(R.id.radioButton_news);
                break;
            case 2:
                radioGroup.check(R.id.radioButton_me);
                break;
        }
    }

    //双击退出
    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }


}

