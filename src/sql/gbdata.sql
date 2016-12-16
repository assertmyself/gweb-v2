/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.0.45-community-nt : Database - gbdata
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`gbdata` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `gbdata`;

/*Table structure for table `app_version` */

DROP TABLE IF EXISTS `app_version`;

CREATE TABLE `app_version` (
  `id` bigint(20) NOT NULL,
  `vendor_code` varchar(100) default NULL,
  `app_os_type` int(11) default NULL,
  `app_version_name` varchar(100) default NULL,
  `app_version_code` bigint(20) default NULL,
  `app_file_name` varchar(100) default NULL,
  `app_file_path` varchar(100) default NULL,
  `app_file_size` varchar(100) default NULL,
  `md5` varchar(100) default NULL,
  `app_plist_file_url` varchar(100) default NULL,
  `create_timestamp` bigint(20) NOT NULL,
  `update_content` text,
  `state` bit(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `app_version` */

/*Table structure for table `config_table` */

DROP TABLE IF EXISTS `config_table`;

CREATE TABLE `config_table` (
  `id` bigint(20) NOT NULL,
  `table_name` varchar(100) default NULL,
  `is_log` bit(1) default NULL,
  `extend_xml` text,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  `update_user` varchar(100) default NULL,
  `create_user` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `config_table` */

/*Table structure for table `demo_hello` */

DROP TABLE IF EXISTS `demo_hello`;

CREATE TABLE `demo_hello` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) default NULL,
  `age` int(11) default NULL,
  `address` varchar(50) default NULL,
  `description` varchar(50) default NULL,
  `create_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `demo_hello` */

insert  into `demo_hello`(`id`,`name`,`age`,`address`,`description`,`create_time`) values (4,'aaa',16,'adsf','sfsdf','2016-02-24 10:52:04');

/*Table structure for table `sys_area` */

DROP TABLE IF EXISTS `sys_area`;

CREATE TABLE `sys_area` (
  `id` bigint(20) NOT NULL,
  `layer` bigint(20) NOT NULL,
  `area_name` varchar(50) default NULL,
  `area_code` varchar(20) default NULL,
  `display_name` varchar(50) default NULL,
  `is_reserved` bit(1) default NULL,
  `is_leaf` bit(1) default NULL,
  `tree_id` varchar(200) default NULL,
  `description` varchar(200) default NULL,
  `pid` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK749F02BFAA7DACC5` (`pid`),
  CONSTRAINT `FK749F02BFAA7DACC5` FOREIGN KEY (`pid`) REFERENCES `sys_area` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `sys_area` */

/*Table structure for table `sys_areanes` */

DROP TABLE IF EXISTS `sys_areanes`;

CREATE TABLE `sys_areanes` (
  `id` bigint(20) NOT NULL,
  `neid` varchar(50) default NULL,
  `ne_name` varchar(20) default NULL,
  `fk_col` varchar(50) default NULL,
  `fk_value` varchar(50) default NULL,
  `fk_table` varchar(50) default NULL,
  `area_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK54423A7D7DFDB547` (`area_id`),
  CONSTRAINT `FK54423A7D7DFDB547` FOREIGN KEY (`area_id`) REFERENCES `sys_area` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `sys_areanes` */

/*Table structure for table `sys_code` */

DROP TABLE IF EXISTS `sys_code`;

CREATE TABLE `sys_code` (
  `id` bigint(20) NOT NULL,
  `code` varchar(50) default NULL,
  `name` varchar(100) default NULL,
  `is_reserved` bit(1) default NULL,
  `is_leaf` bit(1) default NULL,
  `tree_id` varchar(200) default NULL,
  `description` varchar(200) default NULL,
  `parent_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK749FE01F25E3896A` (`parent_id`),
  CONSTRAINT `FK749FE01F25E3896A` FOREIGN KEY (`parent_id`) REFERENCES `sys_code` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_code` */

insert  into `sys_code`(`id`,`code`,`name`,`is_reserved`,`is_leaf`,`tree_id`,`description`,`parent_id`) values (1,'Notify_Type','消息通知类型','','','00021','',NULL),(2,'Notify_State','通知发送状态','','','00022','',NULL),(31,'SysPrivilege','权限类型','',NULL,'00012',NULL,NULL),(35,'MonitorWeather','天气情况','\0','','00016',NULL,NULL),(36,'SysLogType','日志操作类型','\0','','00017',NULL,NULL),(37,'SysUserType','用户类型','\0','','00018',NULL,NULL),(38,'Provice','省/直辖市','\0','','00019',NULL,NULL),(39,'City','城市','\0','','00020',NULL,NULL);

/*Table structure for table `sys_code_detail` */

DROP TABLE IF EXISTS `sys_code_detail`;

CREATE TABLE `sys_code_detail` (
  `id` bigint(20) NOT NULL,
  `code` varchar(50) default NULL,
  `name` varchar(100) default NULL,
  `is_leaf` bit(1) default NULL,
  `tree_id` varchar(200) default NULL,
  `is_reserved` bit(1) default NULL,
  `tag` varchar(50) default NULL,
  `is_valid` bit(1) default NULL,
  `description` varchar(200) default NULL,
  `parent_id` bigint(20) default NULL,
  `sys_code_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKD7E2A471636081D5` (`sys_code_id`),
  KEY `FKD7E2A471E730325B` (`parent_id`),
  CONSTRAINT `FKD7E2A471636081D5` FOREIGN KEY (`sys_code_id`) REFERENCES `sys_code` (`id`),
  CONSTRAINT `FKD7E2A471E730325B` FOREIGN KEY (`parent_id`) REFERENCES `sys_code_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_code_detail` */

insert  into `sys_code_detail`(`id`,`code`,`name`,`is_leaf`,`tree_id`,`is_reserved`,`tag`,`is_valid`,`description`,`parent_id`,`sys_code_id`) values (1,'Common','普通通知','','00084','','','','',NULL,1),(2,'Bussiness','业务通知','','00085','\0','0001','','',NULL,1),(3,'Sending','正在发送','','00086','','','','',NULL,2),(4,'Send_Success','发送成功','','00087','','','','',NULL,2),(5,'Send_Fail','发送失败','','00088','','','','',NULL,2),(88,'page','页面权限','','00033','\0',NULL,'',NULL,NULL,31),(89,'button','按钮权限','','00063','\0',NULL,'',NULL,NULL,31),(94,'1','登录','','00068','\0',NULL,'',NULL,NULL,36),(95,'2','正常','','00069','\0',NULL,'',NULL,NULL,36),(96,'3','错误','','00070','\0',NULL,'',NULL,NULL,36),(97,'4','退出','','00071','\0',NULL,'',NULL,NULL,36),(98,'5','webservice','','00073','\0',NULL,'',NULL,NULL,36),(99,'1','内网用户','','00072','\0',NULL,'',NULL,NULL,37),(100,'2','外网用户','','00074','\0',NULL,'',NULL,NULL,37),(101,'3','临时用户','','00075','\0',NULL,'',NULL,NULL,37),(102,'bj','北京','','00076','\0',NULL,'',NULL,NULL,38),(103,'sh','上海','','00077','\0',NULL,'',NULL,NULL,38),(104,'nj','南京','','00078','\0',NULL,'',NULL,NULL,39),(105,'nc','南昌','','00079','\0',NULL,'',NULL,NULL,39),(106,'yt','烟台','','00080','\0',NULL,'',NULL,NULL,39),(107,'1','晴','','00081','\0',NULL,'',NULL,NULL,35),(108,'2','多云','','00082','\0',NULL,'',NULL,NULL,35),(109,'3','小雨','','00083','\0',NULL,'',NULL,NULL,35);

/*Table structure for table `sys_dept` */

DROP TABLE IF EXISTS `sys_dept`;

CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL,
  `code` varchar(100) default NULL,
  `name` varchar(100) default NULL,
  `short_name` varchar(100) default NULL,
  `organization_code` varchar(40) default NULL,
  `address` varchar(200) default NULL,
  `contacter` varchar(100) default NULL,
  `telephone` varchar(100) default NULL,
  `is_comapny` bit(1) default NULL,
  `is_valid` bit(1) default NULL,
  `is_leaf` bit(1) default NULL,
  `tree_id` varchar(200) default NULL,
  `order_no` bigint(20) default NULL,
  `memo` text,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  `create_user` varchar(100) default NULL,
  `update_user` varchar(100) default NULL,
  `parent_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK74A0307725E3D9C2` (`parent_id`),
  CONSTRAINT `FK74A0307725E3D9C2` FOREIGN KEY (`parent_id`) REFERENCES `sys_dept` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_dept` */

insert  into `sys_dept`(`id`,`code`,`name`,`short_name`,`organization_code`,`address`,`contacter`,`telephone`,`is_comapny`,`is_valid`,`is_leaf`,`tree_id`,`order_no`,`memo`,`create_time`,`update_time`,`create_user`,`update_user`,`parent_id`) values (1,'gbcom','111','111','','','','','\0','','',NULL,NULL,'',NULL,NULL,NULL,NULL,NULL),(5,'oem','oem','oem','','','','123123123','','\0','','00001',NULL,'',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL,
  `ip_address` varchar(50) default NULL,
  `enter_time` datetime default NULL,
  `out_time` datetime default NULL,
  `page_url` varchar(200) default NULL,
  `ie_version` text,
  `sessionid` varchar(50) default NULL,
  `result` varchar(10) default NULL,
  `moudle` varchar(50) default NULL,
  `event_type` varchar(20) default NULL,
  `log_type` varchar(20) default NULL,
  `user_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK986862D2A1C00487` (`user_id`),
  CONSTRAINT `FK986862D2A1C00487` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `sys_log` */

insert  into `sys_log`(`id`,`ip_address`,`enter_time`,`out_time`,`page_url`,`ie_version`,`sessionid`,`result`,`moudle`,`event_type`,`log_type`,`user_id`) values (60,'127.0.0.1','2015-12-25 14:17:06','2015-12-25 14:17:06','/gb-web/sysRole/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','403E03271CC41EAF202E7ED92CE4FA5B','SUCCESS','sysRole','QUERY','2',2),(61,'127.0.0.1',NULL,'2015-12-25 14:17:10',NULL,NULL,NULL,'SUCCESS',NULL,NULL,'4',2),(62,'127.0.0.1','2015-12-25 14:17:18',NULL,'/mainPage/index.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS',NULL,NULL,'1',1),(63,'127.0.0.1','2015-12-25 14:17:22','2015-12-25 14:17:22','/gb-web/sysRole/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysRole','QUERY','2',1),(64,'127.0.0.1','2015-12-25 14:27:27','2015-12-25 14:27:31','/gb-web/sysRole/savePrivilege.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysRole','MODIFY','2',1),(65,'127.0.0.1','2015-12-25 14:28:25','2015-12-25 14:28:27','/gb-web/sysRole/savePrivilege.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysRole','MODIFY','2',1),(66,'127.0.0.1','2015-12-25 14:29:55','2015-12-25 14:29:56','/gb-web/sysRole/savePrivilege.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysRole','MODIFY','2',1),(67,'127.0.0.1','2015-12-25 14:30:45','2015-12-25 14:30:47','/gb-web/sysRole/savePrivilege.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysRole','MODIFY','2',1),(68,'127.0.0.1','2015-12-25 14:31:28','2015-12-25 14:31:29','/gb-web/sysRole/savePrivilege.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysRole','MODIFY','2',1),(69,'127.0.0.1','2015-12-25 14:31:33','2015-12-25 14:31:33','/gb-web/sysPrivilege/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','QUERY','2',1),(70,'127.0.0.1','2015-12-25 14:31:41','2015-12-25 14:31:41','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(71,'127.0.0.1','2015-12-25 14:31:45','2015-12-25 14:31:46','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(72,'127.0.0.1','2015-12-25 14:31:55','2015-12-25 14:31:55','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(73,'127.0.0.1','2015-12-25 14:32:04','2015-12-25 14:32:04','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(74,'127.0.0.1','2015-12-25 14:32:08','2015-12-25 14:32:08','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(75,'127.0.0.1','2015-12-25 14:32:14','2015-12-25 14:32:14','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(76,'127.0.0.1','2015-12-25 14:32:20','2015-12-25 14:32:20','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(77,'127.0.0.1','2015-12-25 14:32:30','2015-12-25 14:32:30','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(78,'127.0.0.1','2015-12-25 14:32:34','2015-12-25 14:32:34','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(79,'127.0.0.1','2015-12-25 14:32:38','2015-12-25 14:32:38','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(80,'127.0.0.1','2015-12-25 14:32:41','2015-12-25 14:32:41','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(81,'127.0.0.1','2015-12-25 14:32:48','2015-12-25 14:32:48','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(82,'127.0.0.1','2015-12-25 14:32:52','2015-12-25 14:32:52','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(83,'127.0.0.1','2015-12-25 14:32:56','2015-12-25 14:32:56','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(84,'127.0.0.1','2015-12-25 14:33:00','2015-12-25 14:33:00','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(85,'127.0.0.1','2015-12-25 14:33:03','2015-12-25 14:33:03','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(86,'127.0.0.1','2015-12-25 14:33:06','2015-12-25 14:33:06','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(87,'127.0.0.1','2015-12-25 14:33:11','2015-12-25 14:33:11','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(88,'127.0.0.1','2015-12-25 14:33:14','2015-12-25 14:33:14','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(89,'127.0.0.1','2015-12-25 14:33:19','2015-12-25 14:33:19','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(90,'127.0.0.1','2015-12-25 14:33:25','2015-12-25 14:33:25','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(91,'127.0.0.1','2015-12-25 14:33:29','2015-12-25 14:33:30','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(92,'127.0.0.1','2015-12-25 14:33:32','2015-12-25 14:33:32','/gb-web/sysPrivilege/modify.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','MODIFY','2',1),(93,'127.0.0.1','2015-12-25 14:33:36','2015-12-25 14:33:36','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(94,'127.0.0.1','2015-12-25 14:33:41','2015-12-25 14:33:41','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(95,'127.0.0.1','2015-12-25 14:33:44','2015-12-25 14:33:44','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(96,'127.0.0.1','2015-12-25 14:33:49','2015-12-25 14:33:49','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(97,'127.0.0.1','2015-12-25 14:33:52','2015-12-25 14:33:52','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(98,'127.0.0.1','2015-12-25 14:33:55','2015-12-25 14:33:55','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(99,'127.0.0.1','2015-12-25 14:34:00','2015-12-25 14:34:00','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(100,'127.0.0.1','2015-12-25 14:34:05','2015-12-25 14:34:05','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(101,'127.0.0.1','2015-12-25 14:34:11','2015-12-25 14:34:11','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(102,'127.0.0.1','2015-12-25 14:34:17','2015-12-25 14:34:17','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(103,'127.0.0.1','2015-12-25 14:34:21','2015-12-25 14:34:21','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(104,'127.0.0.1','2015-12-25 14:34:27','2015-12-25 14:34:27','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(105,'127.0.0.1','2015-12-25 14:34:30','2015-12-25 14:34:30','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(106,'127.0.0.1','2015-12-25 14:34:33','2015-12-25 14:34:33','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(107,'127.0.0.1','2015-12-25 14:34:38','2015-12-25 14:34:38','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(108,'127.0.0.1','2015-12-25 14:34:41','2015-12-25 14:34:41','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(109,'127.0.0.1','2015-12-25 14:34:41',NULL,'/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','failed','sysPrivilege','DELETE','2',1),(110,'127.0.0.1','2015-12-25 14:35:16','2015-12-25 14:35:16','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysPrivilege','DELETE','2',1),(111,'127.0.0.1','2015-12-25 14:35:17',NULL,'/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','failed','sysPrivilege','DELETE','2',1),(112,'127.0.0.1','2015-12-25 14:35:56','2015-12-25 14:35:56','/gb-web/sysMenu/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','QUERY','2',1),(113,'127.0.0.1','2015-12-25 14:36:01','2015-12-25 14:36:02','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(114,'127.0.0.1','2015-12-25 14:36:05','2015-12-25 14:36:05','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(115,'127.0.0.1','2015-12-25 14:36:08','2015-12-25 14:36:08','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(116,'127.0.0.1','2015-12-25 14:36:12','2015-12-25 14:36:12','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(117,'127.0.0.1','2015-12-25 14:36:15','2015-12-25 14:36:15','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(118,'127.0.0.1','2015-12-25 14:36:17','2015-12-25 14:36:17','/gb-web/sysMenu/view.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','VIEW','2',1),(119,'127.0.0.1','2015-12-25 14:36:23','2015-12-25 14:36:23','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(120,'127.0.0.1','2015-12-25 14:36:27','2015-12-25 14:36:27','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(121,'127.0.0.1','2015-12-25 14:36:30','2015-12-25 14:36:30','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(122,'127.0.0.1','2015-12-25 14:36:34','2015-12-25 14:36:34','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(123,'127.0.0.1','2015-12-25 14:36:37','2015-12-25 14:36:37','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(124,'127.0.0.1','2015-12-25 14:36:41','2015-12-25 14:36:41','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(125,'127.0.0.1','2015-12-25 14:36:44','2015-12-25 14:36:44','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(126,'127.0.0.1','2015-12-25 14:36:47','2015-12-25 14:36:47','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(127,'127.0.0.1','2015-12-25 14:36:50','2015-12-25 14:36:50','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(128,'127.0.0.1','2015-12-25 14:36:53','2015-12-25 14:36:53','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(129,'127.0.0.1','2015-12-25 14:36:56','2015-12-25 14:36:56','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(130,'127.0.0.1','2015-12-25 14:37:01','2015-12-25 14:37:01','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(131,'127.0.0.1','2015-12-25 14:37:04','2015-12-25 14:37:04','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(132,'127.0.0.1','2015-12-25 14:37:07','2015-12-25 14:37:07','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(133,'127.0.0.1','2015-12-25 14:37:10','2015-12-25 14:37:10','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(134,'127.0.0.1','2015-12-25 14:37:13','2015-12-25 14:37:13','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(135,'127.0.0.1','2015-12-25 14:37:16','2015-12-25 14:37:16','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(136,'127.0.0.1','2015-12-25 14:37:21','2015-12-25 14:37:21','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(137,'127.0.0.1','2015-12-25 14:37:25','2015-12-25 14:37:25','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(138,'127.0.0.1','2015-12-25 14:37:29','2015-12-25 14:37:29','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','SUCCESS','sysMenu','DELETE','2',1),(139,'127.0.0.1','2015-12-25 14:37:29',NULL,'/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','1C0062609DB5BDF52F4B4FFB054B6EF4','failed','sysMenu','DELETE','2',1),(140,'127.0.0.1',NULL,'2015-12-25 14:37:41',NULL,NULL,NULL,'SUCCESS',NULL,NULL,'4',1),(141,'127.0.0.1','2015-12-25 14:37:49',NULL,'/mainPage/index.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS',NULL,NULL,'1',1),(142,'127.0.0.1','2015-12-25 14:37:58','2015-12-25 14:37:58','/gb-web/operLogStat/init.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','operLogStat','QUERY','2',1),(143,'127.0.0.1','2015-12-25 14:38:01','2015-12-25 14:38:01','/gb-web/sysRole/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysRole','QUERY','2',1),(144,'127.0.0.1','2015-12-25 14:38:15','2015-12-25 14:38:15','/gb-web/sysPrivilege/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','QUERY','2',1),(145,'127.0.0.1','2015-12-25 14:38:22','2015-12-25 14:38:22','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(146,'127.0.0.1','2015-12-25 14:38:26','2015-12-25 14:38:26','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(147,'127.0.0.1','2015-12-25 14:38:29','2015-12-25 14:38:29','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(148,'127.0.0.1','2015-12-25 14:38:33','2015-12-25 14:38:33','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(149,'127.0.0.1','2015-12-25 14:38:36','2015-12-25 14:38:36','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(150,'127.0.0.1','2015-12-25 14:38:43','2015-12-25 14:38:43','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(151,'127.0.0.1','2015-12-25 14:38:47','2015-12-25 14:38:47','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(152,'127.0.0.1','2015-12-25 14:38:50','2015-12-25 14:38:51','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(153,'127.0.0.1','2015-12-25 14:38:56','2015-12-25 14:38:56','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(154,'127.0.0.1','2015-12-25 14:39:02','2015-12-25 14:39:02','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(155,'127.0.0.1','2015-12-25 14:39:07','2015-12-25 14:39:07','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(156,'127.0.0.1','2015-12-25 14:39:10','2015-12-25 14:39:10','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(157,'127.0.0.1','2015-12-25 14:39:15','2015-12-25 14:39:15','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(158,'127.0.0.1','2015-12-25 14:39:19','2015-12-25 14:39:19','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(159,'127.0.0.1','2015-12-25 14:39:22','2015-12-25 14:39:22','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(160,'127.0.0.1','2015-12-25 14:39:25','2015-12-25 14:39:25','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(161,'127.0.0.1','2015-12-25 14:39:28','2015-12-25 14:39:28','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(162,'127.0.0.1','2015-12-25 14:39:32','2015-12-25 14:39:32','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(163,'127.0.0.1','2015-12-25 14:39:35','2015-12-25 14:39:35','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(164,'127.0.0.1','2015-12-25 14:39:38','2015-12-25 14:39:38','/gb-web/sysPrivilege/view.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','VIEW','2',1),(165,'127.0.0.1','2015-12-25 14:39:43','2015-12-25 14:39:43','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(166,'127.0.0.1','2015-12-25 14:39:45','2015-12-25 14:39:45','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(167,'127.0.0.1','2015-12-25 14:39:49','2015-12-25 14:39:49','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(168,'127.0.0.1','2015-12-25 14:39:53','2015-12-25 14:39:53','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(169,'127.0.0.1','2015-12-25 14:39:59','2015-12-25 14:39:59','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(170,'127.0.0.1','2015-12-25 14:40:02','2015-12-25 14:40:02','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(171,'127.0.0.1','2015-12-25 14:40:07','2015-12-25 14:40:07','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(172,'127.0.0.1','2015-12-25 14:40:10','2015-12-25 14:40:10','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(173,'127.0.0.1','2015-12-25 14:40:13','2015-12-25 14:40:13','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(174,'127.0.0.1','2015-12-25 14:40:15','2015-12-25 14:40:16','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(175,'127.0.0.1','2015-12-25 14:40:18','2015-12-25 14:40:19','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(176,'127.0.0.1','2015-12-25 14:40:21','2015-12-25 14:40:21','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(177,'127.0.0.1','2015-12-25 14:40:24','2015-12-25 14:40:24','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(178,'127.0.0.1','2015-12-25 14:40:28','2015-12-25 14:40:28','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(179,'127.0.0.1','2015-12-25 14:40:32','2015-12-25 14:40:32','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(180,'127.0.0.1','2015-12-25 14:40:36','2015-12-25 14:40:36','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(181,'127.0.0.1','2015-12-25 14:40:40','2015-12-25 14:40:40','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(182,'127.0.0.1','2015-12-25 14:40:45','2015-12-25 14:40:45','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(183,'127.0.0.1','2015-12-25 14:40:50','2015-12-25 14:40:50','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(184,'127.0.0.1','2015-12-25 14:40:55','2015-12-25 14:40:55','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(185,'127.0.0.1','2015-12-25 14:40:59','2015-12-25 14:40:59','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(186,'127.0.0.1','2015-12-25 14:41:02','2015-12-25 14:41:02','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','0990D1AE94F4004DABE0D8014EAD1D20','SUCCESS','sysPrivilege','DELETE','2',1),(187,'127.0.0.1','2015-12-25 15:13:51',NULL,'/mainPage/index.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','6DC6747FE5B59D5380E0A2C4FC9A318C','SUCCESS',NULL,NULL,'1',2),(188,'127.0.0.1',NULL,'2015-12-25 15:14:00',NULL,NULL,NULL,'SUCCESS',NULL,NULL,'4',2),(189,'127.0.0.1','2015-12-25 15:14:09',NULL,'/mainPage/index.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS',NULL,NULL,'1',1),(190,'127.0.0.1','2015-12-25 15:14:13','2015-12-25 15:14:13','/gb-web/sysMenu/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','QUERY','2',1),(191,'127.0.0.1','2015-12-25 15:14:17','2015-12-25 15:14:17','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(192,'127.0.0.1','2015-12-25 15:14:21','2015-12-25 15:14:22','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(193,'127.0.0.1','2015-12-25 15:14:25','2015-12-25 15:14:25','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(194,'127.0.0.1','2015-12-25 15:14:29','2015-12-25 15:14:29','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(195,'127.0.0.1','2015-12-25 15:14:32','2015-12-25 15:14:32','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(196,'127.0.0.1','2015-12-25 15:14:34','2015-12-25 15:14:34','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(197,'127.0.0.1','2015-12-25 15:14:37','2015-12-25 15:14:37','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(198,'127.0.0.1','2015-12-25 15:14:40','2015-12-25 15:14:40','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(199,'127.0.0.1','2015-12-25 15:14:42','2015-12-25 15:14:43','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(200,'127.0.0.1','2015-12-25 15:14:46','2015-12-25 15:14:46','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(201,'127.0.0.1','2015-12-25 15:14:51','2015-12-25 15:14:51','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(202,'127.0.0.1','2015-12-25 15:14:53','2015-12-25 15:14:53','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(203,'127.0.0.1','2015-12-25 15:14:56','2015-12-25 15:14:56','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(204,'127.0.0.1','2015-12-25 15:14:59','2015-12-25 15:14:59','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(205,'127.0.0.1','2015-12-25 15:15:02','2015-12-25 15:15:02','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(206,'127.0.0.1','2015-12-25 15:15:05','2015-12-25 15:15:05','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(207,'127.0.0.1','2015-12-25 15:15:13','2015-12-25 15:15:13','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(208,'127.0.0.1','2015-12-25 15:15:16','2015-12-25 15:15:16','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(209,'127.0.0.1','2015-12-25 15:15:18','2015-12-25 15:15:18','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(210,'127.0.0.1','2015-12-25 15:15:21','2015-12-25 15:15:21','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(211,'127.0.0.1','2015-12-25 15:15:22','2015-12-25 15:15:22','/gb-web/sysMenu/view.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','VIEW','2',1),(212,'127.0.0.1','2015-12-25 15:15:30','2015-12-25 15:15:30','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(213,'127.0.0.1','2015-12-25 15:15:33','2015-12-25 15:15:33','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(214,'127.0.0.1','2015-12-25 15:15:36','2015-12-25 15:15:36','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(215,'127.0.0.1','2015-12-25 15:15:39','2015-12-25 15:15:39','/gb-web/sysMenu/view.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','VIEW','2',1),(216,'127.0.0.1','2015-12-25 15:15:43','2015-12-25 15:15:43','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(217,'127.0.0.1','2015-12-25 15:15:48','2015-12-25 15:15:48','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(218,'127.0.0.1','2015-12-25 15:15:51','2015-12-25 15:15:51','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(219,'127.0.0.1','2015-12-25 15:15:54','2015-12-25 15:15:54','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(220,'127.0.0.1','2015-12-25 15:16:03','2015-12-25 15:16:03','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(221,'127.0.0.1','2015-12-25 15:16:08','2015-12-25 15:16:08','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(222,'127.0.0.1','2015-12-25 15:16:11','2015-12-25 15:16:11','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(223,'127.0.0.1','2015-12-25 15:16:14','2015-12-25 15:16:14','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(224,'127.0.0.1','2015-12-25 15:16:18','2015-12-25 15:16:18','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(225,'127.0.0.1','2015-12-25 15:16:23','2015-12-25 15:16:23','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(226,'127.0.0.1','2015-12-25 15:16:27','2015-12-25 15:16:27','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(227,'127.0.0.1','2015-12-25 15:16:31','2015-12-25 15:16:31','/gb-web/sysMenu/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','DELETE','2',1),(228,'127.0.0.1','2015-12-25 15:16:36','2015-12-25 15:16:36','/gb-web/sysPrivilege/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysPrivilege','QUERY','2',1),(229,'127.0.0.1','2015-12-25 15:16:48','2015-12-25 15:16:48','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysPrivilege','DELETE','2',1),(230,'127.0.0.1','2015-12-25 15:16:51','2015-12-25 15:16:51','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysPrivilege','DELETE','2',1),(231,'127.0.0.1','2015-12-25 15:16:54','2015-12-25 15:16:54','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysPrivilege','DELETE','2',1),(232,'127.0.0.1','2015-12-25 15:16:57','2015-12-25 15:16:57','/gb-web/sysPrivilege/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysPrivilege','DELETE','2',1),(233,'127.0.0.1','2015-12-25 15:17:04','2015-12-25 15:17:04','/gb-web/sysPrivilege/modify.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysPrivilege','MODIFY','2',1),(234,'127.0.0.1','2015-12-25 15:17:14','2015-12-25 15:17:14','/gb-web/sysPrivilege/modify.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysPrivilege','MODIFY','2',1),(235,'127.0.0.1','2015-12-25 15:17:22','2015-12-25 15:17:22','/gb-web/sysMenu/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','QUERY','2',1),(236,'127.0.0.1','2015-12-25 15:17:23','2015-12-25 15:17:23','/gb-web/sysRole/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysRole','QUERY','2',1),(237,'127.0.0.1','2015-12-25 15:17:24','2015-12-25 15:17:24','/gb-web/sysPrivilege/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysPrivilege','QUERY','2',1),(238,'127.0.0.1','2015-12-25 15:17:25','2015-12-25 15:17:25','/gb-web/sysRole/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysRole','QUERY','2',1),(239,'127.0.0.1','2015-12-25 15:17:25','2015-12-25 15:17:25','/gb-web/sysPrivilege/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysPrivilege','QUERY','2',1),(240,'127.0.0.1','2015-12-25 15:17:26','2015-12-25 15:17:26','/gb-web/sysMenu/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysMenu','QUERY','2',1),(241,'127.0.0.1','2015-12-25 15:17:26','2015-12-25 15:17:26','/gb-web/sysPrivilege/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysPrivilege','QUERY','2',1),(242,'127.0.0.1','2015-12-25 15:17:26','2015-12-25 15:17:26','/gb-web/sysRole/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysRole','QUERY','2',1),(243,'127.0.0.1','2015-12-25 15:22:04','2015-12-25 15:22:04','/gb-web/sysUser/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysUser','QUERY','2',1),(244,'127.0.0.1','2015-12-25 15:22:13','2015-12-25 15:22:13','/gb-web/sysUser/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysUser','DELETE','2',1),(245,'127.0.0.1','2015-12-25 15:22:17','2015-12-25 15:22:17','/gb-web/sysUser/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysUser','DELETE','2',1),(246,'127.0.0.1','2015-12-25 15:22:21','2015-12-25 15:22:21','/gb-web/sysUser/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysUser','DELETE','2',1),(247,'127.0.0.1','2015-12-25 15:22:27','2015-12-25 15:22:27','/gb-web/sysUser/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysUser','DELETE','2',1),(248,'127.0.0.1','2015-12-25 15:22:31','2015-12-25 15:22:31','/gb-web/sysUser/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysUser','DELETE','2',1),(249,'127.0.0.1','2015-12-25 15:22:36','2015-12-25 15:22:36','/gb-web/sysUser/delete.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysUser','DELETE','2',1),(250,'127.0.0.1','2015-12-25 15:22:39','2015-12-25 15:22:39','/gb-web/sysPerson/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysPerson','QUERY','2',1),(251,'127.0.0.1','2015-12-25 15:22:41','2015-12-25 15:22:41','/gb-web/sysUser/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysUser','QUERY','2',1),(252,'127.0.0.1','2015-12-25 15:22:42','2015-12-25 15:22:42','/gb-web/sysPerson/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysPerson','QUERY','2',1),(253,'127.0.0.1','2015-12-25 15:22:42','2015-12-25 15:22:42','/gb-web/sysUser/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysUser','QUERY','2',1),(254,'127.0.0.1','2015-12-25 15:22:43','2015-12-25 15:22:43','/gb-web/sysPerson/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysPerson','QUERY','2',1),(255,'127.0.0.1','2015-12-25 15:22:44','2015-12-25 15:22:44','/gb-web/sysPerson/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysPerson','QUERY','2',1),(256,'127.0.0.1','2015-12-25 15:24:15','2015-12-25 15:24:15','/gb-web/sysPerson/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysPerson','QUERY','2',1),(257,'127.0.0.1','2015-12-25 15:24:18','2015-12-25 15:24:18','/gb-web/sysPerson/modify.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysPerson','MODIFY','2',1),(258,'127.0.0.1','2015-12-25 15:24:37','2015-12-25 15:24:37','/gb-web/sysRole/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysRole','QUERY','2',1),(259,'127.0.0.1','2015-12-25 15:29:08','2015-12-25 15:29:08','/gb-web/sysInfo/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','sysInfo','QUERY','2',1),(260,'127.0.0.1','2015-12-25 15:29:12','2015-12-25 15:29:12','/gb-web/operLogStat/init.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','operLogStat','QUERY','2',1),(261,'127.0.0.1','2015-12-25 15:29:15','2015-12-25 15:29:15','/gb-web/userLogStat/init.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A8A982A3F2CD3EA1000148529F6F7034','SUCCESS','userLogStat','QUERY','2',1),(262,'127.0.0.1','2015-12-28 16:51:10',NULL,'/mainPage/index.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A44FADAC3572C10E9CA8265453635CBB','SUCCESS',NULL,NULL,'1',1),(263,'127.0.0.1','2015-12-28 16:51:23','2015-12-28 16:51:23','/gb-web/sysMenu/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A44FADAC3572C10E9CA8265453635CBB','SUCCESS','sysMenu','QUERY','2',1),(264,'127.0.0.1','2015-12-28 16:51:27','2015-12-28 16:51:27','/gb-web/sysMenu/add.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A44FADAC3572C10E9CA8265453635CBB','SUCCESS','sysMenu','ADD','2',1),(265,'127.0.0.1','2015-12-28 16:52:14','2015-12-28 16:52:14','/gb-web/sysMenu/add.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A44FADAC3572C10E9CA8265453635CBB','SUCCESS','sysMenu','ADD','2',1),(266,'127.0.0.1','2015-12-28 16:52:24','2015-12-28 16:52:24','/gb-web/sysMenu/view.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A44FADAC3572C10E9CA8265453635CBB','SUCCESS','sysMenu','VIEW','2',1),(267,'127.0.0.1','2015-12-28 16:52:30','2015-12-28 16:52:30','/gb-web/sysMenu/view.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A44FADAC3572C10E9CA8265453635CBB','SUCCESS','sysMenu','VIEW','2',1),(268,'127.0.0.1','2015-12-28 16:52:33','2015-12-28 16:52:33','/gb-web/sysMenu/view.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A44FADAC3572C10E9CA8265453635CBB','SUCCESS','sysMenu','VIEW','2',1),(269,'127.0.0.1','2015-12-28 16:52:39','2015-12-28 16:52:39','/gb-web/sysMenu/add.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A44FADAC3572C10E9CA8265453635CBB','SUCCESS','sysMenu','ADD','2',1),(270,'127.0.0.1','2015-12-28 16:54:49','2015-12-28 16:54:49','/gb-web/sysMenu/modify.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A44FADAC3572C10E9CA8265453635CBB','SUCCESS','sysMenu','MODIFY','2',1),(271,'127.0.0.1','2015-12-28 16:55:02','2015-12-28 16:55:02','/gb-web/sysMenu/view.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A44FADAC3572C10E9CA8265453635CBB','SUCCESS','sysMenu','VIEW','2',1),(272,'127.0.0.1','2015-12-28 16:55:07','2015-12-28 16:55:07','/gb-web/sysPrivilege/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A44FADAC3572C10E9CA8265453635CBB','SUCCESS','sysPrivilege','QUERY','2',1),(273,'127.0.0.1','2015-12-28 16:55:17','2015-12-28 16:55:17','/gb-web/sysPrivilege/add.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A44FADAC3572C10E9CA8265453635CBB','SUCCESS','sysPrivilege','ADD','2',1),(274,'127.0.0.1','2015-12-28 16:55:56','2015-12-28 16:55:56','/gb-web/sysPrivilege/add.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A44FADAC3572C10E9CA8265453635CBB','SUCCESS','sysPrivilege','ADD','2',1),(275,'127.0.0.1','2015-12-28 16:56:22','2015-12-28 16:56:22','/gb-web/sysRole/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A44FADAC3572C10E9CA8265453635CBB','SUCCESS','sysRole','QUERY','2',1),(276,'127.0.0.1','2015-12-28 16:57:30','2015-12-28 16:57:34','/gb-web/sysRole/savePrivilege.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','A44FADAC3572C10E9CA8265453635CBB','SUCCESS','sysRole','MODIFY','2',1),(277,'127.0.0.1',NULL,'2015-12-28 16:57:40',NULL,NULL,NULL,'SUCCESS',NULL,NULL,'4',1),(278,'127.0.0.1','2015-12-28 16:57:48',NULL,'/mainPage/index.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','2B828B8878ED1044BEFCE8719A3C8767','SUCCESS',NULL,NULL,'1',1),(279,'127.0.0.1','2015-12-28 16:57:52','2015-12-28 16:57:52','/gb-web//hello/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','2B828B8878ED1044BEFCE8719A3C8767','SUCCESS','hello','QUERY','2',1),(280,'127.0.0.1','2015-12-28 16:58:01','2015-12-28 16:58:01','/gb-web/hello/add.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','2B828B8878ED1044BEFCE8719A3C8767','SUCCESS','hello','ADD','2',1),(281,'127.0.0.1','2015-12-28 16:58:53','2015-12-28 16:58:53','/gb-web//hello/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','2B828B8878ED1044BEFCE8719A3C8767','SUCCESS','hello','QUERY','2',1),(282,'127.0.0.1','2015-12-28 16:58:54','2015-12-28 16:58:54','/gb-web//hello/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','2B828B8878ED1044BEFCE8719A3C8767','SUCCESS','hello','QUERY','2',1),(283,'127.0.0.1','2015-12-28 17:00:35','2015-12-28 17:00:35','/gb-web//hello/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','2B828B8878ED1044BEFCE8719A3C8767','SUCCESS','hello','QUERY','2',1),(284,'127.0.0.1','2015-12-28 17:00:36','2015-12-28 17:00:36','/gb-web/hello/add.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','2B828B8878ED1044BEFCE8719A3C8767','SUCCESS','hello','ADD','2',1),(285,'127.0.0.1','2015-12-28 17:00:54','2015-12-28 17:00:54','/gb-web/hello/add.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','2B828B8878ED1044BEFCE8719A3C8767','SUCCESS','hello','ADD','2',1),(286,'127.0.0.1','2015-12-28 17:02:17','2015-12-28 17:02:17','/gb-web//hello/grid.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','2B828B8878ED1044BEFCE8719A3C8767','SUCCESS','hello','QUERY','2',1),(287,'127.0.0.1','2015-12-28 17:02:22','2015-12-28 17:02:22','/gb-web/hello/view.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','2B828B8878ED1044BEFCE8719A3C8767','SUCCESS','hello','VIEW','2',1),(288,'127.0.0.1','2015-12-28 17:02:25','2015-12-28 17:02:25','/gb-web/hello/add.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','2B828B8878ED1044BEFCE8719A3C8767','SUCCESS','hello','ADD','2',1),(289,'127.0.0.1','2015-12-28 17:02:34','2015-12-28 17:02:34','/gb-web/hello/add.do','Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0','2B828B8878ED1044BEFCE8719A3C8767','SUCCESS','hello','ADD','2',1),(290,'0:0:0:0:0:0:0:1',NULL,'2016-02-24 10:45:43',NULL,NULL,NULL,'SUCCESS',NULL,NULL,'4',2),(291,'0:0:0:0:0:0:0:1','2016-02-24 10:45:49',NULL,'/mainPage/index.do','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0','51BB5498EB08844A41948E1F72CFD5A8','SUCCESS',NULL,NULL,'1',2),(292,'0:0:0:0:0:0:0:1','2016-02-24 10:46:00','2016-02-24 10:46:00','/gb-web/sysArea/grid.do','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0','51BB5498EB08844A41948E1F72CFD5A8','SUCCESS','sysArea','QUERY','2',2),(293,'0:0:0:0:0:0:0:1',NULL,'2016-02-24 10:46:36',NULL,NULL,NULL,'SUCCESS',NULL,NULL,'4',2),(294,'0:0:0:0:0:0:0:1','2016-02-24 10:46:41',NULL,'/mainPage/index.do','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0','BD6247A4FD6D30A7B203C4A1D08F9843','SUCCESS',NULL,NULL,'1',1),(295,'0:0:0:0:0:0:0:1','2016-02-24 10:46:46','2016-02-24 10:46:46','/gb-web//hello/grid.do','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0','BD6247A4FD6D30A7B203C4A1D08F9843','SUCCESS','hello','QUERY','2',1),(296,'0:0:0:0:0:0:0:1','2016-02-24 10:46:49','2016-02-24 10:46:49','/gb-web//hello/grid.do','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0','BD6247A4FD6D30A7B203C4A1D08F9843','SUCCESS','hello','QUERY','2',1),(297,'0:0:0:0:0:0:0:1','2016-02-24 10:49:57','2016-02-24 10:49:57','/gb-web//hello/grid.do','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0','BD6247A4FD6D30A7B203C4A1D08F9843','SUCCESS','hello','QUERY','2',1),(298,'0:0:0:0:0:0:0:1','2016-02-24 10:51:34',NULL,'/mainPage/index.do','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0','1969F900E1303A2F36688DE0537CDD8A','SUCCESS',NULL,NULL,'1',2),(299,'0:0:0:0:0:0:0:1',NULL,'2016-02-24 10:51:43',NULL,NULL,NULL,'SUCCESS',NULL,NULL,'4',2),(300,'0:0:0:0:0:0:0:1','2016-02-24 10:51:50',NULL,'/mainPage/index.do','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0','3BDDA54124FA300AD8D83BCC64E5B9A4','SUCCESS',NULL,NULL,'1',1),(301,'0:0:0:0:0:0:0:1','2016-02-24 10:51:52','2016-02-24 10:51:52','/gb-web//hello/grid.do','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0','3BDDA54124FA300AD8D83BCC64E5B9A4','SUCCESS','hello','QUERY','2',1),(302,'0:0:0:0:0:0:0:1','2016-02-24 10:51:53','2016-02-24 10:51:53','/gb-web/hello/add.do','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0','3BDDA54124FA300AD8D83BCC64E5B9A4','SUCCESS','hello','ADD','2',1),(303,'0:0:0:0:0:0:0:1','2016-02-24 11:12:11','2016-02-24 11:12:11','/gb-web/sysMenu/grid.do','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0','3BDDA54124FA300AD8D83BCC64E5B9A4','SUCCESS','sysMenu','QUERY','2',1),(304,'0:0:0:0:0:0:0:1','2016-02-24 11:15:23','2016-02-24 11:15:23','/gb-web/sysMenu/view.do','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0','3BDDA54124FA300AD8D83BCC64E5B9A4','SUCCESS','sysMenu','VIEW','2',1),(305,'0:0:0:0:0:0:0:1','2016-02-24 11:15:27','2016-02-24 11:15:27','/gb-web/sysMenu/view.do','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0','3BDDA54124FA300AD8D83BCC64E5B9A4','SUCCESS','sysMenu','VIEW','2',1),(306,'0:0:0:0:0:0:0:1','2016-02-24 11:16:21','2016-02-24 11:16:21','/gb-web/sysRole/grid.do','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0','3BDDA54124FA300AD8D83BCC64E5B9A4','SUCCESS','sysRole','QUERY','2',1),(307,'0:0:0:0:0:0:0:1','2016-02-24 11:16:31','2016-02-24 11:16:32','/gb-web/sysRole/savePrivilege.do','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0','3BDDA54124FA300AD8D83BCC64E5B9A4','SUCCESS','sysRole','MODIFY','2',1),(308,'0:0:0:0:0:0:0:1',NULL,'2016-02-24 11:16:36',NULL,NULL,NULL,'SUCCESS',NULL,NULL,'4',1),(309,'0:0:0:0:0:0:0:1','2016-02-24 11:16:44',NULL,'/mainPage/index.do','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0','B4FBCA4BBEB80B4F97F35D394B707856','SUCCESS',NULL,NULL,'1',1),(310,'0:0:0:0:0:0:0:1',NULL,'2016-02-24 11:16:51',NULL,NULL,NULL,'SUCCESS',NULL,NULL,'4',1),(311,'0:0:0:0:0:0:0:1','2016-02-24 11:16:56',NULL,'/mainPage/index.do','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0','86692FABD05DEDAA5797588416910283','SUCCESS',NULL,NULL,'1',2);

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `privilege` varchar(100) NOT NULL,
  `menu_level` int(11) default NULL,
  `url` varchar(200) default NULL,
  `js_event` varchar(200) default NULL,
  `is_leaf` bit(1) default NULL,
  `tree_id` varchar(255) default NULL,
  `is_valid` bit(1) default NULL,
  `param` varchar(100) default NULL,
  `icon` varchar(200) default NULL,
  `target` varchar(100) default NULL,
  `parent_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK74A4479125E7F0DC` (`parent_id`),
  CONSTRAINT `FK74A4479125E7F0DC` FOREIGN KEY (`parent_id`) REFERENCES `sys_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`name`,`privilege`,`menu_level`,`url`,`js_event`,`is_leaf`,`tree_id`,`is_valid`,`param`,`icon`,`target`,`parent_id`) values (35,'系统维护','SysinfoM',1,'','','\0','00028','','','icon3-tag','',NULL),(36,'系统信息','SysInfo',2,'sysInfo/grid.do','','','00028.00001','','','icon2-g-dept','',35),(47,'在线用户','OnlineUser',2,'onlineUser/grid.do','','','00021.00001','','','icon2-g-dept','',600),(69,'服务器配置','SysSet',2,'sysInfo/input.do?m=sysSet','open','','00028.00002','','','icon2-g-dept','',35),(70,'License升级','License',2,'sysInfo/input.do?m=license','open','','00028.00003','','','icon2-g-dept','',35),(71,'数据库备份','DbBackup',2,'sysInfo/input.do?m=dbBackup','open','','00028.00004','','','icon2-g-dept','',35),(72,'数据库还原','Recovery',2,'sysInfo/input.do?m=recovery','open','','00028.00005','','','icon2-g-dept','',35),(77,'统计分析','pmstat',1,'pmstat','','\0','00027','','','icon3-chartpie','',NULL),(82,'分组管理','sysArea',NULL,'sysArea/init.do','','','00025.00019','','','icon2-g-dept','',600),(89,'L2TP服务','l2tp',2,'sysInfo/init.do?m=l2tp','open','','00028.00006','','','icon2-g-dept','',35),(96,'操作日志统计','operLogStat',2,'operLogStat/init.do','','','00027.00001','','','icon2-g-dept','',77),(97,'用户日志统计','userLogStat',NULL,'userLogStat/init.do','','','00027.00002','','','icon2-g-dept','',77),(98,'例子','demo',1,'/demo','','\0','00029','','','icon3-twitter','',NULL),(99,'HelloWorld','hello',2,'/hello/grid.do','','','00029.00001','','','icon2-g-dept','',98),(600,'系统管理','Sys',1,'','','\0','00025','','','icon3-buddygroup','',NULL),(601,'系统字典','SysCode',2,'sysCode/init.do','','\0','00025.00003','','','icon2-g-dept','',600),(603,'部门管理','SysDept',2,'sysDept/init.do','','\0','00025.00005','','','icon2-g-dept','',600),(604,'用户管理','SysUser',2,'sysUser/grid.do',NULL,'\0','00025.00007','',NULL,'icon2-g-dept',NULL,600),(605,'角色管理','SysRole',2,'sysRole/grid.do','','\0','00025.00008','','','icon2-g-dept','',600),(606,'人员管理','SysPerson',2,'sysPerson/grid.do',NULL,'\0','00025.00006','',NULL,'icon2-g-dept',NULL,600),(607,'权限管理','SysPrivilege',2,'sysPrivilege/init.do','','\0','00025.00010','','','icon2-g-dept','',600),(608,'系统日志','SysLog',2,'sysLog/grid.do','','\0','00025.00012','','','icon2-g-dept','',600),(609,'菜单管理','SysMenu',2,'sysMenu/init.do',NULL,'\0','00025.00011','',NULL,'icon2-g-dept',NULL,600),(842,'在线用户查看','SysOnlineUser',2,'sysOnlineUser/grid.do','','','00025.00018','','','icon2-g-dept','',600);

/*Table structure for table `sys_operation_table_log` */

DROP TABLE IF EXISTS `sys_operation_table_log`;

CREATE TABLE `sys_operation_table_log` (
  `id` bigint(20) NOT NULL,
  `log_xml` varchar(255) default NULL,
  `ip_address` varchar(50) default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  `update_user` varchar(100) default NULL,
  `create_user` varchar(100) default NULL,
  `table_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKB359D609FFAC8318` (`table_id`),
  CONSTRAINT `FKB359D609FFAC8318` FOREIGN KEY (`table_id`) REFERENCES `config_table` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `sys_operation_table_log` */

/*Table structure for table `sys_person` */

DROP TABLE IF EXISTS `sys_person`;

CREATE TABLE `sys_person` (
  `id` bigint(20) NOT NULL,
  `code` varchar(50) default NULL,
  `name` varchar(50) default NULL,
  `card` varchar(50) default NULL,
  `age` int(11) default NULL,
  `sex` bit(1) default NULL,
  `born_date` date default NULL,
  `born_place` varchar(100) default NULL,
  `mobile` varchar(50) default NULL,
  `office_tel` varchar(50) default NULL,
  `fax_tel` varchar(50) default NULL,
  `email` varchar(100) default NULL,
  `zipcode` varchar(50) default NULL,
  `work_year` int(11) default NULL,
  `msn_code` varchar(100) default NULL,
  `qq_code` varchar(100) default NULL,
  `memo` text,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  `update_user` varchar(100) default NULL,
  `create_user` varchar(100) default NULL,
  `education` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKE1D109075A74E933` (`education`),
  CONSTRAINT `FKE1D109075A74E933` FOREIGN KEY (`education`) REFERENCES `sys_code_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_person` */

insert  into `sys_person`(`id`,`code`,`name`,`card`,`age`,`sex`,`born_date`,`born_place`,`mobile`,`office_tel`,`fax_tel`,`email`,`zipcode`,`work_year`,`msn_code`,`qq_code`,`memo`,`create_time`,`update_time`,`update_user`,`create_user`,`education`) values (1,'administrator','administrator','2',NULL,'\0',NULL,NULL,'13127918163',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'AREA','AREA','234234234234',23,'',NULL,'','1832234234','','','','',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL),(10,'WIFI','WIFI','320382198908112823',35,'','1989-08-28','','134345345345','','','','',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `sys_person_dept` */

DROP TABLE IF EXISTS `sys_person_dept`;

CREATE TABLE `sys_person_dept` (
  `id` bigint(20) NOT NULL,
  `position` varchar(100) default NULL,
  `order_no` bigint(20) default NULL,
  `is_valid` bit(1) default NULL,
  `is_manager` bit(1) default NULL,
  `dept_id` bigint(20) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK5C83A7D7263A47` (`dept_id`),
  KEY `FK5C83A7D472C6907` (`person_id`),
  CONSTRAINT `FK5C83A7D472C6907` FOREIGN KEY (`person_id`) REFERENCES `sys_person` (`id`),
  CONSTRAINT `FK5C83A7D7263A47` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_person_dept` */

insert  into `sys_person_dept`(`id`,`position`,`order_no`,`is_valid`,`is_manager`,`dept_id`,`person_id`) values (1,NULL,0,'',NULL,1,2),(5,NULL,1,'\0',NULL,1,1),(10,NULL,0,'',NULL,1,10);

/*Table structure for table `sys_privilege` */

DROP TABLE IF EXISTS `sys_privilege`;

CREATE TABLE `sys_privilege` (
  `id` bigint(20) NOT NULL,
  `code` varchar(100) NOT NULL,
  `name` varchar(80) default NULL,
  `tag` bigint(20) default NULL,
  `url` text,
  `definition` varchar(255) default NULL,
  `description` varchar(100) default NULL,
  `is_leaf` bit(1) default NULL,
  `tree_id` varchar(200) default NULL,
  `parent_id` bigint(20) default NULL,
  `type` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKF3CB53BF6C0010C5` (`type`),
  KEY `FKF3CB53BF869420F4` (`parent_id`),
  CONSTRAINT `FKF3CB53BF6C0010C5` FOREIGN KEY (`type`) REFERENCES `sys_code_detail` (`id`),
  CONSTRAINT `FKF3CB53BF869420F4` FOREIGN KEY (`parent_id`) REFERENCES `sys_privilege` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_privilege` */

insert  into `sys_privilege`(`id`,`code`,`name`,`tag`,`url`,`definition`,`description`,`is_leaf`,`tree_id`,`parent_id`,`type`) values (9,'SysUser','用户管理',1,'/sysUser/',NULL,NULL,'\0','00009.00002',58,88),(11,'SysPerson','人员管理',2,'/sysPerson/',NULL,NULL,'\0','00009.00003',58,88),(12,'SysPrivilege','权限管理',22,'/sysPrivilege/',NULL,NULL,'\0','00009.00005',58,88),(42,'SysinfoM','系统维护',NULL,'','','','\0','00017',NULL,88),(43,'SysInfo','系统信息',NULL,'/sysInfo/','','','','00017.00001',42,88),(55,'SysMenu','菜单管理',NULL,'/sysMenu/',NULL,NULL,'\0','00009.00006',58,88),(58,'Sys','系统管理',NULL,NULL,NULL,NULL,'\0','00009',NULL,88),(59,'SysCode','系统字典',NULL,'sysCode/init.do',NULL,NULL,'\0','00009.00004',58,88),(60,'SysDept','部门管理',NULL,'sysDept/init.do',NULL,NULL,'\0','00009.00007',58,88),(61,'SysRole','角色管理',NULL,'/sysRole/',NULL,NULL,'\0','00009.00009',58,88),(62,'SysLog','系统日志',NULL,'sysLog/grid.do',NULL,NULL,'\0','00009.00011',58,88),(81,'SysSet','服务器配置',NULL,'/SysSet/','','','','00017.00002',42,88),(82,'License','License升级',NULL,'/License/','','','','00017.00003',42,88),(83,'DbBackup','数据库备份',NULL,'/DbBackup/','','','','00017.00004',42,88),(84,'Recovery','数据还原',NULL,'/Recovery/','','','','00017.00005',42,88),(89,'pmStat','统计分析',NULL,'pmStat','','','\0','00025',NULL,88),(91,'SysUser_edit','用户管理_编辑',NULL,NULL,NULL,NULL,'\0','00009.00002.00001',9,89),(92,'SysPerson_edit','人员管理_编辑',NULL,NULL,NULL,NULL,'\0','00009.00003.00001',11,89),(93,'SysCode_edit','系统字典_编辑',NULL,NULL,NULL,NULL,'\0','00009.00004.00001',59,89),(94,'SysPrivilege_edit','权限管理_编辑',NULL,NULL,NULL,NULL,'\0','00009.00005.00001',12,89),(95,'SysMenu_edit','菜单管理_编辑',NULL,NULL,NULL,NULL,'\0','00009.00006.00001',55,89),(96,'SysDept_edit','部门管理_编辑',NULL,NULL,NULL,NULL,'\0','00009.00007.00001',60,89),(98,'SysRole_edit','角色管理_编辑',NULL,NULL,NULL,NULL,'\0','00009.00009.00001',61,89),(100,'SysLog_edit','系统日志_编辑',NULL,NULL,NULL,NULL,'\0','00009.00011.00001',62,89),(101,'sysArea','管理区域',NULL,'/sysArea/','','','\0','00009.00017',58,88),(121,'SysRole_designate','角色管理-授权',NULL,'SysRole_designate','','','','00009.00009.00002',61,89),(122,'SysArea_edit','管理区域-编辑',NULL,'SysArea_edit','','','','00009.00017.00001',101,89),(129,'l2tp','L2TP服务',NULL,'/l2tp/','','','','00017.00006',42,88),(142,'operLogStat','操作分析',NULL,'operLogStat','','','','00025.00001',89,88),(143,'userLogStat','用户分析',NULL,'userLogStat','','','','00025.00002',89,88),(144,'demo','例子',NULL,'/demo','','','\0','00026',NULL,88),(145,'hello','HelloWorld',NULL,'/HelloWorld','','','','00026.00001',144,88),(251,'SysOnlineUser','在线用户查看',NULL,'/sysOnlineUser/',NULL,NULL,'\0','00009.00016',58,88);

/*Table structure for table `sys_registration` */

DROP TABLE IF EXISTS `sys_registration`;

CREATE TABLE `sys_registration` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `organization_code` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  `contacter` varchar(10) NOT NULL,
  `telephone` varchar(20) NOT NULL,
  `notify_phone` varchar(20) NOT NULL,
  `license` varchar(255) default NULL,
  `organization_certificate` tinyblob,
  `register_date` date default NULL,
  `check_result` varchar(1) default NULL,
  `check_date` date default NULL,
  `check_user_id` bigint(20) default NULL,
  `city_id` bigint(20) default NULL,
  `province_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK7600C8EBF7C8BA15` (`province_id`),
  KEY `FK7600C8EB9A9A5DFA` (`city_id`),
  KEY `FK7600C8EBBA620850` (`check_user_id`),
  CONSTRAINT `FK7600C8EB9A9A5DFA` FOREIGN KEY (`city_id`) REFERENCES `sys_code_detail` (`id`),
  CONSTRAINT `FK7600C8EBBA620850` FOREIGN KEY (`check_user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FK7600C8EBF7C8BA15` FOREIGN KEY (`province_id`) REFERENCES `sys_code_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `sys_registration` */

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL,
  `code` varchar(50) default NULL,
  `role_name` varchar(50) default NULL,
  `description` varchar(100) default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  `create_user` varchar(100) default NULL,
  `update_user` varchar(100) default NULL,
  `role_type` int(11) default NULL,
  `view_type` int(11) default NULL,
  `domain_type` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`code`,`role_name`,`description`,`create_time`,`update_time`,`create_user`,`update_user`,`role_type`,`view_type`,`domain_type`) values (1,'SUPER_ADMIN','超级管理员','超级管理员，所有权限、包括开发权限，不对外开放',NULL,'2014-12-26 11:46:09',NULL,'admin',0,0,0),(2,'SYS_ADMIN','系统管理员','系统管理员','2014-04-24 15:00:36','2014-12-26 11:46:31','admin','admin',0,0,0),(3,'SYS_VISITOR','访问者','只读权限',NULL,'2014-12-26 11:46:43','admin','admin',0,0,0),(8,'AREA_FIRST','一级区域管理员','区域管理员  一级区域。','2014-04-24 15:00:36','2014-04-24 15:00:36','admin','admin',2,0,1),(9,'AREA_SECOND','二级区域管理员','区域管理员  二级区域。','2014-04-24 15:00:36','2014-04-24 15:00:36','admin','admin',2,0,1);

/*Table structure for table `sys_role_privilege` */

DROP TABLE IF EXISTS `sys_role_privilege`;

CREATE TABLE `sys_role_privilege` (
  `id` bigint(20) NOT NULL,
  `privilege_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK60C171DADF18A18D` (`privilege_id`),
  KEY `FK60C171DAFC9540A7` (`role_id`),
  CONSTRAINT `FK60C171DADF18A18D` FOREIGN KEY (`privilege_id`) REFERENCES `sys_privilege` (`id`),
  CONSTRAINT `FK60C171DAFC9540A7` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_privilege` */

insert  into `sys_role_privilege`(`id`,`privilege_id`,`role_id`) values (5677,9,3),(5678,61,3),(5679,62,3),(5680,251,3),(5681,101,3),(5682,43,3),(5683,89,3),(5684,142,3),(5685,143,3),(5686,9,8),(5687,61,8),(5688,62,8),(5689,251,8),(5690,43,8),(5691,81,8),(5692,89,8),(5693,142,8),(5694,143,8),(5695,9,9),(5696,61,9),(5697,251,9),(5698,43,9),(5699,89,9),(5700,142,9),(5701,143,9),(5702,58,1),(5703,9,1),(5704,91,1),(5705,11,1),(5706,92,1),(5707,59,1),(5708,93,1),(5709,12,1),(5710,94,1),(5711,55,1),(5712,95,1),(5713,60,1),(5714,96,1),(5715,61,1),(5716,98,1),(5717,121,1),(5718,62,1),(5719,100,1),(5720,251,1),(5721,101,1),(5722,122,1),(5723,42,1),(5724,43,1),(5725,81,1),(5726,82,1),(5727,83,1),(5728,84,1),(5729,129,1),(5730,89,1),(5731,142,1),(5732,143,1),(5733,144,1),(5734,145,1),(5735,9,2),(5736,91,2),(5737,61,2),(5738,62,2),(5739,100,2),(5740,251,2),(5741,101,2),(5742,122,2),(5743,42,2),(5744,43,2),(5745,81,2),(5746,82,2),(5747,83,2),(5748,84,2),(5749,129,2),(5750,89,2),(5751,142,2),(5752,143,2),(5753,144,2),(5754,145,2);

/*Table structure for table `sys_sequence` */

DROP TABLE IF EXISTS `sys_sequence`;

CREATE TABLE `sys_sequence` (
  `code` varchar(255) NOT NULL,
  `lastid` bigint(20) NOT NULL,
  PRIMARY KEY  (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_sequence` */

insert  into `sys_sequence`(`code`,`lastid`) values ('APDEVICE',2),('HELLO',5),('SYSCODE',201),('SYSCODEDETAIL',201),('SYSDEPT',62),('SYSLOG',312),('SYSMENU',100),('SYSPERSON',11),('SYSPERSONDEPT',11),('SYSPRIVILEGE',146),('SYSROLE',14),('SYSROLEPRIVILEGE',5755),('SYSUSER',10),('SYSUSERROLE',10);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL,
  `login_name` varchar(100) NOT NULL,
  `password` varchar(100) default NULL,
  `display_name` varchar(100) default NULL,
  `status` varchar(6) default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  `update_user` varchar(100) default NULL,
  `create_user` varchar(100) default NULL,
  `reason_desc` varchar(100) default NULL,
  `open_date` date default NULL,
  `close_date` date default NULL,
  `person_id` bigint(20) default NULL,
  `user_type_id` bigint(20) default NULL,
  `area_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK74A81DFD93DFF957` (`user_type_id`),
  KEY `FK74A81DFD472C6907` (`person_id`),
  KEY `FK74A81DFD7DFDB547` (`area_id`),
  CONSTRAINT `FK74A81DFD472C6907` FOREIGN KEY (`person_id`) REFERENCES `sys_person` (`id`),
  CONSTRAINT `FK74A81DFD7DFDB547` FOREIGN KEY (`area_id`) REFERENCES `sys_area` (`id`),
  CONSTRAINT `FK74A81DFD93DFF957` FOREIGN KEY (`user_type_id`) REFERENCES `sys_code_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`login_name`,`password`,`display_name`,`status`,`create_time`,`update_time`,`update_user`,`create_user`,`reason_desc`,`open_date`,`close_date`,`person_id`,`user_type_id`,`area_id`) values (1,'super','1b3231655cebb7a1f783eddf27d254ca','超级管理员','1','2014-04-09 16:38:52',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL),(2,'admin','21232f297a57a5a743894a0e4a801fc3','管理员','1','2014-04-09 16:38:52',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'vistor','e10adc3949ba59abbe56e057f20f883e','访问者','1','2014-04-09 16:38:52',NULL,NULL,NULL,NULL,NULL,NULL,10,NULL,NULL);

/*Table structure for table `sys_user_privilege` */

DROP TABLE IF EXISTS `sys_user_privilege`;

CREATE TABLE `sys_user_privilege` (
  `id` bigint(20) NOT NULL,
  `is_deny` bit(1) default NULL,
  `user_id` bigint(20) NOT NULL,
  `privilege_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK1A2876EFDF18A18D` (`privilege_id`),
  KEY `FK1A2876EFA1C00487` (`user_id`),
  CONSTRAINT `FK1A2876EFA1C00487` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FK1A2876EFDF18A18D` FOREIGN KEY (`privilege_id`) REFERENCES `sys_privilege` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `sys_user_privilege` */

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK660C5178FC9540A7` (`role_id`),
  KEY `FK660C5178A1C00487` (`user_id`),
  CONSTRAINT `FK660C5178A1C00487` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FK660C5178FC9540A7` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`role_id`,`user_id`) values (1,1,1),(2,2,2),(3,3,3);

/*Table structure for table `time_plan` */

DROP TABLE IF EXISTS `time_plan`;

CREATE TABLE `time_plan` (
  `id` bigint(20) NOT NULL,
  `owner_type` int(11) default NULL,
  `type` int(11) default NULL,
  `begin_time` datetime default NULL,
  `repeat_time` varchar(50) default NULL,
  `select_week` varchar(50) default NULL,
  `select_day` varchar(50) default NULL,
  `interval_time` int(11) default NULL,
  `state` bit(1) default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `time_plan` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
