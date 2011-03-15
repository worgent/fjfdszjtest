/*
MySQL Data Transfer
Source Host: localhost
Source Database: womobile
Target Host: localhost
Target Database: womobile
Date: 2011-01-21 17:19:55
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_attendance
-- ----------------------------
CREATE TABLE `t_attendance` (
  `Id` bigint(20) unsigned NOT NULL auto_increment,
  `UserId` varchar(20) default NULL,
  `Position` varchar(50) default NULL,
  `Latitude` varchar(20) default NULL,
  `Longitude` varchar(20) default NULL,
  `Updatetime` datetime default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_dailywork
-- ----------------------------
CREATE TABLE `t_dailywork` (
  `id` varchar(20) NOT NULL,
  `title` varchar(100) default NULL,
  `dailyworktype` varchar(20) default NULL COMMENT '字典表中增加 日常工作类别={由用户自定义}            本表中填入其值。',
  `missiongrade` varchar(10) default NULL COMMENT '字典表中增加  任务级别={日常任务、紧急任务、特殊任务}            在本表中，直接填入任务级别值。',
  `attachment` varchar(100) default '' COMMENT '上传文件，此字段记录文件所在相对目录及文件名',
  `target` varchar(20) character set utf8 collate utf8_bin default '' COMMENT '字典表中定义 工作指标={日、周、月}            本表中填入值',
  `targetvalue` smallint(6) default NULL COMMENT '手工录入，只能是整数',
  `feedbackpattern` varchar(20) default NULL COMMENT '关键反馈单的ID编号',
  `makedatetime` datetime default NULL,
  `maker` varchar(20) default NULL,
  `remark` varchar(200) default NULL,
  `executors` text COMMENT '选取各部门中的员工，员工之间以半角逗号分隔，最后一个员工不加逗号，如”张三,李四,王五“',
  `state` varchar(10) default NULL COMMENT '新任务、已审核、处理中、已中止、已完成、已归档            除了新任务外，其他状态不允许可删除任务单,已审核状态执行人手机就可收到任务，执行人读取后返回处理中，任务可以由创建人标记已中止，已完成，已归档',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日常工作表';

-- ----------------------------
-- Table structure for t_dailyworkexcute
-- ----------------------------
CREATE TABLE `t_dailyworkexcute` (
  `id` varchar(20) NOT NULL default '',
  `pid` varchar(20) default NULL,
  `executorid` varchar(20) default NULL,
  `status` smallint(6) default '0' COMMENT '处理中=0，已完成=1，已评份=9',
  `score` int(11) default '0',
  `remark` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='执行人列表';

-- ----------------------------
-- Table structure for t_declare
-- ----------------------------
CREATE TABLE `t_declare` (
  `id` varchar(20) NOT NULL,
  `title` varchar(100) default NULL,
  `declaretype` varchar(20) default NULL COMMENT '字典表中增加 日常工作类别={由用户自定义}            本表中填入其值。',
  `missiongrade` varchar(10) default NULL COMMENT '字典表中增加  任务级别={日常任务、紧急任务、特殊任务}            在本表中，直接填入任务级别值。',
  `attachment` varchar(100) default NULL COMMENT '上传文件，此字段记录文件所在相对目录及文件名',
  `target` varchar(20) character set utf8 collate utf8_bin default NULL COMMENT '字典表中定义 工作指标={日、周、月}            本表中填入值',
  `targetvalue` smallint(6) default NULL COMMENT '手工录入，只能是整数',
  `declarepattern` varchar(20) default NULL COMMENT '关键反馈单的ID编号',
  `makedatetime` datetime default NULL,
  `maker` varchar(20) default NULL,
  `remark` varchar(200) default NULL,
  `executors` text COMMENT '选取各部门中的员工，员工之间以半角逗号分隔，最后一个员工不加逗号，如”张三,李四,王五“',
  `state` varchar(10) default NULL COMMENT '新任务、已审核、处理中、已中止、已完成、已归档            除了新任务外，其他状态不允许可删除任务单,已审核状态执行人手机就可收到任务，执行人读取后返回处理中，任务可以由创建人标记已中止，已完成，已归档',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='事项申报';

-- ----------------------------
-- Table structure for t_declareexcute
-- ----------------------------
CREATE TABLE `t_declareexcute` (
  `id` varchar(20) NOT NULL default '',
  `pid` varchar(20) default NULL,
  `executorid` varchar(20) default NULL,
  `status` smallint(6) default '0' COMMENT '处理中=0，已完成=1，已评份=9',
  `score` int(11) default '0',
  `remark` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='执行人列表';

-- ----------------------------
-- Table structure for t_dictionary
-- ----------------------------
CREATE TABLE `t_dictionary` (
  `Id` varchar(20) NOT NULL,
  `Name` varchar(50) default NULL,
  `Remark` varchar(100) default '',
  `IsSys` smallint(6) default '0',
  `SysDictId` varchar(20) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典表';

-- ----------------------------
-- Table structure for t_dictionaryd
-- ----------------------------
CREATE TABLE `t_dictionaryd` (
  `id` varchar(20) NOT NULL default '',
  `pid` varchar(20) default NULL,
  `dictvalue` varchar(50) default NULL COMMENT 'label的名称根据父表的名称定',
  `state` smallint(6) default '1' COMMENT '启用=1,停用=0，默认启用',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_pattern
-- ----------------------------
CREATE TABLE `t_pattern` (
  `id` varchar(20) NOT NULL default '',
  `patterntype` varchar(20) default NULL,
  `patternname` varchar(100) default NULL,
  `remark` varchar(200) default NULL,
  `maker` varchar(20) default NULL,
  `makedatetime` datetime default NULL,
  `state` smallint(6) default '1' COMMENT '启用=1，停用=0，默认启用',
  `events` varchar(100) default NULL COMMENT '多选择，事件之间以半角逗号分隔',
  `tablename` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_patternd
-- ----------------------------
CREATE TABLE `t_patternd` (
  `id` varchar(20) NOT NULL default '',
  `pid` varchar(20) default NULL,
  `ParentType` varchar(10) default NULL,
  `fieldseqn` smallint(6) default NULL,
  `fielddesc` varchar(20) default NULL,
  `fieldname` varchar(20) default NULL,
  `fieldtype` varchar(10) default NULL COMMENT '字符、数字、日期、枚举、位置、照片  当选择字符时，字段长度只能整数并大于1，选择数字时，牧师字段类型为number型，长度允许有小数位，选择位置时，为字符型??长度固定30，经纬度按经续度之间以半角逗号分隔，如45.345233,118.342453。选择照片时，固定为字符串，长度200，用于保存照片的相对路径与文件。选择枚举时，选择数据字典中项目。',
  `fieldlength` varchar(10) default NULL,
  `remark` varchar(100) default NULL,
  `fieldenum` varchar(200) default NULL,
  `state` smallint(6) default NULL COMMENT '启用=1，停用=0，默认启用            此字段为备用字段，目前暂不使用',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='生成的物理表中必须有以下几个字段           编号、父表编号、执行人ID、状态(根据事件来定义，默认为0，对于正';

-- ----------------------------
-- Table structure for t_reportpattern
-- ----------------------------
CREATE TABLE `t_reportpattern` (
  `ID` varchar(20) NOT NULL,
  `PatternId` varchar(20) default NULL,
  `ReportName` varchar(100) default NULL,
  `State` varchar(10) default NULL,
  `Remark` varchar(200) default NULL,
  `Maker` varchar(20) default NULL,
  `MakeDateTime` datetime default NULL,
  `patterntype` varchar(20) default '',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_reportpatternc
-- ----------------------------
CREATE TABLE `t_reportpatternc` (
  `Id` varchar(20) NOT NULL,
  `ReportId` varchar(20) default NULL,
  `PatternId` varchar(20) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_reportpatternf
-- ----------------------------
CREATE TABLE `t_reportpatternf` (
  `Id` varchar(20) NOT NULL,
  `ReportId` varchar(20) default NULL,
  `PatternId` varchar(20) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_system_branch
-- ----------------------------
CREATE TABLE `t_system_branch` (
  `branchId` varchar(20) NOT NULL,
  `branchName` varchar(100) default NULL,
  `parentBranchId` varchar(20) default NULL,
  `branchOrder` int(11) default NULL,
  `CreateId` varchar(20) default NULL,
  PRIMARY KEY  (`branchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_system_group
-- ----------------------------
CREATE TABLE `t_system_group` (
  `groupId` varchar(20) NOT NULL,
  `groupName` varchar(255) NOT NULL,
  `groupDesc` varchar(255) NOT NULL,
  `createId` varchar(20) NOT NULL,
  PRIMARY KEY  (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_system_grouprole
-- ----------------------------
CREATE TABLE `t_system_grouprole` (
  `GroupRoleId` varchar(20) default NULL,
  `GroupId` varchar(20) default NULL,
  `RoleId` varchar(20) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_system_permission
-- ----------------------------
CREATE TABLE `t_system_permission` (
  `permissionId` varchar(20) NOT NULL,
  `permissionName` varchar(100) default NULL,
  `permissionResource` varchar(50) default NULL,
  `action` varchar(255) default NULL,
  `parentPermissionId` int(11) default NULL,
  `isMenu` smallint(1) default NULL,
  `permissionOrder` int(11) default NULL,
  `CreateId` int(11) default NULL,
  PRIMARY KEY  (`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_system_role
-- ----------------------------
CREATE TABLE `t_system_role` (
  `roleId` varchar(20) NOT NULL,
  `roleName` varchar(100) default NULL,
  `createId` varchar(20) default NULL,
  PRIMARY KEY  (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_system_rp
-- ----------------------------
CREATE TABLE `t_system_rp` (
  `RolePermissionId` varchar(20) NOT NULL,
  `RoleId` varchar(20) default NULL,
  `PermissionId` varchar(20) default NULL,
  PRIMARY KEY  (`RolePermissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_system_user
-- ----------------------------
CREATE TABLE `t_system_user` (
  `id` varchar(20) NOT NULL default 'current_timestamp' COMMENT '主键',
  `userId` varchar(100) NOT NULL COMMENT '用户编号',
  `groupId` varchar(20) default NULL COMMENT '用户组编号',
  `userName` varchar(50) NOT NULL COMMENT '用户名',
  `repasswd` varchar(64) default NULL,
  `passwd` varchar(20) default NULL COMMENT '密码',
  `realName` varchar(10) default NULL COMMENT '真实姓名',
  `zhicheng` varchar(20) default NULL COMMENT '职称',
  `duty` varchar(20) default NULL COMMENT '职务',
  `sex` smallint(1) default NULL COMMENT '性别',
  `mobilephone` varchar(20) default NULL COMMENT '移动电话',
  `workphone` varchar(20) default NULL COMMENT '工作电话',
  `creator` varchar(20) default NULL COMMENT '创建人',
  `createDate` timestamp NULL default CURRENT_TIMESTAMP COMMENT '创建时间',
  `email` varchar(100) default NULL,
  `birthday` date default NULL COMMENT '出生日期',
  `address` varchar(100) default NULL COMMENT '通讯地址',
  `postId` varchar(20) default NULL COMMENT '邮编',
  `accountBank` varchar(100) default NULL COMMENT '开户行',
  `accountId` varchar(40) default NULL COMMENT '银行账号',
  `remark` varchar(200) default NULL COMMENT '备注',
  `branchId` varchar(20) NOT NULL COMMENT '所属机构',
  `state` smallint(1) NOT NULL default '1' COMMENT '状态(0.停用 ,1.启用)',
  `intervalVal` int(11) default NULL COMMENT '时间间隔',
  `startTime` time default NULL COMMENT '有效时间hh:mm(开始)',
  `endTime` time default NULL COMMENT '有效时间hh:mm(结束)',
  `uploadIds` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_tomonitor
-- ----------------------------
CREATE TABLE `t_tomonitor` (
  `id` varchar(20) NOT NULL,
  `title` varchar(100) default NULL,
  `tomonitortype` varchar(20) default NULL COMMENT '字典表中增加 日常工作类别={由用户自定义}\r\n            本表中填入其值。',
  `missiongrade` varchar(10) default NULL COMMENT '字典表中增加  任务级别={日常任务、紧急任务、特殊任务}\r\n            在本表中，直接填入任务级别值。',
  `attachment` varchar(100) default NULL COMMENT '上传文件，此字段记录文件所在相对目录及文件名',
  `target` varchar(20) character set utf8 collate utf8_bin default NULL COMMENT '字典表中定义 工作指标={日、周、月}            本表中填入值',
  `targetvalue` smallint(6) default NULL COMMENT '手工录入，只能是整数',
  `feedbackpattern` varchar(20) default NULL COMMENT '关键反馈单的ID编号',
  `makedatetime` datetime default NULL,
  `maker` varchar(20) default NULL,
  `remark` varchar(200) default NULL,
  `executors` text COMMENT '选取各部门中的员工，员工之间以半角逗号分隔，最后一个员工不加逗号，如”张三,李四,王五“',
  `state` varchar(10) default NULL COMMENT '新任务、已审核、处理中、已中止、已完成、已归档            除了新任务外，其他状态不允许可删除任务单,已审核状态执行人手机就可收到任务，执行人读取后返回处理中，任务可以由创建人标记已中止，已完成，已归档',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='督办工作';

-- ----------------------------
-- Table structure for t_tomonitorexcute
-- ----------------------------
CREATE TABLE `t_tomonitorexcute` (
  `id` varchar(20) NOT NULL default '',
  `pid` varchar(20) default NULL,
  `executorid` varchar(20) default NULL,
  `status` smallint(6) default '0' COMMENT '处理中=0，已完成=1，已评份=9',
  `score` int(11) default '0',
  `remark` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='执行人列表';

-- ----------------------------
-- Table structure for td_sequences
-- ----------------------------
CREATE TABLE `td_sequences` (
  `seq_name` varchar(200) NOT NULL default '',
  `value` int(11) default '0',
  PRIMARY KEY  (`seq_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for u_0030
-- ----------------------------
CREATE TABLE `u_0030` (
  `id` int(11) NOT NULL auto_increment,
  `executorId` varchar(20) default NULL,
  `parentId` varchar(20) default NULL,
  `stateRemark` varchar(200) default NULL,
  `state` smallint(6) default '1',
  `zt0` varchar(50) default NULL,
  `cxrq1` date default NULL,
  `cxsp2` varchar(20) default NULL,
  `cxqk3` varchar(100) default NULL,
  `zp4` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for u_0031
-- ----------------------------
CREATE TABLE `u_0031` (
  `id` int(11) NOT NULL auto_increment,
  `executorId` varchar(20) default NULL,
  `parentId` varchar(20) default NULL,
  `stateRemark` varchar(200) default NULL,
  `state` smallint(6) default '1',
  `zt0` varchar(50) default NULL,
  `xfrq1` date default NULL,
  `fwtd2` varchar(20) default NULL,
  `dmxx3` varchar(100) default NULL,
  `zp4` varchar(200) default NULL,
  `bz5` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for u_0032
-- ----------------------------
CREATE TABLE `u_0032` (
  `id` int(11) NOT NULL auto_increment,
  `executorId` varchar(20) default NULL,
  `parentId` varchar(20) default NULL,
  `stateRemark` varchar(200) default NULL,
  `state` smallint(6) default '1',
  `zt0` varchar(50) default NULL,
  `bfrq1` date default NULL,
  `bfr2` varchar(50) default NULL,
  `bffk3` varchar(100) default NULL,
  `bz4` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Procedure structure for pb_delAllSubBranch
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_delAllSubBranch`(in branchId1 varchar(20)  CHARSET utf8)
BEGIN
call pb_getSubBranch(branchId1,@a);
    if @a is not null then
    set @b=concat('\'',@a,'\'');
    end if;
 set @SqlCmd=concat("delete from t_system_branch where branchId in(",@b,")");
PREPARE stmt FROM @SqlCmd;
EXECUTE stmt ;
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pb_delAllSubPermission
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_delAllSubPermission`(in permissionId1 varchar(20)  CHARSET utf8)
BEGIN
call pb_getSubPermission(permissionId1,@a);
    if @a is not null then
    set @b=concat('\'',@a,'\'');
    end if;
 set @SqlCmd=concat("delete from t_system_permission where permissionId in(",@b,")");
PREPARE stmt FROM @SqlCmd;
EXECUTE stmt ;
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pb_delRPById
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_delRPById`(in permissionId1 varchar(20) CHARSET utf8)
BEGIN
    call pb_getSubPermission(permissionId1,@a);
    if @a is not null then
    set @b=concat('\'',@a,'\'');
    end if;
 set @SqlCmd=concat("delete from t_system_rp where permissionId in(",@b,")");
PREPARE stmt FROM @SqlCmd;
EXECUTE stmt ;
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pb_get_allPermission
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_get_allPermission`(userid1 varchar(20) CHARSET utf8)
BEGIN
declare done INT DEFAULT 0;
declare permissionId1 varchar(2000);
DECLARE cur_permission CURSOR for
select f.permissionid from t_system_rp f join
(select a.roleid from t_system_grouprole a join (select groupid from t_system_user where userid=userid1) b 
on a.groupid=b.groupid) e on f.roleid=e.roleid;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
CREATE  temporary   TABLE   if not exists  tmp_permission1 (permissionId varchar(20) character set utf8 collate utf8_general_ci,permissionName varchar(100) character set utf8 collate utf8_general_ci,parentPermissionId varchar(20) character set utf8 collate utf8_general_ci,permissionResource varchar(50) character set utf8 collate utf8_general_ci,action varchar(255) character set utf8 collate utf8_general_ci, permissionOrder int(13));
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
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pb_get_menu_tree
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_get_menu_tree`(userid1 varchar(20) CHARSET utf8)
begin
declare done INT DEFAULT 0;
declare permissionId1 varchar(2000);
declare permissionOrder1 int;
declare permissionIds varchar(2000);
DECLARE cur_permission CURSOR for
select permissionid from (
select distinct permissionid from t_system_rp d join
(select a.roleid from t_system_grouprole a join (select groupid from t_system_user where userid=userid1) b
on a.groupid=b.groupid) c on d.roleid=c.roleid) t;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
CREATE  temporary   TABLE   if not exists  tmp_permission (permissionId varchar(20) CHARSET utf8,permissionOrder int(13));
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
drop table tmp_permission;end;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pb_getBranchTree
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_getBranchTree`(in branchId1 varchar(20) CHARSET utf8)
BEGIN
declare done INT DEFAULT 0;
declare branchName1 varchar(100);
declare parentBranchId1 varchar(20);
declare branchOrder1 int;
declare checked1 char(1);
DECLARE cur_branch CURSOR for
select branchid from t_system_branch where parentBranchId=branchId1 order by branchOrder;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
SET @@max_sp_recursion_depth = 10;
CREATE  temporary   TABLE   if not exists  tmp_branchTree (branchId varchar(20) character set utf8 collate utf8_general_ci,branchName varchar(100) character set utf8 collate utf8_general_ci,parentBranchId varchar(20) character set utf8 collate utf8_general_ci, branchOrder int(13),checked char(1));
select branchName into branchName1 from t_system_branch where branchId=branchId1;
select parentBranchId  into parentBranchId1 from t_system_branch where branchId=branchId1;
select branchOrder into branchOrder1 from t_system_branch where branchId=branchId1;
 select case when a.branchId in (select b.parentbranchId from t_system_branch as b) then '1' else '0' end into checked1
from t_system_branch as a where a.branchid=branchId1;
insert into tmp_branchTree(branchId,branchName,parentBranchId,branchOrder,checked) values(branchId1,branchName1,parentBranchId1,branchOrder1,checked1);
open cur_branch;
   repeat
	fetch  cur_branch into branchId1;
	if not done then
          call pb_getSubBranchTree(branchId1);
        end  if;
  UNTIL done END REPEAT;
  select * from tmp_branchTree ;
  drop table tmp_branchTree;
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pb_getBranchTreeForChange
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_getBranchTreeForChange`(in branchId1 varchar(20) CHARSET utf8)
BEGIN
declare done INT DEFAULT 0;
declare branchName1 varchar(100);
declare parentBranchId1 varchar(20);
declare branchOrder1 int;
declare checked1 char(1);
DECLARE cur_branch CURSOR for
select branchid from t_system_branch where parentBranchId=branchId1 order by branchOrder;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
SET @@max_sp_recursion_depth = 10;
CREATE  temporary   TABLE   if not exists  tmp_branchTree1 (branchId varchar(20) character set utf8 collate utf8_general_ci,branchName varchar(100) character set utf8 collate utf8_general_ci,parentBranchId varchar(20) character set utf8 collate utf8_general_ci, branchOrder int(13));
select branchName into branchName1 from t_system_branch where branchId=branchId1;
select parentBranchId  into parentBranchId1 from t_system_branch where branchId=branchId1;
select branchOrder into branchOrder1 from t_system_branch where branchId=branchId1;
insert into tmp_branchTree1(branchId,branchName,parentBranchId,branchOrder) values(branchId1,branchName1,parentBranchId1,branchOrder1);
open cur_branch;
   repeat
	fetch  cur_branch into branchId1;
	if not done then
          call pb_getSubBranchTreeForChange(branchId1,0);
        end  if;
  UNTIL done END REPEAT;
  select * from tmp_branchTree1;
  drop table tmp_branchTree1;
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pb_getBranchUserTree
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_getBranchUserTree`(in branchId1 varchar(20) CHARSET utf8)
BEGIN
declare done INT DEFAULT 0;
declare branchName1 varchar(100);
declare parentBranchId1 varchar(20);
declare branchOrder1 int;
declare checked1 char(1);
DECLARE cur_branch CURSOR for
select branchid from t_system_branch where parentBranchId=branchId1 order by branchOrder;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
SET @@max_sp_recursion_depth = 10;
CREATE  temporary   TABLE   if not exists  tmp_branchTree (branchId varchar(20) character set utf8 collate utf8_general_ci,branchName varchar(100) character set utf8 collate utf8_general_ci,parentBranchId varchar(20) character set utf8 collate utf8_general_ci, branchOrder int(13),checked char(1));

select branchName into branchName1 from t_system_branch where branchId=branchId1;

select parentBranchId  into parentBranchId1 from t_system_branch where branchId=branchId1;

select branchOrder into branchOrder1 from t_system_branch where branchId=branchId1;

insert into tmp_branchTree(branchId,branchName,parentBranchId,branchOrder,checked) values(branchId1,branchName1,parentBranchId1,branchOrder1,1);

open cur_branch;
   repeat
	fetch  cur_branch into branchId1;
	if not done then
          call pb_getSubBranchUserTree(branchId1);
        end  if;
  UNTIL done END REPEAT;
  select * from tmp_branchTree ;
  drop table tmp_branchTree;
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pb_getRolePermissionForChange
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_getRolePermissionForChange`(in userid1 varchar(20) CHARSET utf8,in roleId1 varchar(20) CHARSET utf8)
BEGIN
declare done INT DEFAULT 0;
declare permissionId1 varchar(2000);
declare permissionOrder1 int;
declare permissionIds varchar(2000);
DECLARE cur_permission CURSOR for
select permissionid from (
select distinct permissionid from t_system_rp d join
(select a.roleid from t_system_grouprole a join (select groupid from t_system_user where userid=userid1) b
on a.groupid=b.groupid) c on d.roleid=c.roleid) t;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
CREATE  temporary   TABLE   if not exists  tmp_permission (permissionId varchar(20) CHARSET utf8,permissionOrder int(13));
open cur_permission;
   repeat
	fetch  cur_permission into permissionId1;
	if not done then
          call proc_getAllSuperMenu(permissionId1);
        end  if;
  UNTIL done END REPEAT;
close cur_permission;
select m.*,n.permissionName,n.parentPermissionId from (
select distinct x.permissionId,case when x.permissionId=y.permissionId then 1 else 0 end 'checked' from tmp_permission x left join(select distinct f.permissionid from t_system_rp f
where f.roleId=roleId1)y  on x.permissionId=y.permissionId
) m, t_system_permission n where m.permissionId=n.permissionId order by m.permissionId;
drop table tmp_permission;
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pb_getSubBranch
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_getSubBranch`(in branchId1 varchar(20) CHARSET utf8,out result varchar(1000) CHARSET utf8)
BEGIN
declare done INT DEFAULT 0;
declare cur1 Cursor for  select branchId  from t_system_branch where parentBranchId=branchId1;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
SET @@max_sp_recursion_depth = 10; 
set result=branchId1;
open cur1;
    repeat
	fetch  cur1 into branchId1;
	if not done then
           call pb_getSubBranch(branchId1, @a);
           set result=concat(result,'\',\'',@a);
           
	   
        end  if;
    UNTIL done END REPEAT;
    close cur1;
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pb_getSubBranchTree
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_getSubBranchTree`(in branchId1 varchar(20) CHARSET utf8)
BEGIN
declare done INT DEFAULT 0;
declare branchName1 varchar(100);
declare parentBranchId1 varchar(20);
declare branchOrder1 int;
declare checked1 char(1);
DECLARE cur_branch CURSOR for
select branchid from t_system_branch where parentBranchId=branchId1 order by branchOrder;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
SET @@max_sp_recursion_depth = 10;
select branchName into branchName1 from t_system_branch where branchId=branchId1;
 select case when a.branchId in (select b.parentbranchId from t_system_branch as b) then '1' else '0' end into checked1
from t_system_branch as a where a.branchid=branchId1;
select parentBranchId  into parentBranchId1 from t_system_branch where branchId=branchId1;
select branchOrder into branchOrder1 from t_system_branch where branchId=branchId1;
insert into tmp_branchTree(branchId,branchName,parentBranchId,branchOrder,checked) values(branchId1,branchName1,parentBranchId1,branchOrder1,checked1);
open cur_branch;
   repeat
	fetch  cur_branch into branchId1;
	if not done then
          call pb_getSubBranchTree(branchId1);
        end  if;
  UNTIL done END REPEAT;
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pb_getSubBranchTreeForChange
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_getSubBranchTreeForChange`(in branchId1 varchar(20) CHARSET utf8,in depth smallint(2))
BEGIN
declare done INT DEFAULT 0;
declare branchName1 varchar(100);
declare parentBranchId1 varchar(20);
declare branchOrder1 int;
declare checked1 char(1);
declare str1 varchar(20) default '';
declare num smallint default 0;
DECLARE cur_branch CURSOR for
select branchid from t_system_branch where parentBranchId=branchId1 order by branchOrder;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
SET @@max_sp_recursion_depth = 10;
select branchName into branchName1 from t_system_branch where branchId=branchId1;
select parentBranchId  into parentBranchId1 from t_system_branch where branchId=branchId1;
select branchOrder into branchOrder1 from t_system_branch where branchId=branchId1;
while num<=depth do
   set str1=concat('--',str1);
   set num=num+1;
end while;
insert into tmp_branchTree1(branchId,branchName,parentBranchId,branchOrder) values(branchId1,concat(str1,branchName1),parentBranchId1,branchOrder1);
open cur_branch;
   repeat
	fetch  cur_branch into branchId1;
	if not done then
          call pb_getSubBranchTreeForChange(branchId1,depth+1);
        end  if;
  UNTIL done END REPEAT;
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pb_getSubBranchUserTree
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_getSubBranchUserTree`(in branchId1 varchar(20) CHARSET utf8)
BEGIN
declare done INT DEFAULT 0;
declare branchName1 varchar(100);
declare parentBranchId1 varchar(20);
declare branchOrder1 int;
declare checked1 char(1);
declare flag int;

DECLARE cur_branch CURSOR for
select branchid from t_system_branch where parentBranchId=branchId1 order by branchOrder;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
SET @@max_sp_recursion_depth = 10;
select branchName into branchName1 from t_system_branch where branchId=branchId1;


select parentBranchId  into parentBranchId1 from t_system_branch where branchId=branchId1;
select branchOrder into branchOrder1 from t_system_branch where branchId=branchId1;
insert into tmp_branchTree(branchId,branchName,parentBranchId,branchOrder,checked) values(branchId1,branchName1,parentBranchId1,branchOrder1,1);

set flag=1;

open cur_branch;
   repeat
	fetch  cur_branch into branchId1;
	if not done then
         set flag=0;
          call pb_getSubBranchUserTree(branchId1);
        end  if;
  UNTIL done END REPEAT;

if flag=1 then
        call pb_getUserTree(branchId1);
end if;

END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pb_getSubPermission
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_getSubPermission`(in permissionId1 varchar(20) CHARSET utf8,out result varchar(1000) CHARSET utf8)
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
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pb_getsuperPermission
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_getsuperPermission`(in permissionId1 int(13))
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
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pb_getUserByBranchId
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_getUserByBranchId`(branchId varchar(20) CHARSET utf8)
BEGIN
declare allSubBranch varchar(1000);
select fun_getSubBranch(branchId) into allSubBranch;
set @SqlCmd=concat("select id,userId,groupId,userName,passwd from t_system_user
where branchId in(",allSubBranch,")");
 PREPARE stmt1 FROM @SqlCmd;
EXECUTE stmt1 ;
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pb_getUserTree
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_getUserTree`(in branchId1 varchar(20) CHARSET utf8)
BEGIN
 declare concount int(11);      /*需任务的任务数*/

  select ifnull(count(*),0) into concount from t_system_user where branchId=branchId1;

  if concount!=0 then
insert into tmp_branchTree(branchId,branchName,parentBranchId,branchOrder,checked)
select  concat(id,'_ex'),username,branchId1 ,1,0 from t_system_user where branchId=branchId1;
 end if;

END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pro_delPatternById
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pro_delPatternById`(id1 varchar(20) CHARSET utf8,out info varchar(10) CHARSET utf8)
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
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Proc_getAllSuperBranch
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_getAllSuperBranch`(idkey varchar(20),out result varchar(8000))
BEGIN
/*查询上级机构信息,递归查询到根结点(不包括本结点)*/
declare parentidkey varchar(20);/*临时根结点*/
declare result_tmp varchar(8000) default '';/*临时结果集*/
set result_tmp=idkey ;
  lab1:while true do/*定义标签,为退出循环服务*/
     /*查询上级父结点*/
      select parentBranchId into parentidkey from t_system_branch where branchId=idkey;
      /*当月到达根时退出*/
      if parentidkey='-1' or parentidkey is null  then
          leave LAB1;
      else
      /*连接每个根数据*/
         set result_tmp=concat(parentidkey,',',result_tmp);
     /*设置根的设置*/
         set idkey=parentidkey;
      end if;
  end while lab1;
  set result=result_tmp;
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for proc_getAllsuperMenu
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_getAllsuperMenu`(in permissionId1 varchar(20))
BEGIN
declare SJ_PermissionId varchar(20);
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
END;;
DELIMITER ;

-- ----------------------------
-- Function structure for FB_GetParm_ById
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `FB_GetParm_ById`(parmid varchar(30) CHARSET utf8,type_id varchar(30) CHARSET utf8) RETURNS char(100) CHARSET utf8
begin
/*
  ????????????????????????,type_id??????,id????,????????name
*/
declare v_value varchar(100);
set v_value='';
if parmid is null then
   return '';
end if;
if type_id='user' then
       select username  into v_value from t_system_user where userid=parmid  limit 1;
elseif type_id='pattern' then
      select PatternName into v_value  from T_Pattern where id=parmid limit 1;
elseif type_id='dictionary' then
      select dictvalue into  v_value from t_dictionaryd where id=parmid limit 1;
else
    set v_value='';
end if;
return trim(v_value);
end;;
DELIMITER ;

-- ----------------------------
-- Function structure for FB_GetSeqn
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `FB_GetSeqn`(in_seq_name varchar(30) CHARSET utf8) RETURNS varchar(20) CHARSET utf8
begin
	select ifnull(value,0) into @v_value from td_sequences where seq_name=@in_seq_name;
        select CURDATE()+0  into @v_date ;
        select lpad(concat(@v_value,''),8,'0') into @v_result;
        select concat(@v_date,@v_result) into @v_result;
        return @v_result;
end;;
DELIMITER ;

-- ----------------------------
-- Function structure for fun_getParentPermission
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `fun_getParentPermission`(permissionId1 int) RETURNS int(11)
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
END;;
DELIMITER ;

-- ----------------------------
-- Function structure for fun_getSubBranch
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `fun_getSubBranch`(branchId1 varchar(20) CHARSET utf8) RETURNS varchar(1000) CHARSET utf8
BEGIN
declare done INT DEFAULT 0;
declare result varchar(1000);
declare cur1 Cursor for  select branchId  from t_system_branch where parentBranchId=branchId1;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
SET @@max_sp_recursion_depth = 10;
set result=branchId1;
   open cur1;
    repeat
	fetch  cur1 into branchId1;
	if not done then
           call pb_getSubBranch(branchId1, @a);
           set result=concat(result,'\',\'',@a);


        end  if;
    UNTIL done END REPEAT;
    close cur1;
if(result is not null)then
set result=concat('\'',result,'\'');
end if;
return result;
END;;
DELIMITER ;

-- ----------------------------
-- Function structure for Func_getAllSuperBranch
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `Func_getAllSuperBranch`(branchId1 varchar(20)) RETURNS varchar(8000) CHARSET utf8
BEGIN
call Proc_getAllSuperBranch(branchId1,@tmp);
return  @tmp;
END;;
DELIMITER ;

-- ----------------------------
-- Function structure for seqformat
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `seqformat`(in_seq_name char(200) CHARSET utf8) RETURNS char(100) CHARSET utf8
begin
	update td_sequences set value=value + 1 where seq_name =in_seq_name;
	select ifnull(value,0) into @v_value from td_sequences where seq_name=in_seq_name;
        select CURDATE()+0  into @v_date ;
        select lpad(concat(@v_value,''),8,'0') into @v_result;
        select concat(@v_date,@v_result) into @v_result;
        return @v_result;
end;;
DELIMITER ;

-- ----------------------------
-- Function structure for seqTableName
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `seqTableName`() RETURNS char(100) CHARSET utf8
BEGIN
update td_sequences set value=value + 1 where seq_name ='t_pattern_name';
	select ifnull(value,0) into @v_value from td_sequences where seq_name='t_pattern_name';
       
        select lpad(concat(@v_value,''),4,'0') into @v_result;
        return concat('u_',@v_result);
END;;
DELIMITER ;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_attendance` VALUES ('1', '1', '24.999999,45.898989', '24.888979', '118.611115', '2011-01-13 16:54:53');
INSERT INTO `t_attendance` VALUES ('2', '1', '24.999999,45.898989', '24.889979', '118.610115', '2011-01-13 16:55:21');
INSERT INTO `t_dailywork` VALUES ('2011012100000040', '2011年旺旺食品促销活动', '13', '10', '', '日', '1', '2011012100000008', '2011-01-21 16:36:34', null, null, '1_ex,2011012100000040_ex,2011012100000041_ex,2011012100000042_ex,', '0');
INSERT INTO `t_dailyworkexcute` VALUES ('2011012100000018', '2011012100000040', '1', '0', '0', null);
INSERT INTO `t_dailyworkexcute` VALUES ('2011012100000019', '2011012100000040', '2011012100000040', '0', '0', null);
INSERT INTO `t_dailyworkexcute` VALUES ('2011012100000020', '2011012100000040', '2011012100000041', '0', '0', null);
INSERT INTO `t_dailyworkexcute` VALUES ('2011012100000021', '2011012100000040', '2011012100000042', '0', '0', null);
INSERT INTO `t_declare` VALUES ('2011012100000006', '2011年1月旺旺客户拜访', '16', '11', null, '日', '1', '2011012100000010', '2011-01-21 16:52:26', null, null, null, '0');
INSERT INTO `t_dictionary` VALUES ('1', '采集类别', null, '0', '0');
INSERT INTO `t_dictionary` VALUES ('10', '职务', '', '1', '2003');
INSERT INTO `t_dictionary` VALUES ('12', '职称', '', '1', '2002');
INSERT INTO `t_dictionary` VALUES ('2', '服务态度', null, '0', '0');
INSERT INTO `t_dictionary` VALUES ('3', '商品名称', null, '0', '0');
INSERT INTO `t_dictionary` VALUES ('4', '事件', '', '1', '2001');
INSERT INTO `t_dictionary` VALUES ('5', '工作类别', '', '1', '1002');
INSERT INTO `t_dictionary` VALUES ('6', '任务级别', '', '1', '1001');
INSERT INTO `t_dictionary` VALUES ('7', '申报类别', '', '1', '1003');
INSERT INTO `t_dictionary` VALUES ('8', '督办类别', '', '1', '1004');
INSERT INTO `t_dictionaryd` VALUES ('1', '1', '质量', '1');
INSERT INTO `t_dictionaryd` VALUES ('10', '6', '日常任务', '1');
INSERT INTO `t_dictionaryd` VALUES ('11', '6', '紧急任务', '1');
INSERT INTO `t_dictionaryd` VALUES ('12', '6', '特殊任务', '1');
INSERT INTO `t_dictionaryd` VALUES ('13', '5', '日常工作', '1');
INSERT INTO `t_dictionaryd` VALUES ('14', '5', '类别2', '1');
INSERT INTO `t_dictionaryd` VALUES ('15', '5', '类别3', '1');
INSERT INTO `t_dictionaryd` VALUES ('16', '7', '申报1', '1');
INSERT INTO `t_dictionaryd` VALUES ('17', '7', '申报2', '1');
INSERT INTO `t_dictionaryd` VALUES ('18', '8', '督办1', '1');
INSERT INTO `t_dictionaryd` VALUES ('19', '8', '督办2', '1');
INSERT INTO `t_dictionaryd` VALUES ('2', '1', '数量', '1');
INSERT INTO `t_dictionaryd` VALUES ('20', '4', '确认', '1');
INSERT INTO `t_dictionaryd` VALUES ('21', '4', '驳回', '1');
INSERT INTO `t_dictionaryd` VALUES ('3', '2', '非常好', '1');
INSERT INTO `t_dictionaryd` VALUES ('4', '2', '很好', '1');
INSERT INTO `t_dictionaryd` VALUES ('5', '2', '普通', '1');
INSERT INTO `t_dictionaryd` VALUES ('6', '2', '恶劣', '1');
INSERT INTO `t_dictionaryd` VALUES ('7', '3', '饼干', '1');
INSERT INTO `t_dictionaryd` VALUES ('8', '3', '矿泉水', '1');
INSERT INTO `t_dictionaryd` VALUES ('80', '10', '无', '1');
INSERT INTO `t_dictionaryd` VALUES ('81', '10', '普通员工', '1');
INSERT INTO `t_dictionaryd` VALUES ('82', '10', '项目经理', '1');
INSERT INTO `t_dictionaryd` VALUES ('83', '10', '部门经理', '1');
INSERT INTO `t_dictionaryd` VALUES ('84', '10', '副总经理', '1');
INSERT INTO `t_dictionaryd` VALUES ('85', '10', '总经理', '1');
INSERT INTO `t_dictionaryd` VALUES ('9', '3', '面包', '1');
INSERT INTO `t_dictionaryd` VALUES ('90', '12', '无', '1');
INSERT INTO `t_dictionaryd` VALUES ('91', '12', '助理工程师', '1');
INSERT INTO `t_dictionaryd` VALUES ('92', '12', '工程师', '1');
INSERT INTO `t_dictionaryd` VALUES ('93', '12', '高级工程师', '1');
INSERT INTO `t_pattern` VALUES ('2011012100000008', '1', '旺旺食品促销活动', '旺旺食品促销活动', 'admini', '2011-01-21 16:14:43', '1', '20,21', 'u_0030');
INSERT INTO `t_pattern` VALUES ('2011012100000009', '2', '七匹狼专营店巡访', '七匹狼专营店巡访', 'admini', '2011-01-21 16:18:02', '1', '20,21', 'u_0031');
INSERT INTO `t_pattern` VALUES ('2011012100000010', '3', '旺旺客户拜访', '旺旺客户拜访', 'admini', '2011-01-21 16:21:42', '1', '20,21', 'u_0032');
INSERT INTO `t_patternd` VALUES ('2011012100000041', '2011012100000008', null, '1', '主题', 'zt0', 'varchar', '50', null, '', '1');
INSERT INTO `t_patternd` VALUES ('2011012100000042', '2011012100000008', null, '2', '促销日期', 'cxrq1', 'date', '20', null, '', '1');
INSERT INTO `t_patternd` VALUES ('2011012100000043', '2011012100000008', null, '3', '促销商品', 'cxsp2', 'enum', '20', null, '3', '1');
INSERT INTO `t_patternd` VALUES ('2011012100000044', '2011012100000008', null, '4', '促销情况', 'cxqk3', 'varchar', '100', null, '', '1');
INSERT INTO `t_patternd` VALUES ('2011012100000045', '2011012100000008', null, '5', '照片', 'zp4', 'photo', '100', null, '', '1');
INSERT INTO `t_patternd` VALUES ('2011012100000046', '2011012100000009', null, '1', '主题', 'zt0', 'varchar', '50', null, '', '1');
INSERT INTO `t_patternd` VALUES ('2011012100000047', '2011012100000009', null, '2', '巡访日期', 'xfrq1', 'date', '20', null, '', '1');
INSERT INTO `t_patternd` VALUES ('2011012100000048', '2011012100000009', null, '3', '服务态度', 'fwtd2', 'enum', '20', null, '2', '1');
INSERT INTO `t_patternd` VALUES ('2011012100000049', '2011012100000009', null, '4', '店面形象', 'dmxx3', 'varchar', '100', null, '', '1');
INSERT INTO `t_patternd` VALUES ('2011012100000050', '2011012100000009', null, '5', '照片', 'zp4', 'photo', '100', null, '', '1');
INSERT INTO `t_patternd` VALUES ('2011012100000051', '2011012100000009', null, '6', '备注', 'bz5', 'varchar', '100', null, '', '1');
INSERT INTO `t_patternd` VALUES ('2011012100000052', '2011012100000010', null, '1', '主题', 'zt0', 'varchar', '50', null, '', '1');
INSERT INTO `t_patternd` VALUES ('2011012100000053', '2011012100000010', null, '2', '拜访日期', 'bfrq1', 'date', '20', null, '', '1');
INSERT INTO `t_patternd` VALUES ('2011012100000054', '2011012100000010', null, '3', '拜访人', 'bfr2', 'varchar', '50', null, '', '1');
INSERT INTO `t_patternd` VALUES ('2011012100000055', '2011012100000010', null, '4', '拜访反馈', 'bffk3', 'varchar', '100', null, '', '1');
INSERT INTO `t_patternd` VALUES ('2011012100000056', '2011012100000010', null, '5', '备注', 'bz4', 'varchar', '100', null, '', '1');
INSERT INTO `t_reportpattern` VALUES ('2011012100000003', '2011012100000008', '旺旺食品促销活动', '1', null, null, '2011-01-21 16:22:51', '1');
INSERT INTO `t_reportpattern` VALUES ('2011012100000004', '2011012100000009', '七匹狼专营店巡访查询', '1', null, null, '2011-01-21 16:23:41', '2');
INSERT INTO `t_reportpattern` VALUES ('2011012100000005', '2011012100000010', '旺旺客户拜访查询', '1', null, null, '2011-01-21 16:24:06', '3');
INSERT INTO `t_reportpatternc` VALUES ('2011012100000007', '2011012100000003', '2011012100000041');
INSERT INTO `t_reportpatternc` VALUES ('2011012100000008', '2011012100000003', '2011012100000042');
INSERT INTO `t_reportpatternc` VALUES ('2011012100000009', '2011012100000003', '2011012100000043');
INSERT INTO `t_reportpatternc` VALUES ('2011012100000010', '2011012100000004', '2011012100000046');
INSERT INTO `t_reportpatternc` VALUES ('2011012100000011', '2011012100000004', '2011012100000047');
INSERT INTO `t_reportpatternc` VALUES ('2011012100000012', '2011012100000004', '2011012100000048');
INSERT INTO `t_reportpatternc` VALUES ('2011012100000013', '2011012100000005', '2011012100000052');
INSERT INTO `t_reportpatternc` VALUES ('2011012100000014', '2011012100000005', '2011012100000053');
INSERT INTO `t_reportpatternc` VALUES ('2011012100000015', '2011012100000005', '2011012100000054');
INSERT INTO `t_system_branch` VALUES ('0', '组织机构', '-1', '1', null);
INSERT INTO `t_system_branch` VALUES ('2011012100000009', '综合部', '0', '1', '1111');
INSERT INTO `t_system_branch` VALUES ('2011012100000010', '业务部', '0', '2', '1111');
INSERT INTO `t_system_branch` VALUES ('2011012100000011', '事业部', '0', '3', '1111');
INSERT INTO `t_system_group` VALUES ('1', '系统管理员', '系统管理员', '1');
INSERT INTO `t_system_group` VALUES ('2011010400000030', '普通用户', '', '1');
INSERT INTO `t_system_grouprole` VALUES ('1', '1', '1');
INSERT INTO `t_system_grouprole` VALUES ('2011010400000008', '2011010400000030', '2010120800000029');
INSERT INTO `t_system_permission` VALUES ('0', '菜单', null, null, '-1', '1', '1', null);
INSERT INTO `t_system_permission` VALUES ('2000', '业务管理', null, null, '0', '1', '2000', '1');
INSERT INTO `t_system_permission` VALUES ('2001', '日常工作', 'work/tdailywork', null, '2000', '1', '2001', '1');
INSERT INTO `t_system_permission` VALUES ('2002', '督办工作', 'work/ttomonitor', null, '2000', '1', '2002', '1');
INSERT INTO `t_system_permission` VALUES ('2003', '事项申报', 'work/tdeclare', null, '2000', '1', '2003', '1');
INSERT INTO `t_system_permission` VALUES ('5000', '自定义管理', null, null, '0', '1', '5000', '1');
INSERT INTO `t_system_permission` VALUES ('5001', '反馈模板', '/custom', 'list&actionType=toQueryPage', '5000', '1', '5001', '1');
INSERT INTO `t_system_permission` VALUES ('5005', '报表模板', '/report/treportpattern', '', '5000', '1', '5005', '1');
INSERT INTO `t_system_permission` VALUES ('6000', '报表管理', null, null, '0', '1', '6000', '1');
INSERT INTO `t_system_permission` VALUES ('6001', '业务报表', 'customReport', '', '6000', '1', '6001', '1');
INSERT INTO `t_system_permission` VALUES ('6002', '考勤查询', '/report/repattendance', null, '6000', '1', '6002', '1');
INSERT INTO `t_system_permission` VALUES ('9000', '系统管理', '', null, '0', '1', '9000', '1');
INSERT INTO `t_system_permission` VALUES ('9006', '组织机构', 'branch', 'list', '9000', '1', '9006', '1');
INSERT INTO `t_system_permission` VALUES ('9007', '用户管理', 'user', null, '9000', '1', '9007', '1');
INSERT INTO `t_system_permission` VALUES ('9008', '用户组', 'group', '', '9000', '1', '9008', '1');
INSERT INTO `t_system_permission` VALUES ('9009', '角色权限', 'role', '', '9000', '1', '9009', '1');
INSERT INTO `t_system_permission` VALUES ('9010', '数据字典', 'dictionary', '', '9000', '1', '9010', '1');
INSERT INTO `t_system_permission` VALUES ('9099', '系统菜单', 'permission', 'list', '9000', '1', '9099', '1');
INSERT INTO `t_system_role` VALUES ('1', '超级用户', '1');
INSERT INTO `t_system_role` VALUES ('2010120800000029', '普通用户', '1');
INSERT INTO `t_system_rp` VALUES ('1', '1', '0');
INSERT INTO `t_system_rp` VALUES ('10', '1', '5001');
INSERT INTO `t_system_rp` VALUES ('11', '1', '9000');
INSERT INTO `t_system_rp` VALUES ('12', '1', '2000');
INSERT INTO `t_system_rp` VALUES ('13', '1', '5000');
INSERT INTO `t_system_rp` VALUES ('14', '1', '9099');
INSERT INTO `t_system_rp` VALUES ('15', '1', '9006');
INSERT INTO `t_system_rp` VALUES ('16', '1', '6000');
INSERT INTO `t_system_rp` VALUES ('17', '1', '6001');
INSERT INTO `t_system_rp` VALUES ('18', '1', '6002');
INSERT INTO `t_system_rp` VALUES ('19', '1', '9010');
INSERT INTO `t_system_rp` VALUES ('2', '1', '1000');
INSERT INTO `t_system_rp` VALUES ('2011010400000007', '2011010400000035', '0');
INSERT INTO `t_system_rp` VALUES ('2011010400000008', '2011010400000035', '2000');
INSERT INTO `t_system_rp` VALUES ('2011010400000009', '2011010400000035', '2001');
INSERT INTO `t_system_rp` VALUES ('2011010400000010', '2011010400000035', '2002');
INSERT INTO `t_system_rp` VALUES ('2011010400000011', '2011010400000035', '2003');
INSERT INTO `t_system_rp` VALUES ('2011010400000013', '2011010400000035', '5000');
INSERT INTO `t_system_rp` VALUES ('2011010400000014', '2011010400000035', '5001');
INSERT INTO `t_system_rp` VALUES ('2011011300000036', '1', '5000');
INSERT INTO `t_system_rp` VALUES ('2011011300000037', '1', '5001');
INSERT INTO `t_system_rp` VALUES ('2011011300000038', '1', '5005');
INSERT INTO `t_system_rp` VALUES ('2011012100000019', '2010120800000029', '0');
INSERT INTO `t_system_rp` VALUES ('2011012100000020', '2010120800000029', '2000');
INSERT INTO `t_system_rp` VALUES ('2011012100000021', '2010120800000029', '2001');
INSERT INTO `t_system_rp` VALUES ('2011012100000022', '2010120800000029', '2002');
INSERT INTO `t_system_rp` VALUES ('2011012100000023', '2010120800000029', '2003');
INSERT INTO `t_system_rp` VALUES ('2011012100000024', '2010120800000029', '6000');
INSERT INTO `t_system_rp` VALUES ('2011012100000025', '2010120800000029', '6001');
INSERT INTO `t_system_rp` VALUES ('2011012100000026', '2010120800000029', '6002');
INSERT INTO `t_system_rp` VALUES ('3', '1', '9007');
INSERT INTO `t_system_rp` VALUES ('4', '1', '9008');
INSERT INTO `t_system_rp` VALUES ('5', '1', '9009');
INSERT INTO `t_system_rp` VALUES ('6', '1', '2001');
INSERT INTO `t_system_rp` VALUES ('7', '1', '2002');
INSERT INTO `t_system_rp` VALUES ('8', '1', '2003');
INSERT INTO `t_system_user` VALUES ('1', '1', '1', 'admini', 'B1C21019AFD435194216C87F414E6CCE', 'admini', '', '90', '80', '0', '', '', '1', '2010-12-27 03:35:46', '', null, '', '', '', '', '', '2011012100000009', '1', null, null, null, null);
INSERT INTO `t_system_user` VALUES ('2011012100000040', '1', '2011010400000030', '张三', 'E10ADC3949BA59ABBE56E057F20F883E', '123456', '张三', '91', '83', '1', null, null, '1', '2011-01-21 16:09:02', null, '2011-01-21', null, null, null, null, null, '2011012100000009', '1', null, null, null, null);
INSERT INTO `t_system_user` VALUES ('2011012100000041', '2', '2011010400000030', '李四', 'E10ADC3949BA59ABBE56E057F20F883E', '123456', '李四', '92', '82', '1', null, null, '1', '2011-01-21 16:09:34', null, '2011-01-21', null, null, null, null, null, '2011012100000010', '1', null, null, null, null);
INSERT INTO `t_system_user` VALUES ('2011012100000042', '3', '2011010400000030', '王五', 'E10ADC3949BA59ABBE56E057F20F883E', '123456', '王五', '93', '84', '1', null, null, '1', '2011-01-21 16:10:07', null, '2011-01-21', null, null, null, null, null, '2011012100000011', '1', null, null, null, null);
INSERT INTO `t_tomonitor` VALUES ('2011012100000010', '2011年1月七匹狼专营店巡访', '18', '10', null, '日', '1', '2011012100000009', '2011-01-21 16:45:27', null, null, '1_ex,2011012100000040_ex,2011012100000041_ex,2011012100000042_ex,', '0');
INSERT INTO `td_sequences` VALUES ('t_dailywork', '40');
INSERT INTO `td_sequences` VALUES ('t_dailyworkexcute', '21');
INSERT INTO `td_sequences` VALUES ('t_declare', '6');
INSERT INTO `td_sequences` VALUES ('t_declareexcute', '0');
INSERT INTO `td_sequences` VALUES ('t_dictionary', '0');
INSERT INTO `td_sequences` VALUES ('t_dictionaryd', '0');
INSERT INTO `td_sequences` VALUES ('t_group', '30');
INSERT INTO `td_sequences` VALUES ('t_pattern', '10');
INSERT INTO `td_sequences` VALUES ('t_patternd', '56');
INSERT INTO `td_sequences` VALUES ('t_pattern_name', '32');
INSERT INTO `td_sequences` VALUES ('t_permission', '10');
INSERT INTO `td_sequences` VALUES ('t_reportpattern', '5');
INSERT INTO `td_sequences` VALUES ('t_reportpatternc', '15');
INSERT INTO `td_sequences` VALUES ('t_reportpatternf', '0');
INSERT INTO `td_sequences` VALUES ('t_role', '35');
INSERT INTO `td_sequences` VALUES ('t_system_branch', '11');
INSERT INTO `td_sequences` VALUES ('t_system_rp', '26');
INSERT INTO `td_sequences` VALUES ('t_system_user', '42');
INSERT INTO `td_sequences` VALUES ('t_toMonitor', '10');
INSERT INTO `td_sequences` VALUES ('t_tomonitorexcute', '4');

-- ----------------------------
-- Trigger structure for trigger_delCustom
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `trigger_delCustom` BEFORE DELETE ON `t_pattern` FOR EACH ROW delete from t_patternd where pid = OLD.id;;
DELIMITER ;
