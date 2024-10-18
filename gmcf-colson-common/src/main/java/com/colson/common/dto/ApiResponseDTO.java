package com.colson.common.dto;


public class ApiResponseDTO<T> {
    
    private int code;    // 响应码
    private String msg;  // 响应消息
    private T data;      // 泛型，处理不同类型的响应数据

    // Getter and Setter
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}