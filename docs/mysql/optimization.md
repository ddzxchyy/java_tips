# MySQL 优化

本文不讲 MySQL 的原理，只介绍一些优化 MySQL 查询的手段。

## 压力给到服务端

一个软件系统的吞吐量瓶颈很大可能来自数据库。数据库的水平扩展的难度和成本要比服务端的水平扩展高很多。如果考虑垂直扩展，成本上升会比较快。

服务端的水平扩展就简单多了，实现负载均衡就可以了。而且有些公司的部分服务器都是很空闲的！

所以在开发过程中，我推荐尽量把压力给到服务端。

### order by

如果业务不需要分页，请在服务端分页，比如有如下 sql：

```mysql
select column1,column2 from table_a order by star desc;
```

可以通过如下方式降低数据库压力

```java
List<AEntity> aList = aDao.selectList();
aList.sort(Comparator.comparing(AEntity::getStar).reversed());
```



## 慢查询基础：优化数据访问

查询性能差，最常见的原因就是访问的数据太多了。可以先基于下面二个步骤分析：

**是否访问了太多行**

不要把所有的数据查出来，再在应用程序里进行数据的操作。

如果报表等功能真的需要访问非常大量的数据，一定要严格控制访问频率，我工作的公司就出现过这样的惨案，直接把数据库 CPU 干到 100%。

对于访问数据量很大的请求，尽量通过缓存或者汇总表的方式实现。

> 表报统计全部数据的时候，对于不会变的历史数据可以提前缓存好，只实时计算今天的数据。

**是否访问了太多列**

使用 ORM 工具，很容易写出 select * 的查询语句，这会让优化器无法完成索引覆盖扫描这类优化，而且要是包含 text 类型的字段，那么传输时间会大大增加。

规矩都是死的，大家要灵活变通。我个人非常不支持禁止使用 select *，尤其在后台页面的开发上，不使用 select  *， 接口的复用性变得很低，影响开发效率。做好代码评审，大致清楚哪些表日后数据增长会比较快，就不会有问题。当然对于对性能要求很高的部分移动端接口，务必按需 select。

## 充分利用索引

现有索引 record_type(record_type, sport_ground_id, state) ，而业务的查询条件只有 sport_ground_id 和 state 。如果 a 的可能值不是非常多，可以通过加上 a 的所有可能值的条件来利用索引。

```mysql
EXPLAIN
select * from st_record where 
sport_ground_id = 10 and state = 1;
# SIMPLE	st_record		ALL					4958799	1.00	Using where

EXPLAIN
select * from st_record where 
record_type in (1,2,3,4,5,6,7,8) and 
sport_ground_id = 10 and state = 1;
# SIMPLE	st_record		range	record_type	record_type	12		8	100.00	Using index condition


```

可以看到扫描的行数从 4958799 降到了 8， 效率提升了几个数量级。

## 优化 limit 和 offset 语句

在 offset 很大的情况下， MySQL 服务器层会查询出一大推不需要的数据，最后却返回 limit 数量的数据

```mysql
select * from st_record t1 join (select id from st_record order by record_type desc limit 100000, 10 ) t2 using(id);
# 用时 0.08s

select * from st_record order by record_type desc limit 100000, 10 ;
# 用时 9s
```

