1. 基本使用：
 //反射工具包，指明扫描路径
Reflections reflections = new Reflections("cn.qdgd.center.base.service.impl");
//获取带Handler注解的类
Set<Class<?>> classList = reflections.getTypesAnnotatedWith(BpsService.class);
Date now = new Date();
// 根据自己场景书写其他代码。。。


2. 扩展方法
 // 初始化工具类
 Reflections reflections = new Reflections(new ConfigurationBuilder().forPackages(basePackages).addScanners(new SubTypesScanner()).addScanners(new FieldAnnotationsScanner()));
 // 获取某个包下类型注解对应的类
 Set<Class<?>> typeClass = reflections.getTypesAnnotatedWith(RpcInterface.class, true);
 // 获取子类
 Set<Class<? extends SomeType>> subTypes = reflections.getSubTypesOf(SomeType.class);
 // 获取注解对应的方法
 Set<Method> resources =reflections.getMethodsAnnotatedWith(SomeAnnotation.class);
 // 获取注解对应的字段
 Set<Field> ids = reflections.getFieldsAnnotatedWith(javax.persistence.Id.class);
 // 获取特定参数对应的方法
 Set<Method> someMethods = reflections.getMethodsMatchParams(long.class, int.class);
 Set<Method> voidMethods = reflections.getMethodsReturn(void.class);
 Set<Method> pathParamMethods =reflections.getMethodsWithAnyParamAnnotated(PathParam.class);
 // 获取资源文件
 Set<String> properties = reflections.getResources(Pattern.compile(".*\\.properties"));

3.部分使用场景
1）获取某个类型的所有子类；比如，有一个父类是TestInterface，可以获取到TestInterface的所有子类。
2）获取某个注解的所有类型/字段变量，支持注解参数匹配。
3）使用正则表达式获取所有匹配的资源文件
4）获取特定签名方法。