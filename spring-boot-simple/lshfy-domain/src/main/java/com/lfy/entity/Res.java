package com.lfy.entity;

import lombok.Data;

@Data
public class Res {

    private Integer code;

    private Object data;

    private String message;

    public void setSuccess(Object data, String message){
        this.code = 200;
        this.data = data;
        this.message = message;
    }

    public void setSuccess(Object data){
        this.code = 200;
        this.data = data;
        this.message = "成功";
    }

    public void setFail(String message){
        this.code = 500;
        this.message = message;
    }

    public void setFail(String message, Integer code){
        this.code = code;
        this.message = message;
    }
}
