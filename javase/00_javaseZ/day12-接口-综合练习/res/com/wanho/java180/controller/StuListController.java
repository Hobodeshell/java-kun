package com.wanho.java180.controller;

import com.wanho.java180.entity.Stu;
import com.wanho.java180.model.StuModel;
import com.wanho.java180.service.StuService;
import com.wanho.java180.service.impl.StuServiceImpl;
import com.wanho.java180.util.AlertUtil;
import com.wanho.java180.util.SidUtil;
import com.wanho.java180.util.ViewUtil;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class StuListController implements Initializable {
    @FXML
    private TextField snameTxf;
    @FXML
    private TextField addressTxf;
    @FXML
    private TableView<StuModel> stuTableView;
//    @FXML
//    private TableColumn<StuModel, String> sidTabCol;
    @FXML
    private TableColumn<StuModel, CheckBox> sidChkTabCol;
    @FXML
    private TableColumn<StuModel, String> snameTabCol;
    @FXML
    private TableColumn<StuModel, String> ageTabCol;
    @FXML
    private TableColumn<StuModel, String> sexTabCol;
    @FXML
    private TableColumn<StuModel, String> addressTabCol;
    /**
     * 界面视图 观察者模式：
     */
    private ObservableList<StuModel> dataList = FXCollections.observableArrayList();
    /**
     * 存放 被选中的学号数组
     */
    private List<String> sidList = new ArrayList<>() ;

    /**
     * 创建业务对象  使用多态  实现
     */
    private StuService stuService = new StuServiceImpl();

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
     *
     * @param stuAy
     */
    private void fillData2ListView(Stu[] stuAy) {
        //先清空  界面中数据
        dataList.clear();
        //循环数组 存放数据到观察者集合中
        for (int i = 0; i < stuAy.length; i++) {
            dataList.add(new StuModel(stuAy[i]));
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stu[] stuAy = stuService.findAll();
        fillData2ListView(stuAy);

        //绑定数据到view中
        //sidChkTabCol.setCellValueFactory(new PropertyValueFactory<>("choose"));
        sidChkTabCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StuModel,CheckBox>, ObservableValue<CheckBox>>() {

            @Override
            public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<StuModel, CheckBox> param) {
                StuModel stuModel = param.getValue();
                CheckBox is_select = new CheckBox(stuModel.getSid());
                return new ReadOnlyObjectWrapper<CheckBox>(is_select);
            }
        });
        sidChkTabCol.setCellFactory(new Callback<TableColumn<StuModel, CheckBox>, TableCell<StuModel, CheckBox>>() {
            @Override
            public TableCell<StuModel, CheckBox> call(TableColumn<StuModel, CheckBox> param) {
                TableCell<StuModel, CheckBox> tableCell = new TableCell<StuModel, CheckBox>(){
                    @Override
                    protected void updateItem(CheckBox item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty){
                            setGraphic(null);
                        }else {
                            item.setOnAction(e->{
                                if (item.isSelected()) {
                                    sidList.add(item.getText()) ;
                                }else{
                                    sidList.remove(item.getText()) ;
                                }
                            });
                            setGraphic(item);
                        }
                    }
                };
                return tableCell;
            }
        }
       );

        //sidTabCol.setCellValueFactory(new PropertyValueFactory<>("sid"));
        snameTabCol.setCellValueFactory(new PropertyValueFactory<>("sname"));
        ageTabCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        sexTabCol.setCellValueFactory(new PropertyValueFactory<>("sex"));
        addressTabCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        stuTableView.setItems(dataList);
    }

    public void del() {
        //获得 选中的学号数组
        String[] sidAy = sidList.toArray(new String[]{});
        if (sidAy.length==0){
            AlertUtil.alertError("请先选中要删除的记录！！！");
            return;
        }
        System.out.println("要删除的学生是:" + Arrays.toString(sidAy));
    }

    public void view() {
        //获得选中的数据
        int idx = stuTableView.getSelectionModel().getSelectedIndex();
        //如果没有选中
        if (idx==-1) {
            AlertUtil.alertError("请选中要修改的学生信息！！！");
            return;
        }
        //获得学号
        String sid = dataList.get(idx).getSid();
        //要修改的学号是:
        System.out.println("要修改的学号是:" + sid);
        SidUtil.setId(sid);
        ViewUtil.show("/com/wanho/java180/view/modify-stu.fxml");
    }

    public void add() {
        ViewUtil.show("/com/wanho/java180/view/add-stu.fxml");
    }
}
