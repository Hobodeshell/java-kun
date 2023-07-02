package com.wanho.java180.entity;

import java.util.Objects;

/**
 * 封装 com/wanho/java180/data/dept.txt 一行记录
 */
public class Dept {
    /**部门编号 唯一标识*/
    private Integer deptno ;
    /**部门名称*/
    private String dname ;
    /**部门所在地*/
    private String loc ;

    public Dept() {
    }

    public Dept(Integer deptno) {
        this.deptno = deptno;
    }

    public Dept(String dname) {
        this.dname = dname;
    }

    public Dept(Integer deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dept dept = (Dept) o;
        return Objects.equals(deptno, dept.deptno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptno);
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
