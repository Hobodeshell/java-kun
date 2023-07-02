package com.wanho.java180.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CalculatorController {
    @FXML
    private TextField num1Txf ;
    @FXML
    private TextField num2Txf ;
    @FXML
    private TextField rsTxf ;

    /**
     * 加法
     */
    public void add() {
        //获得 用户输入的 2 个数字
        String num1Str = num1Txf.getText();
        String num2Str = num2Txf.getText();
        //用户输入的一切都是字符串  此次需要类型转换
        double num1 = Double.parseDouble(num1Str) ;
        double num2 = Double.parseDouble(num2Str) ;
        //进行加法计算
        double rs = num1+num2 ;
        //进行数据回显  此次 +"" 把double 和字符串进行拼接 实现类型转换为字符串
        rsTxf.setText(rs+"");
    }

    /**
     * 减法
     */
    public void subtract() {
    }

    /**
     * 乘法
     */
    public void multiply() {
    }

    /**
     * 除法
     */
    public void divide() {
    }
}
