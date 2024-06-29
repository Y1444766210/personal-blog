# 个人博客系统

基于Spring，SpringMVC，SpringBoot，MySQL结合Mybatis-Plus,Redis等技术实现的个人博客系统

## 快速入门

基础配置：JDK-1.8、Maven-3.6.3、MySQL-5.7以上版本、Redis-6.2.6（暂时未遇到Redis版本不兼容问题，这里采用当时最新版本）

运行SQL文件夹中的sql文件

利用Maven导入包后，修改MySQL、Redis以及阿里云OSS配置

**路径1**：blog-front/src/main/resources/application.yml

该配置为博客前台系统（即展示博文等页面）配置，将MySQL、Redis配置修改为本机配置

![image-20240629121848444](C:\Users\mark\AppData\Roaming\Typora\typora-user-images\image-20240629121848444.png)

**路径2**：blog-framework/src/main/resources/application.properties

该配置为阿里云OSS配置，主要用于存储系统中图片信息，将参数修改为自己的配置

![image-20240629122101048](C:\Users\mark\AppData\Roaming\Typora\typora-user-images\image-20240629122101048.png)

**路径3**：blog-admin/src/main/resources/application.yml

该配置为博客后台系统（即修改博文等页面）配置，修改MySQL、Redis配置（可参考路径1配置）

![image-20240629122320150](C:\Users\mark\AppData\Roaming\Typora\typora-user-images\image-20240629122320150.png)





