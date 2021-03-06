DROP DATABASE IF EXISTS `radius_db`;
CREATE DATABASE IF NOT EXISTS `radius_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `radius_db`;

create table RAD_OPTION
(
    OPTION_NAME VARCHAR(255) NOT NULL,
    OPTION_VALUE VARCHAR(512) NOT NULL,
    OPTION_DESC VARCHAR(255) NOT NULL,
    PRIMARY KEY (OPTION_NAME)
);

create table RAD_USER
(
    USER_NAME VARCHAR(64) NOT NULL,
    PASSWORD VARCHAR(64) NOT NULL,
    GROUP_NAME VARCHAR(64) NOT NULL,
    PRIMARY KEY (USER_NAME)  
);

create table RAD_USER_META
(
    USER_NAME VARCHAR(64) NOT NULL,
    NAME VARCHAR(255) NOT NULL,
    VALUE VARCHAR(255) NOT NULL,
    META_DESC VARCHAR(255)  NULL
);   
alter table RAD_USER_META add constraint PK_RAD_USER_META primary key (USER_NAME,NAME);



create table RAD_GROUP
(
    GROUP_NAME VARCHAR(64) NOT NULL,
    GROUP_DESC VARCHAR(255) NOT NULL,
    PRIMARY KEY (GROUP_NAME)
); 


create table RAD_GROUP_META
(
    GROUP_NAME VARCHAR(64) NOT NULL,
    NAME VARCHAR(255) NOT NULL,
    VALUE VARCHAR(255) NOT NULL,
    META_DESC VARCHAR(255)  NULL
); 
alter table RAD_GROUP_META add constraint PK_RAD_GROUP_META primary key (GROUP_NAME,NAME);

create table RAD_CLIENT
(
    ADDRESS VARCHAR(128) NOT NULL,
    SECRET VARCHAR(128) NOT NULL,
    CLIENT_DESC VARCHAR(255) NOT NULL,
    CLIENT_TYPE VARCHAR(255) NOT NULL,
    PRIMARY KEY (ADDRESS) 
); 


create table RAD_ADMIN
(
    LOGIN_NAME VARCHAR(128) NOT NULL,
    PASSWORD  VARCHAR(128) NOT NULL,
    PRIMARY KEY (LOGIN_NAME)
); 


create table RAD_ONLINE
(
    USER_NAME VARCHAR(128) NOT NULL,
    CLIENT_ADDRESS  VARCHAR(128) NOT NULL,
    SESSION_ID  VARCHAR(128) NOT NULL,
    IP_ADDRESS  VARCHAR(128) NOT NULL,
    MAC_ADDRESS  VARCHAR(128) NOT NULL,
    ACCT_START  VARCHAR(128) NOT NULL,
    NAS_PORT  VARCHAR(512) NOT NULL,
    START_SOURCE  INT(1),
    PRIMARY KEY (CLIENT_ADDRESS,SESSION_ID) 
);



INSERT INTO RAD_OPTION VALUES('USER_EXPIRED_KEEP','60','过期帐号保留天数');
INSERT INTO RAD_OPTION VALUES('USER_EXPORT_ATTRS','STATUS,EXPIRE,CREDIT,PERIOD,BIND_MAC,CONCUR_NUMBER','用户导出属性集合');
INSERT INTO RAD_USER VALUES('test','test','test');
INSERT INTO RAD_USER VALUES('test2','test2','test');
INSERT INTO RAD_GROUP VALUES('test','test');
INSERT INTO RAD_CLIENT VALUES('192.168.1.1','123456','ros client','ros');
INSERT INTO RAD_ADMIN VALUES('admin','admin');             


