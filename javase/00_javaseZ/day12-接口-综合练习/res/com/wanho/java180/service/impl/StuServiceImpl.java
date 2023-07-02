package com.wanho.java180.service.impl;

import com.wanho.java180.entity.Stu;
import com.wanho.java180.service.StuService;

import static com.wanho.java180.constant.SexType.BOY;
import static com.wanho.java180.constant.SexType.GIRL;

public class StuServiceImpl implements StuService {
    private static Stu[] stuAy = {
            new Stu("S1","张三",19, BOY,"南京市") ,
            new Stu("S2","李四",18,GIRL,"北京市") ,
            new Stu("S3","王五",20,BOY,"上海市") ,
            new Stu("S4","赵六",18,GIRL,"北京市") ,
            new Stu("S5","钱七",20,GIRL,"北京市") ,
            new Stu("S7","老王",20,BOY,"上海市")
    };

    /**
     * 查询所有
     * @return
     */
    public Stu[] findAll(){
        return stuAy ;
    }
}
