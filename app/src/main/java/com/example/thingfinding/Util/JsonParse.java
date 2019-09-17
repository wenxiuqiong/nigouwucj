package com.example.thingfinding.Util;

import com.example.thingfinding.Bean.userBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Lee on 2019/9/10
 */
public class JsonParse {
    public static List<userBean> getuserInfo(String json){
        Gson gson=new Gson();
        Type listType=new TypeToken<List<userBean>>(){}.getType();
        List<userBean> userinfos=gson.fromJson(json,listType);
        return userinfos;
    }
}
