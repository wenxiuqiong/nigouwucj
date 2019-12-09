package com.example.thingfinding.user;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
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
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.example.thingfinding.Adapter.ViewpagerAdapter;
import com.example.thingfinding.Adapter.transactionAdpter;
import com.example.thingfinding.BaseActivity;
import com.example.thingfinding.Bean.CommonCustomerneedBean;
import com.example.thingfinding.Bean.CommonResultBean;
import com.example.thingfinding.DialogUtil;
import com.example.thingfinding.Fragment.Fragment_HomePage;
import com.example.thingfinding.Fragment.Fragment_Me;
import com.example.thingfinding.Fragment.Fragment_News;
import com.example.thingfinding.Fragment.MyFragmentPageAdapter;
import com.example.thingfinding.R;
import com.example.thingfinding.ScaleTransitionPagerTitleView;
import com.example.thingfinding.Util.BaseCallback;
import com.example.thingfinding.Util.BaseUrl;
import com.example.thingfinding.Util.OkHttpHelp;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class My_TransactionActivity extends BaseActivity{

    //定义FragmentManager , ViewPager.OnPageChangeListener
    private FragmentManager fragmentManager;

    private ViewPager viewPager;
    private List<Fragment> fragmentLists;
    private ViewpagerAdapter adapter;
    private RadioGroup radioGroup;
    private RadioButton whole; // 表示第一个RadioButton 组件
    //指定首页要出现的卡片
    private static final CHANNEL[] CHANNELS =
            new CHANNEL[]{CHANNEL.MY, CHANNEL.DISCORY, CHANNEL.FRIEND,CHANNEL.VIDEO};


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

        adapter=new ViewpagerAdapter(getSupportFragmentManager(),CHANNELS);
        viewPager.setAdapter(adapter);
        initMagicIndicator();

    }

    private void init() {
        initNavBar(true,"我的交易");
        viewPager = (ViewPager) findViewById(R.id.viewPager);

    }


    //初始化指示器
    private void initMagicIndicator() {
        MagicIndicator magicIndicator = findViewById(R.id.magic_indicator);
        magicIndicator.setBackgroundColor(Color.WHITE);
        //官方用法
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            //返回拥有的卡片个数
            public int getCount() {
                return CHANNELS == null ? 0 : CHANNELS.length;
            }

            /**
             * 重要
             * 初始化每个titleview 的效果
             *  滑到该个卡片时，设置卡片的名称，名称的颜色，字体大小，选择状态的颜色
             */
            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                final SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(CHANNELS[index].getKey());
                simplePagerTitleView.setTextSize(19);
                simplePagerTitleView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                simplePagerTitleView.setNormalColor(Color.parseColor("#999999"));//未被选择的颜色
                simplePagerTitleView.setSelectedColor(Color.parseColor("#333333"));//被选择时的颜色
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }

            @Override
            public float getTitleWeight(Context context, int index) {
                return 1.0f;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }


}
