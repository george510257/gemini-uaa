## 项目介绍

- 项目名称：gemini-uaa
- 项目描述：认证授权服务

## 项目结构

```lua
─ gemini-uaa
├─db -- 数据库脚本
├─gemini-uaa-boot -- 启动模块
│  └─src
│     └─main
│        ├─java
│        │  └─com.gls.gemini.uaa.boot
│        │     ├─config -- 配置
│        │     └─web
│        │        ├─controller -- 控制器
│        │        ├─converter -- 转换器
│        │        ├─entity -- 实体
│        │        ├─mapper -- mapper
│        │        └─service -- 服务
│        └─resources
│           └─mapper -- mapper文件
└─gemini-uaa-sdk -- sdk模块
   └─src
      └─main
         ├─java
         │  └─com.gls.gemini.uaa.sdk
         │     ├─feign -- feign
         │     └─vo -- vo
         └─resources
            └─META-INF
               └─spring -- spring配置文件
```