package com.example.weixin.mouldMessage;

import java.util.Map;

public class Data {
    private Map<String,String> first;
    private Map<String,String> company;
    private Map<String,String> time;
    private Map<String,String> result;
    private Map<String,String> remark;

    public Data(Map<String, String> first, Map<String, String> company, Map<String, String> time, Map<String, String> result, Map<String, String> remark) {
        this.first = first;
        this.company = company;
        this.time = time;
        this.result = result;
        this.remark = remark;
    }

    public Map<String, String> getFirst() {
        return first;
    }

    public void setFirst(Map<String, String> first) {
        this.first = first;
    }

    public Map<String, String> getCompany() {
        return company;
    }

    public void setCompany(Map<String, String> company) {
        this.company = company;
    }

    public Map<String, String> getTime() {
        return time;
    }

    public void setTime(Map<String, String> time) {
        this.time = time;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }

    public Map<String, String> getRemark() {
        return remark;
    }

    public void setRemark(Map<String, String> remark) {
        this.remark = remark;
    }
}
