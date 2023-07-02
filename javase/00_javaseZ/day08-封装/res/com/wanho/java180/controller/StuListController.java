package com.wanho.java180.controller;

import com.wanho.java180.entity.Stu;
import com.wanho.java180.model.StuModel;
import com.wanho.java180.service.StuService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class StuListController implements Initializable {
    @FXML
    private TextField snameTxf ;
    @FXML
    private TextField addressTxf ;
    @FXML
    private TableView<StuModel> stuTableView ;
    @FXML
    private TableColumn<StuModel,String> sidTabCol ;
    @FXML
    private TableColumn<StuModel,String> snameTabCol ;
    @FXML
    private TableColumn<StuModel,String> ageTabCol ;
    @FXML
    private TableColumn<StuModel,String> sexTabCol ;
    @FXML
    private TableColumn<StuModel,String> addressTabCol ;
    /**
     * 界面视图 观察者模式：
     */
    private ObservableList<StuModel> dataList = FXCollections.observableArrayList();

    private StuService stuService = new StuService() ;

    /**
     * 点击 界面 搜索按钮执行的代码逻辑
     */
    public void search() {
        //获得 用户输入的关键字
        String customerName = snameTxf.getText();
        String customerDept = addressTxf.getText();

    }

    /**
     * 填充数据到界面中
     * @param stuAy
     */
    private void fillData2ListView(Stu[] stuAy) {
        //先清空  界面中数据
        dataList.clear();
        //循环数组 存放数据到观察者集合中
        for (int i = 0; i < stuAy.length; i++) {
            dataList.add(new StuModel(stuAy[i])) ;
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stu[] stuAy = stuService.findAll();
        fillData2ListView(stuAy);
        //绑定数据到view中
        sidTabCol.setCellValueFactory(new PropertyValueFactory<>("sid"));
        snameTabCol.setCellValueFactory(new PropertyValueFactory<>("sname"));
        ageTabCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        sexTabCol.setCellValueFactory(new PropertyValueFactory<>("sex"));
        addressTabCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        stuTableView.setItems(dataList);
    }
}
