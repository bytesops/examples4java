# examples4java

收集整理并亲自实践过的示例程序，致力于简单化，通熟化，文档化，面向一个个小问题（方案）并能跑通的小项目，代码不一定精美，但确保能用。

技术更新速度较快，每个项目都会标出环境及版本信息，不同版本可能会有差异，如需参考请注意版本问题。

当前所有项目是在 JDK 1.8 和 Maven 环境下构建。

## 示例索引

排序为时间顺序，不保证知识点的系统性及递进性，比较随意，积累到足够量了再考虑适当打标签归类。

另外，示例项目名称也不一定完全准确，只能大概描述其范围，看项目说明吧。

每个项目可能额外涵盖其他的知识点，大家无需关注（比如辅助性的 lombok 等），只需关注项目说明中提到的功能点即可。



| 编号 | 示例 | 说明 |
|:-------:|-------|-------|
| 000 | start | 基于 SpringBoot 的初始化项目，构建自 https://start.aliyun.com 默认未说明情况下所有项目都是基于这个为底座进行构建 |
| 001 | hello-world | SpringBoot 实现 HelloWorld, API调用能正常返回结果 |
| 002 | jpa-mysql-01 | SpringBoot JPA 实现简单 CURD |
| 003 | jpa-mysql-02 | SpringBoot JPA 实现复杂查询（手写 SQL 语句） |
| 004 | jpa-mysql-03 | SpringBoot JPA 实现复杂查询（借助 Specification） |
| 005 | jpa-mysql-04 | SpringBoot JPA 实现复杂查询（借助 [jpa-spec](https://github.com/wenhao/jpa-spec)） |
| 007 | pan-files-share | SpringBoot JPA 网盘目录数据存储接口实现 |