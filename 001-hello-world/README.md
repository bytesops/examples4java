# Hello World

对 SpringBoot Restful API 进行实践。 主要掌握常见的 URL 及 METHOD 即可。

## 项目结构

```text
.
├── HELP.md
├── README.md
├── demo.iml # 忽略，idea 生成的
├── pom.xml # maven 依赖配置
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── bytesops
    │   │           └── demo
    │   │               ├── DemoApplication.java #1
    │   │               └── demos
    │   │                   └── web
    │   │                       ├── BasicController.java #2
    │   │                       ├── PathVariableController.java #3
    │   │                       └── User.java #4
    │   └── resources
    │       ├── application.properties #5
    │       └── static
    │           └── index.html #6
    └── test
        └── java
            └── com
                └── bytesops
                    └── demo
                        └── DemoApplicationTests.java #7
```

- `#1` 启动程序，主程序
- `#2` 基础 URL 请求示例
- `#3` Path 变量占位符形式请求示例
- `#4` 请求用到的 User 实体
- `#5` 配置文件
- `#6` 默认静态页面
- `#7` 单元测试示例

## 功能点

### 常用请求方法

### 参数或请求体

### 状态码及返回值

### Path 变量占位符