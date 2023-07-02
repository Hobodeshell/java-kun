package com.wanho.java180.controller;

import com.wanho.java180.entity.Dept;
import com.wanho.java180.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class DeptController implements Initializable {
    // 表单
    @FXML
    private Label infoLab ;
    @FXML
    private TextField deptnoTxf ;
    @FXML
    private TextField dnameTxf ;
    @FXML
    private TextField locTxf ;
    @FXML
    private TextField hiddenIdx ;
    // 表格
    @FXML
    private TableColumn<Dept,Integer> deptTabCol ;
    @FXML
    private TableColumn<Dept,Integer> dnameTabCol ;
    @FXML
    private TableColumn<Dept,Integer> locTabCol ;
    @FXML
    private TableView<Dept> deptTableView ;

    // 观察者模式
    private ObservableList<Dept> dataList = FXCollections.observableArrayList() ;

    /**
     * 一个界面 同时实现 新增 和 修改
     */
    public void submit() {
        //获得用户输入的内容 并 去掉首尾空格
        String deptno = deptnoTxf.getText().trim();
        String dname = dnameTxf.getText().trim();
        String loc = locTxf.getText().trim();
        //封装 dept 对象
        Dept dept = new Dept();
        dept.setDeptno(Integer.parseInt(deptno));
        dept.setDname(dname);
        dept.setLoc(loc);
        //判断 部门编号是否为禁用状态
        boolean disabled = deptnoTxf.isDisabled();
        //修改 【点击按钮 部门编号禁用 为修改的业务逻辑】
        if (disabled){
            System.out.println("add部门信息："+dept);
            /////////////////////////////////////
            //此次 调用业务方法进行 修改部门信息
            boolean updateRs = true ;
            if (updateRs){
                //更新监听模型
                int idx = Integer.parseInt(hiddenIdx.getText().trim()) ;
                dataList.set(idx,dept) ;
                return ;
            }
            AlertUtil.alertError("修改部门信息失败！！！");
            return ;
        }

        System.out.println("add部门信息："+dept);
        //新增 部门编号启用 状态  可以用户输入部门编号 故：判断为新增部门信息 【业务部门编号不能重复】
        ////////////////////////////////////////
        // 此次 修改成 调用业务方法的新增功能
        boolean insertRs = true ;
        if (insertRs) {
            //更新监听者模型
            dataList.add(dept);
            return;
        }
        AlertUtil.alertError("新增部门信息失败！！！");
    }

    public void del() {
        int selectedIndex = deptTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex==-1) {
            AlertUtil.alertError("请选中要删除的数据！！！");
            return;
        }
        Dept dept = dataList.get(selectedIndex);
        //获得部门编号
        Integer deptno = dept.getDeptno();
        System.out.println("del部门信息=========要删除的部门id是:" + deptno);
        //////////////////////////////////////////////////
        //此次调用业务方法 进行根据id 进行删除部门
        //请注意： 删除部门  需要到 emp.txt中 查询 该编号是否被 员工引用，否则会造成孤儿数据
        boolean deleteRs = true;
        if (deleteRs) {
            //更新监听者模型
            dataList.remove(selectedIndex);
            return;
        }
        AlertUtil.alertError("部门【"+dept.getDname()+"】有关联数据无法删除！！！");
    }

    public void view() {
        int selectedIndex = deptTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex==-1) {
            AlertUtil.alertError("请选中要修改的数据！！！");
            return;
        }
        //获得被选中的部门编号
        Integer deptno = dataList.get(selectedIndex).getDeptno();
        //此次 调用业务方法进行查询  根据部门编号 返回部门对象
        /////////////////////////////////////////////////////////
        //此次 为伪码【直接怼死】
        Dept dbDept = new Dept(20,"蜀国","成都");
        //设置默认值
        deptnoTxf.setText(dbDept.getDeptno()+"");
        //设置禁用 【部门编号不能修改】
        deptnoTxf.setDisable(true);
        dnameTxf.setText(dbDept.getDname());
        locTxf.setText(dbDept.getLoc());
        infoLab.setText("修改部门");
        hiddenIdx.setText(selectedIndex+"");
        System.out.println("view部门信息");
    }

    public void exit(ActionEvent actionEvent) {
        System.out.println("exit 退出系统");
        System.exit(0);
    }

    /**
     * 页面 打开事件 执行 初始化方法
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("加载部门列表数据=====>渲染到表格显示");
        //设置表格列 对应Dept 类中属性名
        deptTabCol.setCellValueFactory(new PropertyValueFactory<>("deptno"));
        dnameTabCol.setCellValueFactory(new PropertyValueFactory<>("dname"));
        locTabCol.setCellValueFactory(new PropertyValueFactory<>("loc"));
        //此次调用业务层代码 查询所有的部门信息列表
        //////////////////////////////////////////////////
        //目前此次直接怼死【此次为伪码】
        List<Dept> deptList = Arrays.asList(
                new Dept(10,"魏国","洛阳"),
                new Dept(20,"蜀国","成都"),
                new Dept(30,"吴国","建业")
        );
        //添加数据到 观察者中
        for (Dept dept : deptList) {
            dataList.add(dept) ;
        }
        deptTableView.setItems(dataList);
    }
}
