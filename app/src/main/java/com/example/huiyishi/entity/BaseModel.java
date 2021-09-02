package com.example.huiyishi.entity;

import java.util.List;

public class BaseModel<T> {
    int code;
    String message;
    List<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /*本项目仅供参考学习使用*/
    /*开发者联系方式qq：1459016889 林先生*/
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
