package com.zjl.codetool.util;

import java.io.Serializable;


public class ZlJson<T> implements Serializable {

    private static final long serialVersionUID = -2348005848540290635L;

    /**返回数据*/
    private T data;

    private Integer code;

    private String message;


    public ZlJson(){
        this.code = 200;
        this.message = "成功";
    }


    public ZlJson(T data) {
        this.code = 200;
        this.message = "成功";
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