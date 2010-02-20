-- phpMyAdmin SQL Dump
-- version 2.9.0.3
-- http://www.phpmyadmin.net
-- 
-- 主机: localhost
-- 生成日期: 2008 年 06 月 20 日 15:25
-- 服务器版本: 4.1.10
-- PHP 版本: 5.0.4
-- 
-- 数据库: `yzapprove`
-- 
---create database elework

---drop database elework
-- --------------------------------------------------------

-- 
-- 表的结构 `tbdatumdepartment`
-- 

CREATE TABLE `tbdatumdepartment` (
  `TheCode` varchar(20) NOT NULL default '',
  `TheName` varchar(30) default NULL,
  `RelCode` varchar(20) default NULL,
  `ParentCode` varchar(20) default NULL,
  `Belongcode` varchar(20) default NULL,
  `Remark` varchar(20) default NULL,
  `TheState` int(4) NOT NULL default '0',
  `IsUse` int(4) NOT NULL default '0',
  `Creater` varchar(20) default NULL,
  `CreateTime` varchar(14) default NULL,
  `Stater` varchar(20) default NULL,
  `StateTime` varchar(14) default NULL,
  `Editer` varchar(20) default NULL,
  `EditeTime` varchar(14) default NULL,
  PRIMARY KEY  (`TheCode`)
) ;

------ENGINE=MyISAM DEFAULT CHARSET=utf8
--- 
--- 导出表中的数据 `tbdatumdepartment`
---

INSERT INTO `tbdatumdepartment` (`TheCode`, `TheName`, `RelCode`, `ParentCode`, `Belongcode`, `Remark`, `TheState`, `IsUse`, `Creater`, `CreateTime`, `Stater`, `StateTime`, `Editer`, `EditeTime`) VALUES 
('001001', '开发部', '001', '001', '', 'Remark', 0, 0, NULL, '20080529125016', NULL, '20080528211311', NULL, '20080528211311'),
('1002', '总经办', '001', '', NULL, '', 0, 0, '001', '20080608193859', '001', '20080608193859', '001', '20080608193859'),
('1003', '市场部', '001', '', NULL, '02', 0, 0, '001', '20080608193947', '001', '20080608193947', '001', '20080608193947');

-- --------------------------------------------------------

-- 
--- 表的结构 `tbdatumemployee`
-- 

CREATE TABLE `tbdatumemployee` (
  `TheCode` varchar(20) NOT NULL default '',
  `TheName` varchar(30) default NULL,
  `Sex` varchar(4) default NULL,
  `Address` varchar(200) NOT NULL default '',
  `EmployeTypeCode` varchar(20) default NULL,
  `CertifyTypeCode` varchar(20) default NULL,
  `CertifyCode` varchar(20) default NULL,
  `OtherRel` varchar(100) default NULL,
  `RelTelphone` varchar(20) default NULL,
  `InDate` varchar(8) default NULL,
  `OutDate` varchar(8) default NULL,
  `DepCode` varchar(20) default NULL,
  `SerialCode` varchar(20) default NULL,
  `Remark` varchar(20) default NULL,
  `TheState` int(4) NOT NULL default '0',
  `IsUse` int(4) NOT NULL default '0',
  `Creater` varchar(20) default NULL,
  `CreateTime` varchar(14) default NULL,
  `Stater` varchar(20) default NULL,
  `StateTime` varchar(14) default NULL,
  `Editer` varchar(20) default NULL,
  `EditeTime` varchar(14) default NULL,
  PRIMARY KEY  (`TheCode`)
) ;

-- 
-- 导出表中的数据 `tbdatumemployee`
-- 

INSERT INTO `tbdatumemployee` (`TheCode`, `TheName`, `Sex`, `Address`, `EmployeTypeCode`, `CertifyTypeCode`, `CertifyCode`, `OtherRel`, `RelTelphone`, `InDate`, `OutDate`, `DepCode`, `SerialCode`, `Remark`, `TheState`, `IsUse`, `Creater`, `CreateTime`, `Stater`, `StateTime`, `Editer`, `EditeTime`) VALUES 
('001', '施振景', '3', '', '1', '1001', '', '', '13599204724', '20080528', '00000000', '001001', '001', NULL, 0, 0, NULL, '20080528225118', '001', '20080607225134', '001', '20080607225134'),
('002', '小陈', '3', '  453453', '1', '1001', '123456498798', '', '', '20080603', '20080611', '001001', NULL, NULL, 0, 0, '001', '20080604230926', '001', '20080607225148', '001', '20080607225148'),
('003', '小明', '3', '', '1', '1001', '', '', '', '20080602', '20080603', '', NULL, NULL, 0, 0, '001', '20080607225353', '001', '20080607225711', '001', '20080607225711');

-- --------------------------------------------------------

-- 
-- 表的结构 `tbdatumproduct`
-- 

CREATE TABLE `tbdatumproduct` (
  `TheCode` varchar(20) NOT NULL default '',
  `TheName` varchar(30) default NULL,
  `ModelCode` varchar(20) default NULL,
  `SpecCode` varchar(20) default NULL,
  `UnitCode` varchar(20) default NULL,
  `ProductTypeCode` varchar(30) default NULL,
  `Remark` varchar(20) default NULL,
  `TheState` int(4) NOT NULL default '0',
  `IsUse` int(4) NOT NULL default '0',
  `Creater` varchar(20) default NULL,
  `CreateTime` varchar(14) default NULL,
  `Stater` varchar(20) default NULL,
  `StateTime` varchar(14) default NULL,
  `Editer` varchar(20) default NULL,
  `EditeTime` varchar(14) default NULL,
  PRIMARY KEY  (`TheCode`)
) ;

-- 
-- 导出表中的数据 `tbdatumproduct`
-- 

INSERT INTO `tbdatumproduct` (`TheCode`, `TheName`, `SpecCode`, `UnitCode`, `ProductTypeCode`, `Remark`, `TheState`, `IsUse`, `Creater`, `CreateTime`, `Stater`, `StateTime`, `Editer`, `EditeTime`) VALUES 
('1002', '商品2', '2', '1001', '1002', '', 0, 0, '001', '20080607181930', '001', '20080607181930', '001', '20080607181930'),
('1001', '商品1', '1', '4', '1001', '', 0, 0, '001', '20080607181907', '001', '20080607181907', '001', '20080607181907');

-- --------------------------------------------------------

-- 
-- 表的结构 `tssystemfunction`
-- 

CREATE TABLE `tssystemfunction` (
  `TheCode` varchar(20) NOT NULL default '',
  `TheShortCode` varchar(20) default NULL,
  `TheName` varchar(30) default NULL,
  `ParentCode` varchar(20) default NULL,
  `childflag` varchar(2) default NULL,
  `RefPage` varchar(100) default NULL,
  `Img` varchar(100) default NULL,
  `Remark` varchar(100) default NULL,
  `TheState` int(4) NOT NULL default '0',
  `IsUse` int(4) NOT NULL default '0',
  `Creater` varchar(20) default NULL,
  `CreateTime` varchar(14) default NULL,
  `Stater` varchar(20) default NULL,
  `StateTime` varchar(14) default NULL,
  `Editer` varchar(20) default NULL,
  `EditeTime` varchar(14) default NULL,
  PRIMARY KEY  (`TheCode`)
) ;

-- 
-- 导出表中的数据 `tssystemfunction`
-- 

INSERT INTO `tssystemfunction` (`TheCode`, `TheShortCode`, `TheName`, `ParentCode`, `childflag`, `RefPage`, `Img`, `Remark`, `TheState`, `IsUse`, `Creater`, `CreateTime`, `Stater`, `StateTime`, `Editer`, `EditeTime`) VALUES 
('1003', 'YzApprove', '电子工单(模块)', '0', '1', NULL, NULL, 'Remark', 0, 0, NULL, '20080528210456', NULL, '20080528210456', NULL, '20080528210456'),
('1003Rtp', 'RtpYzApprove', '电子工单(报表)', '0', '1', NULL, NULL, 'Remark', 0, 0, NULL, '20080529123626', NULL, '20080528210456', NULL, '20080528210456'),
('1003990', 'J_Archive', '基础档案', '1003', '1', NULL, NULL, 'Remark', 0, 0, NULL, '20080528210456', NULL, '20080528210456', NULL, '20080528210456'),
('100399010', 'J_DatumDepart', '部门档案', '1003990', '0', 'm_archive/DepartmentManage.faces', NULL, 'Remark', 0, 0, NULL, '20080529132618', NULL, '20080528210456', NULL, '20080528210456'),
('100399020', 'J_DatumEmployee', '员工档案', '1003990', '0', 'm_archive/EmployeeManage.faces', NULL, 'Remark', 0, 0, NULL, '20080529132618', NULL, '20080528210456', NULL, '20080528210456'),
('100399030', 'J_DatumProduct', '设备档案', '1003990', '0', 'm_archive/DatumProductManage.faces', NULL, 'Remark', 0, 0, NULL, '20080529132618', NULL, '20080528210456', NULL, '20080528210456'),
('100399040', 'J_DatumProduct', '客户档案', '1003990', '0', 'm_archive/DatumCopartner.faces', NULL, 'Remark', 0, 0, NULL, '20080529132618', NULL, '20080528210456', NULL, '20080528210456'),

('1003998', 'J_Subject', '数据字典', '1003', '1', NULL, NULL, 'Remark', 0, 0, NULL, '20080528210456', NULL, '20080528210456', NULL, '20080528210456'),
('1003998001', 'YzApprove_SubjectApp', '巡检维护', '1003998', '0', 'm_subject/uiPatrolReport.faces', NULL, 'Remark', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('1003998010', 'YzApprove_SubjectApp', '工单字典', '1003998', '0', 'm_subject/subjectframe.faces', NULL, 'Remark', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('1003998990', 'YzApprove_SubjectApp', '基础字典', '1003998', '0', 'm_subject/subjectframe.faces', NULL, 'Remark', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('1003998999', 'YzApprove_SubjectApp', '系统字典', '1003998', '0', 'm_subject/subjectframe.faces', NULL, 'Remark', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),

('1003999', 'J_System', '系统管理', '1003', '1', NULL, NULL, 'Remark', 0, 0, NULL, '20080528210456', NULL, '20080528210456', NULL, '20080528210456'),
('1003999003', 'J_SystemGroupUser', '用户设置', '1003999', '0', 'm_system/GroupUserManage.faces', NULL, 'Remark', 0, 0, NULL, '20080529132618', NULL, '20080529130409', NULL, '20080529130409'),
('1003999002', 'J_SystemGroup', '角色设置', '1003999', '0', 'm_system/GroupManage.faces', NULL, 'Remark', 0, 0, NULL, '20080529132618', NULL, '20080529130409', NULL, '20080529130409'),
('1003999001', 'J_SystemFunction', '功能设置', '1003999', '0', 'm_system/FunctionManage.faces', NULL, 'Remark', 0, 0, NULL, '20080529132618', NULL, '20080529130409', NULL, '20080529130409'),
('1003999010', 'J_SystemFlow', '审批流程', '1003999', '0', 'm_system/FlowManage.faces', NULL, 'Remark', 0, 0, NULL, '20080529132618', NULL, '20080529130607', NULL, '20080529130607'),
('1003999020', 'J_SystemFlow', '报表管理', '1003999', '0', 'm_system/rptManage.faces', NULL, 'Remark', 0, 0, NULL, '20080529132618', NULL, '20080529130607', NULL, '20080529130607'),
('1003999990', 'J_SystemFlow', '日志管理', '1003999', '0', 'm_system/logManage.faces', NULL, 'Remark', 0, 0, NULL, '20080529132618', NULL, '20080529130710', NULL, '20080529130710'),
('1003999999', 'J_SystemFlow', '修改密码', '1003999', '', 'm_system/modifyPwd.faces', '', 'Remark', 0, 0, NULL, '20080529132618', '001', '20080606123110', '001', '20080606123110'),

('1003010', 'YzApprove_Apply', '工单管理', '1003', '1', NULL, NULL, 'Remark', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('1003010010', 'YzApprove_StorageApp', '电话处理', '1003010', '0', 'm_elework/uieleworkTel.faces', NULL, 'Remark', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('1003010020', 'YzApprove_StorageApp', '外出维护', '1003010', '0', 'm_elework/uieleworkoutrepair.faces', NULL, 'Remark', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('1003010030', 'YzApproveRtp_Storage', '内部维修', '1003010', '0', 'm_elework/uieleworkrepair.faces', '', '', 0, 0, '001', '20080606090337', '001', '20080606090337', '001', '20080606090337'),
('1003010040', 'YzApproveRtp_Storage', '巡检登记', '1003010', '0', 'm_elework/uieleworkpatrol.faces', '', '', 0, 0, '001', '20080606091024', '001', '20080606091024', '001', '20080606091024'),
('1003010050', 'YzApprove_StorageApp', '工程维护', '1003010', '0', 'm_elework/uieleworkproject.faces', '', '', 0, 0, '001', '20080606091428', '001', '20080606091428', '001', '20080606091428'),

('1003020', 'YzApprove_Apply', '物料管理', '1003', '1', NULL, NULL, 'Remark', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('1003020010', 'YzApprove_StorageApp', '物料分布', '1003020', '0', 'm_Storage/uiStorage.faces', NULL, 'Remark', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('1003020020', 'YzApprove_StorageApp', '物料流转', '1003020', '0', 'm_Storage/uiStorageList.faces', NULL, 'Remark', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);


--------------------------------------------------------

-- 
-- 表的结构 `tssystemgroup`
-- 

CREATE TABLE `tssystemgroup` (
  `TheCode` varchar(20) NOT NULL default '',
  `TheName` varchar(30) default NULL,
  `Remark` varchar(20) default NULL,
  `TheState` int(4) NOT NULL default '0',
  `IsUse` int(4) NOT NULL default '0',
  `Creater` varchar(20) default NULL,
  `CreateTime` varchar(14) default NULL,
  `Stater` varchar(20) default NULL,
  `StateTime` varchar(14) default NULL,
  `Editer` varchar(20) default NULL,
  `EditeTime` varchar(14) default NULL,
  PRIMARY KEY  (`TheCode`)
) ;

-- 
-- 导出表中的数据 `tssystemgroup`
-- 

INSERT INTO `tssystemgroup` (`TheCode`, `TheName`, `Remark`, `TheState`, `IsUse`, `Creater`, `CreateTime`, `Stater`, `StateTime`, `Editer`, `EditeTime`) VALUES 
('001', '管理员', '管理员', 0, 0, '001', '20080601204029', 'fjfdszj', '20080601204029', 'fjfdszj', '20080601204029'),
('002', '经理', '', 0, 0, '001', '20080607180953', '001', '20080607180953', '001', '20080607180953'),
('003', '主管', '123', 0, 0, '001', '20080607203716', '001', '20080607203716', '001', '20080607203716'),
('004', '职员', '', 0, 0, '001', '20080607200203', '001', '20080607200203', '001', '20080607200203'),
('006', '市场部', '', 0, 0, '001', '20080608231053', '001', '20080608231053', '001', '20080608231053'),
('100', '测试', '测试使用', 0, 0, '001', '20080619092357', '001', '20080619092357', '001', '20080619092357');

-- --------------------------------------------------------

-- 
-- 表的结构 `tssystemgrouppower`
-- 

CREATE TABLE `tssystemgrouppower` (
  `TheCode` varchar(20) NOT NULL default '',
  `FunCode` varchar(20) NOT NULL default '',
  `FunisShow` tinyint(1) NOT NULL default '1',
  `PowerValue` varchar(20) default NULL,
  `Remark` varchar(20) default NULL,
  `TheState` int(4) NOT NULL default '0',
  `IsUse` int(4) NOT NULL default '0',
  `Creater` varchar(20) default NULL,
  `CreateTime` varchar(14) default NULL,
  `Stater` varchar(20) default NULL,
  `StateTime` varchar(14) default NULL,
  `Editer` varchar(20) default NULL,
  `EditeTime` varchar(14) default NULL,
  PRIMARY KEY  (`TheCode`,`FunCode`)
) ;


INSERT INTO `tssystemgrouppower` (`TheCode`, `FunCode`, `FunisShow`, `PowerValue`, `Remark`, `TheState`, `IsUse`, `Creater`, `CreateTime`, `Stater`, `StateTime`, `Editer`, `EditeTime`) VALUES 
('001', '1003', 1, '111100', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('001', '1003Rtp', 1, '111100', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('001', '1003990', 1, '111100', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('001', '100399010', 1, '111100', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('001', '100399020', 1, '111100', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('001', '100399030', 1, '111100', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('001', '1003998', 1, '111100', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('001', '1003998010', 1, '111100', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('001', '1003998990', 1, '111100', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('001', '1003998999', 1, '111100', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('001', '1003999', 1, '111100', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('001', '1003999001', 1, '111100', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('001', '1003999002', 1, '111100', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('001', '1003999003', 1, '111100', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('002', '1003010052', 1, '111111', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('001', '1003999010', 1, '111100', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('001', '1003999020', 1, '111100', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('001', '1003999990', 1, '111100', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('001', '1003999999', 1, '111100', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('001', '1003010', 1, '111100', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('001', '1003010010', 1, '111100', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
('001', '1003010020', 1, '111100', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);

-- 
-- 导出表中的数据 `tssystemgrouppower`
-- 
-- --------------------------------------------------------

-- 
-- 表的结构 `tssystemgroupuser`
-- 

CREATE TABLE `tssystemgroupuser` (
  `TheCode` varchar(20) NOT NULL default '',
  `TheName` varchar(30) default NULL,
  `GroupCode` varchar(20) default NULL,
  `EmployeCode` varchar(20) default NULL,
  `PassWd` varchar(100) default NULL,
  `Remark` varchar(20) default NULL,
  `TheState` int(4) NOT NULL default '0',
  `IsUse` int(4) NOT NULL default '0',
  `Creater` varchar(20) default NULL,
  `CreateTime` varchar(14) default NULL,
  `Stater` varchar(20) default NULL,
  `StateTime` varchar(14) default NULL,
  `Editer` varchar(20) default NULL,
  `EditeTime` varchar(14) default NULL,
  PRIMARY KEY  (`TheCode`)
) ;

-- 
-- 导出表中的数据 `tssystemgroupuser`
-- 

INSERT INTO `tssystemgroupuser` (`TheCode`, `TheName`, `GroupCode`, `EmployeCode`, `PassWd`, `Remark`, `TheState`, `IsUse`, `Creater`, `CreateTime`, `Stater`, `StateTime`, `Editer`, `EditeTime`) VALUES 
('001', 'fjfdszj', '001', '001', '123@123', NULL, 0, 0, NULL, '20080528215019', NULL, '20080528215019', NULL, '20080528215019'),
('002', '测试', '100', '001', '111111', '', 0, 0, '001', '20080530143355', '001', '20080619092942', '001', '20080619092942'),
('003', 'root', '002', '001', '111111', '', 0, 0, '001', '20080530143620', '001', '20080619120126', '001', '20080619120126'),
('ds', 'ds', '001', '', '111111', 'sdddds', 0, 0, '001', '20080620123226', '001', '20080620123226', '001', '20080620123226');

-- --------------------------------------------------------

-- 
-- 表的结构 `tssystemrecord`
-- 

CREATE TABLE `tssystemrecord` (
  `TheCode` varchar(20) NOT NULL default '',
  `TheName` varchar(30) default NULL,
  `SubjectSort` varchar(20) NOT NULL default '',
  `Remark` varchar(20) default NULL,
  `TheState` int(4) NOT NULL default '0',
  `IsUse` int(4) NOT NULL default '0',
  `Creater` varchar(20) default NULL,
  `CreateTime` varchar(14) default NULL,
  `Stater` varchar(20) default NULL,
  `StateTime` varchar(14) default NULL,
  `Editer` varchar(20) default NULL,
  `EditeTime` varchar(14) default NULL,
  `flag` varchar(20) default NULL,
  PRIMARY KEY  (`TheCode`,`SubjectSort`)
);

-- 
-- 导出表中的数据 `tssystemrecord`
-- 

INSERT INTO `tssystemrecord` (`TheCode`, `TheName`, `SubjectSort`, `Remark`, `TheState`, `IsUse`, `Creater`, `CreateTime`, `Stater`, `StateTime`, `Editer`, `EditeTime`, `flag`) VALUES 
('1001', '规格', '1001998990', 'SpecCode', 0, 0, '', '', '001', '20080607153343', '001', '20080607153343', 'SpecCode'),
('1002', '计量单位', '1001998990', 'UnitCode备注', 0, 0, '', '', '001', '20080604171634', '001', '20080604171634', 'UnitCode'),
('1003', '性别', '1001998990', 'sexremark', 0, 0, '', '', '001', '20080604221537', '001', '20080604221537', 'Sex'),
('1004', '证件类型', '1001998990', 'CertifyTypeCode', 0, 0, '', '', '001', '20080604225859', '001', '20080604225859', 'CertifyTypeCode'),
('1005', '工种', '1001998990', 'EmployeTypeCode', 0, 0, '', '', '001', '20080604172516', '001', '20080604172516', 'EmployeTypeCode'),
('001', '申请科目1', '1001998010', '', 0, 0, '001', '20080603231340', '001', '20080607201256', '001', '20080607201256', ''),
('1001', '001', '1001998010', '', 0, 0, '001', '20080607201340', '001', '20080607201340', '001', '20080607201340', '001'),
('001', '申请科目1', '1001998999', '', 0, 0, '001', '20080603231420', '001', '20080607201256', '001', '20080607201256', ''),
('002', '经理', '1001998999', '', 0, 0, '001', '20080603231429', '001', '20080607175212', '001', '20080607175212', ''),
('1400', '100', '1001998999', '', 0, 0, '001', '20080603231904', '001', '20080603231904', '001', '20080603231904', '100'),
('1006', '商品类别', '1001998990', 'ProductTypeCode', 0, 0, '001', '20080604171749', '001', '20080604171749', '001', '20080604171749', 'ProductTypeCode');

-- --------------------------------------------------------

-- 
-- 表的结构 `tssystemsubjectrecord`
-- 

CREATE TABLE `tssystemsubjectrecord` (
  `RowNo` int(4) NOT NULL default '0',
  `TheName` varchar(30) default NULL,
  `SubjectSort` varchar(20) NOT NULL default '',
  `TheCode` varchar(20) NOT NULL default '',
  `RemarkItem` varchar(20) NOT NULL default '',
  `TheState` int(4) NOT NULL default '0',
  `IsUse` int(4) NOT NULL default '0',
  `Creater` varchar(20) default NULL,
  `CreateTime` varchar(14) default NULL,
  `Stater` varchar(20) default NULL,
  `StateTime` varchar(14) default NULL,
  `Editer` varchar(20) default NULL,
  `EditeTime` varchar(14) default NULL,
  PRIMARY KEY  (`RowNo`,`SubjectSort`,`TheCode`)
) ;

-- 
-- 导出表中的数据 `tssystemsubjectrecord`
-- 

INSERT INTO `tssystemsubjectrecord` (`RowNo`, `TheName`, `SubjectSort`, `TheCode`, `RemarkItem`, `TheState`, `IsUse`, `Creater`, `CreateTime`, `Stater`, `StateTime`, `Editer`, `EditeTime`) VALUES 
(1003, '本', '1001998990', '1002', '', 0, 0, '001', '20080604171537', '001', '20080604171537', '001', '20080604171537'),
(1002, '支', '1001998990', '1002', '', 0, 0, '001', '20080604171525', '001', '20080604171525', '001', '20080604171525'),
(1001, '个', '1001998990', '1002', '', 0, 0, '001', '20080604171511', '001', '20080604171511', '001', '20080604171511'),
(100, '46456', '1001998999', '1400', '', 0, 0, '001', '20080603231916', 'fjfdszj', 'fjfdszj', '20080603231935', '20080603231935'),
(3, '男', '1001998990', '1003', '', 0, 0, '001', '20080603222051', 'fjfdszj', 'fjfdszj', '20080604221315', '20080604221315'),
(1, '50×100', '1001998990', '1001', '50×100', 0, 0, '001', '20080603222015', 'fjfdszj', 'fjfdszj', '20080604171836', '20080604171836'),
(4, '册', '1001998990', '1002', '', 0, 0, '001', '20080604171550', '001', '20080604171550', '001', '20080604171550'),
(4, '100×500', '1001998990', '1001', '100×500规格', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
(5, '001', '1001998010', '1001', '001', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
(2, '20×100', '1001998990', '1001', '20×100规格', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
(1001, '手机', '1001998990', '1006', '', 0, 0, '001', '20080604172015', '001', '20080604172015', '001', '20080604172015'),
(1002, '配件', '1001998990', '1006', '', 0, 0, '001', '20080604172032', '001', '20080604172032', '001', '20080604172032'),
(1003, '电话卡', '1001998990', '1006', '', 0, 0, '001', '20080604172052', '001', '20080604172052', '001', '20080604172052'),
(1004, '笔', '1001998990', '1006', '', 0, 0, '001', '20080604172130', '001', '20080604172130', '001', '20080604172130'),
(1005, '本子', '1001998990', '1006', '', 0, 0, '001', '20080604172140', '001', '20080604172140', '001', '20080604172140'),
(1001, '身份证', '1001998990', '1004', '', 0, 0, '001', '20080604172317', '001', '20080604172317', '001', '20080604172317'),
(1002, '工作证', '1001998990', '1004', '', 0, 0, '001', '20080604172327', '001', '20080604172327', '001', '20080604172327'),
(1003, '军管证', '1001998990', '1004', '', 0, 0, '001', '20080604172409', '001', '20080604172409', '001', '20080604172409'),
(2, '女', '1001998990', '1003', '', 0, 0, '001', '20080604221326', '001', '20080604221326', '001', '20080604221326'),
(1, '正式工', '1001998990', '1005', '', 0, 0, '001', '20080604221415', '001', '20080604221415', '001', '20080604221415'),
(2, '临时工', '1001998990', '1005', '', 0, 0, '001', '20080604221445', '001', '20080604221445', '001', '20080604221445'),
(3, '钟点工', '1001998990', '1005', '', 0, 0, '001', '20080604221458', '001', '20080604221458', '001', '20080604221458'),
(6, '002', '1001998010', '1001', '002', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL),
(7, '003', '1001998010', '1001', '002', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);



-- 
-- 表的结构 `tssystemgroupuser`
-- 往来单位信息

CREATE TABLE `tbDatumCopartner` (
  `TheCode` varchar(20) NOT NULL default '',
  `TheName` varchar(30) default NULL,
  `TheShortName` varchar(20) default NULL,
  `CopartnerType` varchar(20) default NULL,  
  `RelFax` varchar(20) default NULL,
  `RelTelephone` varchar(20) default NULL,
  `RelCode` varchar(20) default NULL,
  `Remark` varchar(20) default NULL,
  `TheState` int(4) NOT NULL default '0',
  `IsUse` int(4) NOT NULL default '0',
  `Creater` varchar(20) default NULL,
  `CreateTime` varchar(14) default NULL,
  `Stater` varchar(20) default NULL,
  `StateTime` varchar(14) default NULL,
  `Editer` varchar(20) default NULL,
  `EditeTime` varchar(14) default NULL,
  PRIMARY KEY  (`TheCode`)
) ;



-------------------------------------------------------------------------
-- 
-- 表的结构 `tbEleworkOutRepair`
-- 
---外出维修单
CREATE TABLE `tbEleworkOutRepair` (
  `TheSeqn` int(12) unsigned NOT NULL auto_increment,
  `BillNo` varchar(20) NOT NULL default '',
  `TheDate` varchar(8) default NULL,
  `RelTelephone` varchar(20) default NULL,
  `RelCode` varchar(20) default NULL,
  `ClientCode` varchar(8) default NULL,
  `FaxCode` varchar(200) default NULL,
  `WorkArray` varchar(200) default NULL,
  `IsFinally`  tinyint(1) NOT NULL default '1',
  `NoticeDate` varchar(8) default NULL,
  `TrafficeDate` varchar(8) default NULL,
  `AskDate` varchar(8) default NULL,
  `TrafficeFee` decimal(12,2) default '0.00',
  `StartDate` varchar(8) default NULL,
  `FinDate` varchar(8) default NULL,
  `ClientSign` varchar(20) default NULL,
  `CheckSign` varchar(20) default NULL,
  `EngineSign` varchar(8) default NULL,
  `ClientAttitud` varchar(200) default NULL,
  `TransferSort` varchar(20) default NULL,
  `BillSortCode` varchar(20) default NULL,
  `UseState` int(4) NOT NULL default '0',
  `Remark` varchar(20) default NULL,
  `TheState` int(4) NOT NULL default '0',
  `IsUse` int(4) NOT NULL default '0',
  `Creater` varchar(20) default NULL,
  `CreateTime` varchar(14) default NULL,
  `Stater` varchar(20) default NULL,
  `StateTime` varchar(14) default NULL,
  `Editer` varchar(20) default NULL,
  `EditeTime` varchar(14) default NULL,
  PRIMARY KEY  (`TheSeqn`,`BillNo`)
) ;

-- --------------------------------------------------------

-- 
-- 表的结构 `tbEleworkOutRepairItem`
-- 子表---外出维修单

CREATE TABLE `tbEleworkOutRepairItem` (
  `TheSeqn` int(12) unsigned NOT NULL auto_increment,
  `BillNo` varchar(20) NOT NULL default '',
  `RowNo` int(4) NOT NULL default '0',
  `ListNo` varchar(20) default NULL,
  `ProductCode` varchar(20) default NULL,
  `ProductSort` varchar(20) default NULL,
  `Quedesc` varchar(200) default NULL,
  `Taskdesc` varchar(200) default NULL,
  `Remark` varchar(20) default NULL,
  PRIMARY KEY  (`TheSeqn`,`BillNo`,`RowNo`)
) ;

---------------------------------------------
-- 表的结构 `tbEleworkOutRepairItemDetail`
-- 子表---外出维修单
CREATE TABLE `tbEleworkOutRepairItemDetail` (
  `TheSeqn` int(12) unsigned NOT NULL auto_increment,
  `BillNo` varchar(20) NOT NULL default '',
  `RowNo` int(4) NOT NULL default '0',
  `DetailNo` int(4) NOT NULL default '0',
  `FitCode` varchar(20) default NULL,
  `FitName` varchar(20) default NULL,
  `Number` int(4) NOT NULL default '0',
  `Price` decimal(12,2) default '0.00',
  `TotalMoney` decimal(12,2) default '0.00',
  `Remark` varchar(200) default NULL,
  PRIMARY KEY  (`TheSeqn`,`BillNo`,`RowNo`)
) ;


-------------------------------------------------------------------------
-- 
-- 表的结构 `tbEleworkOutRepair`
-- 
---维修单
CREATE TABLE `tbEleworkRepair` (
  `TheSeqn` int(12) unsigned NOT NULL auto_increment,
  `BillNo` varchar(20) NOT NULL default '',
  `TheDate` varchar(8) default NULL,
  `RelTelephone` varchar(20) default NULL,
  `RelCode` varchar(20) default NULL,
  `ClientCode` varchar(8) default NULL,
  `ClientAdd` varchar(200) default NULL,
  `ServiceSortCode` varchar(200) default NULL,
  `IsFinally`  tinyint(1) NOT NULL default '1',
  `InHandOver` varchar(20) default NULL,
  `InSelfSign` varchar(20) default NULL,
  `OutHandOver` varchar(20) default NULL,
  `OutSelfSign` varchar(20) default NULL,
  `FinDate` varchar(8) default NULL,
  `ClientSign` varchar(20) default NULL,
  `CheckSign` varchar(20) default NULL,
  `EngineSign` varchar(8) default NULL,
  `ClientAttitud` varchar(200) default NULL,
  `TransferSort` varchar(20) default NULL,
  `BillSortCode` varchar(20) default NULL,
  `UseState` int(4) NOT NULL default '0',
  `Remark` varchar(20) default NULL,
  `TheState` int(4) NOT NULL default '0',
  `IsUse` int(4) NOT NULL default '0',
  `Creater` varchar(20) default NULL,
  `CreateTime` varchar(14) default NULL,
  `Stater` varchar(20) default NULL,
  `StateTime` varchar(14) default NULL,
  `Editer` varchar(20) default NULL,
  `EditeTime` varchar(14) default NULL,
  PRIMARY KEY  (`TheSeqn`,`BillNo`)
)  ;

-- --------------------------------------------------------

-- 
-- 表的结构 `tbEleworkOutRepairItem`
-- 子表

CREATE TABLE `tbEleworkRepairItem` (
  `TheSeqn` int(12) unsigned NOT NULL auto_increment,
  `BillNo` varchar(20) NOT NULL default '',
  `RowNo` int(4) NOT NULL default '0',
  `ListNo` varchar(20) default NULL,
  `ProductCode` varchar(20) default NULL,
  `ProductSort` varchar(20) default NULL,
  `InDate` varchar(8) default NULL,
  `IsKeep` tinyint(1) NOT NULL default '1',
  `IsReject` tinyint(1) NOT NULL default '1',
  `Quedesc` varchar(200) default NULL,
  `Configdesc` varchar(200) default NULL,   
  `Appeardesc` varchar(200) default NULL,
  `Operdesc` varchar(200) default NULL,
  `Taskdesc` varchar(200) default NULL,
  `Remark` varchar(20) default NULL,
  PRIMARY KEY  (`TheSeqn`,`BillNo`,`RowNo`)
)  ;


CREATE TABLE `tbEleworkRepairItemDetail` (
  `TheSeqn` int(12) unsigned NOT NULL auto_increment,
  `BillNo` varchar(20) NOT NULL default '',
  `RowNo` int(4) NOT NULL default '0',
  `DetailNo` int(4) NOT NULL default '0',
  `FitCode` varchar(20) default NULL,
  `FitName` varchar(20) default NULL,
  `Number` int(4) NOT NULL default '0',
  `Price` decimal(12,2) default '0.00',
  `TotalMoney` decimal(12,2) default '0.00',
  `Remark` varchar(200) default NULL,
  PRIMARY KEY  (`TheSeqn`,`BillNo`,`RowNo`)
)  ;


-------------------------------------------------------------------------
-- 
-- 表的结构 `tbEleworkOutRepair`
-- 
---巡检单
CREATE TABLE `tbEleworkPatrol` (
  `TheSeqn` int(12) unsigned NOT NULL auto_increment,
  `BillNo` varchar(20) NOT NULL default '',
  `TheDate` varchar(8) default NULL,
 
  `ClientCode` varchar(8) default NULL,
  `PatrolDate` varchar(200) default NULL,
  `IsFinally`  tinyint(1) NOT NULL default '1',
  `FinDate` varchar(8) default NULL,
  `CheckSign` varchar(20) default NULL,
  `EngineSign` varchar(8) default NULL,
  `ClientSign` varchar(20) default NULL,
  `ClientAttitud` varchar(200) default NULL,
  `TransferSort` varchar(20) default NULL,
  `BillSortCode` varchar(20) default NULL,
  `UseState` int(4) NOT NULL default '0',
  `Remark` varchar(20) default NULL,
  `TheState` int(4) NOT NULL default '0',
  `IsUse` int(4) NOT NULL default '0',
  `Creater` varchar(20) default NULL,
  `CreateTime` varchar(14) default NULL,
  `Stater` varchar(20) default NULL,
  `StateTime` varchar(14) default NULL,
  `Editer` varchar(20) default NULL,
  `EditeTime` varchar(14) default NULL,
  PRIMARY KEY  (`TheSeqn`,`BillNo`)
) ;

-- --------------------------------------------------------

-- 
-- 表的结构 `tbEleworkOutRepairItem`
-- 子表---巡检单

CREATE TABLE `tbEleworkPatrolItem` (
  `TheSeqn` int(12) unsigned NOT NULL auto_increment,
  `BillNo` varchar(20) NOT NULL default '',
  `RowNo` int(4) NOT NULL default '0',
  `ProjectCode` varchar(20) default NULL,
  `PatrolCode` varchar(20) default NULL,
  `IsNormal` varchar(20) default NULL,
  `Abnordesc` varchar(200) default NULL,
  `Remark` varchar(20) default NULL,
  PRIMARY KEY  (`TheSeqn`,`BillNo`,`RowNo`)
) ;


-------------------------------------------------------------------------
-- 
-- 表的结构 `tbEleworkOutRepair`
-- 
---巡检单
CREATE TABLE `tbStorage` (
  `TheSeqn` int(12) unsigned NOT NULL auto_increment,
  `BillNo` varchar(20) NOT NULL default '',
  `TheDate` varchar(8) default NULL,
  `ClientCode` varchar(8) default NULL,
  `StorageCode` varchar(20) default NULL,
  `PlaceCode` varchar(20) default NULL,
  `Number`  int default NULL,
  `UseState` int(4) NOT NULL default '0',
  `Remark` varchar(20) default NULL,
  `TheState` int(4) NOT NULL default '0',
  `IsUse` int(4) NOT NULL default '0',
  `Creater` varchar(20) default NULL,
  `CreateTime` varchar(14) default NULL,
  `Stater` varchar(20) default NULL,
  `StateTime` varchar(14) default NULL,
  `Editer` varchar(20) default NULL,
  `EditeTime` varchar(14) default NULL,
  PRIMARY KEY  (`TheSeqn`,`BillNo`)
) ;

-- --------------------------------------------------------

-- 
-- 表的结构 `tbEleworkOutRepairItem`
-- 子表---巡检单

CREATE TABLE `tbStorageItem` (
  `TheSeqn` int(12) unsigned NOT NULL auto_increment,
  `BillNo` varchar(20) NOT NULL default '',
  `RowNo` int(4) NOT NULL default '0',
  `ListNo` varchar(20) default NULL,
  `ProductCode` varchar(20) default NULL,
  `productTypecode` varchar(20) default NULL,
  `AssentsCode` varchar(20) default NULL,
  `Remark` varchar(20) default NULL,
  PRIMARY KEY  (`TheSeqn`,`BillNo`,`RowNo`)
) ;


-------------------------------------------------------------------------
-- 
-- 表的结构 `tbEleworkOutRepair`
-- 
---巡检单
CREATE TABLE `tbPatrolReport` (
  `TheSeqn` int(12) unsigned NOT NULL auto_increment,
  `BillNo` varchar(20) NOT NULL default '',
  `TheDate` varchar(8) default NULL,
  `ProjectCode` varchar(20) default NULL,
  `ProjectName` varchar(30) default NULL,
  `UseState` int(4) NOT NULL default '0',
  `Remark` varchar(20) default NULL,
  `TheState` int(4) NOT NULL default '0',
  `IsUse` int(4) NOT NULL default '0',
  `Creater` varchar(20) default NULL,
  `CreateTime` varchar(14) default NULL,
  `Stater` varchar(20) default NULL,
  `StateTime` varchar(14) default NULL,
  `Editer` varchar(20) default NULL,
  `EditeTime` varchar(14) default NULL,
  PRIMARY KEY  (`TheSeqn`,`BillNo`)
)  ;

-- --------------------------------------------------------

-- 
-- 表的结构 `tbEleworkOutRepairItem`
-- 子表---巡检单

CREATE TABLE `tbPatrolReportItem` (
  `TheSeqn` int(12) unsigned NOT NULL auto_increment,
  `BillNo` varchar(20) NOT NULL default '',
  `RowNo` int(4) NOT NULL default '0',
  `PatrolCode` varchar(20) default NULL,
  `ReportContent` varchar(20) default NULL,
  `RowTheState` tinyint(1) NOT NULL default '1',
  `Remark` varchar(20) default NULL,
  PRIMARY KEY  (`TheSeqn`,`BillNo`,`RowNo`)
) ;







-- --------------------------------------------------------

-- 
-- 表的结构 `tbstorageflowlog`
-- 

CREATE TABLE `tbStorageList` (
  `TheSeqn` int(12) unsigned NOT NULL auto_increment,
  `ListNo` varchar(20) NOT NULL default '',
  `SerialNO` int(4) NOT NULL default '0',
  `BillNo` varchar(20) NOT NULL default '',
  `BillSort` varchar(20) default NULL,
  `RepaireCode` varchar(20) default NULL,
  `RepaireDate` varchar(8) default NULL,
  `TheState` int(4) NOT NULL default '0',
  `IsUse` int(4) NOT NULL default '0',
  `Creater` varchar(20) default NULL,
  `CreateTime` varchar(14) default NULL,
  `Stater` varchar(20) default NULL,
  `StateTime` varchar(14) default NULL,
  `Editer` varchar(20) default NULL,
  `EditeTime` varchar(14) default NULL,
  PRIMARY KEY  (`TheSeqn`,`ListNo`,`SerialNO`)
) ;