package com.webucation.pojo.faxuan;

public class FaxuanResult {
    private int code;
    private String msg;
    private FaxuanUserInfo data;

    @Override
    public String toString() {
        return "FaxuanResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public FaxuanUserInfo getData() {
        return data;
    }

    public void setData(FaxuanUserInfo data) {
        this.data = data;
    }
}
