/*
MySQL Data Transfer
Source Host: localhost
Source Database: womobile
Target Host: localhost
Target Database: womobile
Date: 2011-1-17 10:50:20
*/

SET FOREIGN_KEY_CHECKS=0;
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
  `id` varchar(20) NOT NULL,
  `patterntype` varchar(20) NOT NULL,
  `patternname` varchar(100) NOT NULL,
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
  `State` int(11) default NULL,
  `Remark` varchar(200) default NULL,
  `Maker` varchar(20) default NULL,
  `MakeDateTime` datetime default NULL,
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
  `GroupRoleId` varchar(20) NOT NULL,
  `GroupId` varchar(20) NOT NULL,
  `RoleId` varchar(20) NOT NULL
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
  `id` varchar(20) default NULL,
  `pid` varchar(20) default NULL,
  `executorid` varchar(20) default NULL,
  `status` smallint(6) default '0' COMMENT '处理中=0，已完成=1，已评份=9',
  `score` int(11) default '0',
  `remark` varchar(200) default NULL
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
-- Table structure for u_0022
-- ----------------------------
CREATE TABLE `u_0022` (
  `id` int(11) NOT NULL auto_increment,
  `executorId` varchar(20) default NULL,
  `parentId` varchar(20) default NULL,
  `stateRemark` varchar(200) default NULL,
  `state` smallint(6) default '1',
  `bz1` varchar(20) default NULL,
  `spmc2` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for u_0024
-- ----------------------------
CREATE TABLE `u_0024` (
  `id` int(11) NOT NULL auto_increment,
  `executorId` varchar(20) default NULL,
  `parentId` varchar(20) default NULL,
  `stateRemark` varchar(200) default NULL,
  `state` smallint(6) default '1',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for u_0026
-- ----------------------------
CREATE TABLE `u_0026` (
  `id` int(11) NOT NULL auto_increment,
  `executorId` varchar(20) default NULL,
  `parentId` varchar(20) default NULL,
  `stateRemark` varchar(200) default NULL,
  `state` smallint(6) default '1',
  `zt0` varchar(100) default NULL,
  `fwtd1` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for u_0029
-- ----------------------------
CREATE TABLE `u_0029` (
  `id` int(11) NOT NULL auto_increment,
  `executorId` varchar(20) default NULL,
  `parentId` varchar(20) default NULL,
  `stateRemark` varchar(200) default NULL,
  `state` smallint(6) default '1',
  `zt0` varchar(100) default NULL,
  `spmc1` varchar(20) default NULL,
  `zt2` varchar(20) character set utf8 collate utf8_bin default NULL,
  `spmc2` varchar(20) default NULL,
  `zt3` date default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for u_0030
-- ----------------------------
CREATE TABLE `u_0030` (
  `id` int(11) NOT NULL auto_increment,
  `executorId` varchar(20) default NULL,
  `parentId` varchar(20) default NULL,
  `stateRemark` varchar(200) default NULL,
  `state` smallint(6) default '1',
  `lsr0` varchar(100) default NULL,
  `sr1` varchar(20) default '0',
  `rg2` varchar(20) default NULL,
  `rq3` date default NULL,
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
INSERT INTO `t_dailywork` VALUES ('1', '2010年冬季服装销售情况调查', '13', '日常工作', '', '周', '100', '2010121000000005', '2010-11-08 00:00:00', 'admini', '测试', null, '0');
INSERT INTO `t_dailywork` VALUES ('2', '2010年盼盼食品全国专营店巡检', '13', '日常工作', '', '周', '150', '2010121000000005', '2010-11-09 00:00:00', 'admini', '测试二', null, '0');
INSERT INTO `t_dailywork` VALUES ('2010120100000036', 'ddd', '13', '10', '', '日', '1', '2010121000000005', '2010-12-01 00:00:00', 'admini', null, '1', '-1');
INSERT INTO `t_dailywork` VALUES ('2010121000000037', 'test', '13', '11', '', '日', '2', '2010121000000005', '2010-12-10 15:52:51', null, null, '1', '-1');
INSERT INTO `t_dailywork` VALUES ('2010121000000038', '测试', '13', '12', '', '日', '4', '2010121000000005', '2010-12-10 16:08:48', null, null, '1', '-1');
INSERT INTO `t_dailywork` VALUES ('2011011300000039', '元旦工作单', '13', '10', '', '日', null, '2011011300000009', '2011-01-13 16:24:43', null, null, '1', '0');
INSERT INTO `t_dailyworkexcute` VALUES ('1', '1', '1', '0', '0', '0');
INSERT INTO `t_dailyworkexcute` VALUES ('2', '1', '1', '0', '0', '0');
INSERT INTO `t_dailyworkexcute` VALUES ('2010121000000007', '2010120100000036', '1', null, null, null);
INSERT INTO `t_dailyworkexcute` VALUES ('2011010400000012', '2010121000000038', '1', '0', '0', null);
INSERT INTO `t_dailyworkexcute` VALUES ('2011011300000013', '2011011300000039', '1', '0', '0', null);
INSERT INTO `t_dailyworkexcute` VALUES ('3', '2', '1', '0', null, null);
INSERT INTO `t_declare` VALUES ('1', '商品配送申请', '日常工作', '日常工作', null, '天', '1', '3', '2010-11-11 00:00:00', 'admini', null, 'admini', '0');
INSERT INTO `t_dictionary` VALUES ('1', '采集类别', null, '0', '0');
INSERT INTO `t_dictionary` VALUES ('10', '职务', '', '0', '2003');
INSERT INTO `t_dictionary` VALUES ('11', '事件', '', '1', '2001');
INSERT INTO `t_dictionary` VALUES ('12', '职称', '', '0', '2002');
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
INSERT INTO `t_dictionaryd` VALUES ('21', '11', '驳回', '1');
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
INSERT INTO `t_pattern` VALUES ('1', '1', '日常巡检', '日常巡检工作', 'admini', '2010-12-10 22:18:05', '1', '', 'u_0022');
INSERT INTO `t_pattern` VALUES ('2010121000000003', '2', '服务态度回访', '', 'admini', '2010-12-11 16:06:01', '1', '20,21', 'u_0026');
INSERT INTO `t_pattern` VALUES ('2010121000000005', '1', 'test', '', 'admini', '2010-12-20 16:37:28', '1', '20,21', 'u_0029');
INSERT INTO `t_pattern` VALUES ('2010121100000007', '1', 'hhh', '', 'admini', '2010-12-11 15:58:45', '1', '20,21', 'u_0029');
INSERT INTO `t_pattern` VALUES ('2011011300000009', '1', 'aaaa', 'aaa', 'admini', '2011-01-13 16:19:26', '1', '21,20', 'u_0030');
INSERT INTO `t_patternd` VALUES ('1', null, '2001', '1', '主表主题', 'title', 'varchar', null, null, null, '1');
INSERT INTO `t_patternd` VALUES ('2010122000000039', '2010121000000005', '', '1', '主题', 'zt0', 'varchar', '100', null, '', '1');
INSERT INTO `t_patternd` VALUES ('2010122000000040', '2010121000000005', '', '2', '商品名称', 'spmc1', 'enum', '20', null, '3', '1');
INSERT INTO `t_patternd` VALUES ('2010122000000041', '2010121000000005', '', '3', '主题21', 'zt2', 'enum', '100', null, '1', '1');
INSERT INTO `t_patternd` VALUES ('2010122000000042', '2010121000000005', null, '4', '商品名称2', 'spmc2', 'varchar', '200', null, null, '1');
INSERT INTO `t_patternd` VALUES ('2010122000000043', '2010121000000007', null, '5', '日期', 'zt3', 'date', '', null, null, '1');
INSERT INTO `t_patternd` VALUES ('2011011300000041', '2011011300000007', null, '1', '???', 'lsr0', 'varchar', '100', null, '', '1');
INSERT INTO `t_patternd` VALUES ('2011011300000042', '2011011300000007', null, '0', '??', 'sr1', 'number', '20', null, '', '1');
INSERT INTO `t_patternd` VALUES ('2011011300000043', '2011011300000007', null, '0', '??', 'rg2', 'enum', '20', null, '10', '1');
INSERT INTO `t_patternd` VALUES ('2011011300000044', '2011011300000007', null, '0', '日期', 'rq3', 'date', '20', null, '', '1');
INSERT INTO `t_reportpattern` VALUES ('2010121000000005', '2010121000000005', '一月份统计1', '1', '查询一月份数据1', '1', '2011-01-11 23:04:32');
INSERT INTO `t_reportpattern` VALUES ('2010121000000006', '2010121100000007', '一月份统计2', '1', '查询一月份数据2', '1', '2011-01-11 23:04:32');
INSERT INTO `t_reportpatternc` VALUES ('1', '2010121000000005', '2010122000000039');
INSERT INTO `t_reportpatternc` VALUES ('10', '2010121000000006', '2010122000000041');
INSERT INTO `t_reportpatternc` VALUES ('11', '2010121000000006', '2010122000000040');
INSERT INTO `t_reportpatternc` VALUES ('12', '2010121000000006', '2010122000000039');
INSERT INTO `t_reportpatternc` VALUES ('2', '2010121000000005', '2010122000000040');
INSERT INTO `t_reportpatternc` VALUES ('4', '2010121000000005', '2010122000000041');
INSERT INTO `t_reportpatternc` VALUES ('5', '2010121000000005', '2010122000000042');
INSERT INTO `t_reportpatternc` VALUES ('6', '2010121000000005', '2010122000000043');
INSERT INTO `t_reportpatternc` VALUES ('7', '2010121000000005', '1');
INSERT INTO `t_reportpatternc` VALUES ('8', '2010121000000006', '2010122000000043');
INSERT INTO `t_reportpatternc` VALUES ('9', '2010121000000006', '2010122000000042');
INSERT INTO `t_reportpatternf` VALUES ('1', '2010121000000005', '2010122000000039');
INSERT INTO `t_reportpatternf` VALUES ('10', '2010121000000006', '2010122000000039');
INSERT INTO `t_reportpatternf` VALUES ('2', '2010121000000005', '2010122000000040');
INSERT INTO `t_reportpatternf` VALUES ('3', '2010121000000005', '2010122000000041');
INSERT INTO `t_reportpatternf` VALUES ('4', '2010121000000005', '2010122000000042');
INSERT INTO `t_reportpatternf` VALUES ('5', '2010121000000005', '2010122000000043');
INSERT INTO `t_reportpatternf` VALUES ('6', '2010121000000005', '1');
INSERT INTO `t_reportpatternf` VALUES ('7', '2010121000000006', '2010122000000042');
INSERT INTO `t_reportpatternf` VALUES ('8', '2010121000000006', '2010122000000041');
INSERT INTO `t_reportpatternf` VALUES ('9', '2010121000000006', '2010122000000040');
INSERT INTO `t_system_branch` VALUES ('0', '组织机构', '-1', '1', null);
INSERT INTO `t_system_branch` VALUES ('2', '开发部', '0', '4', null);
INSERT INTO `t_system_branch` VALUES ('2010122100000001', '办公室', '0', '2', '1111');
INSERT INTO `t_system_branch` VALUES ('2011010400000008', '中国', '0', '0', '1111');
INSERT INTO `t_system_branch` VALUES ('4', '综合部', '0', '3', null);
INSERT INTO `t_system_group` VALUES ('1', '系统管理员', '系统管理员', '1');
INSERT INTO `t_system_group` VALUES ('2011010400000030', '普通用户', '', '1');
INSERT INTO `t_system_grouprole` VALUES ('1', '1', '1');
INSERT INTO `t_system_grouprole` VALUES ('2011010400000008', '2011010400000030', '2010120800000029');
INSERT INTO `t_system_permission` VALUES ('0', '菜单', null, null, '-1', '1', '1', null);
INSERT INTO `t_system_permission` VALUES ('2000', '业务管理', null, null, '0', '1', '2000', '1');
INSERT INTO `t_system_permission` VALUES ('2001', '日常工作', 'work/tdailywork', null, '2000', '1', '2001', '1');
INSERT INTO `t_system_permission` VALUES ('2002', '督办工作', 'work/ttomonitor', null, '2000', '1', '2002', '1');
INSERT INTO `t_system_permission` VALUES ('2003', '事项申报', 'work/tdeclare', null, '2000', '1', '2003', '1');
INSERT INTO `t_system_permission` VALUES ('2004', '位置考勤', null, null, '2000', '1', '2004', '1');
INSERT INTO `t_system_permission` VALUES ('5000', '自定义管理', null, null, '0', '1', '5000', '1');
INSERT INTO `t_system_permission` VALUES ('5001', '自定义模板', '/custom', 'list&actionType=toQueryPage', '5000', '1', '5001', '1');
INSERT INTO `t_system_permission` VALUES ('5005', '报表查询', 'customReport', null, '5000', '1', '5005', '1');
INSERT INTO `t_system_permission` VALUES ('9000', '系统管理', '', null, '0', '1', '9000', '1');
INSERT INTO `t_system_permission` VALUES ('9006', '组织机构', 'branch', 'list', '9000', '1', '9006', '1');
INSERT INTO `t_system_permission` VALUES ('9007', '用户管理', 'user', null, '9000', '1', '9007', '1');
INSERT INTO `t_system_permission` VALUES ('9008', '用户组', 'group', '', '9000', '1', '9008', '1');
INSERT INTO `t_system_permission` VALUES ('9009', '角色权限', 'role', '', '9000', '1', '9009', '1');
INSERT INTO `t_system_permission` VALUES ('9099', '系统菜单', 'permission', 'list', '9000', '1', '9099', '1');
INSERT INTO `t_system_role` VALUES ('1', '超级用户', '1');
INSERT INTO `t_system_role` VALUES ('2010120800000029', '普通用户', '1');
INSERT INTO `t_system_role` VALUES ('2011010400000035', '在一', '1');
INSERT INTO `t_system_rp` VALUES ('2011010', null, null);
INSERT INTO `t_system_rp` VALUES ('2011010400000007', '2011010400000035', '0');
INSERT INTO `t_system_rp` VALUES ('2011010400000008', '2011010400000035', '2000');
INSERT INTO `t_system_rp` VALUES ('2011010400000009', '2011010400000035', '2001');
INSERT INTO `t_system_rp` VALUES ('2011010400000010', '2011010400000035', '2002');
INSERT INTO `t_system_rp` VALUES ('2011010400000011', '2011010400000035', '2003');
INSERT INTO `t_system_rp` VALUES ('2011010400000012', '2011010400000035', '2004');
INSERT INTO `t_system_rp` VALUES ('2011010400000013', '2011010400000035', '5000');
INSERT INTO `t_system_rp` VALUES ('2011010400000014', '2011010400000035', '5001');
INSERT INTO `t_system_rp` VALUES ('2011011300000032', '1', '0');
INSERT INTO `t_system_rp` VALUES ('2011011300000033', '1', '2000');
INSERT INTO `t_system_rp` VALUES ('2011011300000034', '1', '2001');
INSERT INTO `t_system_rp` VALUES ('2011011300000035', '1', '2002');
INSERT INTO `t_system_rp` VALUES ('2011011300000036', '1', '5000');
INSERT INTO `t_system_rp` VALUES ('2011011300000037', '1', '5001');
INSERT INTO `t_system_rp` VALUES ('2011011300000038', '1', '5005');
INSERT INTO `t_system_rp` VALUES ('2011011300000039', '1', '9000');
INSERT INTO `t_system_rp` VALUES ('2011011300000040', '1', '9006');
INSERT INTO `t_system_rp` VALUES ('2011011300000041', '1', '9007');
INSERT INTO `t_system_rp` VALUES ('2011011300000042', '1', '9008');
INSERT INTO `t_system_rp` VALUES ('2011011300000043', '1', '9009');
INSERT INTO `t_system_rp` VALUES ('2011011300000044', '1', '9099');
INSERT INTO `t_system_rp` VALUES ('2011011300000045', '2010120800000029', '0');
INSERT INTO `t_system_rp` VALUES ('2011011300000046', '2010120800000029', '2000');
INSERT INTO `t_system_rp` VALUES ('2011011300000047', '2010120800000029', '2001');
INSERT INTO `t_system_rp` VALUES ('2011011300000048', '2010120800000029', '2002');
INSERT INTO `t_system_rp` VALUES ('2011011300000049', '2010120800000029', '5000');
INSERT INTO `t_system_rp` VALUES ('201101130000005', '1', '2003');
INSERT INTO `t_system_rp` VALUES ('2011011300000050', '2010120800000029', '5005');
INSERT INTO `t_system_rp` VALUES ('2011011300000051', '2010120800000029', '9000');
INSERT INTO `t_system_rp` VALUES ('2011011300000052', '2010120800000029', '9006');
INSERT INTO `t_system_rp` VALUES ('2011011300000053', '2010120800000029', '9007');
INSERT INTO `t_system_rp` VALUES ('2011011300000054', '2010120800000029', '9008');
INSERT INTO `t_system_rp` VALUES ('2011011300000055', '2010120800000029', '9009');
INSERT INTO `t_system_rp` VALUES ('2011011300000056', '2010120800000029', '9099');
INSERT INTO `t_system_rp` VALUES ('201101130000006', '1', '2004');
INSERT INTO `t_system_user` VALUES ('1', '1', '1', 'admini', 'B1C21019AFD435194216C87F414E6CCE', 'admini', null, null, null, null, null, null, '1', '2010-12-27 03:35:46', null, null, null, null, null, null, null, '0', '1', null, null, null, null);
INSERT INTO `t_system_user` VALUES ('2010122700000032', 'aaa', '1', 'aaa', '0B4E7A0E5FE84AD35FB5F95B9CEEAC79', 'aaaaaa', null, '90', '80', '1', null, null, '1', '2010-12-27 03:35:46', null, null, null, null, null, null, null, '0', '1', '12', '08:15:00', '21:45:00', '1,2,3,4');
INSERT INTO `t_system_user` VALUES ('2010122700000034', 'eee', '1', 'eee', 'CD87CD5EF753A06EE79FC75DC7CFE66C', 'eeeeee', '', '90', '80', '0', '', '', '1', '2010-12-27 03:52:51', '', null, '', '', '', '', '', '0', '1', '13', '20:00:00', '20:00:00', '1,2,3');
INSERT INTO `t_system_user` VALUES ('2010122700000037', 'bbb', '1', 'bbb', '875F26FDB1CECF20CEB4CA028263DEC6', 'bbbbbb', '', '90', '80', '0', '', '', '1', '2010-12-27 11:28:17', '', null, '', '', '', '', '', '2010122100000001', '1', '12', '21:15:00', '21:45:00', '1,2');
INSERT INTO `t_system_user` VALUES ('2010122700000038', '333', '1', '333', '1A100D2C0DAB19C4430E7D73762B3423', '333333', '', '90', '80', '0', '', '', '1', '2010-12-27 11:37:36', '', null, '', '', '', '', '', '0', '1', '12', '23:10:00', '23:45:00', '1,2');
INSERT INTO `t_system_user` VALUES ('2011010300000039', '111', '1', 'aaa', '0B4E7A0E5FE84AD35FB5F95B9CEEAC79', 'aaaaaa', '', '90', '80', '0', '', '', '1', '2011-01-03 23:03:52', '', null, '', '', '', '', '', '2010122400000001', '1', null, null, null, null);
INSERT INTO `t_system_user` VALUES ('2011010400000039', '11', '1', '张三', 'E10ADC3949BA59ABBE56E057F20F883E', '123456', '222', '90', '80', '0', '', '', '1', '2011-01-04 10:02:00', '', null, '', '', '', '', '', '0', '1', '30', '08:18:00', '15:18:00', '1,2');
INSERT INTO `t_system_user` VALUES ('2011010400000040', 'aaaaaa', '2011010400000037', 'aaaaaa', '0B4E7A0E5FE84AD35FB5F95B9CEEAC79', 'aaaaaa', '', '90', '80', '0', '', '', '1', '2011-01-04 00:45:34', '', null, '', '', '', '', '', '0', '1', '12', '01:00:00', '01:30:00', '1,2,3');
INSERT INTO `t_system_user` VALUES ('2011010400000041', 'bbb', '2011010400000037', 'bbbbbb', '875F26FDB1CECF20CEB4CA028263DEC6', 'bbbbbb', '', '90', '80', '0', '', '', '1', '2011-01-04 01:04:22', '', null, '', '', '', '', '', '2', '1', '12', '01:59:00', '01:59:00', '1,2,3,4,5,6,7');
INSERT INTO `t_tomonitor` VALUES ('1', '代理商门店及服务情况抽查', '临时工作', '紧急任务', null, '天', '2', '2', '2010-11-11 00:00:00', 'admini', null, 'admini', '0');
INSERT INTO `t_tomonitorexcute` VALUES ('1', '1', 'admini', '0', '0', null);
INSERT INTO `td_sequences` VALUES ('t_dailywork', '39');
INSERT INTO `td_sequences` VALUES ('t_dailyworkexcute', '13');
INSERT INTO `td_sequences` VALUES ('t_declare', '5');
INSERT INTO `td_sequences` VALUES ('t_declareexcute', '0');
INSERT INTO `td_sequences` VALUES ('t_group', '30');
INSERT INTO `td_sequences` VALUES ('t_pattern', '9');
INSERT INTO `td_sequences` VALUES ('t_patternd', '44');
INSERT INTO `td_sequences` VALUES ('t_pattern_name', '30');
INSERT INTO `td_sequences` VALUES ('t_permission', '13');
INSERT INTO `td_sequences` VALUES ('t_role', '35');
INSERT INTO `td_sequences` VALUES ('t_system_branch', '8');
INSERT INTO `td_sequences` VALUES ('t_system_rp', '57');
INSERT INTO `td_sequences` VALUES ('t_system_user', '39');
INSERT INTO `td_sequences` VALUES ('t_toMonitor', '8');
INSERT INTO `td_sequences` VALUES ('t_tomonitorexcute', '0');
INSERT INTO `u_0029` VALUES ('1', '1', '2010120100000036', '', '1', 'bbb', '7', '1', '8', '2011-01-10');
INSERT INTO `u_0029` VALUES ('2', '1', '2010120100000036', null, '1', 'aaa', '7', null, '8', '2011-01-03');
INSERT INTO `u_0029` VALUES ('3', '1', '2010120100000036', '', '1', 'aaa', '8', '1', '7', '2011-01-31');
INSERT INTO `u_0029` VALUES ('4', '1', '2010120100000036', '', '1', 'bbb', '8', '1', '7', '2011-01-11');
INSERT INTO `u_0029` VALUES ('5', '1', '2010120100000036', '', '1', 'bbb', '7', '1', '8', '2011-01-10');
INSERT INTO `u_0029` VALUES ('9', '1', '2010120100000036', '', '1', 'bbb', '7', '1', '8', '2011-01-10');
INSERT INTO `u_0029` VALUES ('10', '1', '2010120100000036', null, '1', 'aaa', '7', null, '8', '2011-01-03');
INSERT INTO `u_0029` VALUES ('11', '1', '2010120100000036', '', '1', 'aaa', '8', '1', '7', '2011-01-31');
INSERT INTO `u_0029` VALUES ('12', '1', '2010120100000036', '', '1', 'bbb', '8', '1', '7', '2011-01-11');
INSERT INTO `u_0029` VALUES ('13', '1', '2010120100000036', '', '1', 'bbb', '7', '1', '8', '2011-01-10');
INSERT INTO `u_0029` VALUES ('14', '1', '2010120100000036', null, '1', 'aaa', '7', null, '8', '2011-01-03');
INSERT INTO `u_0029` VALUES ('15', '1', '2010120100000036', '', '1', 'aaa', '8', '1', '7', '2011-01-31');
INSERT INTO `u_0029` VALUES ('16', '1', '2010120100000036', '', '1', 'bbb', '8', '1', '7', '2011-01-11');
INSERT INTO `u_0029` VALUES ('17', '1', '2010120100000036', '', '1', 'bbb', '7', '1', '8', '2011-01-10');
INSERT INTO `u_0029` VALUES ('18', '1', '2010120100000036', null, '1', 'aaa', '7', null, '8', '2011-01-03');
INSERT INTO `u_0029` VALUES ('19', '1', '2010120100000036', '', '1', 'aaa', '8', '1', '7', '2011-01-31');
INSERT INTO `u_0029` VALUES ('20', '1', '2010120100000036', '', '1', 'bbb', '8', '1', '7', '2011-01-11');
INSERT INTO `u_0029` VALUES ('21', '1', '2010120100000036', '', '1', 'bbb', '7', '1', '8', '2011-01-10');
INSERT INTO `u_0029` VALUES ('22', '1', '2010120100000036', null, '1', 'aaa', '7', null, '8', '2011-01-03');
INSERT INTO `u_0029` VALUES ('24', '1', '2010120100000036', '', '1', 'bbb', '8', '1', '7', '2011-01-11');
INSERT INTO `u_0029` VALUES ('25', '1', '2010120100000036', '', '1', 'bbb', '7', '1', '8', '2011-01-10');
INSERT INTO `u_0029` VALUES ('26', '1', '2010120100000036', null, '1', 'aaa', '7', null, '8', '2011-01-03');
INSERT INTO `u_0029` VALUES ('33', '1', '2010120100000036', '', '1', 'aaa', '8', '1', '7', '2011-01-31');
INSERT INTO `u_0029` VALUES ('37', '1', '2010120100000036', '', '1', 'aaa', '8', '1', '7', '2011-01-31');
INSERT INTO `u_0029` VALUES ('38', '1', '2010120100000036', '', '1', 'bbb', '8', '1', '7', '2011-01-11');

-- ----------------------------
-- Trigger structure for trigger_delCustom
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `trigger_delCustom` BEFORE DELETE ON `t_pattern` FOR EACH ROW delete from t_patternd where pid = OLD.id;;
DELIMITER ;
