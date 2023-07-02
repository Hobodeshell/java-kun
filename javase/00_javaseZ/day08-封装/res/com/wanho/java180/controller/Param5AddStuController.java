package com.wanho.java180.controller;

import com.wanho.java180.util.AlertUtil;
import com.wanho.java180.util.ViewUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class Param5AddStuController implements Initializable {
    private ToggleGroup group = new ToggleGroup()  ;
    @FXML
    private TextField sidTxf ;
    @FXML
    private TextField snameTxf ;
    @FXML
    private TextField ageTxf ;
    @FXML
    private RadioButton boyRadio ;
    @FXML
    private RadioButton girlRadio ;
    @FXML
    private ComboBox<String> addressCombox ;
    /**
     * 界面视图 观察者模式：
     */
    private ObservableList<String> dataList = FXCollections.observableArrayList();


    /**
     * 添加学生
     */
    public void add() {
        //获得 用户输入的 2 个数字
        String sid = sidTxf.getText();
        String sname = snameTxf.getText();
        String ageStr = ageTxf.getText();
        //类型转换
        int age = Integer.parseInt(ageStr) ;
        //默认值 为 男 0
        int sex = 0 ;
        //如果 女被选中： 性别值为 1
        if (girlRadio.isSelected()){
            sex = 1 ;
        }
        //获得住址
        String address = addressCombox.getSelectionModel().getSelectedItem();
        //调用业务方法进行新增功能

        //如果新增成功  跳转到学生列表页面
        boolean isAddSuc = true ;
        if (isAddSuc) {
            ViewUtil.show("/com/wanho/java180/view/student-list.fxml");
            return ;
        }
        AlertUtil.alertError("新增学生["+sid+"]已存在,请检查！！！");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //设置按钮在一个按钮组中
        boyRadio.setToggleGroup(group);
        girlRadio.setToggleGroup(group);
        dataList.add("北京市");
        dataList.add("南京市");
        dataList.add("上海市");
        addressCombox.setItems(dataList);
    }
}
