package com.example.huiyishi.entity;

import com.google.gson.annotations.Expose;

public class UserCourse {
    private Integer employeeId;
    //员工姓名
    private String employeeName;
    //用户名
    private String username;
    //电话
    private String phone;
    //电子邮件
    private String email;
    //状态（0表示未审批  1表示审批通过 2表示审批未通过）
    private Integer status;
    //部门编号
    private Integer departmentId;
    //密码
    private String password;
    //角色（1表示普通用户  2表示管理员）
    private Integer role;
    /*本项目仅供参考学习使用*/
    /*开发者联系方式qq：1459016889 林先生*/
    //所属部门
    private String department;


    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
