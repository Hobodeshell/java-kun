# 码云配置ssh公钥

## 一、

1.注册登录，打开个人设置，怎样生成公钥![image-20230406223944331](D:\Notes\Typora\image-20230406223944331.png)

2.新链接打开怎样生成公钥

你可以按如下命令来生成 sshkey:

```
ssh-keygen -t ed25519 -C "chengjb@nuaa.edu.cn" 
```

```java
ssh公钥 :路径C:\Users\naiso\.ssh （pub文件）

ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIECRvRqS/uu+T7UrG6xDIx9mz8OuYDTrlsZ6wdQXx9sE chengjb@nuaa.edu.cn

```



3.测试

执行代码

```java
ssh -T git@gitee.com
```

