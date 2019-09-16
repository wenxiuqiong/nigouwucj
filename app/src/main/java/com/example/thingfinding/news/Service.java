package com.example.thingfinding.news;

import android.util.Xml;

import com.example.thingfinding.ItemInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Service {

    public static List<NewsInfo> getInfoJson(InputStream is) throws IOException {
        byte[] buffer = new byte[is.available()];
        is.read(buffer);
        String json = new String(buffer, "utf-8");
        Gson gson = new Gson();
        Type listType = new TypeToken<List<NewsInfo>>() {
        }.getType();
        List<NewsInfo> info = gson.fromJson(json, listType);
        return info;
    }

}
