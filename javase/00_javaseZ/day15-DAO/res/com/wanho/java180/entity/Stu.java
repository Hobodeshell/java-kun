package com.wanho.java180.entity;

import com.wanho.java180.constant.SexType;

public class Stu {
    private String sid ;
    private String sname ;
    private int age ;
    private SexType sex ;
    private String address ;

    public Stu() {
    }

    public Stu(String sid, String sname) {
        this.sid = sid;
        this.sname = sname;
    }

    public Stu(String sid, String sname, int age) {
        this.sid = sid;
        this.sname = sname;
        this.age = age;
    }

    public Stu(String sid, String sname, int age, SexType sex) {
        this.sid = sid;
        this.sname = sname;
        this.age = age;
        this.sex = sex;
    }

    public Stu(String sid, String sname, int age, SexType sex, String address) {
        this.sid = sid;
        this.sname = sname;
        this.age = age;
        this.sex = sex;
        this.address = address;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
