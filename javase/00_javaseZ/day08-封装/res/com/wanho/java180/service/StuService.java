package com.wanho.java180.service;

import com.wanho.java180.entity.Stu;

public class StuService {
    private static Stu[] stuAy = {
            new Stu("S1","张三",19,0,"南京市") ,
            new Stu("S2","李四",18,1,"北京市") ,
            new Stu("S3","王五",20,0,"上海市") ,
            new Stu("S4","赵六") ,
            new Stu("S5","钱七",20) ,
            new Stu("S7","老王",20,0)
    };

    public Stu[] findAll(){
        return stuAy ;
    }
}
