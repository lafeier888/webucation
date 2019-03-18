package com.webucation.pojo;

public class Result {
    Boolean success = null;
    Object data = null;

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", data=" + data +
                '}';
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
