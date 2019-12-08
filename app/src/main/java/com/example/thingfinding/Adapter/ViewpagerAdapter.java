package com.example.thingfinding.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.thingfinding.user.Fragment_Transaction;


public class ViewpagerAdapter extends FragmentPagerAdapter {

    private String[] mList;

    public ViewpagerAdapter(FragmentManager fm, String[] datas) {
        super(fm);
        mList = datas;
    }

    //初始化对应的fragment
    @Override
    public Fragment getItem(int position) {
        String type = mList[position];
        switch (type) {
            case "全部订单":
                return Fragment_Transaction.newInstance("全部订单");
            case "客户选择中":
                return Fragment_Transaction.newInstance("客户选择中");
            case "交易成功":
                return Fragment_Transaction.newInstance("交易成功");
            case "交易失败":
                return Fragment_Transaction.newInstance("交易失败");
        }
        return null;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.length;
    }
}
