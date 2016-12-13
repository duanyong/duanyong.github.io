-- MySQL dump 10.13  Distrib 5.6.20, for osx10.10 (x86_64)
--
-- Host: localhost    Database: tonghua
-- ------------------------------------------------------
-- Server version	5.6.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `zone_id` int(11) unsigned DEFAULT '0' COMMENT '地址的省份 城市 行政区',
  `current` varchar(255) DEFAULT '' COMMENT '用户默认的当前使用地址',
  `alias` varchar(255) DEFAULT NULL,
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asset`
--

DROP TABLE IF EXISTS `asset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asset` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `code` char(12) NOT NULL DEFAULT '' COMMENT '资产的编码',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '资产的名称',
  `trade_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '所属平台',
  `type_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '资产类型',
  `use_user` int(11) unsigned DEFAULT NULL COMMENT '使用人',
  `use_outdate` date DEFAULT NULL COMMENT '到期使用日期',
  `area` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '附件的类型',
  `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '资产单价',
  `number` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '资产数量',
  `discount` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '打折数量',
  `unit_ids` varchar(255) DEFAULT NULL COMMENT '资源所拥有的单位',
  `pos` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `desc` varchar(255) DEFAULT '' COMMENT '资产描述',
  `source_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '添加的用户',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '数据状态',
  PRIMARY KEY (`id`),
  KEY `idx_code` (`code`),
  KEY `idx_name` (`ctime`,`name`),
  KEY `idx_type` (`type_id`),
  KEY `idx_total` (`discount`),
  KEY `idx_size` (`area`),
  KEY `idx_status` (`status`),
  KEY `idx_trade` (`trade_id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='hd name@201210zhiku_attachment';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset`
--

LOCK TABLES `asset` WRITE;
/*!40000 ALTER TABLE `asset` DISABLE KEYS */;
/*!40000 ALTER TABLE `asset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attachment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'primary attachment',
  `table` varchar(64) NOT NULL DEFAULT '' COMMENT '附件对应的表名',
  `tid` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '附件对应的主键',
  `name` varchar(512) NOT NULL DEFAULT '' COMMENT '附件的名称',
  `size` int(11) DEFAULT '0' COMMENT '附件的大小',
  `type` varchar(512) DEFAULT '' COMMENT '附件的类型',
  `pos` tinyint(4) unsigned DEFAULT '0' COMMENT '附件排序',
  `desc` varchar(512) DEFAULT '' COMMENT '附件描述',
  `md5` char(32) DEFAULT '' COMMENT '附件MD5值',
  `url` varchar(512) DEFAULT '' COMMENT '附件下载地址',
  `path` varchar(512) DEFAULT '' COMMENT '附件路径',
  `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '时间',
  `status` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '数据状态',
  PRIMARY KEY (`id`),
  KEY `idx_table` (`table`),
  KEY `idx_tid` (`tid`),
  KEY `idx_url` (`url`(333)),
  KEY `idx_time` (`time`),
  KEY `idx_status` (`status`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='hd name@201210zhiku_attachment';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachment`
--

LOCK TABLES `attachment` WRITE;
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `top_id` int(11) unsigned NOT NULL DEFAULT '0',
  `parent_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '父类名称',
  `trade_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '平台类别',
  `name` varchar(45) DEFAULT NULL COMMENT '样式名称',
  `alias` varchar(45) DEFAULT NULL COMMENT '类别别名',
  `css` varchar(45) DEFAULT NULL COMMENT '样式代码',
  `css1` varchar(45) DEFAULT NULL COMMENT '样式代码',
  `css2` varchar(45) DEFAULT NULL COMMENT '样式代码',
  `zone` smallint(5) unsigned DEFAULT '0',
  `pos` smallint(5) unsigned DEFAULT '0',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_top` (`top_id`),
  KEY `idx_parent` (`parent_id`),
  KEY `idx_status` (`status`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crumb`
--

DROP TABLE IF EXISTS `crumb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crumb` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) unsigned NOT NULL DEFAULT '0',
  `name` varchar(255) DEFAULT NULL COMMENT '页面名称',
  `hidden` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `uri` varchar(255) DEFAULT NULL COMMENT '页面地址',
  `auth_type_ids` varchar(255) DEFAULT NULL COMMENT '可访问的平台',
  `auth_group_ids` varchar(255) DEFAULT NULL COMMENT '可访问的组',
  `class1` varchar(45) DEFAULT NULL,
  `class2` varchar(45) DEFAULT NULL,
  `class3` varchar(45) DEFAULT NULL,
  `pos` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `source_id` int(11) DEFAULT NULL COMMENT '添加的用户的主键',
  `source_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_status` (`status`),
  KEY `idx_url` (`uri`),
  KEY `idx_parent` (`parent_id`),
  KEY `idx_group` (`auth_group_ids`),
  KEY `idx_type` (`auth_type_ids`),
  KEY `idx_hidden` (`hidden`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crumb`
--

LOCK TABLES `crumb` WRITE;
/*!40000 ALTER TABLE `crumb` DISABLE KEYS */;
/*!40000 ALTER TABLE `crumb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(45) DEFAULT '' COMMENT '部门名称',
  `type_id` int(10) unsigned DEFAULT '0' COMMENT '部门类型：实体组织或虚拟组织',
  `leader_id` int(10) unsigned DEFAULT '0' COMMENT '主管人员',
  `deputy_id` int(10) unsigned DEFAULT '0' COMMENT '副手人员',
  `assist_id` int(10) unsigned DEFAULT '0' COMMENT '助手人员',
  `count` smallint(5) unsigned DEFAULT '0' COMMENT '人员总数',
  `desc` varchar(45) DEFAULT '',
  `time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` tinyint(4) unsigned DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department_staff`
--

DROP TABLE IF EXISTS `department_staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department_staff` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `department_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '主管人员',
  `title_id` int(10) unsigned DEFAULT '0' COMMENT '职位名称',
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '所属人员',
  `desc` varchar(45) DEFAULT NULL,
  `time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '加入时间',
  `status` tinyint(3) unsigned DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_depart_user` (`department_id`,`user_id`),
  KEY `idx_depart` (`department_id`),
  KEY `idx_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department_staff`
--

LOCK TABLES `department_staff` WRITE;
/*!40000 ALTER TABLE `department_staff` DISABLE KEYS */;
/*!40000 ALTER TABLE `department_staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(125) NOT NULL DEFAULT '' COMMENT '文件名称',
  `type` varchar(64) DEFAULT '' COMMENT '文件类型',
  `size` int(10) unsigned DEFAULT '0' COMMENT '文件大小',
  `path` varchar(256) DEFAULT '' COMMENT '存储路径',
  `desc` varchar(256) DEFAULT '',
  `time` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) unsigned DEFAULT '0' COMMENT '数据状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_card`
--

DROP TABLE IF EXISTS `time_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `time_card` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '考勤卡',
  `type_id` int(11) unsigned DEFAULT '0' COMMENT '卡类别',
  `user_id` int(11) unsigned DEFAULT '0' COMMENT '受卡的用户',
  `cardno` varchar(45) DEFAULT '' COMMENT '合同号',
  `desc` varchar(45) NOT NULL DEFAULT '',
  `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '数据状态',
  PRIMARY KEY (`id`),
  KEY `idx_status` (`status`),
  KEY `idx_cardno` (`cardno`),
  KEY `idx_user` (`user_id`),
  KEY `idx_user_type` (`type_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门禁系统';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_card`
--

LOCK TABLES `time_card` WRITE;
/*!40000 ALTER TABLE `time_card` DISABLE KEYS */;
/*!40000 ALTER TABLE `time_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_device`
--

DROP TABLE IF EXISTS `time_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `time_device` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '考勤机',
  `sn` varchar(125) DEFAULT '' COMMENT '设备号',
  `ip` varchar(256) DEFAULT '000.000.000.000',
  `title` varchar(125) DEFAULT NULL,
  `desc` varchar(256) DEFAULT '' COMMENT '设备的描述',
  `time` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) unsigned DEFAULT '0' COMMENT '设备状态',
  PRIMARY KEY (`id`),
  KEY `idx_status` (`status`),
  KEY `idx_device` (`sn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门禁系统';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_device`
--

LOCK TABLES `time_device` WRITE;
/*!40000 ALTER TABLE `time_device` DISABLE KEYS */;
/*!40000 ALTER TABLE `time_device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_log`
--

DROP TABLE IF EXISTS `time_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `time_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `time_device_id` int(11) unsigned DEFAULT NULL COMMENT '用户所刷的考勤机',
  `time_card_id` int(11) unsigned DEFAULT '0' COMMENT '用户在考勤设备上的卡号',
  `user_id` int(11) unsigned DEFAULT NULL,
  `desc` varchar(256) DEFAULT '' COMMENT '备注',
  `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '打卡时间',
  `status` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '数据状态',
  PRIMARY KEY (`id`),
  KEY `idx_device` (`status`,`time_device_id`,`user_id`),
  KEY `idx_user_time` (`time`,`user_id`),
  KEY `idx_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门禁系统';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_log`
--

LOCK TABLES `time_log` WRITE;
/*!40000 ALTER TABLE `time_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `time_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL DEFAULT 'no@mail.com' COMMENT '登录系统的用户名称',
  `password` char(32) NOT NULL DEFAULT 'nopassword' COMMENT '登录系统的用户密码',
  `firstname` varchar(45) NOT NULL COMMENT '显示在系统之中的名',
  `lastname` varchar(45) NOT NULL COMMENT '显示在系统之中的姓',
  `pinyin` varchar(45) DEFAULT '',
  `staffno` varchar(45) DEFAULT '' COMMENT '员工编号',
  `mobile` varchar(45) DEFAULT NULL COMMENT '用户的手机号',
  `sex_id` int(11) unsigned DEFAULT '0' COMMENT '用户性别',
  `school_id` int(11) unsigned DEFAULT '0' COMMENT '最后一次毕业学校',
  `major_id` int(11) unsigned DEFAULT '0' COMMENT '毕业专业',
  `qq` varchar(45) DEFAULT NULL COMMENT 'QQ号码',
  `weixin` varchar(45) DEFAULT NULL COMMENT '微信号码',
  `weibo` varchar(45) DEFAULT NULL,
  `salt` varchar(45) DEFAULT 'tonghua' COMMENT '加密盐值',
  `login_salt` char(32) DEFAULT NULL COMMENT '当次登录的盐值',
  `login_intime` datetime DEFAULT NULL COMMENT '当次登录的时间',
  `login_outime` datetime DEFAULT NULL COMMENT '当次登出的时间',
  `desc` varchar(256) DEFAULT '',
  `time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` tinyint(4) unsigned DEFAULT '0' COMMENT '员工状态',
  PRIMARY KEY (`id`),
  KEY `idx_username` (`username`),
  KEY `idx_mobile` (`mobile`),
  KEY `idx_pinyin` (`pinyin`),
  KEY `idx_staff_no` (`staffno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-24 18:02:11
