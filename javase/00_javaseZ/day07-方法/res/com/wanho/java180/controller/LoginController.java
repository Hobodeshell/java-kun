package com.wanho.java180.controller;


import com.wanho.java180.util.AlertUtil;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField accountTxf ;
    @FXML
    private PasswordField pswdPw ;
    /**
     * 用户登录
     */
    public void login() {
        //获得用户输入的账号  密码
        String account = accountTxf.getText();
        String pswd = pswdPw.getText();
        //假设 已知系统存在： 账号  admin  密码 123
        //此次 补全 判断逻辑
        boolean isLoginSuc = true ;
        if (isLoginSuc) {
            AlertUtil.alertInfo("登录成功！！！");
            return;
        }
        AlertUtil.alertError("账号或密码错误！！！");
    }
}
