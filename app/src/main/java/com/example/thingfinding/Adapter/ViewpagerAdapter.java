package com.example.thingfinding.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.thingfinding.Fragment.Fragment_Me;
import com.example.thingfinding.user.CHANNEL;
import com.example.thingfinding.user.Fragment_Transaction;
import com.example.thingfinding.user.Fragment_Transaction_four;
import com.example.thingfinding.user.Fragment_Transaction_three;
import com.example.thingfinding.user.Fragment_Transaction_two;


public class ViewpagerAdapter extends FragmentPagerAdapter {

    private CHANNEL[] mList;

    public ViewpagerAdapter(FragmentManager fm,  CHANNEL[] datas) {
        super(fm);
        mList = datas;
    }

    //初始化对应的fragment
    @Override
    public Fragment getItem(int position) {
        String  type = mList[position].getKey();
        switch (type) {
            case "全部订单":
                return Fragment_Transaction.newInstance("全部订单");
            case "客户选择中":
                return Fragment_Transaction_two.newInstance("客户选择中");
            case "交易成功":
                return Fragment_Transaction_three.newInstance("交易成功");
            case "交易失败":
                return Fragment_Transaction_four.newInstance("交易失败");
        }
        return null;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : (mList.length-1);
    }
}
