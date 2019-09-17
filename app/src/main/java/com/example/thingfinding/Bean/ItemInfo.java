package com.example.thingfinding.Bean;

import android.graphics.Bitmap;

import java.io.Serializable;

public class ItemInfo implements Serializable {
    private String id;
    private String username;
    private String password;
    private String information;
    private String time;
    private byte[] image;
    private boolean isShow; // 是否显示CheckBox
    private boolean isChecked; // 是否选中CheckBox
    private Bitmap bitmap;

    public ItemInfo(){
    }
    public ItemInfo(String name, byte[] image){
        this.username=name;
        this.image=image;
    }

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }

    public String getUserName(){
        return username;
    }
    public void setUserName(String username){
        this.username=username;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }

    public byte[] getImage(){
        return image;
    }
    public void setImage(byte[] image){
        this.image=image;
    }

    public void setTime(String time){
        this.time=time;
    }
    public String getTime(){
        return time;
    }
    public void setInformation(String information){
        this.information=information;
    }
    public String getInformation(){
        return information;
    }

    public void setBitmap(Bitmap bitmap){
        this.bitmap=bitmap;
    }
    public Bitmap getBitmap(){
        return bitmap;
    }

    public boolean isShow() {
        return isShow;
    }
    public void setShow(boolean isShow) {
        this.isShow = isShow;
    }
    public boolean isChecked() {
        return isChecked;
    }
    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
    public ItemInfo(String information,String time,Bitmap bitmap, boolean isShow, boolean isChecked) {
        super();
        this.information = information;
        this.time=time;
        this.bitmap=bitmap;
        this.isShow = isShow;
        this.isChecked = isChecked;
    }
}
