package com.wanho.java180.controller;

import com.wanho.java180.entity.Customer;
import com.wanho.java180.model.CustomerModel;
import com.wanho.java180.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class CustomerPlusController {
    @FXML
    private TextField customerNameTxf ;
    @FXML
    private TextField customerDeptTxf ;
    @FXML
    private TableView<CustomerModel> customerTableView ;
    @FXML
    private TableColumn<CustomerModel,String> customerNameTabCol ;
    @FXML
    private TableColumn<CustomerModel,String> customerDeptTabCol ;
    /**
     * 界面视图 观察者模式：
     */
    private ObservableList<CustomerModel> datList = FXCollections.observableArrayList();


    /**
     * 点击 界面 搜索按钮执行的代码逻辑
     */
    public void search() {
        //获得 用户输入的关键字
        String customerName = customerNameTxf.getText();
        String customerDept = customerDeptTxf.getText();
        //根据关键字 获得 包含关键字的顾客姓名 放在数组中

        //此次为 伪码
        Customer customer1 = new Customer() ;
        customer1.name = "乔峰" ;
        customer1.dept = "丐帮帮主" ;

        Customer customer2 = new Customer() ;
        customer2.name = "段誉" ;
        customer2.dept = "大理王子" ;

        Customer customer3 = new Customer() ;
        customer3.name = "灭绝师太" ;
        customer3.dept = "峨眉" ;
        Customer[] customerAy = {customer1,customer2,customer3} ;
        //填充数据到界面中
        fillData2ListView(customerAy);
    }


    /**
     * 点击 界面 添加按钮执行的代码逻辑
     */
    public void add() {
        //获得 用户输入的关键字
        String customerName = customerNameTxf.getText();
        String customerDept = customerDeptTxf.getText();
        //非空判断
        if ("".equals(customerName)) {
            AlertUtil.alertInfo("顾客姓名不能为空！！！");
            return;
        }
        if ("".equals(customerDept)) {
            AlertUtil.alertInfo("顾客部门不能为空！！！");
            return;
        }
        //输出新增的顾客姓名
        System.out.println("新增的顾客姓名:" + customerName+",顾客部门信息:"+customerDept);
        //把数据存放在数组中

        //要求顾客姓名不能重复

        boolean isAddSuc = true ;
        //如果失败  提示错误消息
        if (isAddSuc) {
            //清空输入框内容
            customerNameTxf.setText("");
            customerDeptTxf.setText("");
            //更新视图
            Customer customer = new Customer() ;
            customer.name = customerName ;
            customer.dept = customerDept ;
            datList.add(new CustomerModel(customer)) ;
            return;
        }
        AlertUtil.alertError("新增顾客["+customerName+"]失败！！！");
    }

    /**
     * 点击 界面 删除按钮执行的代码逻辑
     */
    public void delete() {
        //获得选中的数据
        int idx = customerTableView.getSelectionModel().getSelectedIndex();
        //如果没有选中
        if (idx==-1) {
            AlertUtil.alertError("请选中要删除的顾客！！！");
            return;
        }
        //删除顾客信息
        System.out.println("被选中删除的顾客索引:" + idx);
        //从数组中删除被选中的顾客 [此次需要 代码进行补全]
        boolean isDelSuc = true ;

        //删除成功 更新视图
        if (isDelSuc) {
            datList.remove(idx);
            return;
        }

        //删除失败  提示错误信息
        AlertUtil.alertError("删除失败！！！");
    }


    /**
     * 填充数据到界面中
     * @param customerAy
     */
    private void fillData2ListView(Customer[] customerAy) {
        //先清空  界面中数据
        datList.clear();
        //循环数组 存放数据到观察者集合中
        for (int i = 0; i < customerAy.length; i++) {
            datList.add(new CustomerModel(customerAy[i])) ;
        }
        //绑定数据到view中
        customerNameTabCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerDeptTabCol.setCellValueFactory(new PropertyValueFactory<>("dept"));
        customerTableView.setItems(datList);
    }
}
