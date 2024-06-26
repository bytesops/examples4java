# examples4java

收集整理并亲自实践过的示例程序，致力于简单化，通熟化，文档化，面向一个个小问题（方案）并能跑通的小项目，代码不一定精美，但确保能用。

技术更新速度较快，每个项目都会标出环境及版本信息，不同版本可能会有差异，如需参考请注意版本问题。

当前所有项目是在 JDK 1.8 和 Maven 环境下构建。

## 示例索引

排序为时间顺序，不保证知识点的系统性及递进性，比较随意，积累到足够量了再考虑适当打标签归类。

另外，示例项目名称也不一定完全准确，只能大概描述其范围，看项目说明吧。

每个项目可能额外涵盖其他的知识点，大家无需关注（比如辅助性的 lombok 等），只需关注项目说明中提到的功能点即可。

- 000-start
  - 基于 SpringBoot 的初始化项目
  - 构建自 https://start.aliyun.com 
  - 默认未说明情况下所有项目都是基于这个为底座进行构建
- 001-hello-world
  - 实现常用接口方法，不同请求体，响应体结构
  - Restful API 风格示例
- 002-jpa-mysql-01
  - JPA 实现简单 CURD
  - 实现表关联查询
  - 实现 JPA Auditor 自动插入更新日期时间
- 003-jpa-mysql-02
  - JPA 实现复杂查询
  - 手写 SQL 语句
  - 借助 Specification
- 004-jpa-mysql-03
  - JPA 借助 [jpa-spec](https://github.com/wenhao/jpa-spec) 实现复杂查询
  - 大牛封装的 Specification 工具集，可以拜读一下源码，不是太复杂
- 005-datasource-config
  - 数据源常用配置
  - 多种数据源配置参考
  - 常用连接池配置
- 006-multi-datasource-01
  - 多数据源配置
  - 项目接入多个数据源，不同的表结构定义适配
- 007-pan-files-share
  - 网盘分享群文件目录数据存储接口简单实现
- 008-java-crawler-01
  - 简易版网页爬虫，使用 HttpClient + Jsoup 实现
  - 直接请求获取页面，并进行打印控制台
  - 练习 HttpClient 的使用
  - 练习 Jsoup 的使用
- 008-java-crawler-02
  - 掌握 webmagic 实现爬取网站数据
