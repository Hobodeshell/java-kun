# 1. 多表操作

```
Many2One  多对一   N 个员工 对 1 个部门

One2Many  一对多   1 个部门 对 N 个员工

Many2Many  多对多  N个用户  对 N 个角色

One2One    一对一  1个丈夫   对  1个妻子

Tree
```

## 1.1 界面原型

![image-20230509085742420](images\image-20230509085742420.png)

## 1.2 表结构【数据建模】

![image-20230509085742420](images\image-20220327091956399.png)

## 1.3 实体类【领域建模】

### vo

```
value Object 值对象
view  Object  视图对象
```

```java
/**
 * 查询 列表  显示 多表数据【table 表格中 每一行】
 */
public class EmpVO {
    /**员工编号 唯一标识*/
    private Integer empno ;
    /**员工姓名*/
    private String ename ;
    /**员工入职日期*/
    private LocalDate hiredate ;
    /**部门名称*/
    private String dname ;
}
```



### entity

```java
// 类名 = 表名   属性名=列名   属性类型=列类型
public class Emp {
    /**员工编号 唯一标识*/
    private Integer empno ;
    /**员工姓名*/
    private String ename ;
    /**员工工种*/
    private String job ;
    /**员工直接领导工号*/
    private Integer mgr ;
    /**员工入职日期*/
    private LocalDate hiredate ;
    /**员工基本工资*/
    private Double sal ;
    /**员工奖金*/
    private Double comm ;
    /**员工部门编号*/
    private Integer deptno ;
}
```

## A.方案一【N+1】

### dao 

![image-20230509091858157](images\image-20230509091858157.png)

```java
public interface EmpDAO {
    /**
     * 查询所有的员工信息
     * @return
     */
    List<Emp> selectAll();

    /**
     * 根据部门编号查询员工信息
     * @param empno
     * @return
     */
    Emp selectById(Integer empno) ;

    /**
     * 根据部门编号 删除员工信息
     * @param empno
     * @return
     */
    boolean delete(Integer empno) ;

    /**
     * 新增部门信息
     * @param emp  封装表单数据
     * @return true 新增成功 false 新增失败
     */
    boolean insert(Emp emp) ;

    /**
     * 修改部门信息
     * @param emp 封装表单数据
     * @return
     */
    boolean update(Emp emp) ;
}
```

EmpDAOImpl.java

```java
package com.wanho.java180.dao.impl;

import com.wanho.java180.dao.EmpDAO;
import com.wanho.java180.entity.Dept;
import com.wanho.java180.entity.Emp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpDAOImpl implements EmpDAO {

    @Override
    public List<Emp> selectAll() {
        //创建 空的 部门对象列表
        List<Emp> empList = new ArrayList<>() ;
        try {
            //1.获得数据库连接  会话
            Connection conn = DriverManager.getConnection("jdbc:mysql:///scott?useSSL=false", "root", "root");
            String sql = "select empno,ename,job,mgr,hiredate,sal,comm,deptno from emp" ;
            //2.获得执行SQL 语句对象  Statement 简单
            PreparedStatement pstm = conn.prepareStatement(sql);
            //3.执行DQL
            ResultSet rs = pstm.executeQuery();
            //光标 向下一行
            while (rs.next()) {
                // empno,ename,job,mgr,hiredate,sal,comm,deptno
                int empno = rs.getInt("empno");
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                int mgr = rs.getInt("mgr");
                Date hiredate = rs.getDate("hiredate");
                double sal = rs.getDouble("sal");
                double comm = rs.getDouble("comm");
                int deptno = rs.getInt("deptno");
                //封装Emp对象
                Emp emp = new Emp();
                emp.setEmpno(empno);
                emp.setEname(ename);
                emp.setJob(job);
                emp.setMgr(mgr);
                emp.setComm(comm);
                emp.setSal(sal);
                emp.setDeptno(deptno);
                emp.setHiredate(hiredate.toLocalDate());
                //添加对象集合中
                empList.add(emp) ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empList ;
    }

    @Override
    public Emp selectById(Integer empno) {
        try {
            //1.获得数据库连接  会话
            Connection conn = DriverManager.getConnection("jdbc:mysql:///scott?useSSL=false", "root", "root");
            String sql = "select empno,ename,job,mgr,hiredate,sal,comm,deptno from emp where empno = ?" ;
            //2.获得执行SQL 语句对象  Statement 简单
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,empno);
            //3.执行DQL
            ResultSet rs = pstm.executeQuery();
            //光标 向下一行
            while (rs.next()) {
                // empno,ename,job,mgr,hiredate,sal,comm,deptno
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                int mgr = rs.getInt("mgr");
                Date hiredate = rs.getDate("hiredate");
                double sal = rs.getDouble("sal");
                double comm = rs.getDouble("comm");
                int deptno = rs.getInt("deptno");
                //封装Emp对象
                Emp emp = new Emp();
                emp.setEmpno(empno);
                emp.setEname(ename);
                emp.setJob(job);
                emp.setMgr(mgr);
                emp.setComm(comm);
                emp.setSal(sal);
                emp.setDeptno(deptno);
                emp.setHiredate(hiredate.toLocalDate());
                return emp ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null  ;
    }

    @Override
    public boolean delete(Integer empno) {
        try {
            //1.获得数据库连接  会话
            Connection conn = DriverManager.getConnection("jdbc:mysql:///scott?useSSL=false", "root", "root");
            String sql = "delete from emp where empno=?" ;
            //2.获得执行SQL 语句对象  Statement 简单
            PreparedStatement pstm = conn.prepareStatement(sql);
            //3. 占位符 替换值
            pstm.setInt(1,empno) ;
            //4. cut 执行结果： 影响表的行数
            return pstm.executeUpdate()>0 ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false ;
    }

    @Override
    public boolean insert(Emp emp) {
        try {
            //1.获得数据库连接  会话   &useUnicode=true&characterEncoding=UTF-8
            Connection conn = DriverManager.getConnection("jdbc:mysql:///scott?useSSL=false", "root", "root");
            String sql = "INSERT INTO EMP(empno,ename,job,mgr,hiredate,sal,comm,deptno) VALUES (?,?,?,?,?,?,?,?)" ;
            //2.获得执行SQL 语句对象  Statement 简单
            PreparedStatement pstm = conn.prepareStatement(sql);
            //3. 占位符 替换值  empno,ename,job,mgr,hiredate,sal,comm,deptno
            pstm.setInt(1,emp.getEmpno()) ;
            pstm.setString(2,emp.getEname());
            pstm.setString(3,emp.getJob());
            pstm.setInt(4,emp.getMgr());
            pstm.setDate(5, java.sql.Date.valueOf(emp.getHiredate()));
            pstm.setDouble(6,emp.getSal());
            pstm.setDouble(7,emp.getComm());
            pstm.setInt(8,emp.getDeptno());
            //4. cut 执行结果： 影响表的行数
            int cut = pstm.executeUpdate() ;
            if (cut>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false ;
    }

    @Override
    public boolean update(Emp emp) {
        try {
            //1.获得数据库连接  会话   &useUnicode=true&characterEncoding=UTF-8
            Connection conn = DriverManager.getConnection("jdbc:mysql:///scott?useSSL=false", "root", "root");
            String sql = "update emp set ename=?,job=?,mgr=?,hiredate=?,sal=?,comm=?,deptno=? where empno=?" ;
            //2.获得执行SQL 语句对象  Statement 简单
            PreparedStatement pstm = conn.prepareStatement(sql);
            //3. 占位符 替换值  empno,ename,job,mgr,hiredate,sal,comm,deptno
            pstm.setString(1,emp.getEname());
            pstm.setString(2,emp.getJob());
            pstm.setInt(3,emp.getMgr());
            pstm.setDate(4, java.sql.Date.valueOf(emp.getHiredate()));
            pstm.setDouble(5,emp.getSal());
            pstm.setDouble(6,emp.getComm());
            pstm.setInt(7,emp.getDeptno());
            pstm.setInt(8,emp.getEmpno()) ;
            //4. cut 执行结果： 影响表的行数
            int cut = pstm.executeUpdate() ;
            if (cut>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false ;
    }
}
```

### Service

DeptServiceImpl.java

如果 理不清同学： 判断逻辑 直接注释掉

```java
@Override
public boolean delete(Integer deptno) {
    //查询所有的员工
    List<Emp> empList = empDAO.selectAll();
    //判断 要删除部门编号 是否被 员工关联
    for (Emp emp : empList) {
        if (deptno.equals(emp.getDeptno())){
            return false ;
        }
    }
    return deptDAO.delete(deptno);
}
```

EmpService.java

```java
public interface EmpService {
        /**
         * 查询所有的员工信息
         * @return
         */
        List<EmpVO> selectAll();

        /**
         * 根据部门编号查询员工信息
         * @param empno
         * @return
         */
        Emp selectById(Integer empno) ;

        /**
         * 根据部门编号 删除员工信息
         * @param empno
         * @return
         */
        boolean delete(Integer empno) ;

        /**
         * 新增部门信息
         * @param emp  封装表单数据
         * @return true 新增成功 false 新增失败
         */
        boolean insert(Emp emp) ;

        /**
         * 修改部门信息
         * @param emp 封装表单数据
         * @return
         */
        boolean update(Emp emp) ;
}
```

EmpServiceImpl.java

```java
public class EmpServiceImpl implements EmpService {
    private EmpDAO empDAO = new EmpDAOImpl() ;
    private DeptDAO deptDAO = new DeptDAOImpl() ;
    @Override
    public List<EmpVO> selectAll() {
        List<EmpVO> empVOList = new ArrayList<>() ;
        //1 查询员工单表数据
        List<Emp> empList = empDAO.selectAll();
        //N  根据员工所在部门编号 查询部门信息
        for (Emp emp : empList) {
            Integer deptno = emp.getDeptno();
            Dept dept = deptDAO.selectById(deptno);
            EmpVO empVO = new EmpVO();
            empVO.setEmpno(emp.getEmpno());
            empVO.setEname(emp.getEname());
            empVO.setHiredate(emp.getHiredate());
            empVO.setDname(dept.getDname());
            empVOList.add(empVO) ;
        }
        return empVOList;
    }

    @Override
    public Emp selectById(Integer empno) {
        return empDAO.selectById(empno);
    }

    @Override
    public boolean delete(Integer empno) {
        return empDAO.delete(empno);
    }

    @Override
    public boolean insert(Emp emp) {
        //判断 是否重复?
        Emp dbEmp = empDAO.selectById(emp.getEmpno());
        if (dbEmp!=null){
            return  false ;
        }
        return empDAO.insert(emp);
    }

    @Override
    public boolean update(Emp emp) {
        return empDAO.update(emp);
    }
}
```

### Controller

```java
package com.wanho.java180.controller;

import com.wanho.java180.entity.Dept;
import com.wanho.java180.entity.Emp;
import com.wanho.java180.service.DeptService;
import com.wanho.java180.service.EmpService;
import com.wanho.java180.service.impl.DeptServiceImpl;
import com.wanho.java180.service.impl.EmpServiceImpl;
import com.wanho.java180.util.AlertUtil;
import com.wanho.java180.vo.EmpVO;
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
    private TableView<EmpVO> empTableView ;
    @FXML
    private TableColumn<EmpVO,Integer> empnoTabCol ;
    @FXML
    private TableColumn<EmpVO,Integer> enameTabCol ;
    @FXML
    private TableColumn<EmpVO, LocalDate> hiredateTabCol ;
    @FXML
    private TableColumn<EmpVO,String> dnameTabCol ;
    private  ObservableList<EmpVO> empObservableList = FXCollections.observableArrayList();
    /** 创建业务层对象*/
    private EmpService empService = new EmpServiceImpl() ;


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
       /* List<Emp> empList = Arrays.asList(
                new Emp(7788,"张飞",LocalDate.now(),new Dept("蜀国")),
                new Emp(7799,"关羽",LocalDate.now(),new Dept("蜀国")),
                new Emp(7766,"徐晃",LocalDate.now(),new Dept("魏国"))
                );*/
        List<EmpVO> empVOList = empService.selectAll();
        //设置表格数据
        empTableView.setItems(empObservableList);
        empObservableList.addAll(empVOList);
        empnoTabCol.setCellValueFactory(new PropertyValueFactory<>("empno"));
        enameTabCol.setCellValueFactory(new PropertyValueFactory<>("ename"));
        hiredateTabCol.setCellValueFactory(new PropertyValueFactory<>("hiredate"));
        //关联类类型 手动设置值
        dnameTabCol.setCellValueFactory(new PropertyValueFactory<>("dname"));

        //加载部门下拉框
        //查询 所有的部门
        //////////////////////////////////////////////////
        //此次 调用业务层 查询所有的部门信息
        DeptService deptService = new DeptServiceImpl() ;
        List<Dept> deptList = deptService.selectAll();
       /* List<Dept> deptList = Arrays.asList(
                new Dept(10,"魏国","洛阳"),
                new Dept(10,"魏国","洛阳"),
                new Dept(20,"蜀国","成都"),
                new Dept(30,"吴国","建业")
        );*/
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
        emp.setDeptno(dept.getDeptno());
        EmpVO empVO = new EmpVO();
        empVO.setEmpno(emp.getEmpno());
        empVO.setEname(emp.getEname());
        empVO.setHiredate(emp.getHiredate());
        empVO.setDname(dept.getDname());
        //判断 员工工号是否被禁用  如果被禁用 进入修改业务代码
        if (empnoTxf.isDisabled()) {
            System.out.println("修改员工的信息是:" + emp);
            ////////////////////////////////////////////////
            //调用业务方法 实现修改员工信息  注意：直接领导编号  1.是否存在？ 2.不能是自己的工号【自己是自己的爹】？  3.不能是他子代的工号【不能认儿子当爹】？
            //boolean modifyRs = true ;
            boolean modifyRs = empService.update(emp) ;
            if (modifyRs){
                //更新监听模型
                int idx = Integer.parseInt(hiddenIdx.getText().trim()) ;
                empObservableList.set(idx,empVO) ;
                return ;
            }
            AlertUtil.alertError("修改员工信息失败！！！");
            return;
        }
        System.out.println("新增员工的信息是:" + emp);
        //插入数据
        ////////////////////////////////////////////////
        //调用业务方法  进行实现插入员工信息 注意：此次界面输入工号不能重复  并且上级领导工号 需要在emp.txt中存在的员工工号 【一切输入都是罪恶的】
        //boolean saveRs = true ;
        boolean saveRs = empService.insert(emp) ;
        if (saveRs) {
            //更新 数据模型
            empObservableList.add(empVO) ;
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
        EmpVO emp = empObservableList.get(selectedIndex);
        //获得 员工 工号
        Integer empno = emp.getEmpno();
        ////////////////////////////////////////
        //此次 调用 业务方法进行 删除员工
        System.out.println("=======删除员工信息=========:"+empno);
        //boolean isDel = true ;
        boolean isDel = empService.delete(empno) ;
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
        EmpVO selectEmp = empObservableList.get(selectedIndex);
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
        //Emp emp = new Emp(7788,"曹操","丞相",null,hiredate,8000D,100D,new Dept(10)) ;
        Emp emp = empService.selectById(empno) ;
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
        Dept dept = new Dept();
        dept.setDeptno(emp.getDeptno());
        deptnoComBox.setValue(dept);
    }
}
```

