package com.wanho.java180.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable{
    @FXML
    private AnchorPane mainPane ;
    @FXML
    private BarChart<String,Number> bc ;
    @FXML
    private NumberAxis yAxis ;
    @FXML
    private CategoryAxis xAxis ;

    private ObservableList<Node> observableList ;

    public void showDept() throws IOException {
        AnchorPane deptPane = FXMLLoader.load(getClass().getResource("/com/wanho/java180/view/dept.fxml"));
        observableList.clear() ;
        observableList.add(deptPane) ;
        System.out.println("显示部门");
    }

    public void showEmp() throws IOException {
        AnchorPane deptPane = FXMLLoader.load(getClass().getResource("/com/wanho/java180/view/emp.fxml"));
        observableList.clear() ;
        observableList.add(deptPane) ;
        System.out.println("显示员工");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.observableList = mainPane.getChildren();
        bc.setTitle("部门职位人数统计柱状图表");//设置图的标题
        xAxis.setLabel("部门/职位");//设置x轴标签

        yAxis.setLabel("人数");//设置y轴标签
        /////////////////////////////////////////////
        // 此次：扩展功能  针对有基础的部分同学  对emp.txt 文件进行数据统计

        //目前此次为伪数据 直接怼死
        XYChart.Series series1 = new XYChart.Series();//创建数据序列1
        series1.setName("武将");//序列标识

        series1.getData().add(new XYChart.Data("魏",4 ));//设置数据序列值
        series1.getData().add(new XYChart.Data("蜀",6));//设置数据序列值
        series1.getData().add(new XYChart.Data("吴", 3));//设置数据序列值


        XYChart.Series series2 = new XYChart.Series();//创建数据序列2

        series2.setName("军师");//序列标识
        series2.getData().add(new XYChart.Data("魏",3 ));//设置数据序列值
        series2.getData().add(new XYChart.Data("蜀",4));//设置数据序列值
        series2.getData().add(new XYChart.Data("吴", 5));//设置数据序列值

        XYChart.Series series3 = new XYChart.Series();//创建数据序列2

        series3.setName("副将");//序列标识
        series3.getData().add(new XYChart.Data("魏",3 ));//设置数据序列值
        series3.getData().add(new XYChart.Data("蜀",2));//设置数据序列值
        series3.getData().add(new XYChart.Data("吴", 4));//设置数据序列值
        bc.getData().addAll(series1, series2,series3);//柱状图对象上挂载3个数据序列
    }
}
