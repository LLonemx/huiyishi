package com.example.huiyishi.sqlcourse;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Unique;

import java.io.Serializable;

public class sqluser extends SugarRecord implements Serializable{
    @Unique
    private long useId;
    //员工姓名
    private String employeeName;
    //用户名
    private String username;
    //电话
    private String phone;
    //电子邮件
    private String email;
    //密码
    private String password;
    //部门编号
    private String depart;

    private int employeeid;

    /*本项目仅供参考学习使用*/
    /*开发者联系方式qq：1459016889 林先生*/

public sqluser(){}

public sqluser(String username,String password,String employeeName,String depart,String phone,String email,int employeeid){
    this.username=username;
    this.password=password;
    this.employeeName=employeeName;
    this.depart=depart;
    this.phone=phone;
    this.email=email;
    this.employeeid=employeeid;
}


    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }
}
