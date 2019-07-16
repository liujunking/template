Template Application Platform
---------------------------------------

#### 项目简介
* 基于Restful风格的安全、稳定、快速的云平台
---------------------------------------
#### 核心技术
* 前端技术：
 > (1) JSP2<br>
 > (2) ThymeLeaf3<br>
 > (3) JQuery3<br>
 > (4) BootStrap4<br>
 > (5) EasyUI<br>
* 后台技术：
 > (1) Spring MVC<br>
 > (2) Spring5<br>
 > (3) Spring JDBCTemplate<br>
 > (4) MyBatis3
 > (5) Druild数据库连接池<br>
---------------------------------------
#### 项目管理与版本控制
* Maven3项目管理
* Git版本控制
---------------------------------------
#### 相关服务器
---------------------------------------
* MySQL5： 存储数据库
* Tomcat： WEB应用容器
---------------------------------------
#### 如何运行平台
* 安装JDK1.8-64bit
 	>方案：配置相关环境变量JAVA_HOME CLASSPATH  PATH等
* 安装Eclipse-JEE-neon3-64bit 
	>方案：切记为neon3-64bit版，其他有的版本并不集成git和maven插件
* 配置eclipse的默认WorkSpace编码默认为UTF-8
	>方案：Window/Prepferences/General/Workspace下，更改GBK为UTF-8编码
* 配置eclipse的默认.properties属性文件的编码为UTF-8 
	>方案：Window/Prepferences/General/Content Types/Text/Java Properties File/下，更新ISO-8859-1为UTF-8编码
* .m2中的settings.xml文件中要配置jdk的版本
	>方案：在settings.xml文件中的profiles配置项中增加如下的profile
```xml
    <profiles>
        .............其他配置
        <profile>
        	<id>jdk-1.8</id>
        	<activation>
        	<activeByDefault>true</activeByDefault>
        	<jdk>1.8</jdk>
        	</activation>
        	<properties>
        	    <maven.compiler.source>1.8</maven.compiler.source>
        	    <maven.compiler.target>1.8</maven.compiler.target>
        	    <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
        	</properties>
    	</profile>
        ..............其他配置
    </profiles>
```
* 下载该平台，并导入到Eclipse JEE中
	> 下载可以采用Git克隆或者直接下载zip压缩包的方式
	>（如何导入Maven项目的方式网上教程很多，这里不详述。）
* 右击项目中的pom.xml文件
	> 执行Run as -> Maven Build... -> clean install
	> 该步骤会下载项目中所需要的jar包，故此必须网络要良好，否则请反复执行此步骤，直到Build Success!
* 启动项目 
	> 右击pom.xml
	> 执行Run as -> Maven Build...
	> Goals中输入tomcat7:run
	> 默认8080端口，若没有其他应用占用该端口启动成功  
* 访问应用
	> 打开Google或360浏览器
	> 访问前台：http://localhost:8080/uek
	> 访问后台：http://localhost:8080/uek/admin