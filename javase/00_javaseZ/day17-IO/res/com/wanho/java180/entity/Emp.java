package com.wanho.java180.entity;

import java.time.LocalDate;

/**
 * 封装 com/wanho/java180/data/emp.txt 一行记录
 */
public class Emp {
    /**员工编号 唯一标识*/
    private Integer empno ;
    /**员工姓名*/
    private String ename ;
    /**员工工种*/
    private String job ;
    /**员工直接领导工号*/
    private Integer mgr ;
    /**员工直接领导对象*/
    private Emp parentEmp ;
    /**员工入职日期*/
    private LocalDate hiredate ;
    /**员工基本工资*/
    private Double sal ;
    /**员工奖金*/
    private Double comm ;
    /**当前员工对应的部门对象*/
    private Dept dept  ;

    public Emp() {
    }

    public Emp(Integer empno, String ename, LocalDate hiredate, Dept dept) {
        this.empno = empno;
        this.ename = ename;
        this.hiredate = hiredate;
        this.dept = dept;
    }

    public Emp(Integer empno, String ename, String job, Integer mgr, LocalDate hiredate, Double sal, Double comm, Dept dept) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.dept = dept;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Emp getParentEmp() {
        return parentEmp;
    }

    public void setParentEmp(Emp parentEmp) {
        this.parentEmp = parentEmp;
    }

    public LocalDate getHiredate() {
        return hiredate;
    }

    public void setHiredate(LocalDate hiredate) {
        this.hiredate = hiredate;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Double getComm() {
        return comm;
    }

    public void setComm(Double comm) {
        this.comm = comm;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", parentEmp=" + parentEmp +
                ", hiredate=" + hiredate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", dept=" + dept +
                '}';
    }
}
