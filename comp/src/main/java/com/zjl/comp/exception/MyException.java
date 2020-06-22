package com.zjl.comp.exception;


import com.zjl.comp.util.ResultCode;

import java.io.Serializable;

/**
 * 自定义异常
 * user zjl
 * date 2019/12/13
 */
public class MyException extends RuntimeException implements Serializable {

    private Integer code;

    private String message;


    public MyException(){
        ResultCode resultCode = ResultCode.COMMON_FAIL;
        this.code=resultCode.getCode();
        this.message=resultCode.getMessage();
    }

    /**
     * 自定义异常信息
     * @param message
     */
    public MyException(String message) {
        this.code=999;
        this.message=message;
    }

    /**
     * 自定义异常信息及错误码
     * @param code
     * @param message
     */
    public MyException(Integer code, String message) {
        this.code=code;
        this.message=message;
    }

    public MyException(ResultCode resultCode) {
        this.code=resultCode.getCode();
        this.message=resultCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
