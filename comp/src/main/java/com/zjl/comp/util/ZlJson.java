package com.zjl.comp.util;

import java.io.Serializable;


public class ZlJson<T> implements Serializable {

    private static final long serialVersionUID = -2348005848540290635L;

    /**返回数据*/
    private T data;

    private Integer code;

    private String message;


    public ZlJson(){
        ResultCode r = ResultCode.SUCCESS;
        this.code = r.getCode();
        this.message = r.getMessage();
    }

    public ZlJson(ResultCode r,T data) {
        this.code = r.getCode();
        this.message = r.getMessage();
        this.data=data;
    }

    public ZlJson(ResultCode r) {
        this.code = r.getCode();
        this.message = r.getMessage();
    }

    public ZlJson(T data) {
        ResultCode r = ResultCode.SUCCESS;
        this.code = r.getCode();
        this.message = r.getMessage();
        this.data=data;
    }


    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}