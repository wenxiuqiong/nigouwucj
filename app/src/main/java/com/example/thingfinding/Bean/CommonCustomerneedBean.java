package com.example.thingfinding.Bean;

/**
 * Created by Lee on 2019/9/17
 */
public class CommonCustomerneedBean {
    private String customerUserName;
    private String beginTime;
    private String endTime;
    private String customerAddress;
    private String sentense;
    public String getCustomerUserName() {
        return customerUserName;
    }

    public void setCustomerUserName(String customerUserName) {
        this.customerUserName = customerUserName;
    }

    public String getBeginDate() {
        return beginTime;
    }

    public void setBeginDate(String beginDate) {
        this.beginTime = beginDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getSentense() {
        return sentense;
    }

    public void setSentense(String sentense) {
        this.sentense = sentense;
    }
}
