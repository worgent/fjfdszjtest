-- MySQL dump 10.11
--
-- Host: localhost    Database: womobile
-- ------------------------------------------------------
-- Server version	5.0.45-community-nt

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
-- Table structure for table `t_archives_menu`
--

DROP TABLE IF EXISTS `t_archives_menu`;
CREATE TABLE `t_archives_menu` (
  `id` varchar(20) NOT NULL COMMENT '主键标识',
  `name` varchar(50) default NULL COMMENT '菜单名称',
  `url` varchar(100) default NULL COMMENT '地址链接的url',
  `img` varchar(100) default NULL COMMENT '图片:名称,前缀显程序自动处理',
  `orderid` int(11) default NULL COMMENT '分为四位:1菜单级别,2功能模块级别,3-4预留',
  `powvalue` int(11) default NULL COMMENT '9:权限值最高,1:权限值最低\r\n            x<9:都可见',
  `layerid` char(2) default NULL COMMENT '1:父结点,2:子结点'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动态生成html标签?????单';

--
-- Dumping data for table `t_archives_menu`
--

LOCK TABLES `t_archives_menu` WRITE;
/*!40000 ALTER TABLE `t_archives_menu` DISABLE KEYS */;
INSERT INTO `t_archives_menu` VALUES ('1','系统','','',1000,9,'1'),('2','','test.do','user1_mobilephone.png',1001,9,'2'),('3','','','',2000,9,'1'),('4','','test.do','user1_mobilephone.png',3000,9,'2'),('5','','test.do','user1_mobilephone.png',3000,9,'2'),('6','','test.do','user1_mobilephone.png',3000,9,'2');
/*!40000 ALTER TABLE `t_archives_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_archives_user`
--

DROP TABLE IF EXISTS `t_archives_user`;
CREATE TABLE `t_archives_user` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `code` varchar(40) default NULL COMMENT '用户名',
  `name` varchar(20) default NULL COMMENT '姓名',
  `passwd` char(32) default NULL COMMENT '密码\r\n            ＭＤ５有３２位版本的算法，１６位版本的算法',
  `bill_type` char(2) default NULL COMMENT '0:未审\r\n            1:已审',
  `bill_state` char(2) default NULL COMMENT '0：删除\r\n            1：在用',
  `create_date` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';

--
-- Dumping data for table `t_archives_user`
--

LOCK TABLES `t_archives_user` WRITE;
/*!40000 ALTER TABLE `t_archives_user` DISABLE KEYS */;
INSERT INTO `t_archives_user` VALUES ('1','admini','admini','admini','1','1','2010-11-04 00:00:00');
/*!40000 ALTER TABLE `t_archives_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_dailywork`
--

DROP TABLE IF EXISTS `t_dailywork`;
CREATE TABLE `t_dailywork` (
  `id` varchar(20) NOT NULL,
  `title` varchar(100) default NULL,
  `dailyworktype` varchar(20) default NULL COMMENT '字典表中增加 日常工作类别={由用户自定义}\r\n            本表中填入其值。',
  `missiongrade` varchar(10) default NULL COMMENT '字典表中增加  任务级别={日常任务、紧急任务、特殊任务}\r\n            在本表中，直接填入任务级别值。',
  `attachment` varchar(100) default NULL COMMENT '上传文件，此字段记录文件所在相对目录及文件名',
  `target` varbinary(20) default NULL COMMENT '字典表中定义 工作指标={日、周、月}\r\n            本表中填入值',
  `targetvalue` smallint(6) default NULL COMMENT '手工录入，只能是整数',
  `feedbackpattern` varchar(20) default NULL COMMENT '关键反馈单的ID编号',
  `makedatetime` datetime default NULL,
  `maker` varchar(20) default NULL,
  `remark` varchar(200) default NULL,
  `executors` text COMMENT '选取各部门中的员工，员工之间以半角逗号分隔，最后一个员工不加逗号，如”张三,李四,王五“',
  `state` varchar(10) default NULL COMMENT '新任务、已审核、处理中、已中止、已完成、已归档\r\n            除了新任务外，其他状态不允许可删除任务单,已审核状态执行人手机就可收到任务，执行人读取后返回处理中，任务可以由创建人标记已中止，已完成，已归档',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日常工作表';

--
-- Dumping data for table `t_dailywork`
--

LOCK TABLES `t_dailywork` WRITE;
/*!40000 ALTER TABLE `t_dailywork` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_dailywork` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_dailyworkexcute`
--

DROP TABLE IF EXISTS `t_dailyworkexcute`;
CREATE TABLE `t_dailyworkexcute` (
  `id` varchar(20) default NULL,
  `pid` varchar(20) default NULL,
  `executorid` varchar(20) default NULL,
  `status` smallint(6) default NULL COMMENT '处理中=0，已完成=1，已评份=9',
  `score` int(11) default NULL,
  `remark` varchar(200) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='执行人列表';

--
-- Dumping data for table `t_dailyworkexcute`
--

LOCK TABLES `t_dailyworkexcute` WRITE;
/*!40000 ALTER TABLE `t_dailyworkexcute` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_dailyworkexcute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_declare`
--

DROP TABLE IF EXISTS `t_declare`;
CREATE TABLE `t_declare` (
  `id` varchar(20) NOT NULL,
  `title` varchar(100) default NULL,
  `tomonitortype` varchar(20) default NULL COMMENT '字典表中增加 日常工作类别={由用户自定义}\r\n            本表中填入其值。',
  `missiongrade` varchar(10) default NULL COMMENT '字典表中增加  任务级别={日常任务、紧急任务、特殊任务}\r\n            在本表中，直接填入任务级别值。',
  `attachment` varchar(100) default NULL COMMENT '上传文件，此字段记录文件所在相对目录及文件名',
  `target` varbinary(20) default NULL COMMENT '字典表中定义 工作指标={日、周、月}\r\n            本表中填入值',
  `targetvalue` smallint(6) default NULL COMMENT '手工录入，只能是整数',
  `declarepattern` varchar(20) default NULL COMMENT '关键反馈单的ID编号',
  `makedatetime` datetime default NULL,
  `maker` varchar(20) default NULL,
  `remark` varchar(200) default NULL,
  `executors` text COMMENT '选取各部门中的员工，员工之间以半角逗号分隔，最后一个员工不加逗号，如”张三,李四,王五“',
  `state` varchar(10) default NULL COMMENT '新任务、已审核、处理中、已中止、已完成、已归档\r\n            除了新任务外，其他状态不允许可删除任务单,已审核状态执行人手机就可收到任务，执行人读取后返回处理中，任务可以由创建人标记已中止，已完成，已归档',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='事项申报';

--
-- Dumping data for table `t_declare`
--

LOCK TABLES `t_declare` WRITE;
/*!40000 ALTER TABLE `t_declare` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_declare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_declareexcute`
--

DROP TABLE IF EXISTS `t_declareexcute`;
CREATE TABLE `t_declareexcute` (
  `id` varchar(20) default NULL,
  `pid` varchar(20) default NULL,
  `executorid` varchar(20) default NULL,
  `status` smallint(6) default NULL COMMENT '处理中=0，已完成=1，已评份=9',
  `score` int(11) default NULL,
  `remark` varchar(200) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='执行人列表';

--
-- Dumping data for table `t_declareexcute`
--

LOCK TABLES `t_declareexcute` WRITE;
/*!40000 ALTER TABLE `t_declareexcute` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_declareexcute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_dictionary`
--

DROP TABLE IF EXISTS `t_dictionary`;
CREATE TABLE `t_dictionary` (
  `Id` varchar(20) NOT NULL,
  `Name` varchar(50) default NULL,
  `Remark` varchar(100) default NULL,
  `isSys` smallint(1) default '0' COMMENT '瀹氫箟绫诲瀷锛�1.绯荤粺瀹氫箟锛�0.鐢ㄦ埛鑷畾涔�)锛�',
  `SysDictId` varchar(20) default NULL COMMENT '绯荤粺瀛楀吀缂栧彿',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='鏁版嵁瀛楀吀琛�';

--
-- Dumping data for table `t_dictionary`
--

LOCK TABLES `t_dictionary` WRITE;
/*!40000 ALTER TABLE `t_dictionary` DISABLE KEYS */;
INSERT INTO `t_dictionary` VALUES ('1','閲囬泦绫诲埆',NULL,2,NULL),('2','鏈嶅姟鎬佸害',NULL,2,NULL),('3','鍟嗗搧鍚嶇О',NULL,2,NULL),('4','浜嬩欢',NULL,1,'2001');
/*!40000 ALTER TABLE `t_dictionary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_dictionaryd`
--

DROP TABLE IF EXISTS `t_dictionaryd`;
CREATE TABLE `t_dictionaryd` (
  `id` varchar(20) default NULL,
  `pid` varchar(20) default NULL,
  `dictvalue` varchar(50) default NULL COMMENT 'label的名称根据父表的名称定',
  `state` smallint(6) default '1' COMMENT '启用=1,停用=0，默认启用'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_dictionaryd`
--

LOCK TABLES `t_dictionaryd` WRITE;
/*!40000 ALTER TABLE `t_dictionaryd` DISABLE KEYS */;
INSERT INTO `t_dictionaryd` VALUES ('1','1','璐ㄩ噺',1),('2','1','鏁伴噺',1),('3','2','闈炲父濂�',1),('4','2','寰堝ソ',1),('5','2','鏅��',1),('6','2','鎭跺姡',1),('7','3','楗煎共',1),('8','3','鐭挎硥姘�',1),('9','3','闈㈠寘',1),('10','4','鎻愪氦',1),('11','4','閫�鍥�',1),('12','4','閫氳繃',1),('13','4','涓嶉�氳繃',1);
/*!40000 ALTER TABLE `t_dictionaryd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_pattern`
--

DROP TABLE IF EXISTS `t_pattern`;
CREATE TABLE `t_pattern` (
  `id` varchar(20) NOT NULL COMMENT '缂栧彿',
  `PatternType` varchar(20) default NULL COMMENT '妯℃澘绫诲埆',
  `PatternName` varchar(100) default NULL COMMENT '妯℃澘鍚嶇О',
  `Remark` varchar(200) default NULL COMMENT '鎻忚堪',
  `Maker` varchar(20) default NULL COMMENT '鍒涘缓浜�',
  `MakeDatetime` datetime default NULL COMMENT '鍒涘缓鏃堕棿',
  `State` smallint(6) default NULL COMMENT '鐘舵��',
  `Events` varchar(100) default NULL COMMENT '浜嬩欢',
  `TableName` varchar(20) default NULL COMMENT '鐗╃悊琛ㄥ悕绉�',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_pattern`
--

LOCK TABLES `t_pattern` WRITE;
/*!40000 ALTER TABLE `t_pattern` DISABLE KEYS */;
INSERT INTO `t_pattern` VALUES ('2010120500000106','2','鏋楀湥濡�1','baba','鏋楀湥濡傜紪鍙�','2010-12-05 20:26:37',1,'10,11,12','u_0081'),('2010120500000107','2','鏋楀湥濡�1','baba','鏋楀湥濡傜紪鍙�','2010-12-05 20:27:39',1,'10,11,12','u_0082'),('2010120500000108','1','sss1','ddsababa','鏋楀湥濡傜紪鍙�','2010-12-05 20:30:05',1,'10,11,13','u_0083'),('2010120500000110','1','aaba','eeeaba','鏋楀湥濡傜紪鍙�','2010-12-05 20:31:46',1,'10,11,12,13','u_0085'),('2010120500000111','1','aaabaaa','dddsaa','鏋楀湥濡傜紪鍙�','2010-12-05 20:36:16',1,'10,11,13','u_0086'),('2010120500000112','1','妯℃澘3','aaaba','鏋楀湥濡傜紪鍙�','2010-12-05 20:36:17',1,'10,11,13','u_0087'),('2010120500000113','1','sss','					ssss\r\n					','鏋楀湥濡�','2010-12-05 22:19:00',1,'10,11,13','u_0092'),('2010120500000114','1','sss','																																										ssss\r\n						\r\n					\r\n					\r\n					\r\n					\r\n					\r\n					\r\n					','鏋楀湥濡�','2010-12-05 22:56:01',1,'10,11,13','u_0103');
/*!40000 ALTER TABLE `t_pattern` ENABLE KEYS */;
UNLOCK TABLES;

DELIMITER ;;
/*!50003 SET SESSION SQL_MODE="" */;;
/*!50003 CREATE */ /*!50017 DEFINER=`root`@`localhost` */ /*!50003 TRIGGER `trigger_delCustom` BEFORE DELETE ON `t_pattern` FOR EACH ROW delete from t_patternd where pid = OLD.id */;;

DELIMITER ;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;

--
-- Table structure for table `t_patternd`
--

DROP TABLE IF EXISTS `t_patternd`;
CREATE TABLE `t_patternd` (
  `id` int(11) NOT NULL auto_increment,
  `pid` varchar(20) default NULL,
  `fieldseqn` smallint(6) default NULL,
  `fielddesc` varchar(20) default NULL,
  `fieldname` varchar(20) default NULL,
  `fieldtype` varchar(10) default NULL COMMENT '字符、数字、日期、枚举、位置、照片\r\n            当选择字符时，字段长度只能整数并大于1，选择数字时，牧师字段类型为number型，长度允许有小数位，选择位置时，为字符型??长度固定30，经纬度按经续度之间以半角逗号分隔，如45.345233,118.342453。选择照片时，固定为字符串，长度200，用于保存照片的相对路径与文件。选择',
  `fieldlength` varchar(10) default NULL,
  `remark` varchar(100) default NULL,
  `fieldenum` varchar(200) default NULL,
  `state` smallint(6) default NULL COMMENT '启用=1，停用=0，默认启用\r\n            此字段为备用字段，目前暂不使用',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='生成的物理表中必须有以下几个字段\r\n           编号、父表编号、执行人';

--
-- Dumping data for table `t_patternd`
--

LOCK TABLES `t_patternd` WRITE;
/*!40000 ALTER TABLE `t_patternd` DISABLE KEYS */;
INSERT INTO `t_patternd` VALUES (12,NULL,1,'鏋楀湥濡�','lsri','varchar','1',NULL,NULL,1),(13,NULL,1,'鏋楀湥濡�','lsr1','varchar','1',NULL,NULL,1),(14,NULL,1,'鏋楀湥濡�','lsr1','varchar','1',NULL,NULL,1),(15,NULL,1,'鏋�','l1','varchar','1',NULL,NULL,1),(16,NULL,1,'鏋�','l1','varchar','1',NULL,NULL,1),(17,NULL,1,'鏋�','l1','varchar','1',NULL,NULL,1),(18,NULL,1,'鏋�','l1','varchar','1',NULL,NULL,1),(19,NULL,1,'鏋�','l1','varchar','1',NULL,NULL,1),(20,NULL,2,'鍦�','s2','varchar','1',NULL,NULL,1),(25,'2010120500000106',1,'','lsrr','varchar','2',NULL,NULL,1),(26,'2010120500000107',1,'','lsrr','varchar','2',NULL,NULL,1),(27,'2010120500000110',1,'','ss','varchar','1',NULL,NULL,1),(28,'2010120500000111',1,'aaaaaa','AAAAAA','varchar','1',NULL,NULL,1),(29,'2010120500000112',1,'','lsr','varchar','1',NULL,NULL,1),(30,'2010120500000113',1,'鏋�','l','varchar','1',NULL,NULL,1),(41,'2010120500000114',1,'鏋�','l','varchar','2',NULL,NULL,1);
/*!40000 ALTER TABLE `t_patternd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_system_group`
--

DROP TABLE IF EXISTS `t_system_group`;
CREATE TABLE `t_system_group` (
  `groupId` varchar(20) NOT NULL,
  `groupName` varchar(255) NOT NULL,
  `groupDesc` varchar(255) NOT NULL,
  `createId` varchar(20) NOT NULL,
  PRIMARY KEY  (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_system_group`
--

LOCK TABLES `t_system_group` WRITE;
/*!40000 ALTER TABLE `t_system_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_system_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_system_grouprole`
--

DROP TABLE IF EXISTS `t_system_grouprole`;
CREATE TABLE `t_system_grouprole` (
  `GroupRoleId` int(11) NOT NULL,
  `GroupId` int(11) NOT NULL,
  `RoleId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_system_grouprole`
--

LOCK TABLES `t_system_grouprole` WRITE;
/*!40000 ALTER TABLE `t_system_grouprole` DISABLE KEYS */;
INSERT INTO `t_system_grouprole` VALUES (1,1,1),(1,1,204);
/*!40000 ALTER TABLE `t_system_grouprole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_system_permission`
--

DROP TABLE IF EXISTS `t_system_permission`;
CREATE TABLE `t_system_permission` (
  `permissionId` varchar(20) NOT NULL,
  `permissionName` varchar(100) default NULL COMMENT '鏉冮檺鍚嶇О',
  `permissionResource` varchar(50) default NULL COMMENT '鏉冮檺璧勬簮(/login)',
  `action` varchar(255) default NULL COMMENT '鏂规硶',
  `parentPermissionId` int(11) default NULL COMMENT '涓婄骇鏉冮檺缂栧彿',
  `isMenu` smallint(1) default NULL COMMENT '鏄惁鏄彍鍗�(鑻ユ槸鐨勮瘽锛屼笂绾т篃蹇呴』鏄彍鍗�)',
  `permissionOrder` int(11) default NULL COMMENT '椤哄簭鍙�',
  `CreateId` int(11) default NULL COMMENT '鍒涘缓鑰�',
  PRIMARY KEY  (`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_system_permission`
--

LOCK TABLES `t_system_permission` WRITE;
/*!40000 ALTER TABLE `t_system_permission` DISABLE KEYS */;
INSERT INTO `t_system_permission` VALUES ('0','绯荤粺鑿滃崟',NULL,NULL,-1,1,1,NULL),('1000','涓氬姟妯″潡','',NULL,0,1,1000,1),('2010120700000007','permission','permission','list',1000,1,1,1);
/*!40000 ALTER TABLE `t_system_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_system_role`
--

DROP TABLE IF EXISTS `t_system_role`;
CREATE TABLE `t_system_role` (
  `roleId` varchar(20) NOT NULL,
  `roleName` varchar(100) default NULL,
  `createId` varchar(20) default NULL,
  PRIMARY KEY  (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_system_role`
--

LOCK TABLES `t_system_role` WRITE;
/*!40000 ALTER TABLE `t_system_role` DISABLE KEYS */;
INSERT INTO `t_system_role` VALUES ('2010120600000002','瑙掕壊涓�','1'),('2010120600000005','瑙掕壊浜�','1'),('2010120600000008','瑙掕壊涓�','1'),('2010120700000011','閻╁瓨甯撮崣鍌欑瑢','1');
/*!40000 ALTER TABLE `t_system_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_system_rp`
--

DROP TABLE IF EXISTS `t_system_rp`;
CREATE TABLE `t_system_rp` (
  `RolePermissionId` varchar(20) NOT NULL,
  `RoleId` varchar(20) default NULL,
  `PermissionId` varchar(20) default NULL,
  PRIMARY KEY  (`RolePermissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_system_rp`
--

LOCK TABLES `t_system_rp` WRITE;
/*!40000 ALTER TABLE `t_system_rp` DISABLE KEYS */;
INSERT INTO `t_system_rp` VALUES ('1','1','0'),('2','1','1000'),('2010120600000003','2010120600000002','0'),('2010120600000004','2010120600000002','1000'),('2010120600000006','2010120600000005','0'),('2010120600000007','2010120600000005','1000'),('2010120600000009','2010120600000008','0'),('2010120600000010','2010120600000008','1000'),('2010120700000008','1','2010120700000007'),('2010120700000012','2010120700000011','0'),('2010120700000013','2010120700000011','1000');
/*!40000 ALTER TABLE `t_system_rp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_system_user`
--

DROP TABLE IF EXISTS `t_system_user`;
CREATE TABLE `t_system_user` (
  `userId` int(11) NOT NULL auto_increment,
  `groupId` int(11) default NULL COMMENT '鐢ㄦ埛缁勭紪鍙�',
  `userName` varchar(50) NOT NULL COMMENT '鐢ㄦ埛鍚�',
  `passwd` varchar(40) default NULL COMMENT '瀵嗙爜鏄庢枃',
  `repasswd` varchar(40) default NULL COMMENT '瀵嗙爜瀵嗘枃',
  PRIMARY KEY  (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_system_user`
--

LOCK TABLES `t_system_user` WRITE;
/*!40000 ALTER TABLE `t_system_user` DISABLE KEYS */;
INSERT INTO `t_system_user` VALUES (1,1,'admini','admini','B1C21019AFD435194216C87F414E6CCE');
/*!40000 ALTER TABLE `t_system_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_tomonitor`
--

DROP TABLE IF EXISTS `t_tomonitor`;
CREATE TABLE `t_tomonitor` (
  `id` varchar(20) NOT NULL,
  `title` varchar(100) default NULL,
  `tomonitortype` varchar(20) default NULL COMMENT '字典表中增加 日常工作类别={由用户自定义}\r\n            本表中填入其值。',
  `missiongrade` varchar(10) default NULL COMMENT '字典表中增加  任务级别={日常任务、紧急任务、特殊任务}\r\n            在本表中，直接填入任务级别值。',
  `attachment` varchar(100) default NULL COMMENT '上传文件，此字段记录文件所在相对目录及文件名',
  `target` varbinary(20) default NULL COMMENT '字典表中定义 工作指标={日、周、月}\r\n            本表中填入值',
  `targetvalue` smallint(6) default NULL COMMENT '手工录入，只能是整数',
  `feedbackpattern` varchar(20) default NULL COMMENT '关键反馈单的ID编号',
  `makedatetime` datetime default NULL,
  `maker` varchar(20) default NULL,
  `remark` varchar(200) default NULL,
  `executors` text COMMENT '选取各部门中的员工，员工之间以半角逗号分隔，最后一个员工不加逗号，如”张三,李四,王五“',
  `state` varchar(10) default NULL COMMENT '新任务、已审核、处理中、已中止、已完成、已归档\r\n            除了新任务外，其他状态不允许可删除任务单,已审核状态执行人手机就可收到任务，执行人读取后返回处理中，任务可以由创建人标记已中止，已完成，已归档',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='督办工作';

--
-- Dumping data for table `t_tomonitor`
--

LOCK TABLES `t_tomonitor` WRITE;
/*!40000 ALTER TABLE `t_tomonitor` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_tomonitor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_tomonitorexcute`
--

DROP TABLE IF EXISTS `t_tomonitorexcute`;
CREATE TABLE `t_tomonitorexcute` (
  `id` varchar(20) default NULL,
  `pid` varchar(20) default NULL,
  `executorid` varchar(20) default NULL,
  `status` smallint(6) default NULL COMMENT '处理中=0，已完成=1，已评份=9',
  `score` int(11) default NULL,
  `remark` varchar(200) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='执行人列表';

--
-- Dumping data for table `t_tomonitorexcute`
--

LOCK TABLES `t_tomonitorexcute` WRITE;
/*!40000 ALTER TABLE `t_tomonitorexcute` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_tomonitorexcute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `td_sequences`
--

DROP TABLE IF EXISTS `td_sequences`;
CREATE TABLE `td_sequences` (
  `seq_name` varchar(200) NOT NULL default '',
  `value` int(11) default '0',
  PRIMARY KEY  (`seq_name`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='Sequences?';

--
-- Dumping data for table `td_sequences`
--

LOCK TABLES `td_sequences` WRITE;
/*!40000 ALTER TABLE `td_sequences` DISABLE KEYS */;
INSERT INTO `td_sequences` VALUES ('t_dailywork',36),('t_declare',5),('t_pattern',114),('t_pattern_name',103),('t_permission',8),('t_role',13),('t_toMonitor',8);
/*!40000 ALTER TABLE `td_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `u_0081`
--

DROP TABLE IF EXISTS `u_0081`;
CREATE TABLE `u_0081` (
  `id` int(11) NOT NULL auto_increment,
  `executorId` varchar(20) default NULL,
  `parentId` varchar(20) default NULL,
  `stateRemark` varchar(200) default NULL,
  `state` smallint(6) default '1',
  `lsrr` varchar(2) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `u_0081`
--

LOCK TABLES `u_0081` WRITE;
/*!40000 ALTER TABLE `u_0081` DISABLE KEYS */;
/*!40000 ALTER TABLE `u_0081` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `u_0082`
--

DROP TABLE IF EXISTS `u_0082`;
CREATE TABLE `u_0082` (
  `id` int(11) NOT NULL auto_increment,
  `executorId` varchar(20) default NULL,
  `parentId` varchar(20) default NULL,
  `stateRemark` varchar(200) default NULL,
  `state` smallint(6) default '1',
  `lsrr` varchar(2) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `u_0082`
--

LOCK TABLES `u_0082` WRITE;
/*!40000 ALTER TABLE `u_0082` DISABLE KEYS */;
/*!40000 ALTER TABLE `u_0082` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `u_0083`
--

DROP TABLE IF EXISTS `u_0083`;
CREATE TABLE `u_0083` (
  `id` int(11) NOT NULL auto_increment,
  `executorId` varchar(20) default NULL,
  `parentId` varchar(20) default NULL,
  `stateRemark` varchar(200) default NULL,
  `state` smallint(6) default '1',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `u_0083`
--

LOCK TABLES `u_0083` WRITE;
/*!40000 ALTER TABLE `u_0083` DISABLE KEYS */;
/*!40000 ALTER TABLE `u_0083` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `u_0085`
--

DROP TABLE IF EXISTS `u_0085`;
CREATE TABLE `u_0085` (
  `id` int(11) NOT NULL auto_increment,
  `executorId` varchar(20) default NULL,
  `parentId` varchar(20) default NULL,
  `stateRemark` varchar(200) default NULL,
  `state` smallint(6) default '1',
  `ss` varchar(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `u_0085`
--

LOCK TABLES `u_0085` WRITE;
/*!40000 ALTER TABLE `u_0085` DISABLE KEYS */;
/*!40000 ALTER TABLE `u_0085` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `u_0086`
--

DROP TABLE IF EXISTS `u_0086`;
CREATE TABLE `u_0086` (
  `id` int(11) NOT NULL auto_increment,
  `executorId` varchar(20) default NULL,
  `parentId` varchar(20) default NULL,
  `stateRemark` varchar(200) default NULL,
  `state` smallint(6) default '1',
  `AAAAAA` varchar(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `u_0086`
--

LOCK TABLES `u_0086` WRITE;
/*!40000 ALTER TABLE `u_0086` DISABLE KEYS */;
/*!40000 ALTER TABLE `u_0086` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `u_0087`
--

DROP TABLE IF EXISTS `u_0087`;
CREATE TABLE `u_0087` (
  `id` int(11) NOT NULL auto_increment,
  `executorId` varchar(20) default NULL,
  `parentId` varchar(20) default NULL,
  `stateRemark` varchar(200) default NULL,
  `state` smallint(6) default '1',
  `lsr` varchar(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `u_0087`
--

LOCK TABLES `u_0087` WRITE;
/*!40000 ALTER TABLE `u_0087` DISABLE KEYS */;
/*!40000 ALTER TABLE `u_0087` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `u_0089`
--

DROP TABLE IF EXISTS `u_0089`;
CREATE TABLE `u_0089` (
  `id` int(11) NOT NULL auto_increment,
  `executorId` varchar(20) default NULL,
  `parentId` varchar(20) default NULL,
  `stateRemark` varchar(200) default NULL,
  `state` smallint(6) default '1',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `u_0089`
--

LOCK TABLES `u_0089` WRITE;
/*!40000 ALTER TABLE `u_0089` DISABLE KEYS */;
/*!40000 ALTER TABLE `u_0089` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `u_0101`
--

DROP TABLE IF EXISTS `u_0101`;
CREATE TABLE `u_0101` (
  `id` int(11) NOT NULL auto_increment,
  `executorId` varchar(20) default NULL,
  `parentId` varchar(20) default NULL,
  `stateRemark` varchar(200) default NULL,
  `state` smallint(6) default '1',
  `aaa` varchar(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `u_0101`
--

LOCK TABLES `u_0101` WRITE;
/*!40000 ALTER TABLE `u_0101` DISABLE KEYS */;
/*!40000 ALTER TABLE `u_0101` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `u_0103`
--

DROP TABLE IF EXISTS `u_0103`;
CREATE TABLE `u_0103` (
  `id` int(11) NOT NULL auto_increment,
  `executorId` varchar(20) default NULL,
  `parentId` varchar(20) default NULL,
  `stateRemark` varchar(200) default NULL,
  `state` smallint(6) default '1',
  `l` varchar(2) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `u_0103`
--

LOCK TABLES `u_0103` WRITE;
/*!40000 ALTER TABLE `u_0103` DISABLE KEYS */;
/*!40000 ALTER TABLE `u_0103` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'womobile'
--
DELIMITER ;;
/*!50003 DROP FUNCTION IF EXISTS `fun_getParentPermission` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `fun_getParentPermission`(permissionId1 int) RETURNS int(11)
BEGIN
declare parentPermissionId1 int;
declare result_tmp varchar(8000) default '';
 select distinct permissionid into @a from t_system_rp d join
                (select a.roleid from t_system_grouprole a join (select groupid from t_system_user where userid=1) b 
                on a.groupid=b.groupid) c on d.roleid=c.roleid;
  lab1:while true do
      select parentPermissionId into parentPermissionId1 from t_system_permission where permissionId=permissionId1;
     
      if parentPermissionId1='-1' or parentPermissionId1 is null  then
          leave LAB1;
      else
         set result_tmp=concat(parentPermissionId1,',',result_tmp);
         set permissionId1=parentPermissionId1;
      end if;
  end while lab1;
  return result_tmp;
END */;;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE*/;;
/*!50003 DROP FUNCTION IF EXISTS `seqformat` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `seqformat`(in_seq_name char(200)) RETURNS char(100) CHARSET gbk
BEGIN
	update td_sequences set value=value + 1 where seq_name =@in_seq_name;
	select ifnull(value,0) into @v_value from td_sequences where seq_name=@in_seq_name;
        select CURDATE()+0  into @v_date ;
        select lpad(concat(@v_value,''),8,'0') into @v_result;
        select concat(@v_date,@v_result) into @v_result;
        return @v_result;
END */;;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE*/;;
/*!50003 DROP FUNCTION IF EXISTS `seqTableName` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `seqTableName`() RETURNS char(100) CHARSET gbk
BEGIN
update td_sequences set value=value + 1 where seq_name ='t_pattern_name';
	select ifnull(value,0) into @v_value from td_sequences where seq_name='t_pattern_name';
       
        select lpad(concat(@v_value,''),4,'0') into @v_result;
        return concat('u_',@v_result);
END */;;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE*/;;
/*!50003 DROP PROCEDURE IF EXISTS `pb_delAllSubPermission` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `pb_delAllSubPermission`(in permissionId1 varchar(20))
BEGIN
call pb_getSubPermission(permissionId1,@a);
    if @a is not null then
    set @b=concat('\'',@a,'\'');
    end if;
 set @SqlCmd=concat("delete from t_system_permission where permissionId in(",@b,")");
PREPARE stmt FROM @SqlCmd;
EXECUTE stmt ;
END */;;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE*/;;
/*!50003 DROP PROCEDURE IF EXISTS `pb_delRPById` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `pb_delRPById`(in permissionId1 varchar(20))
BEGIN
    call pb_getSubPermission(permissionId1,@a);
    if @a is not null then
    set @b=concat('\'',@a,'\'');
    end if;
 set @SqlCmd=concat("delete from t_system_rp where permissionId in(",@b,")");
PREPARE stmt FROM @SqlCmd;
EXECUTE stmt ;
END */;;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE*/;;
/*!50003 DROP PROCEDURE IF EXISTS `pb_getSubPermission` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `pb_getSubPermission`(in permissionId1 varchar(20),out result varchar(1000))
BEGIN
declare done INT DEFAULT 0;
declare cur1 Cursor for  select permissionId  from t_system_permission where parentPermissionId=permissionId1;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
SET @@max_sp_recursion_depth = 10; 
set result=permissionId1;
open cur1;
    repeat
	fetch  cur1 into permissionId1;
	if not done then
           call pb_getSubPermission(permissionId1, @a);
           set result=concat(result,'\',\'',@a);
           
	   
        end  if;
    UNTIL done END REPEAT;
    close cur1;
END */;;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE*/;;
/*!50003 DROP PROCEDURE IF EXISTS `pb_getsuperPermission` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `pb_getsuperPermission`(in permissionId1 int(13))
BEGIN
declare SJ_PermissionId int(13);
declare SJ_PermissionOrder int(13);
declare permissionOrder1 int(13);
declare ismenu1 int(13);
declare result_tmp varchar(8000) default '';
  select ismenu into ismenu1 from t_system_permission where permissionId=permissionId1;
 
  if(ismenu1='1') then
   select permissionOrder into permissionOrder1 from t_system_permission where ismenu=1 and permissionId=permissionId1;
   insert into tmp_permission1(permissionId,permissionName,parentPermissionId,permissionResource,action,permissionorder)
	select permissionId,permissionName,parentPermissionId,permissionResource,action,permissionorder from t_system_permission where 
        permissionId=permissionId1; 
  end if;
  lab1:while true do
      select parentPermissionId into SJ_PermissionId from t_system_permission where permissionId=permissionId1;
      
     
      if SJ_PermissionId='-1' or SJ_PermissionId is null  then
          leave LAB1;
      else
	 select permissionOrder into SJ_PermissionOrder from t_system_permission where ismenu=1 and permissionId=permissionId1;
           
	insert into tmp_permission1(permissionId,permissionName,parentPermissionId,permissionResource,action,permissionorder)
	select permissionId,permissionName,parentPermissionId,permissionResource,action,permissionorder from t_system_permission where 
        permissionId=SJ_PermissionOrder; 
         set permissionId1=SJ_PermissionId;
      end if;
  end while lab1;
END */;;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE*/;;
/*!50003 DROP PROCEDURE IF EXISTS `pb_get_allPermission` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `pb_get_allPermission`(userid1 varchar(20))
BEGIN
declare done INT DEFAULT 0;
declare permissionId1 varchar(20);
DECLARE cur_permission CURSOR for
select f.permissionid from t_system_rp f join
(select a.roleid from t_system_grouprole a join (select groupid from t_system_user where userid=userid1) b 
on a.groupid=b.groupid) e on f.roleid=e.roleid;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
CREATE  temporary   TABLE   if not exists  tmp_permission1 (permissionId varchar(20),permissionName varchar(100),parentPermissionId varchar(20),permissionResource varchar(50),action varchar(255), permissionOrder int(13));
open cur_permission;
   repeat
	fetch  cur_permission into permissionId1;
	if not done then
          call pb_getsuperPermission(permissionId1);
        end  if;
  UNTIL done END REPEAT;
select distinct d.permissionId,d.permissionName,d.parentPermissionId,d.permissionResource,d.action,'0' as Checked from tmp_permission1 
	d order by permissionOrder;
drop table tmp_permission1;
END */;;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE*/;;
/*!50003 DROP PROCEDURE IF EXISTS `pb_get_menu_tree` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `pb_get_menu_tree`(userid1 varchar(20) )
BEGIN
declare done INT DEFAULT 0;
declare permissionId1 varchar(20);
declare permissionOrder1 int;
declare permissionIds varchar(2000);
DECLARE cur_permission CURSOR for
select permissionid from (
select distinct permissionid from t_system_rp d join
(select a.roleid from t_system_grouprole a join (select groupid from t_system_user where userid=userid1) b 
on a.groupid=b.groupid) c on d.roleid=c.roleid) t;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
CREATE  temporary   TABLE   if not exists  tmp_permission (permissionId varchar(20),permissionOrder int(13));
open cur_permission;
   repeat
	fetch  cur_permission into permissionId1;
	if not done then
          call proc_getAllSuperMenu(permissionId1);
        end  if;
  UNTIL done END REPEAT;
close cur_permission;
select * from ( select
    d.permissionId,d.permissionName,d.parentPermissionId,d.permissionResource,d.action,(
    SELECT COUNT(*) FROM ( 
        select permissionId from tmp_permission
    ) e WHERE e.permissionId = d.permissionId) as Checked from ( 
              (
                       select distinct f.permissionid from t_system_rp f join
                      (select a.roleid from t_system_grouprole a join (select groupid from t_system_user where userid=userid1) b 
                       on a.groupid=b.groupid) e on f.roleid=e.roleid 
               ) c Join t_system_permission d ON
    c.permissionId=d.permissionId and d.permissionId is not null) order by permissionOrder ) t
    where t.checked >0;
drop table tmp_permission;
END */;;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE*/;;
/*!50003 DROP PROCEDURE IF EXISTS `proc_getAllsuperMenu` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `proc_getAllsuperMenu`(in permissionId1 int(13))
BEGIN
declare SJ_PermissionId int(13);
declare SJ_PermissionOrder int(13);
declare permissionOrder1 int(13);
declare ismenu1 int(13);
declare result_tmp varchar(8000) default '';
  select ismenu into ismenu1 from t_system_permission where permissionId=permissionId1;
 
  if(ismenu1='1') then
   select permissionOrder into permissionOrder1 from t_system_permission where ismenu=1 and permissionId=permissionId1;
   insert into tmp_permission(permissionId,permissionOrder)values (permissionId1,permissionOrder1); 
  end if;
  lab1:while true do
      select parentPermissionId into SJ_PermissionId from t_system_permission where ismenu=1 and permissionId=permissionId1;
      
     
      if SJ_PermissionId='-1' or SJ_PermissionId is null  then
          leave LAB1;
      else
	 select permissionOrder into SJ_PermissionOrder from t_system_permission where ismenu=1 and permissionId=permissionId1;
         insert into tmp_permission(permissionId,permissionOrder)values (SJ_PermissionId,SJ_PermissionOrder);  
         
         set permissionId1=SJ_PermissionId;
      end if;
  end while lab1;
END */;;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE*/;;
/*!50003 DROP PROCEDURE IF EXISTS `pro_delPatternById` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `pro_delPatternById`(id1 varchar(20),out info varchar(10))
BEGIN
declare StrSql  Varchar(500);
set info=0;
start transaction;   
select tableName into @tableName from t_pattern where id=id1;
set StrSql=concat("drop table ",@tableName);
    set @a=StrSql;
    PREPARE stmt1 FROM @a;
        EXECUTE stmt1 ;
delete from t_pattern where id=id1;
delete from t_patternd where pid=id1;
 commit;
   set info='1';
END */;;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE*/;;
DELIMITER ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-12-07  2:45:34
