package com.wanho.java180.controller;

import com.wanho.java180.util.AlertUtil;
import com.wanho.java180.util.ViewUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Param2AddStuController {
    @FXML
    private TextField sidTxf ;
    @FXML
    private TextField snameTxf ;


    /**
     * 添加学生
     */
    public void add() {
        //获得 用户输入的 2 个数字
        String sid = sidTxf.getText();
        String sname = snameTxf.getText();

        //调用业务方法进行新增功能

        //如果新增成功  跳转到学生列表页面
        boolean isAddSuc = true ;
        if (isAddSuc) {
            ViewUtil.show("/com/wanho/java180/view/student-list.fxml");
            return ;
        }
        AlertUtil.alertError("新增学生["+sid+"]已存在,请检查！！！");
    }
}
