package com.wanho.java180.controller;

import com.wanho.java180.constant.SexType;
import com.wanho.java180.entity.Stu;
import com.wanho.java180.util.AlertUtil;
import com.wanho.java180.util.SidUtil;
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

import static com.wanho.java180.constant.SexType.GIRL;

public class ModifyStuController implements Initializable {
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
    public void modify() {
        //获得 用户输入的 2 个数字
        String sid = sidTxf.getText();
        String sname = snameTxf.getText();
        String ageStr = ageTxf.getText();
        //类型转换
        int age = Integer.parseInt(ageStr) ;
        //默认值 为 男 0
        SexType sex = SexType.BOY ;
        //如果 女被选中： 性别值为 1
        if (girlRadio.isSelected()){
            sex = SexType.GIRL ;
        }
        //获得住址
        String address = addressCombox.getSelectionModel().getSelectedItem();
        //调用业务方法进行新增功能

        //如果新增成功  跳转到学生列表页面
        boolean isAddSuc = true ;
        if (isAddSuc) {
            ViewUtil.show("/com/wanho/java180/view/stu-list.fxml");
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

        //1. 获得 学号信息
        String sid = SidUtil.getId();
        System.out.println("查看学生的学号:" + sid);
        //2. 调用业务方法 根据学号 查询 学生对象信息
        Stu stu = new Stu("S4", "赵六", 18, GIRL, "北京市");
        //3. 渲染数据到界面中
        sidTxf.setText(stu.getSid());
        snameTxf.setText(stu.getSname());
        ageTxf.setText(stu.getAge()+"");
        SexType sex = stu.getSex() ;
        //如果 女被选中： 性别值为 1
        if (sex == SexType.GIRL){
            girlRadio.setSelected(true);
        }
        //住址：
        addressCombox.setValue(stu.getAddress());
    }
}
