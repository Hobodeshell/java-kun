# 1.CRUD

## 1.1 新增学生

### 界面原型

![image-20230422110515460](images\image-20230422110515460.png)

### 时序图

![image-20230422110607517](images\image-20230422110607517.png)

### 参考代码

#### service

接口

![image-20230422112021428](\images\image-20230422112021428.png)

实现类

![image-20230422112959468](images\image-20230422112959468.png)

#### controller

![image-20230422113046836](images\image-20230422113046836.png)



## 1.2 删除学生

### 界面原型

![image-20230422113155092](images\image-20230422113155092.png)

### 时序图

![image-20230422141911364](\images\image-20230422141911364.png)

### 参考代码

#### service

接口

```java
public interface StuService {
    /**
     * 删除选中的学生
     * @param sidAy  被选中的学生的学号数组
     * @return true 删除成功  false 删除失败
     */
    boolean remove(String ... sidAy) ;
}
```

实现类

```java
public class StuServiceImpl implements StuService {
   
    @Override
    public boolean remove(String... sidAy) {
        boolean isDelSuc = false;
        for (int j = 0; j < sidAy.length; j++) {
            for (int i = 0; i < stuAy.length; i++) {
                if (stuAy[i].getSid().equals(sidAy[j])) {
                    stuAy = ArrayUtils.remove(stuAy, i);
                    isDelSuc = true;
                }
            }
        }
        return isDelSuc;
    }
}
```



#### controller

```java
public void del() {
    //获得 选中的学号数组
    String[] sidAy = sidList.toArray(new String[]{});
    if (sidAy.length==0){
        AlertUtil.alertError("请先选中要删除的记录！！！");
        return;
    }
    //调用业务方法  删除方法
    boolean remove = stuService.remove(sidAy);
    if (remove) {
        //打开新界面 方法一:
        //ViewUtil.show("/com/wanho/java180/view/stu-list.fxml");
        //重新查询最新的数组  填充数据 方法二:
        Stu[] stuAy = stuService.findAll();
        fillData2ListView(stuAy);
        //更新数据模型 观察者模式 方法三:  目前多次点击删除 出现bug  前端UI API 忽略此方案
        // dataList.remove(0);
        return ;
    }
    System.out.println("要删除的学生是:" + Arrays.toString(sidAy));
}
```

## 1.3 查看学生

### 界面原型

![image-20230422144401546](images\image-20230422144401546.png)

### 时序图

![image-20230422144739875](images\image-20230422144739875.png)

### 参考代码

#### service

接口

```java
public interface StuService {
    /**
     * 根据学号查询学生信息
     * @param sid  学号
     * @return  封装的学生对象
     */
    Stu findById(String sid) ;
}
```

实现类

```java
@Override
public Stu findById(String sid) {
    //循环 所有的学生信息
    for (int i = 0; i < stuAy.length; i++) {
        //获得当前被循环的学生对象
        Stu dbStu = stuAy[i];
        //判断 学号 是否  要查找的学生的学号
        if (dbStu.getSid().equals(sid)){
            //返回当前学生
            return dbStu ;
        }
    }
    return null;
}
```

#### controller

![image-20230422145504250](\images\image-20230422145504250.png)



## 1.4 修改学生

### 界面原型

![image-20230422145554054](\images\image-20230422145554054.png)

### 时序图

![image-20230422150458334](images\image-20230422150458334.png)



### 参考代码

#### service

接口

```java
public interface StuService {
    /**
     * 修改学生 信息
     * @param stu  封装 修改界面 中 数据 【stu对象】
     * @return true 修改成功 false 修改失败
     */
    boolean modify(Stu stu) ;
}
```

实现类

```java
public class StuServiceImpl implements StuService {
    @Override
    public boolean modify(Stu stu) {
        //循环当前数组
        for (int i = 0; i < stuAy.length; i++) {
            //当前数组中的学生对象
            Stu dbStu = stuAy[i];
            //数组中学号 修改学生学号
            if (dbStu.getSid().equals(stu.getSid())){
                stuAy[i] = stu ;
                return  true ;
            }
        }
        return false;
    }
}
```

#### controller

![image-20230422154837628](images\image-20230422154837628.png)

## 1.5 条件查询

### 界面原型

![image-20230422155046887](images\image-20230422155046887.png)

```java
// 方案一: 根据用户输入条件 动态匹配方法
Stu[] searchNoInput() ;
Stu[] searchBySname(String sname) ;
Stu[] searchByAddress(String address) ;
Stu[] searchBySnameAndAddress(String sname,String address) ;

//方案二: 使用最多参数  进行方法体内部 各种if判断
Stu[] search(String sname,String address) ;

//方案三: 使用封装stu类型
Stu[] search(Stu stu) ;

//方案四: 使用自定义对象  封装查询条件
public class StuQuery{
    private String sname ;
    private String address ;
}

Stu[] search(StuQuery query) ;

//方案五: 参数接口化
public interface StuFilter{
    boolean test(Stu stu,String sname,String address) ;
}
//提供 多个实现类  根据用户输入动态匹配实现类
public class StuFilterImpl implements StuFilter{
    ....
}

Stu[] search(StuFilter filter,Stu searchStu) ;
```

### 时序图

![image-20230422161020266](\images\image-20230422161020266.png)

### 参考代码

#### filter

接口

```java
/**
 * 自定义 筛选 过滤器
 */
public interface StuFilter {
    /**
     * 筛选 过滤器
     * @param stu 当前的学生对象
     * @param name 用户输入姓名关键字
     * @param address 用户输入住址的关键字
     * @return true 满足条件  false 不满足条件
     */
    boolean test(Stu stu,String name,String address) ;
}
```

实现类

```java
public class StuFilterImpl implements StuFilter {
    @Override
    public boolean test(Stu stu,String name,String address) {
        return stu.getSname().contains(name) && stu.getAddress().contains(address) ;
    }
}
```

#### service

接口

```java
/**
 * 查找学生信息
 * @param filter  自定义过滤器 接口
 * @return  满足添加的学生数组
 */
Stu[] findCond(StuFilter filter,Stu searchStu) ;
```

实现类

```java
public class StuServiceImpl implements StuService {
   
    @Override
    public Stu[] findCond(StuFilter filter,Stu searchStu) {
        //定义 满足条件的学生数组
        Stu[] matchStuAy = {} ;
        //循环 当前数组
        for (int i = 0; i < stuAy.length; i++) {
            //获得当前的学生对象
            Stu dbStu = stuAy[i];
            //调用接口中方法
            if (filter.test(dbStu,searchStu.getSname(),searchStu.getAddress())) {
                matchStuAy = ArrayUtils.add(matchStuAy,dbStu) ;
            }
        }
        return matchStuAy;
    }
}
```



#### controller

```java
public class StuListController implements Initializable {
    /**
     * 点击 界面 搜索按钮执行的代码逻辑
     */
    public void search() {
        //获得 用户输入的关键字
        String sname = snameTxf.getText();
        String address = addressTxf.getText();
        System.out.println("搜索的关键字:姓名:" + sname + ",住址:" + address);
        //调用 查找业务方法
        StuFilter filter = new StuFilterImpl() ;
        Stu searchStu = new Stu();
        searchStu.setSname(sname);
        searchStu.setAddress(address);
        Stu[] stuAy = stuService.findCond(filter,searchStu);
        //回填数据
        fillData2ListView(stuAy);
    }
}
```



