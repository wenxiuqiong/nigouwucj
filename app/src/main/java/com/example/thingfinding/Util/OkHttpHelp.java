package com.example.thingfinding.Util;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.thingfinding.Bean.CommonCustomerneedBean;
import com.example.thingfinding.Bean.CommonResultBean;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lee on 2019/9/12
 */
public class OkHttpHelp {
    private static OkHttpHelp mOkHttpHelperInstance;
    private static OkHttpClient mClientInstance;
    private Handler mHandler;
    private Gson mGson;
    private JSONArray jsonArray;
    private JSONObject jsonObject;
    private JSONObject[] jsonObjects;
    public static final String BASE_URL = "http://192.168.8.109:8080/business/user";
    /**
     * 单例模式，私有构造函数，构造函数里面进行一些初始化
     */
    private OkHttpHelp() {
        mClientInstance = new OkHttpClient();

        mClientInstance.setConnectTimeout(10, TimeUnit.SECONDS);
        mClientInstance.setReadTimeout(10, TimeUnit.SECONDS);
        mClientInstance.setWriteTimeout(30, TimeUnit.SECONDS);
        mGson = new Gson();
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static OkHttpHelp getinstance() {

        if (mOkHttpHelperInstance == null) {

            synchronized (OkHttpHelp.class) {
                if (mOkHttpHelperInstance == null) {
                    mOkHttpHelperInstance = new OkHttpHelp();
                }
            }
        }
        return mOkHttpHelperInstance;
    }

    /**
     * 封装一个request方法，不管post或者get方法中都会用到
     */
    public JSONArray request(final Request request, final BaseCallback callback) {

        //在请求之前所做的事，比如弹出对话框等
        callback.onRequestBefore();

        mClientInstance.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                //返回失败
                callbackFailure(request, callback, e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    //返回成功回调
                    String resString = response.body().string();
                    /*if (callback.mType == String.class) {
                        //如果我们需要返回String类型
                    } else {*/
                        //如果返回的是其他类型，则利用Gson去解析
                       try {
                           CommonResultBean<Object> obj = mGson.fromJson(resString,CommonResultBean.class);
                           callbackSuccess(obj, callback);

                        } catch (JsonParseException e) {
                            callbackError(response, callback, e);
                        }
                    //}

                } else {
                    //返回错误
                    //callbackError(response, callback, null);

                }
            }
        });
        /**setJsonArray(jsonArray);
        int size = jsonArray.size();
        jsonObjects=new JSONObject[size];
        for (int i = 0; i < size; i++) {
            jsonObject = jsonArray.getJSONObject(i);
            System.out.println("用户名:  " + jsonObject.getString("customerUserName"));
            jsonObjects[i]=jsonObject;
        }
        getJsonArray();*/
        return jsonArray;
    }

    /**
     * 在主线程中执行的回调
     *
     *
     * @param
     * @param callback
     */
    private void callbackSuccess(final CommonResultBean<Object> obj, final BaseCallback callback) {
        System.out.println("成功转型："+obj);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(obj);
            }
        });
    }

    /**
     * 在主线程中执行的回调
     * @param response
     * @param callback
     * @param e
     */
    private void callbackError(final Response response, final BaseCallback callback, final Exception e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(response, response.code(), e);
            }
        });
    }

    /**
     * 在主线程中执行的回调
     * @param request
     * @param callback
     * @param e
     */
    private void callbackFailure(final Request request, final BaseCallback callback, final Exception e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onFailure(request, e);
            }
        });
    }

    /**
     * 对外公开的get方法
     *
     * @param url
     * @param callback
     */
    public void get(String url, BaseCallback callback) {
        Request request = buildRequest(url, null, HttpMethodType.GET);
        request(request, callback);
    }

    /**
     * 对外公开的post方法
     *
     * @param url
     * @param params
     * @param callback
     */
    public JSONArray post(String url, Map<String, String> params, BaseCallback callback) {
        Request request = buildRequest(url, params, HttpMethodType.POST);
        return request(request, callback);
    }

    /**
     * 构建请求对象
     *
     * @param url
     * @param params
     * @param type
     * @return
     */
    private Request buildRequest(String url, Map<String, String> params, HttpMethodType type) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (type == HttpMethodType.GET) {
            builder.get();
        } else if (type == HttpMethodType.POST) {
            builder.post(buildRequestBody(params));
        }
        return builder.build();
    }

    /**
     * 通过Map的键值对构建请求对象的body
     *
     * @param params
     * @return
     */
    private RequestBody buildRequestBody(Map<String, String> params) {

        FormEncodingBuilder builder = new FormEncodingBuilder();

        if (params != null) {
            for (Map.Entry<String, String> entity : params.entrySet()) {
                builder.add(entity.getKey(), entity.getValue());
            }
        }
        return builder.build();
    }

    /**
     * 这个枚举用于指明是哪一种提交方式
     */
    enum HttpMethodType {
        GET,
        POST
    }
    public void setJsonArray(JSONArray jsonArray) {
        System.out.println("它的长度为0："+jsonArray.size());
        this.jsonArray=jsonArray;
    }

    public JSONArray getJsonArray() {
        System.out.println("它的长度为1："+jsonArray.size());
        return this.jsonArray;
    }
}
