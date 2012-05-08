/*
Navicat MySQL Data Transfer

Source Server         : self
Source Server Version : 50045
Source Host           : localhost:3306
Source Database       : nhibernatedemo

Target Server Type    : MYSQL
Target Server Version : 50045
File Encoding         : 65001

Date: 2012-05-08 10:56:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `districtinfo`
-- ----------------------------
DROP TABLE IF EXISTS `districtinfo`;
CREATE TABLE `districtinfo` (
  `ID` varchar(40) NOT NULL,
  `DistrictName` varchar(50) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of districtinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `entity1`
-- ----------------------------
DROP TABLE IF EXISTS `entity1`;
CREATE TABLE `entity1` (
  `id` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entity1
-- ----------------------------

-- ----------------------------
-- Table structure for `funinfo`
-- ----------------------------
DROP TABLE IF EXISTS `funinfo`;
CREATE TABLE `funinfo` (
  `ID` char(16) NOT NULL,
  `orderCode` int(11) default NULL,
  `funName` varchar(50) NOT NULL,
  `imgSrc` varchar(50) default NULL,
  `funUrl` varchar(50) default NULL,
  `target` varchar(50) default NULL,
  `isPublic` smallint(6) NOT NULL,
  `isMenu` smallint(6) NOT NULL,
  `funState` varchar(50) NOT NULL,
  `createTime` datetime default NULL,
  `ParentId` char(1) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `ParentId` (`ParentId`),
  CONSTRAINT `FK4CCDAD09FC222D86` FOREIGN KEY (`ParentId`) REFERENCES `funinfo` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of funinfo
-- ----------------------------
INSERT INTO `funinfo` VALUES ('1', '1', '基础设置', '1', '1', '1', '1', '1', '1', '2011-01-01 00:00:00', null);
INSERT INTO `funinfo` VALUES ('2', '2', '机构管理', '1', '/Org/Index', '1', '1', '1', '1', '2011-01-01 00:00:00', '1');
INSERT INTO `funinfo` VALUES ('3', '3', '用户列表', '1', '/User/Index', '1', '1', '1', '1', '2011-01-01 00:00:00', '1');
INSERT INTO `funinfo` VALUES ('4', '4', '角色管理', '1', '/Role/Index', '1', '1', '11', '1', '2011-01-01 00:00:00', '1');

-- ----------------------------
-- Table structure for `gang`
-- ----------------------------
DROP TABLE IF EXISTS `gang`;
CREATE TABLE `gang` (
  `id` varchar(40) NOT NULL,
  `Name` varchar(255) default NULL,
  `Money` int(11) default NULL,
  `Boss` varchar(40) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `Boss` (`Boss`),
  KEY `Boss_2` (`Boss`),
  CONSTRAINT `FK8F9A912D96930D01` FOREIGN KEY (`Boss`) REFERENCES `player` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gang
-- ----------------------------

-- ----------------------------
-- Table structure for `organize`
-- ----------------------------
DROP TABLE IF EXISTS `organize`;
CREATE TABLE `organize` (
  `ID` varchar(16) NOT NULL,
  `organizeNo` varchar(50) NOT NULL,
  `organizeName` varchar(50) default NULL,
  `Address` varchar(50) default NULL,
  `Manager` varchar(50) default NULL,
  `organizeTel` varchar(50) default NULL,
  `organizeUrl` varchar(50) default NULL,
  `orderCode` int(11) default NULL,
  `haveSub` char(1) NOT NULL,
  `Amount` int(11) default NULL,
  `ParentId` char(1) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `ParentId` (`ParentId`),
  CONSTRAINT `FKF6A6882C2C301CC9` FOREIGN KEY (`ParentId`) REFERENCES `organize` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organize
-- ----------------------------
INSERT INTO `organize` VALUES ('1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for `player`
-- ----------------------------
DROP TABLE IF EXISTS `player`;
CREATE TABLE `player` (
  `Id` varchar(40) NOT NULL,
  `Name` varchar(255) default NULL,
  `Money` int(11) default NULL,
  `XP` int(11) default NULL,
  `memberOf` varchar(40) default NULL,
  PRIMARY KEY  (`Id`),
  KEY `memberOf` (`memberOf`),
  CONSTRAINT `FK86D0ADC6B86C8D8C` FOREIGN KEY (`memberOf`) REFERENCES `gang` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of player
-- ----------------------------
INSERT INTO `player` VALUES ('a9a8616d-1310-42c0-af27-54e4e3ffcdf0', '123', '30', '30', null);

-- ----------------------------
-- Table structure for `resource`
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `ID` varchar(40) NOT NULL,
  `resName` varchar(50) NOT NULL,
  `objectName` varchar(50) default NULL,
  `isVerify` smallint(6) NOT NULL,
  `resNote` varchar(150) default NULL,
  `orderCode` int(11) default NULL,
  `createTime` datetime default NULL,
  `funId` varchar(40) NOT NULL,
  PRIMARY KEY  (`ID`),
  KEY `funId` (`funId`),
  CONSTRAINT `FKCBF7F82B4946D30B` FOREIGN KEY (`funId`) REFERENCES `funinfo` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------

-- ----------------------------
-- Table structure for `rolefun`
-- ----------------------------
DROP TABLE IF EXISTS `rolefun`;
CREATE TABLE `rolefun` (
  `roleId` varchar(40) NOT NULL,
  `funId` varchar(40) NOT NULL,
  KEY `roleId` (`roleId`),
  KEY `funId` (`funId`),
  CONSTRAINT `FKA44B43F82A4F9C74` FOREIGN KEY (`roleId`) REFERENCES `roleinfo` (`ID`),
  CONSTRAINT `FKA44B43F84946D30B` FOREIGN KEY (`funId`) REFERENCES `funinfo` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rolefun
-- ----------------------------
INSERT INTO `rolefun` VALUES ('1', '1');
INSERT INTO `rolefun` VALUES ('1', '3');
INSERT INTO `rolefun` VALUES ('1', '3');
INSERT INTO `rolefun` VALUES ('1', '4');

-- ----------------------------
-- Table structure for `roleinfo`
-- ----------------------------
DROP TABLE IF EXISTS `roleinfo`;
CREATE TABLE `roleinfo` (
  `ID` varchar(40) NOT NULL,
  `parentRoleId` int(11) NOT NULL,
  `roleName` varchar(50) NOT NULL,
  `isEnable` smallint(6) NOT NULL,
  `roleNote` varchar(200) default NULL,
  `orderCode` int(11) default NULL,
  `createTime` datetime default NULL,
  `haveSub` smallint(6) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleinfo
-- ----------------------------
INSERT INTO `roleinfo` VALUES ('1', '1', '1', '1', '1', '1', '2010-01-01 00:00:00', '1');

-- ----------------------------
-- Table structure for `roleres`
-- ----------------------------
DROP TABLE IF EXISTS `roleres`;
CREATE TABLE `roleres` (
  `resId` varchar(40) NOT NULL,
  `roleId` varchar(40) NOT NULL,
  `funId` varchar(40) NOT NULL,
  KEY `roleId` (`roleId`),
  KEY `resId` (`resId`),
  KEY `funId` (`funId`),
  CONSTRAINT `FKBC64E6332A4F9C74` FOREIGN KEY (`roleId`) REFERENCES `roleinfo` (`ID`),
  CONSTRAINT `FKBC64E6334946D30B` FOREIGN KEY (`funId`) REFERENCES `funinfo` (`ID`),
  CONSTRAINT `FKBC64E6334CFDB09A` FOREIGN KEY (`resId`) REFERENCES `resource` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleres
-- ----------------------------

-- ----------------------------
-- Table structure for `system_department`
-- ----------------------------
DROP TABLE IF EXISTS `system_department`;
CREATE TABLE `system_department` (
  `ID` varchar(255) NOT NULL,
  `DEPARTNAME` text,
  `CHARGER` varchar(50) default NULL,
  `TEL` varchar(20) default NULL,
  `ADDRESS` text,
  `FATHER` varchar(20) default NULL,
  `REMARK` text,
  `STATE` varchar(2) default NULL,
  `CREATEMAN` varchar(20) default NULL,
  `CREATEDATE` datetime default NULL,
  `TYPE` varchar(2) default NULL,
  `ORDERBY` varchar(10) default NULL,
  `ORDERNO` varchar(10) default NULL,
  `ISREPAIR` varchar(2) default NULL,
  `LEVEL` varchar(2) default NULL,
  `LEVELNO` varchar(2) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_department
-- ----------------------------
INSERT INTO `system_department` VALUES ('0', 'Root', 'Root', 'Root', 'Root', '-1', 'Root', '1', '', '0001-01-01 00:00:00', '1', '1', null, null, null, null);
INSERT INTO `system_department` VALUES ('20111102172259103612', '泉邮市', '泉邮市', '泉邮市', null, '3', '泉邮市', '1', '', '2011-11-02 17:22:59', '1', '1', null, null, null, null);
INSERT INTO `system_department` VALUES ('20111109114147851858', '中文', '123', '13599204724', '123', '20111102172259103612', '123', '1', '1', '2011-11-09 11:41:47', '1', '1', null, null, null, null);
INSERT INTO `system_department` VALUES ('20111214090646744150', 'test', 'test', '13512345678', 'xxx', '20111109114147851858', 'test', '1', '1', '2011-12-14 09:06:46', '1', '1', null, null, null, null);
INSERT INTO `system_department` VALUES ('20111224234947704371', '3', '3', null, '3', '20111102172259103612', '3', '1', '1', '2011-12-24 23:49:47', '1', '1', null, null, null, null);
INSERT INTO `system_department` VALUES ('20111224235001198249', '4', '3', null, '3', '20111102172259103612', '3', '1', '1', '2011-12-24 23:50:01', '1', '1', null, null, null, null);
INSERT INTO `system_department` VALUES ('20111224235002151053', '4', '3', null, '3', '20111102172259103612', '3', '1', '1', '2011-12-24 23:50:02', '1', '1', null, null, null, null);
INSERT INTO `system_department` VALUES ('20120117112128683221', 'x', null, null, null, '20111214090646744150', null, '1', '1', '2012-01-17 11:21:28', '5', null, null, '0', '6', null);
INSERT INTO `system_department` VALUES ('20120117112136497943', 'y', null, null, null, '20111214090646744150', null, '1', '1', '2012-01-17 11:21:36', '5', null, null, '0', '6', null);
INSERT INTO `system_department` VALUES ('3', '泉3', '泉邮', '13512345678', '泉邮', '0', '泉邮', '1', '1', '0001-01-01 00:00:00', '5', '1', null, '1', '2', '2');

-- ----------------------------
-- Table structure for `system_log`
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log` (
  `ID` varchar(255) NOT NULL,
  `OPERCODE` varchar(100) default NULL,
  `CONTROLLERSCODE` varchar(100) default NULL,
  `USERID` varchar(20) default NULL,
  `OPERDATE` datetime default NULL,
  `OPERRESULT` text,
  `OPERIP` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_log
-- ----------------------------
INSERT INTO `system_log` VALUES ('20120214091202546188', 'add', '/archives/company', '20120116170911734125', '2012-02-14 09:12:02', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120215230734793307', 'add', '/resources/dedicateline', '20120116170911734125', '2012-02-15 23:07:34', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120215230743214787', 'add', '/resources/dedicateline', '20120116170911734125', '2012-02-15 23:07:43', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120215230751937168', 'add', '/resources/dedicateline', '20120116170911734125', '2012-02-15 23:07:51', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120215230758261039', 'add', '/resources/equipment', '20120116170911734125', '2012-02-15 23:07:58', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120216190935424489', 'add', '/system/user', '20120116170911734125', '2012-02-16 19:09:35', '', '::1');
INSERT INTO `system_log` VALUES ('20120216191001920411', 'add', '/system/user', '20120116170911734125', '2012-02-16 19:10:01', '', '::1');
INSERT INTO `system_log` VALUES ('20120326111429985724', 'modifyrole', '/system/user', '1', '2012-03-26 11:14:29', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120326112200476356', 'modifyrole', '/system/user', '1', '2012-03-26 11:22:00', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120326112215154479', 'modifyrole', '/system/user', '1', '2012-03-26 11:22:15', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120326112232833795', 'modifyrole', '/system/user', '1', '2012-03-26 11:22:32', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120326112236300076', 'modify', '/system/user', '1', '2012-03-26 11:22:36', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120326112316584960', 'modifyrole', '/system/user', '20120116170911734125', '2012-03-26 11:23:16', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120326112329714863', 'modifyrole', '/system/user', '20120116170911734125', '2012-03-26 11:23:29', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120326112335597162', 'modifyrole', '/system/user', '20120116170911734125', '2012-03-26 11:23:35', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120326112425378593', 'modifyrole', '/system/user', '20120116170911734125', '2012-03-26 11:24:25', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120326112510710120', 'modifyrole', '/system/user', '20120116170911734125', '2012-03-26 11:25:10', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120326112531636977', 'modifyrole', '/system/user', '20120116170911734125', '2012-03-26 11:25:31', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120326112558635873', 'modifyrole', '/system/user', '20120116170911734125', '2012-03-26 11:25:58', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120326112620803613', 'modifyrole', '/system/user', '20120116170911734125', '2012-03-26 11:26:20', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120326112645264409', 'modifyrole', '/system/user', '20120116170911734125', '2012-03-26 11:26:45', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120326112707291728', 'modifyrole', '/system/user', '20120116170911734125', '2012-03-26 11:27:07', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120326112755423668', 'modifyrole', '/system/user', '20120116170911734125', '2012-03-26 11:27:55', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120326163506441377', 'modifyrole', '/system/user', '1', '2012-03-26 16:35:06', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120326163514114138', 'modifyrole', '/system/user', '1', '2012-03-26 16:35:14', '', '127.0.0.1');
INSERT INTO `system_log` VALUES ('20120326164942623055', 'add', '/system/user', '1', '2012-03-26 16:49:42', '', '127.0.0.1');

-- ----------------------------
-- Table structure for `system_menu`
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu` (
  `ID` varchar(255) NOT NULL,
  `NAME` text,
  `PIC` text,
  `URL` text,
  `ORDERNO` varchar(10) default NULL,
  `FATHER` varchar(20) default NULL,
  `ISMENU` char(1) default NULL,
  `PERMISSIONSFLAG` varchar(100) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES ('0                   ', 'Root', ' ', null, '1         ', '-1                  ', '1', null);
INSERT INTO `system_menu` VALUES ('2                   ', '菜单管理', ' ', '/system/menu', '3         ', '4                   ', '1', null);
INSERT INTO `system_menu` VALUES ('20110921164907407684', '系统管理', null, null, '9000      ', '0                   ', '1', null);
INSERT INTO `system_menu` VALUES ('20110922104955321561', '城市管理', null, '/archives/city', null, '20111214234039504531', '1', null);
INSERT INTO `system_menu` VALUES ('20110922112506800586', '区县管理', null, '/archives/district', null, '20111214234039504531', '1', null);
INSERT INTO `system_menu` VALUES ('20110922112552598470', '公司管理', null, '/archives/company', null, '20111214234039504531', '1', null);
INSERT INTO `system_menu` VALUES ('20110922112615115710', '网格管理', null, '/archives/grid', null, '20111214234039504531', '1', null);
INSERT INTO `system_menu` VALUES ('20110927000804747221', '客户规模等级', null, '/archives/cliescalegrade/', '1         ', '20111214233809343419', '1', null);
INSERT INTO `system_menu` VALUES ('20110927000846892808', '客户服务星级', null, '/archives/clieservstarleve/', '1         ', '20111214233809343419', '1', null);
INSERT INTO `system_menu` VALUES ('20110927000932655757', '业务保障等级', null, '/archives/bizassuranleve/', '1         ', '20111214233809343419', '1', null);
INSERT INTO `system_menu` VALUES ('20110927000955563428', '业务类型', null, '/archives/biztype', '1         ', '20111214233809343419', '1', null);
INSERT INTO `system_menu` VALUES ('20110927001031122263', '组网方式管理', null, '/archives/networkingmode/', '1         ', '20111214233809343419', '1', null);
INSERT INTO `system_menu` VALUES ('20110927001106938444', '厂家信息管理', null, '/archives/factory', '1         ', '20111214233809343419', '1', null);
INSERT INTO `system_menu` VALUES ('20110927001137227864', '设备类型管理', null, '/archives/equiptype/', '1         ', '20111214233809343419', '1', null);
INSERT INTO `system_menu` VALUES ('20110927001203627360', '设备型号管理', null, '/archives/equipmodel/', '1         ', '20111214233809343419', '1', null);
INSERT INTO `system_menu` VALUES ('20110928163254514988', '专线资源管理', null, null, '0000      ', '0                   ', '1', null);
INSERT INTO `system_menu` VALUES ('20110928163358644582', '互联网专线信息', null, '/resources/dedicateline/internet', '0200      ', '20110928163254514988', '1', null);
INSERT INTO `system_menu` VALUES ('20110928163733140331', '集团客户信息管理', '1', '/resources/groupclie', '0         ', '20111214233809343419', '1', null);
INSERT INTO `system_menu` VALUES ('20110929162456755201', 'IMS专线信息', null, '/resources/dedicateline/ims', '0300      ', '20110928163254514988', '1', null);
INSERT INTO `system_menu` VALUES ('20110929162524168952', 'VOIP专线信息', null, '/resources/dedicateline/voip', '0400      ', '20110928163254514988', '1', null);
INSERT INTO `system_menu` VALUES ('20110929162542124476', '广域网专线信息', null, '/resources/dedicateline/wide', '0500      ', '20110928163254514988', '1', null);
INSERT INTO `system_menu` VALUES ('20111013153403420603', '专线设备管理', null, '/resources/equipment', '0600      ', '20110928163254514988', '1', null);
INSERT INTO `system_menu` VALUES ('20111018004002680562', '自助终端管理', null, null, '1000      ', '0                   ', '1', null);
INSERT INTO `system_menu` VALUES ('20111018004151308039', '自助终端设备管理', null, '/resources/selfhelpequip', '1100      ', '20111018004002680562', '1', null);
INSERT INTO `system_menu` VALUES ('20111018004212940642', '自助终端故障管理', null, '/resources/equipfault', '1200      ', '20111018004002680562', '1', null);
INSERT INTO `system_menu` VALUES ('20111018092804732516', '故障类型管理', null, '/archives/breakdowntype', null, '20111214233003124873', '1', null);
INSERT INTO `system_menu` VALUES ('20111018092838424932', '部件管理', null, '/archives/component', null, '20111214233003124873', '1', null);
INSERT INTO `system_menu` VALUES ('20111021164707626123', '巡检管理', null, null, '4000      ', '0                   ', '1', null);
INSERT INTO `system_menu` VALUES ('20111024111742315107', '巡检时间', null, '/cop/cyctime', '1         ', '20111021164707626123', '1', null);
INSERT INTO `system_menu` VALUES ('20111024150903442685', '巡检周期', null, '/cop/cyc', null, '20111021164707626123', '1', null);
INSERT INTO `system_menu` VALUES ('20111025150004376940', '巡检登记', null, '/cop/enrol', null, '20111021164707626123', '1', null);
INSERT INTO `system_menu` VALUES ('20111027163123898561', '人员档案管理', null, '/resources/personnel', '2100      ', '20120106111409482121', '1', null);
INSERT INTO `system_menu` VALUES ('20111027163145260735', '车辆档案管理', null, '/resources/vehicle', '2200      ', '20120106111409482121', '1', null);
INSERT INTO `system_menu` VALUES ('20111027163214705719', '仪器仪表档案管理', null, '/resources/apparatus', '2300      ', '20120106111409482121', '1', null);
INSERT INTO `system_menu` VALUES ('20111027163335384549', '仓库管理', null, null, '3000      ', '0                   ', '1', null);
INSERT INTO `system_menu` VALUES ('20111027163445763148', '盘点上传管理', null, '/warehouse/inventoryfile', null, '20111027163335384549', '1', null);
INSERT INTO `system_menu` VALUES ('20111101155107838655', '用户管理', ' ', '/system/user', '2         ', '4                   ', '1', null);
INSERT INTO `system_menu` VALUES ('20111101155132986948', '机构管理', ' ', '/system/department/index', '1         ', '4                   ', '1', '/system/department/index');
INSERT INTO `system_menu` VALUES ('20111103205603908531', '网点类型', null, '/archives/outletstype', null, '20111214233003124873', '1', null);
INSERT INTO `system_menu` VALUES ('20111103205631688189', '人员岗位', null, '/archives/duty', null, '20111214232494301212', '1', null);
INSERT INTO `system_menu` VALUES ('20111107105640419249', '仪器仪表状态', null, '/archives/meterstate/', null, '20111214232494301212', '1', null);
INSERT INTO `system_menu` VALUES ('20111107105725116891', '维护专业', null, '/archives/maintainspecialty/', null, '20111214232494301212', '1', null);
INSERT INTO `system_menu` VALUES ('20111107105752140112', '用途', null, '/archives/use/', null, '20111214232494301212', '1', null);
INSERT INTO `system_menu` VALUES ('20111107105837302009', '信令方式', null, '/archives/signalmodel/', '8100      ', '20111214233809343419', '1', null);
INSERT INTO `system_menu` VALUES ('20111107105853103346', '驻点', null, '/archives/stagnation/', null, '20111214234039504531', '1', null);
INSERT INTO `system_menu` VALUES ('20111107105923460923', '车辆性质', null, '/archives/vehiclenature/', null, '20111214232494301212', '1', null);
INSERT INTO `system_menu` VALUES ('20111107110006458174', '认证资历类型', null, '/archives/qualificationtype/', null, '20111214232494301212', '1', null);
INSERT INTO `system_menu` VALUES ('20111107152909123102', '性质', null, '/archives/nature', null, '20111214232494301212', '1', null);
INSERT INTO `system_menu` VALUES ('20111108212605526921', '文凭管理', null, '/archives/diploma/', null, '20111214232494301212', '1', null);
INSERT INTO `system_menu` VALUES ('20111108212625885390', '上岗证管理', null, '/archives/certificate/', null, '20111214232494301212', '1', null);
INSERT INTO `system_menu` VALUES ('20111108212650600550', '项目属性管理', null, '/archives/itemproperty/', null, '20111214232494301212', '1', null);
INSERT INTO `system_menu` VALUES ('20111109165930699219', '督办工单', null, null, '7000      ', '0                   ', '1', null);
INSERT INTO `system_menu` VALUES ('20111109170057788785', '派遣工单', null, '/supervision/send', '1         ', '20111109165930699219', '1', null);
INSERT INTO `system_menu` VALUES ('20111109170113637220', '我的工单', null, '/supervision/orders', '2         ', '20111109165930699219', '1', null);
INSERT INTO `system_menu` VALUES ('20111110110034831695', '170专线资源管理', null, '/resources/onesevenzero/', '0100      ', '20110928163254514988', '1', null);
INSERT INTO `system_menu` VALUES ('20111114115209794573', '我的考试', null, null, '5000      ', '0                   ', '1', null);
INSERT INTO `system_menu` VALUES ('20111114115417506723', '成绩管理', null, '/exam/achievement', '1         ', '20111114115209794573', '1', null);
INSERT INTO `system_menu` VALUES ('20111114170630512362', '在线考试', null, null, '6000      ', '0                   ', '1', null);
INSERT INTO `system_menu` VALUES ('20111114171053725848', '题库管理', null, '/exam/subject', '1         ', '20111114170630512362', '1', null);
INSERT INTO `system_menu` VALUES ('20111114171432702794', '试题类型', null, '/exam/subjecttype', '1         ', '20111114170630512362', '1', null);
INSERT INTO `system_menu` VALUES ('20111114171530654584', '试题等级', null, '/exam/scalegrade', '1         ', '20111114170630512362', '1', null);
INSERT INTO `system_menu` VALUES ('20111114171603910703', '科目类型', null, '/exam/course', '1         ', '20111114170630512362', '1', null);
INSERT INTO `system_menu` VALUES ('20111114220008361509', '试卷类型', null, '/exam/testtype', '1         ', '20111114170630512362', '1', null);
INSERT INTO `system_menu` VALUES ('20111114232216216362', '试卷管理', null, '/exam/test', '1         ', '20111114170630512362', '1', null);
INSERT INTO `system_menu` VALUES ('20111115094631522483', '在线考试', null, '/exam/testset/selectexam', '6100      ', '20111114115209794573', '1', null);
INSERT INTO `system_menu` VALUES ('20111115094646198234', '模拟考试', null, '/exam/testmock/selectexam', '3         ', '20111114115209794573', '1', null);
INSERT INTO `system_menu` VALUES ('20111115145355243388', '在线练习', null, '/exam/testexercise/selectexam', '4         ', '20111114115209794573', '1', null);
INSERT INTO `system_menu` VALUES ('20111116100623622305', '随机出卷', null, '/exam/test/editrandom/0', '2         ', '20111114232216216362', '0', null);
INSERT INTO `system_menu` VALUES ('20111117152829657826', '手动出卷', null, '/exam/test/edittest/0', '1         ', '20111114232216216362', '0', null);
INSERT INTO `system_menu` VALUES ('20111117170217256523', '单位管理', null, '/archives/unit/', null, '20111214234595450541', '1', null);
INSERT INTO `system_menu` VALUES ('20111117170239807186', '出入库方式管理', null, '/archives/ioclass/', null, '20111214234595450541', '1', null);
INSERT INTO `system_menu` VALUES ('20111118154206950796', '仓库信息管理', null, '/warehouse/repertoty', '3100      ', '20111027163335384549', '1', null);
INSERT INTO `system_menu` VALUES ('20111118154251121452', '库存管理', null, null, '3200      ', '20111027163335384549', '1', null);
INSERT INTO `system_menu` VALUES ('20111118154328263431', '库存信息管理', null, '/warehouse/stock', '3210      ', '20111118154251121452', '1', null);
INSERT INTO `system_menu` VALUES ('20111118154434457901', '入库', null, '/warehouse/iolist/ilist', '3210      ', '20111118154251121452', '1', null);
INSERT INTO `system_menu` VALUES ('20111118154449465484', '出库', null, '/warehouse/iolist/olist', '3220      ', '20111118154251121452', '1', null);
INSERT INTO `system_menu` VALUES ('20111123113102500342', '审核工单', null, '/supervision/audit', '4         ', '20111109165930699219', '1', null);
INSERT INTO `system_menu` VALUES ('20111128092049857746', '人员职位管理', null, '/archives/posts/', null, '20111214232494301212', '1', null);
INSERT INTO `system_menu` VALUES ('20111128092111574215', '自助厂家管理', null, '/archives/selfhelpfactory/', null, '20111214233003124873', '1', null);
INSERT INTO `system_menu` VALUES ('20111128092131339880', '自助设备类型管理', null, '/archives/selfhelpequiptype/', null, '20111214233003124873', '1', null);
INSERT INTO `system_menu` VALUES ('20111128092146962728', '自助设备型号管理', null, '/archives/selfhelpequipmodel/', null, '20111214233003124873', '1', null);
INSERT INTO `system_menu` VALUES ('20111130092215579284', '网点名称', null, '/archives/networkname/', null, '20111214233003124873', '1', null);
INSERT INTO `system_menu` VALUES ('20111214230080932131', '小区类型管理', null, '/archives/communitytype', null, '20111214241441984311', '1', null);
INSERT INTO `system_menu` VALUES ('20111214231104221349', '耗材类型管理', null, '/archives/suppliestype', null, '20111214241441984311', '1', null);
INSERT INTO `system_menu` VALUES ('20111214231209872123', '备件用途管理', null, '/archives/stockequipuse', null, '20111214234595450541', '1', null);
INSERT INTO `system_menu` VALUES ('20111214231434123981', '营业部管理', null, '/archives/saledepartment', null, '20111214241441984311', '1', null);
INSERT INTO `system_menu` VALUES ('20111214231441853134', '接入方式管理', null, '/archives/accessway', null, '20111214241441984311', '1', null);
INSERT INTO `system_menu` VALUES ('20111214231998434012', '建设方式管理', null, '/archives/buildway', null, '20111214241441984311', '1', null);
INSERT INTO `system_menu` VALUES ('20111214232198412391', '装维类型管理', null, '/archives/maintaintype', null, '20111214241441984311', '1', null);
INSERT INTO `system_menu` VALUES ('20111214232494301212', '人员车辆仪表基础信息', null, null, '9300      ', '20110921164907407684', '1', null);
INSERT INTO `system_menu` VALUES ('20111214233003124873', '自助终端基础信息', null, null, '9400      ', '20110921164907407684', '1', null);
INSERT INTO `system_menu` VALUES ('20111214233809343419', '专线基础信息', null, null, '9200      ', '20110921164907407684', '1', null);
INSERT INTO `system_menu` VALUES ('20111214234039504531', '公共基础信息', null, null, '9100      ', '20110921164907407684', '1', null);
INSERT INTO `system_menu` VALUES ('20111214234595450541', '仓库基础信息', null, null, '9500      ', '20110921164907407684', '1', null);
INSERT INTO `system_menu` VALUES ('20111214241441984311', '耗材基础信息', null, null, '9600      ', '20110921164907407684', '1', null);
INSERT INTO `system_menu` VALUES ('20111215094138835073', '已组织考试', null, '/exam/testset/', '9         ', '20111114170630512362', '1', null);
INSERT INTO `system_menu` VALUES ('20111215094233704567', '已组织模拟考', null, '/exam/testmock/', '10        ', '20111114170630512362', '1', null);
INSERT INTO `system_menu` VALUES ('20111215094305322787', '已组织练习', null, '/exam/testexercise/', '11        ', '20111114170630512362', '1', null);
INSERT INTO `system_menu` VALUES ('20120106111409482121', '人员车辆管理', null, null, '2000      ', '0                   ', '1', null);
INSERT INTO `system_menu` VALUES ('20120213232400954244', '日志管理', null, '/system/log', '6', '4', '1', null);
INSERT INTO `system_menu` VALUES ('4                   ', '权限管理', ' ', null, '9999      ', '20110921164907407684', '1', null);
INSERT INTO `system_menu` VALUES ('5                   ', '角色管理', ' ', '/system/role/index', '5         ', '4                   ', '1', null);
INSERT INTO `system_menu` VALUES ('6                   ', '菜单字段', ' ', '/system/menufield/index', '4         ', '4                   ', '1', null);
INSERT INTO `system_menu` VALUES ('b0', '查询', '/Lib/ligerUI/skins/icons/add.gif', 'search', '256', '5', '2', null);
INSERT INTO `system_menu` VALUES ('b1                  ', '增加', ' /Lib/ligerUI/skins/icons/add.gif', 'add', '2         ', '5                   ', '2', null);
INSERT INTO `system_menu` VALUES ('b2                  ', '删除', '/Lib/ligerUI/skins/icons/delete.gif', 'delete', '4         ', '5                   ', '2', null);
INSERT INTO `system_menu` VALUES ('b3                  ', '修改', ' /Lib/ligerUI/skins/icons/modify.gif', 'modify', '8         ', '5                   ', '2', null);
INSERT INTO `system_menu` VALUES ('b4                  ', '启用', '/Lib/ligerUI/skins/icons/delete.gif', 'usestart', '16        ', '5                   ', '2', null);
INSERT INTO `system_menu` VALUES ('b5                  ', '停用', '/Lib/ligerUI/skins/icons/delete.gif', 'usestop', '32        ', '5                   ', '2', null);
INSERT INTO `system_menu` VALUES ('b6                  ', '导入', '/Lib/ligerUI/skins/icons/delete.gif', 'loadfile', '64        ', '5                   ', '2', null);
INSERT INTO `system_menu` VALUES ('b7                  ', '退网', '/Lib/ligerUI/skins/icons/delete.gif', 'quit', '128       ', '5                   ', '2', null);

-- ----------------------------
-- Table structure for `system_menufield`
-- ----------------------------
DROP TABLE IF EXISTS `system_menufield`;
CREATE TABLE `system_menufield` (
  `ID` varchar(255) NOT NULL,
  `MENUID` varchar(20) default NULL,
  `TABLECODE` varchar(50) default NULL,
  `TABLENAME` varchar(100) default NULL,
  `REMARK` varchar(100) default NULL,
  `FIELDCODE` varchar(50) default NULL,
  `FIELDNAME` varchar(100) default NULL,
  `PERMISSIONSFLAG` varchar(100) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_menufield
-- ----------------------------
INSERT INTO `system_menufield` VALUES ('20111114193134686610', '20111101155132986948', 'SystemDepartment', '部门表', null, 'Departname', '部门', 'QzgfFrame.System.DepartmentManger.Domain.DepartmentFacadeImpl');

-- ----------------------------
-- Table structure for `system_role`
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `ID` varchar(255) NOT NULL,
  `ROLENAME` text,
  `REMARK` text,
  `CREATETIME` datetime default NULL,
  `STATE` varchar(2) default NULL,
  `CREATEMAN` varchar(20) default NULL,
  `CREATEDATE` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES ('1', '系统管理员', '系统管理员', '2011-10-19 00:00:00', '1', null, null);
INSERT INTO `system_role` VALUES ('20111019163652913105', '普通用户', '普通用', '0001-01-01 00:00:00', '1', null, null);
INSERT INTO `system_role` VALUES ('20111019164108689747', '测试用户', '测试用户', '2011-10-19 16:41:09', '1', null, null);
INSERT INTO `system_role` VALUES ('20111022131645791446', 'xxx', 'b', '2011-10-22 13:16:45', '1', '1', null);
INSERT INTO `system_role` VALUES ('20111109150256597823', '1', null, null, '1', '1', '2011-11-09 15:02:56');
INSERT INTO `system_role` VALUES ('20111109150413290573', '2', 'tt', null, '1', '1', '2011-11-09 15:04:13');
INSERT INTO `system_role` VALUES ('20111109150418712140', '3', 'tt', null, '1', '1', '2011-11-09 15:04:18');
INSERT INTO `system_role` VALUES ('20111206154311615746', 'x', 'x', null, '2', '1', '2011-12-06 15:43:11');

-- ----------------------------
-- Table structure for `system_rolefiledpower`
-- ----------------------------
DROP TABLE IF EXISTS `system_rolefiledpower`;
CREATE TABLE `system_rolefiledpower` (
  `ID` varchar(255) NOT NULL,
  `ROLEID` varchar(20) default NULL,
  `POWERVAL` int(11) default NULL,
  `FIELDID` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_rolefiledpower
-- ----------------------------
INSERT INTO `system_rolefiledpower` VALUES ('20111207190028325113', '20111019163652913105', '5', '20111109150907597824');
INSERT INTO `system_rolefiledpower` VALUES ('20111207190028508816', '20111019163652913105', '2', '20111029225822921133');
INSERT INTO `system_rolefiledpower` VALUES ('20111207190028997225', '20111019163652913105', '7', '20111114193134686610');
INSERT INTO `system_rolefiledpower` VALUES ('20120214091051546187', '1', '7', '20111114193134686610');

-- ----------------------------
-- Table structure for `system_rolemenu`
-- ----------------------------
DROP TABLE IF EXISTS `system_rolemenu`;
CREATE TABLE `system_rolemenu` (
  `ID` varchar(255) NOT NULL,
  `ROLEID` varchar(20) default NULL,
  `MENUID` varchar(20) default NULL,
  `OPTVAL` int(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_rolemenu
-- ----------------------------
INSERT INTO `system_rolemenu` VALUES ('20111207190011323114', '20111019164108689747', '5', '63');
INSERT INTO `system_rolemenu` VALUES ('20111207190011447698', '20111019164108689747', '2', '63');
INSERT INTO `system_rolemenu` VALUES ('20111207190011807998', '20111019164108689747', '4', '1');
INSERT INTO `system_rolemenu` VALUES ('20111207190011839660', '20111019164108689747', '6', '63');
INSERT INTO `system_rolemenu` VALUES ('20111207190011970951', '20111019164108689747', '20111101155107838655', '3');
INSERT INTO `system_rolemenu` VALUES ('20111207190027667904', '20111019163652913105', '4', '1');
INSERT INTO `system_rolemenu` VALUES ('20120214091051106107', '1', '20120213232400954244', '259');
INSERT INTO `system_rolemenu` VALUES ('20120214091051115626', '1', '20111214230080932131', '259');
INSERT INTO `system_rolemenu` VALUES ('20120214091051159560', '1', '20111214232494301212', '1');
INSERT INTO `system_rolemenu` VALUES ('20120214091051160091', '1', '20111214233809343419', '1');
INSERT INTO `system_rolemenu` VALUES ('20120214091051172263', '1', '20111214234039504531', '1');
INSERT INTO `system_rolemenu` VALUES ('20120214091051174685', '1', '20111107110006458174', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051185746', '1', '20110927000846892808', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051188459', '1', '20110927001031122263', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051199590', '1', '20111018004212940642', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051230171', '1', '20110922112552598470', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051250701', '1', '20111027163123898561', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051252143', '1', '20111107152909123102', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051274005', '1', '20111214231104221349', '259');
INSERT INTO `system_rolemenu` VALUES ('20120214091051294075', '1', '20111128092049857746', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051294675', '1', '20111103205603908531', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051295516', '1', '20111214233003124873', '1');
INSERT INTO `system_rolemenu` VALUES ('20120214091051331372', '1', '20111109170113637220', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051334385', '1', '20111130092215579284', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051351252', '1', '20111114171530654584', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051359740', '1', '20111114171432702794', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051365646', '1', '20111109165930699219', '1');
INSERT INTO `system_rolemenu` VALUES ('20120214091051367698', '1', '20111215094138835073', '259');
INSERT INTO `system_rolemenu` VALUES ('20120214091051372423', '1', '20111107105752140112', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051377448', '1', '20111027163445763148', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051400911', '1', '20111215094305322787', '259');
INSERT INTO `system_rolemenu` VALUES ('20120214091051419260', '1', '20110929162524168952', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051424595', '1', '20111118154449465484', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051425696', '1', '20111114115417506723', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051425946', '1', '20111027163145260735', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051427258', '1', '20111118154434457901', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051441182', '1', '20111114115209794573', '1');
INSERT INTO `system_rolemenu` VALUES ('20120214091051456567', '1', '20111128092146962728', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051457738', '1', '20111024150903442685', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051463214', '1', '20111018092838424932', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051465596', '1', '20120106111409482121', '1');
INSERT INTO `system_rolemenu` VALUES ('20120214091051468709', '1', '20110929162456755201', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051479630', '1', '20110922104955321561', '259');
INSERT INTO `system_rolemenu` VALUES ('20120214091051479800', '1', '20111114232216216362', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051502893', '1', '20110927000932655757', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051504415', '1', '20111018004151308039', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051514855', '1', '20111107105837302009', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051515376', '1', '20110928163733140331', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051526057', '1', '20111027163335384549', '1');
INSERT INTO `system_rolemenu` VALUES ('20120214091051533804', '1', '20110922112506800586', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051535936', '1', '20111109170057788785', '383');
INSERT INTO `system_rolemenu` VALUES ('20120214091051538199', '1', '20111114171603910703', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051539660', '1', '6                   ', '259');
INSERT INTO `system_rolemenu` VALUES ('20120214091051540751', '1', '20111214232198412391', '259');
INSERT INTO `system_rolemenu` VALUES ('20120214091051546187', '1', '20111110110034831695', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051559700', '1', '5                   ', '259');
INSERT INTO `system_rolemenu` VALUES ('20120214091051567818', '1', '20111214231441853134', '259');
INSERT INTO `system_rolemenu` VALUES ('20120214091051578279', '1', '20111118154206950796', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051578449', '1', '20111215094233704567', '259');
INSERT INTO `system_rolemenu` VALUES ('20120214091051588679', '1', '20110921164907407684', '1');
INSERT INTO `system_rolemenu` VALUES ('20120214091051594995', '1', '20111118154328263431', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051614935', '1', '20110927000804747221', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051630561', '1', '20111114171053725848', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051630611', '1', '20111018092804732516', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051632493', '1', '20110927001137227864', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051643544', '1', '20111128092131339880', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051649950', '1', '20110928163254514988', '1');
INSERT INTO `system_rolemenu` VALUES ('20120214091051651052', '1', '20110927001203627360', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051653684', '1', '20111115145355243388', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051660471', '1', '20111214231209872123', '259');
INSERT INTO `system_rolemenu` VALUES ('20120214091051682743', '1', '20111108212605526921', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051690331', '1', '20111027163214705719', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051693214', '1', '20111128092111574215', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051694385', '1', '20111214231998434012', '259');
INSERT INTO `system_rolemenu` VALUES ('20120214091051732733', '1', '20111115094631522483', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051733864', '1', '2                   ', '259');
INSERT INTO `system_rolemenu` VALUES ('20120214091051749850', '1', '20111101155132986948', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051758409', '1', '20111107105725116891', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051758809', '1', '20111115094646198234', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051764505', '1', '20111123113102500342', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051768199', '1', '20111021164707626123', '1');
INSERT INTO `system_rolemenu` VALUES ('20120214091051771662', '1', '20111107105640419249', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051774425', '1', '20111107105853103346', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051782833', '1', '20111114220008361509', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051793964', '1', '20111114170630512362', '1');
INSERT INTO `system_rolemenu` VALUES ('20120214091051818909', '1', '20111013153403420603', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051836197', '1', '20111024111742315107', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051838459', '1', '20111117170217256523', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051840551', '1', '20110929162542124476', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051848249', '1', '20110928163358644582', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051848359', '1', '20111214231434123981', '259');
INSERT INTO `system_rolemenu` VALUES ('20120214091051852823', '1', '20111101155107838655', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051879250', '1', '20111117170239807186', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051890941', '1', '20111107105923460923', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051897638', '1', '20111108212625885390', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051899150', '1', '20111118154251121452', '1');
INSERT INTO `system_rolemenu` VALUES ('20120214091051902983', '1', '20111018004002680562', '1');
INSERT INTO `system_rolemenu` VALUES ('20120214091051909190', '1', '20111025150004376940', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051920331', '1', '20110922112615115710', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051925796', '1', '20111214234595450541', '1');
INSERT INTO `system_rolemenu` VALUES ('20120214091051939040', '1', '20110927001106938444', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051945936', '1', '20111214241441984311', '1');
INSERT INTO `system_rolemenu` VALUES ('20120214091051970361', '1', '20110927000955563428', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051993204', '1', '4                   ', '1');
INSERT INTO `system_rolemenu` VALUES ('20120214091051996257', '1', '20111103205631688189', '275');
INSERT INTO `system_rolemenu` VALUES ('20120214091051999810', '1', '20111108212650600550', '275');

-- ----------------------------
-- Table structure for `system_user`
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `ID` varchar(255) NOT NULL,
  `USERNAME` varchar(100) default NULL,
  `PASSWORD` varchar(100) default NULL,
  `DEPARTMENTID` varchar(20) default NULL,
  `CREATEDATE` datetime default NULL,
  `TEL` varchar(50) default NULL,
  `EMAIL` varchar(100) default NULL,
  `REMARK` varchar(200) default NULL,
  `CREATEMAN` varchar(20) default NULL,
  `STATE` varchar(2) default NULL,
  `NICKNAME` varchar(100) default NULL,
  `AREAID` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('1', 'admin', '21232F297A57A5A743894AE4A801FC3', '20111102172259103612', '0001-01-01 00:00:00', '', 'admin@139.com', 'admin', '', '1', null, null);
INSERT INTO `system_user` VALUES ('20111104104056266852', 'test', '98F6BCD4621D373CADE4E832627B4F6', '20111102152949752041', '0001-01-01 00:00:00', '', null, 'saf', '', '0', null, null);
INSERT INTO `system_user` VALUES ('20111104111936577173', null, 'D41D8CD98F0B24E980998ECF8427E', null, '2011-11-04 11:19:36', '', null, null, '1', '0', null, null);
INSERT INTO `system_user` VALUES ('20111104111953808331', '3', 'ECCBC87E4B5CE2FE28308FD9F2A7BAF3', '20111109114147851858', '0001-01-01 00:00:00', null, null, null, '1', '0', null, null);
INSERT INTO `system_user` VALUES ('20111114094244532846', '1', '1', '20111109114147851858', '0001-01-01 00:00:00', '', null, null, '', '0', null, null);
INSERT INTO `system_user` VALUES ('20111114094258737715', '2', 'C81E728D9D4C2F636F67F89CC14862C', '20111109114147851858', '2011-11-14 09:42:58', null, null, null, '1', '0', null, null);
INSERT INTO `system_user` VALUES ('20111114115233856879', '', 'A87FF679A2F3E71D9181A67B7542122C', '', '2011-11-14 11:52:33', '', '', '', '1', '0', null, null);
INSERT INTO `system_user` VALUES ('20111206100437406173', '4', 'A87FF679A2F3E71D9181A67B7542122C', '20111109114147851858', '2011-12-06 10:04:37', '4', '4', null, '1', '0', null, null);
INSERT INTO `system_user` VALUES ('20111206100923243376', '5', 'E4DA3B7FBBCE2345D7772B674A318D5', '20111109114147851858', '2011-12-06 10:09:23', '5', null, null, '1', '0', null, null);
INSERT INTO `system_user` VALUES ('20111206150946615741', '6', '167991C5A88FAF6FB5E687EB1B2DC', '20111109114147851858', '2011-12-06 15:09:46', '6', '6', null, '1', '0', null, null);
INSERT INTO `system_user` VALUES ('20111206151503167010', '6', '167991C5A88FAF6FB5E687EB1B2DC', '20111109114147851858', '2011-12-06 15:15:03', '7', '7', null, '1', '0', null, null);
INSERT INTO `system_user` VALUES ('20111214091606744150', 'g', 'B2F5FF47436671B6E533D8DC3614845D', '20111109114147851858', '2011-12-14 09:16:06', 'xx', 'g', 'g', '1', '0', null, null);
INSERT INTO `system_user` VALUES ('20111224235121704375', 'v', '9990775155C3518AD7917F778B24AA', '20111102172215906071', '2011-12-24 23:51:21', 'ttt', 'v', null, '1', '0', null, null);
INSERT INTO `system_user` VALUES ('20111224235131198249', 'x', '1AABAC6D68EEF6A7BAD3FDF50A05CC8', '20111102172215906071', '2011-12-24 23:51:31', 'dd', 'v', null, '1', '0', null, null);
INSERT INTO `system_user` VALUES ('20120104153155582697', '7', '8F14E45FCEEA167A5A36DEDD4BEA2543', '20111102172259103612', '2012-01-04 15:31:55', null, null, null, '1', '0', null, null);
INSERT INTO `system_user` VALUES ('20120104153159399258', '7', '8F14E45FCEEA167A5A36DEDD4BEA2543', '20111102172259103612', '2012-01-04 15:31:59', null, null, null, '1', '0', null, null);
INSERT INTO `system_user` VALUES ('20120104153203183116', '7', '8F14E45FCEEA167A5A36DEDD4BEA2543', '20111102172259103612', '2012-01-04 15:32:03', null, null, null, '1', '0', null, null);
INSERT INTO `system_user` VALUES ('20120104153207926163', '7', '8F14E45FCEEA167A5A36DEDD4BEA2543', '20111102172259103612', '2012-01-04 15:32:07', null, null, null, '1', '0', null, null);
INSERT INTO `system_user` VALUES ('20120104154821439040', '8', 'C9F0F895FB98AB9159F51FD0297E236D', '20111102172259103612', '2012-01-04 15:48:21', null, null, null, '1', '0', null, null);
INSERT INTO `system_user` VALUES ('20120104154825630835', '8', 'C9F0F895FB98AB9159F51FD0297E236D', '20111102172259103612', '2012-01-04 15:48:25', null, null, null, '1', '0', null, null);
INSERT INTO `system_user` VALUES ('20120104154917757764', '8', 'C9F0F895FB98AB9159F51FD0297E236D', '20111102172259103612', '2012-01-04 15:49:17', null, null, null, '1', '0', null, null);
INSERT INTO `system_user` VALUES ('20120104154937218235', '8', 'C9F0F895FB98AB9159F51FD0297E236D', '20111102172259103612', '2012-01-04 15:49:37', null, null, null, '1', '0', null, null);
INSERT INTO `system_user` VALUES ('20120104155142420502', '9', '45C48CCE2E2D7FBDEA1AFC51C7C6AD26', '20111102172259103612', '2012-01-04 15:51:42', null, null, null, '1', '0', null, null);
INSERT INTO `system_user` VALUES ('20120104155148372740', '9', '45C48CCE2E2D7FBDEA1AFC51C7C6AD26', '20111102172259103612', '2012-01-04 15:51:48', '11', null, null, '1', '0', '姓名', '20120113225923193026');
INSERT INTO `system_user` VALUES ('20120116170911734125', '1', 'C4CA4238A0B92382DCC509A6F75849B', '20111215000623166279', '2012-01-16 17:09:11', '1', '1', '1', '1', '1', '1', '20120116162348387355');
INSERT INTO `system_user` VALUES ('20120213110455549384', '2', 'C81E728D9D4C2F636F67F89CC14862C', '20110928114938767045', '2012-02-13 11:04:55', '2', '2', null, null, '1', '2', '20110928151357192049');
INSERT INTO `system_user` VALUES ('20120213201113873616', '3', 'ECCBC87E4B5CE2FE28308FD9F2A7BAF3', '20110928114938767045', '2012-02-13 20:11:13', '3', '3', '1', null, '1', '3', '20111208112131224905');

-- ----------------------------
-- Table structure for `system_userrole`
-- ----------------------------
DROP TABLE IF EXISTS `system_userrole`;
CREATE TABLE `system_userrole` (
  `ID` varchar(255) NOT NULL,
  `USERID` varchar(20) default NULL,
  `ROLEID` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_userrole
-- ----------------------------
INSERT INTO `system_userrole` VALUES ('20111104104221691262', '20111104104056266852', '20111022131645791446');
INSERT INTO `system_userrole` VALUES ('20111104111936577173', '20111104111936577173', '');
INSERT INTO `system_userrole` VALUES ('20111104153149616065', '1', '1');
INSERT INTO `system_userrole` VALUES ('20111114094346439015', '20111114094244532846', '20111019164108689747');
INSERT INTO `system_userrole` VALUES ('20111114115233279802', '20111114115233856879', '20111022131645791446');
INSERT INTO `system_userrole` VALUES ('20111205185954272816', '20111104111953808331', '20111019163652913105');
INSERT INTO `system_userrole` VALUES ('20111205185954600844', '20111104111953808331', '20111019164108689747');
INSERT INTO `system_userrole` VALUES ('20111206100923263636', '20111206100923243376', '20111109150256597823');
INSERT INTO `system_userrole` VALUES ('20111206100923971414', '20111206100923243376', '20111022131645791446');
INSERT INTO `system_userrole` VALUES ('20111206151142566328', '20111206150946615741', '20111019163652913105');
INSERT INTO `system_userrole` VALUES ('20111206151142671273', '20111206150946615741', '1');
INSERT INTO `system_userrole` VALUES ('20111206152507502539', '20111206151503167010', '20111019163652913105');
INSERT INTO `system_userrole` VALUES ('20111206152523224617', '20111206100437406173', '20111022131645791446');
INSERT INTO `system_userrole` VALUES ('20111206152523403196', '20111206100437406173', '20111019164108689747');
INSERT INTO `system_userrole` VALUES ('20111214091625400475', '20111214091606744150', '20111019164108689747');
INSERT INTO `system_userrole` VALUES ('20111214094316702018', '20111114094258737715', '1');
INSERT INTO `system_userrole` VALUES ('20111224235935151056', '20111224235131198249', '20111022131645791446');
INSERT INTO `system_userrole` VALUES ('20111225000055415280', '20111224235121704375', '20111022131645791446');
INSERT INTO `system_userrole` VALUES ('20120104153155582697', '20120104153155582697', '1');
INSERT INTO `system_userrole` VALUES ('20120104153159399258', '20120104153159399258', '1');
INSERT INTO `system_userrole` VALUES ('20120104153203183116', '20120104153203183116', '1');
INSERT INTO `system_userrole` VALUES ('20120104154249439048', '20120104153207926163', '1');
INSERT INTO `system_userrole` VALUES ('20120104154821630831', '20120104154821439040', '1');
INSERT INTO `system_userrole` VALUES ('20120104154825757762', '20120104154825630835', '1');
INSERT INTO `system_userrole` VALUES ('20120104154917218235', '20120104154917757764', '1');
INSERT INTO `system_userrole` VALUES ('20120104154937420507', '20120104154937218235', '1');
INSERT INTO `system_userrole` VALUES ('20120104155142372744', '20120104155142420502', '1');
INSERT INTO `system_userrole` VALUES ('20120116170800734124', '20120104155148372740', '1');
INSERT INTO `system_userrole` VALUES ('20120207231014769233', '20120116170911734125', '1');
INSERT INTO `system_userrole` VALUES ('20120207231014979043', '20120116170911734125', '20111019164108689747');
INSERT INTO `system_userrole` VALUES ('20120326112229300699', '20120213201113873616', '20111022131645791446');
INSERT INTO `system_userrole` VALUES ('20120326112326232038', '20120213110455549384', '20111019163652913105');
INSERT INTO `system_userrole` VALUES ('20120326112326645011', '20120213110455549384', '20111022131645791446');
INSERT INTO `system_userrole` VALUES ('20120326112326968084', '20120213110455549384', '1');

-- ----------------------------
-- Table structure for `tab_access_way`
-- ----------------------------
DROP TABLE IF EXISTS `tab_access_way`;
CREATE TABLE `tab_access_way` (
  `ID` varchar(20) NOT NULL,
  `AccessWayName` varchar(10) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_access_way
-- ----------------------------
INSERT INTO `tab_access_way` VALUES ('20111215000226678664', 'ADSL');
INSERT INTO `tab_access_way` VALUES ('20111215000234754608', 'LAN');
INSERT INTO `tab_access_way` VALUES ('20111215000245424859', 'FTTH');

-- ----------------------------
-- Table structure for `tab_apparatus`
-- ----------------------------
DROP TABLE IF EXISTS `tab_apparatus`;
CREATE TABLE `tab_apparatus` (
  `ID` varchar(255) NOT NULL,
  `TwoDimensionalCode` text,
  `SeqNo` text,
  `ManuFacturer` text,
  `MeterName` text,
  `MeterModel` text,
  `Remark` text,
  `StagnationId` text,
  `MaintainSpecialtyId` text,
  `UseId` text,
  `MeterStateId` text,
  `CompanyId` text,
  `CityId` text,
  `DistrictId` text,
  `GridId` text,
  `MeterType` varchar(80) default NULL,
  `CreateUserId` varchar(20) default NULL,
  `CreateDate` datetime default NULL,
  `DelFlag` smallint(6) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_apparatus
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_biz_assuran_leve`
-- ----------------------------
DROP TABLE IF EXISTS `tab_biz_assuran_leve`;
CREATE TABLE `tab_biz_assuran_leve` (
  `ID` varchar(20) NOT NULL,
  `AssuranLeveName` varchar(8) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_biz_assuran_leve
-- ----------------------------
INSERT INTO `tab_biz_assuran_leve` VALUES ('20110927004304492576', 'AAA');
INSERT INTO `tab_biz_assuran_leve` VALUES ('20110928120359314763', 'AA');
INSERT INTO `tab_biz_assuran_leve` VALUES ('20110928120401273374', 'A');
INSERT INTO `tab_biz_assuran_leve` VALUES ('20110928120404946280', '普通');
INSERT INTO `tab_biz_assuran_leve` VALUES ('20110928120430697997', '待定');

-- ----------------------------
-- Table structure for `tab_biz_type`
-- ----------------------------
DROP TABLE IF EXISTS `tab_biz_type`;
CREATE TABLE `tab_biz_type` (
  `ID` varchar(20) NOT NULL,
  `BizTypeName` varchar(10) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_biz_type
-- ----------------------------
INSERT INTO `tab_biz_type` VALUES ('20111208182605754429', '互联网专线');
INSERT INTO `tab_biz_type` VALUES ('20111208182616823109', 'IMS专线');
INSERT INTO `tab_biz_type` VALUES ('20111208182625112747', 'VOIP专线');
INSERT INTO `tab_biz_type` VALUES ('20111208182634173827', '广域网专线');

-- ----------------------------
-- Table structure for `tab_breakdown_type`
-- ----------------------------
DROP TABLE IF EXISTS `tab_breakdown_type`;
CREATE TABLE `tab_breakdown_type` (
  `ID` varchar(20) NOT NULL,
  `BreakdownTypeName` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_breakdown_type
-- ----------------------------
INSERT INTO `tab_breakdown_type` VALUES ('20111018092906552118', '硬件故障');
INSERT INTO `tab_breakdown_type` VALUES ('2011101809291323558', '软件故障');
INSERT INTO `tab_breakdown_type` VALUES ('20111018092930102672', '线路故障');
INSERT INTO `tab_breakdown_type` VALUES ('2011101809294434690', '其他故障');

-- ----------------------------
-- Table structure for `tab_build_way`
-- ----------------------------
DROP TABLE IF EXISTS `tab_build_way`;
CREATE TABLE `tab_build_way` (
  `ID` varchar(20) NOT NULL,
  `BuildWayName` varchar(10) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_build_way
-- ----------------------------
INSERT INTO `tab_build_way` VALUES ('20111215000302656868', '移动自建');
INSERT INTO `tab_build_way` VALUES ('20111215000310955565', '铁通合作');
INSERT INTO `tab_build_way` VALUES ('20111215000319596545', '广电合作');
INSERT INTO `tab_build_way` VALUES ('20111215000327149256', '铁通自建');

-- ----------------------------
-- Table structure for `tab_certificate`
-- ----------------------------
DROP TABLE IF EXISTS `tab_certificate`;
CREATE TABLE `tab_certificate` (
  `ID` varchar(20) NOT NULL,
  `CertificateName` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_certificate
-- ----------------------------
INSERT INTO `tab_certificate` VALUES ('20111108213353502735', '数据维护D级');
INSERT INTO `tab_certificate` VALUES ('20111108213451307268', '数据维护A级');
INSERT INTO `tab_certificate` VALUES ('20111108213458241889', '数据维护B级');
INSERT INTO `tab_certificate` VALUES ('20111108213505393338', '数据维护C级');

-- ----------------------------
-- Table structure for `tab_city`
-- ----------------------------
DROP TABLE IF EXISTS `tab_city`;
CREATE TABLE `tab_city` (
  `ID` varchar(20) NOT NULL,
  `CityName` varchar(50) default NULL,
  `CityCode` varchar(4) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_city
-- ----------------------------
INSERT INTO `tab_city` VALUES ('20110928151246297903', '泉州市', '0595');

-- ----------------------------
-- Table structure for `tab_clie_equip`
-- ----------------------------
DROP TABLE IF EXISTS `tab_clie_equip`;
CREATE TABLE `tab_clie_equip` (
  `ID` varchar(20) NOT NULL,
  `OccupySlot` varchar(10) default NULL,
  `BoardType` varchar(30) default NULL,
  `OccupyPort` varchar(10) default NULL,
  `ClieId` varchar(20) default NULL,
  `EquipId` varchar(20) default NULL,
  `LineId` varchar(20) default NULL,
  `DelFlag` smallint(6) default NULL,
  `PortTypeId` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_clie_equip
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_clie_grid`
-- ----------------------------
DROP TABLE IF EXISTS `tab_clie_grid`;
CREATE TABLE `tab_clie_grid` (
  `ID` varchar(20) NOT NULL,
  `GridId` varchar(20) default NULL,
  `ClieId` varchar(20) default NULL,
  `DelFlag` smallint(6) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_clie_grid
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_clie_scale_grade`
-- ----------------------------
DROP TABLE IF EXISTS `tab_clie_scale_grade`;
CREATE TABLE `tab_clie_scale_grade` (
  `ID` varchar(20) NOT NULL,
  `ScaleGradeName` varchar(8) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_clie_scale_grade
-- ----------------------------
INSERT INTO `tab_clie_scale_grade` VALUES ('20110927004327490767', 'A');
INSERT INTO `tab_clie_scale_grade` VALUES ('20110928115914797051', 'B');
INSERT INTO `tab_clie_scale_grade` VALUES ('20110928115922147239', 'C');
INSERT INTO `tab_clie_scale_grade` VALUES ('20110928120349476075', '待定');

-- ----------------------------
-- Table structure for `tab_clie_serv_star_leve`
-- ----------------------------
DROP TABLE IF EXISTS `tab_clie_serv_star_leve`;
CREATE TABLE `tab_clie_serv_star_leve` (
  `ID` varchar(20) NOT NULL,
  `StarLeveName` varchar(8) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_clie_serv_star_leve
-- ----------------------------
INSERT INTO `tab_clie_serv_star_leve` VALUES ('20110927004316115471', 'A1');
INSERT INTO `tab_clie_serv_star_leve` VALUES ('20110928120314537731', 'A2');
INSERT INTO `tab_clie_serv_star_leve` VALUES ('20110928120323578221', 'B1');
INSERT INTO `tab_clie_serv_star_leve` VALUES ('20110928120328241319', 'B2');
INSERT INTO `tab_clie_serv_star_leve` VALUES ('20110928120334486720', 'C');
INSERT INTO `tab_clie_serv_star_leve` VALUES ('20110928120344276380', '待定');

-- ----------------------------
-- Table structure for `tab_collar`
-- ----------------------------
DROP TABLE IF EXISTS `tab_collar`;
CREATE TABLE `tab_collar` (
  `ID` varchar(20) NOT NULL,
  `Num` int(11) default NULL,
  `CollarDate` datetime NOT NULL,
  `CollarSuppliesId` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_collar
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_collar_supplies`
-- ----------------------------
DROP TABLE IF EXISTS `tab_collar_supplies`;
CREATE TABLE `tab_collar_supplies` (
  `ID` varchar(255) NOT NULL,
  `DistrictId` varchar(20) default NULL,
  `CollarDate` datetime NOT NULL,
  `CreateUserId` varchar(20) NOT NULL,
  `CreateDate` datetime NOT NULL,
  `DelFlag` smallint(6) default NULL,
  `Remark` varchar(200) default NULL,
  `Num` int(11) default NULL,
  `CompanyId` varchar(20) default NULL,
  `CollarUser` varchar(30) default NULL,
  `SuppliesTypeId` varchar(20) default NULL,
  `IsArrival` varchar(2) default NULL,
  `ArrivalNum` int(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_collar_supplies
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_collar_supplies_detail`
-- ----------------------------
DROP TABLE IF EXISTS `tab_collar_supplies_detail`;
CREATE TABLE `tab_collar_supplies_detail` (
  `ID` varchar(255) NOT NULL,
  `Num` int(11) default NULL,
  `SuppliesTypeId` varchar(20) default NULL,
  `CollarSuppliesId` varchar(20) default NULL,
  `DelFlag` smallint(6) default NULL,
  `Remark` varchar(100) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_collar_supplies_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_community_manager`
-- ----------------------------
DROP TABLE IF EXISTS `tab_community_manager`;
CREATE TABLE `tab_community_manager` (
  `ID` varchar(20) NOT NULL,
  `FullName` varchar(20) default NULL,
  `IDCardNumber` varchar(18) default NULL,
  `LinkTel` varchar(15) default NULL,
  `EntryDate` datetime default NULL,
  `QuitDate` datetime default NULL,
  `Wage` decimal(7,2) default NULL,
  `IsFullTime` smallint(6) default NULL,
  `IsInService` smallint(6) default NULL,
  `CityId` varchar(20) default NULL,
  `DistrictId` varchar(20) default NULL,
  `CompanyId` varchar(20) default NULL,
  `SaleDepartmentId` varchar(20) default NULL,
  `DelFlag` smallint(6) default NULL,
  `CreateUserId` varchar(20) default NULL,
  `CreateDate` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_community_manager
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_community_type`
-- ----------------------------
DROP TABLE IF EXISTS `tab_community_type`;
CREATE TABLE `tab_community_type` (
  `ID` varchar(20) NOT NULL,
  `CommunityTypeName` varchar(10) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_community_type
-- ----------------------------
INSERT INTO `tab_community_type` VALUES ('20111215000142200662', '入户');
INSERT INTO `tab_community_type` VALUES ('20111215000151920261', '到弱电井');
INSERT INTO `tab_community_type` VALUES ('20111215000200398328', '其他');

-- ----------------------------
-- Table structure for `tab_company`
-- ----------------------------
DROP TABLE IF EXISTS `tab_company`;
CREATE TABLE `tab_company` (
  `ID` varchar(20) NOT NULL,
  `CompanyName` varchar(30) default NULL,
  `FullName` varchar(100) default NULL,
  `IsMaintain` smallint(6) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_company
-- ----------------------------
INSERT INTO `tab_company` VALUES ('20110928114938767045', '泉邮', '泉州市泉邮信息技术有限公司', '1');
INSERT INTO `tab_company` VALUES ('20110928115155247752', '国脉', '国脉', '1');
INSERT INTO `tab_company` VALUES ('20110928115247719586', '移动', '移动', '0');
INSERT INTO `tab_company` VALUES ('20111215000623166279', '铁通', '铁通', '1');

-- ----------------------------
-- Table structure for `tab_component`
-- ----------------------------
DROP TABLE IF EXISTS `tab_component`;
CREATE TABLE `tab_component` (
  `ID` varchar(20) NOT NULL,
  `ComponentName` varchar(30) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_component
-- ----------------------------
INSERT INTO `tab_component` VALUES ('20111018093012207609', '读卡器');
INSERT INTO `tab_component` VALUES ('20111018093021610181', '发票打印机');
INSERT INTO `tab_component` VALUES ('20111018093026313139', '清单打印机');
INSERT INTO `tab_component` VALUES ('20111018093040718538', '识币器');
INSERT INTO `tab_component` VALUES ('2011101809305284810', '触摸屏');
INSERT INTO `tab_component` VALUES ('2011101809311042747', 'UPS电源');
INSERT INTO `tab_component` VALUES ('20111018093122659831', '主板');
INSERT INTO `tab_component` VALUES ('20111018093128346274', '小键盘');
INSERT INTO `tab_component` VALUES ('2011101809314119011', '其他');

-- ----------------------------
-- Table structure for `tab_cop_cyc`
-- ----------------------------
DROP TABLE IF EXISTS `tab_cop_cyc`;
CREATE TABLE `tab_cop_cyc` (
  `ID` varchar(255) NOT NULL,
  `CycTimeId` text,
  `BizTypeId` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_cop_cyc
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_cop_cyctime`
-- ----------------------------
DROP TABLE IF EXISTS `tab_cop_cyctime`;
CREATE TABLE `tab_cop_cyctime` (
  `ID` varchar(255) NOT NULL,
  `CycTime` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_cop_cyctime
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_cop_enrol`
-- ----------------------------
DROP TABLE IF EXISTS `tab_cop_enrol`;
CREATE TABLE `tab_cop_enrol` (
  `ID` varchar(255) NOT NULL,
  `LineId` text,
  `AssuranLeveId` text,
  `GroupClieId` text,
  `BizTypeId` text,
  `CycId` text,
  `CycTime` text,
  `Address` text,
  `ConnectStation` text,
  `ConnectPort` text,
  `Linkman` text,
  `Phone` text,
  `NetworkingModeId` text,
  `ConnectCircs` text,
  `ElectricalLineCheck` text,
  `Lineclutter` text,
  `IsColligationCircsInGear` text,
  `IsHiddenTroubleBe` text,
  `EquipmentRunStateCheck` text,
  `IsDustCatcher` text,
  `LabelCircs` text,
  `PortData` text,
  `IsEnvironmentalHazardsExist` text,
  `Entironmentcontent` text,
  `IsEquipmentEarth` text,
  `EmpennageLabel` text,
  `GatewayMinimumDelay` text,
  `GatewayMaximumDelay` text,
  `GatewayAverageDelay` text,
  `GatewaySubstitutionRate` text,
  `DnsMinimumDelay` text,
  `DnsMaximumDelay` text,
  `DnsAverageDelay` text,
  `DnsSubstitutionRate` text,
  `NetworkSpeedTest` text,
  `DownloadTest` text,
  `IsNormalLogin` text,
  `TheAverageDelay` text,
  `SubstitutionRate` text,
  `PhoneCallTesting` text,
  `FaxCallTesting` text,
  `MaintenanceOfViews` text,
  `Problems` text,
  `LastProblemSolvingSituations` text,
  `Remarks` text,
  `Attachment` text,
  `AttachmentName` text,
  `NewAttachmentName` text,
  `CycTimeId` text,
  `CopPlanId` varchar(50) default NULL,
  `PersonnelId` varchar(50) default NULL,
  `CycEnrolTime` datetime default NULL,
  `Temperature` varchar(10) default NULL,
  `Humidity` varchar(10) default NULL,
  `OpticalCableLaying` varchar(10) default NULL,
  `DeviceLabel` varchar(10) default NULL,
  `PowerLabel` varchar(10) default NULL,
  `FiberOpticCableLabel` varchar(10) default NULL,
  `CircuitLabel` varchar(10) default NULL,
  `UPSAlarms` varchar(10) default NULL,
  `InverterAlarm` varchar(10) default NULL,
  `ACVoltage` varchar(10) default NULL,
  `DCVoltage` varchar(10) default NULL,
  `ZeroToVoltage` varchar(10) default NULL,
  `EquipmentGrounding` varchar(10) default NULL,
  `OtherInspectionItems` text,
  `OperationalRecords` text,
  `OverallAssessment` text,
  `ClientOpinionSignature` text,
  `IsDelete` int(11) default NULL,
  `CreationTime` datetime default NULL,
  `DeleteTime` datetime default NULL,
  `CreateUserId` varchar(50) default NULL,
  `Personnel` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_cop_enrol
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_cop_plan`
-- ----------------------------
DROP TABLE IF EXISTS `tab_cop_plan`;
CREATE TABLE `tab_cop_plan` (
  `ID` varchar(255) NOT NULL,
  `LineID` text,
  `InspectionStartTime` text,
  `InspectionEndTime` text,
  `BizTypeId` text,
  `CycId` text,
  `PersonnelId` text,
  `GroupId` text,
  `CycTimeId` text,
  `DedicateLineId` varchar(50) default NULL,
  `StartCycTime` datetime default NULL,
  `NextCycTime` datetime default NULL,
  `CycTime` varchar(10) default NULL,
  `IsDelete` int(11) default NULL,
  `CreationTime` datetime default NULL,
  `DeleteTime` datetime default NULL,
  `ProductNoLine` varchar(80) default NULL,
  `CreateUserId` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_cop_plan
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_cop_terminalenrol`
-- ----------------------------
DROP TABLE IF EXISTS `tab_cop_terminalenrol`;
CREATE TABLE `tab_cop_terminalenrol` (
  `ID` varchar(255) NOT NULL,
  `TerminalEnrolTime` datetime NOT NULL,
  `Unit` varchar(50) default NULL,
  `Contact` varchar(50) default NULL,
  `TerminalPlanId` varchar(50) default NULL,
  `Phone` varchar(50) default NULL,
  `OperatingStatus` varchar(10) default NULL,
  `HandlingClean` varchar(10) default NULL,
  `PaperWarehouseClean` varchar(10) default NULL,
  `ThermalHeadFilmClean` varchar(10) default NULL,
  `ListOfPrinters` varchar(10) default NULL,
  `InvoicePrinter` varchar(10) default NULL,
  `KnowTheCoin` varchar(10) default NULL,
  `UPSPowerSupply` varchar(10) default NULL,
  `MetalKeypad` varchar(50) default NULL,
  `TrainingAndExchange` text,
  `PersonnelId` varchar(50) default NULL,
  `MaintenanceTime` datetime NOT NULL,
  `ServiceSatisfaction` varchar(10) default NULL,
  `Personnel` varchar(50) default NULL,
  `IsDelete` int(11) default NULL,
  `CreationTime` datetime default NULL,
  `DeleteTime` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_cop_terminalenrol
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_cop_terminalplan`
-- ----------------------------
DROP TABLE IF EXISTS `tab_cop_terminalplan`;
CREATE TABLE `tab_cop_terminalplan` (
  `ID` varchar(255) NOT NULL,
  `SelfHelpEquipId` varchar(50) default NULL,
  `StartTerminalTime` datetime NOT NULL,
  `NextTerminalTime` datetime NOT NULL,
  `TerminalTimeId` varchar(50) default NULL,
  `TerminalTime` varchar(50) default NULL,
  `IsDelete` int(11) default NULL,
  `CreationTime` datetime default NULL,
  `DeleteTime` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_cop_terminalplan
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_cop_terminaltime`
-- ----------------------------
DROP TABLE IF EXISTS `tab_cop_terminaltime`;
CREATE TABLE `tab_cop_terminaltime` (
  `ID` varchar(255) NOT NULL,
  `TerminalTime` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_cop_terminaltime
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_course_type`
-- ----------------------------
DROP TABLE IF EXISTS `tab_course_type`;
CREATE TABLE `tab_course_type` (
  `ID` varchar(255) NOT NULL,
  `NAME` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_course_type
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_dedicate_line`
-- ----------------------------
DROP TABLE IF EXISTS `tab_dedicate_line`;
CREATE TABLE `tab_dedicate_line` (
  `ID` varchar(20) NOT NULL,
  `ContractBandwidth` varchar(10) default NULL,
  `ActualBandwidth` varchar(10) default NULL,
  `VLANNumber` varchar(10) default NULL,
  `GateWay` varchar(20) default NULL,
  `IP` varchar(20) default NULL,
  `SubnetMask` varchar(20) default NULL,
  `Rate` varchar(20) default NULL,
  `RalayNumber` varchar(50) default NULL,
  `SignalModel` varchar(20) default NULL,
  `TelNumber` varchar(200) default NULL,
  `CityNetSwitch` varchar(50) default NULL,
  `CityNetSwitchPort` varchar(100) default NULL,
  `ComputRoomtTEquip` varchar(50) default NULL,
  `ComputRoomtTEquipPort` varchar(100) default NULL,
  `StationName` varchar(10) default NULL,
  `StationTEquipAndPort` varchar(100) default NULL,
  `CircuitNumber` varchar(50) default NULL,
  `ZDDFT` varchar(50) default NULL,
  `ZDDFE` varchar(50) default NULL,
  `TermiEquip` varchar(100) default NULL,
  `TermiPort` varchar(10) default NULL,
  `ADDFT` varchar(50) default NULL,
  `ADDFE` varchar(50) default NULL,
  `ZBizEquip` varchar(100) default NULL,
  `ZBizEquipPort` varchar(10) default NULL,
  `ATEquip` varchar(100) default NULL,
  `ATEquipPort` varchar(100) default NULL,
  `ABizEquip` varchar(10) default NULL,
  `ABizEquipPort` varchar(100) default NULL,
  `BizComputRoomName` varchar(10) default NULL,
  `OLTEquipAPort` varchar(100) default NULL,
  `LOID` varchar(30) default NULL,
  `DDFT` varchar(50) default NULL,
  `DDFE` varchar(50) default NULL,
  `StartDateTime` datetime default NULL,
  `IsAccessLocalNet` smallint(6) default NULL,
  `IsAccessProviNet` smallint(6) default NULL,
  `Remark` text,
  `LineState` smallint(6) default NULL,
  `CoreId` varchar(20) default NULL,
  `NetWorkingModeId` varchar(20) default NULL,
  `BizTypeId` varchar(20) default NULL,
  `ClieId` varchar(20) default NULL,
  `BizAssuranLeveId` varchar(20) default NULL,
  `OtherInfoID` varchar(20) default NULL,
  `LineNo` varchar(20) default NULL,
  `State` smallint(6) default NULL,
  `ProductNo` varchar(80) default NULL,
  `CreateUserId` varchar(20) default NULL,
  `CreateDate` datetime default NULL,
  `DelFlag` smallint(6) default NULL,
  `CompanyId` varchar(20) default NULL,
  `DistrictId` varchar(20) default NULL,
  `ABizEquipId` varchar(20) default NULL,
  `ZBizEquipId` varchar(20) default NULL,
  `ZStationName` varchar(20) default NULL,
  `ZComputRoomName` varchar(40) default NULL,
  `ZTEquip` varchar(40) default NULL,
  `ZTEquipPort` varchar(40) default NULL,
  `AccessProviNet` varchar(20) default NULL,
  `AccessLocalNet` varchar(20) default NULL,
  `ZClieId` varchar(20) default NULL,
  `BizNote` varchar(100) default NULL,
  `ZNetWorkingModeId` varchar(20) default NULL,
  `LineType` varchar(2) default NULL,
  `UpdateUserId` varchar(20) default NULL,
  `UpdateUserName` varchar(30) default NULL,
  `UpdateDate` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_dedicate_line
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_diploma`
-- ----------------------------
DROP TABLE IF EXISTS `tab_diploma`;
CREATE TABLE `tab_diploma` (
  `ID` varchar(20) NOT NULL,
  `DiplomaName` varchar(10) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_diploma
-- ----------------------------
INSERT INTO `tab_diploma` VALUES ('20111108213121585746', '大专');
INSERT INTO `tab_diploma` VALUES ('20111108213125547112', '本科');
INSERT INTO `tab_diploma` VALUES ('20111108213138372220', '硕士');
INSERT INTO `tab_diploma` VALUES ('20111108213154270744', '中专');
INSERT INTO `tab_diploma` VALUES ('20111108213204796850', '技校');
INSERT INTO `tab_diploma` VALUES ('20111108213215921556', '博士后');
INSERT INTO `tab_diploma` VALUES ('20111108213227949936', '高中');
INSERT INTO `tab_diploma` VALUES ('20111108213237437714', '博士');

-- ----------------------------
-- Table structure for `tab_distribution`
-- ----------------------------
DROP TABLE IF EXISTS `tab_distribution`;
CREATE TABLE `tab_distribution` (
  `ID` varchar(20) NOT NULL,
  `CreateDatetime` datetime default NULL,
  `CityId` varchar(20) default NULL,
  `DistrictId` varchar(20) default NULL,
  `SaleDepartmentId` varchar(20) default NULL,
  `Remark` varchar(20) default NULL,
  `CompanyId` varchar(20) default NULL,
  `Num` int(11) default NULL,
  `DistributionUser` varchar(30) default NULL,
  `DCompanyId` varchar(20) default NULL,
  `State` varchar(2) default NULL,
  `Reason` text,
  `DelFlag` smallint(6) default NULL,
  `CreateUserId` varchar(20) default NULL,
  `CreateDate` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_distribution
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_distribution_detail`
-- ----------------------------
DROP TABLE IF EXISTS `tab_distribution_detail`;
CREATE TABLE `tab_distribution_detail` (
  `ID` varchar(20) NOT NULL,
  `Num` int(11) default NULL,
  `DistributionId` varchar(20) default NULL,
  `UnitId` varchar(20) default NULL,
  `SuppliesTypeId` varchar(20) default NULL,
  `SuppliesStockId` varchar(20) default NULL,
  `DistrictId` varchar(20) default NULL,
  `CompanyId` varchar(20) default NULL,
  `SaleDepartmentId` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_distribution_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_district`
-- ----------------------------
DROP TABLE IF EXISTS `tab_district`;
CREATE TABLE `tab_district` (
  `ID` varchar(20) NOT NULL,
  `DistrictName` varchar(20) default NULL,
  `CityId` varchar(20) default NULL,
  `ParentId` varchar(20) default NULL,
  `SeqNo` varchar(10) default NULL,
  `HNo` int(11) default NULL,
  `STATE` varchar(2) default NULL,
  `TypeFlag` smallint(6) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_district
-- ----------------------------
INSERT INTO `tab_district` VALUES ('20110928151246297903', '泉州市', null, '20120116162348387355', '0100', '2', '0', null);
INSERT INTO `tab_district` VALUES ('20110928151342773351', '市区', null, '20110928151246297903', '1100', '3', '0', null);
INSERT INTO `tab_district` VALUES ('20110928151357192049', '石狮', null, '20110928151246297903', '1104', '3', '0', '0');
INSERT INTO `tab_district` VALUES ('20110928151422200821', '晋江', null, '20110928151246297903', '1103', '3', '0', null);
INSERT INTO `tab_district` VALUES ('20110928151436403099', '永春', null, '20110928151246297903', '1108', '3', '0', null);
INSERT INTO `tab_district` VALUES ('20110928151448311689', '安溪', null, '20110928151246297903', '1107', '3', '0', null);
INSERT INTO `tab_district` VALUES ('20110928151457171078', '惠安', null, '20110928151246297903', '1106', '3', '0', null);
INSERT INTO `tab_district` VALUES ('20110928151505183228', '南安', null, '20110928151246297903', '1105', '3', '0', null);
INSERT INTO `tab_district` VALUES ('20110928151523232545', '泉港', null, '20110928151246297903', '1110', '3', '0', null);
INSERT INTO `tab_district` VALUES ('20110928151534663477', '德化', null, '20110928151246297903', '1109', '3', '0', null);
INSERT INTO `tab_district` VALUES ('20111208111816536402', '丰泽', null, '20110928151342773351', '1101', '4', '0', null);
INSERT INTO `tab_district` VALUES ('20111208112131224905', '鲤城', null, '20110928151342773351', '1102', '4', '0', null);
INSERT INTO `tab_district` VALUES ('20120116162103123359', 'Root', null, '-1                  ', '0000', '0', '0', null);
INSERT INTO `tab_district` VALUES ('20120116162348387355', '福建省', null, '20120116162103123359', '0001', '1', '0', '0');

-- ----------------------------
-- Table structure for `tab_duty`
-- ----------------------------
DROP TABLE IF EXISTS `tab_duty`;
CREATE TABLE `tab_duty` (
  `ID` varchar(20) NOT NULL,
  `DutyName` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_duty
-- ----------------------------
INSERT INTO `tab_duty` VALUES ('20111103205743374707', '项目负责人');
INSERT INTO `tab_duty` VALUES ('20111103205755713268', '数据维护组长');
INSERT INTO `tab_duty` VALUES ('20111103205803780283', '数据维护工程师');
INSERT INTO `tab_duty` VALUES ('20111103205811828609', '数据维护技术员');

-- ----------------------------
-- Table structure for `tab_equipment`
-- ----------------------------
DROP TABLE IF EXISTS `tab_equipment`;
CREATE TABLE `tab_equipment` (
  `ID` varchar(20) NOT NULL,
  `EquipName` varchar(20) default NULL,
  `SeqNumber` varchar(20) default NULL,
  `Position` smallint(6) default NULL,
  `BaseStationName` varchar(20) default NULL,
  `MacAddress` varchar(50) default NULL,
  `StartDatetime` datetime default NULL,
  `LoginStyle` varchar(20) default NULL,
  `SnmpOnlyRead` varchar(20) default NULL,
  `SnmpOnlyWrite` varchar(20) default NULL,
  `UserName` varchar(20) default NULL,
  `PassWord` varchar(20) default NULL,
  `NetManageIp` varchar(20) default NULL,
  `WebPort` varchar(10) default NULL,
  `NetWorkingModeId` varchar(20) default NULL,
  `EquipModelId` varchar(20) default NULL,
  `FactoryId` varchar(20) default NULL,
  `EquipTypeId` varchar(20) default NULL,
  `Remark` varchar(200) default NULL,
  `OrderNo` varchar(10) default NULL,
  `State` smallint(6) default NULL,
  `EquipModelName` varchar(100) default NULL,
  `CreateUserId` varchar(20) default NULL,
  `CreateDate` datetime default NULL,
  `DelFlag` smallint(6) default NULL,
  `CompanyId` varchar(20) default NULL,
  `DistrictId` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_equipment
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_equip_component`
-- ----------------------------
DROP TABLE IF EXISTS `tab_equip_component`;
CREATE TABLE `tab_equip_component` (
  `ID` varchar(20) NOT NULL,
  `ComponentModel` varchar(30) default NULL,
  `ComponentId` varchar(20) default NULL,
  `SelfHelpEquipId` varchar(20) default NULL,
  `DelFlag` smallint(6) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_equip_component
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_equip_fault`
-- ----------------------------
DROP TABLE IF EXISTS `tab_equip_fault`;
CREATE TABLE `tab_equip_fault` (
  `ID` varchar(20) NOT NULL,
  `NoticeDateTime` datetime default NULL,
  `ReachDatetime` datetime default NULL,
  `SolveDatetime` datetime default NULL,
  `HandleTime` bigint(20) default NULL,
  `TroubleShooter` varchar(30) default NULL,
  `Description` text,
  `HandleProcess` text,
  `HandleResult` text,
  `IsReplace` smallint(6) default NULL,
  `Remark` text,
  `ComponentId` varchar(20) default NULL,
  `BreakdownTypeId` varchar(20) default NULL,
  `SelfHelpEquipId` varchar(20) default NULL,
  `ClieTel` varchar(15) default NULL,
  `UseNetName` varchar(20) default NULL,
  `TermiId` varchar(20) default NULL,
  `DistrictId` varchar(20) default NULL,
  `FactoryId` varchar(20) default NULL,
  `EquipModelId` varchar(20) default NULL,
  `CompanyId` varchar(20) default NULL,
  `State` smallint(6) default NULL,
  `CreateUserId` varchar(20) default NULL,
  `CreateDate` datetime default NULL,
  `DelFlag` smallint(6) default NULL,
  `UseNetNo` varchar(7) default NULL,
  `EquipModelName` varchar(100) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_equip_fault
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_equip_model`
-- ----------------------------
DROP TABLE IF EXISTS `tab_equip_model`;
CREATE TABLE `tab_equip_model` (
  `ID` varchar(20) NOT NULL,
  `EquipModelName` varchar(20) default NULL,
  `FactoryId` varchar(20) default NULL,
  `EquipTypeId` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_equip_model
-- ----------------------------
INSERT INTO `tab_equip_model` VALUES ('20111219111303507330', 'tee', '20110928154952367929', '20110927004205845040');

-- ----------------------------
-- Table structure for `tab_equip_type`
-- ----------------------------
DROP TABLE IF EXISTS `tab_equip_type`;
CREATE TABLE `tab_equip_type` (
  `ID` varchar(20) NOT NULL,
  `EquipTypeName` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_equip_type
-- ----------------------------
INSERT INTO `tab_equip_type` VALUES ('20110927004205845040', '交换机');
INSERT INTO `tab_equip_type` VALUES ('20110928154640993283', '路由器');
INSERT INTO `tab_equip_type` VALUES ('20110928154718799727', 'PDH');
INSERT INTO `tab_equip_type` VALUES ('2011092815473098686', '多业务光猫');
INSERT INTO `tab_equip_type` VALUES ('20110928154738571009', 'IPPBX');
INSERT INTO `tab_equip_type` VALUES ('20110928154746311557', 'IAD');
INSERT INTO `tab_equip_type` VALUES ('20110928154755588683', '协转');
INSERT INTO `tab_equip_type` VALUES ('20111013172830424424', 'ONU');

-- ----------------------------
-- Table structure for `tab_examinee_info`
-- ----------------------------
DROP TABLE IF EXISTS `tab_examinee_info`;
CREATE TABLE `tab_examinee_info` (
  `ID` varchar(255) NOT NULL,
  `FULLNAME` varchar(20) NOT NULL,
  `LOGINNAME` varchar(20) NOT NULL,
  `PASSWORD` text NOT NULL,
  `SEX` smallint(6) NOT NULL,
  `IDCardNumber` varchar(18) NOT NULL,
  `MobileNumber` varchar(15) default NULL,
  `DIPLOMA` text,
  `BIRTHDATE` datetime NOT NULL,
  `EXAMTYPE` varchar(20) default NULL,
  `REMARK` text,
  `DIPLOMAID` text,
  `EXAMTYPEID` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_examinee_info
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_examinee_range`
-- ----------------------------
DROP TABLE IF EXISTS `tab_examinee_range`;
CREATE TABLE `tab_examinee_range` (
  `ID` varchar(255) NOT NULL,
  `TESTSETID` text,
  `EXAMINEEID` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_examinee_range
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_exam_achievement`
-- ----------------------------
DROP TABLE IF EXISTS `tab_exam_achievement`;
CREATE TABLE `tab_exam_achievement` (
  `ID` varchar(255) NOT NULL,
  `CandidateID` text,
  `AnswerStartTime` datetime default NULL,
  `AnswerEndTime` datetime default NULL,
  `Fraction` text,
  `IsSubmitted` int(11) default NULL,
  `MarkingState` int(11) default NULL,
  `MarkingTime` datetime default NULL,
  `TestId` text,
  `UserId` text,
  `SetUpId` text,
  `ExamType` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_exam_achievement
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_exam_achievementx`
-- ----------------------------
DROP TABLE IF EXISTS `tab_exam_achievementx`;
CREATE TABLE `tab_exam_achievementx` (
  `ID` varchar(255) NOT NULL,
  `TopicNum` text,
  `Answer` text,
  `Score` text,
  `AchievementId` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_exam_achievementx
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_exam_answer_exam`
-- ----------------------------
DROP TABLE IF EXISTS `tab_exam_answer_exam`;
CREATE TABLE `tab_exam_answer_exam` (
  `ID` varchar(255) NOT NULL,
  `AchievementId` text,
  `TestSubjectId` text,
  `ProblemScore` text,
  `Comments` text,
  `SubjectAnswer` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_exam_answer_exam
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_exam_fillblanks_answer`
-- ----------------------------
DROP TABLE IF EXISTS `tab_exam_fillblanks_answer`;
CREATE TABLE `tab_exam_fillblanks_answer` (
  `ID` varchar(255) NOT NULL,
  `AnswerExamId` text,
  `AnswerContent` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_exam_fillblanks_answer
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_exam_judge_answer`
-- ----------------------------
DROP TABLE IF EXISTS `tab_exam_judge_answer`;
CREATE TABLE `tab_exam_judge_answer` (
  `ID` varchar(255) NOT NULL,
  `AnswerExamId` text,
  `AnswerContent` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_exam_judge_answer
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_exam_multiple_answer`
-- ----------------------------
DROP TABLE IF EXISTS `tab_exam_multiple_answer`;
CREATE TABLE `tab_exam_multiple_answer` (
  `ID` varchar(255) NOT NULL,
  `AnswerExamId` text,
  `AnswerContent` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_exam_multiple_answer
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_exam_type`
-- ----------------------------
DROP TABLE IF EXISTS `tab_exam_type`;
CREATE TABLE `tab_exam_type` (
  `ID` varchar(255) NOT NULL,
  `TYPE` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_exam_type
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_factory`
-- ----------------------------
DROP TABLE IF EXISTS `tab_factory`;
CREATE TABLE `tab_factory` (
  `ID` varchar(20) NOT NULL,
  `Abbrevia` varchar(20) default NULL,
  `ChinaName` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_factory
-- ----------------------------
INSERT INTO `tab_factory` VALUES ('20110928154952367929', '华为', '华为');
INSERT INTO `tab_factory` VALUES ('20110928154959578477', '中兴', '中兴');
INSERT INTO `tab_factory` VALUES ('20110928155042438440', '瑞斯康达', '瑞斯康达');
INSERT INTO `tab_factory` VALUES ('201109281550491209 ', 'DGC', 'DGC');
INSERT INTO `tab_factory` VALUES ('20110928155056392848', 'D-LINK', 'D-LINK');
INSERT INTO `tab_factory` VALUES ('20110928155103370043', 'ELINK', 'ELINK');
INSERT INTO `tab_factory` VALUES ('20110928155110131281', 'TP-LINK', 'TP-LINK');
INSERT INTO `tab_factory` VALUES ('20110928155117251338', '烽火通信', '烽火通信');
INSERT INTO `tab_factory` VALUES ('20110928155124392156', '烽火网络', '烽火网络');
INSERT INTO `tab_factory` VALUES ('20110928155134140344', '迈普', '迈普');
INSERT INTO `tab_factory` VALUES ('2011092815514230335', '敏讯', '敏讯');
INSERT INTO `tab_factory` VALUES ('20110928155157459086', '申瓯', '申瓯');
INSERT INTO `tab_factory` VALUES ('20110928155204206230', '思科', '思科');
INSERT INTO `tab_factory` VALUES ('20110928155210186656', '网经', '网经');
INSERT INTO `tab_factory` VALUES ('20110928155216738534', '锐捷通信', '锐捷通信');
INSERT INTO `tab_factory` VALUES ('20110928155222701563', '有恒斯康CCOM', '有恒斯康CCOM');
INSERT INTO `tab_factory` VALUES ('2011092815523373568', '珠海佳和', '珠海佳和');
INSERT INTO `tab_factory` VALUES ('20110928155241960241', '深信服', '深信服');
INSERT INTO `tab_factory` VALUES ('20110928155247529606', '华三', '华三');
INSERT INTO `tab_factory` VALUES ('20110928155254259283', '锐捷网络', '锐捷网络');
INSERT INTO `tab_factory` VALUES ('20110928155302818800', '一厂', '一厂');
INSERT INTO `tab_factory` VALUES ('20110928155308959547', '普天', '普天');
INSERT INTO `tab_factory` VALUES ('2011092815531468731', '其它', '其它');

-- ----------------------------
-- Table structure for `tab_fiber_core`
-- ----------------------------
DROP TABLE IF EXISTS `tab_fiber_core`;
CREATE TABLE `tab_fiber_core` (
  `ID` varchar(20) NOT NULL,
  `SeqNo` varchar(10) default NULL,
  `BaseStationName` varchar(30) default NULL,
  `ODUStation` varchar(30) default NULL,
  `ToPosition` varchar(30) default NULL,
  `Core` varchar(30) default NULL,
  `LineNo` varchar(20) default NULL,
  `ClieId` varchar(20) default NULL,
  `LineId` varchar(20) default NULL,
  `DelFlag` smallint(6) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_fiber_core
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_fillblanks_subject`
-- ----------------------------
DROP TABLE IF EXISTS `tab_fillblanks_subject`;
CREATE TABLE `tab_fillblanks_subject` (
  `ID` varchar(255) NOT NULL,
  `SUBJECTID` text,
  `ORDERNO` text,
  `ANSWER` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_fillblanks_subject
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_grid`
-- ----------------------------
DROP TABLE IF EXISTS `tab_grid`;
CREATE TABLE `tab_grid` (
  `ID` varchar(20) NOT NULL,
  `GridCode` varchar(20) default NULL,
  `GridName` varchar(50) default NULL,
  `Partners` varchar(50) default NULL,
  `NatureId` varchar(20) default NULL,
  `GridArea` varchar(30) default NULL,
  `DistrictId` varchar(20) default NULL,
  `CompanyId` varchar(20) default NULL,
  `OfficeId` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_grid
-- ----------------------------
INSERT INTO `tab_grid` VALUES ('20111212093004275069', 'QZHWGZW01', '泉州市区01（自维）', '泉州市公司', '20111107153155363788', null, '20110928151342773351', '20110928115247719586', null);
INSERT INTO `tab_grid` VALUES ('20111212093039133612', 'QZHWGZW02', '泉州安溪01（自维）', '安溪县公司', '20111107153155363788', null, '20110928151448311689', '20110928115247719586', null);
INSERT INTO `tab_grid` VALUES ('20111212093101948209', 'QZHWGZW03', '泉州永春01（自维）', '永春县公司', '20111107153155363788', null, '20110928151436403099', '20110928115247719586', null);
INSERT INTO `tab_grid` VALUES ('20111212093124831415', 'QZHWGZW04', '泉州德化01（自维）', '德化县公司', '20111107153155363788', null, '20110928151534663477', '20110928115247719586', null);
INSERT INTO `tab_grid` VALUES ('20111212093154913187', 'QZHWGZW05', '泉州石狮01（自维）', '石狮县公司', '20111107153155363788', null, '20110928151357192049', '20110928115247719586', null);
INSERT INTO `tab_grid` VALUES ('20111212093215268533', 'QZHWGZW06', '泉州南安01（自维）', '南安县公司', '20111107153155363788', null, '20110928151505183228', '20110928115247719586', null);
INSERT INTO `tab_grid` VALUES ('20111212093236192048', 'QZHWGZW07', '泉州晋江01（自维）', '晋江县公司', '20111107153155363788', null, '20110928151422200821', '20110928115247719586', null);
INSERT INTO `tab_grid` VALUES ('20111212093254295509', 'QZHWGZW08', '泉州惠安01（自维）', '惠安县公司', '20111107153155363788', null, '20110928151457171078', '20110928115247719586', null);
INSERT INTO `tab_grid` VALUES ('20111212093311856387', 'QZHWGZW09', '泉州泉港01（自维）', '泉港县公司', '20111107153155363788', null, '20110928151523232545', '20110928115247719586', null);
INSERT INTO `tab_grid` VALUES ('20111212094550485885', 'QZHWG039', '惠安网格03', '国脉', '20111107153143253946', '惠安崇武网格', '20110928151457171078', '20110928115155247752', null);
INSERT INTO `tab_grid` VALUES ('20111212094611336857', 'QZHWG038', '惠安网格02', '泉邮', '20111107153143253946', '惠安洛阳网格', '20110928151457171078', '20110928114938767045', null);
INSERT INTO `tab_grid` VALUES ('20111212094642814086', 'QZHWG037', '惠安网格01', '国脉', '20111107153143253946', '惠安城关网格', '20110928151457171078', '20110928115155247752', null);
INSERT INTO `tab_grid` VALUES ('20111212094708467275', 'QZHWG036', '晋江网格07', '国脉', '20111107153143253946', '晋江青阳网格', '20110928151422200821', '20110928115155247752', null);
INSERT INTO `tab_grid` VALUES ('20111212094732655587', 'QZHWG035', '晋江网格06', '泉邮', '20111107153143253946', '晋江罗山网格', '20110928151422200821', '20110928114938767045', null);
INSERT INTO `tab_grid` VALUES ('20111212094752465397', 'QZHWG034', '晋江网格05', '泉邮', '20111107153143253946', '晋江龙湖网格', '20110928151422200821', '20110928114938767045', null);
INSERT INTO `tab_grid` VALUES ('20111212094803590093', 'QZHWG033', '晋江网格04', '泉邮', '20111107153143253946', '晋江东石网格', '20110928151422200821', '20110928114938767045', null);
INSERT INTO `tab_grid` VALUES ('20111212094823971024', 'QZHWG032', '晋江网格03', '国脉', '20111107153143253946', '晋江池店网格', '20110928151422200821', '20110928115155247752', null);
INSERT INTO `tab_grid` VALUES ('20111212094847613640', 'QZHWG031', '晋江网格02', '泉邮', '20111107153143253946', '晋江陈埭网格', '20110928151422200821', '20110928114938767045', null);
INSERT INTO `tab_grid` VALUES ('20111212094901134525', 'QZHWG030', '晋江网格01', '泉邮', '20111107153143253946', '晋江安海网格', '20110928151422200821', '20110928114938767045', null);
INSERT INTO `tab_grid` VALUES ('20111212094923321964', 'QZHWG029', '南安网格06', '泉邮', '20111107153143253946', '南安沿海片网格', '20110928151505183228', '20110928114938767045', null);
INSERT INTO `tab_grid` VALUES ('20111212094941651162', 'QZHWG028', '南安网格05', '国脉', '20111107153143253946', '南安市区片网格', '20110928151505183228', '20110928115155247752', null);
INSERT INTO `tab_grid` VALUES ('20111212095009533932', 'QZHWG027', '南安网格04', '泉邮', '20111107153143253946', '南安英仑片网格', '20110928151505183228', '20110928114938767045', null);
INSERT INTO `tab_grid` VALUES ('20111212095031322943', 'QZHWG026', '南安网格03', '国脉', '20111107153143253946', '南安梅罗九片网格', '20110928151505183228', '20110928115155247752', null);
INSERT INTO `tab_grid` VALUES ('20111212095042720482', 'QZHWG025', '南安网格02', '国脉', '20111107153143253946', '南安诗淘码片网格', '20110928151505183228', '20110928115155247752', null);
INSERT INTO `tab_grid` VALUES ('20111212095058967365', 'QZHWG024', '南安网格01', '国脉', '20111107153143253946', '南安丰霞片网格', '20110928151505183228', '20110928115155247752', null);
INSERT INTO `tab_grid` VALUES ('20111212095134444008', 'QZHWG023', '石狮网格03', '泉邮', '20111107153143253946', '石狮湖滨网格', '20110928151357192049', '20110928114938767045', null);
INSERT INTO `tab_grid` VALUES ('20111212095152377739', 'QZHWG022', '石狮网格02', '国脉', '20111107153143253946', '石狮凤里网格', '20110928151357192049', '20110928115155247752', null);
INSERT INTO `tab_grid` VALUES ('20111212095203769342', 'QZHWG021', '石狮网格01', '国脉', '20111107153143253946', '石狮宝盖网格', '20110928151357192049', '20110928115155247752', null);
INSERT INTO `tab_grid` VALUES ('20111212095226602478', 'QZHWG020', '德化网格03', '国脉', '20111107153143253946', '德化西片区网格', '20110928151534663477', '20110928115155247752', null);
INSERT INTO `tab_grid` VALUES ('20111212095238483601', 'QZHWG19', '德化网格02', '国脉', '20111107153143253946', '德化东片区网格', '20110928151534663477', '20110928115155247752', null);
INSERT INTO `tab_grid` VALUES ('20111212095248115663', 'QZHWG18', '德化网格01', '国脉', '20111107153143253946', '德化城关片区网格', '20110928151534663477', '20110928115155247752', null);
INSERT INTO `tab_grid` VALUES ('20111212095317839436', 'QZHWG17', '永春网格04', '泉邮', '20111107153143253946', '永春城关片区网格', '20110928151436403099', '20110928114938767045', null);
INSERT INTO `tab_grid` VALUES ('20111212095330162992', 'QZHWG16', '永春网格03', '泉邮', '20111107153143253946', '永春外半县网格', '20110928151436403099', '20110928114938767045', null);
INSERT INTO `tab_grid` VALUES ('20111212095351785396', 'QZHWG15', '永春网格02', '国脉', '20111107153143253946', '永春蓬壶网格', '20110928151436403099', '20110928115155247752', null);
INSERT INTO `tab_grid` VALUES ('20111212095403898201', 'QZHWG14', '永春网格01', '国脉', '20111107153143253946', '永春坑仔口网格', '20110928151436403099', '20110928115155247752', null);
INSERT INTO `tab_grid` VALUES ('20111212095428707575', 'QZHWG13', '泉港网格01', '泉邮', '20111107153143253946', '泉港网格', '20110928151523232545', '20110928114938767045', null);
INSERT INTO `tab_grid` VALUES ('20111212095501941222', 'QZHWG12', '安溪网格05', '国脉', '20111107153143253946', '安溪剑斗网格', '20110928151448311689', '20110928115155247752', null);
INSERT INTO `tab_grid` VALUES ('20111212095517739596', 'QZHWG11', '安溪网格04', '泉邮', '20111107153143253946', '安溪虎邱网格', '20110928151448311689', '20110928114938767045', null);
INSERT INTO `tab_grid` VALUES ('20111212095533760723', 'QZHWG10', '安溪网格03', '国脉', '20111107153143253946', '安溪湖头网格', '20110928151448311689', '20110928115155247752', null);
INSERT INTO `tab_grid` VALUES ('20111212095555154279', 'QZHWG09', '安溪网格02', '国脉', '20111107153143253946', '安溪长坑网格', '20110928151448311689', '20110928115155247752', null);
INSERT INTO `tab_grid` VALUES ('20111212095609647686', 'QZHWG08', '安溪网格01', '泉邮', '20111107153143253946', '安溪城关网格', '20110928151448311689', '20110928114938767045', null);
INSERT INTO `tab_grid` VALUES ('20111212095650631381', 'QZHWG07', '泉州市区网格07', '国脉', '20111107153143253946', '泉州东海网格', '20110928151342773351', '20110928115155247752', null);
INSERT INTO `tab_grid` VALUES ('20111212095707464971', 'QZHWG06', '泉州市区网格06', '泉邮', '20111107153143253946', '泉州江南网格', '20110928151342773351', '20110928114938767045', null);
INSERT INTO `tab_grid` VALUES ('20111212095723460663', 'QZHWG05', '泉州市区网格05', '国脉', '20111107153143253946', '泉州罗马网格', '20110928151342773351', '20110928115155247752', null);
INSERT INTO `tab_grid` VALUES ('20111212095741238239', 'QZHWG04', '泉州市区网格04', '国脉', '20111107153143253946', '泉州城东河市网格', '20110928151342773351', '20110928115155247752', null);
INSERT INTO `tab_grid` VALUES ('20111212095756327273', 'QZHWG03', '泉州市区网格03', '国脉', '20111107153143253946', '泉州市温东网格', '20110928151342773351', '20110928115155247752', null);
INSERT INTO `tab_grid` VALUES ('20111212095810137527', 'QZHWG02', '泉州市区网格02', '泉邮', '20111107153143253946', '泉州西北片网格', '20110928151342773351', '20110928114938767045', null);
INSERT INTO `tab_grid` VALUES ('20111212095820819809', 'QZHWG01', '泉州市区网格01', '泉邮', '20111107153143253946', '泉州市温西网格', '20110928151342773351', '20110928114938767045', null);

-- ----------------------------
-- Table structure for `tab_group_clie`
-- ----------------------------
DROP TABLE IF EXISTS `tab_group_clie`;
CREATE TABLE `tab_group_clie` (
  `ID` varchar(255) NOT NULL,
  `ClieNo` text,
  `ClieName` text,
  `ClieContacTel` text,
  `ClieAddress` text,
  `ClieAreaLongitude` text,
  `ClieAreaLatitude` text,
  `GroupClieContacts` text,
  `GroupClieContacTel` text,
  `ClieManager` text,
  `ClieManagerTel` text,
  `ClieBizContacts` text,
  `ClieBizContacTel` text,
  `CityId` text,
  `DistrictId` text,
  `CompanyId` text,
  `GridId` text,
  `AssuranLeveId` text,
  `StarLeveId` text,
  `ScaleGradeId` text,
  `State` smallint(6) default NULL,
  `CreateUserId` varchar(20) default NULL,
  `CreateDate` datetime default NULL,
  `DelFlag` smallint(6) default NULL,
  `BizAssuranLeveId` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_group_clie
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_inventory`
-- ----------------------------
DROP TABLE IF EXISTS `tab_inventory`;
CREATE TABLE `tab_inventory` (
  `ID` varchar(20) NOT NULL,
  `Operator` varchar(20) default NULL,
  `InventoryDate` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_inventory
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_inventory_file`
-- ----------------------------
DROP TABLE IF EXISTS `tab_inventory_file`;
CREATE TABLE `tab_inventory_file` (
  `ID` varchar(255) NOT NULL,
  `LedgerFileName` text,
  `LedgerFileType` text,
  `LedgerFileSize` int(11) default NULL,
  `LocalDatetime` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_inventory_file
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_ioclass`
-- ----------------------------
DROP TABLE IF EXISTS `tab_ioclass`;
CREATE TABLE `tab_ioclass` (
  `ID` varchar(20) NOT NULL,
  `TypeName` varchar(6) default NULL,
  `StyleName` varchar(20) default NULL,
  `ClassName` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_ioclass
-- ----------------------------
INSERT INTO `tab_ioclass` VALUES ('20111117172515778103', 'OList', '全新入库', 'NewIList');
INSERT INTO `tab_ioclass` VALUES ('20111118151658758666', 'IList', '回收入库', 'RecycleIList');
INSERT INTO `tab_ioclass` VALUES ('20111118151721369030', 'IList', '归还入库', 'ReturnIList');
INSERT INTO `tab_ioclass` VALUES ('20111118151739962161', 'IList', '返修回收', 'RepairRecovery');
INSERT INTO `tab_ioclass` VALUES ('20111118151807923410', 'IList', '领用出库', 'CollarOList');
INSERT INTO `tab_ioclass` VALUES ('20111118151824511765', 'OList', '借用出库', 'BorrowOList');

-- ----------------------------
-- Table structure for `tab_iodetai`
-- ----------------------------
DROP TABLE IF EXISTS `tab_iodetai`;
CREATE TABLE `tab_iodetai` (
  `ID` varchar(20) NOT NULL,
  `EquipName` varchar(30) default NULL,
  `Num` int(11) default NULL,
  `EquipModelId` varchar(20) default NULL,
  `FactoryId` varchar(20) default NULL,
  `EquipState` smallint(6) default NULL,
  `State` smallint(6) default NULL,
  `Remark` varchar(200) default NULL,
  `IOListId` varchar(20) default NULL,
  `ReNum` int(11) default NULL,
  `ReIOListId` varchar(20) default NULL,
  `ReIODetailId` varchar(20) default NULL,
  `EquipTypeId` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_iodetai
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_iodetail`
-- ----------------------------
DROP TABLE IF EXISTS `tab_iodetail`;
CREATE TABLE `tab_iodetail` (
  `ID` varchar(20) NOT NULL,
  `EquipModelName` varchar(30) default NULL,
  `Num` int(11) default NULL,
  `EquipModelId` varchar(20) default NULL,
  `EquipTypeId` varchar(20) default NULL,
  `FactoryId` varchar(20) default NULL,
  `StockTypeId` varchar(20) default NULL,
  `EquipState` varchar(2) default NULL,
  `UnitName` varchar(20) default NULL,
  `State` varchar(2) default NULL,
  `Remark` varchar(200) default NULL,
  `IOListId` varchar(20) default NULL,
  `ReNum` int(11) default NULL,
  `ReIOListId` varchar(20) default NULL,
  `ReIODetailId` varchar(20) default NULL,
  `IsArrival` varchar(2) default NULL,
  `ArrivalNum` int(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_iodetail
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_iolist`
-- ----------------------------
DROP TABLE IF EXISTS `tab_iolist`;
CREATE TABLE `tab_iolist` (
  `ID` varchar(20) NOT NULL,
  `Type` varchar(30) default NULL,
  `IOClassId` varchar(20) default NULL,
  `TotalNum` int(11) default NULL,
  `BadNum` int(11) default NULL,
  `ScrapNum` int(11) default NULL,
  `Operator` varchar(20) default NULL,
  `IODate` datetime default NULL,
  `Auditor` varchar(20) default NULL,
  `AuditDate` datetime default NULL,
  `State` smallint(6) default NULL,
  `Supplier` varchar(50) default NULL,
  `ICompanyId` varchar(20) default NULL,
  `IDistrictId` varchar(20) default NULL,
  `OCompanyId` varchar(20) default NULL,
  `ODistrictId` varchar(20) default NULL,
  `ClieId` varchar(20) default NULL,
  `ClieNo` varchar(20) default NULL,
  `Remark` varchar(200) default NULL,
  `IODetailId` varchar(20) default NULL,
  `IsArrival` varchar(2) default NULL,
  `ArrivalNum` int(11) default NULL,
  `Reason` varchar(200) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_iolist
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_item_property`
-- ----------------------------
DROP TABLE IF EXISTS `tab_item_property`;
CREATE TABLE `tab_item_property` (
  `ID` varchar(20) NOT NULL,
  `ItemPropertyName` varchar(30) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_item_property
-- ----------------------------
INSERT INTO `tab_item_property` VALUES ('20111108213909634093', '县公司线路管理');
INSERT INTO `tab_item_property` VALUES ('20111108213922113825', '县公司数据管理');
INSERT INTO `tab_item_property` VALUES ('20111108213929976845', '县公司基站管理');
INSERT INTO `tab_item_property` VALUES ('20111108213936475701', '县公司网优管理');
INSERT INTO `tab_item_property` VALUES ('20111108213942673395', '市公司线路管理');
INSERT INTO `tab_item_property` VALUES ('20111108213951963184', '市公司数据管理');
INSERT INTO `tab_item_property` VALUES ('20111108213959261740', '市公司基站管理');
INSERT INTO `tab_item_property` VALUES ('20111108214006694950', '市公司网优管理');

-- ----------------------------
-- Table structure for `tab_judge_subject`
-- ----------------------------
DROP TABLE IF EXISTS `tab_judge_subject`;
CREATE TABLE `tab_judge_subject` (
  `ID` varchar(255) NOT NULL,
  `SUBJECTID` text,
  `ANSWER` tinyint(1) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_judge_subject
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_line_equip`
-- ----------------------------
DROP TABLE IF EXISTS `tab_line_equip`;
CREATE TABLE `tab_line_equip` (
  `ID` varchar(20) NOT NULL,
  `LineId` varchar(20) NOT NULL,
  `EquipId` varchar(20) NOT NULL,
  `ClieId` varchar(20) NOT NULL,
  `DelFlag` smallint(6) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_line_equip
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_maintainer`
-- ----------------------------
DROP TABLE IF EXISTS `tab_maintainer`;
CREATE TABLE `tab_maintainer` (
  `ID` varchar(20) NOT NULL,
  `FullName` varchar(20) default NULL,
  `IDCardNumber` varchar(18) default NULL,
  `LinkTel` varchar(15) default NULL,
  `DistrictId` varchar(20) default NULL,
  `CompanyId` varchar(20) default NULL,
  `SaleDepartmentId` varchar(20) default NULL,
  `DelFlag` smallint(6) default NULL,
  `CreateUserId` varchar(20) default NULL,
  `CreateDate` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_maintainer
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_maintain_specialty`
-- ----------------------------
DROP TABLE IF EXISTS `tab_maintain_specialty`;
CREATE TABLE `tab_maintain_specialty` (
  `ID` varchar(20) NOT NULL,
  `MaintainSpecialtyName` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_maintain_specialty
-- ----------------------------
INSERT INTO `tab_maintain_specialty` VALUES ('20111107111049864303', '基站综维');
INSERT INTO `tab_maintain_specialty` VALUES ('20111107111101146697', '线路维护');
INSERT INTO `tab_maintain_specialty` VALUES ('20111107111110383433', '铁塔微波');
INSERT INTO `tab_maintain_specialty` VALUES ('20111107111121153834', '协维');
INSERT INTO `tab_maintain_specialty` VALUES ('20111107111129380799', '数据维护');
INSERT INTO `tab_maintain_specialty` VALUES ('20111107111141136337', '网优测试');

-- ----------------------------
-- Table structure for `tab_maintain_type`
-- ----------------------------
DROP TABLE IF EXISTS `tab_maintain_type`;
CREATE TABLE `tab_maintain_type` (
  `ID` varchar(20) NOT NULL,
  `MaintainTypeName` varchar(10) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_maintain_type
-- ----------------------------
INSERT INTO `tab_maintain_type` VALUES ('20111215000105676911', '安装');
INSERT INTO `tab_maintain_type` VALUES ('20111215000115506311', '维护');
INSERT INTO `tab_maintain_type` VALUES ('20111215000124144318', '移机');

-- ----------------------------
-- Table structure for `tab_meter_state`
-- ----------------------------
DROP TABLE IF EXISTS `tab_meter_state`;
CREATE TABLE `tab_meter_state` (
  `ID` varchar(20) NOT NULL,
  `MeterStateName` varchar(10) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_meter_state
-- ----------------------------
INSERT INTO `tab_meter_state` VALUES ('20111107110157823450', '在用');
INSERT INTO `tab_meter_state` VALUES ('20111107110831610311', '送修');
INSERT INTO `tab_meter_state` VALUES ('20111107110836208464', '报废');

-- ----------------------------
-- Table structure for `tab_multiple_subject`
-- ----------------------------
DROP TABLE IF EXISTS `tab_multiple_subject`;
CREATE TABLE `tab_multiple_subject` (
  `ID` varchar(255) NOT NULL,
  `MULTIPLECONTENT` text,
  `SUBJECTID` text,
  `ORDERNO` text,
  `ANSWER` tinyint(1) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_multiple_subject
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_nature`
-- ----------------------------
DROP TABLE IF EXISTS `tab_nature`;
CREATE TABLE `tab_nature` (
  `ID` varchar(20) NOT NULL,
  `NatureName` varchar(10) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_nature
-- ----------------------------
INSERT INTO `tab_nature` VALUES ('20111107153143253946', '代维');
INSERT INTO `tab_nature` VALUES ('20111107153155363788', '自维');

-- ----------------------------
-- Table structure for `tab_networking_mode`
-- ----------------------------
DROP TABLE IF EXISTS `tab_networking_mode`;
CREATE TABLE `tab_networking_mode` (
  `ID` varchar(20) NOT NULL,
  `ModeName` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_networking_mode
-- ----------------------------
INSERT INTO `tab_networking_mode` VALUES ('20110927004235768093', 'MSTP');
INSERT INTO `tab_networking_mode` VALUES ('20110928154239279918', 'MSAP');
INSERT INTO `tab_networking_mode` VALUES ('20110928154243604967', '协转');
INSERT INTO `tab_networking_mode` VALUES ('20110928154303609602', 'PON');
INSERT INTO `tab_networking_mode` VALUES ('20110928154310729649', '裸纤');

-- ----------------------------
-- Table structure for `tab_network_name`
-- ----------------------------
DROP TABLE IF EXISTS `tab_network_name`;
CREATE TABLE `tab_network_name` (
  `ID` varchar(20) NOT NULL,
  `NetworkName` varchar(100) default NULL,
  `NetworkNo` varchar(7) default NULL,
  `DistrictId` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_network_name
-- ----------------------------
INSERT INTO `tab_network_name` VALUES ('20111130095141552563', '泉州泉秀999电讯专营店', '5055005', '20111208112131224905');
INSERT INTO `tab_network_name` VALUES ('20111130095152398470', '惠安紫山营业厅', '5051666', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111130095205493468', '惠安辋川营业厅', '5051555', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111130095215997102', '惠安宏达专营店', '5056999', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111130095226570176', '惠安崇武嘟捷通专营店', '5051111', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208112431473164', '惠安螺城旭阳专营店', '5055005', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208112531576847', '惠安客户服务中心', '5056116', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208112601171912', '惠安中闽百汇', '5051120', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208112624124698', '惠安涂寨亿华专营店', '5055026', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208112641284165', '惠安涂寨阳光专营店', '5055019', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208112657461478', '惠安洛阳鑫山专营店', '5055002', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208112709116865', '惠安螺城中新专营店', '5051101', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208112722519041', '涂寨镇振源专营店', '5055020', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208112735271616', '惠安东岭清山专营店', '5055027', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208112748598286', '惠安崇武沟通专营店', '5055777', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208112754711545', '惠安洛阳远达专营店', '5055015', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208115509938747', '惠安洛阳永兴专营店', '5055013', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208115511246667', '盛达专营店', '5055888', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208115512909031', '惠安螺阳煌信专营店', '5055035', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208115513448631', '惠安张板三通专营店', '5055028', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208115514916630', '惠安新华都讯达专营店', '5050142', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208115515650605', '惠安中原专营店', '5051103', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208115517942939', '泉州芳草园营业厅', '5101202', '20111208112131224905');
INSERT INTO `tab_network_name` VALUES ('20111208115518981129', '泉州九一动感地带品牌店', '5101064', '20111208112131224905');
INSERT INTO `tab_network_name` VALUES ('20111208115529419698', '泉州九一路营业厅', '5101001', '20111208112131224905');
INSERT INTO `tab_network_name` VALUES ('20111208115531357738', '泉州鲤城营业厅', '5101008', '20111208112131224905');
INSERT INTO `tab_network_name` VALUES ('20111208115532621653', '泉州清蒙营业厅', '5109971', '20111208112131224905');
INSERT INTO `tab_network_name` VALUES ('20111208115533308521', '泉州西街动感店', '5109838', '20111208112131224905');
INSERT INTO `tab_network_name` VALUES ('20111208115534870734', '泉州丰华专营店', '5109876', '20111208112131224905');
INSERT INTO `tab_network_name` VALUES ('20111208115535137482', '泉州华远专营店', '5101169', '20111208112131224905');
INSERT INTO `tab_network_name` VALUES ('20111208115536304230', '泉州铁通临江营业厅', '5109720', '20111208112131224905');
INSERT INTO `tab_network_name` VALUES ('20111208115539477796', '泉州中闽百汇', '5109877', '20111208112131224905');
INSERT INTO `tab_network_name` VALUES ('20111208115540240170', '聚鑫营业厅', '5100160', '20111208112131224905');
INSERT INTO `tab_network_name` VALUES ('20111208115541758249', '惠安张坂营业厅', '5052333', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208115542297859', '惠安新街口营业厅', '5051106', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208115543787520', '惠安小乍营业厅', '5052999', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208115544151465', '惠安涂寨营业厅', '5051222', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208115545766711', '惠安山霞营业厅', '5051333', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208115547347484', '惠安螺城营业厅', '5051888', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208115549608447', '惠安净峰营业厅', '5052888', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208115550176456', '惠安黄塘营业厅', '5051777', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208115551440381', '惠安东园营业厅', '5052111', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208115552205727', '惠安东桥营业厅', '5052777', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208115553968241', '惠安东岭营业厅', '5052666', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208115554636400', '惠安崇武营业厅', '5056555', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208115555226081', '惠安崇武五峰营业厅', '5051444', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208115556392838', '惠安城南营业厅', '5052555', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208115557481108', '惠安百崎营业厅', '5052444', '20110928151457171078');
INSERT INTO `tab_network_name` VALUES ('20111208115558597775', '石狮华丽专营店', '5034915', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208115559187456', '煌兴专营店', '5034512', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208115600902862', '石狮顺捷专营店', '5035103', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208115601571022', '石狮鑫威专营店', '5034513', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208115602960542', '石狮嘉铭专营店', '5035001', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208115603923226', '石狮中恒专营店', '5034539', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208115604365649', '石狮祥芝鸿信指定专营店', '5035048', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208115605403838', '金鑫专营店', '5034531', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208115607884591', '石狮湖滨闽南理工专营店（分店）', '5034529', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208115608474272', '彭田利发专营店', '5034530', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208115609866755', '隆信专营店', '5035020', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208115610682182', '石狮加多宝集团', '5032990', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208115611673264', '石狮宝盖新东专营店', '5035167', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208115612840012', '石狮祥盛专营店', '5034525', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208115617959216', '石狮永兴专营店', '5034504', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208115619821740', '石狮诚达专营店', '5034523', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208115620439829', '石狮裕德专营店', '5034535', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208115621126687', '石狮金曾社区服务站', '5035097', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208115622594686', '石狮蚶江锦亭浩瀚专营店', '5034549', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142508547575', '石狮迪信通营业厅', '5034400', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142510181381', '石狮华丽营业厅', '5034515', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142512693525', '石狮三荣营业厅', '5034524', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142513154647', '石狮佳盛专营店', '5034553', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142514315449', '石狮明兴专营店', '5034541', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142516701997', '石狮祥芝三联专营店', '5034508', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142519323922', '石狮协盛协封集团', '5031111', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142520211881', '石狮客户服务室', '5031001', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142521601392', '石狮湖滨闽南理工专营店', '5034527', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142522413845', '石狮永兴专营店', '5034504', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142524100704', '石狮亿达专营店', '5034522', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142525314569', '石狮宏兴专营店', '5034518', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142526704070', '石狮立兴专营店', '5034440', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142527870827', '石狮松青专营店', '5034545', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142528761769', '石狮蚶江营业厅', '5031016', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142529947206', '石狮振兴路营业厅', '5031011', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142530565295', '石狮永宁营业厅', '5031019', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142531327668', '石狮石永路营业厅', '5031010', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142533262735', '石狮锦尚营业厅', '5031017', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142534623847', '石狮金林营业厅', '5031021', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142535113378', '石狮鸿山营业厅', '5031014', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142537875882', '石狮动感地带品牌店', '5031020', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142538892400', '石狮大堡营业厅', '5031018', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142539751950', '石狮北环路营业厅', '5031013', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142541112923', '石狮宝盖营业厅', '5031015', '20110928151357192049');
INSERT INTO `tab_network_name` VALUES ('20111208142542452364', '南安九都书仪指定专营店', '5042112', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142543942035', '美林伟达专营店', '5042160', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142544384468', '官桥千里传音专营店', '5043625', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142545874139', '官桥信源二店', '5042177', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142546335251', '金淘和立专营店', '5042154', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142547502009', '官桥远信专营店', '5043957', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142548894492', '石井通捷专营店', '5042123', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142549804123', '官桥盛记专营店', '5042104', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142551563524', '南安码头扬英专营店', '5042117', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142552680192', '南安梅山远盛专营店', '5042119', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142553668301', '南安金淘宏捷专营店', '5042114', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142554982316', '康美DIY专营店(闽科店)', '5042132', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142555920345', '南安康美庆发专营店', '5042113', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142557986943', '南安石井迅达专营店', '5042124', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142559517636', '石井院东专营店(三乡店)', '5042182', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142559740409', '南安官桥信源专营店', '5042107', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142602180722', '南安水头东南营业厅', '5041913', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142602810832', '南安水头全通专营店', '5042131', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142604159913', '南安柳城工会营业厅', '5041964', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142606775171', '南安霞美和立专营店', '5042162', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142607139116', '南安客户服务中心', '5040002', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142608927065', '省新通信专营店', '5042138', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142609613922', '南安石井荣盛营业厅', '5042003', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142610153533', '溪美威信专营店(新华店)', '5042140', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142611173024', '南安溪美和立专营店(长安店)', '5042027', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142612161133', '南安洪赖耀颖专营店', '5042044', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142614120844', '南安溪美远东专营店', '5042025', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142615287592', '南安新华营业厅', '5041960', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142616275701', '南安霞美营业厅', '5041958', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142617489556', '南安水头厦盛营业厅', '5041961', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142618556154', '水头华远专营店(自建他营)', '5042158', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142619741600', '南安石井营业厅', '5041914', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142620632532', '南安诗山营业厅', '5041943', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142622846388', '南安省新营业厅', '5041959', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142623207350', '南安梅山营业厅', '5041933', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142624421205', '码头华远专营店', '5042155', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208142626857833', '南安罗东营业厅', '5041935', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208143659569798', '南安仑仓营业厅', '5041953', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208143701332163', '南安柳城成功营业厅', '5041963', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208143702370352', '乐峰华远专营店', '5042153', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208143704829443', '南安洪濑营业厅', '5041923', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208143705817552', '南安官桥营业厅', '5041956', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208143706131417', '南安丰州营业厅', '5041957', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208143707492529', '南安大盈营业厅', '5041602', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208143708530718', '长安营业厅（主营厅）', '5042002', '20110928151505183228');
INSERT INTO `tab_network_name` VALUES ('20111208143709324463', '安溪湖头哲友指定专营店', '5062036', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143711538329', '安溪剑斗恒兴指定专营店', '5062042', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143905363408', '安溪联谊润家指定专营店', '5064806', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143906978664', '安溪官桥鑫祥专营店', '5062056', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143908489867', '安溪龙涓隆诚专营店', '5062045', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143909703712', '安溪邮政储蓄', '5067737', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143910723203', '特产城营业厅', '5064505', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143911460141', '安溪讯通通讯店', '5062001', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143912479631', '安溪长坑舒心指定专营店', '5062008', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143913615008', '安溪官桥福星通讯', '5062020', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143914204688', '安溪官桥三志通讯', '5062023', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143915948503', '安溪迅联专营店', '5062005', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143916858134', '安溪联动专营店', '5062015', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143917476223', '安溪世纪风专营店', '5062016', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143918238596', '安溪远佳专营店', '5062006', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143920951031', '安溪鸿立专营店', '5062075', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143921863634', '安溪城关新正专营店', '5062002', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143922578900', '安溪城关和讯专营店', '5062048', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143923344247', '安溪湖头精英专营店', '5062007', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143924733767', '安溪亿龙营业厅', '5061003', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143925724849', '安溪新安营业厅', '5061002', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143926393019', '安溪西坪营业厅', '5061006', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143927384091', '安溪尚卿新祥专营店', '5062066', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143928870798', '安溪蓬莱小黑专营店', '5062069', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143929106175', '安溪金谷讯通专营店', '5062067', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143930674324', '安溪剑斗营业厅', '5061007', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143931536857', '安溪虎邱营业厅', '5061005', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143932753675', '安溪湖头营业厅', '5064502', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143933870353', '安溪官桥营业厅', '5061004', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143934312776', '安溪感德营业厅', '5061008', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143935451116', '安溪天纶集团', '5067738', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143936840636', '安溪长坑营业厅', '5064503', '20110928151448311689');
INSERT INTO `tab_network_name` VALUES ('20111208143937107394', '桂洋亚星专营店', '5074075', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208143938597065', '下洋玖龙专营店', '5074121', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208143939136665', '永春坑仔口新旺专营店', '5074071', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208143940576266', '永春玉斗新福专营店', '5047099', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208143942238480', '永春下洋宏达专营店', '5074086', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208144516166102', '永春达埔恒远专营店(自建他店)', '5074134', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208144517878545', '永春蓬壶恒远专营店', '5074069', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208144518546704', '永春桃城新威专营店', '5074695', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208144519136385', '永春桃城东海专营店', '5074054', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208144520851791', '永春苏坑轩逸专营店', '5074722', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208144521165666', '永春客户服务中心室', '5071030', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208144522508070', '东平营业厅', '5071024', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208144523446119', '永春桃城桃园专营店(城东主店)', '5074057', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208150145851966', '永春五里街永恒专营店', '5074098', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208150149467946', '永春吾峰艺成专营店(心机)', '5074062', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208150150534544', '岵山营业厅', '5071015', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208150151249800', '一都营业厅', '5071016', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208150152463665', '下洋营业厅', '5071027', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208150154900284', '五里街营业厅', '5071020', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208150155587142', '蓬壶营业厅', '5071018', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208150156302418', '坑仔口营业厅', '5071029', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208150157641858', '湖洋营业厅', '5071023', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208150158952880', '湖美营业厅(主营业厅)', '5071025', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208150334692346', '达埔营业厅', '5071019', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208150335533208', '美湖营业厅', '5071013', '20110928151436403099');
INSERT INTO `tab_network_name` VALUES ('20111208150336220066', '德化恒亿专营店', '5081895', '20110928151534663477');
INSERT INTO `tab_network_name` VALUES ('20111208150337885402', '捷星专营店', '5080067', '20110928151534663477');
INSERT INTO `tab_network_name` VALUES ('20111208150338475083', '德化水口营业厅', '5081884', '20110928151534663477');
INSERT INTO `tab_network_name` VALUES ('20111208150506856982', '德化浔阳营业厅', '5080012', '20110928151534663477');
INSERT INTO `tab_network_name` VALUES ('20111208150507170857', '德化永恒电讯店', '5081897', '20110928151534663477');
INSERT INTO `tab_network_name` VALUES ('20111208150508209047', '德化上涌营业厅', '5081882', '20110928151534663477');
INSERT INTO `tab_network_name` VALUES ('20111208150510197157', '德化三班营业厅', '5081885', '20110928151534663477');
INSERT INTO `tab_network_name` VALUES ('20111208150511313824', '德化南门营业厅', '5081886', '20110928151534663477');
INSERT INTO `tab_network_name` VALUES ('20111208150512979161', '德化凤池营业厅', '5081888', '20110928151534663477');
INSERT INTO `tab_network_name` VALUES ('20111208150513242945', '德化东大路营业厅', '5080003', '20110928151534663477');
INSERT INTO `tab_network_name` VALUES ('20111208150514105479', '德化百德营业厅', '5081881', '20110928151534663477');
INSERT INTO `tab_network_name` VALUES ('20111208150736424750', '泉港宏发专营店', '5094020', '20110928151523232545');
INSERT INTO `tab_network_name` VALUES ('20111208150738679767', '泉港区山腰百汇专营店', '5094043', '20110928151523232545');
INSERT INTO `tab_network_name` VALUES ('20111208150739843542', '泉港山腰速达专营店', '5094025', '20110928151523232545');
INSERT INTO `tab_network_name` VALUES ('20111208150741524455', '泉港中乔专营店', '5094021', '20110928151523232545');
INSERT INTO `tab_network_name` VALUES ('20111208150742591053', '泉港华友专营店', '5094037', '20110928151523232545');
INSERT INTO `tab_network_name` VALUES ('20111208150743854987', '泉港南港专营店', '5094032', '20110928151523232545');
INSERT INTO `tab_network_name` VALUES ('20111208150744795999', '泉港生活区营业厅', '5092007', '20110928151523232545');
INSERT INTO `tab_network_name` VALUES ('20111208150746834180', '泉港前黄速达指定专营店', '5090108', '20110928151523232545');
INSERT INTO `tab_network_name` VALUES ('20111208150747872379', '泉港前黄营业厅', '5092014', '20110928151523232545');
INSERT INTO `tab_network_name` VALUES ('20111208150748587645', '泉港南埔营业厅', '5092012', '20110928151523232545');
INSERT INTO `tab_network_name` VALUES ('20111208150749945774', '泉港海天营业厅', '5092005', '20110928151523232545');
INSERT INTO `tab_network_name` VALUES ('20111208150751209560', '泉港峰尾营业厅', '5092008', '20110928151523232545');
INSERT INTO `tab_network_name` VALUES ('20111208150752821853', '泉港本部营业厅', '5092006', '20110928151523232545');
INSERT INTO `tab_network_name` VALUES ('20111208151029245754', '福景花园社区服务站', '5022117', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151031905146', '晋江青阳迪信通专营店', '5024546', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151032219001', '晋江内坑中天专营店', '5024590', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151033533016', '青阳隆福专营店', '5026285', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151034875429', '晋江东石侨元专营店', '5026335', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151035788023', '晋江内坑恒仁专营店', '5026935', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151036280526', '晋江九亿专营店', '5026725', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151037118415', '彬豪艾派店', '5029280', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151038256754', '永和铭丰专营店', '5026695', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151039423502', '东石创辉通信', '5026290', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151040913173', '老朋友专营店', '5024535', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151041405676', '全球通机场VIP贵宾厅', '5021001', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151042895347', '罗山彬豪专营店', '5029010', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151043563506', '交警大队全球通贵宾厅', '5023360', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151044200284', '晋江磁灶永成专营店', '5026920', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151045367032', '晋江青阳铭炀专营店', '5026845', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151046681047', '安海鸿盛永祥店', '5024533', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151047669156', '晋江恒泰专营店', '5026135', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151048462900', '晋江安海鸿盛专营店罗山灵源分店2', '5029205', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151050448058', '晋江安海九洲专营店', '5024233', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151051404785', '晋江青阳世通移动专营店', '5024539', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151052521453', '晋江金井环球专营店', '5026370', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151054286800', '金井圳佳特约代理点', '5027213', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151055673348', '晋江百佳专营店', '5026515', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151057799806', '晋江新塘鸿博专营店', '5026810', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151058593551', '晋江安平博天专营店', '5026815', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151059506155', '晋江铭耀专营店', '5026776', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151100321571', '晋江双沟辉扬指定专营店', '5026165', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151101312663', '深沪专营', '5026655', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151102128080', '深沪蓝天专营店', '5026655', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151103567670', '晋江东石张厝恒昌专营店', '5530048', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151105778553', '晋江陈埭远大专营店', '5026175', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151106543909', '晋江西滨工业区服务站', '5027360', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151107707674', '晋江西滨鸿滨专营店', '5026195', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151108422940', '晋江中信专营店', '5026190', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151109912611', '晋江富隆专营店', '5026510', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151110950800', '远传专营店', '5026530', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151111264665', '晋江青阳塘岸移动营业厅', '5026570', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151112657159', '晋江青阳王厝社区', '5027365', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151113149652', '青阳盛鑫', '5026140', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151114140744', '晋江青阳宝龙社区', '5027348', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151115404679', '晋江池店鑫远专营店', '5026250', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151117167044', '晋江磁灶聪海专营店', '5026270', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151118158126', '晋江恒源专营店', '5026580', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151119673232', '晋江龙湖捷龙专营店', '5026575', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151120984264', '晋江英林环球专营店', '5026710', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151121476767', '晋江兴信移动专营店', '5026520', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151122640542', '晋江客户服务室', '5023700', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151123857370', '晋江东石鸿基专营店', '5024523', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151124848452', '晋江新滨新中天专营店', '5026170', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208151125839544', '晋江青阳时尚专营店', '5026605', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152518223471', '晋江东石鸿基专营店', '5024523', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152520710170', '晋江新滨新中天专营店', '5026170', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152521328269', '晋江青阳时尚专营店', '5026605', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152522190782', '晋江磁灶中天专营店', '5026300', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152523730533', '晋江龙湖永顺专营店', '5026500', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152524668562', '晋江池店科兴专营店', '5024538', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152525932497', '晋江青阳龙锋专营店', '5026590', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152526823439', '晋江深沪佳友专营店', '5024542', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152527265852', '晋江陈埭远近专营店', '5026155', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152529451300', '晋江洋埭专营厅', '5026205', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152530665155', '晋江池店阳光专营店', '5026255', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152531781822', '晋江清阳天力专营厅', '5026615', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152532195847', '晋江永和春雷专营店', '5024513', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152618925883', '晋江邮政局', '5025000', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152619866895', '晋江新鸿达专营店', '5026565', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152619998427', '晋江泉安专营店', '5024540', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152620585135', '晋江英林隆天专营店', '5026715', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152621676377', '晋江紫帽科兴专营店', '5026260', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152621974655', '晋江磁灶恒华专营店', '5026275', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152622213005', '陈埭老地方专营店', '5024543', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152622394616', '晋江英林利达专营店', '5024521', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152622567309', '晋江青阳中北专营店', '5024548', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152623385698', '晋江龙湖恒达专营店', '5026380', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152623702675', '晋江陈埭铭记专营厅', '5024524', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152623705648', '晋江金井鑫源专营店', '5024522', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152624145099', '晋江金井金鹰通信', '5024520', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152624699703', '晋江安海鸿盛专营店', '5026645', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152624872396', '晋江洋棣新创先专营店', '5026395', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152625317792', '晋江永和营业厅', '5021003', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152625634769', '晋江洋埭营业厅', '5027001', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152626353009', '晋江新大街营业厅', '5021900', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152626631797', '晋江新塘营业厅', '5021300', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152627397144', '晋江阳光时代营业厅', '5021101', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152627742529', '晋江双沟营业厅', '5022170', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152627892759', '晋江深沪营业厅', '5027100', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152628658096', '晋江罗山营业厅', '5021000', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152628915213', '晋江内坑营业厅', '5022450', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152630220160', '晋江龙湖营业厅', '5021700', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152630863033', '晋江金井营业厅', '5021002', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152631578309', '晋江磁灶营业厅', '5021500', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208152631712803', '晋江东石营业厅', '5021100', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208153221488879', '晋江陈埭中行营业厅', '5027300', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208153221720551', '晋江池店营业厅', '5021600', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208153222755777', '晋江罗山彬豪专营店', '5024536', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208153223643736', '晋江安海成功东路营业厅', '5022800', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208153223749832', '晋江百灵专营店', '5026505', '20110928151422200821');
INSERT INTO `tab_network_name` VALUES ('20111208153342816518', '东海镇泉州新市政府营业厅', '5010540', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153343387490', '999田安专营店', '5019733', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153343431634', '洛江双阳999专营店', '5019821', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153344202916', '东海华宇专营店', '5019807', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153344698532', '泉州银拓专营店', '5019902', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153345109574', '虹景营业厅', '5010434', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153345419744', '洛江红星美凯龙', '5010396', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153346580556', '中邮普泰南益专营', '5018885', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153347126093', '泉州理工学院', '5015101', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153347314511', '泉州华侨大学专营店', '5019613', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153348562720', '泉州东海营业厅', '5019055', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153349246605', '泉州八一信通专营店', '5019615', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153349478297', '全球通手机俱乐部', '5011005', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153401315296', '泉州客户服务中心', '5014150', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153401858009', '泉州铁通丰泽营业厅', '5019723', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153402256298', '泉州999电讯专营店', '5018054', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153403394647', '泉州仰大动感店', '5019708', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153644231385', '泉州信息学院动感店', '5014451', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153644451185', '泉州马甲营业厅', '5014450', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153645295010', '泉州洛江营业厅', '5019834', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153727941078', '泉州黎明大学动感店', '5019834', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153728433571', '泉州华大动感店', '5019834', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153729377556', '泉州动感地带师院共青团店', '5019852', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153730644454', '泉州刺桐营业厅', '5011002', '20111208111816536402');
INSERT INTO `tab_network_name` VALUES ('20111208153733344867', '泉州北峰营业厅', '5011007', '20111208111816536402');

-- ----------------------------
-- Table structure for `tab_number_group`
-- ----------------------------
DROP TABLE IF EXISTS `tab_number_group`;
CREATE TABLE `tab_number_group` (
  `ID` varchar(20) NOT NULL,
  `OrderNo` varchar(10) default NULL,
  `TelNumber` varchar(15) default NULL,
  `UserName` varchar(50) default NULL,
  `PassWord` varchar(20) default NULL,
  `ClieId` varchar(20) default NULL,
  `LineId` varchar(20) default NULL,
  `DelFlag` smallint(6) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_number_group
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_other_info`
-- ----------------------------
DROP TABLE IF EXISTS `tab_other_info`;
CREATE TABLE `tab_other_info` (
  `ID` varchar(20) NOT NULL,
  `InfoFileName` varchar(30) default NULL,
  `InfoFileType` varchar(20) default NULL,
  `InfoFileSize` int(11) default NULL,
  `LocalDatetime` datetime default NULL,
  `LineId` varchar(20) default NULL,
  `LoadFileName` varchar(100) default NULL,
  `DelFlag` smallint(6) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_other_info
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_outlets_type`
-- ----------------------------
DROP TABLE IF EXISTS `tab_outlets_type`;
CREATE TABLE `tab_outlets_type` (
  `ID` varchar(20) NOT NULL,
  `OutletsTypeName` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_outlets_type
-- ----------------------------
INSERT INTO `tab_outlets_type` VALUES ('20111103205701151712', '动感地带');
INSERT INTO `tab_outlets_type` VALUES ('20111103205716460556', '专营店');
INSERT INTO `tab_outlets_type` VALUES ('20111103205725641276', '营业厅');

-- ----------------------------
-- Table structure for `tab_pay_standard`
-- ----------------------------
DROP TABLE IF EXISTS `tab_pay_standard`;
CREATE TABLE `tab_pay_standard` (
  `ID` varchar(255) NOT NULL,
  `StartDate` datetime default NULL,
  `EndDate` datetime default NULL,
  `Pay` decimal(6,2) default NULL,
  `AccessWayId` varchar(20) default NULL,
  `MaintainTypeId` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_pay_standard
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_personnel`
-- ----------------------------
DROP TABLE IF EXISTS `tab_personnel`;
CREATE TABLE `tab_personnel` (
  `ID` varchar(20) NOT NULL,
  `TwoDimensionalCode` varchar(20) default NULL,
  `FullName` varchar(20) default NULL,
  `Sex` smallint(6) default NULL,
  `BirthDate` datetime default NULL,
  `IDCardNumber` varchar(18) default NULL,
  `MobileNumber` varchar(15) default NULL,
  `CertificationNo` text,
  `Certificate1` varchar(20) default NULL,
  `CertificateAwardDate1` datetime default NULL,
  `Certificate2` varchar(20) default NULL,
  `CertificateAwardDate2` datetime default NULL,
  `Certificate3` varchar(20) default NULL,
  `CertificateAwardDate3` datetime default NULL,
  `Certificate4` varchar(20) default NULL,
  `CertificateAwardDate4` datetime default NULL,
  `Certificate5` varchar(20) default NULL,
  `CertificateAwardDate5` datetime default NULL,
  `WorkDate` datetime default NULL,
  `EntryDate` datetime default NULL,
  `IsBackbone` smallint(6) default NULL,
  `ItemName` varchar(50) default NULL,
  `ItemPropertyId` varchar(20) default NULL,
  `MaintainSpecialtyId` varchar(20) default NULL,
  `StagnationId` varchar(20) default NULL,
  `DiplomaId` varchar(20) default NULL,
  `QualificationTypeId` varchar(20) default NULL,
  `PostId` varchar(20) default NULL,
  `DutyId` varchar(20) default NULL,
  `Remark` varchar(200) default NULL,
  `CityId` varchar(20) default NULL,
  `DistrictId` varchar(20) default NULL,
  `CompanyId` varchar(20) default NULL,
  `GridId` varchar(20) default NULL,
  `PostsId` varchar(20) default NULL,
  `CreateUserId` varchar(20) default NULL,
  `CreateDate` datetime default NULL,
  `DelFlag` smallint(6) default NULL,
  `IsSelfMaintain` smallint(6) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_personnel
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_port_type`
-- ----------------------------
DROP TABLE IF EXISTS `tab_port_type`;
CREATE TABLE `tab_port_type` (
  `ID` varchar(20) NOT NULL,
  `PortTypeName` varchar(10) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_port_type
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_posts`
-- ----------------------------
DROP TABLE IF EXISTS `tab_posts`;
CREATE TABLE `tab_posts` (
  `ID` varchar(20) NOT NULL,
  `PostsName` varchar(30) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_posts
-- ----------------------------
INSERT INTO `tab_posts` VALUES ('20111128093100218488', '代维');
INSERT INTO `tab_posts` VALUES ('20111128093127191638', '协维');
INSERT INTO `tab_posts` VALUES ('20111128093135387212', '办公报表');

-- ----------------------------
-- Table structure for `tab_qualification_type`
-- ----------------------------
DROP TABLE IF EXISTS `tab_qualification_type`;
CREATE TABLE `tab_qualification_type` (
  `ID` varchar(20) NOT NULL,
  `QualificationTypeName` varchar(30) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_qualification_type
-- ----------------------------
INSERT INTO `tab_qualification_type` VALUES ('20111107110919224403', '基站综合维护');
INSERT INTO `tab_qualification_type` VALUES ('20111107110935424179', '网优测试');
INSERT INTO `tab_qualification_type` VALUES ('20111107110952486478', '数据综合维护');
INSERT INTO `tab_qualification_type` VALUES ('20111107111006522468', '线路综合维护');

-- ----------------------------
-- Table structure for `tab_qywyy_sms_send`
-- ----------------------------
DROP TABLE IF EXISTS `tab_qywyy_sms_send`;
CREATE TABLE `tab_qywyy_sms_send` (
  `ID` varchar(255) NOT NULL,
  `SEND_QYDM` varchar(12) NOT NULL,
  `MSISDN` decimal(19,5) NOT NULL,
  `SEND_MSG` text NOT NULL,
  `SEND_DATE` datetime NOT NULL,
  `SRC` varchar(20) NOT NULL,
  `SEND_MK` decimal(19,5) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_qywyy_sms_send
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_register`
-- ----------------------------
DROP TABLE IF EXISTS `tab_register`;
CREATE TABLE `tab_register` (
  `ID` varchar(20) NOT NULL,
  `PBOSSJobNo` varchar(20) default NULL,
  `Maintainer` varchar(20) default NULL,
  `CommunityName` varchar(20) default NULL,
  `CommunityCode` varchar(20) default NULL,
  `Address` varchar(50) default NULL,
  `UserName` varchar(20) default NULL,
  `PayNumber` varchar(20) default NULL,
  `CreateDatetime` datetime default NULL,
  `CommunityTypeId` varchar(20) default NULL,
  `MaintainTypeId` varchar(20) default NULL,
  `BuildWayId` varchar(20) default NULL,
  `AccessWayId` varchar(20) default NULL,
  `CompanyId` varchar(20) default NULL,
  `CityId` varchar(20) default NULL,
  `DistrictId` varchar(20) default NULL,
  `SaleDepartmentId` varchar(20) default NULL,
  `Remark` varchar(20) default NULL,
  `Num` int(11) default NULL,
  `MaintainerId` varchar(20) default NULL,
  `MaintainDatetime` datetime default NULL,
  `DelFlag` smallint(6) default NULL,
  `CreateUserId` varchar(20) default NULL,
  `CreateDate` datetime default NULL,
  `HandleTime` varchar(8) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_register
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_register_detail`
-- ----------------------------
DROP TABLE IF EXISTS `tab_register_detail`;
CREATE TABLE `tab_register_detail` (
  `ID` varchar(20) NOT NULL,
  `Num` int(11) default NULL,
  `RegisterId` varchar(20) default NULL,
  `UnitId` varchar(20) default NULL,
  `SuppliesTypeId` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_register_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_relation`
-- ----------------------------
DROP TABLE IF EXISTS `tab_relation`;
CREATE TABLE `tab_relation` (
  `ID` varchar(20) NOT NULL,
  `ControllerName` varchar(50) NOT NULL,
  `MId` varchar(20) NOT NULL,
  `CId` varchar(20) NOT NULL,
  `RelationName` varchar(50) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_relation
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_repair`
-- ----------------------------
DROP TABLE IF EXISTS `tab_repair`;
CREATE TABLE `tab_repair` (
  `ID` varchar(20) NOT NULL,
  `EquipName` varchar(30) default NULL,
  `IsSendOrigi` smallint(6) default NULL,
  `RepairFactoryId` varchar(20) default NULL,
  `SeqNo` varchar(20) default NULL,
  `EquipModelId` varchar(20) default NULL,
  `FactoryId` varchar(20) default NULL,
  `RepertotyId` varchar(20) default NULL,
  `Reason` varchar(200) default NULL,
  `Num` int(11) default NULL,
  `ClieId` varchar(20) default NULL,
  `ClieNo` varchar(20) default NULL,
  `OCompanyId` varchar(20) default NULL,
  `ODistrictId` varchar(20) default NULL,
  `RepairDate` datetime default NULL,
  `IsFix` smallint(6) default NULL,
  `ScheduleDate` datetime default NULL,
  `FactDate` datetime default NULL,
  `HandleNote` varchar(200) default NULL,
  `State` smallint(6) default NULL,
  `Operator` varchar(20) default NULL,
  `ReOperator` varchar(20) default NULL,
  `IsStorage` smallint(6) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_repair
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_repertoty`
-- ----------------------------
DROP TABLE IF EXISTS `tab_repertoty`;
CREATE TABLE `tab_repertoty` (
  `ID` varchar(255) NOT NULL,
  `Address` varchar(50) default NULL,
  `Tel` varchar(15) default NULL,
  `Remark` varchar(200) default NULL,
  `CompanyId` varchar(20) default NULL,
  `DistrictId` varchar(20) default NULL,
  `MUserId` text,
  `RepertotyNo` varchar(20) default NULL,
  `RepertotyName` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_repertoty
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_sale_department`
-- ----------------------------
DROP TABLE IF EXISTS `tab_sale_department`;
CREATE TABLE `tab_sale_department` (
  `ID` varchar(20) NOT NULL,
  `SaleDepartmentName` varchar(20) NOT NULL,
  `CompanyId` varchar(20) NOT NULL,
  `DistrictId` varchar(20) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_sale_department
-- ----------------------------
INSERT INTO `tab_sale_department` VALUES ('20111215001909542991', '前黄片区营销中心', '20111215000623166279', '20110928151523232545');
INSERT INTO `tab_sale_department` VALUES ('20111215001922145337', '生活区片区营销中心', '20111215000623166279', '20110928151523232545');
INSERT INTO `tab_sale_department` VALUES ('20111215001931730431', '南埔片区营销中心', '20111215000623166279', '20110928151523232545');
INSERT INTO `tab_sale_department` VALUES ('20111215001944790204', '浔中营业部', '20111215000623166279', '20110928151534663477');
INSERT INTO `tab_sale_department` VALUES ('20111215001951314505', '龙浔营业部', '20111215000623166279', '20110928151534663477');
INSERT INTO `tab_sale_department` VALUES ('20111215002006855021', '下洋营业部', '20110928115155247752', '20110928151436403099');
INSERT INTO `tab_sale_department` VALUES ('20111215002013209602', '蓬壶营业部', '20110928115155247752', '20110928151436403099');
INSERT INTO `tab_sale_department` VALUES ('20111215002020994134', '五里街营业部', '20110928114938767045', '20110928151436403099');
INSERT INTO `tab_sale_department` VALUES ('20111215002027201468', '城关营业部', '20110928114938767045', '20110928151436403099');
INSERT INTO `tab_sale_department` VALUES ('20111215002039505664', '尚卿营业部', '20111215000623166279', '20110928151448311689');
INSERT INTO `tab_sale_department` VALUES ('20111215002055131346', '蓬莱营业部', '20110928115155247752', '20110928151448311689');
INSERT INTO `tab_sale_department` VALUES ('20111215002107466923', '官桥营业部', '20111215000623166279', '20110928151448311689');
INSERT INTO `tab_sale_department` VALUES ('20111215002116879325', '虎邱营业部', '20111215000623166279', '20110928151448311689');
INSERT INTO `tab_sale_department` VALUES ('20111215002130598348', '西坪营业部', '20111215000623166279', '20110928151448311689');
INSERT INTO `tab_sale_department` VALUES ('20111215002137758705', '龙涓营业部', '20111215000623166279', '20110928151448311689');
INSERT INTO `tab_sale_department` VALUES ('20111215002145878773', '剑斗营业部', '20110928115155247752', '20110928151448311689');
INSERT INTO `tab_sale_department` VALUES ('20111215002152358940', '长坑营业部', '20110928115155247752', '20110928151448311689');
INSERT INTO `tab_sale_department` VALUES ('20111215002208787425', '湖头营业部', '20110928115155247752', '20110928151448311689');
INSERT INTO `tab_sale_department` VALUES ('20111215002219184823', '感德营业部', '20110928115155247752', '20110928151448311689');
INSERT INTO `tab_sale_department` VALUES ('20111215002228192850', '城关营业部', '20111215000623166279', '20110928151448311689');
INSERT INTO `tab_sale_department` VALUES ('20111215002243643006', '东园营业部', '20110928114938767045', '20110928151457171078');
INSERT INTO `tab_sale_department` VALUES ('20111215002251251732', '洛阳营业部', '20110928114938767045', '20110928151457171078');
INSERT INTO `tab_sale_department` VALUES ('20111215002302549121', '涂寨营业部', '20110928114938767045', '20110928151457171078');
INSERT INTO `tab_sale_department` VALUES ('20111215002324711135', '净峰营业部', '20110928115155247752', '20110928151457171078');
INSERT INTO `tab_sale_department` VALUES ('20111215002330422982', '崇武营业部', '20110928115155247752', '20110928151457171078');
INSERT INTO `tab_sale_department` VALUES ('20111215002336369495', '东岭营业部', '20110928115155247752', '20110928151457171078');
INSERT INTO `tab_sale_department` VALUES ('20111215002342385577', '城南营业部', '20110928115155247752', '20110928151457171078');
INSERT INTO `tab_sale_department` VALUES ('20111215002351948209', '螺城营业部', '20110928115155247752', '20110928151457171078');
INSERT INTO `tab_sale_department` VALUES ('20111215002405638813', '石井片区营销中心', '20110928114938767045', '20110928151505183228');
INSERT INTO `tab_sale_department` VALUES ('20111215002413784317', '水头片区营销中心', '20110928114938767045', '20110928151505183228');
INSERT INTO `tab_sale_department` VALUES ('20111215002422102364', '仑仓片区营销中心', '20110928114938767045', '20110928151505183228');
INSERT INTO `tab_sale_department` VALUES ('20111215002435805000', '官桥片区营销中心', '20110928115155247752', '20110928151505183228');
INSERT INTO `tab_sale_department` VALUES ('20111215002442812164', '省新片区营销中心', '20110928115155247752', '20110928151505183228');
INSERT INTO `tab_sale_department` VALUES ('20111215002454445099', '诗山片区营销中心', '20110928115155247752', '20110928151505183228');
INSERT INTO `tab_sale_department` VALUES ('20111215002502226518', '罗东片区营销中心', '20110928115155247752', '20110928151505183228');
INSERT INTO `tab_sale_department` VALUES ('20111215002512225627', '梅山片区营销中心', '20110928115155247752', '20110928151505183228');
INSERT INTO `tab_sale_department` VALUES ('20111215002519107206', '洪濑片区营销中心', '20110928115155247752', '20110928151505183228');
INSERT INTO `tab_sale_department` VALUES ('20111215002525100815', '丰霞片区营销中心', '20110928115155247752', '20110928151505183228');
INSERT INTO `tab_sale_department` VALUES ('20111215002533202185', '柳城片区营销中心', '20111215000623166279', '20110928151505183228');
INSERT INTO `tab_sale_department` VALUES ('20111215002538932000', '溪美片区营销中心', '20111215000623166279', '20110928151505183228');
INSERT INTO `tab_sale_department` VALUES ('20111215002552115117', '永宁营业部', '20110928114938767045', '20110928151357192049');
INSERT INTO `tab_sale_department` VALUES ('20111215002557572099', '锦尚营业部', '20110928114938767045', '20110928151357192049');
INSERT INTO `tab_sale_department` VALUES ('20111215002604905159', '祥芝营业部', '20111215000623166279', '20110928151357192049');
INSERT INTO `tab_sale_department` VALUES ('20111215002609227486', '蚶江营业部', '20111215000623166279', '20110928151357192049');
INSERT INTO `tab_sale_department` VALUES ('20111215002614408652', '宝盖营业部', '20111215000623166279', '20110928151357192049');
INSERT INTO `tab_sale_department` VALUES ('20111215002619586845', '凤里营业部', '20111215000623166279', '20110928151357192049');
INSERT INTO `tab_sale_department` VALUES ('20111215002626468414', '湖滨营业部', '20111215000623166279', '20110928151357192049');
INSERT INTO `tab_sale_department` VALUES ('20111215002644568902', '永和营业部', '20111215000623166279', '20110928151422200821');
INSERT INTO `tab_sale_department` VALUES ('20111215002649577376', '英林营业部', '20111215000623166279', '20110928151422200821');
INSERT INTO `tab_sale_department` VALUES ('20111215002653463226', '新塘营业部', '20111215000623166279', '20110928151422200821');
INSERT INTO `tab_sale_department` VALUES ('20111215002656151757', '深沪营业部', '20111215000623166279', '20110928151422200821');
INSERT INTO `tab_sale_department` VALUES ('20111215002700517497', '青阳营业部', '20111215000623166279', '20110928151422200821');
INSERT INTO `tab_sale_department` VALUES ('20111215002705375730', '内坑营业部', '20111215000623166279', '20110928151422200821');
INSERT INTO `tab_sale_department` VALUES ('20111215002710553933', '罗山营业部', '20111215000623166279', '20110928151422200821');
INSERT INTO `tab_sale_department` VALUES ('20111215002717231438', '龙湖营业部', '20111215000623166279', '20110928151422200821');
INSERT INTO `tab_sale_department` VALUES ('20111215002721365496', '金井营业部', '20111215000623166279', '20110928151422200821');
INSERT INTO `tab_sale_department` VALUES ('20111215002726772398', '东石营业部', '20111215000623166279', '20110928151422200821');
INSERT INTO `tab_sale_department` VALUES ('20111215002731452003', '磁灶营业部', '20111215000623166279', '20110928151422200821');
INSERT INTO `tab_sale_department` VALUES ('20111215002737210967', '池店营业部', '20111215000623166279', '20110928151422200821');
INSERT INTO `tab_sale_department` VALUES ('20111215002742940782', '陈埭营业部', '20111215000623166279', '20110928151422200821');
INSERT INTO `tab_sale_department` VALUES ('20111215002747300447', '安海营业部', '20111215000623166279', '20110928151422200821');
INSERT INTO `tab_sale_department` VALUES ('20111215002758864742', '鲤城营销中心', '20111215000623166279', '20111208112131224905');
INSERT INTO `tab_sale_department` VALUES ('20111215002806263459', '鲤中营销中心', '20111215000623166279', '20111208112131224905');
INSERT INTO `tab_sale_department` VALUES ('20111215002810494694', '清濛营销中心', '20111215000623166279', '20111208112131224905');
INSERT INTO `tab_sale_department` VALUES ('20111215002819220099', '洛江营销中心', '20111215000623166279', '20111208111816536402');
INSERT INTO `tab_sale_department` VALUES ('20111215002823824337', '东海营销中心', '20111215000623166279', '20111208111816536402');
INSERT INTO `tab_sale_department` VALUES ('20111215002828183991', '马甲营销中心', '20111215000623166279', '20111208111816536402');
INSERT INTO `tab_sale_department` VALUES ('20111215002833954977', '丰泽营销中心', '20111215000623166279', '20111208111816536402');
INSERT INTO `tab_sale_department` VALUES ('20111215002841833584', '北峰营销中心', '20111215000623166279', '20111208111816536402');

-- ----------------------------
-- Table structure for `tab_scalegrade`
-- ----------------------------
DROP TABLE IF EXISTS `tab_scalegrade`;
CREATE TABLE `tab_scalegrade` (
  `ID` varchar(255) NOT NULL,
  `TYPE` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_scalegrade
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_selfhelp_equip_model`
-- ----------------------------
DROP TABLE IF EXISTS `tab_selfhelp_equip_model`;
CREATE TABLE `tab_selfhelp_equip_model` (
  `ID` varchar(20) NOT NULL,
  `ModelName` varchar(20) default NULL,
  `SelfHelpFactoryId` varchar(20) default NULL,
  `SelfHelpEquipTypeId` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_selfhelp_equip_model
-- ----------------------------
INSERT INTO `tab_selfhelp_equip_model` VALUES ('20111130093745390035', 'K8K-E', '20111129224448206734', '20111128094806442948');
INSERT INTO `tab_selfhelp_equip_model` VALUES ('20111130093815524859', 'K800-S-II', '20111129224448206734', '20111128094806442948');
INSERT INTO `tab_selfhelp_equip_model` VALUES ('20111130093835190375', 'K8K-W', '20111129224448206734', '20111128094806442948');
INSERT INTO `tab_selfhelp_equip_model` VALUES ('20111130093857205122', 'K800-E', '20111129224448206734', '20111128094806442948');
INSERT INTO `tab_selfhelp_equip_model` VALUES ('20111130093919592861', 'K8K-S', '20111129224448206734', '20111128094806442948');
INSERT INTO `tab_selfhelp_equip_model` VALUES ('20111130093953218871', 'K8000-W-FJ', '20111129224448206734', '20111128094806442948');
INSERT INTO `tab_selfhelp_equip_model` VALUES ('20111130094013527260', 'K800-W', '20111129224448206734', '20111128094806442948');
INSERT INTO `tab_selfhelp_equip_model` VALUES ('20111130094202503025', 'ZT2070-H', '20111129224507846963', '20111128094806442948');
INSERT INTO `tab_selfhelp_equip_model` VALUES ('20111130094213176249', 'ZT2070-W', '20111129224507846963', '20111128094806442948');
INSERT INTO `tab_selfhelp_equip_model` VALUES ('20111130094255853688', 'K8000-W', '20111129224448206734', '20111128094806442948');
INSERT INTO `tab_selfhelp_equip_model` VALUES ('20111130094305582927', 'K8000-E', '20111129224448206734', '20111128094806442948');
INSERT INTO `tab_selfhelp_equip_model` VALUES ('20111130094516523359', 'K8000-S-II', '20111129224448206734', '20111128094806442948');
INSERT INTO `tab_selfhelp_equip_model` VALUES ('20111130094606145051', 'k8k-e', '20111129224448206734', '20111128094806442948');
INSERT INTO `tab_selfhelp_equip_model` VALUES ('20111130094639331600', 'K8000-EA', '20111129224448206734', '20111128094806442948');
INSERT INTO `tab_selfhelp_equip_model` VALUES ('20111130094732600812', 'K8000-S', '20111129224448206734', '20111128094806442948');
INSERT INTO `tab_selfhelp_equip_model` VALUES ('20111130094806581117', 'K8000-EB', '20111129224448206734', '20111128094806442948');
INSERT INTO `tab_selfhelp_equip_model` VALUES ('20111130094938949027', 'KEK-W', '20111129224448206734', '20111128094806442948');
INSERT INTO `tab_selfhelp_equip_model` VALUES ('20111130095035310995', '大堂机', '20111129224435161836', '20111128094747878375');

-- ----------------------------
-- Table structure for `tab_selfhelp_equip_type`
-- ----------------------------
DROP TABLE IF EXISTS `tab_selfhelp_equip_type`;
CREATE TABLE `tab_selfhelp_equip_type` (
  `ID` varchar(20) NOT NULL,
  `SelfHelpEquipTypeName` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_selfhelp_equip_type
-- ----------------------------
INSERT INTO `tab_selfhelp_equip_type` VALUES ('20111128094747878375', '大堂机');
INSERT INTO `tab_selfhelp_equip_type` VALUES ('20111128094806442948', '穿墙机');

-- ----------------------------
-- Table structure for `tab_selfhelp_factory`
-- ----------------------------
DROP TABLE IF EXISTS `tab_selfhelp_factory`;
CREATE TABLE `tab_selfhelp_factory` (
  `ID` varchar(20) NOT NULL,
  `Abbrevia` varchar(20) default NULL,
  `ChinaName` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_selfhelp_factory
-- ----------------------------
INSERT INTO `tab_selfhelp_factory` VALUES ('20111129224435161836', '新大陆', '新大陆');
INSERT INTO `tab_selfhelp_factory` VALUES ('20111129224448206734', '诺恩', '诺恩');
INSERT INTO `tab_selfhelp_factory` VALUES ('20111129224507846963', '证通', '证通');

-- ----------------------------
-- Table structure for `tab_self_help_equip`
-- ----------------------------
DROP TABLE IF EXISTS `tab_self_help_equip`;
CREATE TABLE `tab_self_help_equip` (
  `ID` varchar(20) NOT NULL,
  `TermiId` varchar(8) default NULL,
  `UseNetName` varchar(50) default NULL,
  `NetAddress` varchar(100) default NULL,
  `StartDatetime` datetime default NULL,
  `NetType` varchar(20) default NULL,
  `TerimIP` varchar(20) default NULL,
  `SubnetMask` varchar(20) default NULL,
  `MacAddress` varchar(30) default NULL,
  `IsOverInsuran` smallint(6) default NULL,
  `Life` int(11) default NULL,
  `Remark` varchar(200) default NULL,
  `EquipModelId` varchar(20) default NULL,
  `FactoryId` varchar(20) default NULL,
  `EquipTypeId` varchar(20) default NULL,
  `DistrictId` varchar(20) default NULL,
  `BuyDatetime` datetime default NULL,
  `State` smallint(6) default NULL,
  `FaultNum` int(11) default NULL,
  `CreateUserId` varchar(20) default NULL,
  `CreateDate` datetime default NULL,
  `DelFlag` smallint(6) default NULL,
  `UseNetNo` varchar(7) default NULL,
  `EquipModelName` varchar(100) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_self_help_equip
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_signal_model`
-- ----------------------------
DROP TABLE IF EXISTS `tab_signal_model`;
CREATE TABLE `tab_signal_model` (
  `ID` varchar(20) NOT NULL,
  `SignalModelName` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_signal_model
-- ----------------------------
INSERT INTO `tab_signal_model` VALUES ('20111107111224804868', 'PRI');
INSERT INTO `tab_signal_model` VALUES ('20111107111230722962', 'NO.7');
INSERT INTO `tab_signal_model` VALUES ('20111107111236839195', 'NO.1');
INSERT INTO `tab_signal_model` VALUES ('20111107111245125860', 'MFC');
INSERT INTO `tab_signal_model` VALUES ('20111107111250576906', 'DTMF');

-- ----------------------------
-- Table structure for `tab_stagnation`
-- ----------------------------
DROP TABLE IF EXISTS `tab_stagnation`;
CREATE TABLE `tab_stagnation` (
  `ID` varchar(20) NOT NULL,
  `StagnationName` varchar(50) default NULL,
  `CompanyId` varchar(20) default NULL,
  `DistrictId` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_stagnation
-- ----------------------------
INSERT INTO `tab_stagnation` VALUES ('20111107150519194123', '泉州市区泉邮驻点1', '20110928114938767045', '20110928151342773351');
INSERT INTO `tab_stagnation` VALUES ('20111107150705230975', '泉州市区泉邮驻点2', '20110928114938767045', '20110928151342773351');
INSERT INTO `tab_stagnation` VALUES ('20111107150727615752', '泉州市区泉邮驻点6', '20110928114938767045', '20110928151342773351');
INSERT INTO `tab_stagnation` VALUES ('20111107150745449334', '泉州永春移动自维', '20110928115247719586', '20110928151436403099');
INSERT INTO `tab_stagnation` VALUES ('20111107150804443727', '石狮泉邮驻点3', '20110928114938767045', '20110928151357192049');
INSERT INTO `tab_stagnation` VALUES ('20111107150818175483', '永春泉邮驻点3', '20110928114938767045', '20110928151436403099');
INSERT INTO `tab_stagnation` VALUES ('20111107150830839939', '永春泉邮驻点4', '20110928114938767045', '20110928151436403099');
INSERT INTO `tab_stagnation` VALUES ('20111107150842896738', '惠安泉邮驻点2', '20110928114938767045', '20110928151457171078');
INSERT INTO `tab_stagnation` VALUES ('20111107150855777862', '泉港泉邮驻点1', '20110928114938767045', '20110928151523232545');
INSERT INTO `tab_stagnation` VALUES ('20111107150912517239', '安溪城关泉邮驻点1', '20110928114938767045', '20110928151448311689');
INSERT INTO `tab_stagnation` VALUES ('20111107150925542657', '安溪虎邱泉邮驻点4', '20110928114938767045', '20110928151448311689');
INSERT INTO `tab_stagnation` VALUES ('20111107150947155992', '晋江灵水泉邮驻点1', '20110928114938767045', '20110928151422200821');
INSERT INTO `tab_stagnation` VALUES ('20111107150958447445', '晋江灵水泉邮驻点4', '20110928114938767045', '20110928151422200821');
INSERT INTO `tab_stagnation` VALUES ('20111107151011824185', '晋江龙湖泉邮驻点5', '20110928114938767045', '20110928151422200821');
INSERT INTO `tab_stagnation` VALUES ('20111107151023714237', '晋江青阳泉邮驻点2', '20110928114938767045', '20110928151422200821');
INSERT INTO `tab_stagnation` VALUES ('20111107151032402314', '晋江青阳泉邮驻点6', '20110928114938767045', '20110928151422200821');
INSERT INTO `tab_stagnation` VALUES ('20111107151042222784', '南安水头泉邮驻点6', '20110928114938767045', '20110928151505183228');
INSERT INTO `tab_stagnation` VALUES ('20111107151055282557', '南安溪美泉邮驻点4', '20110928114938767045', '20110928151505183228');
INSERT INTO `tab_stagnation` VALUES ('20111107151107392399', '泉州石狮移动自维', '20110928115247719586', '20110928151357192049');
INSERT INTO `tab_stagnation` VALUES ('20111107151122296858', '泉州泉港移动自维', '20110928115247719586', '20110928151523232545');
INSERT INTO `tab_stagnation` VALUES ('20111107151136483079', '泉州南安移动自维', '20110928115247719586', '20110928151505183228');
INSERT INTO `tab_stagnation` VALUES ('20111107151153501244', '泉州晋江移动自维', '20110928115247719586', '20110928151422200821');
INSERT INTO `tab_stagnation` VALUES ('20111107151215688673', '泉州惠安移动自维', '20110928115247719586', '20110928151457171078');
INSERT INTO `tab_stagnation` VALUES ('20111107151227948755', '泉州市区移动自维', '20110928115247719586', '20110928151342773351');
INSERT INTO `tab_stagnation` VALUES ('20111107151239465794', '泉州德化移动自维', '20110928115247719586', '20110928151534663477');
INSERT INTO `tab_stagnation` VALUES ('20111107151253930803', '泉州安溪移动自维', '20110928115247719586', '20110928151448311689');
INSERT INTO `tab_stagnation` VALUES ('20111107151305410535', '安溪国脉长坑驻点2', '20110928115155247752', '20110928151448311689');
INSERT INTO `tab_stagnation` VALUES ('20111107151316133839', '安溪国脉城关驻点3', '20110928115155247752', '20110928151448311689');
INSERT INTO `tab_stagnation` VALUES ('20111107151326735441', '安溪国脉剑斗驻点5', '20110928115155247752', '20110928151448311689');
INSERT INTO `tab_stagnation` VALUES ('20111107151341394675', '永春国脉坑仔口驻点1', '20110928115155247752', '20110928151436403099');
INSERT INTO `tab_stagnation` VALUES ('20111107151351923754', '永春国脉蓬壶驻点2', '20110928115155247752', '20110928151436403099');
INSERT INTO `tab_stagnation` VALUES ('20111107151409654213', '石狮国脉驻点1', '20110928115155247752', '20110928151357192049');
INSERT INTO `tab_stagnation` VALUES ('20111107151418715283', '石狮国脉驻点2', '20110928115155247752', '20110928151357192049');
INSERT INTO `tab_stagnation` VALUES ('20111107151429335464', '南安国脉洪濑驻点1', '20110928115155247752', '20110928151505183228');
INSERT INTO `tab_stagnation` VALUES ('20111107151443430443', '南安国脉洪濑驻点3', '20110928115155247752', '20110928151505183228');
INSERT INTO `tab_stagnation` VALUES ('20111107151454273387', '南安国脉洪濑驻点5', '20110928115155247752', '20110928151505183228');
INSERT INTO `tab_stagnation` VALUES ('20111107151505818183', '南安国脉金淘驻点2', '20110928115155247752', '20110928151505183228');
INSERT INTO `tab_stagnation` VALUES ('20111107151517429446', '晋江国脉青阳驻点3', '20110928115155247752', '20110928151422200821');
INSERT INTO `tab_stagnation` VALUES ('20111107151529987796', '德化国脉城关驻点1', '20110928115155247752', '20110928151534663477');
INSERT INTO `tab_stagnation` VALUES ('20111107151538820168', '德化国脉城关驻点2', '20110928115155247752', '20110928151534663477');
INSERT INTO `tab_stagnation` VALUES ('20111107151548179377', '德化国脉上涌驻点3', '20110928115155247752', '20110928151534663477');
INSERT INTO `tab_stagnation` VALUES ('20111107151601154715', '泉州市区国脉驻点3', '20110928115155247752', '20110928151342773351');
INSERT INTO `tab_stagnation` VALUES ('20111107151612323545', '泉州市区国脉驻点4', '20110928115155247752', '20110928151342773351');
INSERT INTO `tab_stagnation` VALUES ('20111107151622351063', '泉州市区国脉驻点5', '20110928115155247752', '20110928151342773351');
INSERT INTO `tab_stagnation` VALUES ('20111107151632255977', '泉州市区国脉驻点7', '20110928115155247752', '20110928151342773351');
INSERT INTO `tab_stagnation` VALUES ('20111107151643427770', '惠安国脉驻点1', '20110928115155247752', '20110928151457171078');
INSERT INTO `tab_stagnation` VALUES ('20111107151654775249', '惠安国脉驻点3', '20110928115155247752', '20110928151457171078');

-- ----------------------------
-- Table structure for `tab_stock`
-- ----------------------------
DROP TABLE IF EXISTS `tab_stock`;
CREATE TABLE `tab_stock` (
  `ID` varchar(20) NOT NULL,
  `EquipName` varchar(30) default NULL,
  `TotalNum` int(11) default NULL,
  `BadNum` int(11) default NULL,
  `ScrapNum` int(11) default NULL,
  `FrozenNum` int(11) default NULL,
  `UnitId` varchar(20) default NULL,
  `EquipModelId` varchar(20) default NULL,
  `FactoryId` varchar(20) default NULL,
  `Remark` varchar(200) default NULL,
  `CompanyId` varchar(20) default NULL,
  `DistrictId` varchar(20) default NULL,
  `RepertotyId` varchar(20) default NULL,
  `BarCode` varchar(50) default NULL,
  `StockTypeId` varchar(20) default NULL,
  `State` varchar(2) default NULL,
  `Num` int(11) default NULL,
  `UnitName` varchar(20) default NULL,
  `EquipModelName` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_stock
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_stock_equip_use`
-- ----------------------------
DROP TABLE IF EXISTS `tab_stock_equip_use`;
CREATE TABLE `tab_stock_equip_use` (
  `ID` varchar(20) NOT NULL,
  `StockEquipUse` varchar(20) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_stock_equip_use
-- ----------------------------
INSERT INTO `tab_stock_equip_use` VALUES ('20111215001328126144', '小区宽带备件');
INSERT INTO `tab_stock_equip_use` VALUES ('20111215001340784644', '高校宽带备件');
INSERT INTO `tab_stock_equip_use` VALUES ('20111215001349601280', '专线网管改造');
INSERT INTO `tab_stock_equip_use` VALUES ('20111215001359252041', '专线备件');
INSERT INTO `tab_stock_equip_use` VALUES ('20111215001411947878', 'WLAN备件');

-- ----------------------------
-- Table structure for `tab_stock_type`
-- ----------------------------
DROP TABLE IF EXISTS `tab_stock_type`;
CREATE TABLE `tab_stock_type` (
  `ID` varchar(20) NOT NULL,
  `StockTypeName` varchar(20) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_stock_type
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_subject_content`
-- ----------------------------
DROP TABLE IF EXISTS `tab_subject_content`;
CREATE TABLE `tab_subject_content` (
  `ID` varchar(255) NOT NULL,
  `CONTENT` text,
  `CREATORID` text,
  `CREATETIME` datetime default NULL,
  `SCALEGRADEID` text,
  `TYPEID` text,
  `COURSETYPEID` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_subject_content
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_subject_type`
-- ----------------------------
DROP TABLE IF EXISTS `tab_subject_type`;
CREATE TABLE `tab_subject_type` (
  `ID` varchar(255) NOT NULL,
  `TYPE` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_subject_type
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_supervision_back`
-- ----------------------------
DROP TABLE IF EXISTS `tab_supervision_back`;
CREATE TABLE `tab_supervision_back` (
  `ID` varchar(255) NOT NULL,
  `ReturnReason` text,
  `ReturnTime` datetime default NULL,
  `ApprovalStaff` text,
  `OrdersId` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_supervision_back
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_supervision_orders`
-- ----------------------------
DROP TABLE IF EXISTS `tab_supervision_orders`;
CREATE TABLE `tab_supervision_orders` (
  `ID` varchar(255) NOT NULL,
  `SendingObject` text,
  `OrdersTime` datetime default NULL,
  `BackTime` datetime default NULL,
  `ReBackTime` datetime default NULL,
  `OrdersOvertimeNum` int(11) default NULL,
  `BackOvertimeNum` int(11) default NULL,
  `ReturnNum` int(11) default NULL,
  `AuditStaff` text,
  `ReviewedPhone` text,
  `Status` text,
  `ApprovalStatus` text,
  `SendId` text,
  `BackAttachment` text,
  `SendUnitName` text,
  `BackAttachmentName` text,
  `NewBackAttachmentName` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_supervision_orders
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_supervision_send`
-- ----------------------------
DROP TABLE IF EXISTS `tab_supervision_send`;
CREATE TABLE `tab_supervision_send` (
  `ID` varchar(255) NOT NULL,
  `DispatchTime` datetime default NULL,
  `LimitOrders` datetime default NULL,
  `LimitBack` datetime default NULL,
  `SendTitle` text,
  `SendContent` text,
  `SendAttachment` text,
  `SendPerson` text,
  `Phone` text,
  `AttachmentName` text,
  `NewAttachmentName` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_supervision_send
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_supplier`
-- ----------------------------
DROP TABLE IF EXISTS `tab_supplier`;
CREATE TABLE `tab_supplier` (
  `ID` varchar(20) NOT NULL,
  `SupplierName` varchar(50) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_supplier
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_supplies_stock`
-- ----------------------------
DROP TABLE IF EXISTS `tab_supplies_stock`;
CREATE TABLE `tab_supplies_stock` (
  `ID` varchar(255) NOT NULL,
  `Num` int(11) default NULL,
  `State` varchar(2) default NULL,
  `SuppliesTypeId` varchar(20) default NULL,
  `DistrictId` varchar(20) default NULL,
  `CompanyId` varchar(20) default NULL,
  `SaleDepartmentId` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_supplies_stock
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_supplies_type`
-- ----------------------------
DROP TABLE IF EXISTS `tab_supplies_type`;
CREATE TABLE `tab_supplies_type` (
  `ID` varchar(20) NOT NULL,
  `SuppliesTypeName` varchar(30) NOT NULL,
  `UnitName` varchar(30) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_supplies_type
-- ----------------------------
INSERT INTO `tab_supplies_type` VALUES ('20111215000705859444', '五类线', null);
INSERT INTO `tab_supplies_type` VALUES ('20111215000710536086', 'RJ45水晶头', null);
INSERT INTO `tab_supplies_type` VALUES ('20111215000716435481', '超五类模块', null);
INSERT INTO `tab_supplies_type` VALUES ('20111215000722551713', '双口面板', null);
INSERT INTO `tab_supplies_type` VALUES ('20111215000743502835', '2芯室内皮线光缆', null);
INSERT INTO `tab_supplies_type` VALUES ('20111215000751246937', '2芯室外皮线光缆', null);
INSERT INTO `tab_supplies_type` VALUES ('20111215000758908856', '预埋式快速连接头', null);

-- ----------------------------
-- Table structure for `tab_test_content`
-- ----------------------------
DROP TABLE IF EXISTS `tab_test_content`;
CREATE TABLE `tab_test_content` (
  `ID` varchar(255) NOT NULL,
  `NAME` text,
  `CREATORID` text,
  `CREATETIME` datetime default NULL,
  `POINTS` text,
  `TYPEID` text,
  `STATE` int(11) default NULL,
  `ISDELETE` tinyint(1) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_test_content
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_test_exercise`
-- ----------------------------
DROP TABLE IF EXISTS `tab_test_exercise`;
CREATE TABLE `tab_test_exercise` (
  `ID` varchar(255) NOT NULL,
  `TESTID` text,
  `CREATORID` text,
  `CREATETIME` datetime default NULL,
  `PASS` text,
  `ISDELETE` tinyint(1) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_test_exercise
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_test_mock`
-- ----------------------------
DROP TABLE IF EXISTS `tab_test_mock`;
CREATE TABLE `tab_test_mock` (
  `ID` varchar(255) NOT NULL,
  `TESTID` text,
  `TIMELENGTH` text,
  `CREATORID` text,
  `CREATETIME` datetime default NULL,
  `PASS` text,
  `ISDELETE` tinyint(1) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_test_mock
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_test_set`
-- ----------------------------
DROP TABLE IF EXISTS `tab_test_set`;
CREATE TABLE `tab_test_set` (
  `ID` varchar(255) NOT NULL,
  `TESTID` text,
  `BEGINTIME` datetime default NULL,
  `ENDTIME` datetime default NULL,
  `TIMELENGTH` text,
  `CREATETIME` datetime default NULL,
  `PASS` text,
  `ISDELETE` tinyint(1) default NULL,
  `CREATORID` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_test_set
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_test_subject`
-- ----------------------------
DROP TABLE IF EXISTS `tab_test_subject`;
CREATE TABLE `tab_test_subject` (
  `ID` varchar(255) NOT NULL,
  `TESTSUBJECTTYPEID` text,
  `SUBJECTID` text,
  `SCORE` text,
  `ORDERNO` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_test_subject
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_test_subject_type`
-- ----------------------------
DROP TABLE IF EXISTS `tab_test_subject_type`;
CREATE TABLE `tab_test_subject_type` (
  `ID` varchar(255) NOT NULL,
  `TESTID` text,
  `SUBJECTTYPEID` text,
  `TYPENAME` text,
  `EXPLANATION` text,
  `ORDERNO` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_test_subject_type
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_test_type`
-- ----------------------------
DROP TABLE IF EXISTS `tab_test_type`;
CREATE TABLE `tab_test_type` (
  `ID` varchar(255) NOT NULL,
  `TYPE` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_test_type
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_unit`
-- ----------------------------
DROP TABLE IF EXISTS `tab_unit`;
CREATE TABLE `tab_unit` (
  `ID` varchar(20) NOT NULL,
  `UnitName` varchar(4) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_unit
-- ----------------------------
INSERT INTO `tab_unit` VALUES ('20111117170346462778', '块');
INSERT INTO `tab_unit` VALUES ('20111117170447501518', '张');
INSERT INTO `tab_unit` VALUES ('20111117170452861313', '箱');
INSERT INTO `tab_unit` VALUES ('20111117170456342798', '台');
INSERT INTO `tab_unit` VALUES ('20111213162837872909', '米');
INSERT INTO `tab_unit` VALUES ('20111213163018232980', '粒');
INSERT INTO `tab_unit` VALUES ('20111213163035216791', '个');
INSERT INTO `tab_unit` VALUES ('20111213163040197657', '条');
INSERT INTO `tab_unit` VALUES ('20111213163055979644', '公斤');
INSERT INTO `tab_unit` VALUES ('20111213163101948619', '盒');
INSERT INTO `tab_unit` VALUES ('20111213163116956192', '盘');
INSERT INTO `tab_unit` VALUES ('20111213163121554355', '包');
INSERT INTO `tab_unit` VALUES ('20111213163141715486', '套');
INSERT INTO `tab_unit` VALUES ('20111213163147160437', '付');

-- ----------------------------
-- Table structure for `tab_use`
-- ----------------------------
DROP TABLE IF EXISTS `tab_use`;
CREATE TABLE `tab_use` (
  `ID` varchar(20) NOT NULL,
  `UseName` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_use
-- ----------------------------
INSERT INTO `tab_use` VALUES ('20111107112552203325', '数据综合维护');
INSERT INTO `tab_use` VALUES ('20111107112602236788', '网优测试');
INSERT INTO `tab_use` VALUES ('20111107112614176910', '基站综合维护');
INSERT INTO `tab_use` VALUES ('20111107112624803167', '线路综合维护');
INSERT INTO `tab_use` VALUES ('20111107112635696181', '全业务网格维护');
INSERT INTO `tab_use` VALUES ('20111107112645228093', '铁塔微波维护');

-- ----------------------------
-- Table structure for `tab_vehicle`
-- ----------------------------
DROP TABLE IF EXISTS `tab_vehicle`;
CREATE TABLE `tab_vehicle` (
  `ID` varchar(255) NOT NULL,
  `TwoDimensionalCode` text,
  `LicensePlateNumber` text,
  `StartDatetime` datetime default NULL,
  `UseMileage` int(11) default NULL,
  `DrivingLicenseNo` text,
  `EngineNo` text,
  `AnnualInspectTime` datetime default NULL,
  `ManuFacturer` text,
  `ModelSpecification` text,
  `Displacement` text,
  `Remark` text,
  `StagnationId` text,
  `MaintainSpecialtyId` text,
  `VehicleNatureId` text,
  `CompanyId` text,
  `CityId` text,
  `DistrictId` text,
  `GridId` text,
  `UseId` varchar(20) default NULL,
  `CreateUserId` varchar(20) default NULL,
  `CreateDate` datetime default NULL,
  `DelFlag` smallint(6) default NULL,
  `FullName` varchar(30) default NULL,
  `LinkTel` varchar(15) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_vehicle
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_vehicle_nature`
-- ----------------------------
DROP TABLE IF EXISTS `tab_vehicle_nature`;
CREATE TABLE `tab_vehicle_nature` (
  `ID` varchar(20) NOT NULL,
  `VehicleNatureName` varchar(8) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_vehicle_nature
-- ----------------------------
INSERT INTO `tab_vehicle_nature` VALUES ('20111107111335907652', '自有');
INSERT INTO `tab_vehicle_nature` VALUES ('20111107111339411590', '租用');

-- ----------------------------
-- Table structure for `tab_workload`
-- ----------------------------
DROP TABLE IF EXISTS `tab_workload`;
CREATE TABLE `tab_workload` (
  `ID` varchar(255) NOT NULL,
  `WorkYear` smallint(6) NOT NULL,
  `WorkMonth` smallint(6) NOT NULL,
  `Examination` int(11) default NULL,
  `MonthlyAmount` int(11) default NULL,
  `MonthlyRemuneration` decimal(7,2) default NULL,
  `YearAmount` int(11) default NULL,
  `YearRemuneration` decimal(7,2) default NULL,
  `FixedAmount` int(11) default NULL,
  `FixedYearAmount` decimal(7,2) default NULL,
  `Reissue` decimal(7,2) default NULL,
  `ReissueNote` varchar(200) default NULL,
  `SaleAmount` int(11) default NULL,
  `ADSLInstallations` int(11) default NULL,
  `ADSLRelocation` int(11) default NULL,
  `FTTHInstallations` int(11) default NULL,
  `FTTHRelocation` int(11) default NULL,
  `CompanyId` varchar(20) default NULL,
  `CityId` varchar(20) default NULL,
  `DistrictId` varchar(20) default NULL,
  `SaleDepartmentId` varchar(20) default NULL,
  `CommunityManagerId` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_workload
-- ----------------------------

-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `Id` varchar(40) NOT NULL,
  `Description` varchar(255) default NULL,
  `Difficulty` int(11) default NULL,
  `Reward` int(11) default NULL,
  `XP` int(11) default NULL,
  `GangId` varchar(40) default NULL,
  PRIMARY KEY  (`Id`),
  KEY `GangId` (`GangId`),
  CONSTRAINT `FK1B248E972A236F90` FOREIGN KEY (`GangId`) REFERENCES `gang` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_resource`
-- ----------------------------
DROP TABLE IF EXISTS `tb_resource`;
CREATE TABLE `tb_resource` (
  `Id` varchar(16) NOT NULL,
  `resName` varchar(50) NOT NULL,
  `objectName` varchar(50) default NULL,
  `isVerify` smallint(6) NOT NULL,
  `resNote` varchar(150) default NULL,
  `orderCode` int(11) default NULL,
  `createTime` datetime default NULL,
  `funId` varchar(16) NOT NULL,
  PRIMARY KEY  (`Id`),
  KEY `funId` (`funId`),
  CONSTRAINT `FKD1720B5D4946D30B` FOREIGN KEY (`funId`) REFERENCES `funinfo` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_resource
-- ----------------------------

-- ----------------------------
-- Table structure for `td_sequences`
-- ----------------------------
DROP TABLE IF EXISTS `td_sequences`;
CREATE TABLE `td_sequences` (
  `seq_name` varchar(200) NOT NULL default '' COMMENT '表名则为主键',
  `value` int(11) default '0',
  PRIMARY KEY  (`seq_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_sequences
-- ----------------------------
INSERT INTO `td_sequences` VALUES ('SYSTEM_MENU', '15');

-- ----------------------------
-- Table structure for `t_adjdr`
-- ----------------------------
DROP TABLE IF EXISTS `t_adjdr`;
CREATE TABLE `t_adjdr` (
  `ID` varchar(40) NOT NULL,
  `AdjDRNo` varchar(20) default NULL,
  `AdjDRDate` datetime default NULL,
  `Operator` varchar(20) default NULL,
  `AdjPriMember` varchar(20) default NULL,
  `Remark` varchar(100) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_adjdr
-- ----------------------------

-- ----------------------------
-- Table structure for `t_adjgoodsdr`
-- ----------------------------
DROP TABLE IF EXISTS `t_adjgoodsdr`;
CREATE TABLE `t_adjgoodsdr` (
  `ID` varchar(40) NOT NULL,
  `MemberPrice` decimal(19,5) default NULL,
  `PreferPrice` decimal(19,5) default NULL,
  `DR` decimal(19,5) default NULL,
  `Remark` varchar(100) default NULL,
  `ADRId` varchar(40) NOT NULL,
  `GId` varchar(40) NOT NULL,
  PRIMARY KEY  (`ID`),
  KEY `ADRId` (`ADRId`),
  KEY `GId` (`GId`),
  CONSTRAINT `FK205C08298BAD5489` FOREIGN KEY (`ADRId`) REFERENCES `t_adjdr` (`ID`),
  CONSTRAINT `FK205C0829E19D6501` FOREIGN KEY (`GId`) REFERENCES `t_goods` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_adjgoodsdr
-- ----------------------------

-- ----------------------------
-- Table structure for `t_adjgoodsprice`
-- ----------------------------
DROP TABLE IF EXISTS `t_adjgoodsprice`;
CREATE TABLE `t_adjgoodsprice` (
  `ID` varchar(40) NOT NULL,
  `OldRP` decimal(19,5) default NULL,
  `NewRP` decimal(19,5) default NULL,
  `Remark` varchar(100) default NULL,
  `APriceId` varchar(40) NOT NULL,
  `GId` varchar(40) NOT NULL,
  PRIMARY KEY  (`ID`),
  KEY `APriceId` (`APriceId`),
  KEY `GId` (`GId`),
  CONSTRAINT `FKF6D8314B5B377EE0` FOREIGN KEY (`APriceId`) REFERENCES `t_adjprice` (`ID`),
  CONSTRAINT `FKF6D8314BE19D6501` FOREIGN KEY (`GId`) REFERENCES `t_goods` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_adjgoodsprice
-- ----------------------------

-- ----------------------------
-- Table structure for `t_adjprice`
-- ----------------------------
DROP TABLE IF EXISTS `t_adjprice`;
CREATE TABLE `t_adjprice` (
  `ID` varchar(40) NOT NULL,
  `AdjPriceNo` varchar(20) default NULL,
  `AdjPriceDate` datetime default NULL,
  `Operator` varchar(20) default NULL,
  `AdjPriMember` varchar(20) default NULL,
  `Remark` varchar(100) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_adjprice
-- ----------------------------

-- ----------------------------
-- Table structure for `t_goods`
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `ID` varchar(40) NOT NULL,
  `BarCode` varchar(20) default NULL,
  `IssueDate` datetime default NULL,
  `GoodsNo` varchar(20) default NULL,
  `GoodsName` varchar(100) default NULL,
  `Standard` decimal(19,5) default NULL,
  `Settlement` decimal(7,2) default NULL,
  `RetailPrice` decimal(7,2) default NULL,
  `MemberPrice` decimal(7,2) default NULL,
  `PreferPrice` decimal(7,2) default NULL,
  `DR` decimal(3,2) default NULL,
  `Remark` varchar(100) default NULL,
  `IsEnable` smallint(6) default NULL,
  `GClsId` varchar(40) NOT NULL,
  `UnitId` varchar(40) NOT NULL,
  PRIMARY KEY  (`ID`),
  KEY `GClsId` (`GClsId`),
  KEY `UnitId` (`UnitId`),
  CONSTRAINT `FK87FFE13F36284F5B` FOREIGN KEY (`UnitId`) REFERENCES `t_unit` (`ID`),
  CONSTRAINT `FK87FFE13FCC2345A0` FOREIGN KEY (`GClsId`) REFERENCES `t_goodsclass` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_goods
-- ----------------------------

-- ----------------------------
-- Table structure for `t_goodsclass`
-- ----------------------------
DROP TABLE IF EXISTS `t_goodsclass`;
CREATE TABLE `t_goodsclass` (
  `ID` varchar(40) NOT NULL,
  `ClsName` varchar(20) NOT NULL,
  `Remark` varchar(100) default NULL,
  `IsEnable` smallint(6) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_goodsclass
-- ----------------------------

-- ----------------------------
-- Table structure for `t_inoutlist`
-- ----------------------------
DROP TABLE IF EXISTS `t_inoutlist`;
CREATE TABLE `t_inoutlist` (
  `ID` varchar(40) NOT NULL,
  `ListNo` varchar(20) NOT NULL,
  `ListDate` datetime NOT NULL,
  `Operator` varchar(20) default NULL,
  `StoreKeeper` varchar(20) default NULL,
  `Assessor` varchar(20) default NULL,
  `SignInMan` varchar(20) default NULL,
  `DeliverFlag` smallint(6) default NULL,
  `PayOff` decimal(19,5) default NULL,
  `Total` int(11) default NULL,
  `State` smallint(6) default NULL,
  `IsAudit` smallint(6) default NULL,
  `Remark` varchar(100) default NULL,
  `listClsId` varchar(40) NOT NULL,
  `ROrgId` varchar(40) NOT NULL,
  `DOrgId` varchar(40) default NULL,
  `SupplierId` varchar(40) default NULL,
  `MClsId` varchar(40) NOT NULL,
  `InOutId` varchar(40) NOT NULL,
  PRIMARY KEY  (`ID`),
  KEY `listClsId` (`listClsId`),
  KEY `SupplierId` (`SupplierId`),
  KEY `MClsId` (`MClsId`),
  KEY `InOutId` (`InOutId`),
  KEY `ROrgId` (`ROrgId`),
  KEY `DOrgId` (`DOrgId`),
  CONSTRAINT `FK293E7B6A32A1A2BD` FOREIGN KEY (`MClsId`) REFERENCES `t_memberclass` (`ID`),
  CONSTRAINT `FK293E7B6A5B004CA8` FOREIGN KEY (`SupplierId`) REFERENCES `t_supplier` (`ID`),
  CONSTRAINT `FK293E7B6A941AEA6C` FOREIGN KEY (`listClsId`) REFERENCES `t_listclass` (`ID`),
  CONSTRAINT `FK293E7B6AC281F2F6` FOREIGN KEY (`InOutId`) REFERENCES `t_listclass` (`ID`),
  CONSTRAINT `FK293E7B6AD862793D` FOREIGN KEY (`DOrgId`) REFERENCES `organize` (`ID`),
  CONSTRAINT `FK293E7B6AD8627F4F` FOREIGN KEY (`ROrgId`) REFERENCES `organize` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_inoutlist
-- ----------------------------

-- ----------------------------
-- Table structure for `t_listclass`
-- ----------------------------
DROP TABLE IF EXISTS `t_listclass`;
CREATE TABLE `t_listclass` (
  `ID` varchar(40) NOT NULL,
  `ClsNameNo` smallint(6) NOT NULL,
  `StyleName` varchar(10) NOT NULL,
  `Remark` varchar(100) default NULL,
  `IsEnable` smallint(6) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_listclass
-- ----------------------------

-- ----------------------------
-- Table structure for `t_listdetail`
-- ----------------------------
DROP TABLE IF EXISTS `t_listdetail`;
CREATE TABLE `t_listdetail` (
  `ID` varchar(40) NOT NULL,
  `Num` int(11) default NULL,
  `SettlePrice` decimal(19,5) default NULL,
  `RetailPrice` decimal(19,5) default NULL,
  `Remark` varchar(100) default NULL,
  `InOutListId` varchar(40) NOT NULL,
  `GId` varchar(40) NOT NULL,
  PRIMARY KEY  (`ID`),
  KEY `InOutListId` (`InOutListId`),
  KEY `GId` (`GId`),
  CONSTRAINT `FKD3167A6316FA83C7` FOREIGN KEY (`InOutListId`) REFERENCES `t_inoutlist` (`ID`),
  CONSTRAINT `FKD3167A63E19D6501` FOREIGN KEY (`GId`) REFERENCES `t_goods` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_listdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `t_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `ID` varchar(40) NOT NULL,
  `CreateLogDate` datetime default NULL,
  `UserName` varchar(20) default NULL,
  `OperaterName` varchar(20) default NULL,
  `ListId` varchar(40) default NULL,
  `Description` smallint(6) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_log
-- ----------------------------

-- ----------------------------
-- Table structure for `t_member`
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member` (
  `ID` varchar(40) NOT NULL,
  `MemberNo` varchar(20) NOT NULL,
  `MemberName` varchar(20) NOT NULL,
  `LinkMan` varchar(20) default NULL,
  `LinkTel` varchar(12) default NULL,
  `LinkAddress` varchar(100) default NULL,
  `Integral` int(11) default NULL,
  `SpendAm` decimal(19,5) default NULL,
  `AdvanceAm` decimal(19,5) default NULL,
  `DebtAm` decimal(19,5) default NULL,
  `IsEnable` smallint(6) default NULL,
  `MClsId` varchar(40) NOT NULL,
  PRIMARY KEY  (`ID`),
  KEY `MClsId` (`MClsId`),
  CONSTRAINT `FKE20655D32A1A2BD` FOREIGN KEY (`MClsId`) REFERENCES `t_memberclass` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_member
-- ----------------------------

-- ----------------------------
-- Table structure for `t_memberclass`
-- ----------------------------
DROP TABLE IF EXISTS `t_memberclass`;
CREATE TABLE `t_memberclass` (
  `ID` varchar(40) NOT NULL,
  `ClsName` varchar(20) NOT NULL,
  `Remark` varchar(100) default NULL,
  `IsEnable` smallint(6) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_memberclass
-- ----------------------------

-- ----------------------------
-- Table structure for `t_orggoods`
-- ----------------------------
DROP TABLE IF EXISTS `t_orggoods`;
CREATE TABLE `t_orggoods` (
  `ID` varchar(40) NOT NULL,
  `Num` int(11) default NULL,
  `EnableNum` int(11) default NULL,
  `NoEnableNum` int(11) default NULL,
  `OrgId` varchar(40) NOT NULL,
  `GId` varchar(40) NOT NULL,
  PRIMARY KEY  (`ID`),
  KEY `GId` (`GId`),
  KEY `OrgId` (`OrgId`),
  CONSTRAINT `FKE100546DD11EC8DE` FOREIGN KEY (`OrgId`) REFERENCES `organize` (`ID`),
  CONSTRAINT `FKE100546DE19D6501` FOREIGN KEY (`GId`) REFERENCES `t_goods` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_orggoods
-- ----------------------------

-- ----------------------------
-- Table structure for `t_sell`
-- ----------------------------
DROP TABLE IF EXISTS `t_sell`;
CREATE TABLE `t_sell` (
  `ID` varchar(40) NOT NULL,
  `SellNo` varchar(20) NOT NULL,
  `SellDate` datetime NOT NULL,
  `Total` int(11) default NULL,
  `TotalAcc` decimal(19,5) default NULL,
  `RealAcc` decimal(19,5) default NULL,
  `Operator` varchar(20) default NULL,
  `Seller` varchar(20) default NULL,
  `SignInMan` varchar(20) default NULL,
  `Remark` varchar(100) default NULL,
  `DrFlag` smallint(6) default NULL,
  `DrAuditor` varchar(20) default NULL,
  `AuditDate` datetime default NULL,
  `listClsId` varchar(40) NOT NULL,
  `SOrgId` varchar(40) NOT NULL,
  `MClsId` varchar(40) NOT NULL,
  `MId` varchar(40) default NULL,
  `AdjDRId` varchar(40) default NULL,
  `SellId` varchar(40) NOT NULL,
  PRIMARY KEY  (`ID`),
  KEY `listClsId` (`listClsId`),
  KEY `MClsId` (`MClsId`),
  KEY `MId` (`MId`),
  KEY `AdjDRId` (`AdjDRId`),
  KEY `SellId` (`SellId`),
  KEY `SOrgId` (`SOrgId`),
  CONSTRAINT `FK13E0B96932A1A2BD` FOREIGN KEY (`MClsId`) REFERENCES `t_memberclass` (`ID`),
  CONSTRAINT `FK13E0B969941AEA6C` FOREIGN KEY (`listClsId`) REFERENCES `t_listclass` (`ID`),
  CONSTRAINT `FK13E0B96997058E79` FOREIGN KEY (`AdjDRId`) REFERENCES `t_adjdr` (`ID`),
  CONSTRAINT `FK13E0B969B93A193E` FOREIGN KEY (`SellId`) REFERENCES `t_listclass` (`ID`),
  CONSTRAINT `FK13E0B969D8627F68` FOREIGN KEY (`SOrgId`) REFERENCES `organize` (`ID`),
  CONSTRAINT `FK13E0B969DC8A15C5` FOREIGN KEY (`MId`) REFERENCES `t_member` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sell
-- ----------------------------

-- ----------------------------
-- Table structure for `t_selldetail`
-- ----------------------------
DROP TABLE IF EXISTS `t_selldetail`;
CREATE TABLE `t_selldetail` (
  `ID` varchar(40) NOT NULL,
  `Num` int(11) default NULL,
  `RetailPrice` decimal(19,5) default NULL,
  `Remark` varchar(100) default NULL,
  `ReNum` int(11) default NULL,
  `SellId` varchar(40) NOT NULL,
  `GId` varchar(40) NOT NULL,
  PRIMARY KEY  (`ID`),
  KEY `SellId` (`SellId`),
  KEY `GId` (`GId`),
  CONSTRAINT `FK7E03B541E19D6501` FOREIGN KEY (`GId`) REFERENCES `t_goods` (`ID`),
  CONSTRAINT `FK7E03B541FFF500B7` FOREIGN KEY (`SellId`) REFERENCES `t_sell` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_selldetail
-- ----------------------------

-- ----------------------------
-- Table structure for `t_supplier`
-- ----------------------------
DROP TABLE IF EXISTS `t_supplier`;
CREATE TABLE `t_supplier` (
  `ID` varchar(40) NOT NULL,
  `SupplierNo` varchar(20) default NULL,
  `SupplierName` varchar(50) default NULL,
  `LinkMan` varchar(20) default NULL,
  `LinkTel` varchar(15) default NULL,
  `Fax` varchar(15) default NULL,
  `Address` varchar(100) default NULL,
  `Bank` varchar(50) default NULL,
  `Account` varchar(20) default NULL,
  `Remark` varchar(100) default NULL,
  `IsEnable` smallint(6) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_supplier
-- ----------------------------

-- ----------------------------
-- Table structure for `t_unit`
-- ----------------------------
DROP TABLE IF EXISTS `t_unit`;
CREATE TABLE `t_unit` (
  `ID` varchar(40) NOT NULL,
  `UnitName` varchar(8) NOT NULL,
  `Remark` varchar(100) default NULL,
  `IsEnable` smallint(6) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_unit
-- ----------------------------

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `ID` varchar(40) NOT NULL,
  `loginName` varchar(50) NOT NULL,
  `loginPwd` varchar(50) NOT NULL,
  `userName` varchar(50) default NULL,
  `userTel` varchar(50) default NULL,
  `loginState` smallint(6) NOT NULL,
  `userSex` varchar(50) NOT NULL,
  `userIdCard` varchar(50) default NULL,
  `userNetGrid` varchar(50) default NULL,
  `userUnit` varchar(50) default NULL,
  `userCounty` varchar(50) NOT NULL,
  `certifiCate` varchar(50) default NULL,
  `registerTime` datetime default NULL,
  `lastLoginTime` datetime default NULL,
  `orderCode` int(11) default NULL,
  `OrgId` varchar(40) NOT NULL,
  PRIMARY KEY  (`ID`),
  KEY `OrgId` (`OrgId`),
  CONSTRAINT `FK8A947DB7D11EC8DE` FOREIGN KEY (`OrgId`) REFERENCES `organize` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', '1', 'C4CA4238A0B923820DCC509A6F75849B', '1', '1', '0', '1', '1', '1', '', '1', '1', '2011-07-17 00:00:00', '2011-07-17 00:00:00', '0', '1');
INSERT INTO `userinfo` VALUES ('2', 'admini', 'C4CA4238A0B923820DCC509A6F75849B', 'admini', '13511111111', '1', '男', '1', '1', '1', '1', '11', '2011-07-17 14:51:59', '2011-07-17 14:52:05', '1', '1');

-- ----------------------------
-- Table structure for `userrole`
-- ----------------------------
DROP TABLE IF EXISTS `userrole`;
CREATE TABLE `userrole` (
  `userId` varchar(40) NOT NULL,
  `roleId` varchar(40) NOT NULL,
  KEY `roleId` (`roleId`),
  KEY `userId` (`userId`),
  CONSTRAINT `FK297C0BDE2A4F9C74` FOREIGN KEY (`roleId`) REFERENCES `roleinfo` (`ID`),
  CONSTRAINT `FK297C0BDEE601554A` FOREIGN KEY (`userId`) REFERENCES `userinfo` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userrole
-- ----------------------------

-- ----------------------------
-- Table structure for `vspro`
-- ----------------------------
DROP TABLE IF EXISTS `vspro`;
CREATE TABLE `vspro` (
  `id` int(11) default NULL,
  `name` varchar(20) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vspro
-- ----------------------------
INSERT INTO `vspro` VALUES ('1', '1');
INSERT INTO `vspro` VALUES ('1', '2');

-- ----------------------------
-- Table structure for `weapon`
-- ----------------------------
DROP TABLE IF EXISTS `weapon`;
CREATE TABLE `weapon` (
  `Id` varchar(40) NOT NULL,
  `Name` varchar(255) default NULL,
  `XpBonus` double default NULL,
  `Price` int(11) default NULL,
  `PlayerId` varchar(40) default NULL,
  PRIMARY KEY  (`Id`),
  KEY `PlayerId` (`PlayerId`),
  CONSTRAINT `FKB654ACA76513C579` FOREIGN KEY (`PlayerId`) REFERENCES `player` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weapon
-- ----------------------------
INSERT INTO `weapon` VALUES ('55e09f6f-a51b-4be1-8b16-655f25fcf2e3', 'Knife', '0.1', '120', 'a9a8616d-1310-42c0-af27-54e4e3ffcdf0');
INSERT INTO `weapon` VALUES ('6769e5ed-96cc-40eb-856a-e0e1c21f3e65', 'Taser', '0.2', '350', 'a9a8616d-1310-42c0-af27-54e4e3ffcdf0');
INSERT INTO `weapon` VALUES ('839ac439-49e1-4bf2-aa2c-f901509efa19', 'Bomb', '0.25', '1500', 'a9a8616d-1310-42c0-af27-54e4e3ffcdf0');
INSERT INTO `weapon` VALUES ('f06a3729-aef7-4d13-8023-c4ebec73fb49', 'Gun', '0.45', '750', 'a9a8616d-1310-42c0-af27-54e4e3ffcdf0');
INSERT INTO `weapon` VALUES ('f59a10e1-59f4-4ff6-b5c5-2e73aae4dad4', 'Club', '0.05', '50', 'a9a8616d-1310-42c0-af27-54e4e3ffcdf0');

-- ----------------------------
-- Table structure for `yd_170data`
-- ----------------------------
DROP TABLE IF EXISTS `yd_170data`;
CREATE TABLE `yd_170data` (
  `ID` varchar(255) NOT NULL,
  `CITYCOUNTRYNO` varchar(50) default NULL,
  `ENSUREGRADE` varchar(50) default NULL,
  `CLIENTGRADE` varchar(50) default NULL,
  `IMPORTLEVEL` varchar(50) default NULL,
  `MANAGERTEL` varchar(50) default NULL,
  `NAME` varchar(50) default NULL,
  `ISFLYOVER` varchar(2) default NULL,
  `INTENDMONEY` varchar(50) default NULL,
  `EXPRESSREMARK` text,
  `OPERATIONTYPE` varchar(50) default NULL,
  `APPLYMAN` varchar(50) default NULL,
  `APPLYTEL` varchar(50) default NULL,
  `APPLYFAX` varchar(50) default NULL,
  `CLIENTTYPE` varchar(50) default NULL,
  `COMMUNICATEADDRESS` text,
  `GROUPID` varchar(50) default NULL,
  `CLIENTCOMPANYNAME` varchar(100) default NULL,
  `CITYCOUNTY` varchar(50) default NULL,
  `PROJECTEXPOSITORY` text,
  `COMPLETETIME13` datetime default NULL,
  `COMPLETETIME14` datetime default NULL,
  `COMPLETETIME15` datetime default NULL,
  `COMPLETETIME16` datetime default NULL,
  `WANEQUIPMENT` varchar(100) default NULL,
  `WANEQUIPMENTPORT` varchar(50) default NULL,
  `BANDWIDTH` varchar(50) default NULL,
  `CIRCUITNO` varchar(50) default NULL,
  `TRANSFERSEQUIPMENT` varchar(100) default NULL,
  `TRANSFERSEQUIPMENTPORT` varchar(50) default NULL,
  `COREINFORMATION` text,
  `ORGNETMODE` varchar(50) default NULL,
  `VLAN` varchar(50) default NULL,
  `WANSWITCHEQUIPMENT` varchar(100) default NULL,
  `WANSWITCHEQUIPMENTPORT` varchar(50) default NULL,
  `BASESTATION` varchar(100) default NULL,
  `CLIENTIP` varchar(50) default NULL,
  `CLIENTGATEWAY` varchar(50) default NULL,
  `CLIENTSUBNETMASK` varchar(50) default NULL,
  `ACCESSTOTHEBASESTATION` varchar(100) default NULL,
  `NORESOURCE` text,
  `WANBOSSFACTORYMODE` varchar(100) default NULL,
  `USERFIRSTFACTORY` varchar(100) default NULL,
  `USERFIRSTMODE` varchar(100) default NULL,
  `USERSENCODEFACTORY` varchar(100) default NULL,
  `USERSENCODEMODE` varchar(100) default NULL,
  `USERTHIRDFACTORY` varchar(100) default NULL,
  `USERTHIRDMODE` varchar(100) default NULL,
  `USERFOURFACTORY` varchar(100) default NULL,
  `USERFOURMODE` varchar(100) default NULL,
  `USERFIVEFACTORY` varchar(100) default NULL,
  `USERFIVEMODE` varchar(100) default NULL,
  `BASESTATIONFIRSTFACTORY` varchar(100) default NULL,
  `BASESTATIONFIRSTMODE` varchar(100) default NULL,
  `BASESTATIONSENCODEFACTORY` varchar(100) default NULL,
  `BASESTATIONSENCODEMODE` varchar(100) default NULL,
  `GROUPINNET` varchar(100) default NULL,
  `MAINTENANCECOMPANY` varchar(100) default NULL,
  `ISINTEGRATEBEFOREHAND` varchar(2) default NULL,
  `ISLINEPIPEBEFORDHAND` varchar(2) default NULL,
  `ISINTEGRATEINDUE` varchar(2) default NULL,
  `ISLINEPIPEINDUE` varchar(2) default NULL,
  `STATE` varchar(10) default NULL,
  `JOBNO` varchar(6) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yd_170data
-- ----------------------------
INSERT INTO `yd_170data` VALUES ('20111024210122683995', '', '', '', '', '', '', '', '', '', '', '', '', '34', '', '', '', '', '', '', '0001-01-01 08:00:00', '0001-01-01 08:00:00', '0001-01-01 08:00:00', '0001-01-01 08:00:00', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '123', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '1', null);
INSERT INTO `yd_170data` VALUES ('2011102421122650268', '', '', '', '', '', '', '', '', '', '', '', '', '34', '', '', '', '', '', '', '0001-01-01 08:00:00', '0001-01-01 08:00:00', '0001-01-01 08:00:00', '0001-01-01 08:00:00', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '123', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '1', null);
INSERT INTO `yd_170data` VALUES ('20111024211256433419', '', '', '', '', '', '', '', '', '', '', '', '', '34', '', '', '', '', '', '', '0001-01-01 08:00:00', '0001-01-01 08:00:00', '0001-01-01 08:00:00', '0001-01-01 08:00:00', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '123', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '1', null);
INSERT INTO `yd_170data` VALUES ('20111024211306689855', '', '', '', '', '', '', '', '', '', '', '', '', '34', '', '', '', '', '', '', '0001-01-01 08:00:00', '0001-01-01 08:00:00', '0001-01-01 08:00:00', '0001-01-01 08:00:00', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '123', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '1', null);
INSERT INTO `yd_170data` VALUES ('20111221153424741735', '', '', '', '', '', '', '', '', '', '', '', '', '34', '', '', '', '', '', '', '0001-01-01 08:00:00', '0001-01-01 08:00:00', '0001-01-01 08:00:00', '0001-01-01 08:00:00', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '123', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '1', '');

-- ----------------------------
-- View structure for `view_rolemenu`
-- ----------------------------
DROP VIEW IF EXISTS `view_rolemenu`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_rolemenu` AS select `sm`.`ID` AS `id`,`sm`.`NAME` AS `name`,`sm`.`FATHER` AS `father`,(case ifnull(`sr`.`MENUID`,_utf8'') when _gbk'' then 0 else 1 end) AS `ischeck` from (`system_menu` `sm` left join `system_rolemenu` `sr` on((`sm`.`ID` = `sr`.`MENUID`)));

-- ----------------------------
-- View structure for `view_rolepowerval`
-- ----------------------------
DROP VIEW IF EXISTS `view_rolepowerval`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_rolepowerval` AS select `sm`.`ID` AS `id`,`sm`.`NAME` AS `name`,`sm`.`FATHER` AS `father`,ifnull(`smp`.`POWERVAL`,0) AS `powerval` from (`system_menu` `sm` left join `system_modulepower` `smp` on((`sm`.`ID` = `smp`.`MODULEID`)));

-- ----------------------------
-- View structure for `view_system_rolemenu`
-- ----------------------------
DROP VIEW IF EXISTS `view_system_rolemenu`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_system_rolemenu` AS select `sm`.`ID` AS `id`,`sm`.`NAME` AS `name`,`sm`.`FATHER` AS `father`,ifnull(`sr`.`ROLEID`,_utf8'') AS `roleid`,(case ifnull(`sr`.`MENUID`,_utf8'') when _gbk'' then 0 else 1 end) AS `ischeck` from (`system_menu` `sm` left join `system_rolemenu` `sr` on((`sm`.`ID` = `sr`.`MENUID`)));

-- ----------------------------
-- View structure for `view_system_rolepowerval`
-- ----------------------------
DROP VIEW IF EXISTS `view_system_rolepowerval`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_system_rolepowerval` AS select `smf`.`ID` AS `id`,`smf`.`MENUID` AS `menuid`,`sm`.`NAME` AS `menuname`,`smf`.`FIELDCODE` AS `fieldcode`,`smf`.`FIELDNAME` AS `fieldname`,`smf`.`TABLECODE` AS `tablecode`,`smf`.`TABLENAME` AS `tablename`,ifnull(`srfp`.`ROLEID`,_utf8'') AS `roleid`,ifnull(`srfp`.`POWERVAL`,0) AS `powerval` from ((`system_menufield` `smf` left join `system_rolefiledpower` `srfp` on((`smf`.`ID` = `srfp`.`FIELDID`))) left join `system_menu` `sm` on((`smf`.`MENUID` = `sm`.`ID`)));

-- ----------------------------
-- View structure for `view_system_user`
-- ----------------------------
DROP VIEW IF EXISTS `view_system_user`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_system_user` AS select `su`.`ID` AS `id`,`su`.`USERNAME` AS `username`,`su`.`TEL` AS `tel`,`su`.`EMAIL` AS `email`,`su`.`PASSWORD` AS `password`,`su`.`DEPARTMENTID` AS `departmentid`,`sd`.`DEPARTNAME` AS `departmentname`,`su`.`REMARK` AS `remark`,`su`.`CREATEMAN` AS `createman`,`su`.`CREATEDATE` AS `createdate`,`su`.`STATE` AS `state` from (`system_user` `su` left join `system_department` `sd` on((`su`.`DEPARTMENTID` = `sd`.`ID`)));

-- ----------------------------
-- Procedure structure for `css`
-- ----------------------------
DROP PROCEDURE IF EXISTS `css`;
DELIMITER ;;
CREATE DEFINER=`fjfdszj`@`localhost` PROCEDURE `css`(Prefix varchar(10) ,TableName varchar(100),Flagselect int)
begin
	declare  ColumName varchar(50);
	declare  ExecStr varchar(2000);
	declare  CreateStr varchar(2000);
	declare  NewLine int;
      DECLARE done INT DEFAULT 0;
      declare ColumsStrs cursor for select column_name  from Tmp;
      DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
      CREATE  temporary   TABLE  Tmp(column_name varchar(50));
	Set CreateStr='';
	/*Set ExecStr= concat('select column_name into Tmp from columns where table_name = ', TableName, ' order by ordinal_position' ); */
       PREPARE stmt FROM 'insert  into Tmp select column_name  from information_schema.columns where table_name =? order by ordinal_position' ;
      set @ExecStr=TableName;
      execute stmt using @ExecStr;
	set NewLine=1;
        open ColumsStrs;
        REPEAT
		fetch ColumsStrs into ColumName;
                IF NOT done THEN
 			        if Prefix<>'' then
                                set ColumName=concat(Prefix,'.',ColumName);
                                end if;
  				Set CreateStr=concat(CreateStr , ColumName , ',');
 	       		 	if  NewLine=8 then
    					set CreateStr=concat(CreateStr , CONVERT(0x0A  , CHAR));
    					set  NewLine=0;
                                end if;
  			        set  NewLine = NewLine + 1;
             END IF;
        UNTIL  done
    END REPEAT;
	close ColumsStrs;
       /*deallocate ColumsStrs;*/
	if FlagSelect=1 then
 		 if Prefix<>'' then
   			 Set CreateStr=concat('select ' ,substring(CreateStr, 1, length(CreateStr)-1) ,  CONVERT(0x0A  , CHAR) , 'from ' ,TableName , ' ', Prefix);
		 else
   		 Set CreateStr=concat('select ' , substring(CreateStr, 1, length(CreateStr)-1) ,  CONVERT(0x0A  , CHAR) , 'from ' , TableName);
	         end if;
         end if;
select CreateStr;
drop temporary table  Tmp;
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `P_GridViewPager`
-- ----------------------------
DROP PROCEDURE IF EXISTS `P_GridViewPager`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `P_GridViewPager`(OUT recordTotal int,IN viewName varchar(800),IN fieldName varchar(800),IN keyName varchar(200),IN pageSize int,IN pageNo int,IN orderString varchar(200),IN whereString varchar(800))
BEGIN
     DECLARE beginRow INT;
     DECLARE endRow INT;
     DECLARE tempLimit VARCHAR(200);
     DECLARE tempCount NVARCHAR(1000);
     DECLARE tempMain VARCHAR(1000);

     SET beginRow = (pageNo - 1) * pageSize;
     SET endRow = pageNo * pageSize;
     SET tempLimit = concat(' limit ' , beginRow  ,' , ',pageSize);
     
     /*输出参数为总记录数
     SET tempCount = 'SELECT recordTotal = COUNT(*) FROM (SELECT '+keyName+' FROM '+viewName+' WHERE '+whereString+') AS my_temp';
     EXECUTE sp_executesql tempCount,N'recordTotal INT OUTPUT',recordTotal OUTPUT;
     */

     /*主查询返回结果集*/
     SET @tempMain = concat('SELECT ',fieldName,' FROM ',viewName,' WHERE ',whereString,' ',orderString,tempLimit);
   
     /*PRINT tempMain   SELECT @tempMain; */
     PREPARE stmt FROM @tempMain;
     EXECUTE stmt ;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `sys_get_sequences`
-- ----------------------------
DROP FUNCTION IF EXISTS `sys_get_sequences`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `sys_get_sequences`(in_seq_name char(200)) RETURNS char(100) CHARSET gbk
BEGIN
       #������ż�1
	update td_sequences set value=value + 1 where seq_name =in_seq_name;
	select value into @v_value from td_sequences where seq_name=in_seq_name;
        select CURDATE()+0  into @v_date ;
        select lpad(concat(@v_value,''),8,'0') into @v_result;
        select concat(@v_date,@v_result) into @v_result;
        return @v_result;
END
;;
DELIMITER ;
