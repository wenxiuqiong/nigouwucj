package com.example.thingfinding.Bean;

/**
 * Created by Lee on 2019/9/14
 */
public class CommonResultBean<T> {
    private String code;
    private T data;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

}
