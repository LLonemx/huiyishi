package com.example.huiyishi.entity;

import java.util.List;

public class Loginjson<T> {
    int code;
    String message;
    T datas;


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

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }
}
