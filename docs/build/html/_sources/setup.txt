二进制发布包部署
=================

如果您不想去做系统环境配置，版本构建这些太过专业的事情，您可以下载已经包含了jdk的发布版本，
解压直接运行启动脚本即可,或者您系统中已经安装了jdk1.6或更高版本的jdk，
您可以通过修改sh或bat脚本中的jdk路径即可，然后直接运行启动脚本

系统环境配置
===============

ToughRadius是一个跨平台的认证计费系统，支持多种操作系统，这里我们主要介绍windows和linux下的安装配置为主。

java环境设置
---------------

我们建议以jdk1.6来作为ToughRadius提供java运行环境，您可以从java官方网站上下载jdk的二进制安装包。

根据您的目标平台选择windows版本还是linux版本，是32位版本还是64位版本。注意在设置完环境变量后要验证是否生效.

输入java -version查看java版本是否正确，输入ant查看结果ant命令是否生效。在linux下设置完成后需要注销重登陆一次系统。

Jdk1.6下载： http://pan.baidu.com/share/link?shareid=2774752657&uk=604278103

* windows下的环境变量设置

右键点击我的电脑—属性—高级—环境变量，新建系统变量
    
JAVA_HOME=C:\\java\\jdk1.6.0_37 //具体路径以实际为准
    
在path变量后加入 ;%JAVA_HOME%\\bin

* linux下的环境变量设置

修改/etc/profile文件，加入以下内容::

    export JAVA_HOME=/opt/jdk1.6.0_37 
    
    export PATH=$JAVA_HOME/bin:$PATH 
    

ant环境设置
-------------

如果你需要在目标平台上用源码构建版本，按以下几步进行：

下载安装配置ant

从apache网站http://ant.apache.org/bindownload.cgi下载ant安装包，解压到指定目录

和java环境变量配置一样

在windows下加入

ANT_HOME=C:\\ant1.9.0 //具体路径以实际为准

在path变量后加入 ;%ANT_HOME%\\bin

在linux下修改/etc/profile文件，加入以下内容::

    export ANT_HOME=/opt/ant1.9.0
    
    export PATH=$ANT_HOME/bin:$PATH 

版本构建
==============

请确认您已经设置好ant环境

从ToughRadius源码托管网站下载代码压缩包，解压到指定，以linux为例

进入以下两个网址中任意一个，找到zip或tar压缩包下载链接并下载到/usr/local/src/目录下

* https://github.com/jamiesun/ToughRadius 
* http://git.oschina.net/jamiesun/toughradius

.. code-block:: bash


    cd  /usr/local/src/
    
    unzip ToughRadiusV1.0.zip
    
    cd ToughRadiusV1.0
    
    ant -f build.xml

注意构建过程的日志输出，确认构建成功

构建完成后，会生成release目录，release目录下有构建完成的版本，将其拷贝到运行目录::

    cp  /usr/local/src/ToughRadiusV1.0/release/ToughRadiusV1.0 /opt/ToughRadiusV1.0


数据库安装配置
=================

Hsqldb配置
------------

Hsqldb是一个高效的嵌入式数据库，ToughRadius默认内置Hsqldb数据库，并提供了一个脚本来进行初始化配置。

进入ToughRadius运行目录，

windows下运行setupdb.bat

linux下执行以下命令

.. code-block:: bash

    cd /opt/ToughRadiusV1.0
    
    chmod +x setupdb.sh
    
    ./setupdb.sh

按提示完成数据库的初始化

.. code-block:: bash

    即将初始化数据库,请输入数据库名 
    
    >> radius
    
    数据库已经存在，是否删除： y/n 
    
    >> y
    
    ...
    
    是否导入数据脚本(docs/data/*.sql)： y/n 
    
    >> y
    
    ...

导入的数据sql脚本放在 docs/data/目录下，参考import.sql

完成以上操作，就完成了数据库初始化了

MySql配置
---------------------

MySql的具体安装暂不介绍，请参考互联网上的介绍指导。

在项目docs目录下提供了MySql的建库初始化脚本mysql_create.sql，在您的MySql服务器上执行它以完成数据库的创建。

修改系统配置脚本，设置Myql的连接，替换默认的Hsqldb连接。


应用系统配置
======================

ToughRadius的配置包括系统配置文件和日志配置文件

conf/system.xml

.. code-block:: xml
    
    <?xml version="1.0" encoding="UTF-8" ?>
    <config>
        <radius>
            <version>ToughRadius V1.0</version> <!-- 版本 -->
            <authPort>1812</authPort> <!-- 认证端口 -->
            <acctPort>1813</acctPort> <!-- 计费端口 -->
            <adminPort>1815</adminPort> <!-- 版本 -->
            <maxSessionTimeout>86400</maxSessionTimeout> <!-- 默认最大会话时长 -->
        </radius>
        <webapp>
            <port>8080</port> <!-- web管理端口 -->
            <maxConn>1024</maxConn> <!-- web管理最大连接数 -->
        </webapp>
        
        <!-- 确定您使用的目标数据库，去除或注释不使用的配置 -->
        <!--
        <database>
            <driver>org.hsqldb.jdbc.JDBCDriver</driver>
            <url>jdbc:hsqldb:./data/radius</url>
            <username>sa</username>
            <password></password>
        </database>    
        -->
        <database>
            <driver>com.mysql.jdbc.Driver</driver>
            <url>jdbc:mysql://127.0.0.1:3306/radius_db?useUnicode=true&amp;characterEncoding=UTF-8</url>
            <username>root</username>
            <password>tsroot</password>
        </database>    
    </config>
    
conf/log4j.xml

.. code-block:: xml
    
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE log4j:configuration PUBLIC "-//ZLAB //DTD Log4j Configuration 1.0.1//EN" "http://www.ly-bns.net/dtd/log4j.dtd">
    <log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
    
    <appender name="console.log" class="org.apache.log4j.ConsoleAppender">
          <layout class="org.apache.log4j.PatternLayout">
              <param name="ConversionPattern" value="%d [%t] %p - %m%n" />
          </layout>
      </appender>
    
      <appender name="error.log" class="org.apache.log4j.DailyRollingFileAppender">
            <param name="File" value="logs/error.log" />
            <param name="Append" value="true" />
            <param name="DatePattern" value="'.'yyyy-MM-dd"/>
            <layout class="org.apache.log4j.PatternLayout">
              <param name="ConversionPattern" value="%d [%t] %p - %m%n" />
            </layout>
            <filter class="org.apache.log4j.varia.LevelRangeFilter">     
                <param name="LevelMin" value="WARN" />     
                <param name="LevelMax" value="FATAL" />     
            </filter>  
       </appender>
    
        <appender name="info.log" class="org.apache.log4j.DailyRollingFileAppender">
            <param name="File" value="logs/info.log" />
            <param name="Append" value="true" />
            <param name="DatePattern" value="'.'yyyy-MM-dd"/>
            <layout class="org.apache.log4j.PatternLayout">
              <param name="ConversionPattern" value="%d [%t] %p - %m%n" />
            </layout>
            <filter class="org.apache.log4j.varia.LevelRangeFilter">     
                <param name="LevelMin" value="DEBUG" />
                <param name="LevelMax" value="INFO" />
            </filter>  
        </appender>
      
        <appender name="acct.log" class="org.apache.log4j.DailyRollingFileAppender">
             <param name="File" value="logs/acct/acct.rec" />
             <param name="Append" value="true" />
             <layout class="org.apache.log4j.PatternLayout">
                  <param name="ConversionPattern" value="%m%n" />
             </layout>
        </appender>
      
        <!-- 计费日志 -->
        <logger name="acct" additivity="false"> 
            <level value="info" />
            <appender-ref ref="acct.log" />
        </logger>
    
       <!-- 数据库调试日志，在生产环境中不需要 -->
        <logger name="com.ibatis" additivity="true">
            <level value="DEBUG" />
        </logger>
        <logger name="java.sql.Connection" additivity="true">
            <level value="DEBUG" />
        </logger>
        <logger name="java.sql.Statement" additivity="true">
            <level value="DEBUG" />
        </logger>
        <logger name="java.sql.PreparedStatement" additivity="true">
            <level value="DEBUG" />
        </logger>
        <logger name="java.sql.ResultSet" additivity="true">
            <level value="DEBUG" />
        </logger> 
        
        <!--生产环境中，将日志级别改为error -->
        <root>
            <level value="info" />  
            <appender-ref ref="console.log" />
            <appender-ref ref="error.log" />
            <appender-ref ref="info.log" />
        </root>
        
    </log4j:configuration>


运行说明
================

完成所有前提工作以后，进入运行目录，启动应用

在windows下，直接点击startup.bat会打开一个控制台，并实时输出运行日志。通过编辑器可以打开查看logs目录下的日志文件

在linux下

.. code-block:: bash

    cd /opt/ToughRadiusV1.0
    
    chmod +x startup.sh
    
    ./startup.sh
    
在linux下运行命令后，会以守护进程的方式在后台运行，所有日志被重定向到nohup.out文件，
你可以通过tail工具来观察运行情况

.. code-block:: bash
   
    # 所有输出实时定向到屏幕，按ctrl+c退出，不会影响守护进程
    tail -f nohup.out
    
也通过vi可以查看logs目录下的日志。

管理界面
=======================

打开浏览器，建议使用ie8以上，chrome，火狐，猎豹等浏览器操作以便有更好的显示效果。

进入http://127.0.0.1:8080  默认管理密码为 admin/admin

