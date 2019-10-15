# 2 mybatis的使用

参考文件
- [笔记01](resource/mybatis/Mybatis_day01笔记.docx)
- [笔记02](resource/mybatis/Mybatis_day02笔记.docx)

以下所使用的测试项目,已经移动到 `Demo` 中，引用路径未修改，目前是有问题的

## 2.1 环境配置 

 使用mybatis实现悬赏模块的增删改查

1. 下载mybatis包：
    - 官网：https://github.com/mybatis/mybatis-3/releases
    - 本地：[resource/mybatis/mybatis-3.2.7.zip](\resource\mybatis\mybatis-3.2.7.zip)
2. 将以下jar添加到项目中
    - `mybatis-3.2.7.jar`mybatis的核心包;
    - `lib文件夹下所有jar`mybatis的依赖包所在，也需要添加
3. 创建实体类[mybatis.bean.RewardOrder.java](src\mybatis\bean\RewardOrder.java)
4. 创建sql映射文件，书写sql:[mybatis/bean/RewardOrder.xml](src/mybatis/bean/RewardOrder.xml)
5. 创建config文件夹，添加配置文件
    - mybatis默认使用log4j输出日志信息，配置文件为[log4j.properties](src\log4j.properties)
    - [mybatis/SqlMapConfig.xml](src\mybatis\SqlMapConfig.xml),配置数据源和事务管理
6. 使用案例:[mybatis.test.MyBatisTest.java](src\mybatis\test\MyBatisTest.java)

## 2.2 知识点

- `${}`拼接符和`#{}`占位符的区别
- `selectOne`和`selectList`
- `parameterType`和`resutlType`
- mysql中的`insert into tablename() value/values`两种写法的区别
- `SELECT uuid()`自动生成UUID;`SELECT LAST_INSERT_ID()`递增,（需要数据库字段设置自增？） `AFTER`和`BEFORE`的区别和使用场景
- insert 和 update delete 需要配合 submit
- delete table 和 delete from table
- Mybatis解决jdbc编程的问题
- mybatis与hibernate不同

## 2.3 Dao开发模式

需要知悉：SqlSession、SqlSessionFactoryBuilder、SqlSessionFactory的作用和使用范围；见`resource/mybatis/Mybatis_day01笔记.docx`

### 2.3.1 原始Dao

- 将与数据库相关的操作放到Dao包下
- 编写Dao接口，[mybatis.dao.IRewardOrderDao.java](src\mybatis\dao\IRewardOrderDao.java)定义操作数据库的共用方法
- 编写Dao接口的实现类[mybatis.dao.RewardOrderDaoImpl.java](src\mybatis\dao\RewardOrderDaoImpl.java)，完成数据库操作
- 测试[mybatis.test.MyBatisTest](\src\mybatis\test\MyBatisTest.java)

问题：
- 重复代码：Dao接口的实现类中：`SqlSession sqlSession = sqlSessionFactory.openSession();`
- 硬编码：

### 2.3.2 Mapper动态代理

动态代理可以省略对Dao接口的实现类的编写,只需要以下4部分即可

- 实体类：[mybatis.bean.RewardOrder.java](\src\mybatis\bean\RewardOrder.java)
- Dao接口，实现数据库操作：[mybatis.dao.IRewardOrderDao](src\mybatis\dao\IRewardOrderDao.java)
- Mapper.xml编写SQL语句：[mybatis.bean.RewardOrder.xml](\src\mybatis\bean\RewardOrder.xml)
- 测试类，相当于业务逻辑的实现：[mybatis.test.MyBatisMapperTest.java](\src\mybatis\test\MyBatisMapperTest.java)

Mapper接口开发需要遵循以下规范：

- Mapper.xml文件中的`namespace`与mapper接口的类路径相同。
```XML
<mapper namespace="mybatis.dao.IRewardOrderDao">
```
- Mapper.xml中定义的每个statement的`id`和Mapper接口方法名相同 
```xml
 <select id="queryRewardOrderList" resultMap="rewardOrder">
```
```java
IRewardOrderDao rewardOrderDao = sqlSession.getMapper(IRewardOrderDao.class);
List<RewardOrder> list = rewardOrderDao.queryRewardOrderList();
```
- Mapper.xml中定义的每个sql的`parameterType`的类型和Mapper接口方法的输入参数类型相同
- Mapper.xml中定义的每个sql的`resultType`的类型和Mapper接口方法的输出参数类型相同

## 2.4 SqlMapConfig.xml配置文件

SqlMapConfig.xml配置文件节点顺序如下：
- properties（属性）:可以将连接数据库的信息放到properties文件中
- settings（全局配置参数）
- typeAliases（类型别名）
- typeHandlers（类型处理器）
- objectFactory（对象工厂）
- plugins（插件）
- environments（环境集合属性对象）
    - environment（环境子属性对象）
    - transactionManager（事务管理）
    - dataSource（数据源）
- mappers（映射器）：有三种配置方式

## 2.5 输入映射和输出映射

### 2.5.1 输入类型parameterType

- 简单类型
- 对象类型
- 对象的属性是另一个对象

### 2.5.2 输出类型resultType

- 简单类型
- 输出对象
- 输出对象List
- resultMap,将查询结果字段和对象属性进行映射

## 2.6 动态SQL

### 2.6.1 if标签

注意字符串类型的数据需要要做不等于空字符串校验。

```xml
	WHERE 1=1
	<if test="sex != null and sex != ''">
		AND sex = #{sex}
	</if>
	<if test="username != null and username != ''">
		AND username LIKE
		'%${username}%'
	</if>
```

### 2.6.2 where标签

where标签可以自动添加where，同时处理sql语句中第一个and关键字

```xml
	<where>
		<if test="sex != null">
			AND sex = #{sex}
		</if>
		<if test="username != null and username != ''">
			AND username LIKE
			'%${username}%'
		</if>
	</where>
```

## 2.6.3 sql/include标签 Sql片段

重用sql内容

```xml
<select id="queryUserByWhere" parameterType="user" resultType="user">
	<!-- SELECT id, username, birthday, sex, address FROM `user` -->
	<!-- 使用include标签加载sql片段；refid是sql片段id -->
	SELECT <include refid="userFields" /> FROM `user`
	<!-- where标签可以自动添加where关键字，同时处理sql语句中第一个and关键字 -->
	<where>
		<if test="sex != null">
			AND sex = #{sex}
		</if>
		<if test="username != null and username != ''">
			AND username LIKE
			'%${username}%'
		</if>
	</where>
</select>

<!-- 声明sql片段 -->
<sql id="userFields">
	id, username, birthday, sex, address
</sql>
```

## 2.6.4 foreach标签

用于 in 语句

```xml
	SELECT * FROM `user`
	<where>
		<!-- foreach标签，进行遍历 -->
		<!-- collection：遍历的集合，这里是QueryVo的ids属性 -->
		<!-- item：遍历的项目，可以随便写，，但是和后面的#{}里面要一致 -->
		<!-- open：在前面添加的sql片段 -->
		<!-- close：在结尾处添加的sql片段 -->
		<!-- separator：指定遍历的元素之间使用的分隔符 -->
		<foreach collection="ids" item="item" open="id IN (" close=")"
			separator=",">
			#{item}
		</foreach>
	</where>
```

## 2.7 关联查询

### 2.7.1 一对一

resultType:可以使用requestType输出类型，对应一个类A；如果输出字段不在该类A中存在相应属性，可以在类A中添加，或者定义类B，继承类A，并且在类B中添加相应多余属性

resultMap:使用association完成一对一

```xml
<resultMap type="order" id="orderUserResultMap">
    <id property="id" column="id" />
    <result property="userId" column="user_id" />
    <association property="user" javaType="user">
        <!-- id:声明主键，表示user_id是关联查询对象的唯一标识-->
		<id property="id" column="user_id" />
        <result property="username" column="username" />
    </association>
</resultMap>
```

### 2.7.2 一对多

使用resultMap配置collection 属性，完成一对多;对user信息自动完成group，然后orders的list列表作为user的一个属性

```xml
<resultMap type="user" id="userOrderResultMap">
	<id property="id" column="id" />
	<result property="username" column="username" />
	<!-- 配置一对多的关系 -->
	<collection property="orders" javaType="list" ofType="order">
		<!-- 配置主键，是关联Order的唯一标识 -->
		<id property="id" column="oid" />
		<result property="number" column="number" />
	</collection>
</resultMap>
```

## 2.8 mybatis和spring整合

思路：

1. 由spring管理数据库的连接信息、数据库连接池、事务
2. 利用spring的核心控制反转，将SqlSessionFactory、SqlSession放到容器中

### 2.8.1 jar包导入 

需要的jar包 [mybatis与spring整合全部jar包(包括springmvc)](resource/mybatis/mybatis与spring整合全部jar包(包括springmvc).zip) ：

spring、mybatis、[spring+mybatis](resource/mybatis/mybatis-spring-1.2.2.zip)、mysql数据库驱动、数据库连接池(dbcp)

### 2.8.2 配置文件

- [db.properties](\src\mybatis\mybatisSpringConfig\db.properties)：数据库连接信息
- [SqlMapConfig.xml](\src\mybatis\mybatisSpringConfig\SqlMapConfig.xml)：mybatis核心配置
- log4j.properties：mybatis日志输出
- [applicationContext.xml](\src\mybatis\mybatisSpringConfig\applicationContext.xml)：spring核心配置，将数据库连接池，mybatis相关都注入到bean中

### 2.8.3 原始Dao开发

整合以后的imp实现类[mybatis.mybatisSpringConfig.RewardOrderDaoImpl](\src\mybatis\mybatisSpringConfig\RewardOrderDaoImpl.java)需要继承 `SqlSessionDaoSupport` 这样就由spring来管理SqlSession了

[mybatis.test.MybatisSpringDaoTest](\src\mybatis\test\MybatisSpringDaoTest.java)

使用spring后，将需要用到的对象都配置到bean中：applicationContext.xml

程序执行时，先创建IOC容器

```java
this.context = new ClassPathXmlApplicationContext("classpath:mybatis/mybatisSpringConfig/applicationContext.xml");
```

需要使用容器中对象时，从bean容器中获取即可;而不是使用new的方式

```java
RewardOrderDao rewardOrderDao = this.context.getBean(RewardOrderDaoImpl.class);
```

### 2.8.4 Mapper开发

mapper开发的特点是，可以省略Dao接口实现类imp的编写；通过代理，直接获取到实现该接口的类的对象；在使用spring时，这个对象需要在xml文件中配置

两种方案对比：其实是通过两种方式，产生了相同的实现类

```java
IRewardOrderDao rewardOrderDao = (IRewardOrderDao) this.context.getBean("rewardOrderDaoImpl");
IRewardOrderDao rewardOrderDao = (IRewardOrderDao) this.context.getBean("rewardOrderDaoImplMapper");
```

```xml
<!--mybatis与spring整合  传统Dao开发-->
<bean id="rewardOrderDaoImpl" class="mybatis.mybatisSpringConfig.RewardOrderDaoImpl">
	<property name="sqlSessionFactory" ref="sqlSessionFactory"></property>
</bean>

<!--mybatis与spring整合 动态代理Mapper对象，也就是Dao接口的实现类的对象-->
<bean id="rewardOrderDaoImplMapper" class="org.mybatis.spring.mapper.MapperFactoryBean">
	<!-- 配置Mapper接口 -->
	<property name="mapperInterface" value="mybatis.dao.IRewardOrderDao" />
	<!-- 配置sqlSessionFactory -->
	<property name="sqlSessionFactory" ref="sqlSessionFactory" />
</bean>
```