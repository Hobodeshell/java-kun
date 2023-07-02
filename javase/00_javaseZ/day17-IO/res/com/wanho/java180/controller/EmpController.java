package com.wanho.java180.controller;

import com.wanho.java180.entity.Dept;
import com.wanho.java180.entity.Emp;
import com.wanho.java180.util.AlertUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class EmpController implements Initializable {

    //表单
    @FXML
    private TextField empnoTxf ;
    @FXML
    private TextField enameTxf ;
    @FXML
    private TextField jobTxf ;
    @FXML
    private TextField salTxf ;
    @FXML
    private TextField commTxf ;
    @FXML
    private TextField mgrTxf ;
    @FXML
    private DatePicker hiredateDatePick ;
    @FXML
    private ComboBox<Dept> deptnoComBox ;
    @FXML
    private TextField hiddenIdx;
    @FXML
    private Button submitBtn ;
    //下拉框的 观察者模型
    private  ObservableList<Dept> deptObservableList = FXCollections.observableArrayList();

    //表格
    @FXML
    private TableView<Emp> empTableView ;
    @FXML
    private TableColumn<Emp,Integer> empnoTabCol ;
    @FXML
    private TableColumn<Emp,Integer> enameTabCol ;
    @FXML
    private TableColumn<Emp, LocalDate> hiredateTabCol ;
    @FXML
    private TableColumn<Emp,String> dnameTabCol ;
    private  ObservableList<Emp> empObservableList = FXCollections.observableArrayList();


    /**
     * 界面 加载事件 即执行
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //加载员工列表
        ///////////////////////////////////////////////////////////////
        //此次 调用员工业务方法 查询所有员工信息
        //目前为 伪数据 直接怼死
        List<Emp> empList = Arrays.asList(
                new Emp(7788,"张飞",LocalDate.now(),new Dept("蜀国")),
                new Emp(7799,"关羽",LocalDate.now(),new Dept("蜀国")),
                new Emp(7766,"徐晃",LocalDate.now(),new Dept("魏国"))
                );
        //设置表格数据
        empTableView.setItems(empObservableList);
        empObservableList.addAll(empList);
        empnoTabCol.setCellValueFactory(new PropertyValueFactory<>("empno"));
        enameTabCol.setCellValueFactory(new PropertyValueFactory<>("ename"));
        hiredateTabCol.setCellValueFactory(new PropertyValueFactory<>("hiredate"));
        //关联类类型 手动设置值
        dnameTabCol.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getDept().getDname()));

        //加载部门下拉框
        //查询 所有的部门
        //////////////////////////////////////////////////
        //此次 调用业务层 查询所有的部门信息
        List<Dept> deptList = Arrays.asList(
                new Dept(10,"魏国","洛阳"),
                new Dept(20,"蜀国","成都"),
                new Dept(30,"吴国","建业")
        );
        //观察者 模型 绑定 所有的部门数据
        deptObservableList.addAll(deptList) ;
        //下拉框 绑定 数据模型
        deptnoComBox.setItems(deptObservableList);
        //设置 属性
        deptnoComBox.setConverter(new StringConverter<Dept>() {
            @Override
            public String toString(Dept dept) {
                return dept.getDname();
            }

            @Override
            public Dept fromString(String string) {
                return null;
            }
        });
    }

    public void submit() {
        //获得用户输入的值
        String empno = empnoTxf.getText().trim();
        String ename = enameTxf.getText().trim();
        String job = jobTxf.getText().trim();
        String sal = salTxf.getText().trim();
        String comm = commTxf.getText().trim();
        String mgr = mgrTxf.getText().trim();
        LocalDate hiredate = hiredateDatePick.getValue();
        Dept dept = deptnoComBox.getSelectionModel().getSelectedItem();
        //封装 Emp对象
        Emp emp = new Emp();
        emp.setEmpno(Integer.parseInt(empno));
        emp.setEname(ename);
        emp.setJob(job);
        emp.setSal(Double.parseDouble(sal));
        emp.setComm(Double.parseDouble(comm));
        emp.setMgr(Integer.parseInt(mgr));
        emp.setHiredate(hiredate);
        emp.setDept(dept);
        //判断 员工工号是否被禁用  如果被禁用 进入修改业务代码
        if (empnoTxf.isDisabled()) {
            System.out.println("修改员工的信息是:" + emp);
            ////////////////////////////////////////////////
            //调用业务方法 实现修改员工信息  注意：直接领导编号  1.是否存在？ 2.不能是自己的工号【自己是自己的爹】？  3.不能是他子代的工号【不能认儿子当爹】？
            boolean modifyRs = true ;
            if (modifyRs){
                //更新监听模型
                int idx = Integer.parseInt(hiddenIdx.getText().trim()) ;
                empObservableList.set(idx,emp) ;
                return ;
            }
            AlertUtil.alertError("修改员工信息失败！！！");
            return;
        }
        System.out.println("新增员工的信息是:" + emp);
        //插入数据
        ////////////////////////////////////////////////
        //调用业务方法  进行实现插入员工信息 注意：此次界面输入工号不能重复  并且上级领导工号 需要在emp.txt中存在的员工工号 【一切输入都是罪恶的】
        boolean saveRs = true ;
        if (saveRs) {
            //更新 数据模型
            empObservableList.add(emp) ;
            return;
        }
        AlertUtil.alertError("新增员工失败！！！");
    }

    /**
     * 点击 ： 删除员工信息
     */
    public void del() {
        int selectedIndex = empTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex==-1) {
            AlertUtil.alertError("请选中要删除的数据！！！");
            return;
        }
        Emp emp = empObservableList.get(selectedIndex);
        //获得 员工 工号
        Integer empno = emp.getEmpno();
        ////////////////////////////////////////
        //此次 调用 业务方法进行 删除员工
        System.out.println("=======删除员工信息=========:"+empno);
        boolean isDel = true ;
        if (isDel) {
            empObservableList.remove(selectedIndex);
            return;
        }
        AlertUtil.alertError("删除员工错误！！！");
    }

    /**
     * 查看: 员工信息
     */
    public void view() {
        int selectedIndex = empTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex==-1) {
            AlertUtil.alertError("请选中要查看的数据！！！");
            return;
        }
        Emp selectEmp = empObservableList.get(selectedIndex);
        //获得 员工 工号
        Integer empno = selectEmp.getEmpno();
        ////////////////////////////////////////
        //此次 调用 业务方法进行 根据id查询员工
        System.out.println("=======查看员工信息=========:"+empno);
        ////////字符串 转 LocalDate API 调用
        String strDate = "1989-10-20" ;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") ;
        //字符串转  LocalDate
        LocalDate hiredate = LocalDate.parse(strDate, formatter);
        System.out.println("String:["+strDate + "]字符串转  LocalDate:"+hiredate);
        //LocalDate 转 字符串
        String str = hiredate.format(formatter);
        System.out.println("LocalDate:["+hiredate + "] 转  String:"+str);
        ///////////////////////////////////////
        Emp emp = new Emp(7788,"曹操","丞相",null,hiredate,8000D,100D,new Dept(10)) ;
        //数据回显
        //设置禁用
        empnoTxf.setDisable(true);
        hiddenIdx.setText(selectedIndex+"");
        submitBtn.setText("修改员工");
        empnoTxf.setText(emp.getEmpno()+"");
        enameTxf.setText(emp.getEname());
        jobTxf.setText(emp.getJob());
        salTxf.setText(emp.getSal()+"");
        commTxf.setText(emp.getComm()+"");
        mgrTxf.setText(emp.getMgr()==null?"":emp.getMgr()+"");
        hiredateDatePick.setValue(emp.getHiredate());
        deptnoComBox.setValue(emp.getDept());
    }
}
