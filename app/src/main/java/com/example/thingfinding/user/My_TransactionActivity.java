package com.example.thingfinding.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.thingfinding.BaseActivity;
import com.example.thingfinding.Fragment.MyFragmentPageAdapter;
import com.example.thingfinding.R;

import java.util.ArrayList;
import java.util.List;

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
    private MyFragmentPageAdapter adapter;
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
        fragment1 = new Fragment_Transaction();
        fragment2 = new Fragment_Transaction_two();
        fragment3 = new Fragment_Transaction_three();
        fragment4 = new Fragment_Transaction_four();
        fragmentLists = new ArrayList<Fragment>();
        fragmentLists.add(fragment1);
        fragmentLists.add(fragment2);
        fragmentLists.add(fragment3);
        fragmentLists.add(fragment4);
        //获取FragmentManager对象
        fragmentManager = getSupportFragmentManager();
        //获取FragmentPageAdapter对象
        adapter = new MyFragmentPageAdapter(fragmentManager, fragmentLists);
        //设置Adapter，使ViewPager 与 Adapter 进行绑定
        viewPager.setAdapter(adapter);
        //设置ViewPager默认显示第一个View
         viewPager.setCurrentItem(0);
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
