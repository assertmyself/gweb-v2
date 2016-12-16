# gweb-v2
# [Gweb+](https://github.com/assertmyself/gweb-v2) [![Build Status](https://img.shields.io/travis/b3log/solo.svg?style=flat)](https://travis-ci.org/b3log/solo) [![Apache License](http://img.shields.io/badge/license-apache2-orange.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0) 


* [简介](#%E7%AE%80%E4%BB%8B)
* [功能](#%E5%8A%9F%E8%83%BD)
* [安装](#%E5%AE%89%E8%A3%85)
* [技术](#%E6%8A%80%E6%9C%AF)
* [文档](#%E6%96%87%E6%A1%A3)
* [版本历史](#%E7%89%88%E6%9C%AC%E5%8E%86%E5%8F%B2)
* [贡献](#%E8%B4%A1%E7%8C%AE)
* [鸣谢](#%E9%B8%A3%E8%B0%A2)
* [界面截图](#%E7%95%8C%E9%9D%A2%E6%88%AA%E5%9B%BE)
  * [后台](#%E5%90%8E%E5%8F%B0)
  * [前端](#%E5%89%8D%E7%AB%AF)




## 简介

[Gweb+](https://github.com/assertmyself/gweb-v2) 是一个java语言的web开发框架，后台整合了java阵营常用到的技术Spring、Hibernate等等，前端使用bootstrap、jquery等等，并参考Hplus风格搭建的一个通用web开发平台；整合常用的web开发基本功能，例如RBAC权限管理、菜单管理、安全认证、日志记录和统计、系统信息、数据库备份和还原等功能。

## 功能 

Gweb+ 开发框架定位为通用web开发平台，不仅仅包括前端页面框架，还包括后台功能框架，并在文档中做好开发规范的约定，从而使得以最简单的方式进行开发。 


* RBAC权限管理
* 菜单管理
* 日志记录和统计
* 安全认证
* 系统信息
* 数据库备份和还原
* license管理
* 消息通知
* 多皮肤、多主题选择
* hplus风格
* ajax页面最小化加载
* 异常处理
* 登录/注册
* 首页





##安装
* java环境，支持jdk1.6和1.7 不支持jdk1.8 或者更高，以及jdk1.5或者以下
* tomcat环境，支持tomcat6.x和tomcat7.x
* mysql环境 ，，推荐mysql5.0或者5.1
* git上下载的就是eclipse工程，直接导入，类似普通web工程编译、部署即可
* 导入数据库脚本，位于src/sql下
* 访问 http://localhost:8080/gb-web-v2;正常显示即成功
* 默认普通管理员用户名（admin/admin）
* 
### 注意
* 工程中有很多.svn文件，代码之前在svn托管，直接忽略或者删除即可
* 工程的project目录为 ant构建版本构建相关
* target目录为 maven构建目录以及 发布压缩包目录，maven的pom构建有问题
* 详细流程可以参考帮助文档，运行成功之后可以在界面直接下载。也可以参考下面的文档链接
* 另外，如果你仅仅先看下Gweb+的效果，并不想搭建开发环境，可以在target下面 获取zip包 直接解压到tomcat/webapps中，然后访问浏览器查看效果


## 技术

### 后端框架
* spring
* hibernate
* spring-security
* jstl/EL
* jsonlib
* quartz
* 其它

### 前端框架
* jquery
* bootstrap
* hplus
* jqgrid
* ztree
* validationEngine
* iCheck
* highcharts
* layer
* DropzoneJS
* 其它自定义



另外，为了保证 Gweb框架 的质量，我们也做了很多约定，包括：

* 统一规范的编码风格
* 完善的 javadoc 注释
* 不断完善的单元测试用例
* 开发示例

## 文档

* Readme：安装、配置、技术
* [开发指南](https://github.com/assertmyself/gweb-v2/blob/master/WebRoot/upload/guide.chm)：开发环境、项目结构、框架说明、功能介绍
* [示例代码](https://github.com/assertmyself/gweb-v2/blob/master/WebRoot/upload/guide.chm)：开发步骤、模版变量、开发规范

## 版本历史

Gweb 的**第一个版本2015年**，该版本并没有发布到git上，仅仅用于内部的web开发框架，后来对版本进行升级和裁剪，并引入hplus的扁平化风格，最终Gweb+开发完毕，于**2016年底第二个版本发布**。



## 贡献

Gweb 的主要作者是 [syz](http://assertmyself.iteye.com/) 、huaipiqi 和 lurenjia;



## 鸣谢

Gweb 的诞生离不开以下开源项目：

* [jQuery](https://github.com/jquery/jquery)：使用最广泛的 JavaScript 工具库
* [Bootstrap](http://getbootstrap.com/2.3.2/)：最受欢迎的CSS3框架
* [Spring](https://github.com/spring-projects/spring-framework)：J2EE框架，java阵营事实上的标准
* [Hibernate](https://github.com/hibernate)：一个ORM框架，java中最受欢迎的一个。
* [Highlight.js](https://github.com/isagalaev/highlight.js)：前端图表库
* [Apache Commons](http://commons.apache.org)：Java 工具库集
* [Tomcat](https://github.com/apache/tomcat)：使用最多的 Java Web 容器
* [Eclipse](https://www.eclipse.org/downloads/)：国内使用最多（不是最好）的 IDE



## 界面截图 

### 后台

* Admin - 首页 
![Admin - 首页](https://github.com/assertmyself/gweb-v2/blob/master/WebRoot/html/img/md/%E9%A6%96%E9%A1%B5.png)
* Admin - RBAC 
![Admin - RBAC ](https://github.com/assertmyself/gweb-v2/blob/master/WebRoot/html/img/md/rbac.png)
* Admin - 日志统计 
![Admin - 日志统计 ](https://github.com/assertmyself/gweb-v2/blob/master/WebRoot/html/img/md/%E6%97%A5%E5%BF%97%E7%BB%9F%E8%AE%A1.png)
* Admin - 例子 
![Admin - 例子 ](https://github.com/assertmyself/gweb-v2/blob/master/WebRoot/html/img/md/%E4%BE%8B%E5%AD%90.png)


### 前端

* Admin - 主题 
![Admin - 主题 ](https://github.com/assertmyself/gweb-v2/blob/master/WebRoot/html/img/md/%E4%B8%BB%E9%A2%98.png)
