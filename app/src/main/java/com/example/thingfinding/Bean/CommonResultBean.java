package com.example.thingfinding.Bean;

/**
 * Created by Lee on 2019/9/14
 */
public class CommonResultBean<T> {
    private String code;
    private T data;
    private String message;
    private String type;
    private String count;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

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
