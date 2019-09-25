package com.example.thingfinding.Util;

import android.graphics.Canvas;
import android.util.Log;

import com.example.thingfinding.Bean.CommonCustomerneedBean;
import com.example.thingfinding.Bean.CommonResultBean;
import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Lee on 2019/9/12
 */
public abstract class BaseCallback<T> {
    public Type mType;
    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    public BaseCallback() {
        mType = getSuperclassTypeParameter(getClass());
    }

    /**
     * 请求之前调用
     */
    public abstract void onRequestBefore();

    /**
     * 请求失败调用（网络问题）
     *
     * @param request
     * @param e
     */
    public abstract void onFailure(Request request, Exception e);

    /**
     * 请求成功而且没有错误的时候调用
     *
     *
     * @param response
     */
    public abstract void onSuccess(CommonResultBean<T> response);

    /**
     * 请求成功但是有错误的时候调用，例如Gson解析错误等
     *
     * @param response
     * @param errorCode
     * @param e
     */
    public abstract void onError(Response response, int errorCode, Exception e);

}
