妆口袋
======================
>代码规范
###### 包
- src.main.java.com.scoprion
  - annotation  *自定义注解*
  - config      *全局配置*
  - exception *全局异常*
  - interceptor *拦截器*
  - mall *商城*
     - backstage  *运营*
         - controller 控制器
         - mapper *数据库访问层*
         - service *业务层*
     - littlesoft *小程序*
  - result *返回结果*
  - utils *工具类*
- MallApplication *程序主入口*
- SwaggerConfig  *接口配置*

###### 配置文件
     
- src.main.resources
  - mapper  *SQL*
  - static  *静态文件*
  - template *后台页面*
  - application.properties *系统配置*
  
######命名(！见名知意！)
- 包命名小写 `com.scoprion...`

- 类命名首字母大写 `TestClass.class`

- 对象封装按照业务表书写 `Order`

- 对象扩展按照业务表+ext `OrderExt`

- 方法命名驼峰 `public static void methodName(){}`

- 控制器 `OrderController`

- 业务接口 `OrderService`

- 业务实现 `OrderServiceImpl`

- 业务数据 `OrderMapper`

- 数据xml `OrderMaper.xml`

- 字段or属性驼峰命名 `String propertyName`

- 常量命名大写+下划线`CONST_VALUE`

- 新增 `add`

- 查询所有 `findAll`

- 条件查询 `findByCondition`

- 条件修改 `updateByCondition`

- 条件删除 `deleteByCondition`

- 删除所有 `deleteAll`

######代码

- 方法不能超过70行

- if判断带大括号`{}`

- 字符串判断`"a"equals(A)"`

######注意事项

- 提交信息写明确! 不要含糊表述！







     


